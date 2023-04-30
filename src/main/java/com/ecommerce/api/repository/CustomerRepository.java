package com.ecommerce.api.repository;

import com.ecommerce.api.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class CustomerRepository {

  @PersistenceContext private EntityManager entityManager;

  public Customer save(Customer customer) {
    entityManager.persist(customer);
    return customer;
  }

  public Customer findById(Long id) {
    return entityManager.find(Customer.class, id);
  }

  public List<Customer> findAll() {
    return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
  }

  public void update(Customer customer) {
    entityManager.merge(customer);
  }

  public void delete(Long id) {
    entityManager.remove(entityManager.contains(id) ? id : entityManager.merge(id));
  }
}
