package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    // TODO add Logging
    private Logger logger = Logger.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    // Add customer
    public Customer addCustomer(Customer newCustomer) {
        logger.info("\nAdmin added new customer with username: " + newCustomer.getUsername() + "\n");
        return customerRepository.save(newCustomer);
    }

    // READ
    // Get all customers
    public List<Customer> getAllCustomers() {
        logger.info("\nAdmin gets list of all customers \n");
        return customerRepository.findAll();
    }

    // UPDATE
    // Update customer
    public Customer updateCustomer(Customer customerToBeUpdated) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerToBeUpdated.getId());

        StringBuilder updateMessage = new StringBuilder();

        if (existingCustomer.isPresent()) {
            updateMessage.append("\nAdmin updated Customer with id: " + existingCustomer.get().getId() + "\n");
            if (customerToBeUpdated.getUsername() != null && (!customerToBeUpdated.getUsername().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.get().getName()))) {
                existingCustomer.get().setUsername(customerToBeUpdated.getUsername());
                updateMessage.append("Username changed to: " + existingCustomer.get().getUsername() + "\n");
            }
            if (customerToBeUpdated.getName() != null && (!customerToBeUpdated.getName().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.get().getName()))) {
                existingCustomer.get().setName(customerToBeUpdated.getName());
                updateMessage.append("Name changed to: " + existingCustomer.get().getName() + "\n");
            }
            if (customerToBeUpdated.getAddress() != null && (!customerToBeUpdated.getAddress().isEmpty() || !customerToBeUpdated.getAddress().contains(existingCustomer.get().getAddress()))) {
                existingCustomer.get().setAddress(customerToBeUpdated.getAddress());
                updateMessage.append("Address changed to: " + existingCustomer.get().getAddress() + "\n");
            }
            if (customerToBeUpdated.getEmail() != null && (!customerToBeUpdated.getEmail().isEmpty() || !customerToBeUpdated.getEmail().contains(existingCustomer.get().getEmail()))) {
                existingCustomer.get().setEmail(customerToBeUpdated.getEmail());
                updateMessage.append("Email changed to: " + existingCustomer.get().getEmail() + "\n");
            }
            if (customerToBeUpdated.getPhone() != null && (!customerToBeUpdated.getPhone().isEmpty() || !customerToBeUpdated.getPhone().contains(existingCustomer.get().getPhone()))) {
                existingCustomer.get().setPhone(customerToBeUpdated.getPhone());
                updateMessage.append("Phone number changed to: " + existingCustomer.get().getPhone() + "\n");
            }
            logger.info(updateMessage);
        } else {
            logger.info("\nWARN: Admin tried to update Customer but nothing was updated on id: " + customerToBeUpdated.getId() + "\n");
        }

        return existingCustomer.orElseThrow();
    }

    // DELETE
    // Delete customer
    public String deleteCustomer(Customer customerToBeDeleted) {
        Optional<Customer> theCustomer = customerRepository.findById(customerToBeDeleted.getId());

        if (theCustomer.isPresent()) {
            customerRepository.delete(theCustomer.get());
            logger.info("\nAdmin deleted Customer with id: " + customerToBeDeleted.getId() + "\n");
            return "Deleted Customer with id: " + customerToBeDeleted.getId();
        } else {
            logger.warn("\nWARN: Admin tried to delete Customer with ID that does not exist. ID used: " + customerToBeDeleted.getId() + "\n");
            return "Customer could not be found with id: " + customerToBeDeleted.getId() + "\n";
        }
    }

}
