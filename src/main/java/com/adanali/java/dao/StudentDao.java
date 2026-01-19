package com.adanali.java.dao;

import com.adanali.java.domain.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void save(Student student) throws SQLException;

    Optional<Student> findById(int id) throws SQLException;

    List<Student> findAll() throws SQLException;

    int updateName(int id, String name) throws SQLException;

    int updateEmail(int id, String email) throws SQLException;

    int updateGender(int id, String gender) throws SQLException;

    int deleteById(int id) throws SQLException;
}
