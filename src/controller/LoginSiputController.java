/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import koneksi.Koneksi;
import object.User;
import model.UserModel;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class LoginSiputController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label statustext;

    @FXML
    private JFXTextField usernametext;

    @FXML
    private JFXPasswordField passtext;

    @FXML
    private JFXButton btlbtn;
    UserModel userModel = new UserModel();

    public void Login(ActionEvent event) throws Exception {
        int loggedIn_user_id = Koneksi.isLogin(usernametext.getText(), passtext.getText());
        User loggedIn_user = userModel.getUser(Integer.toString(loggedIn_user_id));
        if (loggedIn_user_id != 0 && !loggedIn_user.getUser_tipe().equals("anggota")) {
            statustext.setText("Login Sukses");
            //agar jendela login tertutup setelah berhasil login
            ((Node) (event.getSource())).getScene().getWindow().hide();

            //memanggil jendela menu admins
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainMenuA.fxml"));
            loader.load();
            MainMenuAController aController = loader.getController();
            aController.setUserName(loggedIn_user);
            Parent parent = loader.getRoot();
            aController.btnHomeOnClick(event);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Menu Admin");
            primaryStage.show();
        } else {
            statustext.setText("akun atau sandi salah");
        }
    }

    @FXML
    private void tutupBtn(ActionEvent event) {
        Stage stage = (Stage) btlbtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
