package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.OrderDTO;
import com.sparta.northwindapi.entity.Order;
import com.sparta.northwindapi.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (int i = 0; i< orders.size(); i++) {
            orderDTOS.add(convertOrder(orders.get(i)));
        }
        return orderDTOS;
    }

    public OrderDTO getByID(int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isPresent()) {
            return convertOrder(foundOrder.get());
        } else {
            return new OrderDTO(-1,null,null,null,null,null,null,null,null,null,null);
        }
    }

    public OrderDTO addNewOrder(Order newOrder) {
        Order savedOrder = orderRepository.save(newOrder);
        if (savedOrder != null) {
            return convertOrder(savedOrder);
        } else {
            return new OrderDTO(-1,null,null,null,null,null,null,null,null,null,null);
        }
    }

    public OrderDTO update(Order order) {
        Optional<Order> optional = orderRepository.findById(order.getId());
        Order theOrder = null;
        if(optional.isPresent()) {
            theOrder = optional.get();
        } else {
            return new OrderDTO(-1,null,null,null,null,null,null,null,null,null,null);
        }
        if (order.getOrderDate() != null) {
            theOrder.setOrderDate(order.getOrderDate());
        }
        if (order.getRequiredDate() != null) {
            theOrder.setRequiredDate(order.getRequiredDate());
        }
        if (order.getShippedDate() != null) {
            theOrder.setShippedDate(order.getShippedDate());
        }
        if (order.getFreight() != null) {
            theOrder.setFreight(order.getFreight());
        }
        if (order.getShipName() != null) {
            theOrder.setShipName(order.getShipName());
        }
        if (order.getShipAddress() != null) {
            theOrder.setShipAddress(order.getShipAddress());
        }
        if (order.getShipCity() != null) {
            theOrder.setShipCity(order.getShipCity());
        }
        if (order.getShipRegion() != null) {
            theOrder.setShipRegion(order.getShipRegion());
        }
        if (order.getShipPostalCode() != null) {
            theOrder.setShipPostalCode(order.getShipPostalCode());
        }
        if (order.getShipCountry() != null) {
            theOrder.setShipCountry(order.getShipCountry());
        }
        orderRepository.save(theOrder);
        theOrder = orderRepository.findById(order.getId()).get();
        return new OrderDTO(theOrder.getId(), theOrder.getOrderDate(), theOrder.getRequiredDate(),
                theOrder.getShippedDate(), theOrder.getFreight(), theOrder.getShipName(),
                theOrder.getShipAddress(), theOrder.getShipCity(), theOrder.getShipRegion(),
                theOrder.getShipPostalCode(), theOrder.getShipCountry());
    }

    public int deleteOrder(int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isPresent()) {
            orderRepository.delete(foundOrder.get());
            return foundOrder.get().getId();
        }
        else {
            return -1;
        }
    }

    public OrderDTO convertOrder(Order theOrder) {
        OrderDTO converted = new OrderDTO(theOrder.getId(), theOrder.getOrderDate(), theOrder.getRequiredDate(),
                theOrder.getShippedDate(), theOrder.getFreight(), theOrder.getShipName(),
                theOrder.getShipAddress(), theOrder.getShipCity(), theOrder.getShipRegion(),
                theOrder.getShipPostalCode(), theOrder.getShipCountry());
        return converted;
    }
}
