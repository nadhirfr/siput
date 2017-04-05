/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.beranda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.Koneksi;


/**
 * FXML Controller class
 *
 * @author rheza
 */
public class berandaController implements Initializable {

    @FXML
    private Label lblDisplayName;
    Koneksi dbCon = new Koneksi();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Label lblOrgName1;
    @FXML
    private Label lbBerandaTotalUser;
    @FXML
    private Label lbBerandaTotalPemasukan;
    @FXML
    private Label lbBerandaTotalPengeluaran;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUserName(String displayname) {
        this.lblDisplayName.setText(displayname);
    }

    public Label getLbBerandaTotalUser() {
        return lbBerandaTotalUser;
    }

    public void setLbBerandaTotalUser(int totaluser) {
        this.lbBerandaTotalUser.setText(String.valueOf(totaluser));
    }
    
}
