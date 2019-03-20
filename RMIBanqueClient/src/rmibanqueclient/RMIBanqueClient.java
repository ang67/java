package rmibanqueclient;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;

/**
 *
 * @author bini
 */
public class RMIBanqueClient extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource(Utils.getViewsPath()+"CompteViews.fxml"));
        Scene scene = new Scene(root);        
        
        primaryStage.setTitle("Ma BANQUE+");
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
