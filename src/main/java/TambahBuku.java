import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TambahBuku {

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

    boolean masukkanBuku (String id, String judul, String tahun, String penulis, String foto) {
        String sql = String.format("insert into book(book_id, judul, penulis, tahun, foto) values ('%s', '%s', '%s', '%s', '%s')", id.toUpperCase(), judul, penulis, tahun, foto);
        
        try {
            Database dataRaelsa = new Database();
            new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    TambahBuku () {
        final JFrame frame = new JFrame();
        frame.setTitle("Tambah Buku");
        frame.setVisible(true);

        Font fontInputField = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20);

        Font fontPlaceholder = new Font("Arial", Font.PLAIN, 15);

        final JTextField fieldID = new JTextField();
        fieldID.requestFocusInWindow();
        fieldID.setPreferredSize(new Dimension(650, 50));
        fieldID.setFont(fontInputField);
        frame.add(fieldID);
        fieldID.setLayout(new BorderLayout());

        final JLabel placeholderID = new JLabel();
        placeholderID.setText("ID Buku");
        placeholderID.setFont(fontPlaceholder);
        placeholderID.setForeground(new Color(166, 166, 166));
        fieldID.add(placeholderID, BorderLayout.WEST);

        final JTextField fieldJudul = new JTextField();
        fieldJudul.setLayout(new BorderLayout());
        fieldJudul.setPreferredSize(new Dimension(650, 50));
        fieldJudul.setFont(fontInputField);
        frame.add(fieldJudul);

        final JLabel placeholderJudul = new JLabel();
        placeholderJudul.setText("Judul Buku");
        placeholderJudul.setFont(fontPlaceholder);
        placeholderJudul.setForeground(new Color(166, 166, 166));
        fieldJudul.add(placeholderJudul, BorderLayout.WEST);

        final JTextField fieldTahun = new JTextField();
        fieldTahun.setLayout(new BorderLayout());
        fieldTahun.setPreferredSize(new Dimension(650, 50));
        fieldTahun.setFont(fontInputField);
        frame.add(fieldTahun);

        final JLabel placeholderTahun = new JLabel();
        placeholderTahun.setText("Tahun Terbit Buku");
        placeholderTahun.setFont(fontPlaceholder);
        placeholderTahun.setForeground(new Color(166, 166, 166));
        fieldTahun.add(placeholderTahun, BorderLayout.WEST);

        final JTextField fieldPenulis = new JTextField();
        fieldPenulis.setLayout(new BorderLayout());
        fieldPenulis.setPreferredSize(new Dimension(650, 50));
        fieldPenulis.setFont(fontInputField);
        frame.add(fieldPenulis);

        final JLabel placeholderPenulis = new JLabel();
        placeholderPenulis.setText("Penulis Buku");
        placeholderPenulis.setFont(fontPlaceholder);
        placeholderPenulis.setForeground(new Color(166, 166, 166));
        fieldPenulis.add(placeholderPenulis, BorderLayout.WEST);

        final JTextField fieldFoto = new JTextField();
        fieldFoto.setLayout(new BorderLayout());
        fieldFoto.setPreferredSize(new Dimension(500, 50));
        fieldFoto.setFont(fontInputField);
        frame.add(fieldFoto);

        final JButton tombolFoto = new JButton();
        tombolFoto.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolFoto.setText("Pilih Foto");
        tombolFoto.setPreferredSize(new Dimension(145, 50));
        frame.add(tombolFoto);
        tombolFoto.setFocusable(false);

        final JFileChooser pilihFoto = new JFileChooser();

        tombolFoto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                pilihFoto.showOpenDialog(pilihFoto);
                pilihFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
                String[] namaFoto = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                fieldFoto.setText(namaFoto[namaFoto.length - 1]);
            }
            
        });

        final JLabel placeholderFoto = new JLabel();
        placeholderFoto.setText("Lokasi Foto");
        placeholderFoto.setFont(fontPlaceholder);
        placeholderFoto.setForeground(new Color(166, 166, 166));
        fieldFoto.add(placeholderFoto, BorderLayout.WEST);

        JPanel panelBawah1 = new JPanel(new FlowLayout());
        panelBawah1.setPreferredSize(new Dimension(650, 25));
        frame.add(panelBawah1);

        final JButton tombolOke = new JButton();
        tombolOke.setBackground(new Color(50, 94, 190));
        tombolOke.setForeground(Color.white);
        tombolOke.setPreferredSize(new Dimension(200, 50));
        tombolOke.setFocusable(false);
        tombolOke.setText("Done");
        tombolOke.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(tombolOke);

        JPanel panelBawah2 = new JPanel(new FlowLayout());
        panelBawah2.setPreferredSize(new Dimension(650, 50));
        frame.add(panelBawah2);

        final JLabel berhasilTidak = new JLabel();
        berhasilTidak.setFont(new Font("Arial", Font.BOLD, 20));
        panelBawah2.add(berhasilTidak);

        frame.setLayout(new FlowLayout());
        frame.setSize(700, 460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tombolOke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File source = new File(String.valueOf(pilihFoto.getSelectedFile()));

                    String[] temp = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                    File destination = new File("source\\" + fieldID.getText() + "." + temp[temp.length - 1].split("\\.")[1]);

                    if (masukkanBuku(fieldID.getText(), fieldJudul.getText(), fieldPenulis.getText(), fieldTahun.getText(), "source\\\\" + fieldID.getText() + "." + temp[temp.length - 1].split("\\.")[1])) {
                        berhasilTidak.setForeground(new Color(111, 144, 84));
                        berhasilTidak.setText("Berhasil Menambahkan Member Baru");
                    }

                    else {
                        berhasilTidak.setForeground(new Color(243, 69, 70));
                        berhasilTidak.setText("Gagal Menambahkan Member Baru");
                    }

                    try {
                        copyFileUsingStream(source, destination);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    fieldID.setText("");
                    fieldJudul.setText("");
                    fieldPenulis.setText("");
                    fieldTahun.setText("");
                    fieldFoto.setText("");
            }
            
        });

        fieldID.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldJudul.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldJudul.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldTahun.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldTahun.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldPenulis.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldPenulis.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tombolFoto.doClick();
                    fieldFoto.requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldFoto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    File source = new File(String.valueOf(pilihFoto.getSelectedFile()));

                    String[] temp = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                    File destination = new File("source\\" + fieldID.getText() + "." + temp[temp.length - 1].split("\\.")[1]);

                    if (masukkanBuku(fieldID.getText(), fieldJudul.getText(), fieldTahun.getText(), fieldPenulis.getText(), "source\\\\" + fieldID.getText() + "." + temp[temp.length - 1].split("\\.")[1])) {
                        berhasilTidak.setForeground(new Color(111, 144, 84));
                        berhasilTidak.setText("Berhasil Menambahkan Buku Baru");
                    }

                    else {
                        berhasilTidak.setForeground(new Color(243, 69, 70));
                        berhasilTidak.setText("Gagal Menambahkan Buku Baru");
                    }

                    try {
                        copyFileUsingStream(source, destination);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    fieldID.setText("");
                    fieldJudul.setText("");
                    fieldPenulis.setText("");
                    fieldTahun.setText("");
                    fieldFoto.setText("");
                    fieldID.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        

        fieldID.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderID.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldID.getText().equals("")) {
                    placeholderID.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldJudul.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderJudul.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldJudul.getText().equals("")) {
                    placeholderJudul.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });


        fieldPenulis.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderPenulis.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldPenulis.getText().equals("")) {
                    placeholderPenulis.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldTahun.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderTahun.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldTahun.getText().equals("")) {
                    placeholderTahun.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldFoto.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderFoto.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldFoto.getText().equals("")) {
                    placeholderFoto.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

    }
}
