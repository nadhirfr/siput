/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class PengaturanController implements Initializable {

    @FXML
    public BorderPane bpSettings;
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miOrganize;
    @FXML
    private MenuItem miBackup;
    @FXML
    private Label lblCurrentStatus;
    @FXML
    private StackPane spSettingContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void miMyASccountOnClick(ActionEvent event) {
    }

    @FXML
    private void miOrganizeOnAction(ActionEvent event) {
    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {
    }
    
}
