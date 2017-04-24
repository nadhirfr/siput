/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class LaporanController implements Initializable {
 @FXML
    private TableView<?> tbl_trans;

    @FXML
    private TableColumn<?, ?> clm_tgl;

    @FXML
    private TableColumn<?, ?> clm_nama;

    @FXML
    private TableColumn<?, ?> clm_nom;

    @FXML
    private TableColumn<?, ?> clm_tipe;

    @FXML
    private JFXButton btn_cetak;
    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void CetakLaporan(ActionEvent event) throws IOException {

        try{
            Koneksi objKoneksi = new Koneksi();          
            //File report_file = new File("././laporan/siput-report.jasper");
            String namafile = "src/laporan/siput-report.jasper";
            HashMap hash = new HashMap();
            File report_file = new File(namafile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, objKoneksi.connection());
            JasperViewer.viewReport(jasperPrint,false);
            System.out.println("cetak");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
