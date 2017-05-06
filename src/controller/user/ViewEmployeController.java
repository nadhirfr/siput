/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import object.Deposit;
import model.DepositModel;
import object.Iuran;
import model.IuranModel;
import object.IuranUser;
import model.IuranUserModel;
import object.User;
import model.UserModel;
//<<<<<<< HEAD
import javafx.scene.control.cell.CheckBoxListCell;

//import List.ListEmployee;
//
//import DAL.Users;
//import controller.RegistrationController;
//import dataBase.DBProperties;
//import java.util.Optional;
//=======
import org.controlsfx.control.CheckListView;
//>>>>>>> b985927debe4a2e6039505e2afcafa29ac9aa7c2

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class ViewEmployeController implements Initializable {
    
    private String userId;
    private String name;
    private String id;
    private String creatorId;
    private String creatorName;

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfTipeUser;
    @FXML
    private TextField tfSaldo;
    @FXML
    private CheckListView<Iuran> lvSelectedIuran;
    @FXML
    private TextField tfKTP;
    @FXML
    private TextField tfFAlamat;
    @FXML
    private JFXDatePicker dpTglLahir;
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
                    Date date = Date.from(dpTglLahir.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) ;
                    String tanggal = dateFormat.format(date);
                    userModel.update(new User(user.getUser_id(), 
                            tfUserName.getText(), 
                            tfFullName.getText(), 
                            tfPassword.getText(), 
                            tfTipeUser.getText(), 
                            tfKTP.getText(), 
                            tfFAlamat.getText(), 
                            tanggal));
                    depositModel.update(new Deposit(deposit.getDepositId(),
                            deposit.getUserId(),
                            Integer.valueOf(tfSaldo.getText())));
                    for (Iuran iuran : lvSelectedIuran.getItems()){
                        int iuranUserID = iuranUserModel.getByUserAndIuran(user, iuran) == null ? 0:
                                iuranUserModel.getByUserAndIuran(user, iuran).getIuranUserId();
                        if(iuranUserID == 0 &&
                                lvSelectedIuran.getCheckModel().isChecked(iuran)){
                            System.out.println("pertama true");
                            iuranUserModel.insert(new IuranUser(user.getUser_id(), iuran.getIuranId(), 0));
                        } else if(iuranUserID !=0 &&
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
            try {
                user = userModel.getUser(employeeList.get(Column0MapKey).toString());
                //usersGetway.selectedView(users);
                lvSelectedIuran.getCheckModel().clearChecks();
                for (Iuran iuran : lvSelectedIuran.getItems()) {
                    int iuran_id = iuranUserModel.getByUserAndIuran(user, iuran) == null ? 0 :
                            iuranUserModel.getByUserAndIuran(user, iuran).getIuranId();
                    if(iuran_id == iuran.getIuranId()){
                        lvSelectedIuran.getCheckModel().check(iuran);
                    } else{
                        lvSelectedIuran.getCheckModel().clearCheck(iuran);
                    }
                }
                id = String.valueOf(user.getUser_id());
                tfUserName.setText(user.getUser_username());
                tfFAlamat.setText(user.getUser_alamat());
                tfKTP.setText(user.getUser_ktp());
                dpTglLahir.setValue(dateFormat.parse(user.getUser_tgl_lahir()).toInstant().
                                    atZone(ZoneId.systemDefault()).toLocalDate());
                tfFullName.setText(user.getUser_displayname());
                tfPassword.setText(user.getUser_password());
                tfTipeUser.setText(user.getUser_tipe());
                tfSaldo.setText(depositModel.getByUser(user) == null? "" :
                        String.valueOf(depositModel.getByUser(user).getDepositJumlah()));
            } catch (ParseException ex) {
                Logger.getLogger(ViewEmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
   
    private void clearAll() {
        tfUserName.clear();
        tfFullName.clear();
    }
}
