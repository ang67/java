/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Compte;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author bini
 */
public class Utils {
    public static String cptnoExport;

     public Utils() {
        
    }
    public Utils(String _cptnoExport) {
        Utils.cptnoExport = _cptnoExport;
    }
    
    public static String getViewsPath(){
        return "/views/";
    }
    
    public static void showMessage(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
        
    public static void loadForm(Stage stage, Parent root){
        //scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //pas redimensionnable
        stage.setResizable(false);
        //on recupère le focuss
        stage.requestFocus();
        stage.initStyle(StageStyle.UNDECORATED);
        //on affiche le focus
        stage.show();
    }
    
    public static void loadScene(Stage stage, Parent root){
        //Parent root = FXMLLoader.load(getClass().getResource("ScenePersonne.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //pas redimensionnable
        stage.setResizable(false);
        //Largeur de la fenêtre
        stage.setWidth(772.0);
        stage.setHeight(564.0);
        //on recupère le focuss
        stage.requestFocus();
        //on affiche le focus
        stage.show();
    }
    
public static void myLoadPage(ActionEvent event, String title, Parent root) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        //pas redimensionnable
        stage.setResizable(false);
        stage.setTitle(title);
        
        //on recupère le focuss
        stage.requestFocus();
        //on affiche le focus
        stage.show();
    }

    public String getcptnoExport() {
        return this.cptnoExport;
    }
}