/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import com.jfoenix.controls.JFXComboBox;
import controller.MainMenuAController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Deposit;
import model.DepositModel;
import model.User;
import model.UserModel;

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
    UserModel userModel;
    DepositModel depositModel;
    @FXML
    private ImageView imvUsrImg;
    @FXML
    private JFXComboBox<String> cbTipeUser;
    @FXML
    private TextField tfSaldo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfSaldo.setText("0");
        cbTipeUser.getItems().add("admin");
        cbTipeUser.getItems().add("operator");
        cbTipeUser.getItems().add("anggota");
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        userModel = new UserModel();
        int insert_id = userModel.insert(new User(tfUserName.getText(),
                tfFullName.getText(),
                tfPassword.getText(),
                cbTipeUser.getValue()));
        if (insert_id > 0) {
            depositModel = new DepositModel();
            depositModel.insert(new Deposit(insert_id, Integer.valueOf(tfSaldo.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Baru Dibuat", ButtonType.OK);
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
        cbTipeUser.getItems().clear();
        tfSaldo.setText("0");
    }

}
