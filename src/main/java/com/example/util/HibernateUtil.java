package com.example.util;

import com.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    // CRUD


    // CREATE
    public static Product addProduct(Product product) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
            return product;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al insertar: " + e.getMessage());
            return null;
        }
    }

    // READ BY ID
    public static Product getProductById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar por ID: " + e.getMessage());
            return null;
        }
    }

    // READ ALL
    public static List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> q = session.createQuery("FROM Product", Product.class);
            return q.list();
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return List.of();
        }
    }

    // UPDATE
    public static boolean updateProduct(Product p) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public static boolean deleteProduct(int id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            Product p = session.get(Product.class, id);
            if (p == null) return false;

            tx = session.beginTransaction();
            session.remove(p);
            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al borrar: " + e.getMessage());
            return false;
        }
    }
    //borrartodos
    public static void borrarTodos() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Product").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al borrar todos los productos: " + e.getMessage());
        }
    }


    // Close
    public static void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
