package com.ecommerce.api.service;

import com.ecommerce.api.model.Customer;
import com.ecommerce.api.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService {

  @Autowired CustomerRepository customerRepository;

  public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id);
  }

  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Long id, Customer customer) {
    Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(id));
    if (optionalCustomer.isPresent()) {
      Customer updatedCustomer = optionalCustomer.get();
      updatedCustomer.setId(customer.getId());
      updatedCustomer.setName(customer.getName());
      updatedCustomer.setAddress(customer.getAddress());
      updatedCustomer.setNumber(customer.getNumber());
      updatedCustomer.setNeighborhood(customer.getNeighborhood());
      updatedCustomer.setCity(customer.getCity());
      updatedCustomer.setState(customer.getState());
      updatedCustomer.setEmail(customer.getEmail());
      updatedCustomer.setPhone(customer.getPhone());
      return customerRepository.save(updatedCustomer);
    } else {
      return null;
    }
  }

  public void deleteCustomer(Long id) {
    customerRepository.delete(id);
  }
}
