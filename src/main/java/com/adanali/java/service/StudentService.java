package com.adanali.java.service;

import com.adanali.java.dao.StudentDao;
import com.adanali.java.domain.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void createStudent(Student student) {
        try {
            studentDao.save(student);
        } catch (SQLException e) {
            IO.println(e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        try {
            return studentDao.findAll();
        } catch (SQLException e) {
            IO.println(e.getMessage());
        }
        return List.of();
    }

    public Optional<Student> getStudentById(int id) {
        try {
            return studentDao.findById(id);
        }catch (SQLException e){
            IO.println(e.getMessage());
            return Optional.empty();
        }
    }

    public void updateStudentName(int id, String name) {
        try {
            int rowsAffected = studentDao.updateName(id, name);
            System.out.println(rowsAffected+" Record updated..!");
        }catch (SQLException e){
            IO.println(e.getMessage());
        }
    }

    public void updateStudentEmail(int id, String email) {
        try {
            int rowsAffected = studentDao.updateEmail(id, email);
            System.out.println(rowsAffected+" Record updated..!");
        } catch (SQLException e) {
            IO.println(e.getMessage());
        }
    }

    public void updateStudentGender(int id, String gender) {
        try {
            int rowsAffected = studentDao.updateGender(id, gender);
            System.out.println(rowsAffected+" Record updated..!");
        } catch (SQLException e) {
            IO.println(e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            int rowsAffected = studentDao.deleteById(id);
            System.out.println(rowsAffected+" Record Deleted..!");
        }catch (Exception e){
            IO.println(e.getMessage());
        }
    }
}
