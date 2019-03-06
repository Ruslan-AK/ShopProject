//package com.Kutugin.dao.impl.noDB;
//
//import com.Kutugin.dao.OrderDao;
//import com.Kutugin.domain.Order;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderDaoImpl implements OrderDao {
//
//    private List<Order> orderList = new ArrayList<>();
//
//    private static OrderDao instance = new OrderDaoImpl();
//
//    private OrderDaoImpl() {
//    }
//
//    public static OrderDao getInstance() {
//        return instance;
//    }
//
//    @Override
//    public long add(Order order) {
//        orderList.add(order);
//        return order.getId();
//    }
//
//    @Override
//    public void update(long id, Order order) {
//        for (Order o : orderList) {
//            if (o.getId() == id) {
//                o.update(order);
//            }
//        }
//    }
//
//    @Override
//    public List<Order> getOrders() {
//        return orderList;
//    }
//
//    @Override
//    public void deleteById(long id) {
//        for (Order order : orderList) {
//            if (order.getId() == id) {
//                orderList.remove(order);
//            }
//        }
//    }
//
//    @Override
//    public Order getByPhoneNumber(long id) {
//        for (Order order : orderList) {
//            if (order.getId() == id)
//                return order;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean containsById(long id) {
//        for (Order order : orderList) {
//            if (order.getId() == id)
//                return true;
//        }
//        return false;
//    }
//}
