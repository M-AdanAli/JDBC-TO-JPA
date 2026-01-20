package com.adanali.java.dao;

import com.adanali.java.domain.Student;
import com.adanali.java.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HibernateStudentDao implements StudentDao{
    @Override
    public void save(Student student) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
    }

    @Override
    public Optional<Student> findById(int id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return Optional.ofNullable(session.find(Student.class,id));
        }
    }

    @Override
    public List<Student> findAll() throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery(
                    "FROM Student",Student.class
            ).getResultList();
        }
    }

    @Override
    public int updateName(int id, String name) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class,id);
            if (student == null) return 0;
            student.setName(name);
            transaction.commit();
            return 1;
        }
    }

    @Override
    public int updateEmail(int id, String email) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class,id);
            if (student == null) return 0;
            student.setEmail(email);
            transaction.commit();
            return 1;
        }
    }

    @Override
    public int updateGender(int id, String gender) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class,id);
            if (student == null) return 0;
            student.setGender(gender);
            transaction.commit();
            return 1;
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class,id);
            if (student == null) return 0;
            session.remove(student);
            transaction.commit();
            return 1;
        }
    }
}
