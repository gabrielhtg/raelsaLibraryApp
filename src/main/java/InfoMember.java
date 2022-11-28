import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InfoMember {
    private JFrame frame = new JFrame();
    private String[] kolom = {"NIM", "Nama", "Prodi", "Angkatan", "Status"};
    private DefaultTableModel tabelModel = new DefaultTableModel();
    private JTable tabel = new JTable();
    private JScrollPane scrollPane = new JScrollPane(tabel);

    InfoMember () {
        Font fontRaelsa = new Font("Exo 2", Font.PLAIN, 60);
        tabelModel.setColumnIdentifiers(kolom);

        tabelModel.addColumn(kolom);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1920, 1080);
        frame.setTitle("Raelsa Library Dashboard");
        frame.setLocationRelativeTo(null);

        JPanel panelUtama = new JPanel();
        panelUtama.setPreferredSize(new Dimension(1920, 1030));
        panelUtama.setBackground(new Color(255, 255, 255));
        frame.add(panelUtama);

        JPanel panelAtas = new JPanel(new FlowLayout());
        panelAtas.setPreferredSize(new Dimension(1920 - 420, 100));
        panelAtas.setBackground(new Color(255, 255, 255));
        panelUtama.add(panelAtas);

        JPanel panelBawah = new JPanel(new FlowLayout());
        panelBawah.setPreferredSize(new Dimension(1920 - 420, 900));
        panelBawah.setBackground(new Color(255, 255, 255));
        panelUtama.add(panelBawah);

        ImageIcon iconGaris = new ImageIcon("C:\\LinWin\\projects\\java\\workspace\\TugasPerpustakaan\\garis.jpg");

        JLabel gambarGaris = new JLabel();
        gambarGaris.setIcon(iconGaris);
        gambarGaris.setPreferredSize(new Dimension(1470, 1));
        panelBawah.add(gambarGaris);

        JLabel labelLogoRaelsa = new JLabel();
        labelLogoRaelsa.setText("📕");
        labelLogoRaelsa.setFont(new Font("FiraCode", Font.PLAIN, 70));
        labelLogoRaelsa.setForeground(new Color(50, 94, 190));
        panelAtas.add(labelLogoRaelsa);

        JLabel labelRaelsa = new JLabel("Raelsa Library", JLabel.CENTER);
        labelRaelsa.setFont(fontRaelsa);
        panelAtas.add(labelRaelsa);

        try {
            Database dataRaelsa = new Database();
            ResultSet rs = dataRaelsa.perintah.executeQuery("select * from member");

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String prodi = rs.getString("prodi");
                String angkatan = rs.getString("angkatan");
                String status = rs.getString("status");
                String[] output = {nim, nama, prodi, angkatan, status};

                tabelModel.addRow(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tabel.setDefaultRenderer(String.class, centerRenderer);

        scrollPane.setPreferredSize(new Dimension(900, 800));
        tabel.setRowHeight(30);
        tabel.setLayout(new FlowLayout());;
        tabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panelBawah.add(scrollPane);
        tabel.setModel(tabelModel);
            
        frame.setVisible(true);
    }
}
