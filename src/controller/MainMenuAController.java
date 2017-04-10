/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.application.MPemasukanController;
import controller.application.MPengeluaranController;
import controller.application.PengaturanController;
import controller.application.TmbhItemController;
import controller.user.EmployeController;
import controller.application.TmbhItemIuranController;
import controller.application.beranda.berandaController;
import dao.implementUser;
import factory.DAOFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.model_User;

/**
 * FXML Controller class
 *
 * @author rheza
 */
public class MainMenuAController implements Initializable {

    @FXML
    private AnchorPane acMain;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private Button btnCashin;
    @FXML
    private ImageView imgCashinBtn;
    @FXML
    private Button btnEmplopye;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private Button btnCashout;
    @FXML
    private ImageView imgCashoutBtn;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Button btnLogOut;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Label lblUsrName;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private Label lblUserId;
    @FXML
    private StackPane acContent;

    Image menuImage = new Image("/icon/menu.png");
    Image menuImageBlue = new Image("/icon/menuBlue.png");
    Image image;
    Image home = new Image("/icon/home.png");
    Image homeBlue = new Image("/icon/homeBlue.png");
    Image cashin = new Image("/icon/cash-in.png");
    Image cashinBlue = new Image("/icon/cash-inBlue.png");
    Image cashout = new Image("/icon/cash-out.png");
    Image cashoutBlue = new Image("/icon/cash-outBlue.png");
    Image employee = new Image("/icon/employe.png");
    Image employeeBlue = new Image("/icon/employeBlue.png");
    Image setting = new Image("/icon/settings.png");
    Image settingBlue = new Image("/icon/settingsBlue.png");
    Image about = new Image("/icon/about.png");
    Image aboutBlue = new Image("/icon/aboutBlue.png");

    String defaultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#3498db";

    public void start(Stage primaryStage) throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenuA.fxml"));
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setMaximized(true);
//        primaryStage.show();
    }
//
//    @FXML
//    private void acMain(KeyEvent event) {
//        if (event.getCode() == KeyCode.F11) {
//            Stage stage = (Stage) acMain.getScene().getWindow();
//            stage.setFullScreen(true);
//        }
//    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
            imgMenuBtn.setImage(menuImageBlue);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();
            acDashBord.getChildren().clear();
        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(130);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPan);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUserName(String username, String displayname, String role) {
        this.lblFullName.setText(displayname);
        this.lblUsrName.setText(username);
        this.lblUsrNamePopOver.setText(username);
        this.lblRoleAs.setText(role);
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException {
        homeActive();
        DAOFactory user = DAOFactory.getFactory(DAOFactory.MySQL);
        implementUser dAOUser = user.getUserMySQL();
        int totalUser = dAOUser.getCount();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/beranda.fxml"));
        loader.load();
        berandaController Berandacontroller = loader.getController();
        Berandacontroller.setLbBerandaTotalUser(totalUser);

        AnchorPane root = loader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);

        System.out.println(lblUsrName.getText());
        System.out.println(lblUserId.getText());

    }

    @FXML
    private void btnCashInOnClick(ActionEvent event) throws IOException {
        CIActive();
        //MPemasukanController sc = new MPemasukanController();
        //userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/mPemasukan.fxml").openStream());
        //nm.setId(id);
        MPemasukanController pemasukanController = fXMLLoader.getController();
        //pemasukanController.bpPemasukan.getStylesheets().add("/style/MainStyle.css");
        //stockController.setUserId(usrNameMedia);
        //pemasukanController.btnStockOnAction(event);
        //pemasukanController.settingPermission();
        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCashOutOnClick(ActionEvent event) {
        COActive();
        MPengeluaranController controller = new MPengeluaranController();
        //userNameMedia nm = new userNameMedia();
        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/mPengeluaran.fxml").openStream());
            //nm.setId(id);
            MPengeluaranController pengeluaranController = fXMLLoader.getController();
            //sellController.setNameMedia(usrNameMedia);
            //pengeluaranController.acMainSells.getStylesheets().add("/style/MainStyle.css");
            //pengeluaranController.tbtnSellOnAction(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuAController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnEmployeOnClick(ActionEvent event) throws IOException {
        userActive();
        
        DAOFactory user = DAOFactory.getFactory(DAOFactory.MySQL);
        implementUser dAOUser = user.getUserMySQL();
        List<model_User> UserList = dAOUser.getAll();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/beranda.fxml"));
        loader.load();
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/Employe.fxml"));
        fXMLLoader.load();

        EmployeController employeController = fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/style/MainStyle.css");
        employeController.btnViewEmployeeOnAction(event);

        AnchorPane acPane = fXMLLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(acPane);

    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {
         SettActive();
        //inithilize Setting Controller
        TmbhItemIuranController pengaturanController = new TmbhItemIuranController();
        //inithilize UserNameMedia
        //userNameMedia usrMedia = new userNameMedia();
        // Define a loader to inithilize Settings.fxml controller
        FXMLLoader settingLoader = new FXMLLoader();
        //set the location of Settings.fxml by fxmlloader
        settingLoader.load(getClass().getResource("/view/TmbhItem.fxml").openStream());

        //Send id to userMedia
        //usrMedia.setId(id);
        //take control of settingController elements or node
         TmbhItemController pengaturanControl = settingLoader.getController();
        //pengaturanControl.bpSettings.getStylesheets().add("/style/MainStyle.css");

        //pengaturanControl.setUsrMedia(usrMedia);
        //pengaturanControl.miMyASccountOnClick(event);
        //pengaturanControl.settingPermission();

        AnchorPane acPane = settingLoader.getRoot();
        //acContent clear and make useul for add next node
        acContent.getChildren().clear();
        //add a node call "acPane" to acContent
        acContent.getChildren().add(acPane);

    }

    @FXML
    private void btnAboutOnClick(ActionEvent event) {

        try {
            AboutActive();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/Tentang.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuAController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void homeActive() {
        imgHomeBtn.setImage(homeBlue);
        imgCashinBtn.setImage(cashin);
        imgCashoutBtn.setImage(cashout);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(activeStyle);
        btnCashin.setStyle(defaultStyle);
        btnCashout.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void CIActive() {
        imgHomeBtn.setImage(home);
        imgCashinBtn.setImage(cashinBlue);
        imgCashoutBtn.setImage(cashout);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defaultStyle);
        btnCashin.setStyle(activeStyle);
        btnCashout.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void COActive() {
        imgHomeBtn.setImage(home);
        imgCashinBtn.setImage(cashin);
        imgCashoutBtn.setImage(cashoutBlue);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defaultStyle);
        btnCashin.setStyle(defaultStyle);
        btnCashout.setStyle(activeStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void userActive() {
        imgHomeBtn.setImage(home);
        imgCashinBtn.setImage(cashin);
        imgCashoutBtn.setImage(cashout);
        imgEmployeBtn.setImage(employeeBlue);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defaultStyle);
        btnCashin.setStyle(defaultStyle);
        btnCashout.setStyle(defaultStyle);
        btnEmplopye.setStyle(activeStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void SettActive() {
        imgHomeBtn.setImage(home);
        imgCashinBtn.setImage(cashin);
        imgCashoutBtn.setImage(cashout);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(settingBlue);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defaultStyle);
        btnCashin.setStyle(defaultStyle);
        btnCashout.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(activeStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void AboutActive() {
        imgHomeBtn.setImage(home);
        imgCashinBtn.setImage(cashin);
        imgCashoutBtn.setImage(cashout);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(aboutBlue);
        btnHome.setStyle(defaultStyle);
        btnCashin.setStyle(defaultStyle);
        btnCashout.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(activeStyle);
    }
}
