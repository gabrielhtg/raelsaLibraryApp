import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class HomeDisplay {
    public JFrame frame = new JFrame();
    private String[] temp = new String[6];
    int tampilkan = 1;
    Font fontRaelsa = new Font("Exo 2", Font.PLAIN, 60); // ini adalah font untuk logo raelsa yang paling atas
    JPanel panelUtama = new JPanel(); // ini merupakan panel pada layer paling bawah
    JPanel panelToolBar = new JPanel(new FlowLayout());
    JPanel panelSamping = new JPanel(new FlowLayout());
    JPanel panelSampingAtas = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panelSampingKanan = new JPanel(new FlowLayout());
    ImageIcon iconGaris = new ImageIcon("garis.jpg");
    JLabel gambarGaris = new JLabel();
    JLabel labelInputID = new JLabel("Masukkan id buku/member : "); // ini adalah label untuk input id member ataupun buku
    JTextField fieldInputID = new JTextField(); // field untuk input id
    JPanel panelSampingKananAtas = new JPanel(null);
    JLabel logoBuku = new JLabel();
    Font fontLabelInfo = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 25);
    JPanel panelSampingFotoBuku = new JPanel(new FlowLayout());
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panel3 = new JPanel();
    JLabel labelLogoRaelsa = new JLabel();
    JLabel labelRaelsa = new JLabel("Raelsa Library", JLabel.CENTER);
    JLabel bgToolbar = new JLabel();
    JPanel panelAtasToolBar = new JPanel();
    JLabel fotoProfil = new JLabel();
    JPanel panelBawahToolbar = new JPanel();
    JLabel namaUser = new JLabel();
    JLabel labelTambahan = new JLabel();
    JPanel panelPemisah = new JPanel();
    JButton tambahBuku = new JButton();
    JButton tambahMember = new JButton();
    JButton logOut = new JButton();
    JPanel panelGapAtas = new JPanel();
    JLabel labelIdBuku = new JLabel     ("ID Buku      :");
    JLabel labelJudulBuku = new JLabel  ();
    JLabel labelPenulisBuku = new JLabel("Penulis      :");
    JLabel labelTahunTerbit = new JLabel("Tahun Terbit :");
    JLabel labelStatusBuku = new JLabel ("Status Buku  :");
    JLabel labelIdOutput = new JLabel ("");
    JLabel labelJudulOutput = new JLabel ("");
    JLabel labelPenulisOutput = new JLabel ("");
    JLabel labelTahunOutput = new JLabel ("");
    JLabel labelStatusOutput = new JLabel ("");
    JButton tombolInfoPeminjam = new JButton();
    JTextField fieldId = new JTextField();
    JTextField fieldJudul = new JTextField();
    JTextField fieldPenulis = new JTextField();
    JTextField fieldTahun = new JTextField();
    JTextField fieldFoto = new JTextField();
    JPanel panelBawah = new JPanel(null);
    JButton tombolOke = new JButton();
    JButton tombolBatal = new JButton();
    JButton tombolEditFoto = new JButton();
    JFileChooser pilihFoto = new JFileChooser();
    JButton tombolEdit = new JButton();
    Color merah = new Color(243, 69, 70);
    JLabel labelHapus = new JLabel("");
    JButton tombolPinjam = new JButton();
    JButton tombolHapusBuku = new JButton();
    InputMap inputMap = fieldInputID.getInputMap();
    ActionMap actionMap = fieldInputID.getActionMap();
    KeyStroke delKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
    JLabel labelPinjam1 = new JLabel();
    JLabel labelPinjam2 = new JLabel();
    JPanel panelPemisahbaru = new JPanel();
    JPanel panelSampingKananOverlap = new JPanel(new FlowLayout());
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
    DefaultTableModel tableModel = new DefaultTableModel();
    String[] dataUser = new String[2];

    String getJudul (String nim) {
        String sql = String.format("select * from pinjam where nim = '%s'", nim);

        Database dataRaelsa = new Database();
        try {
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql);
            rs.next();
            String book_id = rs.getString("book_id");
            // Date tglPeminjaman = rs.getDate("waktuPinjam");

            sql = String.format("select judul from book where book_id = '%s'", book_id);
            ResultSet rs2 = dataRaelsa.perintah.executeQuery(sql);
            rs2.next();
            String judul = rs2.getString("judul");
            
            return String.format("%s (%s)", judul, book_id.toUpperCase());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    String getPeminjam (String book_id) {
        String sql = String.format("select * from pinjam where book_id = '%s'", book_id);

        Database dataRaelsa = new Database();
        try {
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql);
            rs.next();
            String nim = rs.getString("nim");
            Date tglPeminjaman = rs.getDate("waktuPinjam");

            sql = String.format("select nama from member where nim = '%s'", nim);
            ResultSet rs2 = dataRaelsa.perintah.executeQuery(sql);
            rs2.next();
            String nama = rs2.getString("nama");

            return String.format("%s(%s) D: %s", nama, book_id.toUpperCase(), tglPeminjaman.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ImageIcon changeSize (String lokasiFoto, int ukuranTujuan) {
        ImageIcon test = new ImageIcon(lokasiFoto); 
        double pengurang = (double)ukuranTujuan / (double)test.getIconHeight();
        Image image2 = test.getImage();
        Image gambarbaru = image2.getScaledInstance((int) Math.round(test.getIconWidth() * pengurang), ukuranTujuan, java.awt.Image.SCALE_SMOOTH);
        ImageIcon gambarBuku = new ImageIcon(gambarbaru);
        return gambarBuku;
    }

    void changeState () {
        tampilkan = 0;
    }

    boolean pinjamkanBuku (String book_id, String nim) {
        String query = String.format("insert into pinjam (book_id, nim, waktuKembali) value ('%s', '%s', '%s')", book_id, nim, LocalDate.now().plusDays(3));
        String sql1 = String.format("select count(%s) from pinjam;", nim);
        int count = 0;
        
        try {
            Database dataRaelsa = new Database();
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql1);
            rs.next();
            count = rs.getInt("count(nim)");
            dataRaelsa.koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count == 0) {
            String sql2 = String.format("update member set status = 1 where nim = '%s'", nim);
            String sql3 = String.format("update book set status = 1 where book_id = '%s'", book_id);

            try {
                Database dataRaelsa = new Database();
                dataRaelsa.perintah.executeUpdate(query);
                dataRaelsa.perintah.executeUpdate(sql2);
                dataRaelsa.perintah.executeUpdate(sql3);
                dataRaelsa.koneksi.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        else {
            return false;
        }
        
    }

    boolean hapusPinjamkanBuku (String book_id, String nim) {
        String sql1 = String.format("select book_id from pinjam where nim = '%s'", nim);

        try {
            Database dataRaelsa = new Database();
            ResultSet rs = dataRaelsa.perintah.executeQuery(sql1);
            rs.next();
            String temp = rs.getString("book_id");

            if (temp.toLowerCase().equals(book_id.toLowerCase())) {
                String query = String.format("delete from pinjam where book_id = '%s' and nim ='%s'", temp, nim);
                String sql2 = String.format("update member set status = 0 where nim = '%s'", nim);
                String sql3 = String.format("update book set status = 0 where book_id = '%s'", temp);
                dataRaelsa.perintah.executeUpdate(query);
                dataRaelsa.perintah.executeUpdate(sql2);
                dataRaelsa.perintah.executeUpdate(sql3);
                dataRaelsa.koneksi.close();
                return true;
            }
            
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean cekMember (String id) {
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
            e.getSQLState();
        }
    }

    void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    boolean editBuku (String idBaru, String judul, String penulis, String tahun, String foto, String idLama) {
        String sql = String.format("update book set book_id = '%s', judul = '%s', penulis = '%s', tahun = '%s', foto = '%s' where book_id = '%s'", idBaru, judul, penulis, tahun, foto, idLama);

        try {
            Database dataRaelsa = new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    boolean editMember (String idBaru, String nama, String prodi, String angkatan, String foto, String idLama) {
        String sql = String.format("update member set nim = '%s', nama = '%s', prodi = '%s', angkatan = '%s', foto = '%s' where nim = '%s'", idBaru, nama, prodi, angkatan, foto, idLama);

        try {
            Database dataRaelsa = new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal mengedit data member.");
            return false;
        }
    }

    boolean cekBuku (String kodeBuku) {
        String sql = String.format("select * from book where book_id = '%s'", kodeBuku);

        try {
            Database dataRaelsa = new Database();
            ResultSet result = dataRaelsa.perintah.executeQuery(sql);
            result.next();
            temp[0] = result.getString("book_id");
            temp[1] = result.getString("judul");
            temp[2] = result.getString("penulis");
            temp[3] = result.getString("tahun");
            temp[4] = String.valueOf(result.getInt("status"));
            temp[5] = result.getString("foto");
            dataRaelsa.koneksi.close();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    void hapusBuku (String id) {
        String sql = String.format("delete from book where book_id = '%s'", id);

        try {
            Database dataRaelsa = new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();

        } catch (SQLException e) {
        }
    }

    void tampilkanInfoMember(String id) {
        panelSampingKananOverlap.remove(labelInputID);
        panelSampingKananOverlap.remove(fieldInputID);
        panelSampingKananAtas.add(labelInputID);
        fieldInputID.setBounds(325, 10 + 15, 250, 30);
        labelInputID.setBounds(15, 10 + 15, 320, 30);
        panelSampingKananAtas.add(fieldInputID);
        panelSampingKananOverlap.setVisible(false);
        if (id.toLowerCase().charAt(0) == 'h') {
            if (cekBuku(id.substring(7))) {
                tombolEdit.setText("Edit Buku");
                tombolEdit.setVisible(true);
                tombolHapusBuku.setText("Hapus Buku");
                tombolHapusBuku.setVisible(true);
                tombolPinjam.setVisible(true);
                tombolPinjam.setText("Pinjamkan Buku");
                fieldInputID.setText("");
                labelIdBuku.setText("ID Buku      :");
                labelJudulBuku.setText("Judul Buku   :");
                labelTahunTerbit.setText("Tahun Terbit :");
                labelPenulisBuku.setText("Penulis      :");
                labelStatusBuku.setText("Status Buku  :");
                labelIdBuku.setVisible(true);
                labelJudulBuku.setVisible(true);
                labelTahunTerbit.setVisible(true);
                labelPenulisBuku.setVisible(true);
                labelStatusBuku.setVisible(true);
                labelIdOutput.setText     (temp[0]);
                labelJudulBuku.setVisible(true);
                labelJudulOutput.setText  (temp[1]);
                labelTahunTerbit.setVisible(true);
                labelTahunOutput.setText(temp[3]);
                labelPenulisBuku.setVisible(true);
                labelPenulisOutput.setText(temp[2]);
                labelStatusBuku.setVisible(true);
        
                if (Integer.valueOf(temp[4]) == 0) {
                    labelStatusOutput.setText ("Tersedia");
                    panel1.setPreferredSize(new Dimension(800, 120));
                    panel2.setPreferredSize(new Dimension(210, 450));
                    panel3.setPreferredSize(new Dimension(650, 450));
                }
        
                else {
                    labelStatusOutput.setText ("Buku Sedang Dipinjam");
                    panel3.setPreferredSize(new Dimension(650, 500));
                    panel2.setPreferredSize(new Dimension(panel2.getWidth(), 500));
                    panel1.setPreferredSize(new Dimension(panel1.getWidth(), 80));
                    tombolInfoPeminjam.setVisible(true);
                    tombolPinjam.setVisible(false);
                }
        
                logoBuku.setIcon(changeSize(temp[5], 512));
                labelHapus.setVisible(false);
                labelHapus.setText("");
            }
        
            else {
                tombolEdit.setVisible(false);
                tombolHapusBuku.setVisible(false);
                fieldInputID.setText("");
                labelHapus.setVisible(true);
                labelIdBuku.setText("ID Buku      :");
                labelIdBuku.setVisible(true);
                labelJudulBuku.setVisible(true);
                labelTahunTerbit.setVisible(true);
                labelPenulisBuku.setVisible(true);
                labelStatusBuku.setVisible(true);
                labelJudulBuku.setText("Judul Buku   :");
                labelTahunTerbit.setText("Tahun Terbit :");
                labelPenulisBuku.setText("Penulis      :");
                labelStatusBuku.setText("Status Buku  :");
                labelIdOutput.setText     ("-");
                labelJudulOutput.setText  ("-");
                labelTahunOutput.setText("-");
                labelPenulisOutput.setText("-");
                labelStatusOutput.setText ("-");
                logoBuku.setIcon(changeSize("source\\fotoKosong.jpg", 512));
                labelHapus.setBounds(60, 0, 600, 50);
                labelHapus.setVisible(true);
                labelHapus.setForeground(merah);
                labelHapus.setText("ID Tidak Ditemukan");
                panel1.setPreferredSize(new Dimension(800, 120));
                panel2.setPreferredSize(new Dimension(210, 450));
                panel3.setPreferredSize(new Dimension(650, 450));
                tombolInfoPeminjam.setVisible(false);
                tombolPinjam.setVisible(false);
            }
        }
        
        else if (id.toLowerCase().charAt(0) == 'b') {
            if (cekBuku(id)) {
                tombolHapusBuku.setVisible(true);
                tombolHapusBuku.setText("Hapus Buku");
                tombolEdit.setText("Edit Buku");
                tombolEdit.setVisible(true);
                fieldInputID.setText("");
                tombolPinjam.setVisible(true);
                tombolPinjam.setText("Pinjamkan Buku");
                labelIdBuku.setVisible(true);
                labelIdBuku.setText("ID Buku      :");
                labelIdOutput.setText     (temp[0]);
                labelJudulBuku.setVisible(true);
                labelJudulBuku.setText("Judul Buku   :");
                labelJudulOutput.setText  (temp[1]);
                labelTahunTerbit.setVisible(true);
                labelTahunTerbit.setText("Tahun Terbit :");
                labelTahunOutput.setText(temp[3]);
                labelPenulisBuku.setVisible(true);
                labelPenulisOutput.setText(temp[2]);
                labelPenulisBuku.setText("Penulis      :");
                labelStatusBuku.setVisible(true);
                labelStatusBuku.setText("Status Buku  :");
        
                if (Integer.valueOf(temp[4]) == 0) {
                    labelStatusOutput.setText ("Tersedia");
                    panel1.setPreferredSize(new Dimension(800, 120));
                    panel2.setPreferredSize(new Dimension(210, 450));
                    panel3.setPreferredSize(new Dimension(650, 450));
                    tombolInfoPeminjam.setVisible(false);
        
                }
        
                else {
                    
                    labelStatusOutput.setText ("Buku Sedang Dipinjam");
                    panel3.setPreferredSize(new Dimension(650, 500));
                    panel2.setPreferredSize(new Dimension(panel2.getWidth(), 500));
                    panel1.setPreferredSize(new Dimension(panel1.getWidth(), 80));
                    tombolInfoPeminjam.setVisible(true);
                    tombolPinjam.setVisible(false);
                }
        
                logoBuku.setIcon(changeSize(temp[5], 512));
                labelHapus.setVisible(false);
                labelHapus.setText("");
            }
        
            else {
                tombolEdit.setVisible(false);
                tombolHapusBuku.setVisible(false);
                fieldInputID.setText("");
                labelHapus.setBounds(60, 0, 600, 50);
                labelHapus.setVisible(true);
                labelIdBuku.setVisible(true);
                labelJudulBuku.setVisible(true);
                labelTahunTerbit.setVisible(true);
                labelPenulisBuku.setVisible(true);
                labelStatusBuku.setVisible(true);
                labelHapus.setForeground(merah);
                labelHapus.setText("ID Tidak Ditemukan");
                tombolInfoPeminjam.setVisible(false);
                panel1.setPreferredSize(new Dimension(800, 120));
                panel2.setPreferredSize(new Dimension(210, 450));
                panel3.setPreferredSize(new Dimension(650, 450));
                tombolPinjam.setVisible(false);
                labelIdBuku.setText("ID Buku      :");
                labelJudulBuku.setText("Judul Buku   :");
                labelTahunTerbit.setText("Tahun Terbit :");
                labelPenulisBuku.setText("Penulis      :");
                labelStatusBuku.setText("Status Buku  :");
                labelIdOutput.setText     ("-");
                labelJudulOutput.setText  ("-");
                labelTahunOutput.setText("-");
                labelPenulisOutput.setText("-");
                labelStatusOutput.setText ("-");
                logoBuku.setIcon(changeSize("source\\fotoKosong.jpg", 512));
                labelHapus.setVisible(true);
            }
        }
        
        else {
            if (cekMember(id)) {
                tombolHapusBuku.setText("Hapus Member");
                tombolEdit.setText("Edit Member");
                tombolHapusBuku.setVisible(true);
                tombolPinjam.setVisible(true);
                tombolPinjam.setText("Pinjamkan Buku");
                tombolEdit.setVisible(true);
                fieldInputID.setText("");
                labelIdBuku.setText("ID           :");
                labelJudulBuku.setText("Nama         :");
                labelTahunTerbit.setText("Angkatan     :");
                labelPenulisBuku.setText("Prodi        :");
                panel1.setPreferredSize(new Dimension(800, 120));
                panel2.setPreferredSize(new Dimension(210, 450));
                panel3.setPreferredSize(new Dimension(650, 450));
                tombolInfoPeminjam.setVisible(false);
                labelStatusBuku.setText("Meminjam     :");
                labelIdBuku.setVisible(true);
                labelJudulBuku.setVisible(true);
                labelTahunTerbit.setVisible(true);
                labelPenulisBuku.setVisible(true);
                labelStatusBuku.setVisible(true);
                labelIdOutput.setText     (temp[0]);
                labelJudulOutput.setText  (temp[1]);
                labelPenulisOutput.setText(temp[2]);
                labelTahunOutput.setText(temp[3]);
        
                if (Integer.valueOf(temp[4]) == 0) {
                    panel1.setPreferredSize(new Dimension(800, 120));
                    labelStatusOutput.setVisible(true);
                    labelStatusOutput.setText ("Sedang Tidak Meminjam Buku");
                    labelPinjam1.setVisible(false);
                    labelPinjam2.setVisible(false);
                    tombolInfoPeminjam.setVisible(false);
                }
        
                else {
                    labelStatusOutput.setText(getJudul(temp[0]));
                    tombolPinjam.setText("Pengembalian");
                }
        
                logoBuku.setIcon(changeSize(temp[5], 512));
                labelHapus.setText("");
            }
        
            else {
                tombolEdit.setVisible(false);
                tombolHapusBuku.setVisible(false);
                labelHapus.setBounds(60, 0, 600, 50);
                labelHapus.setVisible(true);
                labelHapus.setForeground(merah);
                labelHapus.setText("ID Tidak Ditemukan");
                tombolInfoPeminjam.setVisible(false);
                panel1.setPreferredSize(new Dimension(800, 120));
                panel2.setPreferredSize(new Dimension(210, 450));
                panel3.setPreferredSize(new Dimension(650, 450));
                tombolPinjam.setVisible(false);
                labelIdBuku.setText("ID           :");
                labelJudulBuku.setText("Nama         :");
                labelTahunTerbit.setText("Angkatan     :");
                labelPenulisBuku.setText("Prodi        :");
                labelStatusBuku.setText("Status Buku  :");
                labelIdBuku.setVisible(true);
                labelJudulBuku.setVisible(true);
                labelTahunTerbit.setVisible(true);
                labelPenulisBuku.setVisible(true);
                labelStatusBuku.setVisible(true);
                labelIdOutput.setText     ("-");
                labelJudulOutput.setText  ("-");
                labelTahunOutput.setText("-");
                labelPenulisOutput.setText("-");
                labelStatusOutput.setText ("-");
                fieldInputID.setText("");
                logoBuku.setIcon(changeSize("source\\fotoKosong.jpg", 512));
            }
        }
    }

    HomeDisplay (String namaLibrarian, final String fotoLibrarian) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1920, 1080);
        frame.setTitle("Raelsa Library Dashboard");
        frame.setLocationRelativeTo(null);

        panelUtama.setPreferredSize(new Dimension(1920, 1030));
        panelUtama.setBackground(new Color(255, 255, 255));
        frame.add(panelUtama);

        panelToolBar.setBackground(new Color(255, 255, 255));
        panelToolBar.setPreferredSize(new Dimension(400, 1005));
        panelUtama.add(panelToolBar);

        panelSamping.setBackground(new Color(255, 255, 255));
        panelSamping.setPreferredSize(new Dimension(1920 - 420, 1005));
        panelUtama.add(panelSamping);

        panelSampingAtas.setPreferredSize(new Dimension(1920 - 420, 100));
        panelSampingAtas.setBackground(new Color(255, 255, 255));
        panelSamping.add(panelSampingAtas);

        panelSampingKanan.setPreferredSize(new Dimension(1920 - 420, 900));
        panelSampingKanan.setOpaque(false);
        panelSamping.add(panelSampingKanan);

        panelPemisahbaru.setPreferredSize(new Dimension(1920-420, 400));
        panelPemisahbaru.setOpaque(false);

        panelSampingKananOverlap.setPreferredSize(new Dimension(1920 - 420, 900));
        panelSampingKanan.add(panelSampingKananOverlap);
        panelSampingKananOverlap.setOpaque(false);
        panelSampingKananOverlap.add(panelPemisahbaru);

        gambarGaris.setIcon(iconGaris);
        gambarGaris.setPreferredSize(new Dimension(1470, 1));
        panelSampingKanan.add(gambarGaris);

        labelPinjam1.setFont(fontLabelInfo);
        labelPinjam2.setFont(fontLabelInfo);

        labelInputID.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20));
        labelInputID.setBounds(15, 10 + 15, 320, 30);

        fieldInputID.setBounds(325, 10 + 15, 250, 30);
        fieldInputID.setFont(new Font("FiraCode Nerd Font Mono", Font.PLAIN, 17));

        panelSampingKananAtas.setPreferredSize(new Dimension(1920 - 420, 55));
        
        panelSampingKananAtas.setBackground(new Color(255, 255, 255));
        panelSampingKanan.add(panelSampingKananAtas);

        logoBuku.setIcon(changeSize("source\\fotoKosong.jpg", 512));
        panelSampingKanan.add(logoBuku);

        panelSampingFotoBuku.setPreferredSize(new Dimension(900, 700));
        panelSampingFotoBuku.setOpaque(false);
        panelSampingKanan.add(panelSampingFotoBuku);

        panel1.setPreferredSize(new Dimension(800, 120));
        panel1.setOpaque(false);
        panelSampingFotoBuku.add(panel1);

        panel2.setPreferredSize(new Dimension(210, 450));
        panel2.setOpaque(false);
        panelSampingFotoBuku.add(panel2);

        panel3.setPreferredSize(new Dimension(650, 450));
        panel3.setOpaque(false);
        panelSampingFotoBuku.add(panel3);

        labelLogoRaelsa.setText("📕");
        labelLogoRaelsa.setFont(new Font("FiraCode", Font.PLAIN, 70));
        labelLogoRaelsa.setForeground(new Color(50, 94, 190));
        panelSampingAtas.add(labelLogoRaelsa);

        labelRaelsa.setFont(fontRaelsa);
        panelSampingAtas.add(labelRaelsa);

        bgToolbar.setPreferredSize(new Dimension(400, 1005));
        bgToolbar.setLayout(new FlowLayout());
        bgToolbar.setIcon(new ImageIcon("bgToolbar.jpg"));
        panelToolBar.add(bgToolbar);

        panelAtasToolBar.setPreferredSize(new Dimension(500, 60));
        panelAtasToolBar.setOpaque(false);
        bgToolbar.add(panelAtasToolBar);

        fotoProfil.setIcon(changeSize(fotoLibrarian, 200));
        bgToolbar.add(fotoProfil);
        
        panelBawahToolbar.setPreferredSize(new Dimension(500, 15));
        panelBawahToolbar.setOpaque(false);
        bgToolbar.add(panelBawahToolbar);
        
        namaUser.setText(namaLibrarian);
        namaUser.setFont(new Font("Maiandra GD", Font.BOLD, 30));
        bgToolbar.add(namaUser);

        JPanel panelPemisah1 = new JPanel();
        panelPemisah1.setPreferredSize(new Dimension(370, 30));
        panelPemisah1.setOpaque(false);
        bgToolbar.add(panelPemisah1);

        scrollPane.setPreferredSize(new Dimension(370, 450 - 45));
        bgToolbar.add(scrollPane);
        scrollPane.setViewportView(table);

        JPanel panelPemisah2 = new JPanel();
        panelPemisah2.setPreferredSize(new Dimension(370, 30));
        panelPemisah2.setOpaque(false);
        bgToolbar.add(panelPemisah2);

        //! tambahkan tabel disini

        Thread alwaysCek = new Thread(new Runnable(){
            @Override
            public void run() {
                while (true) {
                    tableModel = new DefaultTableModel();

                    table.setModel(tableModel);
                    tableModel.addColumn("NIM");
                    tableModel.addColumn("Time");
                    table.setRowHeight(20);
                    table.setBackground(Color.white); 
                    table.getColumnModel().getColumn(0).setMaxWidth(100);
                    table.getColumnModel().getColumn(0).setPreferredWidth(150);

                    Database dataRaelsa = new Database();

                    String sql3 = String.format("select nim, time from logperpus");
    
                    try {
                        ResultSet rs = dataRaelsa.perintah.executeQuery(sql3);
                        
                        while (rs.next()) {
                            dataUser[0] = rs.getString("nim");
                            dataUser[1] = rs.getString("time");
                            tableModel.insertRow(0, dataUser);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    
                    try {
                        dataRaelsa.koneksi.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        alwaysCek.start();
        
        tambahBuku.setText("Tambah Buku");
        tambahBuku.setBackground(new Color(50, 94, 190));
        tambahBuku.setPreferredSize(new Dimension(300, 50));
        tambahBuku.setFocusable(false);
        tambahBuku.setForeground(Color.WHITE);
        bgToolbar.add(tambahBuku);

        tambahMember.setText("Tambah Member");
        tambahMember.setBackground(new Color(50, 94, 190));
        tambahMember.setPreferredSize(new Dimension(300, 50));
        tambahMember.setFocusable(false);
        tambahMember.setForeground(Color.WHITE);
        bgToolbar.add(tambahMember);
        
        logOut.setText("Logout");
        logOut.setBackground(new Color(243, 69, 70));
        logOut.setPreferredSize(new Dimension(300, 50));
        logOut.setFocusable(false);
        logOut.setForeground(Color.WHITE);
        bgToolbar.add(logOut);

        logOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();                
            }
            
        });

        tambahBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tambahBuku) {
                    new TambahBuku();
                }
            }
        });

        tambahMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tambahMember) {
                    new TambahMember();
                }
            }
        });

        
        panelGapAtas.setPreferredSize(new Dimension(900, 120));
        panelGapAtas.setOpaque(false);
        panelSampingFotoBuku.add(panelGapAtas);
        

        labelIdBuku.setFont(fontLabelInfo);
        labelIdBuku.setVisible(false);
        labelIdBuku.setPreferredSize(new Dimension(210, 80));
        panel2.add(labelIdBuku);

        labelJudulBuku.setText("Judul Buku   :");
        labelJudulBuku.setFont(fontLabelInfo);
        labelJudulBuku.setVisible(false);
        labelJudulBuku.setPreferredSize(new Dimension(210, 80));
        panel2.add(labelJudulBuku);

        labelPenulisBuku.setFont(fontLabelInfo);
        labelPenulisBuku.setVisible(false);
        labelPenulisBuku.setPreferredSize(new Dimension(210, 80));
        panel2.add(labelPenulisBuku);

        labelTahunTerbit.setFont(fontLabelInfo);
        labelTahunTerbit.setVisible(false);
        labelTahunTerbit.setPreferredSize(new Dimension(210, 80));
        panel2.add(labelTahunTerbit);

        labelStatusBuku.setFont(fontLabelInfo);
        labelStatusBuku.setVisible(false);
        labelStatusBuku.setPreferredSize(new Dimension(210, 80));
        panel2.add(labelStatusBuku);

        labelIdOutput.setFont(fontLabelInfo);
        labelIdOutput.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelIdOutput);

        labelJudulOutput.setFont(fontLabelInfo);
        labelJudulOutput.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelJudulOutput);

        labelPenulisOutput.setFont(fontLabelInfo);
        labelPenulisOutput.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelPenulisOutput);

        labelTahunOutput.setFont(fontLabelInfo);
        labelTahunOutput.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelTahunOutput);

        labelStatusOutput.setFont(fontLabelInfo);
        labelStatusOutput.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelStatusOutput);

        labelTambahan.setFont(fontLabelInfo);
        labelTambahan.setLayout(null);
        labelTambahan.setPreferredSize(new Dimension(550, 80));
        panel3.add(labelTambahan);

        tombolInfoPeminjam.setFont(fontLabelInfo);
        tombolInfoPeminjam.setText("Detail");
        tombolInfoPeminjam.setBackground(new Color(255, 255, 255));
        tombolInfoPeminjam.setBounds(5, 15, 130, 50);
        tombolInfoPeminjam.setFocusable(false);
        tombolInfoPeminjam.setVisible(false);
        labelTambahan.add(tombolInfoPeminjam);

        tombolInfoPeminjam.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Detail(labelIdOutput.getText());
            }
            
        });

        fieldId.setVisible(false);
        fieldId.setFont(fontLabelInfo);
        fieldId.setPreferredSize(new Dimension(500, 80));
        panel3.add(fieldId);

        fieldJudul.setVisible(false);
        fieldJudul.setFont(fontLabelInfo);
        fieldJudul.setPreferredSize(new Dimension(500, 80));
        panel3.add(fieldJudul);

        fieldPenulis.setVisible(false);
        fieldPenulis.setFont(fontLabelInfo);
        fieldPenulis.setPreferredSize(new Dimension(500, 80));
        panel3.add(fieldPenulis);

        fieldTahun.setVisible(false);
        fieldTahun.setFont(fontLabelInfo);
        fieldTahun.setPreferredSize(new Dimension(500, 80));
        panel3.add(fieldTahun);

        fieldFoto.setVisible(false);
        fieldFoto.setFont(fontLabelInfo);
        fieldFoto.setPreferredSize(new Dimension(400, 80));
        panel3.add(fieldFoto);

        panelBawah.setOpaque(false);    
        panelBawah.setPreferredSize(new Dimension(1400, 50));
        panelSampingKanan.add(panelBawah);

        tombolOke.setBackground(new Color(50, 94, 190));
        tombolOke.setForeground(Color.white);
        tombolOke.setFocusable(false);
        tombolOke.setVisible(false);
        tombolOke.setText("Oke");
        tombolOke.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolOke.setBounds(690, 0, 200, 50);
        panelBawah.add(tombolOke);

        tombolBatal.setBackground(new Color(50, 94, 190));
        tombolBatal.setForeground(Color.white);
        tombolBatal.setFocusable(false);
        tombolBatal.setVisible(false);
        tombolBatal.setText("Batal");
        tombolBatal.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolBatal.setBounds(895, 0, 200, 50);
        panelBawah.add(tombolBatal);

        tombolEditFoto.setBackground(new Color(50, 94, 190));
        tombolEditFoto.setPreferredSize(new Dimension(95, 80));
        tombolEditFoto.setForeground(Color.white);
        tombolEditFoto.setFocusable(false);
        tombolEditFoto.setVisible(false);
        tombolEditFoto.setText("Pilih");
        tombolEditFoto.setFont(new Font("Arial", Font.PLAIN, 20));
        panel3.add(tombolEditFoto);

        tombolEditFoto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pilihFoto.showOpenDialog(pilihFoto);
                pilihFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
                String[] namaFoto = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                fieldFoto.setText(namaFoto[namaFoto.length - 1]);
                fieldFoto.requestFocusInWindow();
            }
            
        });

        tombolEdit.setBackground(new Color(50, 94, 190));
        tombolEdit.setFocusable(false);
        tombolEdit.setVisible(false);
        tombolEdit.setForeground(Color.white);
        tombolEdit.setText("Edit Buku");
        tombolEdit.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolEdit.setBounds(895, 0, 200, 50);
        panelBawah.add(tombolEdit);

        labelHapus.setFont(new Font("Arial", Font.BOLD, 40));
        labelHapus.setVisible(true);
        labelHapus.setForeground(merah);
        panelBawah.add(labelHapus);

        tombolPinjam.setBackground(new Color(50, 94, 190));
        tombolPinjam.setBounds(750, 0, 200, 50);
        tombolPinjam.setFocusable(false);
        tombolPinjam.setForeground(Color.white);
        tombolPinjam.setVisible(false);
        tombolPinjam.setText("Pinjamkan Buku");
        tombolPinjam.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolPinjam.setBounds(690, 0, 200, 50);
        panelBawah.add(tombolPinjam);

        fieldInputID.setPreferredSize(new Dimension(250, 30));
        panelSampingKananOverlap.add(labelInputID);
        panelSampingKananOverlap.add(fieldInputID);

        tombolPinjam.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (labelIdOutput.getText().toLowerCase().charAt(0) == 'b') { // ketika id buku diimulai dari b
                    if (temp[4].equals("0")) {
                        String sementara = JOptionPane.showInputDialog("Masukkan ID Member");

                        if (pinjamkanBuku(labelIdOutput.getText(), sementara)) {
                            labelHapus.setForeground(Color.GREEN);
                            labelHapus.setBounds(0, 0, 600, 50);
                            labelHapus.setText("Berhasil meminjamkan buku");
                            tampilkanInfoMember(labelIdOutput.getText());
                            labelHapus.setVisible(true);
                        }
    
                        else {
                            labelHapus.setForeground(merah);
                            labelHapus.setBounds(0, 0, 630, 50);
                            labelHapus.setText("Gagal! Buku Sudah Dipinjamkan");
                            labelHapus.setVisible(true);
                        }
                    }
                }
                        

                else {
                    
                    String sementara = JOptionPane.showInputDialog("Masukkan ID Buku");
                    if (temp[4].equals("0")) {
                        if (sementara.toLowerCase().charAt(0) == 'b') {
                            if (pinjamkanBuku(sementara, labelIdOutput.getText())) {
                                labelHapus.setForeground(Color.GREEN);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Buku Berhasil Dipinjamkan");
                                tampilkanInfoMember(labelIdOutput.getText());
                                labelHapus.setVisible(true);
                            }
        
                            else {
                                labelHapus.setForeground(merah);
                                labelHapus.setBounds(0, 0, 630, 50);
                                labelHapus.setText("Gagal! Buku Sudah Dipinjamkan");
                                labelHapus.setVisible(true);
                            }
                        }
    
                        else {
                            if (pinjamkanBuku(sementara.substring(7), labelIdOutput.getText())) {
                                labelHapus.setForeground(Color.GREEN);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Berhasil meminjamkan buku");
                                tampilkanInfoMember(labelIdOutput.getText());
                                labelHapus.setVisible(true);
                            }
        
                            else {
                                labelHapus.setForeground(merah);
                                labelHapus.setBounds(0, 0, 630, 50);
                                labelHapus.setText("Gagal! Buku Sudah Dipinjamkan");
                                labelHapus.setVisible(true);
                            }
                        }
                    }

                    else {
                        if (sementara.toLowerCase().charAt(0) == 'b') { // untuk input book_id B001
                            if (hapusPinjamkanBuku(sementara, labelIdOutput.getText())) {
                                labelHapus.setForeground(Color.GREEN);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Buku Berhasil Dikembalikan");
                                tampilkanInfoMember(labelIdOutput.getText());
                                labelHapus.setVisible(true);
                            }
        
                            else {
                                labelHapus.setForeground(merah);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Gagal mengembalikan buku");
                                labelHapus.setVisible(true);
                            }
                        }
    
                        else { // untuk input book_id http
                            if (hapusPinjamkanBuku(sementara.substring(7), labelIdOutput.getText())) { 
                                labelHapus.setForeground(Color.GREEN);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Buku Berhasil Dikembalikan");
                                tampilkanInfoMember(labelIdOutput.getText());
                                labelHapus.setVisible(true);
                            }
        
                            else {
                                labelHapus.setForeground(merah);
                                labelHapus.setBounds(0, 0, 630, 50);
                                labelHapus.setText("Gagal! Buku Sudah Dipinjamkan");
                                labelHapus.setVisible(true);
                            }
                        }
                    
                    }
                    
                }
            }
            
        });

        tombolEdit.addActionListener(new ActionListener() {;

            @Override
            public void actionPerformed(ActionEvent e) {
                fieldInputID.setVisible(false);
                labelInputID.setVisible(false);
                tombolOke.setVisible(true);
                tombolEdit.setVisible(false);
                labelTambahan.setVisible(false);
                tombolBatal.setVisible(true);
                fieldId.setVisible(true);
                fieldId.setText(temp[0]);
                tombolPinjam.setVisible(false);
                fieldJudul.setVisible(true);
                fieldJudul.setText(temp[1]);
                fieldPenulis.setVisible(true);
                fieldPenulis.setText(temp[2]);
                fieldTahun.setVisible(true);
                fieldTahun.setText(temp[3]);
                labelStatusBuku.setText("Foto         :");
                if (temp[5] != null) {
                    String[] stringTemp = temp[5].split("\\\\");
                    fieldFoto.setText(stringTemp[stringTemp.length - 1]);
                }
                fieldFoto.setVisible(true);
                tombolEditFoto.setVisible(true);
                labelIdOutput.setVisible(false);
                labelJudulOutput.setVisible(false);
                labelPenulisOutput.setVisible(false);
                labelTahunOutput.setVisible(false);
                labelStatusOutput.setVisible(false);
            }
            
        });

        tombolBatal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tombolOke.setVisible(false);
                tombolEdit.setVisible(true);
                tombolBatal.setVisible(false);
                labelTambahan.setVisible(true);
                fieldId.setVisible(false);
                fieldId.setText("");
                fieldJudul.setVisible(false);
                fieldJudul.setText("");
                fieldPenulis.setVisible(false);
                fieldPenulis.setText("");
                fieldTahun.setVisible(false);
                fieldTahun.setText("");
                labelStatusBuku.setText("Status Buku  :");
                fieldFoto.setVisible(false);
                tombolEditFoto.setVisible(false);
                fieldInputID.setVisible(true);
                labelInputID.setVisible(true);
                tombolPinjam.setVisible(true);
                labelIdOutput.setVisible(true);
                labelJudulOutput.setVisible(true);
                labelPenulisOutput.setVisible(true);
                labelTahunOutput.setVisible(true);
                labelStatusOutput.setVisible(true);
                fieldInputID.requestFocusInWindow();
            }
            
        });

        tombolOke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldId.getText().toLowerCase().charAt(0) == 'b') {
                    File source;
                    File destination;
                
                    if (pilihFoto.getSelectedFile() == null) {
                        if (temp[5] != null) {
                            if (editBuku(fieldId.getText().toUpperCase(), fieldJudul.getText(), fieldPenulis.getText(), fieldTahun.getText(), temp[5].replace("\\", "\\\\"), temp[0])) {
                                labelHapus.setForeground(Color.green);
                                labelHapus.setVisible(true);
                                tombolPinjam.setVisible(true);
                                labelHapus.setText("Berhasil Mengedit Buku");
                                String idSementara = fieldId.getText();

                                if (cekBuku(idSementara)) {
                                    fieldInputID.setText("");
                                    labelIdOutput.setText     (temp[0]);
                                    labelJudulOutput.setText  (temp[1]);
                                    labelPenulisOutput.setText(temp[2]);
                                    labelTahunOutput.setText(temp[3]);
            
                                    if (Integer.valueOf(temp[4]) == 0) {
                                        labelStatusOutput.setText ("Tersedia");
                                        panel1.setPreferredSize(new Dimension(800, 120));
                                        panel2.setPreferredSize(new Dimension(210, 450));
                                        panel3.setPreferredSize(new Dimension(650, 450));
                                        tombolInfoPeminjam.setVisible(false);
                                    }
            
                                    else {
                                        labelStatusOutput.setText ("Buku Sedang Dipinjam");
                                        panel3.setPreferredSize(new Dimension(650, 500));
                                        panel2.setPreferredSize(new Dimension(panel2.getWidth(), 500));
                                        panel1.setPreferredSize(new Dimension(panel1.getWidth(), 80));
                                        tombolInfoPeminjam.setVisible(true);
                                        tombolPinjam.setVisible(false);
                                    }
            
                                    logoBuku.setIcon(changeSize(temp[5], 512));
                                }
            
                                else {
                                    labelHapus.setBackground(merah);
                                    labelHapus.setText("Buku Tidak Ditemukan");
                                    labelIdOutput.setText     ("-");
                                    labelJudulOutput.setText  ("-");
                                    labelPenulisOutput.setText("-");
                                    labelTahunOutput.setText("-");
                                    labelStatusOutput.setText ("-");
                                    fieldId.setText("");
                                }
                            }

                            else {
                                labelHapus.setBackground(merah);
                                labelHapus.setBounds(40, 0, 600, 50);
                                labelHapus.setText("Gagal Mengedit Buku");
                                labelHapus.setVisible(true);
                            }
                            
                        }
                    }

                    else {
                        source = new File(String.valueOf(pilihFoto.getSelectedFile()));

                        String[] namaFile = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                        destination = new File("source\\" + fieldId.getText() + "." + namaFile[namaFile.length - 1].split("\\.")[1]);
                        if (temp[5] != null) {
                            File fotoLama = new File(temp[5]);
                            fotoLama.delete();
                        }

                        if (editBuku(fieldId.getText().toUpperCase(), fieldJudul.getText(), fieldPenulis.getText(), fieldTahun.getText(), "source\\\\" + fieldId.getText() + "." + namaFile[namaFile.length - 1].split("\\.")[1], temp[0])) {
                            labelHapus.setForeground(Color.green);
                            labelHapus.setVisible(true);
                            tombolPinjam.setVisible(true);
                            labelHapus.setText("Berhasil Mengedit Buku");
                            String idSementara = fieldId.getText();

                            if (cekBuku(idSementara)) {
                                fieldInputID.setText("");
                                labelIdOutput.setText     (temp[0]);
                                labelJudulOutput.setText  (temp[1]);
                                labelPenulisOutput.setText(temp[2]);
                                labelTahunOutput.setText(temp[3]);
        
                                if (Integer.valueOf(temp[4]) == 0) {
                                    labelStatusOutput.setText ("Tersedia");
                                    panel1.setPreferredSize(new Dimension(800, 120));
                                    panel2.setPreferredSize(new Dimension(210, 450));
                                    panel3.setPreferredSize(new Dimension(650, 450));
                                }
        
                                else {
                                    labelStatusOutput.setText ("Tidak tersedia");
                                }
                                
                                try {
                                    copyFileUsingStream(source, destination);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
        
                                logoBuku.setIcon(changeSize(temp[5].replace("\\", "\\\\"), 512));
                            }
        
                            else {
                                labelHapus.setBackground(merah);
                                labelHapus.setText("Buku Tidak Ditemukan");
                                labelIdOutput.setText     ("-");
                                labelJudulOutput.setText  ("-");
                                labelPenulisOutput.setText("-");
                                labelTahunOutput.setText("-");
                                labelStatusOutput.setText ("-");
                                fieldId.setText("");
                            }
                        }

                        else {
                            labelHapus.setBackground(merah);
                            labelHapus.setBounds(40, 0, 600, 50);
                            labelHapus.setText("Gagal Mengedit Buku");
                            labelHapus.setVisible(true);
                        }
                    }
                    tombolOke.setVisible(false);
                    tombolEdit.setVisible(true);
                    tombolBatal.setVisible(false);
                    fieldId.setVisible(false);
                    fieldId.setText("");
                    fieldJudul.setVisible(false);
                    fieldJudul.setText("");
                    fieldPenulis.setVisible(false);
                    fieldPenulis.setText("");
                    fieldTahun.setVisible(false);
                    fieldTahun.setText("");
                    labelStatusBuku.setText("Status       :");
                    if (temp[5] != null) {
                        String[] stringTemp = temp[5].split("\\\\");
                        fieldFoto.setText(stringTemp[stringTemp.length - 1]);
                    }
                    fieldFoto.setVisible(false);
                    tombolEditFoto.setVisible(false);
                    labelIdOutput.setVisible(true);
                    labelJudulOutput.setVisible(true);
                    tombolPinjam.setVisible(true);
                    labelPenulisOutput.setVisible(true);
                    labelTahunOutput.setVisible(true);
                    labelStatusOutput.setVisible(true);
                    labelInputID.setVisible(true);
                    fieldInputID.setVisible(true);
                    fieldInputID.requestFocusInWindow();
                }

                else {
                    if (pilihFoto.getSelectedFile() == null) {
                        if (temp[5] != null) {
                            if (editMember(fieldId.getText().toUpperCase(), fieldJudul.getText(), fieldPenulis.getText(), fieldTahun.getText(), temp[5].replace("\\", "\\\\"), temp[0])) {
                                String idSementara = fieldId.getText();
                                labelHapus.setForeground(Color.GREEN);
                                tombolPinjam.setVisible(true);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Berhasil Mengedit Member");

                                if (cekMember(idSementara)) {
                                    fieldInputID.setText("");
                                    labelIdOutput.setText     (temp[0]);
                                    labelJudulOutput.setText  (temp[1]);
                                    labelTahunOutput.setText(temp[2]);
                                    labelPenulisOutput.setText(temp[3]);
            
                                    if (Integer.valueOf(temp[4]) == 0) {
                                        labelStatusOutput.setText ("Sedang Tidak Meminjam Buku");
                                    }
            
                                    else {
                                        labelStatusOutput.setText ("Meminjam Buku");
                                    }
            
                                    logoBuku.setIcon(changeSize(temp[5].replace("\\", "\\\\"), 512));
                                }
            
                                else {
                                    labelHapus.setBackground(merah);
                                    labelHapus.setText("Member Tidak Ditemukan");
                                    labelIdOutput.setText     ("-");
                                    labelJudulOutput.setText  ("-");
                                    labelTahunOutput.setText("-");
                                    labelPenulisOutput.setText("-");
                                    labelStatusOutput.setText ("-");
                                    fieldInputID.setText("");
                                }
                            }

                            else {
                                labelHapus.setForeground(merah);
                                labelHapus.setText("Gagal Mengedit Member");
                                labelHapus.setVisible(true);
                            }
                        }
                    }

                    else {
                        File source;
                        File destination;
                        source = new File(String.valueOf(pilihFoto.getSelectedFile()));

                        String[] namaFile = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                        destination = new File("source\\" + fieldId.getText() + "." + namaFile[namaFile.length - 1].split("\\.")[1]);
                        if (temp[5] != null) {
                            File fotoLama = new File(temp[5]);
                            fotoLama.delete();
                        }
                        
                        if (editMember(fieldId.getText().toUpperCase(), fieldJudul.getText(), fieldPenulis.getText(), fieldTahun.getText(), "source\\\\" + fieldId.getText() + "." + namaFile[namaFile.length - 1].split("\\.")[1], temp[0])) {
                            String idSementara = fieldId.getText();
                            if (cekMember(idSementara)) {
                                try {
                                    copyFileUsingStream(source, destination);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                fieldInputID.setText("");
                                labelHapus.setForeground(Color.GREEN);
                                tombolPinjam.setVisible(true);
                                labelHapus.setBounds(0, 0, 600, 50);
                                labelHapus.setText("Berhasil Mengedit Member");
                                labelIdOutput.setText     (temp[0]);
                                labelJudulOutput.setText  (temp[1]);
                                labelPenulisOutput.setText(temp[2]);
                                labelTahunOutput.setText(temp[3]);
        
                                if (Integer.valueOf(temp[4]) == 0) {
                                    labelStatusOutput.setText ("Sedang Tidak Meminjam Buku");
                                }
        
                                else {
                                    labelStatusOutput.setText ("Meminjam Buku");
                                }

                                logoBuku.setIcon(changeSize(temp[5].replace("\\", "\\\\"), 512));
                                // System.out.println(temp[5]);
                            }
                        }

                        else {
                            labelHapus.setText("Gagal Mengedit Member");
                        }
                    }

                    tombolOke.setVisible(false);
                    tombolEdit.setVisible(true);
                    tombolBatal.setVisible(false);
                    fieldId.setVisible(false);
                    fieldId.setText("");
                    fieldJudul.setVisible(false);
                    fieldJudul.setText("");
                    fieldPenulis.setVisible(false);
                    fieldPenulis.setText("");
                    fieldTahun.setVisible(false);
                    fieldTahun.setText("");
                    tombolPinjam.setVisible(true);
                    labelStatusBuku.setText("Status       :");
                    if (temp[5] != null) {
                        String[] stringTemp = temp[5].split("\\\\");
                        fieldFoto.setText(stringTemp[stringTemp.length - 1]);
                    }
                    fieldFoto.setVisible(false);
                    tombolEditFoto.setVisible(false);
                    labelIdOutput.setVisible(true);
                    labelJudulOutput.setVisible(true);
                    labelPenulisOutput.setVisible(true);
                    labelTahunOutput.setVisible(true);
                    labelStatusOutput.setVisible(true);
                    labelInputID.setVisible(true);
                    fieldInputID.setVisible(true);
                    fieldInputID.requestFocusInWindow();
                }
            }
        });

        tombolHapusBuku.setBackground(new Color(243, 69, 70));
        tombolHapusBuku.setForeground(Color.white);
        tombolHapusBuku.setFocusable(false);
        tombolHapusBuku.setVisible(false);
        tombolHapusBuku.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolHapusBuku.setBounds(1100, 0, 200, 50);
        panelBawah.add(tombolHapusBuku);


        inputMap.put(delKeyStroke, delKeyStroke.toString());

        actionMap.put(delKeyStroke.toString(), new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tombolHapusBuku.doClick();                
            }
            
        });

        tombolHapusBuku.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tombolHapusBuku) {
                    int yesNo = JOptionPane.showConfirmDialog(null, "Kamu yakin untuk menghapus buku ini?", "Confirm Window", JOptionPane.YES_NO_OPTION);
                    if (yesNo == 0 && temp[0] != null) {
                        if (temp[0].toLowerCase().charAt(0) == 'b') {
                            hapusBuku(temp[0]);
                            labelIdOutput.setText     ("-");
                            labelJudulOutput.setText  ("-");
                            labelTahunOutput.setText("-");
                            labelPenulisOutput.setText("-");
                            labelStatusOutput.setText ("-");
                            
                            logoBuku.setIcon(changeSize("source\\fotoKosong.jpg", 512));
                            labelHapus.setText("Buku Berhasil Dihapus");
                            File kuhapuslahKau = new File(temp[5]);
                            kuhapuslahKau.delete();
                            
                        }

                        else {
                            hapusMember(temp[0]);
                            labelIdOutput.setText     ("-");
                            labelJudulOutput.setText   ("-");
                            labelTahunOutput.setText     ("-");
                            labelPenulisOutput.setText  ("-");
                            labelStatusOutput.setText   ("-");
                            labelHapus.setText("Member Berhasil Dihapus");
                            
                            logoBuku.setIcon(changeSize(fotoLibrarian, 512));
                        }
                    }
                }                
            }
            
        });

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
                if (tampilkan != 0) {
                    new LoginDisplay();
                }
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

        fieldInputID.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tampilkanInfoMember(fieldInputID.getText());
                    fieldInputID.requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
    }
}
