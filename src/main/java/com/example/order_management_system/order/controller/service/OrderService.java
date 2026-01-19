package com.example.order_management_system.order.controller.service;

import com.example.order_management_system.Product.controller.entity.Product;
import com.example.order_management_system.Product.controller.repository.ProductRepository;
import com.example.order_management_system.inventory.InventoryService;
import com.example.order_management_system.order.controller.dto.OrderItemDTO;
import com.example.order_management_system.order.controller.dto.OrderRequestDTO;
import com.example.order_management_system.order.controller.dto.OrderResponseDTO;
import com.example.order_management_system.order.controller.entity.Order;
import com.example.order_management_system.order.controller.entity.OrderItems;
import com.example.order_management_system.order.controller.entity.Status;
import com.example.order_management_system.order.controller.repository.OrderRepository;
import com.example.order_management_system.user.controller.entity.User;
import com.example.order_management_system.user.controller.repository.UserRepository;
import com.example.order_management_system.user.controller.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.order_management_system.user.controller.entity.Role.USER;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {

        // STEP 1: Fetch user (mocked for now)
        User user=userRepository.findById(1L).orElseThrow(()->new NoSuchElementException("User not found"));

        // STEP 2: Validate request
        if(orderRequestDTO.getItems()==null|| orderRequestDTO.getItems().isEmpty()){
            throw new IllegalArgumentException("order item is empty");
        }

        for(OrderItemDTO items:orderRequestDTO.getItems()){
            if(items.getProductId()==null || items.getQuantity()==null || items.getQuantity()<=0){
                throw  new IllegalArgumentException("Invalid productId or quantity");
            }
        }
        // STEP 3: Fetch all products in one DB call
       List<Long> productIds= orderRequestDTO.getItems()
               .stream()
               .map(orderItemDTO -> orderItemDTO.getProductId())
               .distinct()
               .toList();

          List<Product> productList=productRepository.findAllById(productIds);

          if(productList.size()!=productIds.size()){
              throw  new NoSuchElementException("Some products are missing");
          }

        // STEP 4: Map products for easy lookup

        Map<Long,Product> productMap=productList.
                                        stream()
                                        .collect(Collectors.toMap(Product::getId,p->p));

        // STEP 5: Inventory validation (NO DB WRITE)

        for(OrderItemDTO item:orderRequestDTO.getItems()){
           Product product =productMap.get(item.getProductId());
           inventoryService.validateStock(product,item.getQuantity());
          }

        // STEP 6: Create Order
          Order order=new Order();
          order.setUser(user);
          order.setStatus(Status.PLACED);
          order.setCreatedAt(LocalDateTime.now());

          double totalAmount=0.0;

        // STEP 7: Create OrderItems

        List<OrderItems> orderItems=new ArrayList<>();
          for(OrderItemDTO itemDTO:orderRequestDTO.getItems()){
              Product product= productMap.get(itemDTO.getProductId());

              OrderItems orderItems1=new OrderItems();
              orderItems1.setOrder(order);
              orderItems1.setProduct(product);
              orderItems1.setQuantity(itemDTO.getQuantity());
              orderItems1.setPriceAtPurchase(product.getPrice());

              totalAmount += product.getPrice() * itemDTO.getQuantity();
              orderItems.add(orderItems1);

          }
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        // STEP 8: Reduce stock (NOW we modify DB state)
        for (OrderItemDTO item : orderRequestDTO.getItems()) {
            Product product = productMap.get(item.getProductId());
            inventoryService.reduceStock(product, item.getQuantity());
        }

        // STEP 9: Save order (cascade saves OrderItems)
        Order savedOrder = orderRepository.save(order);

        //convert savedorders to itemDto
        List<OrderItemDTO> itemDTOs = savedOrder.getOrderItems()
                .stream()
                .map(orderItem -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setProductId(orderItem.getProduct().getId());
                    dto.setProductName(orderItem.getProduct().getProductName());
                    dto.setQuantity(orderItem.getQuantity());
                    dto.setPrice(orderItem.getPriceAtPurchase());
                    return dto;
                })
                .toList();

        // STEP 10: Build response
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setOrderId(savedOrder.getId());
        responseDTO.setStatus(savedOrder.getStatus().name());
        responseDTO.setTotalAmount(savedOrder.getTotalAmount());
        responseDTO.setCreatedAt(savedOrder.getCreatedAt());
        responseDTO.setItems(itemDTOs);

        return responseDTO;

    }
}
