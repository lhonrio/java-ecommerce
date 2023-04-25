package com.ecommerce.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ecommerce.api.model.Product;
import com.ecommerce.api.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerTest {

  @InjectMocks ProductController productController;
  @Mock ProductService productService;
  private Product product;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetProductById() {
    Long id = 1L;
    product = new Product();
    product.setId(id);
    when(productService.getProductById(id)).thenReturn(product);

    ResponseEntity<Product> response = productController.getProductById(id);

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody().getId(), id);
  }

  @Test
  void testGetProductsByQuantityAsc() {
    List<Product> productList = new ArrayList<>();
    Product product1 = new Product();
    product1.setQuantity(1);
    productList.add(product1);
    Product product2 = new Product();
    product2.setQuantity(2);
    productList.add(product2);
    when(productService.findByQuantityAsc()).thenReturn(productList);

    List<Product> response = productController.getProductsByQuantityAsc();

    assertEquals(response.size(), 2);
    assertEquals(response.get(0).getQuantity(), 1);
    assertEquals(response.get(1).getQuantity(), 2);
  }

  @Test
  void testGetProductsByQuantityDesc() {
    List<Product> productList = new ArrayList<>();
    Product product1 = new Product();
    product1.setQuantity(2);
    productList.add(product1);
    Product product2 = new Product();
    product2.setQuantity(1);
    productList.add(product2);
    when(productService.findByQuantityDesc()).thenReturn(productList);

    List<Product> response = productController.getProductsByQuantityDesc();

    assertEquals(response.size(), 2);
    assertEquals(response.get(0).getQuantity(), 2);
    assertEquals(response.get(1).getQuantity(), 1);
  }

  @Test
  void testCreateProduct() {
    Product product = new Product();
    when(productService.createProduct(product)).thenReturn(product);

    ResponseEntity<Product> response = productController.createProduct(product);

    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertEquals(response.getBody(), product);
  }

  @Test
  void testUpdateProduct() {
    Long id = 1L;
    Product product = new Product();
    product.setId(id);
    when(productService.updateProduct(id, product)).thenReturn(product);

    ResponseEntity<Product> response = productController.updateProduct(id, product);

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody().getId(), id);
  }
}
