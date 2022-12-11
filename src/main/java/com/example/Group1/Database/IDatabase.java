package com.project.Group1.Dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public interface IDatabase {
    
    public Connection getConnection(String connectionURL, String username, String password);
}
