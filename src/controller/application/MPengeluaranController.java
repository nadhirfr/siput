/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;
import object.Iuran;
import model.IuranModel;
import object.Pengeluaran;
import model.PengeluaranModel;
import object.Transaksi;
import model.TransaksiModel;
import object.User;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MPengeluaranController implements Initializable {

    @FXML
    private BorderPane bpPengeluaran;
    @FXML
    private Label lb_total_saldo;
    @FXML
    private JFXComboBox<Pengeluaran> cb_kategori;
    @FXML
    private JFXTextField tf_nominal;
    @FXML
    private JFXButton bt_simpan;
    @FXML
    private JFXButton bt_batal;

    TransaksiModel transaksiModel = new TransaksiModel();
    IuranModel iuranModel = new IuranModel();
    PengeluaranModel pengeluaranModel = new PengeluaranModel();
    User logedinUser;
    
    Pengeluaran selectedPengeluaran;
    
    
    List<Transaksi> listTransaksi;
    List<Iuran> listIuran;
    List<Pengeluaran> listPengeluaran;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_kategori.setItems(generateDataUserInMap());
        cb_kategori.setConverter(converter_cbKategori);
        cb_kategori.setCellFactory(callback_cbKategori);
//        cb_kategori.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pengeluaran>() {
//            @Override
//            public void changed(ObservableValue<? extends Pengeluaran> observable, Pengeluaran oldValue, Pengeluaran newValue) {
//                selectedPengeluaran = cb_kategori.getValue();
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });

        bt_simpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedPengeluaran = cb_kategori.getValue();
                int nominal = Integer.valueOf(tf_nominal.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(new Date());
                
                if (!tf_nominal.getText().equals("")) {
                    transaksiModel.insert(new Transaksi(nominal, 
                            logedinUser.getUser_id(),
                            null,
                            String.valueOf(selectedPengeluaran.getPengeluaran_id()), 
                            dateString, 
                            selectedPengeluaran.getPengeluaran_nama(), 
                            "pengeluaran"));
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data tersimpan !", ButtonType.OK);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.OK) {
                            //keneki diisii nek sukses
                            tf_nominal.setText("");
                        }
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tidak boleh kosong !", ButtonType.OK);
                    alert.showAndWait();
                }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }    
    
     public void setLoggedInUser(User logedinUser){
        this.logedinUser = logedinUser;
    }

    public void setTotalSaldo(String total) {
        this.lb_total_saldo.setText(total);
    }
    
    private ObservableList<Pengeluaran> generateDataUserInMap() {
        listPengeluaran = pengeluaranModel.getAll();
        ObservableList<Pengeluaran> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listPengeluaran.size(); i++) {
            Pengeluaran dataRow = listPengeluaran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private StringConverter<Pengeluaran> converter_cbKategori
            = new StringConverter<Pengeluaran>() {
        @Override
        public String toString(Pengeluaran object) {
            if (object != null) {
                return object.getPengeluaran_nama();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Pengeluaran fromString(String string) {
            Pengeluaran pengeluaran = new Pengeluaran();
            for (Pengeluaran _pengeluaran : cb_kategori.getItems()) {
                if (_pengeluaran.getPengeluaran_nama().equals(string)) {
                    pengeluaran = _pengeluaran;
                    break;
                }
            }
            return pengeluaran;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private Callback<ListView<Pengeluaran>, ListCell<Pengeluaran>> callback_cbKategori
            = new Callback<ListView<Pengeluaran>, ListCell<Pengeluaran>>() {
        @Override
        public ListCell<Pengeluaran> call(ListView<Pengeluaran> param) {
            final ListCell<Pengeluaran> cell = new ListCell<Pengeluaran>() {
                {
                    super.setPrefWidth(100);
                }

                @Override
                public void updateItem(Pengeluaran item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        if(item.getPengeluaran_jenis_id()== 1 && item.getPengeluaran_kategori_id()==1){
                            //wajib - rutin
                            setTextFill(Color.DARKORANGE);
                        } else if(item.getPengeluaran_jenis_id() == 1 && item.getPengeluaran_kategori_id()==2){
                            //wajib - insidental
                            setTextFill(Color.DARKORCHID);
                        } else if(item.getPengeluaran_jenis_id() == 2 && item.getPengeluaran_kategori_id()==1){
                            //tidak wajib - rutin
                            setTextFill(Color.DARKRED);
                        } else if(item.getPengeluaran_jenis_id() == 2 && item.getPengeluaran_kategori_id()==2){
                            //tidak wajib - insidental
                            setTextFill(Color.DARKSEAGREEN);
                        }
                        setText(item.getPengeluaran_nama());
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };

    
}
