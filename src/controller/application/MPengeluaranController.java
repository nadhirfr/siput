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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Iuran;
import model.IuranModel;
import model.Transaksi;
import model.TransaksiModel;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MPengeluaranController implements Initializable {

    @FXML
    private BorderPane bpPengeluaran;
    @FXML
    private Label lb_total_saldo;
    @FXML
    private JFXComboBox<?> cb_kategori;
    @FXML
    private JFXTextField tf_nominal;
    @FXML
    private JFXButton bt_simpan;
    @FXML
    private JFXButton bt_batal;

    TransaksiModel transaksiModel = new TransaksiModel();
    IuranModel iuranModel = new IuranModel();
    
    List<Transaksi> listTransaksi = transaksiModel.getAll();
    List<Iuran> listIuran = iuranModel.getAll();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
