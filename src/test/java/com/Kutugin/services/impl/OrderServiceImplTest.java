//package com.Kutugin.services.impl;
//
//import com.Kutugin.dao.OrderDao;
//import com.Kutugin.domain.Client;
//import com.Kutugin.domain.Order;
//import com.Kutugin.domain.Products;
//import com.Kutugin.services.OrderService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.Mockito.verify;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OrderServiceImplTest {
//    @Mock
//    private OrderDao orderDao;
//    private OrderService orderService;
//
//    @Before
//    public void init() {
//        orderService = new OrderServiceImpl(orderDao);
//    }
//
//    @Test
//    public void summaryPrice() {
//        //given
//        Order expectedOrder = createOrder();
//        BigDecimal totPriceTest = orderService.summaryPrice(expectedOrder);
//        //When
//        BigDecimal totPriceEspected = new BigDecimal(0);
////        for (Products p : expectedOrder.getProductsList()) {
////            totPriceEspected = totPriceEspected.add(BigDecimal.valueOf(p.getProduct().getPrice()));
////        }
//        //Then
//        Assert.assertEquals(totPriceEspected, totPriceTest);
//    }
//
//    private Order createOrder() {
//        List<Products> productsList = new ArrayList<>();
//        long id = 1L;
//        String date = new Date().toString();
//        for (int i = 0; i < 10; i++) {
//            Products products = new Products();
////            products.setProduct(new Product("firm" + i, "model" + i, 100 + i, "LAPTOP"));
//            products.setAmount(products.getAmount()+1);
//            productsList.add(products);
//        }
//
//        return new Order(id, productsList, date);
//    }
//
//    @Test
//    public void addClientOrder() {
//        //Given
//        Order expectedOrder = createOrder();
//        Client testClient = new Client(1, "Vasia", "Boiko", "33", "vb@cc.com", "0676666666");
//        //When
//        orderService.addClientOrder(testClient, expectedOrder);
//        //Then
//        verify(orderDao).addClientOrder(testClient, expectedOrder);
//    }
//
//    @Test
//    public void update() {
//        //Given
//        Order order = createOrder();
//        //When
//        orderService.update(order.getId(), order);
//        //Then
//        verify(orderDao).update(order.getId(), order);
//    }
//
//    @Test
//    public void deleteById() {
//        //Given
//        Order order = createOrder();
//        //When
//        orderService.deleteById(order.getId());
//        //Then
//        verify(orderDao).deleteById(order.getId());
//    }
//
//    @Test
//    public void getByID() {
//        //Given
//        Order expectedOrder = null;
//        long id = 1L;
//        //When
//        Order actualOrder = orderService.getByID(id);
//        //Then
//        Assert.assertEquals(expectedOrder, actualOrder);
//    }
//
//    @Test
//    public void getOrdersByClient() {
//        //Given
//        long clientId = 1;
//        //When
//        orderService.getByID(clientId);
//        //Then
//        verify(orderDao).getByID(clientId);
//    }
//
//    @Test
//    public void isPresent() {
//        //Given
//        long id = 1L;
//        //When
//        orderService.isPresent(id);
//        //Then
//        verify(orderDao).isPresent(id);
//    }
//}