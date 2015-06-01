package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by mhc
 * 连接数据库的类
 */
public class ConnectionFactory {
    private static Connection conn= null;

    public static Connection getConnction(){
        String driver = null;
        String username = null;
        String password = null;
        String url = null;
        FileInputStream fin=null;
        Properties p = new Properties();
        String rootPath = System.getProperty("user.dir");

        File file = new File(rootPath+"/src/main/resources/db.properties");
        try {
            fin = new FileInputStream(file);
            p.load(fin);
        } catch (FileNotFoundException e) {
            System.out.println("数据库配置文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = p.getProperty("username");
        password = p.getProperty("password");
        driver = p.getProperty("driver");
        url = p.getProperty("url");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

//    public static void main(String[] args) {
//        getConnction();
//    }

}
