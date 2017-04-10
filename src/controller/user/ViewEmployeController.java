/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

//import Getway.UsersGetway;
//import dataBase.DBConnection;

import dao.implementUser;
import factory.DAOFactory;
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
import javafx.scene.shape.Rectangle;
//import media.userNameMedia;
//import custom.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import model.model_User;

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
    @FXML
    public Button btnClrFulNametf;
    @FXML
    public Button btnClrEmailtf;
    @FXML
    public Button btnClrPhonetf;
    @FXML
    public Button btnClrSalarytf;
    @FXML
    private TableView<Map> tblEmoyeeList;
    @FXML
    private TableColumn<Map, ?> clmEmployeId;
    @FXML
    private TableColumn<Map, ?> clmEmployeName;

    Image usrImg = new Image("/img/siput-logo.png");
    
    public static String Column1MapKey = "nama";
    public static String Column0MapKey = "id";


    DAOFactory user = DAOFactory.getFactory(DAOFactory.MySQL);
    implementUser dAOUser = user.getUserMySQL();
    List<model_User> listUser = dAOUser.getAll();
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfTipeUser;
    @FXML
    private TextField tfSaldo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO

        clmEmployeId.setCellValueFactory(new MapValueFactory(Column0MapKey));
        clmEmployeId.setMaxWidth(0);
        clmEmployeName.setCellValueFactory(new MapValueFactory(Column1MapKey));
        clmEmployeName.setMinWidth(160);
        
        tblEmoyeeList.setItems(generateDataInMap());
        tblEmoyeeList.setEditable(true);
        tblEmoyeeList.getSelectionModel().setCellSelectionEnabled(true);
        tblEmoyeeList.getColumns().setAll(clmEmployeName);
        
    }
    
    private ObservableList<Map> generateDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
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

    @FXML
    private void tfSearchOnAction(ActionEvent event) {

    }

    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
//            recUsrImage.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }

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
        model_User user = new model_User();
        if (employeeList != null) {
            user = dAOUser.getUser(employeeList.get(Column0MapKey).toString());
            //usersGetway.selectedView(users);
            id = String.valueOf(user.getUser_id());
            tfUserName.setText(user.getUser_username());
            tfFullName.setText(user.getUser_displayname());
            tfPassword.setText(user.getUser_password());
            tfTipeUser.setText(user.getUser_tipe());
//            tfSalary.setText(users.salary);
//            tfDateofJoin.setText(users.date);
//            creatorId = users.creatorId;
//            taAddress.setText(users.address);
//            image = users.image;
//            recUsrImage.setFill(new ImagePattern(image));
//            sql.creatorNameFindar(creatorId, lblCreator);
//            tfCreatedBy.setText(lblCreator.getText());
//            if (users.status.matches("1")) {
//                cbStatus.setSelected(true);
//                cbStatus.setText("Active");
//            } else if (users.status.matches("0")) {
//                cbStatus.setSelected(false);
//                cbStatus.setText("Deactive");
//            }
//            if (users.id.matches("1")) {
//                btnUpdate.setVisible(false);
//                btnDelete.setVisible(false);
//                hlChangePassword.setVisible(false);
//                hlViewPermission.setVisible(false);
//            } else {
//                btnUpdate.setVisible(true);
//                btnDelete.setVisible(true);
//                hlChangePassword.setVisible(true);
//                hlViewPermission.setVisible(true);
//            }

        }
    }

    public void showDetails() {
//        tblEmoyeeList.setItems(users.employeeLists);
//        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
//        clmEmployeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
//        usersGetway.view(users);
    }

    public void checqPermission() {
//        try {
//            pst = con.prepareStatement("select * from "+db+".UserPermission where UserId=?");
//            pst.setString(1, userId);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                if (rs.getInt(13) != 1) {
//                    hlChangePassword.setDisable(true);
//                } else {
//                    hlChangePassword.setDisable(false);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewEmployeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void clearAll() {
        tfUserName.clear();
        tfFullName.clear();
        tfCreatedBy.clear();
    }
}
