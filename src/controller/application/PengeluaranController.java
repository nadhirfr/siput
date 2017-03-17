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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class PengeluaranController implements Initializable {

    @FXML
    public AnchorPane acMainSells;
    @FXML
    private Label lblPathInfo;
    @FXML
    private ToggleButton tbtnSell;
    @FXML
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnReports;
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
    public void tbtnSellOnAction(ActionEvent event) {
    }

    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) {
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event) {
    }
    
}
