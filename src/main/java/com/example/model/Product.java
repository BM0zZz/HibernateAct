package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   // <-- SIN setter, SIN constructor con id

    private String name;
    private double price;
    private int stock;
    private String category;

    @Column(length = 500)
    private String description;

    // ==== Constructores ====

    // Obligatorio para Hibernate (dejarlo public o protected)
    protected Product() {
    }

    // Alta (desde el main): SIN id y SIN stock
    // stock SIEMPRE empieza en 0
    public Product(String name, double price, String category, String description) {
        setName(name);
        setPrice(price);
        this.stock = 0; // alta => stock inicial 0
        setCategory(category);
        setDescription(description);
    }

    // ==== Getters ====

    public int getId() {  // solo lectura, lo pone Hibernate
        return id;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    // ==== Setters con validación (como en tu modelo original) ====

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name obligatorio");
        }
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price no puede ser negativo");
        }
        this.price = price;
    }

    // OJO: no exponemos un setStock público “tonto” si no quieres
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("stock no puede ser negativo");
        }
        this.stock = stock;
    }

    public void setCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("category obligatoria");
        }
        this.category = category.trim();
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    // ==== Lógica de negocio de stock, como tenías antes ====

    public void increaseStock(int units) {
        if (units <= 0) throw new IllegalArgumentException("unidades > 0");
        this.stock += units;
    }

    public void decreaseStock(int units) {
        if (units <= 0) throw new IllegalArgumentException("unidades > 0");
        if (units > this.stock) throw new IllegalArgumentException("no hay stock suficiente");
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
