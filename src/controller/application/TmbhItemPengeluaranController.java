/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class TmbhItemPengeluaranController implements Initializable {

    @FXML
    private JFXListView<?> List_View_User;
    @FXML
    private ComboBox<?> Cb_Kat;
    @FXML
    private ComboBox<?> Cb_jenis;
    @FXML
    private TextField Txt_Nama;
    @FXML
    private TextField Txt_Ket;
    @FXML
    private TextField Txt_nominal;
    @FXML
    private Button btn_simpan;
    @FXML
    private Button btn_btl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
