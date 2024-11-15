/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projekakhir;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author USEr
 */
public class lastProject extends javax.swing.JFrame {
Connection kon;

private DefaultTableModel tabel1;
boolean AddId = false;
private int Id;

private DefaultTableModel tabel2;
boolean AddNrp = false;
private int Nrp;

private DefaultTableModel tabel3;
boolean AddPem = false;
private int Pem;

private DefaultTableModel tabel4;
private DefaultTableModel tabel4_1;
boolean AddNt = false;
private int Nt;

private DefaultTableModel tabel5;
private DefaultTableModel tabel6;
private DefaultTableModel tabel7;
    /**
     * Creates new form lastProject
     */
    public lastProject() {
        initComponents();
        jTabbedPane1.setSelectedIndex(1);
        kon = koneksi.getConnection();
        
        tabel1 = new DefaultTableModel();
        tbl_pasien.setModel(tabel1);
        tabel1.addColumn("No Pasien");
        tabel1.addColumn("Nama Pasien");
        tabel1.addColumn("Umur");
        tabel1.addColumn("Alamat");
        tf_no.setEditable(false);
        btn_simpan.setEnabled(false);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);
        tf_no.setEnabled(false);
        tf_nama.setEnabled(false);
        tf_usia.setEnabled(false);
        tf_alamat.setEnabled(false);
        loadPasien();
        
        tabel2 = new DefaultTableModel();
        tbl_dokter.setModel(tabel2);
        tabel2.addColumn("NRP");
        tabel2.addColumn("Nama Dokter");
        tabel2.addColumn("Spesialis");
        tf_nrp.setEditable(false);
        btn_simpan_1.setEnabled(false);
        btn_update_1.setEnabled(false);
        btn_hapus_1.setEnabled(false);
        tf_nrp.setEnabled(false);
        tf_nama_1.setEnabled(false);
        cmb_spesialis.setEnabled(false);
        loadDokter();
        
        tabel3 = new DefaultTableModel();
        tbl_pemeriksaan.setModel(tabel3);
        tabel3.addColumn("No Pemeriksaan");
        tabel3.addColumn("No Antrean");
        tabel3.addColumn("Tanggal Periksa");
        tabel3.addColumn("Jenis Periksa");
        tabel3.addColumn("Dokter");
        tabel3.addColumn("Spesialis");
        tabel3.addColumn("Pasien");
        tabel3.addColumn("Diagnosa");
        tf_pem.setEditable(false);
        btn_simpan_2.setEnabled(false);
        btn_update_2.setEnabled(false);
        btn_hapus_2.setEnabled(false);
        dcTanggalPeriksa.setDate(new Date());
        dcTanggalPeriksa.getDateEditor().setEnabled(false);
        tfNoAntrean.setEnabled(false);
        tf_diagnosa.setEnabled(false);
        tf_pem.setEnabled(false);
        cmb_dokter.setEnabled(false);
        cmb_pasien.setEnabled(false);
        cbJenisPeriksa.setEnabled(false);
        loadDokterNames();
        loadPasienNames();
        loadPemeriksaan();
        
        tabel4 = new DefaultTableModel();
        tbl_resep.setModel(tabel4);
        tabel4.addColumn("No Transaksi");
        tabel4.addColumn("No Pemeriksaan");
        tabel4.addColumn("Harga");
        tabel4_1 = new DefaultTableModel();
        tbl_resep_1.setModel(tabel4);
        tabel4_1.addColumn("No Transaksi");
        tabel4_1.addColumn("No Pemeriksaan");
        tabel4_1.addColumn("Harga");
        tf_nt.setEditable(false);
        tf_harga.setEditable(false);
        btn_simpan_3.setEnabled(false);
        btn_batal.setEnabled(false);
        btn_updatee.setEnabled(false);
        tf_nt.setEnabled(false);
        tf_harga.setEnabled(false);
        cmb_np.setEnabled(false);
        btn_hitung.setEnabled(false);
        loadId();
        loadObat();
        loadObat_1();
        
        tabel5 = new DefaultTableModel();
        tbl_transaksi.setModel(tabel5);
        tabel5.addColumn("No Transaksi");
        tabel5.addColumn("Harga Obat");
        tabel5.addColumn("Biaya Pemeriksaan");
        tabel5.addColumn("Total Pembayaran");
        tf_trans.setEditable(false);
        tf_harga_1.setEditable(false);
        tf_total.setEditable(false);
        ta_cetak.setEditable(false);
        btn_cetak.setEnabled(false);
        tf_biaya.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hitung();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                hitung();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hitung();
            }
        });
        loadTransaksi();
        
        tabel6 = new DefaultTableModel();
        tbl_checkout.setModel(tabel6);
        tabel6.addColumn("No Pemeriksaan");
        tabel6.addColumn("No Antrean");
        tabel6.addColumn("Tanggal Periksa");
        tabel6.addColumn("Jenis Periksa");
        tabel6.addColumn("Dokter");
        tabel6.addColumn("Spesialis");
        tabel6.addColumn("Pasien");
        tabel6.addColumn("Diagnosa");
        btn_checkout.setEnabled(false);
        btn_batall.setEnabled(false);
        dateChooser.getDateEditor().setEnabled(false);
        dateChooser_checkout.setDate(new Date());
        dateChooser_checkout.getDateEditor().setEnabled(false);
        tabel7 = new DefaultTableModel();
        tbl_inap.setModel(tabel7);
        tabel7.addColumn("No Pemeriksaan");
        tabel7.addColumn("No Antrean");
        tabel7.addColumn("Tanggal Periksa");
        tabel7.addColumn("Dokter");
        tabel7.addColumn("Pasien");
        tabel7.addColumn("Diagnosa");
        tabel7.addColumn("Status");
        loadCheckoutData();
        loadPemeriksaan();
        loadRiwayatInap();
    }
    
    private void loadFor(){
        try{
            String sql = "ALTER TABLE pemeriksaan DROP FOREIGN KEY idpas";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadFor_1(){
        try{
            String sql = "ALTER TABLE pemeriksaan DROP FOREIGN KEY iddok";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadData(){
        try{
            String sql = "ALTER TABLE pasien DROP no";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadData_1(){
        try{
            String sql = "ALTER TABLE dokter DROP nrp";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadData_2(){
        try{
            String sql = "ALTER TABLE pemeriksaan DROP id";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadData_3(){
        try{
            String sql = "ALTER TABLE resep DROP nt";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadDataa(){
        try{
            String sql = "ALTER TABLE pasien ADD no INT NOT NULL PRIMARY KEY AUTO_INCREMENT KEY FIRST";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadDataa_1(){
        try{
            String sql = "ALTER TABLE dokter ADD nrp INT NOT NULL PRIMARY KEY AUTO_INCREMENT KEY FIRST";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadDataa_2(){
        try{
            String sql = "ALTER TABLE pemeriksaan ADD id INT NOT NULL PRIMARY KEY AUTO_INCREMENT KEY FIRST";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadDataa_3(){
        try{
            String sql = "ALTER TABLE resep ADD nt INT NOT NULL PRIMARY KEY AUTO_INCREMENT KEY FIRST";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadForr(){
        try{
            String sql = "ALTER TABLE pemeriksaan ADD CONSTRAINT idpas FOREIGN KEY (pasien) REFERENCES pasien(no)";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadForr_1(){
        try{
            String sql = "ALTER TABLE pemeriksaan ADD CONSTRAINT iddok FOREIGN KEY (dokter) REFERENCES dokter(nrp)";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.out.println("Error" + e.getMessage());
         }
    }
    
    private void loadPasien(){
        tabel1.setRowCount(0);
        try{
            String sql = "SELECT * FROM pasien";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               tabel1.addRow(new Object[]{
               rs.getInt("no"),
               rs.getString("nama"),
               rs.getInt("usia"),
               rs.getString("alamat")
            });
            }
        } catch (SQLException e) {
           System.out.println("Error Save Data" + e.getMessage());
         }
    }
    
    private void loadDokter(){
        tabel2.setRowCount(0);
        try{
            String sql = "SELECT * FROM dokter";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               tabel2.addRow(new Object[]{
               rs.getInt("nrp"),
               rs.getString("nama"),
               rs.getString("spesialis")
            });
            }
        } catch (SQLException e) {
           System.out.println("Error Save Data" + e.getMessage());
         }
    }
    
    private void loadPemeriksaan() {
        tabel3.setRowCount(0);
        try {
            String sql = "SELECT pemeriksaan.id, pemeriksaan.no_antrean, pemeriksaan.tanggal_periksa, pemeriksaan.jenis_periksa, dokter.nama AS dokter, dokter.spesialis, pasien.nama AS pasien, pemeriksaan.diagnosa " +
                         "FROM pemeriksaan " +
                         "JOIN dokter ON pemeriksaan.dokter = dokter.nrp " +
                         "JOIN pasien ON pemeriksaan.pasien = pasien.no " +
                         "WHERE pemeriksaan.jenis_periksa IS NULL OR pemeriksaan.jenis_periksa != 'Checked Out'";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabel3.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getInt("no_antrean"),
                    rs.getDate("tanggal_periksa"),
                    rs.getString("jenis_periksa"),
                    rs.getString("dokter"),
                    rs.getString("spesialis"),
                    rs.getString("pasien"),
                    rs.getString("diagnosa")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading pemeriksaan data: " + e.getMessage());
        }
    }

    private void loadObat(){
        tabel4.setRowCount(0);
        try{
            String sql = "SELECT * FROM resep";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               tabel4.addRow(new Object[]{
               rs.getInt("nt"),
               rs.getString("pem"),
               rs.getInt("harga")
            });
            }
        } catch (SQLException e) {
           System.out.println("Error Save Data" + e.getMessage());
         }
    }
    
    private void loadObat_1(){
        tabel4_1.setRowCount(0);
        try{
            String sql = "SELECT * FROM resep";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               tabel4_1.addRow(new Object[]{
               rs.getInt("nt"),
               rs.getString("pem"),
               rs.getInt("harga")
            });
            }
        } catch (SQLException e) {
           System.out.println("Error Save Data" + e.getMessage());
         }
    }
    
    private void loadTransaksi(){
        tabel5.setRowCount(0);
        try{
            String sql = "SELECT * FROM transaksi";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               tabel5.addRow(new Object[]{
               rs.getInt("no_trans"),
               rs.getInt("harga"),
               rs.getInt("biaya"),
               rs.getInt("total")
            });
            }
        } catch (SQLException e) {
           System.out.println("Error Save Data" + e.getMessage());
         }
    }
    
    private void generateId() {
        try {
            String sql = "INSERT INTO pasien (nama, usia, alamat) VALUES (NULL, NULL, NULL)";
            PreparedStatement ps = kon.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Id = rs.getInt(1);
                tf_no.setText(String.valueOf(Id));
            }
            btn_baru.setText("BATAL");
            btn_simpan.setEnabled(true);
            btn_update.setEnabled(false);
            btn_hapus.setEnabled(false);
        } catch (SQLException e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }
    }
    
    private void generateNrp() {
        try {
            String sql = "INSERT INTO dokter (nama, spesialis) VALUES (NULL, NULL)";
            PreparedStatement ps = kon.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Nrp = rs.getInt(1);
                tf_nrp.setText(String.valueOf(Nrp));
            }
            btn_baru_1.setText("BATAL");
            btn_simpan_1.setEnabled(true);
            btn_update_1.setEnabled(false);
            btn_hapus_1.setEnabled(false);
        } catch (SQLException e) {
            System.out.println("Error generating NRP: " + e.getMessage());
        }
    }
    
    private void generatePem() {
        try {
            String sql = "INSERT INTO pemeriksaan (dokter, pasien, diagnosa, no_antrean, tanggal_periksa, jenis_periksa) VALUES (NULL, NULL, NULL, NULL, NULL, NULL)";
            PreparedStatement ps = kon.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Pem = rs.getInt(1);
                tf_pem.setText(String.valueOf(Pem));
            }
            btn_baru_2.setText("BATAL");
            btn_simpan_2.setEnabled(true);
            btn_update_2.setEnabled(false);
            btn_hapus_2.setEnabled(false);
        } catch (SQLException e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }
    }
    
    private void generateNt() {
        try {
            String sql = "INSERT INTO resep (harga) VALUES (NULL)";
            PreparedStatement ps = kon.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Nt = rs.getInt(1);
                tf_nt.setText(String.valueOf(Nt));
            }
            btn_baru_3.setText("BATAL");
            btn_simpan_3.setEnabled(true);
        } catch (SQLException e) {
            System.out.println("Error generating No Transaksi: " + e.getMessage());
        }
    }
    
    private void reset() {
        AddId = false;
        btn_baru.setText("BARU");
        btn_baru.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);
        tf_no.setText("");
        tf_nama.setText("");
        tf_usia.setText("");
        tf_alamat.setText("");
        tf_no.setEnabled(false);
        tf_nama.setEnabled(false);
        tf_usia.setEnabled(false);
        tf_alamat.setEnabled(false);
    }
    
    private void reset_1() {
        AddNrp = false;
        btn_baru_1.setText("BARU");
        btn_baru_1.setEnabled(true);
        btn_simpan_1.setEnabled(false);
        btn_update_1.setEnabled(false);
        btn_hapus_1.setEnabled(false);
        tf_nrp.setText("");
        tf_nama_1.setText("");
        cmb_spesialis.setSelectedIndex(0);
        tf_nrp.setEnabled(false);
        tf_nama_1.setEnabled(false);
        cmb_spesialis.setEnabled(false);
    }
    
    private void reset_2() {
        AddPem = false;
        btn_baru_2.setText("BARU");
        btn_baru_2.setEnabled(true);
        btn_simpan_2.setEnabled(false);
        btn_update_2.setEnabled(false);
        btn_hapus_2.setEnabled(false);
        tf_pem.setText("");
        tf_diagnosa.setText("");
        tfNoAntrean.setText("");
        cbJenisPeriksa.setSelectedIndex(0);
        cmb_dokter.setSelectedIndex(0);
        cmb_pasien.setSelectedIndex(0);
        tfNoAntrean.setEnabled(false);
        tf_diagnosa.setEnabled(false);
        tf_pem.setEnabled(false);
        cmb_dokter.setEnabled(false);
        cmb_pasien.setEnabled(false);
        cbJenisPeriksa.setEnabled(false);
    }
    
    private void reset_3() {
        AddNt = false;
        btn_baru_3.setText("BARU");
        btn_baru_3.setEnabled(true);
        btn_simpan_3.setEnabled(false);
        tf_nt.setText("");
        tf_harga.setText("");
        cmb_np.setSelectedIndex(0);
        paracetamol.setSelected(false);
        ibuprofen.setSelected(false);
        antasida.setSelected(false);
        ctm.setSelected(false);
        amoxicillin.setSelected(false);
        betadine.setSelected(false);
        vitamin.setSelected(false);
        
    }

    private void rowId() {
        int Row = tbl_pasien.getSelectedRow();
        tf_no.setText(tbl_pasien.getValueAt(Row, 0).toString());
        tf_nama.setText(tbl_pasien.getValueAt(Row, 1).toString());
        tf_usia.setText(tbl_pasien.getValueAt(Row, 2).toString());
        tf_alamat.setText(tbl_pasien.getValueAt(Row, 3).toString());
    }
    
    private void rowNrp() {
        int Row = tbl_dokter.getSelectedRow();
        tf_nrp.setText(tbl_dokter.getValueAt(Row, 0).toString());
        tf_nama_1.setText(tbl_dokter.getValueAt(Row, 1).toString());
        cmb_spesialis.setSelectedItem(tbl_dokter.getValueAt(Row, 2));
    }
    
    private void rowPem() {
        int Row = tbl_pemeriksaan.getSelectedRow();
        tf_pem.setText(tbl_pemeriksaan.getValueAt(Row, 0).toString());
        tfNoAntrean.setText(tbl_pemeriksaan.getValueAt(Row, 1).toString());
        try {
            String tanggalPeriksa = tbl_pemeriksaan.getValueAt(Row, 2).toString();
            Date datePeriksa = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalPeriksa);
            dcTanggalPeriksa.setDate(datePeriksa);
            dateChooser_checkout.setEnabled(true);
            dateChooser_checkout.setDate(null); 
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        cbJenisPeriksa.setSelectedItem(tbl_pemeriksaan.getValueAt(Row, 3));
        cmb_dokter.setSelectedItem(tbl_pemeriksaan.getValueAt(Row, 4));
        cmb_pasien.setSelectedItem(tbl_pemeriksaan.getValueAt(Row, 6)); 
        tf_diagnosa.setText(tbl_pemeriksaan.getValueAt(Row, 7).toString()); 
    }

    private void rowNt() {
        int Row = tbl_resep_1.getSelectedRow();
        tf_trans.setText(tbl_resep_1.getValueAt(Row, 0).toString());
        tf_harga_1.setText(tbl_resep_1.getValueAt(Row, 2).toString());
    }
    
    private void rowTrans() {
        int selectedRow = tbl_transaksi.getSelectedRow();
        if (selectedRow != -1) {
            tf_trans.setText(tabel5.getValueAt(selectedRow, 0).toString());
            tf_harga_1.setText(tabel5.getValueAt(selectedRow, 1).toString());
            String biaya = tabel5.getValueAt(selectedRow, 2).toString(); 
            tf_biaya.setText(biaya.trim());
            tf_total.setText(tabel5.getValueAt(selectedRow, 3).toString());
        }
    }
    
    private void RowCheck(){
        int Row = tbl_checkout.getSelectedRow();
        tf_check.setText(tbl_checkout.getValueAt(Row, 0).toString());
        try {
            String tanggalPeriksa = tbl_checkout.getValueAt(Row, 2).toString();
            Date datePeriksa = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalPeriksa);
            dateChooser.setDate(datePeriksa);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        
    }

    private void Batal() {
        try {
            String sql = "DELETE FROM pasien WHERE no = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_no.getText()));
            ps.executeUpdate();
            btn_baru.setText("BARU");
            loadFor();
            loadData();
            loadDataa();
            loadForr();
            loadPasien();
            reset();
        } catch (SQLException e) {
            System.out.println("Error deleting temporary data: " + e.getMessage());
        }
    }
    
    private void Batal_1() {
        try {
            String sql = "DELETE FROM dokter WHERE nrp = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_nrp.getText()));
            ps.executeUpdate();
            btn_baru_1.setText("BARU");
            loadFor_1();
            loadData_1();
            loadDataa_1();
            loadForr_1();
            loadDokter();
            reset_1();
        } catch (SQLException e) {
            System.out.println("Error deleting temporary data: " + e.getMessage());
        }
    }
    
    private void Batal_2() {
        try {
            String sql = "DELETE FROM pemeriksaan WHERE id = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_pem.getText()));
            ps.executeUpdate();
            btn_baru_2.setText("BARU");
            loadData_2();
            loadDataa_2();
            loadPemeriksaan();
            reset_2();
        } catch (SQLException e) {
            System.out.println("Error deleting temporary data: " + e.getMessage());
        }
    }
    
    private void Batal_3() {
        try {
            String sql = "DELETE FROM resep WHERE nt = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_nt.getText()));
            ps.executeUpdate();
            btn_baru_3.setText("BARU");
            loadData_3();
            loadDataa_3();
            loadObat();
            reset_3();
        } catch (SQLException e) {
            System.out.println("Error deleting temporary data: " + e.getMessage());
        }
    }
    
    public void tab() {
        jTabbedPane1.setSelectedIndex(2);
    }
    
    
    private void loadDokterNames() {
        cmb_dokter.removeAllItems();
        try {
            String sql = "SELECT nama FROM dokter";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmb_dokter.addItem(rs.getString("nama"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading employee names: " + e.getMessage());
        }
    }
    
    private void loadPasienNames() {
        cmb_pasien.removeAllItems();
        try {
            String sql = "SELECT nama FROM pasien";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmb_pasien.addItem(rs.getString("nama"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading employee names: " + e.getMessage());
        }
    }
    
    private void loadId() {
        cmb_np.removeAllItems();
        try {
            String sql = "SELECT id FROM pemeriksaan";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmb_np.addItem(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading combobox id: " + e.getMessage());
        }
    }
    
    private int getDokter() throws SQLException {
        String dokter = (String) cmb_dokter.getSelectedItem();
        String sql = "SELECT nrp FROM dokter WHERE nama = ?";
        PreparedStatement ps = kon.prepareStatement(sql);
        ps.setString(1, dokter);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("nrp");
        }
        throw new SQLException("ID karyawan tidak ditemukan untuk nama: " + dokter);
    }
    
    private int getPasien() throws SQLException {
        String pasien = (String) cmb_pasien.getSelectedItem();
        String sql = "SELECT no FROM pasien WHERE nama = ?";
        PreparedStatement ps = kon.prepareStatement(sql);
        ps.setString(1, pasien);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("no");
        }
        throw new SQLException("ID karyawan tidak ditemukan untuk nama: " + pasien);
    }
    
    private void hitung(){
        int s_biaya = Integer.parseInt(tf_biaya.getText().trim());
        int harga = Integer.parseInt(tf_harga_1.getText());
        int total = harga + s_biaya;
        
        tf_total.setText(Integer.toString(total));
    }
    
    private boolean cekAntrean(int noAntrean, Date tanggalPeriksa) {
        try {
            String sql = "SELECT COUNT(*) FROM pemeriksaan WHERE no_antrean = ? AND tanggal_periksa = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, noAntrean);
            ps.setDate(2, new java.sql.Date(tanggalPeriksa.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error checking no_antrean availability: " + e.getMessage());
        }
        return true;
    }
    
    private void loadCheckoutData() {
        tabel6.setRowCount(0);
        try {
            String sql = "SELECT pemeriksaan.id, pemeriksaan.no_antrean, pemeriksaan.tanggal_periksa, pemeriksaan.jenis_periksa, dokter.nama AS dokter, dokter.spesialis, pasien.nama AS pasien, pemeriksaan.diagnosa " +
                         "FROM pemeriksaan " +
                         "JOIN dokter ON pemeriksaan.dokter = dokter.nrp " +
                         "JOIN pasien ON pemeriksaan.pasien = pasien.no " +
                         "WHERE pemeriksaan.jenis_periksa = 'Rawat Inap' AND (pemeriksaan.jenis_periksa IS NULL OR pemeriksaan.jenis_periksa != 'Checked Out')";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabel6.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getInt("no_antrean"),
                    rs.getDate("tanggal_periksa"),
                    rs.getString("jenis_periksa"),
                    rs.getString("dokter"),
                    rs.getString("spesialis"),
                    rs.getString("pasien"),
                    rs.getString("diagnosa")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading checkout data: " + e.getMessage());
        }
    }
    
    private void loadRiwayatInap() {
        tabel7.setRowCount(0);
        try {
            String sql = "SELECT pemeriksaan.id, pemeriksaan.no_antrean, pemeriksaan.tanggal_periksa, pemeriksaan.jenis_periksa, dokter.nama AS dokter, dokter.spesialis, pasien.nama AS pasien, pemeriksaan.diagnosa " +
                         "FROM pemeriksaan " +
                         "JOIN dokter ON pemeriksaan.dokter = dokter.nrp " +
                         "JOIN pasien ON pemeriksaan.pasien = pasien.no " +
                         "WHERE pemeriksaan.jenis_periksa = 'Checked Out' AND (pemeriksaan.jenis_periksa IS NULL OR pemeriksaan.jenis_periksa != 'Rawat Inap')";
            PreparedStatement ps = kon.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabel7.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getInt("no_antrean"),
                    rs.getString("tanggal_periksa"),
                    rs.getString("dokter"),
                    rs.getString("pasien"),
                    rs.getString("diagnosa"),
                    rs.getString("jenis_periksa")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading riwayat data: " + e.getMessage());
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pasien = new javax.swing.JTable();
        tf_no = new javax.swing.JTextField();
        tf_usia = new javax.swing.JTextField();
        tf_alamat = new javax.swing.JTextField();
        tf_nama = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_baru = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        tf_nrp = new javax.swing.JTextField();
        tf_nama_1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cmb_spesialis = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dokter = new javax.swing.JTable();
        btn_hapus_1 = new javax.swing.JButton();
        btn_update_1 = new javax.swing.JButton();
        btn_baru_1 = new javax.swing.JButton();
        btn_simpan_1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tf_pem = new javax.swing.JTextField();
        btn_baru_2 = new javax.swing.JButton();
        btn_simpan_2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        tf_diagnosa = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_pemeriksaan = new javax.swing.JTable();
        cmb_dokter = new javax.swing.JComboBox<>();
        cmb_pasien = new javax.swing.JComboBox<>();
        btn_hapus_2 = new javax.swing.JButton();
        btn_update_2 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        tfNoAntrean = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        dcTanggalPeriksa = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        cbJenisPeriksa = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tf_nt = new javax.swing.JTextField();
        tf_harga = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btn_baru_3 = new javax.swing.JButton();
        btn_simpan_3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_resep = new javax.swing.JTable();
        cmb_np = new javax.swing.JComboBox<>();
        btn_hitung = new javax.swing.JButton();
        paracetamol = new javax.swing.JCheckBox();
        ibuprofen = new javax.swing.JCheckBox();
        antasida = new javax.swing.JCheckBox();
        ctm = new javax.swing.JCheckBox();
        amoxicillin = new javax.swing.JCheckBox();
        betadine = new javax.swing.JCheckBox();
        vitamin = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        tf_trans = new javax.swing.JTextField();
        tf_harga_1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tf_biaya = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        tf_total = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        btn_batal = new javax.swing.JButton();
        btn_updatee = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        ta_cetak = new javax.swing.JTextArea();
        btn_cetak = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_resep_1 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        btn_checkout = new javax.swing.JButton();
        dateChooser_checkout = new com.toedter.calendar.JDateChooser();
        dateChooser = new com.toedter.calendar.JDateChooser();
        tf_check = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_checkout = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        btn_batall = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_inap = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn_checkout1 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 255));
        jTabbedPane1.setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel10.setPreferredSize(new java.awt.Dimension(880, 680));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel16.setText("PASIEN");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jPanel10.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel17.setText("NO PASIEN");

        tbl_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_pasien);

        tf_no.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_usia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_alamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_nama.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel18.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel18.setText("USIA");

        jLabel19.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel19.setText("NAMA PASIEN");

        jLabel20.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel20.setText("ALAMAT");

        btn_simpan.setBackground(new java.awt.Color(51, 102, 255));
        btn_simpan.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(51, 102, 255));
        btn_update.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(51, 102, 255));
        btn_hapus.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_baru.setBackground(new java.awt.Color(51, 102, 255));
        btn_baru.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_baru.setForeground(new java.awt.Color(255, 255, 255));
        btn_baru.setText("BARU");
        btn_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baruActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btn_baru, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_no, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(135, 135, 135)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(tf_usia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_no, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_usia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_baru, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel10.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 820, 610));

        jTabbedPane2.addTab("PASIEN", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(51, 51, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Malgun Gothic", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("DOKTER");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel21)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        jPanel11.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 280, 50));

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 255), new java.awt.Color(0, 153, 0)));

        jLabel22.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel22.setText("NRP DOKTER");

        tf_nrp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_nama_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel23.setText("NAMA DOKTER");

        cmb_spesialis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Umum", "Mata", "Gigi", "Anak", "Organ", "Kandungan" }));

        jLabel24.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel24.setText("SPESIALIS");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tf_nama_1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addComponent(tf_nrp)
                        .addComponent(jLabel23)
                        .addComponent(cmb_spesialis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nrp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nama_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_spesialis, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 280, 370));

        tbl_dokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dokterMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_dokter);

        jPanel11.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 380, 420));

        btn_hapus_1.setBackground(new java.awt.Color(51, 102, 255));
        btn_hapus_1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_hapus_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_1.setText("HAPUS");
        btn_hapus_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_1ActionPerformed(evt);
            }
        });
        jPanel11.add(btn_hapus_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, 140, 40));

        btn_update_1.setBackground(new java.awt.Color(51, 102, 255));
        btn_update_1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_update_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_update_1.setText("UPDATE");
        btn_update_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_1ActionPerformed(evt);
            }
        });
        jPanel11.add(btn_update_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 140, 40));

        btn_baru_1.setBackground(new java.awt.Color(51, 102, 255));
        btn_baru_1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_baru_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_baru_1.setText("BARU");
        btn_baru_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baru_1ActionPerformed(evt);
            }
        });
        jPanel11.add(btn_baru_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 140, 40));

        btn_simpan_1.setBackground(new java.awt.Color(51, 102, 255));
        btn_simpan_1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_simpan_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan_1.setText("SIMPAN");
        btn_simpan_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_1ActionPerformed(evt);
            }
        });
        jPanel11.add(btn_simpan_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 140, 40));

        jTabbedPane2.addTab("DOKTER", jPanel11);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel25.setText("PEMERIKSAAN");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addContainerGap())
        );

        jPanel12.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel26.setText("DOKTER");

        jLabel27.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel27.setText("NO PEMERIKSAAN");

        jLabel28.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel28.setText("PASIEN");

        tf_pem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_baru_2.setBackground(new java.awt.Color(51, 102, 255));
        btn_baru_2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_baru_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_baru_2.setText("BARU");
        btn_baru_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baru_2ActionPerformed(evt);
            }
        });

        btn_simpan_2.setBackground(new java.awt.Color(51, 102, 255));
        btn_simpan_2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_simpan_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan_2.setText("SIMPAN");
        btn_simpan_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_2ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel29.setText("DIAGNOSA");

        tf_diagnosa.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tbl_pemeriksaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pemeriksaanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_pemeriksaan);

        cmb_dokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_pasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_hapus_2.setBackground(new java.awt.Color(51, 102, 255));
        btn_hapus_2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_hapus_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_2.setText("HAPUS");
        btn_hapus_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_2ActionPerformed(evt);
            }
        });

        btn_update_2.setBackground(new java.awt.Color(51, 102, 255));
        btn_update_2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_update_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_update_2.setText("UPDATE");
        btn_update_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_2ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel33.setText("NO ANTREAN");

        tfNoAntrean.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel35.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel35.setText("TANGGAL PERIKSA");

        jLabel42.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel42.setText("JENIS PERIKSA");

        cbJenisPeriksa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Periksa", "Rawat Inap" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn_baru_2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_simpan_2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_update_2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btn_hapus_2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(dcTanggalPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(205, 205, 205)
                                .addComponent(cbJenisPeriksa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(cmb_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_pem, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27)))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(cmb_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel21Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(tfNoAntrean, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel21Layout.createSequentialGroup()
                                                .addComponent(jLabel35)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel33)))
                                        .addGap(43, 43, 43)))
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(tf_diagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(77, 77, 77))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_pem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_diagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel35)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbJenisPeriksa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(dcTanggalPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNoAntrean, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(58, 58, 58)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_baru_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jPanel12.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 780, 610));

        jTabbedPane2.addTab("PEMERIKSAAN", jPanel12);

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel30.setText("RESEP OBAT");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addContainerGap())
        );

        jPanel13.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel31.setText("NO PEMERIKSAAN");

        jLabel32.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel32.setText("NO TRANSAKSI");

        tf_nt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_harga.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel34.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel34.setText("HARGA OBAT");

        jLabel36.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel36.setText("NAMA OBAT");

        btn_baru_3.setBackground(new java.awt.Color(51, 102, 255));
        btn_baru_3.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_baru_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_baru_3.setText("BARU");
        btn_baru_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baru_3ActionPerformed(evt);
            }
        });

        btn_simpan_3.setBackground(new java.awt.Color(51, 102, 255));
        btn_simpan_3.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_simpan_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan_3.setText("SIMPAN");
        btn_simpan_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_3ActionPerformed(evt);
            }
        });

        tbl_resep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbl_resep);

        btn_hitung.setBackground(new java.awt.Color(51, 102, 255));
        btn_hitung.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_hitung.setForeground(new java.awt.Color(255, 255, 255));
        btn_hitung.setText("HITUNG");
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });

        paracetamol.setText("Paracetamol");

        ibuprofen.setText("Ibuprofen");

        antasida.setText("Antasida");

        ctm.setText("CTM");

        amoxicillin.setText("Amoxicillin");

        betadine.setText("Betadine");

        vitamin.setText("Vitamin C");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ibuprofen)
                            .addComponent(antasida))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(betadine)
                            .addComponent(amoxicillin)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(paracetamol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ctm))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_baru_3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_np, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36))))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(vitamin)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(btn_simpan_3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_harga, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(71, 71, 71))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(btn_hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(tf_nt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel32)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel34))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paracetamol)
                            .addComponent(ctm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_np, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hitung)
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ibuprofen)
                    .addComponent(amoxicillin)
                    .addComponent(vitamin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(antasida)
                    .addComponent(betadine))
                .addGap(14, 14, 14)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_baru_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 780, 610));

        jTabbedPane2.addTab("RESEP OBAT", jPanel13);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel37.setText("TRANSAKSI OBAT");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addContainerGap())
        );

        jPanel14.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel38.setText("NO TRANSAKSI");

        tf_trans.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_harga_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel39.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel39.setText("HARGA OBAT");

        tf_biaya.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel40.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel40.setText("BIAYA PEMERIKSAAN");

        tf_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel41.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel41.setText("TOTAL PEMBAYARAN");

        btn_batal.setBackground(new java.awt.Color(51, 102, 255));
        btn_batal.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_batal.setForeground(new java.awt.Color(255, 255, 255));
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_updatee.setBackground(new java.awt.Color(51, 102, 255));
        btn_updatee.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_updatee.setForeground(new java.awt.Color(255, 255, 255));
        btn_updatee.setText("UPDATE");
        btn_updatee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateeActionPerformed(evt);
            }
        });

        ta_cetak.setColumns(20);
        ta_cetak.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        ta_cetak.setRows(5);
        jScrollPane6.setViewportView(ta_cetak);

        btn_cetak.setBackground(new java.awt.Color(51, 102, 255));
        btn_cetak.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_cetak.setForeground(new java.awt.Color(255, 255, 255));
        btn_cetak.setText("CETAK");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        tbl_resep_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_resep_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_resep_1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_resep_1);

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_transaksi);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_harga_1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_trans))
                                .addComponent(jLabel38)))))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(btn_updatee, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(tf_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel41))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_harga_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_updatee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel14.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 780, 610));

        jTabbedPane2.addTab("T. OBAT", jPanel14);

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel43.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel43.setText("CHECKOUT");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel43)
                .addContainerGap(580, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addContainerGap())
        );

        jPanel15.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        btn_checkout.setBackground(new java.awt.Color(51, 102, 255));
        btn_checkout.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_checkout.setForeground(new java.awt.Color(255, 255, 255));
        btn_checkout.setText("CHECKOUT");
        btn_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkoutActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel44.setText("TANGGAL MASUK");

        tbl_checkout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_checkout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_checkoutMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_checkout);

        jLabel45.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel45.setText("TANGGAL KELUAR");

        btn_batall.setBackground(new java.awt.Color(51, 102, 255));
        btn_batall.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_batall.setForeground(new java.awt.Color(255, 255, 255));
        btn_batall.setText("BATAL");
        btn_batall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_check, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(btn_batall, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_checkout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooser_checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(42, 42, 42))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(tf_check, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateChooser_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_checkout)
                                .addComponent(btn_batall)))))
                .addGap(28, 28, 28))
        );

        jPanel15.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 780, 610));

        jTabbedPane2.addTab("CHECKOUT", jPanel15);

        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel46.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel46.setText("RIWAYAT PASIEN RAWAT INAP");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel46)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addContainerGap())
        );

        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 80));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        tbl_inap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tbl_inap);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        jPanel28.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 780, 620));

        jTabbedPane2.addTab("RIWAYAT", jPanel28);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel47.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel47.setText("KELUAR");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel47)
                .addContainerGap(631, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addContainerGap())
        );

        jPanel3.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_checkout1.setBackground(new java.awt.Color(51, 102, 255));
        btn_checkout1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        btn_checkout1.setForeground(new java.awt.Color(255, 255, 255));
        btn_checkout1.setText("KELUAR");
        btn_checkout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkout1ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel48.setText("KLIK DISINI");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_checkout1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(btn_checkout1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 780, 610));

        jTabbedPane2.addTab("KELUAR", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("HEALTH CENTER", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\USEr\\Documents\\github\\PROJEK AKHIR\\projekAkhir\\src\\projekakhir\\gambar\\healthcare_2355649 (1).png")); // NOI18N
        jPanel8.add(jLabel12);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel13.setText("HOME");
        jPanel8.add(jLabel13);

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\USEr\\Documents\\github\\PROJEK AKHIR\\projekAkhir\\src\\projekakhir\\gambar\\healthcare_2355649 (1).png")); // NOI18N
        jPanel8.add(jLabel14);

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 80));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\USEr\\Downloads\\clinic_2222554.png")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel15)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel15)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 880, 600));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("HOME", jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_checkoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_checkoutMouseClicked
        btn_checkout.setEnabled(true);
        btn_batall.setEnabled(true);
        RowCheck();
    }//GEN-LAST:event_tbl_checkoutMouseClicked

    private void btn_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkoutActionPerformed
        Date tanggalPeriksa = dateChooser.getDate();
        Date tanggalCheckout = dateChooser_checkout.getDate();
        if (tanggalCheckout.before(tanggalPeriksa)) {
            JOptionPane.showMessageDialog(null,
                "Tanggal checkout tidak boleh sebelum tanggal periksa!",
                "Tanggal Invalid", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String sql = "UPDATE pemeriksaan SET jenis_periksa = 'Checked Out' WHERE id = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_check.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Checkout berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dateChooser_checkout.setEnabled(false);
            tbl_checkout.clearSelection();
            btn_checkout.setEnabled(false);
            btn_batall.setEnabled(false);
            loadCheckoutData();
            loadPemeriksaan();
            loadRiwayatInap();
        } catch (SQLException e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
        dateChooser.setDate(null);
    }//GEN-LAST:event_btn_checkoutActionPerformed

    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        btn_cetak.setEnabled(true);
        btn_batal.setEnabled(true);
        rowTrans();
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void tbl_resep_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_resep_1MouseClicked
        if (!AddPem && tbl_resep_1.getSelectedRow() != -1) {
            btn_updatee.setEnabled(true);
            btn_batal.setEnabled(true);
            rowNt();
        }
    }//GEN-LAST:event_tbl_resep_1MouseClicked

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        if (btn_cetak.getText().equals("CETAK")) {
            int a = Integer.parseInt(tf_trans.getText().trim());
            int b = Integer.parseInt(tf_harga_1.getText().trim());
            int c = Integer.parseInt(tf_biaya.getText().trim());
            int d = Integer.parseInt(tf_total.getText().trim());
            ta_cetak.setText("No Transaksi          : " + a + "\n" +
                "Harga Obat            : " + b + "\n" +
                "Biaya Pemeriksaan : " + c + "\n" +
                "Total Pembayaran  : " + d + "\n");
            btn_cetak.setText("SELESAI");
            btn_batal.setEnabled(false);
            JOptionPane.showMessageDialog(null, "berhasil mencetak invoice", "info",JOptionPane.INFORMATION_MESSAGE);
        } else {
            btn_cetak.setText("CETAK");
            tf_trans.setText("");
            tf_harga_1.setText("");
            tf_biaya.setText("");
            tf_total.setText("");
            tf_biaya.setEditable(true);
            ta_cetak.setText("");
            tbl_transaksi.clearSelection();
            btn_batal.setEnabled(false);
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void btn_updateeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateeActionPerformed
        if(tf_biaya.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "jangan lupa isi biaya pemeriksaannya dulu ya kak!", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            int totall = Integer.parseInt(tf_total.getText());
            String sql = "INSERT INTO transaksi (no_trans, harga, biaya, total) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tf_trans.getText()));
            ps.setInt(2, Integer.parseInt(tf_harga_1.getText()));
            ps.setInt(3, Integer.parseInt(tf_biaya.getText()));
            ps.setInt(4, totall);
            ps.executeUpdate();
            loadTransaksi();
            tf_trans.setText("");
            tf_harga_1.setText("");
            tf_biaya.setText("");
            tf_total.setText("");
            btn_batal.setEnabled(false);
            btn_updatee.setEnabled(false);
            tbl_resep_1.clearSelection();
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil mengupdate data!", "sukses",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_updateeActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        tbl_resep_1.clearSelection();
        tbl_transaksi.clearSelection();
        tf_trans.setText("");
        tf_harga_1.setText("");
        tf_biaya.setText("");
        tf_total.setText("");
        btn_batal.setEnabled(false);
        btn_updatee.setEnabled(false);
        btn_cetak.setEnabled(false);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
        int obat = 0;

        if(paracetamol.isSelected()){
            obat += 5000;
        }
        if(ibuprofen.isSelected()){
            obat += 7000;
        }
        if(antasida.isSelected()){
            obat += 10000;
        }
        if(ctm.isSelected()){
            obat += 5000;
        }
        if(amoxicillin.isSelected()){
            obat += 10000;
        }
        if(betadine.isSelected()){
            obat += 20000;
        }
        if(vitamin.isSelected()){
            obat += 15000;
        }
        tf_harga.setText(String.valueOf(obat));
    }//GEN-LAST:event_btn_hitungActionPerformed

    private void btn_simpan_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_3ActionPerformed
        if (!paracetamol.isSelected() && !ibuprofen.isSelected() && !antasida.isSelected()&& !ctm.isSelected() && !amoxicillin.isSelected() && !betadine.isSelected()&&!vitamin.isSelected()){
            JOptionPane.showMessageDialog(null, "pilih setidaknya 1 obat duluu!", "peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(tf_harga.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "jangan lupa klik button hitung terlebih dahulu ya!", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            AddNt=false;
            if (!AddNt) {
                String sql = "UPDATE resep SET harga = ?, pem = ? WHERE nt = ?";
                PreparedStatement ps = kon.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(tf_harga.getText()));
                ps.setString(2, cmb_np.getSelectedItem().toString());
                ps.setInt(3, Integer.parseInt(tf_nt.getText()));
                ps.executeUpdate();
                loadObat();
                loadObat_1();
                reset_3();
            }
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil menambah data", "sukses",JOptionPane.INFORMATION_MESSAGE);
        tf_nt.setEnabled(false);
        tf_harga.setEnabled(false);
        cmb_np.setEnabled(false);
        btn_hitung.setEnabled(false);
    }//GEN-LAST:event_btn_simpan_3ActionPerformed

    private void btn_baru_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baru_3ActionPerformed
        if (btn_baru_3.getText().equals("BARU")) {
            AddNt = true;
            btn_baru_3.setText("BATAL");
            btn_simpan_3.setEnabled(true);
            generateNt();
            tf_nt.setEnabled(true);
            tf_harga.setEnabled(true);
            cmb_np.setEnabled(true);
            btn_hitung.setEnabled(true);
        } else {
            AddNt = false;
            btn_baru_3.setText("BARU");
            btn_simpan_3.setEnabled(false);
            Batal_3();
            tf_nt.setText("");
            tf_harga.setText("");
            cmb_np.setSelectedIndex(0);
            paracetamol.setSelected(false);
            ibuprofen.setSelected(false);
            antasida.setSelected(false);
            ctm.setSelected(false);
            amoxicillin.setSelected(false);
            betadine.setSelected(false);
            vitamin.setSelected(false);
            tf_nt.setEnabled(false);
            tf_harga.setEnabled(false);
            cmb_np.setEnabled(false);
            btn_hitung.setEnabled(false);
        }
    }//GEN-LAST:event_btn_baru_3ActionPerformed

    private void btn_update_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_2ActionPerformed
        if(tf_diagnosa.getText().trim().isEmpty()||tfNoAntrean.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data dengan sesuai yaa, jangan sampai kosong ya", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (!AddPem) {
                int dokter = getDokter();
                int pasien = getPasien();
                int noAntrean = Integer.parseInt(tfNoAntrean.getText());

                Date tanggal = dcTanggalPeriksa.getDate();
                String tanggalPeriksa = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);

                String jenisPeriksa = cbJenisPeriksa.getSelectedItem().toString();
                String diagnosa = tf_diagnosa.getText();

                String checkSql = "SELECT COUNT(*) AS count FROM pemeriksaan WHERE no_antrean = ? AND tanggal_periksa = ? AND id != ?";
                PreparedStatement checkPs = kon.prepareStatement(checkSql);
                checkPs.setInt(1, noAntrean);
                checkPs.setString(2, tanggalPeriksa);
                checkPs.setInt(3, Integer.parseInt(tf_pem.getText())); 
                ResultSet checkRs = checkPs.executeQuery();
                checkRs.next();
                int count = checkRs.getInt("count");

                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "Nomor antrean pada tanggal yang sama sudah ada. Silakan pilih nomor antrean yang lain.");
                    return;
                }

                String sql = "UPDATE pemeriksaan SET dokter = ?, pasien = ?, no_antrean = ?, tanggal_periksa = ?, jenis_periksa = ?, diagnosa = ? WHERE id = ?";
                PreparedStatement ps = kon.prepareStatement(sql);
                ps.setInt(1, dokter);
                ps.setInt(2, pasien);
                ps.setInt(3, noAntrean);
                ps.setString(4, tanggalPeriksa);
                ps.setString(5, jenisPeriksa);
                ps.setString(6, diagnosa);
                ps.setInt(7, Integer.parseInt(tf_pem.getText()));
                ps.executeUpdate();
                loadId();
                loadPemeriksaan();
                loadCheckoutData();
                reset_2();
            }
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil mengupdate data pemeriksaan", "sukses",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_update_2ActionPerformed

    private void btn_hapus_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_2ActionPerformed
        int confirm = JOptionPane.showOptionDialog(this,
            "anda yakin ingin menghapus data pasien ini?",
            "mau konfirmasi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(confirm == JOptionPane.YES_OPTION){
            try {
                if (!AddPem) {
                    String sql = "DELETE FROM pemeriksaan WHERE id = ?";
                    PreparedStatement ps = kon.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(tf_pem.getText()));
                    ps.executeUpdate();
                    loadId();
                    loadPemeriksaan();
                    loadCheckoutData();
                    loadData_2();
                    loadDataa_2();
                    reset_2();
                }
            } catch (SQLException e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
        }
        if(confirm == JOptionPane.NO_OPTION){
            btn_baru_2.setText("BARU");
            btn_simpan_2.setEnabled(false);
            btn_update_2.setEnabled(false);
            btn_hapus_2.setEnabled(false);
            tbl_pemeriksaan.clearSelection();
            tf_pem.setText("");
            tf_diagnosa.setText("");
            cmb_pasien.setSelectedIndex(0);
            cmb_dokter.setSelectedIndex(0);
            tfNoAntrean.setText("");
            cbJenisPeriksa.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btn_hapus_2ActionPerformed

    private void tbl_pemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pemeriksaanMouseClicked
        if (!AddPem && tbl_pemeriksaan.getSelectedRow() != -1) {
            btn_baru_2.setEnabled(true);
            btn_baru_2.setText("GAJADI");
            btn_update_2.setEnabled(true);
            btn_hapus_2.setEnabled(true);
            tfNoAntrean.setEnabled(true);
            tf_diagnosa.setEnabled(true);
            tf_pem.setEnabled(true);
            cmb_dokter.setEnabled(true);
            cmb_pasien.setEnabled(true);
            cbJenisPeriksa.setEnabled(true);
            rowPem();
        }
    }//GEN-LAST:event_tbl_pemeriksaanMouseClicked

    private void btn_simpan_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_2ActionPerformed
        if(tf_diagnosa.getText().trim().isEmpty()||tfNoAntrean.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data dengan sesuai yaa, jangan sampai kosong ya", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            int noAntrean = Integer.parseInt(tfNoAntrean.getText());
            Date tanggalPeriksa = dcTanggalPeriksa.getDate();
            String jenisPeriksa = cbJenisPeriksa.getSelectedItem().toString();

            if (!cekAntrean(noAntrean, tanggalPeriksa)) {
                JOptionPane.showMessageDialog(null, "Nomor antrean sudah dipakai pada tanggal yang sama.");
                return;
            }

            int dokter = getDokter();
            int pasien = getPasien();
            String sql = "UPDATE pemeriksaan SET no_antrean=?, tanggal_periksa=?, jenis_periksa=?, dokter=?, pasien=?, diagnosa=? WHERE id = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, noAntrean);
            ps.setDate(2, new java.sql.Date(tanggalPeriksa.getTime()));
            ps.setString(3, jenisPeriksa);
            ps.setInt(4, dokter);
            ps.setInt(5, pasien);
            ps.setString(6, tf_diagnosa.getText());
            ps.setString(7, tf_pem.getText());
            ps.executeUpdate();
            loadId();
            loadPemeriksaan();
            loadCheckoutData();
            reset_2();
        } catch (SQLException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil menginput data pemeriksaan", "sukses",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_simpan_2ActionPerformed

    private void btn_baru_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baru_2ActionPerformed
        if (btn_baru_2.getText().equals("BARU")) {
            AddPem = true;
            btn_baru_2.setText("BATAL");
            btn_simpan_2.setEnabled(true);
            btn_update_2.setEnabled(false);
            btn_hapus_2.setEnabled(false);
            tfNoAntrean.setEnabled(true);
            tf_diagnosa.setEnabled(true);
            tf_pem.setEnabled(true);
            cmb_dokter.setEnabled(true);
            cmb_pasien.setEnabled(true);
            cbJenisPeriksa.setEnabled(true);
            generatePem();
        } else if(btn_baru_2.getText().equals("GAJADI")){
            btn_baru_2.setText("BARU");
            btn_simpan_2.setEnabled(false);
            btn_update_2.setEnabled(false);
            btn_hapus_2.setEnabled(false);
            tbl_pemeriksaan.clearSelection();
            tf_pem.setText("");
            tf_diagnosa.setText("");
            cmb_pasien.setSelectedIndex(0);
            cmb_dokter.setSelectedIndex(0);
            tfNoAntrean.setText("");
            cbJenisPeriksa.setSelectedIndex(0);
            tfNoAntrean.setEnabled(false);
            tf_diagnosa.setEnabled(false);
            tf_pem.setEnabled(false);
            cmb_dokter.setEnabled(false);
            cmb_pasien.setEnabled(false);
            cbJenisPeriksa.setEnabled(false);
            dcTanggalPeriksa.setDate(new Date());
        }else {
            AddPem = false;
            btn_baru_2.setText("BARU");
            btn_simpan_2.setEnabled(false);
            Batal_2();
            tf_pem.setText("");
            tf_diagnosa.setText("");
            cmb_dokter.setSelectedIndex(0);
            cmb_pasien.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btn_baru_2ActionPerformed

    private void btn_simpan_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_1ActionPerformed
        if(tf_nama_1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data dengan sesuai yaa, jangan sampai kosong ya", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            String sql = "UPDATE dokter SET nama = ?, spesialis = ? WHERE nrp = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setString(1, tf_nama_1.getText());
            ps.setString(2, cmb_spesialis.getSelectedItem().toString());
            ps.setInt(3, Integer.parseInt(tf_nrp.getText()));
            ps.executeUpdate();
            loadDokterNames();
            loadDokter();
            loadFor_1();
            loadData_1();
            loadDataa_1();
            loadForr_1();
            reset_1();
        } catch (SQLException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil menambah data dokter", "sukses",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_simpan_1ActionPerformed

    private void btn_baru_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baru_1ActionPerformed
        if (btn_baru_1.getText().equals("BARU")) {
            AddNrp = true;
            btn_baru_1.setText("BATAL");
            btn_simpan_1.setEnabled(true);
            btn_update_1.setEnabled(false);
            btn_hapus_1.setEnabled(false);
            tf_nrp.setEnabled(true);
            tf_nama_1.setEnabled(true);
            cmb_spesialis.setEnabled(true);
            generateNrp();
        }else if(btn_baru_1.getText().equals("GAJADI")){
            btn_baru_1.setText("BARU");
            btn_simpan_1.setEnabled(false);
            btn_update_1.setEnabled(false);
            btn_hapus_1.setEnabled(false);
            tbl_dokter.clearSelection();
            tf_nrp.setText("");
            tf_nama_1.setText("");
            cmb_spesialis.setSelectedIndex(0);
            tf_nrp.setEnabled(false);
            tf_nama_1.setEnabled(false);
            cmb_spesialis.setEnabled(false);
        } else {
            AddNrp = false;
            btn_baru_1.setText("BARU");
            btn_simpan_1.setEnabled(false);
            Batal_1();
            tf_nrp.setText("");
            tf_nama_1.setText("");
            cmb_spesialis.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btn_baru_1ActionPerformed

    private void btn_update_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_1ActionPerformed
        if(tf_nama_1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data dengan sesuai yaa, jangan sampai kosong ya", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (!AddNrp) {
                String sql = "UPDATE dokter SET nama = ?, spesialis = ? WHERE nrp = ?";
                PreparedStatement ps = kon.prepareStatement(sql);
                ps.setString(1, tf_nama_1.getText());
                ps.setString(2, cmb_spesialis.getSelectedItem().toString());
                ps.setInt(3, Integer.parseInt(tf_nrp.getText()));
                ps.executeUpdate();
                loadDokter();
                loadDokterNames();
                reset_1();
            }
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "berhasil update data dokter", "sukses",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_update_1ActionPerformed

    private void btn_hapus_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_1ActionPerformed
        int confirm = JOptionPane.showOptionDialog(this,
            "anda yakin ingin menghapus data pasien ini?",
            "mau konfirmasi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(confirm == JOptionPane.YES_OPTION){
            try {
                if (!AddNrp) {
                    String sql = "DELETE FROM dokter WHERE nrp = ?";
                    PreparedStatement ps = kon.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(tf_nrp.getText()));
                    ps.executeUpdate();
                    loadDokterNames();
                    loadDokter();
                    loadFor_1();
                    loadData_1();
                    loadDataa_1();
                    loadForr_1();
                    reset_1();
                }
            } catch (SQLException e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "berhasil delete data dokter", "sukses",JOptionPane.INFORMATION_MESSAGE);
        }
        if(confirm == JOptionPane.NO_OPTION){
            btn_baru_1.setText("BARU");
            btn_simpan_1.setEnabled(false);
            btn_update_1.setEnabled(false);
            btn_hapus_1.setEnabled(false);
            tbl_dokter.clearSelection();
            tf_nrp.setText("");
            tf_nama_1.setText("");
            cmb_spesialis.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btn_hapus_1ActionPerformed

    private void tbl_dokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dokterMouseClicked
        if (!AddNrp && tbl_dokter.getSelectedRow() != -1) {
            btn_baru_1.setEnabled(true);
            btn_baru_1.setText("GAJADI");
            btn_update_1.setEnabled(true);
            btn_hapus_1.setEnabled(true);
            tf_nrp.setEnabled(true);
            tf_nama_1.setEnabled(true);
            cmb_spesialis.setEnabled(true);
            rowNrp();
        }
    }//GEN-LAST:event_tbl_dokterMouseClicked

    private void btn_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baruActionPerformed
        if (btn_baru.getText().equals("BARU")) {
            AddId = true;
            btn_baru.setText("BATAL");
            btn_simpan.setEnabled(true);
            btn_update.setEnabled(false);
            btn_hapus.setEnabled(false);
            tf_no.setEnabled(true);
            tf_nama.setEnabled(true);
            tf_usia.setEnabled(true);
            tf_alamat.setEnabled(true);
            generateId();
        } else if(btn_baru.getText().equals("GAJADI")){
            btn_baru.setText("BARU");
            btn_simpan.setEnabled(false);
            btn_update.setEnabled(false);
            btn_hapus.setEnabled(false);
            tbl_pasien.clearSelection();
            tf_no.setText("");
            tf_nama.setText("");
            tf_usia.setText("");
            tf_alamat.setText("");
            tf_no.setEnabled(false);
            tf_nama.setEnabled(false);
            tf_usia.setEnabled(false);
            tf_alamat.setEnabled(false);
        }else{
            AddId = false;
            btn_baru.setText("BARU");
            btn_simpan.setEnabled(false);
            Batal();
            tf_no.setText("");
            tf_nama.setText("");
            tf_usia.setText("");
            tf_alamat.setText("");
        }
    }//GEN-LAST:event_btn_baruActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int confirm = JOptionPane.showOptionDialog(this,
            "anda yakin ingin menghapus data pasien ini?",
            "mau konfirmasi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(confirm == JOptionPane.YES_OPTION){
            try {
                if (!AddId) {
                    String sql = "DELETE FROM pasien WHERE no = ?";
                    PreparedStatement ps = kon.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(tf_no.getText()));
                    ps.executeUpdate();
                    loadPasienNames();
                    loadFor();
                    loadData();
                    loadDataa();
                    loadForr();
                    loadPasien();
                    reset();
                }
            } catch (SQLException e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "data pasien berhasil dihapus", "info",JOptionPane.INFORMATION_MESSAGE);
        }
        if(confirm == JOptionPane.NO_OPTION){
            btn_baru.setText("BARU");
            btn_simpan.setEnabled(false);
            btn_update.setEnabled(false);
            btn_hapus.setEnabled(false);
            tbl_pasien.clearSelection();
            tf_no.setText("");
            tf_nama.setText("");
            tf_usia.setText("");
            tf_alamat.setText("");
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if(tf_nama.getText().trim().isEmpty()||tf_usia.getText().trim().isEmpty()||tf_alamat.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data yang sesuai ya, jangan ada yang kosong", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (!AddId) {
                String sql = "UPDATE pasien SET nama = ?, usia = ?, alamat = ? WHERE no = ?";
                PreparedStatement ps = kon.prepareStatement(sql);
                ps.setString(1, tf_nama.getText());
                ps.setInt(2, Integer.parseInt(tf_usia.getText()));
                ps.setString(3, tf_alamat.getText());
                ps.setInt(4, Integer.parseInt(tf_no.getText()));
                ps.executeUpdate();
                loadPasienNames();
                loadPasien();
                reset();
            }
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "data pasien berhasil diupdate", "info",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        if(tf_nama.getText().trim().isEmpty()||tf_usia.getText().trim().isEmpty()||tf_alamat.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "isi data yang sesuai ya, jangan ada yang kosong", "minta tolong",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            String sql = "UPDATE pasien SET nama = ?, usia = ?, alamat = ? WHERE no = ?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setString(1, tf_nama.getText());
            ps.setInt(2, Integer.parseInt(tf_usia.getText()));
            ps.setString(3, tf_alamat.getText());
            ps.setInt(4, Integer.parseInt(tf_no.getText()));
            ps.executeUpdate();
            loadPasienNames();
            loadFor();
            loadData();
            loadDataa();
            loadForr();
            loadPasien();
            reset();
        } catch (SQLException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "data pasien berhasil ditambah", "info",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void tbl_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pasienMouseClicked
        if (!AddId && tbl_pasien.getSelectedRow() != -1) {
            btn_baru.setEnabled(true);
            btn_baru.setText("GAJADI");
            btn_update.setEnabled(true);
            btn_hapus.setEnabled(true);
            tf_no.setEnabled(true);
            tf_nama.setEnabled(true);
            tf_usia.setEnabled(true);
            tf_alamat.setEnabled(true);
            rowId();
        }
    }//GEN-LAST:event_tbl_pasienMouseClicked

    private void btn_batallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batallActionPerformed
        btn_checkout.setEnabled(false);
        btn_batall.setEnabled(false);
        tbl_checkout.clearSelection();
    }//GEN-LAST:event_btn_batallActionPerformed

    private void btn_checkout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkout1ActionPerformed
        int confirm = JOptionPane.showOptionDialog(this,
            "anda yakin ingin keluar?",
            "mau konfirmasi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(confirm == JOptionPane.YES_OPTION){
            System.exit(0);
        }
        if(confirm == JOptionPane.NO_OPTION){
            return;
        }
    }//GEN-LAST:event_btn_checkout1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(lastProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lastProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lastProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lastProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lastProject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox amoxicillin;
    private javax.swing.JCheckBox antasida;
    private javax.swing.JCheckBox betadine;
    private javax.swing.JButton btn_baru;
    private javax.swing.JButton btn_baru_1;
    private javax.swing.JButton btn_baru_2;
    private javax.swing.JButton btn_baru_3;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_batall;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_checkout;
    private javax.swing.JButton btn_checkout1;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus_1;
    private javax.swing.JButton btn_hapus_2;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_simpan_1;
    private javax.swing.JButton btn_simpan_2;
    private javax.swing.JButton btn_simpan_3;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_update_1;
    private javax.swing.JButton btn_update_2;
    private javax.swing.JButton btn_updatee;
    private javax.swing.JComboBox<String> cbJenisPeriksa;
    private javax.swing.JComboBox<String> cmb_dokter;
    private javax.swing.JComboBox<String> cmb_np;
    private javax.swing.JComboBox<String> cmb_pasien;
    private javax.swing.JComboBox<String> cmb_spesialis;
    private javax.swing.JCheckBox ctm;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooser_checkout;
    private com.toedter.calendar.JDateChooser dcTanggalPeriksa;
    private javax.swing.JCheckBox ibuprofen;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JCheckBox paracetamol;
    private javax.swing.JTextArea ta_cetak;
    private javax.swing.JTable tbl_checkout;
    private javax.swing.JTable tbl_dokter;
    private javax.swing.JTable tbl_inap;
    private javax.swing.JTable tbl_pasien;
    private javax.swing.JTable tbl_pemeriksaan;
    private javax.swing.JTable tbl_resep;
    private javax.swing.JTable tbl_resep_1;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField tfNoAntrean;
    private javax.swing.JTextField tf_alamat;
    private javax.swing.JTextField tf_biaya;
    private javax.swing.JTextField tf_check;
    private javax.swing.JTextField tf_diagnosa;
    private javax.swing.JTextField tf_harga;
    private javax.swing.JTextField tf_harga_1;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_nama_1;
    private javax.swing.JTextField tf_no;
    private javax.swing.JTextField tf_nrp;
    private javax.swing.JTextField tf_nt;
    private javax.swing.JTextField tf_pem;
    private javax.swing.JTextField tf_total;
    private javax.swing.JTextField tf_trans;
    private javax.swing.JTextField tf_usia;
    private javax.swing.JCheckBox vitamin;
    // End of variables declaration//GEN-END:variables
}
