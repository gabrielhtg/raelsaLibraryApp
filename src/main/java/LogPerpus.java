import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LogPerpus {
    private JFrame frame = new JFrame();
    private String[] dataUser = new String[5];
    // private String[] dataTabel = new String[6];
    private Font fontRaelsa = new Font("Exo 2", Font.PLAIN, 60);
    private JPanel panelUtama = new JPanel();
    private JPanel panelAtas = new JPanel(new FlowLayout());
    private JPanel panelBawah = new JPanel(new FlowLayout());
    private ImageIcon iconGaris = new ImageIcon("garis.jpg");
    private JLabel gambarGaris = new JLabel();
    private JLabel labelIdMemberAtas = new JLabel("Masukkan ID Member : ");
    private JTextField fieldIdMember = new JTextField();
    private JPanel panelInputID = new JPanel(null);
    private JPanel panelTengah = new JPanel();
    private JPanel panelBawahInput = new JPanel(new FlowLayout());
    private JLabel labelLogoRaelsa = new JLabel();
    private JLabel labelRaelsa = new JLabel("Raelsa Library", JLabel.CENTER);
    private JScrollPane scrollPane = new JScrollPane();
    private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();

    void masukkanLog (String nim) {
        String sql = String.format("select * from member where nim = '%s'", nim);
        Database dataRaelsa = new Database();

        try {
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql);
            rs.next();
            dataUser[0] = rs.getString("nim");
            dataUser[1] = rs.getString("nama");
            dataUser[2] = rs.getString("prodi");
            dataUser[3] = rs.getString("angkatan");
            dataUser[4] = LocalDateTime.now().toString();
            tableModel.addRow(dataUser);
        } catch (SQLException e) {

        }

        String sql2 = String.format("insert into logperpus (nim, nama, prodi, angkatan, time) value ('%s', '%s', '%s', '%s', '%s');", dataUser[0], dataUser[1], dataUser[2], dataUser[3], dataUser[4]);

        try {
            dataRaelsa.perintah.execute(sql2);
        } catch (SQLException e) {

        }

        
    }

    LogPerpus() {
        Database dataRaelsa = new Database();

        String sql3 = String.format("select * from logperpus");

        try {
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql3);
            
            while (rs.next()) {
                dataUser[0] = rs.getString("nim");
                dataUser[1] = rs.getString("nama");
                dataUser[2] = rs.getString("prodi");
                dataUser[3] = rs.getString("angkatan");
                dataUser[4] = rs.getString("time");
                tableModel.addRow(dataUser);
            }
        } catch (SQLException e) {

        }

        try {
            dataRaelsa.koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1920, 1080);
        frame.setTitle("Raelsa Library Dashboard");
        frame.setLocationRelativeTo(null);

        panelUtama.setPreferredSize(new Dimension(1920, 1030));
        panelUtama.setBackground(new Color(255, 255, 255));
        frame.add(panelUtama);

        panelAtas.setPreferredSize(new Dimension(1920 - 420, 100));
        panelAtas.setBackground(new Color(255, 255, 255));
        panelUtama.add(panelAtas);

        panelBawah.setPreferredSize(new Dimension(1920 - 420, 900));
        panelBawah.setBackground(new Color(255, 255, 255));
        panelUtama.add(panelBawah);

        gambarGaris.setIcon(iconGaris);
        gambarGaris.setPreferredSize(new Dimension(1470, 1));
        panelBawah.add(gambarGaris);

        labelIdMemberAtas.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20));
        labelIdMemberAtas.setBounds(15, 10 + 15, 300, 30);

        fieldIdMember.setBounds(270, 10 + 15, 250, 30);
        fieldIdMember.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 17));

        fieldIdMember.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                masukkanLog(fieldIdMember.getText());
                fieldIdMember.setText("");
            }
            
        });

        panelInputID.setPreferredSize(new Dimension(1920 - 420, 55));
        panelInputID.add(labelIdMemberAtas);
        panelInputID.add(fieldIdMember);
        panelInputID.setOpaque(false);
        panelBawah.add(panelInputID);

        panelTengah.setPreferredSize(new Dimension(1920 - 420, 50));
        panelTengah.setOpaque(false);
        panelBawah.add(panelTengah);

        panelBawahInput.setPreferredSize(new Dimension(1920 - 420, 700 + 60));
        panelBawahInput.setOpaque(false);
        panelBawah.add(panelBawahInput);

        labelLogoRaelsa.setText("📕");
        labelLogoRaelsa.setFont(new Font("FiraCode", Font.PLAIN, 70));
        labelLogoRaelsa.setForeground(new Color(50, 94, 190));
        panelAtas.add(labelLogoRaelsa);

        labelRaelsa.setFont(fontRaelsa);
        panelAtas.add(labelRaelsa);

        scrollPane.setPreferredSize(new Dimension(1920 - 420, 700 + 55));
        panelBawahInput.add(scrollPane);

        scrollPane.setViewportView(table);

        table.setModel(tableModel);
        tableModel.addColumn("NIM");
        tableModel.addColumn("Nama");
        tableModel.addColumn("Prodi");
        tableModel.addColumn("Angkatan");
        tableModel.addColumn("Time");
        table.setRowHeight(10);
        table.setBackground(Color.white);

        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                new HomeDisplay("Gabriel", "ganteng");
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
            
        });
    }
}
