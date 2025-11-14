package com.example.util;

import com.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure() // lee hibernate.cfg.xml
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // ======================
    //   MÉTODOS CRUD
    // INSERTAR
    public static void insertarProducto(Product p) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        }
    }

    // LISTAR TODOS
    public static List<Product> obtenerProductos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> q = session.createQuery("FROM Product", Product.class);
            return q.list();
        }
    }

    // BUSCAR POR ID
    public static Product buscarPorId(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    // ACTUALIZAR
    public static void actualizarProducto(Product p) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
        }
    }

    // BORRAR POR ID
    public static void borrarProducto(int id) {
        try (Session session = sessionFactory.openSession()) {
            Product p = session.get(Product.class, id);
            if (p == null) return;

            Transaction tx = session.beginTransaction();
            session.remove(p);
            tx.commit();
        }
    }

    // BORRAR TODOS
    public static void borrarTodos() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Product").executeUpdate();
            tx.commit();
        }
    }
}
