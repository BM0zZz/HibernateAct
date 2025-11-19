package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private int stock;
    private String category;

    private String description;

    protected Product() {
        // Hibernate lo necesita
    }


    //Constructor de alta

    public Product(String name, double price, String category, String description) {
        setName(name);
        setPrice(price);
        this.stock = 0;
        setCategory(category);
        setDescription(description);
    }


    //GETTERS

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }


    //SETTERS

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Precio no puede ser negativo");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("Stock no puede ser negativo");
        this.stock = stock;
    }

    public void setCategory(String category) {
        if (category == null || category.isBlank())
            throw new IllegalArgumentException("Categoría obligatoria");
        this.category = category.trim();
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    public void increaseStock(int units) {
        if (units <= 0)
            throw new IllegalArgumentException("Las unidades deben ser > 0");
        this.stock += units;
    }

    public void decreaseStock(int units) {
        if (units <= 0)
            throw new IllegalArgumentException("Las unidades deben ser > 0");
        if (units > this.stock)
            throw new IllegalArgumentException("No hay stock suficiente");
        this.stock -= units;
    }

    @Override
    public String toString() {
        return "Product{id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
