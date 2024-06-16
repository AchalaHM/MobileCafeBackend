package com.cafe.cafeback.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class MobilesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Lob
    private byte[] image;

    @Transient
    private byte[] decompressedImage;
}