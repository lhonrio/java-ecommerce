package com.ecommerce.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import com.ecommerce.api.model.Customer;
import com.ecommerce.api.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

  @InjectMocks CustomerService customerService;
  @Mock CustomerRepository customerRepository;

  private Customer customer1;
  private Customer customer2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    customer1 =
        new Customer(
            1,
            "Teste Teste",
            "Rua Teste",
            "123",
            "Bairro Teste",
            "São Paulo",
            "SP",
            "1234-5678",
            "email@emailtes.com");

    customer2 =
        new Customer(
            2,
            "Test Test",
            "Rua Test",
            "123",
            "Bairro Test",
            "São Paulo",
            "SP",
            "1234-5678",
            "email@emailtes.com");
  }

  @Test
  public void createCustomerTest() throws Exception {
    Mockito.when(customerRepository.save(customer1)).thenReturn(customer1);

    Customer result = customerService.createCustomer(customer1);
    assertNotNull(result);
    assertEquals(result.getId(), customer1.getId());
    assertEquals(result.getName(), customer1.getName());
    assertEquals(result.getAddress(), customer1.getAddress());
    assertEquals(result.getNumber(), customer1.getNumber());
    assertEquals(result.getNeighborhood(), customer1.getNeighborhood());
    assertEquals(result.getCity(), customer1.getCity());
    assertEquals(result.getState(), customer1.getState());
    assertEquals(result.getPhone(), customer1.getPhone());
    assertEquals(result.getEmail(), customer1.getEmail());
  }

  @Test
  public void getAllCustomerTest() throws Exception {
    List<Customer> customers = new ArrayList<>();
    customers.add(customer1);
    customers.add(customer2);
    Mockito.when(customerRepository.findAll()).thenReturn(customers);

    List<Customer> result = customerService.getAllCustomer();
    assertEquals(result, customers);
    assertEquals(result.size(), 2);
  }

  @Test
  public void getCustomerByIdTest() throws Exception {
    Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(customer1);

    Customer result = customerService.getCustomerById(1L);
    assertEquals(result, customer1);
  }

  @Test
  public void deleteCustomerTest() throws Exception {
    Long id = 1L;
    customerService.deleteCustomer(id);

    Mockito.verify(customerRepository, times(1)).delete(id);
  }
}
