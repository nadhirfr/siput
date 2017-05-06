/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import controller.MainMenuAController;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import object.Deposit;
import model.DepositModel;
import object.Iuran;
import model.IuranModel;
import object.IuranUser;
import model.IuranUserModel;
import object.User;
import model.UserModel;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class TambahUserController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfPassword;

    /**
     * Initializes the controller class.
     */
    UserModel userModel = new UserModel();
    DepositModel depositModel = new DepositModel();
    IuranModel iuranModel = new IuranModel();
    IuranUserModel iuranUserModel = new IuranUserModel();
    List<Iuran> listIuran;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @FXML
    private JFXComboBox<String> cbTipeUser;
    @FXML
    private TextField tfSaldo;
    @FXML
    private CheckListView<Iuran> lvIuran;
    @FXML
    private TextField tfKTP;
    @FXML
    private TextField tfAlamat;
    @FXML
    private JFXDatePicker dpTglLahir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfSaldo.setText("0");
        cbTipeUser.getItems().add("admin");
        cbTipeUser.getItems().add("operator");
        cbTipeUser.getItems().add("anggota");

        lvIuran.setItems(generateSelectedIuranData());
        lvIuran.setCellFactory(lv -> new CheckBoxListCell<Iuran>(lvIuran::getItemBooleanProperty) {
            @Override
            public void updateItem(Iuran item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getIuranNama());
            }
        });
    }

    private ObservableList<Iuran> generateSelectedIuranData() {
        listIuran = iuranModel.getAll();
        ObservableList<Iuran> allData = FXCollections.observableArrayList();
        allData.removeAll(allData);
        for (int i = 0; i < listIuran.size(); i++) {
            Iuran dataRow = listIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        userModel = new UserModel();
        Date date = Date.from(dpTglLahir.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) ;
        String tanggal = dateFormat.format(date);
        int insert_id = userModel.insert(new User(tfUserName.getText(), 
                            tfFullName.getText(), 
                            tfPassword.getText(), 
                            cbTipeUser.getValue(), 
                            tfKTP.getText(), 
                            tfAlamat.getText(), 
                            tanggal));
        if (insert_id > 0) {
            depositModel = new DepositModel();
            depositModel.insert(new Deposit(insert_id, Integer.valueOf(tfSaldo.getText())));

            for (Iuran iuran : lvIuran.getItems()) {
                if (lvIuran.getCheckModel().isChecked(iuran)) {
                    System.out.println("pertama true");
                    iuranUserModel.insert(new IuranUser(insert_id, iuran.getIuranId(), 0));
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Baru Dibuat!", ButtonType.OK);
            alert.showAndWait();
            MainMenuAController am = new MainMenuAController();
            if (alert.getResult() == ButtonType.OK) {
                //keneki diisii nek sukses
                clear();
            }
        }
    }

    private void clear() {
        tfUserName.setText("");
        tfFullName.setText("");
        tfPassword.setText("");
        tfAlamat.setText("");
        tfKTP.setText("");
        dpTglLahir.setValue(null);
        cbTipeUser.getItems().clear();
        tfSaldo.setText("0");
        lvIuran.getCheckModel().clearChecks();
    }

}
