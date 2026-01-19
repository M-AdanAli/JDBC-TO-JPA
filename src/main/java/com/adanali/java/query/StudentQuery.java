package com.adanali.java.query;

public enum StudentQuery {
    INSERT("INSERT INTO students (name,email,gender) VALUES (?,?,?)"),
    FIND_BY_ID("SELECT * FROM students WHERE id = ?"),
    FIND_ALL("SELECT * FROM students"),
    UPDATE_NAME("UPDATE students SET name = ? WHERE id = ?"),
    UPDATE_EMAIL("UPDATE students SET email = ? WHERE id = ?"),
    UPDATE_GENDER("UPDATE students SET gender = ? WHERE id = ?"),
    DELETE("DELETE FROM students WHERE id=?");

    private final String sql;

    StudentQuery(String sql) {
        this.sql = sql;
    }

    public String sql() {
        return sql;
    }
}
