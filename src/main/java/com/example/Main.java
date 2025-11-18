package com.example;

import com.example.model.Product;

public class Main {
    public static void main(String[] args) {

        System.out.println("===== TEST ERRORES PRODUCT =====");

        // 1) Nombre vacío
        System.out.println("\n[ERROR 1] Nombre vacío");
        try {
            Product p1 = new Product("", 10, "Cat", "desc");
            System.out.println("ERROR: no se lanzó excepción con nombre vacío");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 2) Nombre null
        System.out.println("\n[ERROR 2] Nombre null");
        try {
            Product p2 = new Product(null, 10, "Cat", "desc");
            System.out.println("ERROR: no se lanzó excepción con nombre null");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 3) Precio negativo
        System.out.println("\n[ERROR 3] Precio negativo");
        try {
            Product p3 = new Product("Teclado", -5, "Cat", "desc");
            System.out.println("ERROR: no se lanzó excepción con precio negativo");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 4) Categoría vacía
        System.out.println("\n[ERROR 4] Categoría vacía");
        try {
            Product p4 = new Product("Teclado", 10, "", "desc");
            System.out.println("ERROR: no se lanzó excepción con categoría vacía");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 5) Categoría null
        System.out.println("\n[ERROR 5] Categoría null");
        try {
            Product p5 = new Product("Teclado", 10, null, "desc");
            System.out.println("ERROR: no se lanzó excepción con categoría null");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 6) setStock negativo
        System.out.println("\n[ERROR 6] setStock negativo");
        try {
            Product p6 = new Product("Teclado", 10, "Cat", "desc");
            p6.setStock(-1);
            System.out.println("ERROR: no se lanzó excepción con stock negativo");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 7) increaseStock con unidades <= 0
        System.out.println("\n[ERROR 7] increaseStock con 0");
        try {
            Product p7 = new Product("Teclado", 10, "Cat", "desc");
            p7.increaseStock(0);
            System.out.println("ERROR: no se lanzó excepción con increaseStock(0)");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 8) decreaseStock con unidades <= 0
        System.out.println("\n[ERROR 8] decreaseStock con 0");
        try {
            Product p8 = new Product("Teclado", 10, "Cat", "desc");
            p8.decreaseStock(0);
            System.out.println("ERROR: no se lanzó excepción con decreaseStock(0)");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        // 9) decreaseStock mayor que stock
        System.out.println("\n[ERROR 9] decreaseStock mayor que stock");
        try {
            Product p9 = new Product("Teclado", 10, "Cat", "desc");
            p9.decreaseStock(1);  // stock empieza en 0
            System.out.println("ERROR: no se lanzó excepción con decreaseStock > stock");
        } catch (Exception e) {
            System.out.println("OK -> " + e.getMessage());
        }

        System.out.println("\n===== FIN TEST ERRORES =====");
    }
}
