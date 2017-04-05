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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MPemasukanController implements Initializable {

    @FXML
    public BorderPane bpPemasukan;
    @FXML
    private JFXComboBox nm_anggotaP;
    @FXML
    private JFXComboBox jns_pembayaranP;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
