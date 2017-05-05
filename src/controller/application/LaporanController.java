/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.mysql.jdbc.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.DatePicker;

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
    
    @FXML
    private JFXDatePicker cbtgl1;
    
    @FXML
    private JFXDatePicker cbtgl12;
    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void CetakLaporan(ActionEvent event) throws IOException {

        try{
            Date date1 = Date.from(cbtgl1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date2 = Date.from(cbtgl12.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Koneksi objKoneksi = new Koneksi();          
            //File report_file = new File("././laporan/siput-report.jasper");
            String namafile = "src/laporan/siput-report.jasper";
            Map hash = new HashMap();
            hash.put("tgl1", date1);
            hash.put("tgl2", date2);

            System.out.println("kuda");
            File report_file = new File(namafile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, objKoneksi.connection());
            JasperViewer.viewReport(jasperPrint,false);
            System.out.println("cetak");
            System.out.println(cbtgl1.getValue());
            System.out.println(cbtgl12.getValue());
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
