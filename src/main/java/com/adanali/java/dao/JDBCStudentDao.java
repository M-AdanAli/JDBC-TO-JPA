package com.adanali.java.dao;

import com.adanali.java.db.DBConnection;
import com.adanali.java.domain.Student;
import com.adanali.java.query.StudentQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCStudentDao implements StudentDao{

    private final DBConnection dbConnection = new DBConnection();

    @Override
    public void save(Student student) throws SQLException{
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(StudentQuery.INSERT.sql())
        ){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setString(3,student.getGender());
            preparedStatement.executeUpdate();
            IO.println("Student inserted into the database...");
        }
    }

    @Override
    public Optional<Student> findById(int id) throws SQLException{
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement(StudentQuery.FIND_BY_ID.sql())
        ) {
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return Optional.of(new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("gender")
                    ));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Student> findAll() throws SQLException{
        List<Student> students = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(StudentQuery.FIND_ALL.sql());
        ) {
            while (resultSet.next()){
                students.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender")));
            }
        }
        return List.copyOf(students);
    }

    @Override
    public int updateName(int id, String name) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(StudentQuery.UPDATE_NAME.sql())
        ){
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int updateEmail(int id, String email) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(StudentQuery.UPDATE_EMAIL.sql())
        ){
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int updateGender(int id, String gender) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(StudentQuery.UPDATE_GENDER.sql())
        ){
            preparedStatement.setString(1,gender);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) throws SQLException{
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(StudentQuery.DELETE.sql())
        ){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }
    }
}
