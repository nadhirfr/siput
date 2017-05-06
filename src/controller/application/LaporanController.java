/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import object.Transaksi;
import com.mysql.jdbc.Connection;
import static controller.user.ViewEmployeController.Column0MapKey;
import static controller.user.ViewEmployeController.Column1MapKey;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.MapValueFactory;
import model.TransaksiModel;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class LaporanController implements Initializable {
 @FXML
    private TableView<Map> tbl_trans;

    @FXML
    private TableColumn<Map, ?> clm_tgl;

    @FXML
    private TableColumn<Map, ?> clm_nama;

    @FXML
    private TableColumn<Map, ?> clm_nom;

    @FXML
    private TableColumn<Map, ?> clm_tipe;

    @FXML
    private JFXButton btn_cetak;
    
    @FXML
    private JFXDatePicker cbtgl1;
    
    @FXML
    private JFXDatePicker cbtgl12;
    
    @FXML
    public ScrollPane spp1;
    /**
     * Initializes the controller class.
     */
    
    public static String Column0MapKey = "tgl";
    public static String Column1MapKey = "nama";
    public static String Column2MapKey = "nominal";
    public static String Column3MapKey = "tipe";
    
    TransaksiModel transaksiModel = new TransaksiModel();
    List<Transaksi> listTransaksi;
    

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
        fillTable();
    }    
    
    
    private void fillTable(){
        tbl_trans.getItems().clear();
        
        clm_tgl.setCellValueFactory(new MapValueFactory(Column0MapKey));
//        clm_tgl.setMaxWidth(0);
        clm_nama.setCellValueFactory(new MapValueFactory(Column1MapKey));
//        clm_nama.setMinWidth(160);
        clm_nom.setCellValueFactory(new MapValueFactory(Column2MapKey));
//        clm_nom.setMinWidth(160);
        clm_tipe.setCellValueFactory(new MapValueFactory(Column3MapKey));
//        clm_tipe.setMinWidth(160);
        
        tbl_trans.setItems(generateDataInMap());
        tbl_trans.setEditable(true);
        tbl_trans.getSelectionModel().setCellSelectionEnabled(true);
//        tblEmoyeeList.getColumns().setAll(clmEmployeName);
//        tblEmoyeeList.refresh();
    }
    
    private ObservableList<Map> generateDataInMap() {
        listTransaksi = transaksiModel.getAll();
        ObservableList<Map> allData = FXCollections.observableArrayList();
        allData.removeAll(allData);
        for (int i = 0; i < listTransaksi.size(); i++) {
            Map<String, String> dataRow = new HashMap<>();
            String value0 = listTransaksi.get(i).getTransaksiDate();
            String value1 = listTransaksi.get(i).getTransaksiNama();
            String value2 = String.valueOf(listTransaksi.get(i).getTransaksiNominal());
            String value3 = listTransaksi.get(i).getTransaksiTipe();
            dataRow.put(Column0MapKey, value0);
            dataRow.put(Column1MapKey, value1);
            dataRow.put(Column2MapKey, value2);
            dataRow.put(Column3MapKey, value3);
            allData.add(dataRow);
        }
        return allData;
    }
}
