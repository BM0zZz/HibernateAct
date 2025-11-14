package com.example;

import com.example.model.Product;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {

        // Crear el producto (sin id y stock = 0)
        Product nuevo = new Product(
                "Ratón Pro",
                29.99,
                "Periférico",
                "Ratón gaming RGB"
        );

        // Abrir sesión de Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Iniciar transacción
            Transaction tx = session.beginTransaction();

            // Guardar el producto (igual que Student en el ejemplo)
            session.persist(nuevo);

            // Confirmar transacción
            tx.commit();

            System.out.println("Producto añadido con id: " + nuevo.getId());
        }

        // Comprobar que realmente se insertó
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("\nProductos en la base de datos:");
            session.createQuery("FROM Product", Product.class)
                    .list()
                    .forEach(System.out::println);
        }
    }
}
