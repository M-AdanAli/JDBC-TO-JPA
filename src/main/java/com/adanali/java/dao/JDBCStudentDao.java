package com.adanali.java.dao;

import com.adanali.java.domain.Student;

import java.util.List;
import java.util.Optional;

public class JDBCStudentDao implements StudentDao{

    @Override
    public void save(Student student) {

    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void deleteById(int id) {

    }
}
