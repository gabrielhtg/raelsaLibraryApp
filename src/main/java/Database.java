import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    Statement perintah;
    Connection koneksi;   
    
    public Database () {
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://blldgh53ylx5abq8vh6b-mysql.services.clever-cloud.com:3306/blldgh53ylx5abq8vh6b", "ueiftuqyioofa9eu", "SFGGz8kxtoSt40n7WQxs");

            perintah = koneksi.createStatement();
        }

        catch (Exception args0) {
            args0.printStackTrace();
        }

        // try {
        //     koneksi = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3300/raelsa", "raelsa", "1234");

        //     perintah = koneksi.createStatement();
        // }

        // catch (Exception args0) {
        //     // args0.printStackTrace();
        // }
    }
}
