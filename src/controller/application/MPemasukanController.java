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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @FXML
    private Label lbSisaSaldo;
    @FXML
    private JFXComboBox<model_Iuran> cb_Pembayaran;
    @FXML
    private Label lbStatus;

    DAOFactory daoMySQL = DAOFactory.getFactory(DAOFactory.MySQL);
    implementUser dAOUser = daoMySQL.getUserMySQL();
    implementDeposit deposit = daoMySQL.getDepositMySQL();
    implementIuran iuran = daoMySQL.getIuranMySQL();
    implementIuranUser iuranUser = daoMySQL.getIuranUserMySQL();
    List<model_User> listUser = dAOUser.getAll();
    List<model_Deposit> listDeposit = deposit.getAll();
    List<model_Iuran> listIuran = iuran.getAll();
    List<model_IuranUser> listIuranUser = iuranUser.getAll();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //nom_saldo_DP.setText("0");
        cb_namaAnggota.setItems(generateDataUserInMap());
        cb_namaAnggota.setConverter(converter_cbAnggota);
        cb_namaAnggota.setCellFactory(callback_cbAnggota);
        cb_namaAnggota.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<model_User>() {
            @Override
            public void changed(ObservableValue<? extends model_User> observable, model_User oldValue, model_User newValue) {
                if (newValue != null) {
                    lbSisaSaldo.setText(String.valueOf(deposit.getByUser(newValue).getDepositJumlah()));
                    //cb_Pembayaran.setItems(generateDataIuranInMap());
                    System.out.println("Selected : " + newValue.getUser_displayname());
                    cb_Pembayaran.getItems().clear();
                    for (int i = 0; i < listIuranUser.size(); i++) {
                        if (listIuranUser.get(i).getUserId() == newValue.getUser_id()) {
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
                            if (newValue != null) {
                                nom_jns_pembayaranP.setText(String.valueOf(newValue.getIuranNominal()));
                                nom_pembayaranP.setText("0");
                                nom_saldo_DP.setText("0");
                            }
                        }
                    });
                }
            }
        });
        cb_namaAnggota.setEditable(true);

        nom_pembayaranP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int kurang;
                if (nom_pembayaranP.getText().equals("")) {
                    nom_pembayaranP.setText("0");
                }

                if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
//                    lbStatus.setText(String.valueOf(Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_pembayaranP.getText())));
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()));
                } else {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()) + Integer.valueOf(nom_saldo_DP.getText()));
                }

                lbStatus.setText(String.valueOf(kurang));
            }
        });
        nom_saldo_DP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int saldo = 0;
                int kurang = 0;
                if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
                    nom_saldo_DP.setText("0");
                    saldo = deposit.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah();
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_pembayaranP.getText());
//                
                } else if (nom_pembayaranP.getText().equals("0")
                        || nom_pembayaranP.getText().equals("")) {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_saldo_DP.getText());
                    saldo = deposit.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() - Integer.valueOf(nom_saldo_DP.getText());

                } else {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - (Integer.valueOf(nom_pembayaranP.getText()) + Integer.valueOf(newValue));
                    saldo = deposit.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() - Integer.valueOf(newValue);
                }
                lbSisaSaldo.setText(String.valueOf(saldo));
                lbStatus.setText(String.valueOf(kurang));
            }
        });
        
        btnSimpanP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Simpan diklik");
            }
        });
    }

    private StringConverter<model_User> converter_cbAnggota
            = new StringConverter<model_User>() {
        @Override
        public String toString(model_User object) {
            if (object != null) {
                return object.getUser_displayname();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public model_User fromString(String string) {
            model_User user = new model_User();
            for (model_User _user : cb_namaAnggota.getItems()) {
                if (_user.getUser_displayname().equals(string)) {
                    user = _user;
                    break;
                }
            }
            return user;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private StringConverter<model_Iuran> converter_cbIuran
            = new StringConverter<model_Iuran>() {
        @Override
        public String toString(model_Iuran object) {
            if (object != null) {
                return object.getIuranNama();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public model_Iuran fromString(String string) {
            model_Iuran iuran = new model_Iuran();
            for (model_Iuran _iuran : cb_Pembayaran.getItems()) {
                if (_iuran.getIuranNama().equals(string)) {
                    iuran = _iuran;
                    break;
                }
            }
            return iuran;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private Callback<ListView<model_User>, ListCell<model_User>> callback_cbAnggota
            = new Callback<ListView<model_User>, ListCell<model_User>>() {
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
                        if (item.getUser_tipe().equals("admin")) {
                            setTextFill(Color.GREEN);
                        }
                        if (item.getUser_tipe().equals("operator")) {
                            setTextFill(Color.YELLOW);
                        }
                        setText(item.getUser_displayname());

                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };

    private Callback<ListView<model_Iuran>, ListCell<model_Iuran>> callback_cbIuran
            = new Callback<ListView<model_Iuran>, ListCell<model_Iuran>>() {
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
