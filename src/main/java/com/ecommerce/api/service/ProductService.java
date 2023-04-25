package com.ecommerce.api.service;

import com.ecommerce.api.Repository.ProductRepository;
import com.ecommerce.api.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public List<Product> findByQuantityAsc() {
    return productRepository.findByQuantityAsc();
  }

  public List<Product> findByQuantityDesc() {
    return productRepository.findByQuantityDesc();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product product) {
    Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findById(id));
    if (optionalProduct.isPresent()) {
      Product updatedProduct = optionalProduct.get();
      updatedProduct.setName(product.getName());
      updatedProduct.setPrice(product.getPrice());
      updatedProduct.setDescription(product.getDescription());
      updatedProduct.setBrand(product.getBrand());
      updatedProduct.setWidth(product.getWidth());
      updatedProduct.setHeight(product.getHeight());
      updatedProduct.setDepth(product.getDepth());
      updatedProduct.setWeight(product.getWeight());
      updatedProduct.setQuantity(product.getQuantity());
      return productRepository.save(updatedProduct);
    } else {
      return null;
    }
  }

  public void deleteProduct(Long id) {
    productRepository.delete(id);
  }
}
