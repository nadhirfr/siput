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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class PemasukanController implements Initializable {

    @FXML
    public BorderPane bpStore;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private ToggleButton btnStock;
    @FXML
    private ToggleButton btnSupplyer;
    @FXML
    private ToggleButton btnBrands;
    @FXML
    private ToggleButton btnCatagory;
    @FXML
    private ToggleButton btnUnit;
    @FXML
    private ToggleButton btnRma;
    @FXML
    private ToggleButton btnRepoerts;
    @FXML
    private Label lblHeader;
    @FXML
    private StackPane spMainContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void btnStockOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSupplyerOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBrandsOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCatagoryOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUnitOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRmaOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }
    
}
