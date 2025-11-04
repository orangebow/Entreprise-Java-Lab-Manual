package com.enterprise.utils;

public class DatabaseConfig {
    public static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/enterprise";
    public static final String POSTGRESQL_USERNAME = "anand";
    public static final String POSTGRESQL_PASSWORD = "java";
    
    public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String ORACLE_USERNAME = "hr";
    public static final String ORACLE_PASSWORD = "password";
    
    public static final String DRIVER_POSTGRESQL = "org.postgresql.Driver";
    public static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
}