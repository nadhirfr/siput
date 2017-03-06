/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
   
   public void Login (ActionEvent event){
       if (usernametext.getText().equals("user") && passtext.getText().equals("pass")) {
            statustext.setText("Login Sukses");
       } else {
            statustext.setText("akun atau sandi salah");
       }
       }
   
    @FXML
    private void tutupBtn(ActionEvent event){
        Stage stage = (Stage) btlbtn.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
