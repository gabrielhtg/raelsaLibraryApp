import java.sql.ResultSet;
import java.sql.SQLException;

public class Killer {
    Killer () {
        String sql = "select ID from information_schema.PROCESSLIST where COMMAND='Sleep'";
        // ArrayList<String> id = new ArrayList<>();
        
        while (true) {
            Database dataRaelsa = new Database();
            try {
                ResultSet rs = dataRaelsa.perintah.executeQuery(sql);
                while (rs.next()) {
                    dataRaelsa.perintah.executeUpdate(String.format("kill %s", rs.getString("ID")));
                    System.out.printf("kill %s", rs.getString("ID"));
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                dataRaelsa.koneksi.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
