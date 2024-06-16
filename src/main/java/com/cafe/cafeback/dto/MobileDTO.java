package com.cafe.cafeback.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MobileDTO {
    private int id;
    private String brandName;
    private String model;
    private int storage;
    private int memory;
    private int quantity;
    private double price;
    private String manufactureYear;
    private LocalDate addedOn;
    private LocalDate updatedOn;
    private String description;
    private byte[] image;
}
