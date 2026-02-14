package org.example.service;

import org.example.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer createCustomer(String fullName) {
        Customer customer = new Customer(fullName);
        customers.add(customer);
        return customer;
    }

    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}