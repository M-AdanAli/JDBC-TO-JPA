package com.adanali.java.dao;

import com.adanali.java.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void save(Student student);

    Optional<Student> findById(int id);

    List<Student> findAll();

    void update(Student student);

    void deleteById(int id);
}
