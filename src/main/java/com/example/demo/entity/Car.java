package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private double dailyprice;
  private String manifacturer;
  private String model;
  private String regnr;
  private boolean available; // om bokad

  public Car() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getDailyprice() {
    return dailyprice;
  }

  public void setDailyprice(double dailyprice) {
    this.dailyprice = dailyprice;
  }

  public String getManifacturer() {
    return manifacturer;
  }

  public void setManifacturer(String manifacturer) {
    this.manifacturer = manifacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRegnr() {
    return regnr;
  }

  public void setRegnr(String regnr) {
    this.regnr = regnr;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
