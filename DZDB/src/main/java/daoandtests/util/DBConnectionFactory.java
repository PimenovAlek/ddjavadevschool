package daoandtests.util;

import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.Connection;
import java.sql.SQLException;


public class DBConnectionFactory {


       public static BasicDataSource ds = new BasicDataSource();

       static {
           String url = "jdbc:postgresql://localhost:5432/";
           String dbName = "hr_sys";
           String login = "postgres";
           String password = "root";
           ds.setUrl(url+dbName);
           ds.setUsername(login);
           ds.setPassword(password);
           ds.setMinIdle(5);
           ds.setMaxIdle(10);
           ds.setInitialSize(10);
       }

       public static Connection getDBConnectionFromPool() throws SQLException{
           return ds.getConnection();
       }



}
