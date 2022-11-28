import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogPerpus {
    public JFrame frame = new JFrame();
    private String[] temp = new String[6];

    boolean cekBuku (String id) {
        String sql = String.format("select * from member where nim = '%s'", id);

        try {
            Database dataRaelsa = new Database();
            ResultSet result = dataRaelsa.perintah.executeQuery(sql);
            result.next();
            temp[0] = result.getString("nim");
            temp[1] = result.getString("nama");
            temp[2] = result.getString("prodi");
            temp[3] = result.getString("angkatan");
            temp[4] = String.valueOf(result.getInt("status"));
            temp[5] = result.getString("foto");
            dataRaelsa.koneksi.close();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    void hapusMember (String id) {
        String sql = String.format("delete from member where nim = '%s'", id);

        try {
            Database dataRaelsa = new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();

        } catch (SQLException e) {
            System.out.println("Gagal menghapus member.");
        }
    }

     {
        Font fontRaelsa = new Font("Exo 2", Font.PLAIN, 60);

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

        JLabel labelIdMemberAtas = new JLabel("Masukkan ID Member : ");
        labelIdMemberAtas.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20));
        labelIdMemberAtas.setBounds(15, 10 + 15, 300, 30);

        final JTextField fieldIdMember = new JTextField();
        fieldIdMember.setBounds(270, 10 + 15, 250, 30);
        fieldIdMember.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 17));

        JPanel panelSampingKananAtas = new JPanel(null);
        panelSampingKananAtas.setPreferredSize(new Dimension(1920 - 420, 55));
        panelSampingKananAtas.add(labelIdMemberAtas);
        panelSampingKananAtas.add(fieldIdMember);
        panelSampingKananAtas.setOpaque(false);
        panelSampingKananAtas.setBackground(new Color(255, 255, 255));
        panelBawah.add(panelSampingKananAtas);

        final JLabel logoBuku = new JLabel();
        logoBuku.setIcon(new ImageIcon("C:\\LinWin\\projects\\java\\workspace\\TugasPerpustakaan\\book.png"));
        panelBawah.add(logoBuku);

        JLabel labelLogoRaelsa = new JLabel();
        labelLogoRaelsa.setText("📕");
        labelLogoRaelsa.setFont(new Font("FiraCode", Font.PLAIN, 70));
        labelLogoRaelsa.setForeground(new Color(50, 94, 190));
        panelAtas.add(labelLogoRaelsa);

        JLabel labelRaelsa = new JLabel("Raelsa Library", JLabel.CENTER);
        labelRaelsa.setFont(fontRaelsa);
        panelAtas.add(labelRaelsa);
      
        // Font fontLabelInfo = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 25);

        // final JLabel labelIdMember = new JLabel   ("ID           :");
        // labelIdMember.setFont(fontLabelInfo);
        // labelIdMember.setPreferredSize(new Dimension(220, 80));
        // panel2.add(labelIdMember);

        // final JLabel labelNamaMember = new JLabel  ();
        // labelNamaMember.setText                   ("Nama         :");
        // labelNamaMember.setFont(fontLabelInfo);
        // labelNamaMember.setPreferredSize(new Dimension(220, 80));
        // panel2.add(labelNamaMember);

        // final JLabel labelProdiMember = new JLabel("Prodi        :");
        // labelProdiMember.setFont(fontLabelInfo);
        // labelProdiMember.setPreferredSize(new Dimension(220, 80));
        // panel2.add(labelProdiMember);

        // final JLabel labelAngkatan = new JLabel   ("Angkatan     :");
        // labelAngkatan.setFont(fontLabelInfo);
        // labelAngkatan.setPreferredSize(new Dimension(220, 80));
        // panel2.add(labelAngkatan);

        // final JLabel labelStatusMember = new JLabel ("Status       :");
        // labelStatusMember.setFont(fontLabelInfo);
        // labelStatusMember.setPreferredSize(new Dimension(220, 80));
        // panel2.add(labelStatusMember);

        // JPanel panelSampingBawah = new JPanel(null);
        // panelSampingBawah.setOpaque(false);    
        // panelSampingBawah.setPreferredSize(new Dimension(1300, 50));
        // panelBawah.add(panelSampingBawah);

        // final JButton tombolHapusMember = new JButton();
        // tombolHapusMember.setBackground(new Color(243, 69, 70));
        // tombolHapusMember.setForeground(Color.white);
        // tombolHapusMember.setFocusable(false);
        // tombolHapusMember.setText("Hapus Member");
        // tombolHapusMember.setFont(new Font("Arial", Font.PLAIN, 20));
        // tombolHapusMember.setBounds(1100, 0, 200, 50);
        // panelSampingBawah.add(tombolHapusMember);

        // final JLabel labelHapus = new JLabel("");
        // labelHapus.setBounds(0, 0, 500, 50);
        // labelHapus.setFont(new Font("Arial", Font.BOLD, 40));
        // labelHapus.setForeground(new Color(243, 69, 70));
        // panelBawah.add(labelHapus);

        // final JLabel labelIdMemberOutput = new JLabel ("");
        // labelIdMemberOutput.setFont(fontLabelInfo);
        // labelIdMemberOutput.setPreferredSize(new Dimension(500, 80));
        // panel3.add(labelIdMemberOutput);

        // final JLabel labelNamaMemberOutput = new JLabel ("");
        // labelNamaMemberOutput.setFont(fontLabelInfo);
        // labelNamaMemberOutput.setPreferredSize(new Dimension(500, 80));
        // panel3.add(labelNamaMemberOutput);

        // final JLabel labelProdiMemberOutput = new JLabel ("");
        // labelProdiMemberOutput.setFont(fontLabelInfo);
        // labelProdiMemberOutput.setPreferredSize(new Dimension(500, 80));
        // panel3.add(labelProdiMemberOutput);

        // final JLabel labelAngkatanMemberOutput = new JLabel ("");
        // labelAngkatanMemberOutput.setFont(fontLabelInfo);
        // labelAngkatanMemberOutput.setPreferredSize(new Dimension(500, 80));
        // panel3.add(labelAngkatanMemberOutput);

        // final JLabel labelStatusMemberOutput = new JLabel ("");
        // labelStatusMemberOutput.setFont(fontLabelInfo);
        // labelStatusMemberOutput.setPreferredSize(new Dimension(500, 80));
        // panel3.add(labelStatusMemberOutput);

        // tombolHapusMember.addActionListener(new ActionListener() {

        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if (e.getSource() == tombolHapusMember) {
        //             hapusMember(temp[0]);
        //             labelIdMemberOutput.setText     ("-");
        //             labelNamaMemberOutput.setText   ("-");
        //             labelAngkatanMemberOutput.setText     ("-");
        //             labelProdiMemberOutput.setText  ("-");
        //             labelStatusMemberOutput.setText   ("-");
        //             labelHapus.setText("Member Berhasil Dihapus");
        //         }                
        //     }
            
        // });

        // frame.setVisible(true);

        // fieldIdMember.addKeyListener(new KeyListener() {

        //     @Override
        //     public void keyTyped(KeyEvent e) {
        //     }

        //     @Override
        //     public void keyPressed(KeyEvent e) {
        //         if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        //             if (cekBuku(fieldIdMember.getText())) {
        //                 fieldIdMember.setText("");
        //                 labelIdMemberOutput.setText     (temp[0]);
        //                 labelNamaMemberOutput.setText  (temp[1]);
        //                 labelAngkatanMemberOutput.setText(temp[2]);
        //                 labelProdiMemberOutput.setText(temp[3]);

        //                 if (Integer.valueOf(temp[4]) == 0) {
        //                     labelStatusMemberOutput.setText ("Sedang Tidak Meminjam Buku");
        //                 }

        //                 else {
        //                     labelStatusMemberOutput.setText ("Meminjam Buku");
        //                 }

        //                 ImageIcon test = new ImageIcon(temp[5]); // assign image to a new ImageIcon
        //                 double pengurang = 512.0 / (double)test.getIconHeight();
        //                 Image image2 = test.getImage(); // transform it 
        //                 Image gambarbaru = image2.getScaledInstance((int)Math.round(test.getIconWidth() * pengurang), 512, java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        //                 final ImageIcon gambarBuku = new ImageIcon(gambarbaru);

        //                 logoBuku.setIcon(gambarBuku);
        //                 labelHapus.setText("");
                        
        //             }

        //             else {
        //                 labelHapus.setText("Member Tidak Ditemukan");
        //                 labelIdMemberOutput.setText     ("-");
        //                 labelNamaMemberOutput.setText  ("-");
        //                 labelAngkatanMemberOutput.setText("-");
        //                 labelProdiMemberOutput.setText("-");
        //                 labelStatusMemberOutput.setText ("-");
        //                 fieldIdMember.setText("");
        //             }
                    
        //         }
        //     }

        //     @Override
        //     public void keyReleased(KeyEvent e) {
        //     }
            
        // });

        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                new HomeDisplay("Gabriel", "ganteng");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
}
