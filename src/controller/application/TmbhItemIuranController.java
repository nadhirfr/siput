/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.CheckComboBox;



/**
 * FXML Controller class
 *
 * @author rheza
 */
public class TmbhItemIuranController implements Initializable {
    
    @FXML
    private CheckComboBox ccbUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                // create the data to show in the CheckComboBox 
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
        strings.add("Item " + i);
        }
 
        // Create the CheckComboBox with the data 
        final CheckComboBox<String> ccbUser = new CheckComboBox<String>(strings);
 
        // and listen to the relevant events (e.g. when the selected indices or 
        // selected items change).
        ccbUser.getItems().addListener(new ListChangeListener<String>() {
        public void onChanged(ListChangeListener.Change<? extends String> c) {
         System.out.println(ccbUser.getItems());
     }
    });
    }    
    
//    public class CheckComboBox<T> extends Control {
//         // create the data to show in the CheckComboBox 
//        final ObservableList<String> strings = FXCollections.observableArrayList();
//        for (int i = 0; i <= 100; i++) {
//        strings.add("Item " + i);
//        }
// 
//        // Create the CheckComboBox with the data 
//        final CheckComboBox<String> checkComboBox = new CheckComboBox<String>(strings);
// 
//        // and listen to the relevant events (e.g. when the selected indices or 
//        // selected items change).
//        checkComboBox.getCheckModel().getSelectedItems().addListener(new ListChangeListener<String>() {
//        public void onChanged(ListChangeListener.Change<? extends String> c) {
//         System.out.println(checkComboBox.getCheckModel().getSelectedItems());
//     }
//    });
// }
        
}
