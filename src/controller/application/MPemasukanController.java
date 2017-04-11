/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.implementDeposit;
import dao.implementIuran;
import dao.implementIuranUser;
import dao.implementUser;
import factory.DAOFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.model_Deposit;
import model.model_Iuran;
import model.model_IuranUser;
import model.model_User;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MPemasukanController implements Initializable {

    @FXML
    public BorderPane bpPemasukan;
    @FXML
    private JFXTextField nom_jns_pembayaranP;
    @FXML
    private JFXTextField nom_saldo_DP;
    @FXML
    private JFXButton btnSimpanP;
    @FXML
    private JFXButton btnBatalP;
    @FXML
    private JFXTextField nom_pembayaranP;
    @FXML
    private JFXComboBox<model_User> cb_namaAnggota;

    DAOFactory daoMySQL = DAOFactory.getFactory(DAOFactory.MySQL);
    implementUser dAOUser = daoMySQL.getUserMySQL();
    implementDeposit deposit = daoMySQL.getDepositMySQL();
    implementIuran iuran = daoMySQL.getIuranMySQL();
    implementIuranUser iuranUser = daoMySQL.getIuranUserMySQL();
    List<model_User> listUser = dAOUser.getAll();
    List<model_Deposit> listDeposit = deposit.getAll();
    List<model_Iuran> listIuran = iuran.getAll();
    List<model_IuranUser> listIuranUser = iuranUser.getAll();
    @FXML
    private Label lbSisaSaldo;
    @FXML
    private JFXComboBox<model_Iuran> cb_Pembayaran;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_namaAnggota.setItems(generateDataUserInMap());
        cb_namaAnggota.setConverter(converter_cbAnggota);
        cb_namaAnggota.setCellFactory(callback_cbAnggota);
        cb_namaAnggota.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<model_User>() {
            @Override
            public void changed(ObservableValue<? extends model_User> observable, model_User oldValue, model_User newValue) {
                if(newValue != null){
                    lbSisaSaldo.setText("Sisa saldo : "+String.valueOf(deposit.getByUser(newValue).getDepositJumlah()));
                    //cb_Pembayaran.setItems(generateDataIuranInMap());
                    System.out.println("Selected : "+newValue.getUser_displayname());
                    cb_Pembayaran.getItems().clear();
                    for (int i = 0; i < listIuranUser.size();i++){
                        if (listIuranUser.get(i).getUserId() == newValue.getUser_id()){
                            cb_Pembayaran.getItems().add(listIuran.get(listIuranUser.get(i).getIuranId()));
                            cb_Pembayaran.setPromptText("Pilih Pembayaran");
                        } else {
                            cb_Pembayaran.setPromptText("Pilih Pembayaran");
                        }
                    }
                  
                    cb_Pembayaran.setConverter(converter_cbIuran);
                    cb_Pembayaran.setCellFactory(callback_cbIuran);
                    cb_Pembayaran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<model_Iuran>() {
                        @Override
                        public void changed(ObservableValue<? extends model_Iuran> observable, model_Iuran oldValue, model_Iuran newValue) {
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                if(newValue != null){
                                    nom_jns_pembayaranP.setText(String.valueOf(newValue.getIuranNominal()));
                                }
                        }
                    });
                }
            }
        });
        cb_namaAnggota.setEditable(true);
   }
    
    private StringConverter<model_User> converter_cbAnggota = 
            new StringConverter<model_User>() {
            @Override
            public String toString(model_User object) {
                if (object != null) {
                    return object.getUser_displayname(); 
                } else{
                    return null;
                }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public model_User fromString(String string) {
                model_User user = new model_User();
                for(model_User _user : cb_namaAnggota.getItems()){
                    if(_user.getUser_displayname().equals(string)){
                        user = _user;
                        break;
                    }
                }
                return user;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    
    private StringConverter<model_Iuran> converter_cbIuran = 
            new StringConverter<model_Iuran>() {
            @Override
            public String toString(model_Iuran object) {
                if (object != null) {
                    return object.getIuranNama(); 
                } else{
                    return null;
                }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public model_Iuran fromString(String string) {
                model_Iuran  iuran= new model_Iuran();
                for(model_Iuran _iuran : cb_Pembayaran.getItems()){
                    if(_iuran.getIuranNama().equals(string)){
                        iuran = _iuran;
                        break;
                    }
                }
                return iuran;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    
    private Callback<ListView<model_User>, ListCell<model_User>> callback_cbAnggota = 
            new Callback<ListView<model_User>, ListCell<model_User>>() {
          @Override
          public ListCell<model_User> call(ListView<model_User> param) {
            final ListCell<model_User> cell = new ListCell<model_User>() {
              {
                super.setPrefWidth(100);
              }

              @Override
              public void updateItem(model_User item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    if(item.getUser_tipe().equals("admin")) setTextFill(Color.GREEN);
                    if(item.getUser_tipe().equals("operator")) setTextFill(Color.YELLOW);
                  setText(item.getUser_displayname());
                  
                } else {
                  setText(null);
                }
              }
            };
            return cell;
          }
        };
    
    private Callback<ListView<model_Iuran>, ListCell<model_Iuran>> callback_cbIuran = 
            new Callback<ListView<model_Iuran>, ListCell<model_Iuran>>() {
                        @Override
                        public ListCell<model_Iuran> call(ListView<model_Iuran> param) {
                            final ListCell<model_Iuran> cell = new ListCell<model_Iuran>() {
                            {
                              super.setPrefWidth(100);
                            }

                            @Override
                            public void updateItem(model_Iuran item, boolean empty) {
                              super.updateItem(item, empty);
                              if (item != null) {
                                  setText(item.getIuranNama());
                              } else {
                                setText(null);
                              }
                            }
                          };
                          return cell;
                        }
                    };
    
    private ObservableList<model_User> generateDataUserInMap() {
        ObservableList<model_User> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listUser.size(); i++) {
            model_User dataRow = listUser.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    //gajadi dipake
    private ObservableList<model_Iuran> generateDataIuranInMap() {
        ObservableList<model_Iuran> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listIuran.size(); i++) {
            model_Iuran dataRow = listIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
}
                
