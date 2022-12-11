package com.project.Group1.Database;

import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public interface IDatabase {
    
    public Connection getConnection(String connectionURL, String username, String password);
}
