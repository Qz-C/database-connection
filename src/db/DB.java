package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    //This object stores the database's connection information
    private static Connection conn = null;

    //This method loads the properties from the file db.properties
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties dbProps = new Properties();
            dbProps.load(fs);
            return(dbProps);
        }catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    //This method connects into in the database
    public static Connection getConnection(){
        if(conn == null)
        {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    //This method closes the connection with database
    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
}
