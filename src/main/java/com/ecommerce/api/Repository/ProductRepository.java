package com.ecommerce.api.Repository;

import com.ecommerce.api.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductRepository {

  @PersistenceContext private EntityManager entityManager;

  public Product save(Product product) {
    entityManager.persist(product);
    return product;
  }

  public Product findById(Long id) {
    return entityManager.find(Product.class, id);
  }

  public List<Product> findAll() {
    return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
  }

  public void update(Product product) {
    entityManager.merge(product);
  }

  public void delete(Long id) {
    entityManager.remove(entityManager.contains(id) ? id : entityManager.merge(id));
  }
}
