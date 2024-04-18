package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hired", nullable = false)
    private Date dateOfHire;

    @Column(name = "duedate", nullable = true)
    private Date dueDate;

    @OneToOne
    @JoinColumn(name = "cars_id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "customers_id")
    private Customer customer;

    public Booking() {
    }

  public Booking(Car car, Customer customer) {
    this.dateOfHire = new Date();
    this.car = car;
    this.customer = customer;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(Date date) {
        this.dateOfHire = date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
