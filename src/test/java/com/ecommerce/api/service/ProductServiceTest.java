package com.ecommerce.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ecommerce.api.model.Product;
import com.ecommerce.api.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

  @InjectMocks private ProductService productService;
  @Mock private ProductRepository productRepository;

  private Product product1;
  private Product product2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Set Product 1
    product1 = new Product();
    product1.setId(1L);
    product1.setName("Product1");
    product1.setPrice(10.0);
    product1.setDescription("Description 1");
    product1.setBrand("Brand 1");
    product1.setDepth(1.0);
    product1.setHeight(2.0);
    product1.setWeight(3.0);
    product1.setWidth(4.0);
    product1.setQuantity(100);

    // Set Product 2
    product2 = new Product();
    product2.setId(1L);
    product2.setName("Product2");
    product2.setPrice(20.0);
    product2.setDescription("Description 2");
    product2.setBrand("Brand 2");
    product2.setDepth(1.0);
    product2.setHeight(2.0);
    product2.setWeight(3.0);
    product2.setWidth(4.0);
    product2.setQuantity(20);
  }

  @Test
  public void getAllProductsTest() {
    List<Product> products = new ArrayList<>();
    products.add(product1);
    products.add(product2);
    when(productRepository.findAll()).thenReturn(products);

    List<Product> result = productService.getAllProducts();

    assertEquals(products.size(), result.size());
    assertEquals(products, result);
    assertEquals(products.get(0).getName(), result.get(0).getName());
    assertEquals(products.get(1).getName(), result.get(1).getName());
  }

  @Test
  public void getProductByIdTest() {
    Long id = 1L;
    when(productRepository.findById(id)).thenReturn(product1);
    Product result = productService.getProductById(id);

    assertEquals(product1.getName(), result.getName());
    assertEquals(product1, result);
  }

  @Test
  public void createProductTest() {
    Product product = new Product();
    product.setId(1L);
    product.setName("Product2");
    product.setPrice(20.0);
    product.setDescription("Description 2");
    product.setBrand("Brand 2");
    product.setDepth(1.0);
    product.setHeight(2.0);
    product.setWeight(3.0);
    product.setWidth(4.0);
    product.setQuantity(20);
    when(productRepository.save(product)).thenReturn(product);

    Product result = productService.createProduct(product);

    assertNotNull(result.getId());
    assertEquals(product.getName(), result.getName());
  }

  @Test
  public void deleteProductTest() {
    Long id = 1L;
    productService.deleteProduct(id);

    Mockito.verify(productRepository, times(1)).delete(id);
  }
}
