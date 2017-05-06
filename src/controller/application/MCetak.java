/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import controller.application.LaporanController;
import controller.application.UndanganController;
/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MCetak implements Initializable {

    @FXML
    private AnchorPane acMainItem;
    @FXML
    private Label lblView;
    @FXML
    private JFXButton btn_laporan;
    @FXML
    private JFXButton btn_undangan;
    @FXML
    private StackPane spMainItem;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void btn_laporan(ActionEvent event) throws IOException {
        lblView.setText("Laporan Transaksi");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Cetak/Laporan.fxml").openStream());
        LaporanController lpController = fXMLLoader.getController();
        lpController.spp1.getStyleClass().clear();
        spMainItem.getChildren().clear();
        spMainItem.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    public void btn_undangan(ActionEvent event) throws IOException {
        lblView.setText("Cetak Undangan");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Cetak/Undangan.fxml").openStream());
        UndanganController uc = fXMLLoader.getController();
        uc.spp1.getStyleClass().clear();
        spMainItem.getChildren().clear();
        spMainItem.getChildren().add(fXMLLoader.getRoot());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
