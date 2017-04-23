/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

//import Getway.UsersGetway;
//import dataBase.DBConnection;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

//import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
//import media.userNameMedia;
//import custom.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Deposit;
import model.DepositModel;
import model.Iuran;
import model.IuranModel;
import model.IuranUser;
import model.IuranUserModel;
import model.User;
import model.UserModel;
import org.controlsfx.control.CheckListView;

//import List.ListEmployee;
//
//import DAL.Users;
//import controller.RegistrationController;
//import dataBase.DBProperties;
//import java.util.Optional;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ViewEmployeController implements Initializable {

    private File file;
    private BufferedImage bufferedImage;
    private String imagePath;
    private Image image;
    private Blob blobImage;

//    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    private String userId;
    private String name;
    private String id;
//    private userNameMedia nameMedia;
    private String creatorId;
    private String creatorName;

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfCreatedBy;
//    @FXML
//    public Button btnClrFulNametf;
//    @FXML
//    public Button btnClrEmailtf;
//    @FXML
//    public Button btnClrPhonetf;
//    @FXML
//    public Button btnClrSalarytf;
    @FXML
    private TableView<Map> tblEmoyeeList;
    @FXML
    private TableColumn<Map, ?> clmEmployeId;
    @FXML
    private TableColumn<Map, ?> clmEmployeName;

    Image usrImg = new Image("/img/siput-logo.png");
    
    public static String Column1MapKey = "nama";
    public static String Column0MapKey = "id";


    UserModel userModel = new UserModel();
    DepositModel depositModel = new DepositModel();
    IuranModel iuranModel = new IuranModel();
    IuranUserModel iuranUserModel = new IuranUserModel();
    List<Iuran> listIuran;
    List<IuranUser> listIuranUser;
    List<User> listUser;
    
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfTipeUser;
    @FXML
    private TextField tfSaldo;
    @FXML
    private CheckListView<Iuran> lvSelectedIuran;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
        lvSelectedIuran.setItems(generateSelectedIuranData());
//        lvSelectedIuran.getCheckModel().checkAll();
        lvSelectedIuran.setCellFactory(lv -> new CheckBoxListCell<Iuran>(lvSelectedIuran::getItemBooleanProperty) {
            @Override
            public void updateItem(Iuran item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getIuranNama());
            }
            
            
        });
        fillTable();
        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Delete clicked");
                Map employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
                if (employeeList != null) {
                    User user = userModel.getUser(employeeList.get(Column0MapKey).toString());
                    userModel.delete(String.valueOf(user.getUser_id()));
                    System.out.println("Deleted user id: "+String.valueOf(user.getUser_id()));
                    tblEmoyeeList.getSelectionModel().selectFirst();
                    //ini harusnya direfresh tampilan tapi belum bisa
                    fillTable();
                }
            }
        });
        
        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Update clicked");
                Map employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
                if (employeeList != null) {
                    User user = userModel.getUser(employeeList.get(Column0MapKey).toString());
                    Deposit deposit = depositModel.getByUser(user);
                    userModel.update(new User(user.getUser_id(), 
                            tfUserName.getText(), 
                            tfFullName.getText(), 
                            tfPassword.getText(), 
                            tfTipeUser.getText()));
                    depositModel.update(new Deposit(deposit.getDepositId(),
                            deposit.getUserId(),
                            Integer.valueOf(tfSaldo.getText())));
                    for (Iuran iuran : lvSelectedIuran.getItems()){
                        if(iuranUserModel.getByUserAndIuran(user, iuran).getIuranUserId() == 0 &&
                                lvSelectedIuran.getCheckModel().isChecked(iuran)){
                            System.out.println("pertama true");
                            iuranUserModel.insert(new IuranUser(user.getUser_id(), iuran.getIuranId(), 0));
                        } else if(iuranUserModel.getByUserAndIuran(user, iuran).getIuranUserId() !=0 &&
                                !lvSelectedIuran.getCheckModel().isChecked(iuran)){
                            System.out.println("kedua true");
                            iuranUserModel.delete(String.valueOf(iuranUserModel.getByUserAndIuran(user, iuran).getIuranUserId()));
                        }
                    }
                    System.out.println("Updated user id: "+String.valueOf(user.getUser_id()));
                    System.out.println("Updated deposit id: "+String.valueOf(deposit.getDepositId()));
                    tblEmoyeeList.getSelectionModel().selectFirst();
                    //ini harusnya direfresh tampilan tapi belum bisa
                    tblEmoyeeList.refresh();
                    fillTable();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data telah di update!\nSilahkan klik refresh untuk memperbarui data!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });
    }
    
    private void fillTable(){
        tblEmoyeeList.getItems().clear();
        
        clmEmployeId.setCellValueFactory(new MapValueFactory(Column0MapKey));
        clmEmployeId.setMaxWidth(0);
        clmEmployeName.setCellValueFactory(new MapValueFactory(Column1MapKey));
        clmEmployeName.setMinWidth(160);
        
        tblEmoyeeList.getItems().removeAll(generateDataInMap());
        tblEmoyeeList.setItems(generateDataInMap());
        tblEmoyeeList.setEditable(true);
        tblEmoyeeList.getSelectionModel().setCellSelectionEnabled(true);
        tblEmoyeeList.getColumns().setAll(clmEmployeName);
        tblEmoyeeList.refresh();
    }
    
    private ObservableList<Map> generateDataInMap() {
        listUser = userModel.getAll();
        ObservableList<Map> allData = FXCollections.observableArrayList();
        allData.removeAll(allData);
        for (int i = 0; i < listUser.size(); i++) {
            Map<String, String> dataRow = new HashMap<>();
            String value0 = String.valueOf(listUser.get(i).getUser_id());
            String value1 = listUser.get(i).getUser_displayname();
            dataRow.put(Column1MapKey, value1);
            dataRow.put(Column0MapKey, value0);
            allData.add(dataRow);
        }
        return allData;
    }
    
    private ObservableList<Iuran> generateSelectedIuranData(){
        listIuran = iuranModel.getAll();
        ObservableList<Iuran> allData = FXCollections.observableArrayList();
        allData.removeAll(allData);
        for (int i = 0; i < listIuran.size(); i++) {
            Iuran dataRow = listIuran.get(i);
            allData.add(dataRow);
        }
        return allData;
    }

    @FXML
    private void tfSearchOnAction(ActionEvent event) {

    }


    @FXML
    private void tblViewOnClick(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }

    @FXML
    public void tblEmloyeOnClick(Event event) {
        setselectedView();
    }


 


    public void setselectedView() {
        clearAll();
        Map employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
        User user = null;
        if (employeeList != null) {
            user = userModel.getUser(employeeList.get(Column0MapKey).toString());
            //usersGetway.selectedView(users);
            lvSelectedIuran.getCheckModel().clearChecks();
            for (Iuran iuran : lvSelectedIuran.getItems()) {
                if(iuranUserModel.getByUserAndIuran(user, iuran).getIuranId() == iuran.getIuranId()){
                    lvSelectedIuran.getCheckModel().check(iuran);
                } else{
                    lvSelectedIuran.getCheckModel().clearCheck(iuran);
                }
            }
            id = String.valueOf(user.getUser_id());
            tfUserName.setText(user.getUser_username());
            tfFullName.setText(user.getUser_displayname());
            tfPassword.setText(user.getUser_password());
            tfTipeUser.setText(user.getUser_tipe());
            tfSaldo.setText(String.valueOf(depositModel.getByUser(user).getDepositJumlah()));

        }
    }

    private void clearAll() {
        tfUserName.clear();
        tfFullName.clear();
        tfCreatedBy.clear();
    }
}
