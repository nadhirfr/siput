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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import controller.application.TmbhItemIuranController;
import controller.application.TmbhItemPengeluaranController;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class TmbhItemController implements Initializable {

    @FXML
    private AnchorPane acMainItem;
    @FXML
    private JFXButton btn_iuran;
    @FXML
    private JFXButton btn_pengeluaran;
    @FXML
    private StackPane spMainItem;
    @FXML
    private Label lblView;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void btn_iuranOnAction(ActionEvent event) throws IOException {
        //lblPathInfo.setText("Sells");
        lblView.setText("Tambah Item Iuran");
        TmbhItemIuranController tmbhitem = new TmbhItemIuranController();
        //userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Pengaturan/TmbhItemIuran.fxml").openStream());
        //media.setId(userId);
        TmbhItemIuranController controller = fXMLLoader.getController();
        //controller.setNameMedia(nameMedia);
        //controller.viewDetails();
//        controller.viewDetails();
        spMainItem.getChildren().clear();
        spMainItem.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    public void btn_pengeluaranOnAction(ActionEvent event) throws IOException {
        //lblPathInfo.setText("Sells");
        lblView.setText("Tambah Item Pengeluaran");
        //userNameMedia media = new userNameMedia();
//        FXMLLoader fXMLLoader = new FXMLLoader();
//        fXMLLoader.load(getClass().getResource("/view/Pengaturan/TmbhItemPengeluaran.fxml").openStream());
        //media.setId(userId);
        
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/Pengaturan/TmbhItemPengeluaran.fxml"));
        fXMLLoader.load();
        TmbhItemPengeluaranController controller = fXMLLoader.getController();
//        controller.notify();
        //controller.setNameMedia(nameMedia);
        //controller.viewDetails();
//        controller.viewDetails();
        spMainItem.getChildren().clear();
        spMainItem.getChildren().add(fXMLLoader.getRoot());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
