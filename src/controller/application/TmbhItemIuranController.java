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
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;
import model.Iuran;
import model.IuranModel;
import model.IuranUser;
import model.IuranUserModel;
import model.JenisIuran;
import model.JenisIuranModel;
import model.KategoriIuran;
import model.KategoriIuranModel;
import model.User;
import model.UserModel;



/**
 * FXML Controller class
 *
 * @author rheza
 */
public class TmbhItemIuranController implements Initializable {
    
    @FXML
    private CheckComboBox<User> ccbUser;
    @FXML
    private JFXListView<Iuran> lvIuran;
    @FXML
    private ComboBox<KategoriIuran> cbKategori;
    @FXML
    private ComboBox<JenisIuran> cbJenis;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfKeterangan;
    @FXML
    private TextField tfNominal;

    UserModel userModel = new UserModel();
    IuranModel iuranModel = new IuranModel();
    IuranUserModel iuranUserModel = new IuranUserModel();
    KategoriIuranModel kategoriIuranModel = new KategoriIuranModel();
    JenisIuranModel jenisIuranModel = new JenisIuranModel();
    ObservableList<User> selectedUser;
    List<KategoriIuran> listKategoriIuran;
    List<JenisIuran> listJenisIuran;
    List<Iuran> listIuran;
    @FXML
    private Button btSimpan;
    @FXML
    private Button btBatal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //nambah data ke CheckComboBox USer
        userModel  = new UserModel();
        List<User> listUser = userModel.getAll();
        for (User user : listUser) {
            ccbUser.getItems().add(user);
        }
        ccbUser.setConverter(converter_ccbUser);
        selectedUser = ccbUser.getCheckModel().getCheckedItems();
        
        //nambah data ke combobox kategori
        cbKategori.setItems(cbKategoriData());
        cbKategori.setConverter(converter_cbKategori);
        
        //nambah data ke combobox jenis
        cbJenis.setItems(cbJenisData());
        cbJenis.setConverter(converter_cbJenis);
        
        //nambah data ke listview iuran
        lvIuran.setItems(lvIuranData());
        lvIuran.setCellFactory(callback_lvIuran);
        
        //action button save
        btSimpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int inserted_id = iuranModel.insert(new Iuran(cbJenis.getValue().getIuranJenisId(), 
                        cbKategori.getValue().getIuranKategoriId(), 
                        tfNama.getText(), 
                        Integer.valueOf(tfNominal.getText())));
                
                for (User user : selectedUser) {
                    iuranUserModel.insert(new IuranUser(user.getUser_id(), inserted_id,  0));
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Disimpan! \n Silahkan klik refresh", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }    
    
    private ObservableList<KategoriIuran> cbKategoriData(){
        listKategoriIuran = kategoriIuranModel.getAll();
        ObservableList<KategoriIuran> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listKategoriIuran.size(); i++) {
            KategoriIuran dataRow = listKategoriIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private ObservableList<JenisIuran> cbJenisData(){
        listJenisIuran = jenisIuranModel.getAll();
        ObservableList<JenisIuran> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listJenisIuran.size(); i++) {
            JenisIuran dataRow = listJenisIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private ObservableList<Iuran> lvIuranData(){
        listIuran = iuranModel.getAll();
        ObservableList<Iuran> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listIuran.size(); i++) {
            Iuran dataRow = listIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    
    private StringConverter<KategoriIuran> converter_cbKategori = 
            new StringConverter<KategoriIuran>() {
        @Override
        public String toString(KategoriIuran object) {
            if (object != null) {
                return object.getIuranKategoriNama();
            } else {
                return null;
            }
        }

        @Override
        public KategoriIuran fromString(String string) {
            KategoriIuran kategoriIuran = new KategoriIuran();
            for (KategoriIuran _kategoriIuran : cbKategori.getItems()) {
                if (_kategoriIuran.getIuranKategoriNama().equals(string)) {
                    kategoriIuran = _kategoriIuran;
                    break;
                }
            }
            return kategoriIuran;
        }
    };
    
    private StringConverter<JenisIuran> converter_cbJenis = 
            new StringConverter<JenisIuran>() {
        @Override
        public String toString(JenisIuran object) {
            if (object != null) {
                return object.getIuranJenisNama();
            } else {
                return null;
            }
        }

        @Override
        public JenisIuran fromString(String string) {
            JenisIuran jenisIuran = new JenisIuran();
            for (JenisIuran _jenisIuran : cbJenis.getItems()) {
                if (_jenisIuran.getIuranJenisNama().equals(string)) {
                    jenisIuran = _jenisIuran;
                    break;
                }
            }
            return jenisIuran;
        }
    };
    
    private StringConverter<User> converter_ccbUser
            = new StringConverter<User>() {
        @Override
        public String toString(User object) {
            if (object != null) {
                return object.getUser_displayname();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public User fromString(String string) {
            User user = new User();
            for (User _user : ccbUser.getItems()) {
                if (_user.getUser_displayname().equals(string)) {
                    user = _user;
                    break;
                }
            }
            return user;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
   
    private final Callback<ListView<Iuran>, ListCell<Iuran>> callback_lvIuran
            = (ListView<Iuran> param) -> {
                final ListCell<Iuran> cell = new ListCell<Iuran>() {
                    {
                        super.setPrefWidth(100);
                    }
                    
                    @Override
                    public void updateItem(Iuran item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getIuranNama());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
    };
        
}
