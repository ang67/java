package views;

import entities.Compte;
import entities.Operation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ICompteRemote;
import services.IOperationRemote;
import utils.MakeOd;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author bini
 */
public class OperationViewsController implements Initializable {
    private IOperationRemote mDbO;
    private ICompteRemote mDbC;
    private Utils util = new Utils();
    private String mNumCpt = util.getcptnoExport();
    Compte cpt;
    
    @FXML
    private TableView<Operation> table_op;
    @FXML
    private TableColumn<Operation, Integer> col_id;
    @FXML
    private TableColumn<Operation, String> col_type;
    @FXML
    private TableColumn<Operation, Date> col_date_op;
    @FXML
    private Label lbl_nom_cpt;
    @FXML
    private Label lbl_num_cpt;
    @FXML
    private MenuItem menu_actualiser;
    @FXML
    private MenuItem menu_exit;
    @FXML
    private MenuItem menu_retrait;
    @FXML
    private MenuItem menu_del_Cpt;
    @FXML
    private MenuItem menu_about;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mDbO = MakeOd.getOperationInstance();
            mDbC = MakeOd.getCompteInstance();

            col_id.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("operationId"));
            col_type.setCellValueFactory(new PropertyValueFactory<Operation, String>("typeOperation"));
            col_date_op.setCellValueFactory(new PropertyValueFactory<Operation, Date>("dateOperation"));
            
            cpt = mDbC.findOneByCompteNumero(mNumCpt);
            
            lbl_nom_cpt.setText(cpt.getNom());
            lbl_num_cpt.setText(cpt.getNumeroCompte());
            
            loadTable();
        } catch (Exception ex) {}
    }    

    @FXML
    private void select_model(MouseEvent event) {
    }

    @FXML
    private void onMenuActualiser(ActionEvent event) {
        ActulizeAction();
    }

    @FXML
    private void onMenuExit(ActionEvent event) {
    }

    @FXML
    private void onMenuRetrait(ActionEvent event) {
    }

    @FXML
    private void onMenuDelCpt(ActionEvent event) {
    }

    @FXML
    private void onMenuAbout(ActionEvent event) {
        Utils.showMessage("A propos", "Projet Java RMI d'une banque,"
                 + "réalisé par:\n ADOUKO Anoh\n ANGUI Bini\n DIE Thiery"
                 + "\n GUIA Rufus \n KONE Okayo", Alert.AlertType.INFORMATION);
    }
     
    ObservableList<Operation> listOperations = FXCollections.observableArrayList();
    private void loadTable() throws RemoteException {
       mDbO.findByCompteNumero(mNumCpt).stream().forEach(op->listOperations.add(op));
       table_op.setItems(listOperations); 
    }

    @FXML
    private void act_retour(ActionEvent event) throws IOException {        
        Parent root = FXMLLoader.load(getClass().getResource(Utils.getViewsPath()+"CompteViews.fxml"));
        Utils.myLoadPage(event,  "Ma BANQUE+", root);
    }
    
    private void ActulizeAction(){
        listOperations.clear();
        try {
            loadTable();
        } catch (RemoteException ex) {}
    }
}
