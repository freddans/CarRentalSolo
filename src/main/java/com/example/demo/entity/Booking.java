package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date date;

  private int carid;
  private int customerid;

  @OneToOne
  @JoinColumn(name = "cars_id")
  private Car car;

  @OneToOne
  @JoinColumn(name = "customers_id")
  private Customer customer;

  public Booking() {
  }

  public Booking(Car car, Customer customer) {
    this.car = car;
    this.customer = customer;
  }

//  public Booking(Car car, Customer customer) {
//    this.date = new Date();
//    this.car = car;
//    this.customer = customer;
//  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getCarid() {
    return carid;
  }

  public void setCarid(int carid) {
    this.carid = carid;
  }

  public int getCustomerid() {
    return customerid;
  }

  public void setCustomerid(int customerid) {
    this.customerid = customerid;
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

}
