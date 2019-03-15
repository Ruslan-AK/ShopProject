package com.Kutugin.services.impl;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ProductServise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;
    private ProductServise productServise;

    @Before
    public void init() {
        productServise = new ProductServiceImpl(productDao);
    }

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
        Mockito.when(productDao.saveProduct(product)).thenReturn(true);
        //Then
        boolean res = productServise.saveProduct(product);
        Assert.assertTrue(res);
    }

    @Test
    public void getProductsTest() {
        productServise.getProducts();
        verify(productDao).getProducts();
    }

    @Test
    public void deleteByIdTest() {
        long id = 1L;
        productServise.deleteById(id);
        verify(productDao).deleteById(id);
    }

    @Test
    public void isInDBTest() {
        //Given
        long id = 1;
        //When
        Mockito.when(productDao.isInDB(id)).thenReturn(true);
        //Then
        boolean res = productServise.isInDB(id);
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
        verify(productDao).updateProduct(id, product);
    }

    @Test
    public void getByIDTest() {
        long id = 1L;
        productServise.getByID(id);
        verify(productDao).getByID(id);
    }
}