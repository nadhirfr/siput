/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
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
public class UndanganController implements Initializable {

    @FXML
     ScrollPane spp1;
    @FXML
    private JFXTextField tfPerihal;
    @FXML
    private JFXTextField tfLampiran;
    @FXML
    private JFXDatePicker dpTgl;
    @FXML
    private JFXTextField tfWaktu;
    @FXML
    private JFXTextField tfTempat;
    @FXML
    private JFXTextField tfAcara;
    @FXML
    private JFXButton btnCetak;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void CetakUndangan(ActionEvent event) throws IOException {

        try{
            Date date1 = Date.from(dpTgl.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Koneksi objKoneksi = new Koneksi();          
            //File report_file = new File("././laporan/siput-report.jasper");
            String namafile = "src/undangan/undangans.jasper";
            Map hash = new HashMap();
            hash.put("perihal", tfPerihal.getText());
            hash.put("lampiran", tfLampiran.getText());
            hash.put("tglh", date1);
            hash.put("wkt", tfWaktu.getText());
            hash.put("tmpt", tfTempat.getText());
            hash.put("acara", tfAcara.getText());

            System.out.println("kuda");
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
