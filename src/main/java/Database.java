import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Statement perintah;
    Connection koneksi;   
    
    public Database () {
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/raelsa", "raelsa", "1234");

            perintah = koneksi.createStatement();
        }

        catch (Exception args0) {
            // args0.printStackTrace();
        }

        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/raelsa", "raelsa", "1234");

            perintah = koneksi.createStatement();
        }

        catch (Exception args0) {
            // args0.printStackTrace();
        }
    }
}
