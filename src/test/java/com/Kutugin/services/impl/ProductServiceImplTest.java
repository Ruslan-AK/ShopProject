package com.Kutugin.services.impl;

import com.Kutugin.dao.impl.H2DB.ProductDBDao;
import com.Kutugin.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDBDao productDBDao;
    @InjectMocks
    private ProductServiceImpl productServise;

//    @Before
//    public void init() {
//        productServise = new ProductServiceImpl(productDao);
//    }

    @Test
    public void saveProductTest() {
        //Given
        String firm = "Toshiba";
        String model = "x80";
        double price = 300.9;
        String type = "LAPTOP";
        long id = 1L;
        Product product = new Product(firm, model, price, type, id);
        //When
        Mockito.when(productDBDao.saveProduct(product)).thenReturn(id);
        //Then
        long resId = productServise.saveProduct(product);
        Assert.assertEquals(id,resId);
    }

    @Test
    public void getProductsTest() {
        productServise.getProducts();
        verify(productDBDao).getProducts();
    }

    @Test
    public void deleteByIdTest() {
        long id = 1L;
        productServise.deleteById(id);
        verify(productDBDao).deleteById(id);
    }

    @Test
    public void isPresentTest() {
        //Given
        long id = 1;
        //When
        Mockito.when(productDBDao.isPresent(id)).thenReturn(true);
        //Then
        boolean res = productServise.isPresent(id);
        Assert.assertTrue(res);
    }

    @Test
    public void updateProductTest() {
        //Given
        String firm = "Toshiba";
        String model = "x80";
        double price = 300.9;
        String type = "LAPTOP";
        long id = 1L;
        Product product = new Product(firm, model, price, type, id);
        //When
        productServise.updateProduct(id, product);
        //Then
        verify(productDBDao).updateProduct(id, product);
    }

    @Test
    public void getByIDTest() {
        long id = 1L;
        productServise.getByID(id);
        verify(productDBDao).getByID(id);
    }
}