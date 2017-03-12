package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rheza
 */
public class MainClass extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    private static class Delta {

        double x, y;
    }
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginSiput.fxml"));
        primaryStage.setScene(new Scene(root));
        //primaryStage.setTitle("Login");
        //agar stage button hilang
        primaryStage.initStyle(StageStyle.UNDECORATED); 
        //agar scene dapat di drag
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = primaryStage.getX() - event.getScreenX();
                dragDelta.y = primaryStage.getY() - event.getScreenY();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + dragDelta.x);
                primaryStage.setY(event.getScreenY() + dragDelta.y);
                primaryStage.getWidth();
                primaryStage.getHeight();
                primaryStage.getX();
                primaryStage.getY();
                inDrag.set(true);

            }
        });
        //primaryStage.sizeToScene();
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
