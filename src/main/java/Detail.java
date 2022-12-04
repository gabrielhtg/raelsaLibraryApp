import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Detail {
    private JFrame frame = new JFrame();
    private Font fontLabel = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20);
    private JPanel[] panel = new JPanel[4];
    private JLabel labelFoto = new JLabel();
    private JLabel labelNIM = new JLabel();
    private JLabel labelNama = new JLabel();
    private JLabel labelProdi = new JLabel();
    private JLabel labelAngkatan = new JLabel();
    private JLabel labelTglPinjam = new JLabel();
    private JLabel labelTglTerakhir = new JLabel();
    private JLabel[] label = new JLabel[5];
    private String[] temp = new String[7];
    private JPanel panelAtas = new JPanel();
    private String nim;


    ImageIcon changeSize (String lokasiFoto, int ukuranTujuan) {
        ImageIcon test = new ImageIcon(lokasiFoto); 
        double pengurang = (double)ukuranTujuan / (double)test.getIconHeight();
        Image image2 = test.getImage();
        Image gambarbaru = image2.getScaledInstance((int) Math.round(test.getIconWidth() * pengurang), ukuranTujuan, java.awt.Image.SCALE_SMOOTH);
        ImageIcon gambarBuku = new ImageIcon(gambarbaru);
        return gambarBuku;
    }

    void cekMember (String id) { 
        if (id.toLowerCase().charAt(0) == 'b') {
            try {
                Database dataRaelsa = new Database();
                String sql1 = String.format("select * from pinjam where book_id = '%s'", id);
                ResultSet rs = dataRaelsa.perintah.executeQuery(sql1);
                rs.next();
                Date tanggalStart = rs.getDate("waktuPinjam");
                Date tanggalEnd = rs.getDate("waktuKembali");
                String tanggal1[] = tanggalStart.toString().split("-");
                String tanggal2[] = tanggalEnd.toString().split("-");
                temp[4] = String.format("%s-%s-%s", tanggal1[2], tanggal1[1], tanggal1[0]);
                temp[5] = String.format("%s-%s-%s", tanggal2[2], tanggal2[1], tanggal2[0]);
                nim = rs.getString("nim");
                rs.close();
                dataRaelsa.koneksi.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Database dataRaelsa = new Database();
                String sql2 = String.format("select * from member where nim = '%s'", nim);
                ResultSet result = dataRaelsa.perintah.executeQuery(sql2);
                result.next();
                temp[0] = result.getString("nim");
                temp[1] = result.getString("nama");
                temp[2] = result.getString("prodi");
                temp[3] = result.getString("angkatan");
                temp[6] = result.getString("foto");
                result.close();
                dataRaelsa.koneksi.close();
            } catch(SQLException e) {
                e.printStackTrace();

            }
        }

        else {
        }
        
    }
    
    Detail (String id) {
        cekMember(id);
        frame.setTitle("Detail " + id);
        frame.setVisible(true);
        // frame.setLayout(new FlowLayout());
        // frame.setSize(800, 460);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        

        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panel[i].setOpaque(false);
            // panel[i].setBackground(new Color(255, 255, 255));
            panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
        }   

        panelAtas.setPreferredSize(new Dimension(800, 50));
        panelAtas.setLayout(new FlowLayout());
        
        JLabel tulisan = new JLabel("Data Peminjam");
        tulisan.setFont(new Font("FiraCode Nerd Font Mono", Font.BOLD, 20));
        panelAtas.add(tulisan);
        panel[0].add(panelAtas);

        panel[0].setPreferredSize(new Dimension(800, 280));

        panel[1].setPreferredSize(new Dimension(200-30, 200));
        // panel[1].setBackground(Color.BLUE);

        labelNIM.setFont(fontLabel);
        labelNIM.setText        (String.format("NIM         : %s", temp[0]));

        labelNama.setFont(fontLabel);
        labelNama.setText       (String.format("Nama        : %s", temp[1]));

        labelProdi.setFont(fontLabel);
        labelProdi.setText      (String.format("Prodi       : %s", temp[2]));

        labelAngkatan.setFont(fontLabel);
        labelAngkatan.setText   (String.format("Angkatan    : %s          ", temp[3]));

        labelTglPinjam.setFont(fontLabel);
        labelTglPinjam.setPreferredSize(new Dimension(400, 25));
        labelTglPinjam.setText    (String.format("Tgl Start   : %s", temp[4]));

        labelTglTerakhir.setFont(fontLabel);
        labelTglTerakhir.setPreferredSize(new Dimension(400, 30));
        labelTglTerakhir.setText  (String.format("Tgl End     : %s", temp[5]));

        panel[2].add(labelNIM);
        panel[2].add(labelNama);
        panel[2].add(labelProdi);
        panel[2].add(labelAngkatan);
        panel[2].add(labelTglPinjam);
        panel[2].add(labelTglTerakhir);
        panel[2].setBackground(Color.BLUE);
        panel[2].setPreferredSize(new Dimension(485+130, 200));

        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setFont(fontLabel);
        }

        labelFoto.setIcon(changeSize(temp[6], 200));
        panel[1].add(labelFoto);

        panel[0].add(panel[1]);
        panel[0].add(panel[2]);

        frame.add(panel[0]);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
