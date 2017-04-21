/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.MainMenuAController;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    public AnchorPane APMasuk;
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
    User selectedUser, logedinUser;
    Iuran selectedIuran;

    List<User> listUser;
    List<Deposit> listDeposit;
    List<Iuran> listIuran;
    List<IuranUser> listIuranUser;
    List<Transaksi> listTransaksi;
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
                    listIuranUser = iuranUserModel.getAll();
                    for (int i = 0; i < listIuranUser.size(); i++) {
                        if (listIuranUser.get(i).isIuranUserStatus() == 0
                                && listIuranUser.get(i).getUserId() == cb_namaAnggota.getValue().getUser_id()) {
                            cb_Pembayaran.getItems().add(iuranModel.get(String.valueOf(listIuranUser.get(i).getIuranId())));
                        }
                    }

                    cb_Pembayaran.setConverter(converter_cbIuran);
                    cb_Pembayaran.setCellFactory(callback_cbIuran);
                    cb_Pembayaran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Iuran>() {
                        @Override
                        public void changed(ObservableValue<? extends Iuran> observable, Iuran oldValue, Iuran newValue) {
                            selectedUser = cb_namaAnggota.getSelectionModel().getSelectedItem();
//                            Iuran selectedIuran = cb_Pembayaran.getValue();
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            if (newValue != null) {
//                                if (iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).isIuranUserStatus()) {
//                                    lbStatus.setText("Lunas");
//                                } else {
                                listTransaksi = transaksiModel.getAll();
                                int total = 0;

                                //ngecek setiap transaksi, jika ditabel transaksi ada pembayaran yg telah dibayar maka nominal dikurangi dgn yg telah dibayar
                                for (Transaksi transaksi : listTransaksi) {
//                                        System.out.println(transaksi.getUserId()+":"+selectedUser.getUser_id());
                                    System.out.println(transaksi.getUserId() + ":" + iuranUserModel.getByUserAndIuran(cb_namaAnggota.getValue(), cb_Pembayaran.getValue()).getUserId());
                                    if (transaksi.getUserId() == iuranUserModel.getByUserAndIuran(selectedUser, newValue).getUserId()
                                            && Integer.valueOf(transaksi.getIuranId()) == iuranUserModel.getByUserAndIuran(selectedUser, newValue).getIuranId()) {
                                        total = total + transaksi.getTransaksiNominal();
//                                            pr biikin yg iuran rutin
                                    }
                                }

//                                    System.out.println("selected user :"+selectedUser.getUser_id());
//                                    System.out.println("selected iuran :"+cb_Pembayaran.getValue().getIuranId());
                                System.out.println(iuranUserModel.getByUserAndIuran(selectedUser, cb_Pembayaran.getValue()).getUserId());
//                                    System.out.println("total : " +total);
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
                int status;
                if (nom_pembayaranP.getText().equals("")) {
                    nom_pembayaranP.setText("0");
                }

                if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()));

                    // besar dari nominal yang harus dibayarkan maka sisanya akan masuk ke deposit
                    if (kurang < 0) {
                        status = 0;
                        saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                                .getDepositJumlah() + (Math.abs(kurang));
                        System.out.println("saldo nambah: " + saldo);
                    } else {
                        status = kurang;
                        saldo = depositModel.getByUser(cb_namaAnggota.getSelectionModel().getSelectedItem())
                                .getDepositJumlah();
                    }
                } else {
                    status = kurang;
                    kurang = Integer.valueOf(nom_jns_pembayaranP.getText())
                            - (Integer.valueOf(nom_pembayaranP.getText()) + Integer.valueOf(nom_saldo_DP.getText()));
                }
                System.out.println("Kurang = " + kurang);
                lbSisaSaldo.setText(String.valueOf(saldo));
                lbStatus.setText(String.valueOf(status));
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
                if (nom_saldo_DP.getText().equals("")) {
                    nom_saldo_DP.setText("0");
                }

                if (Integer.valueOf(nom_pembayaranP.getText()) < Integer.valueOf(nom_jns_pembayaranP.getText())) {
                    if (nom_saldo_DP.getText().equals("") || nom_saldo_DP.getText().equals("0")) {
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
                selectedUser = cb_namaAnggota.getValue();
                selectedIuran = cb_Pembayaran.getValue();
                Deposit deposit = depositModel.getByUser(selectedUser);
                IuranUser iuran_user = iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String toString = dateFormat.format(new Date());

                int total = 0;
                int jumlahTransaksiIuran = 0;

                if (!nom_jns_pembayaranP.getText().equals("0")) {
                    if (Integer.valueOf(nom_pembayaranP.getText()) > Integer.valueOf(nom_jns_pembayaranP.getText())) {
                        jumlahTransaksiIuran = Integer.valueOf(nom_jns_pembayaranP.getText());
                        System.out.println("true lebih besar");
                    } else {
                        System.out.println("true lebih besar");
                        jumlahTransaksiIuran = Integer.valueOf(nom_pembayaranP.getText())
                                + Integer.valueOf(nom_saldo_DP.getText());
                    }
                    //insert ke transaksi bahwa ada iuran baru
                    transaksiModel.insert(new Transaksi(jumlahTransaksiIuran,
                            selectedUser.getUser_id(),
                            String.valueOf(selectedIuran.getIuranId()),
                            null,
                            toString,
                            selectedIuran.getIuranNama(),
                            "iuran"));
                    //mengubah jumlah deposit
                    depositModel.update(new Deposit(deposit.getDepositId(),
                            selectedUser.getUser_id(),
                            Integer.valueOf(lbSisaSaldo.getText())));
                    transaksiModel = new TransaksiModel();
                    listTransaksi = transaksiModel.getAll();
                    //menghitung total transaksi yang dibayarkan (dicek pada tabel transaksi)
                    for (Transaksi transaksi : listTransaksi) {
                        System.out.println(iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId());
                        if (transaksi.getUserId() == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId()
                                && Integer.valueOf(transaksi.getIuranId()) == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getIuranId()) {
                            total = total + transaksi.getTransaksiNominal();
                        }
                    }
                    System.out.println("Total : " + total);
                    //ketika jumlah total lebih dari sama dengan nominal iuran yang dibayarkan maka mengubah statusnya menjadi 1 (artinya sudah lunas)
                    System.out.println("Sebelum : " + iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                    if (total >= selectedIuran.getIuranNominal()) {
                        System.out.println(iuran_user.getIuranId());
                        iuran_user.setIuranUserStatus(1);
                        iuranUserModel.update(iuran_user);
                    }
                    
                    if (iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus() == 1) {
                        Alert alert = new Alert(AlertType.INFORMATION, "Sudah Lunas", ButtonType.OK);
                        alert.showAndWait();
                        MainMenuAController am = new MainMenuAController();
                        if (alert.getResult() == ButtonType.OK) {
                            
                                //keneki diisii nek sukses
                                batalBtnOnClick(event);
                                

                        }
                    }else{
                        Alert alert = new Alert(AlertType.INFORMATION, "Data tersimpan !", ButtonType.OK);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.OK) {
                            //keneki diisii nek sukses
                            batalBtnOnClick(event);
                    }}

                    System.out.println("Sesudah : " + iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                } else {
                    transaksiModel = new TransaksiModel();
                    listTransaksi = transaksiModel.getAll();
                    //menghitung total transaksi yang dibayarkan (dicek pada tabel transaksi)
                    for (Transaksi transaksi : listTransaksi) {
                        System.out.println(iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId());
                        if (transaksi.getUserId() == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getUserId()
                                && Integer.valueOf(transaksi.getIuranId()) == iuranUserModel.getByUserAndIuran(selectedUser, selectedIuran).getIuranId()) {
                            total = total + transaksi.getTransaksiNominal();
                        }
                    }
                    System.out.println("Total : " + total);
                    System.out.println("Sebelum : " + iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                    if (total >= selectedIuran.getIuranNominal()) {
                        System.out.println("iuran user id :" + iuran_user.getIuranId());
                        iuran_user.setIuranUserStatus(1);
                        iuranUserModel.update(iuran_user);
                    }
                    System.out.println("Sesudah : " + iuranUserModel.get(String.valueOf(iuran_user.getIuranId())).isIuranUserStatus());
                }
                   batalBtnOnClick(event);
            }

        });
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
                        if(item.getIuranJenisId() == 1 && item.getIuranKategoriId()==1){
                            //wajib - rutin
                            setTextFill(Color.DARKORANGE);
                        } else if(item.getIuranJenisId() == 1 && item.getIuranKategoriId()==2){
                            //wajib - insidental
                            setTextFill(Color.DARKORCHID);
                        } else if(item.getIuranJenisId() == 2 && item.getIuranKategoriId()==1){
                            //tidak wajib - rutin
                            setTextFill(Color.DARKRED);
                        } else if(item.getIuranJenisId() == 2 && item.getIuranKategoriId()==2){
                            //tidak wajib - insidental
                            setTextFill(Color.DARKSEAGREEN);
                        }
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
        listUser = userModel.getAll();
        ObservableList<User> allData = FXCollections.observableArrayList();
        for (int i = 0; i < listUser.size(); i++) {
            User dataRow = listUser.get(i);
            allData.add(dataRow);
        }
        return allData;
    }
    
    @FXML
    private void batalBtnOnClick(ActionEvent event){
        cb_namaAnggota.valueProperty().set(null);
        nom_pembayaranP.clear();
        cb_Pembayaran.valueProperty().set(null);
        nom_saldo_DP.clear();
        cb_Pembayaran.valueProperty().setValue(null);
        //cb_Pembayaran.getItems().remove(saldo);
    }
    
    public void setLoggedInUser(User logedinUser){
        this.logedinUser = logedinUser;
    }
    
    @FXML
    private void refreshACT(ActionEvent event) throws IOException{
        MainMenuAController am = new MainMenuAController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/mPemasukan.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        //am.acContent.getChildren().clear();
        APMasuk.getChildren().add(acPane);
    }
}
