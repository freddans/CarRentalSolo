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
        Optional<Customer> optionalCustomer = customerRepository.findById(customerToBeUpdated.getId());

        StringBuilder updateMessage = new StringBuilder();

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            updateMessage.append("\nAdmin updated Customer with id: " + existingCustomer.getId() + "\n");
            if (customerToBeUpdated.getUsername() != null && (!customerToBeUpdated.getUsername().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
                existingCustomer.setUsername(customerToBeUpdated.getUsername());
                updateMessage.append("Username changed to: " + existingCustomer.getUsername() + "\n");
            }
            if (customerToBeUpdated.getName() != null && (!customerToBeUpdated.getName().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
                existingCustomer.setName(customerToBeUpdated.getName());
                updateMessage.append("Name changed to: " + existingCustomer.getName() + "\n");
            }
            if (customerToBeUpdated.getAddress() != null && (!customerToBeUpdated.getAddress().isEmpty() || !customerToBeUpdated.getAddress().contains(existingCustomer.getAddress()))) {
                existingCustomer.setAddress(customerToBeUpdated.getAddress());
                updateMessage.append("Address changed to: " + existingCustomer.getAddress() + "\n");
            }
            if (customerToBeUpdated.getEmail() != null && (!customerToBeUpdated.getEmail().isEmpty() || !customerToBeUpdated.getEmail().contains(existingCustomer.getEmail()))) {
                existingCustomer.setEmail(customerToBeUpdated.getEmail());
                updateMessage.append("Email changed to: " + existingCustomer.getEmail() + "\n");
            }
            if (customerToBeUpdated.getPhone() != null && (!customerToBeUpdated.getPhone().isEmpty() || !customerToBeUpdated.getPhone().contains(existingCustomer.getPhone()))) {
                existingCustomer.setPhone(customerToBeUpdated.getPhone());
                updateMessage.append("Phone number changed to: " + existingCustomer.getPhone() + "\n");
            }
            logger.info(updateMessage);
            customerRepository.save(existingCustomer);
        } else {
            logger.info("\nWARN: Admin tried to update Customer but nothing was updated on id: " + customerToBeUpdated.getId() + "\n");
        }

        return optionalCustomer.orElseThrow();
    }

    // DELETE
    // Delete customer
    public String deleteCustomer(Customer customerToBeDeleted) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerToBeDeleted.getId());

        if (optionalCustomer.isPresent()) {
            Customer theCustomer = optionalCustomer.get();
            customerRepository.delete(theCustomer);
            logger.info("\nAdmin deleted Customer with id: " + customerToBeDeleted.getId() + "\n");
            return "Deleted Customer with id: " + customerToBeDeleted.getId();
        } else {
            logger.warn("\nWARN: Admin tried to delete Customer with ID that does not exist. ID used: " + customerToBeDeleted.getId() + "\n");
            return "Customer could not be found with id: " + customerToBeDeleted.getId() + "\n";
        }
    }

}
