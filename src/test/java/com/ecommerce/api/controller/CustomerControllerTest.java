package com.ecommerce.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ecommerce.api.model.Customer;
import com.ecommerce.api.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomerControllerTest {

  @InjectMocks CustomerController customerController;
  @Mock CustomerService customerService;
  private Customer customer;
  private Customer customer1;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    customer =
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

    customer1 =
        new Customer(
            2,
            "Teste Teste",
            "Rua Teste",
            "123",
            "Bairro Teste",
            "São Paulo",
            "SP",
            "1234-5678",
            "email@emailtes.com");
  }

  @Test
  public void getCustomerByIdTest() throws Exception {
    Integer id = 1;
    customer.setId(id);
    when(customerService.getCustomerById(1L)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.getCustomerById(1L);

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), customer);
  }

  @Test
  public void getAllCustomersTest() throws Exception {
    List<Customer> customers = new ArrayList<>();
    customers.add(customer);
    customers.add(customer1);
    when(customerService.getAllCustomer()).thenReturn(customers);

    ResponseEntity<List<Customer>> result = customerController.getAllCustomers();

    assertEquals(result.getStatusCode(), HttpStatus.OK);
    assertEquals(result.getBody(), customers);
  }

  @Test
  public void createCustomerTest() throws Exception {
    when(customerService.createCustomer(customer)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.createCustomer(customer);

    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertEquals(response.getBody(), customer);
  }

  @Test
  public void updateCustomerTest() throws Exception {
    customer.setState("SC");
    customer.setCity("Santa Catarina");

    when(customerService.updateCustomer(1L, customer)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.updateCustomer(1L, customer);

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), customer);
  }
}
