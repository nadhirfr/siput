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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Deposit;
import model.DepositModel;
import model.Iuran;
import model.IuranModel;
import model.IuranUser;
import model.IuranUserModel;
import model.Transaksi;
import model.TransaksiModel;
import model.User;
import model.UserModel;

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
    private JFXComboBox<User> cb_namaAnggota;
    @FXML
    private Label lbSisaSaldo;
    @FXML
    private JFXComboBox<Iuran> cb_Pembayaran;
    @FXML
    private Label lbStatus;

    UserModel userModel = new UserModel();
    DepositModel depositModel = new DepositModel();
    IuranModel iuranModel = new IuranModel();
    IuranUserModel iuranUserModel = new IuranUserModel();
    TransaksiModel transaksiModel = new TransaksiModel();

    List<User> listUser = userModel.getAll();
    List<Deposit> listDeposit = depositModel.getAll();
    List<Iuran> listIuran = iuranModel.getAll();
    List<IuranUser> listIuranUser = iuranUserModel.getAll();
    List<Transaksi> listTransaksi = transaksiModel.getAll();
     int saldo = 0;
     int kurang = 0;

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
        cb_namaAnggota.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    lbSisaSaldo.setText(String.valueOf(depositModel.getByUser(newValue).getDepositJumlah()));
                    //cb_Pembayaran.setItems(generateDataIuranInMap());
                    System.out.println("Selected : " + newValue.getUser_displayname());
                    cb_Pembayaran.getItems().clear();
                    for (int i = 0; i < listIuranUser.size(); i++) {
                        if (listIuranUser.get(i).getUserId() == cb_namaAnggota.getValue().getUser_id()) {
                            cb_Pembayaran.getItems().add(iuranModel.get(String.valueOf(listIuranUser.get(i).getIuranId())));
                            cb_Pembayaran.setPromptText("Pilih Pembayaran");
                        } else {
                            cb_Pembayaran.setPromptText("Pilih Pembayaran");
                        }
                    }

                    cb_Pembayaran.setConverter(converter_cbIuran);
                    cb_Pembayaran.setCellFactory(callback_cbIuran);
                    cb_Pembayaran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Iuran>() {
                        @Override
                        public void changed(ObservableValue<? extends Iuran> observable, Iuran oldValue, Iuran newValue) {
                            User selectedUser = cb_namaAnggota.getSelectionModel().getSelectedItem();
//                            Iuran selectedIuran = cb_Pembayaran.getValue();
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            if (newValue != null) {
//                                if (iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).isIuranUserStatus()) {
//                                    lbStatus.setText("Lunas");
//                                } else {
                                    listTransaksi = transaksiModel.getAll();
                                    int total = 0;
                                    for (Transaksi transaksi : listTransaksi) {
//                                        System.out.println(transaksi.getUserId()+":"+selectedUser.getUser_id());
                                        System.out.println(transaksi.getUserId()+":"+  iuranUserModel.getByUserAndIuran(cb_namaAnggota.getValue(), cb_Pembayaran.getValue()).getUserId());
                                        if (transaksi.getUserId() == iuranUserModel.getByUserAndIuran(selectedUser, newValue).getUserId()
                                                && Integer.valueOf(transaksi.getIuranId()) == iuranUserModel.getByUserAndIuran(selectedUser, newValue).getIuranId()) {
                                            total = total + transaksi.getTransaksiNominal();
                                        }
                                    }
                                    
                                    System.out.println("selected user :"+selectedUser.getUser_id());
                                    System.out.println("selected iuran :"+cb_Pembayaran.getValue().getIuranId());
                                    System.out.println(iuranUserModel.getByUserAndIuran(selectedUser, cb_Pembayaran.getValue()).getUserId());
                                    System.out.println("total : " +total);
//                                int transaksi_total
                                    nom_jns_pembayaranP.setText(String.valueOf(newValue.getIuranNominal() - total));
                                    nom_pembayaranP.setText("0");
                                    nom_saldo_DP.setText("0");
//                                }
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
//                int kurang;
                if (nom_pembayaranP.getText().equals("")) {
                    nom_pembayaranP.setText("0");
                }
                
                if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
//                    lbStatus.setText(String.valueOf(Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_pembayaranP.getText())));
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()));
                    //jika jumlah yg harus dibayar dikurangi jumlah yang dimasukkan pada kolom nominal pemasukkan lebih
                    // besar dari nominal yang harus dibayarkan maka sisanya akan masuk ke deposit
                    if(kurang < 0){
                        saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() + (Math.abs(kurang)-Integer.valueOf(nom_jns_pembayaranP.getText()));
                        System.out.println("saldo : "+saldo);
                    }
                } else {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()) + Integer.valueOf(nom_saldo_DP.getText()));
                    if(kurang < 0){
                        saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() + (kurang-Integer.valueOf(nom_jns_pembayaranP.getText()));
                    }
                }
                System.out.println("Kurang = "+kurang);
                lbSisaSaldo.setText(String.valueOf(saldo));
                lbStatus.setText(String.valueOf(kurang));
            }
        });
        nom_pembayaranP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (nom_pembayaranP.getText().equals("0")) {
                    nom_pembayaranP.requestFocus();
                    nom_pembayaranP.positionCaret(0);
                    nom_pembayaranP.selectNextWord();
                }
            }
        });
        nom_saldo_DP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
                if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
                    nom_saldo_DP.setText("0");
                    saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah();
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_pembayaranP.getText());
//                
                } else if (nom_pembayaranP.getText().equals("0")
                        || nom_pembayaranP.getText().equals("")) {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - Integer.valueOf(nom_saldo_DP.getText());
                    saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() - Integer.valueOf(nom_saldo_DP.getText());

                } else {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText()) - (Integer.valueOf(nom_pembayaranP.getText()) + Integer.valueOf(newValue));
                    saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                            .getDepositJumlah() - Integer.valueOf(newValue);
                }
                lbSisaSaldo.setText(String.valueOf(saldo));
                lbStatus.setText(String.valueOf(kurang));
            }
        });
        nom_saldo_DP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (nom_saldo_DP.getText().equals("0")) {

                    nom_saldo_DP.requestFocus(); // get focus first
                    nom_saldo_DP.positionCaret(0);
                    nom_saldo_DP.selectNextWord();
                }
            }
        });
        
        btnSimpanP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User selectedUser = cb_namaAnggota.getValue();
                Iuran selectedIuran = cb_Pembayaran.getValue();
                Deposit deposit = depositModel.getByUser(selectedUser);
                IuranUser iuran_user = iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String toString = dateFormat.format(new Date());

                int total = 0;
                for (Transaksi transaksi : listTransaksi) {
                    System.out.println(iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId());
                        if (transaksi.getUserId() == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId()
                                && Integer.valueOf(transaksi.getIuranId()) == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getIuranId()) {
                            total = total + transaksi.getTransaksiNominal();
                        }
                }
                    
                if (!nom_jns_pembayaranP.getText().equals("0")) {
                    int jumlahTransaksi = Integer.valueOf(nom_pembayaranP.getText())
                            + Integer.valueOf(nom_saldo_DP.getText());
                    transaksiModel.insert(new Transaksi(jumlahTransaksi,
                            selectedUser.getUser_id(),
                            String.valueOf(selectedIuran.getIuranId()),
                            null,
                            toString,
                            selectedIuran.getIuranNama(),
                            "iuran"));
                    depositModel.update(new Deposit(deposit.getDepositId(),
                            selectedUser.getUser_id(),
                            Integer.valueOf(lbSisaSaldo.getText())));
                    listTransaksi = transaksiModel.getAll();
                    
                    //System.out.println(total);
                    if (total >= selectedIuran.getIuranNominal()) {
                        System.out.println(iuran_user.getIuranId());
                        iuran_user.setIuranUserStatus(1);
                        iuranUserModel.update(iuran_user);
                    }
                } else {
                    System.out.println("Sebelum : "+iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                    if (total >= selectedIuran.getIuranNominal()) {
                        System.out.println("iuran user id :"+iuran_user.getIuranId());
                        iuran_user.setIuranUserStatus(1);
                        iuranUserModel.update(iuran_user);
                    }
                    System.out.println("Sesudah : "+iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                }

            }
        });
    }

    private void validationCheck(){
        
    }
    private StringConverter<User> converter_cbAnggota
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
            for (User _user : cb_namaAnggota.getItems()) {
                if (_user.getUser_displayname().equals(string)) {
                    user = _user;
                    break;
                }
            }
            return user;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private StringConverter<Iuran> converter_cbIuran
            = new StringConverter<Iuran>() {
        @Override
        public String toString(Iuran object) {
            if (object != null) {
                return object.getIuranNama();
            } else {
                return null;
            }
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Iuran fromString(String string) {
            Iuran iuran = new Iuran();
            for (Iuran _iuran : cb_Pembayaran.getItems()) {
                if (_iuran.getIuranNama().equals(string)) {
                    iuran = _iuran;
                    break;
                }
            }
            return iuran;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private Callback<ListView<User>, ListCell<User>> callback_cbAnggota
            = new Callback<ListView<User>, ListCell<User>>() {
        @Override
        public ListCell<User> call(ListView<User> param) {
            final ListCell<User> cell = new ListCell<User>() {
                {
                    super.setPrefWidth(100);
                }

                @Override
                public void updateItem(User item, boolean empty) {
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

    private Callback<ListView<Iuran>, ListCell<Iuran>> callback_cbIuran
            = new Callback<ListView<Iuran>, ListCell<Iuran>>() {
        @Override
        public ListCell<Iuran> call(ListView<Iuran> param) {
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
        }
    };

    private ObservableList<User> generateDataUserInMap() {
        ObservableList<User> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listUser.size(); i++) {
            User dataRow = listUser.get(i);
            allData.add(dataRow);
        }
        return allData;
    }

}
