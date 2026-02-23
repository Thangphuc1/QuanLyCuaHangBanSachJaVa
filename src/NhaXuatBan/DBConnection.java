package NhaXuatBan;

/**
 *
 * @author Quyen
 */
import java.sql.*;

public class DBConnection {
    private final static String url = "jdbc:mysql://localhost:3306/qlbansach";
    private final static String user = "root";
    private final static String password = "quyenthanh1";
    
    public static Connection getDBConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException ex){
            throw new SQLException("Khong tim thay MySQL Driver");
        }
    }
}

