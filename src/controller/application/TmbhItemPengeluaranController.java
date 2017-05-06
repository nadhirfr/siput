/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import object.Pengeluaran;
import object.PengeluaranJenis;
import model.PengeluaranJenisModel;
import object.PengeluaranKategori;
import model.PengeluaranKategoriModel;
import model.PengeluaranModel;

/**
 * FXML Controller class
 *
 * @author fachrul
 */
public class TmbhItemPengeluaranController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PengeluaranModel pengeluaranModel = new PengeluaranModel();
    PengeluaranKategoriModel pengeluaranKategoriModel = new PengeluaranKategoriModel();
    PengeluaranJenisModel pengeluaranJenisModel = new PengeluaranJenisModel();
    
    List<PengeluaranKategori> listPengeluaranKategori;
    List<PengeluaranJenis> listPengeluaranJenis;
    @FXML
    private JFXListView<Pengeluaran> lvPengeluaran;
    @FXML
    private ComboBox<PengeluaranKategori> cbKategori;
    @FXML
    private ComboBox<PengeluaranJenis> cbJenis;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfKeterangan;
    @FXML
    private Button btSimpan;
    @FXML
    private Button btHapus;
    @FXML
    private Button btUpdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lvPengeluaran.setItems(dataListView());
        lvPengeluaran.setCellFactory(callback_ListView);
        cbKategori.setItems(generatePengeluaranKategoriInMap());
//        cbKategori.setCellFactory(callback_cbKategori);
        cbKategori.setConverter(converter_cbKategori);
        cbJenis.setItems(generatePengeluaranJenisInMap());
//        cbJenis.setCellFactory(callback_cbKategori);
        cbJenis.setConverter(converter_cbJenis);
//        cbJenis.getItems().add("kuda");

        lvPengeluaran.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                 if (event.getCode().equals(KeyCode.UP)) {
                    setselectedView();
                } else if (event.getCode().equals(KeyCode.DOWN)) {
                    setselectedView();
                }
            }
        });
        lvPengeluaran.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setselectedView();
            }
        });
        
        btSimpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pengeluaranModel.insert(new Pengeluaran(cbJenis.getValue().getPengeluaran_jenis_id(), 
                        cbKategori.getValue().getPengeluaran_kategori_id(), 
                        tfNama.getText(), 
                        tfKeterangan.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data disimpan !", ButtonType.OK);
                alert.showAndWait();
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        btHapus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pengeluaranModel.delete(String.valueOf(lvPengeluaran.getSelectionModel().getSelectedItem().getPengeluaran_id()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Dihapus! \n Silahkan klik refresh", ButtonType.OK);
                alert.showAndWait();
            }
        });
        btUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pengeluaran pengeluaranSelected = lvPengeluaran.getSelectionModel().getSelectedItem();
                if(pengeluaranSelected!=null){
                    pengeluaranModel.update(new Pengeluaran(pengeluaranSelected.getPengeluaran_id(),
                            cbJenis.getSelectionModel().getSelectedItem().getPengeluaran_jenis_id(), 
                            cbKategori.getSelectionModel().getSelectedItem().getPengeluaran_kategori_id(), 
                            tfNama.getText(), 
                            tfKeterangan.getText()));
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data telah di update!\nSilahkan klik refresh untuk memperbarui data!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });
    }

    private final Callback<ListView<Pengeluaran>, ListCell<Pengeluaran>> callback_ListView
            = (ListView<Pengeluaran> param) -> {
                final ListCell<Pengeluaran> cell = new ListCell<Pengeluaran>() {
                    {
                        super.setPrefWidth(100);
                    }
                    
                    @Override
                    public void updateItem(Pengeluaran item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getPengeluaran_nama());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
    };
    
    
    private ObservableList<Pengeluaran> dataListView(){
        pengeluaranModel = new PengeluaranModel();
        ObservableList<Pengeluaran> allData = FXCollections.observableArrayList();
        List<Pengeluaran> pengeluarans = pengeluaranModel.getAll();
        for(Pengeluaran pengeluaran : pengeluarans){
            allData.add(pengeluaran);
            System.out.println(pengeluaran.getPengeluaran_nama());
        }
        
        return allData;
    }
    
    private ObservableList<PengeluaranKategori> generatePengeluaranKategoriInMap() {
        listPengeluaranKategori = pengeluaranKategoriModel.getAll();
        ObservableList<PengeluaranKategori> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listPengeluaranKategori.size(); i++) {
            PengeluaranKategori dataRow = listPengeluaranKategori.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private ObservableList<PengeluaranJenis> generatePengeluaranJenisInMap() {
        listPengeluaranJenis = pengeluaranJenisModel.getAll();
        ObservableList<PengeluaranJenis> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listPengeluaranJenis.size(); i++) {
            PengeluaranJenis dataRow = listPengeluaranJenis.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private StringConverter<PengeluaranKategori> converter_cbKategori
            = new StringConverter<PengeluaranKategori>() {
        @Override
        public String toString(PengeluaranKategori object) {
            if (object != null) {
                return object.getPengeluaran_kategori_nama();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public PengeluaranKategori fromString(String string) {
            PengeluaranKategori pengeluaranKategori = new PengeluaranKategori();
            for (PengeluaranKategori _pengeluaranKategori : cbKategori.getItems()) {
                if (_pengeluaranKategori.getPengeluaran_kategori_nama().equals(string)) {
                    pengeluaranKategori = _pengeluaranKategori;
                    break;
                }
            }
            return pengeluaranKategori;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
   
    private StringConverter<PengeluaranJenis> converter_cbJenis
            = new StringConverter<PengeluaranJenis>() {
        @Override
        public String toString(PengeluaranJenis object) {
            if (object != null) {
                return object.getPengeluaran_nama();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public PengeluaranJenis fromString(String string) {
            PengeluaranJenis pengeluaranJenis = new PengeluaranJenis();
            for (PengeluaranJenis _pengeluaranJenis : cbJenis.getItems()) {
                if (_pengeluaranJenis.getPengeluaran_nama().equals(string)) {
                    pengeluaranJenis = _pengeluaranJenis;
                    break;
                }
            }
            return pengeluaranJenis;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
   
    public void setselectedView() {
        clearAll();
        Pengeluaran pengeluaranSelected = lvPengeluaran.getSelectionModel().getSelectedItem();
        if (pengeluaranSelected != null) {
            cbJenis.getSelectionModel().select(pengeluaranJenisModel.get(String.valueOf(pengeluaranSelected.getPengeluaran_jenis_id())));
            cbKategori.getSelectionModel().select(pengeluaranKategoriModel.get(String.valueOf(pengeluaranSelected.getPengeluaran_kategori_id())));
            tfNama.setText(pengeluaranSelected.getPengeluaran_nama());
            tfKeterangan.setText(String.valueOf(pengeluaranSelected.getPengeluaran_keterangan()));
            //ini buat ngeset checkcombobox berdasarkan yg diselect
           

        }
    }

    private void clearAll() {
        tfNama.setText("");
        tfKeterangan.setText("");
        cbJenis.getSelectionModel().clearSelection();
        cbKategori.getSelectionModel().clearSelection();
    }
    
}

