package views;
import java.util.Date;
import entities.Compte;
import entities.Operation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ICompteRemote;
import services.IOperationRemote;
import utils.MakeOd;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author bini
 */
public class CompteViewsController implements Initializable {
    private boolean isSuccess = false;
    private ICompteRemote mDb;
    private IOperationRemote mDbO;

    @FXML
    private Button btn_list_op;
    @FXML
    private TableView<Compte> table_cpt;
    @FXML
    private TableColumn<Compte, String> col_cptno;
    @FXML
    private TableColumn<Compte, String> col_nom;
    @FXML
    private TableColumn<Compte, String> col_prenom;
    @FXML
    private TableColumn<Compte, Double> col_solde;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_pass;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField txt_solde;
    @FXML
    private TableColumn<Compte, Date> col_date_creation;
    @FXML
    private MenuItem menu_Add_Cpt;
    @FXML
    private MenuItem menu_del_Cpt;
    @FXML
    private Label label_state;
    @FXML
    private MenuItem menu_actualiser;
    @FXML
    private MenuItem menu_depot;
    @FXML
    private MenuItem menu_retrait;
    @FXML
    private MenuItem menu_about;
    @FXML
    private MenuItem menu_Exit;
    @FXML
    private TextField txt_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mDb = MakeOd.getCompteInstance();
            mDbO = MakeOd.getOperationInstance();

            col_cptno.setCellValueFactory(new PropertyValueFactory<Compte, String>("numeroCompte"));
            col_nom.setCellValueFactory(new PropertyValueFactory<Compte, String>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<Compte, String>("prenom"));
            col_solde.setCellValueFactory(new PropertyValueFactory<Compte, Double>("solde"));
            col_date_creation.setCellValueFactory(new PropertyValueFactory<Compte, Date>("dateCreation"));

            btn_valider.setDisable(true);
            txt_pass.setVisible(false);

            btn_list_op.setVisible(false);

            loadTable();
        } catch (Exception ex) {}
    }    

    @FXML
    private void act_btn_list_op(ActionEvent event) throws IOException{
        String cptnoExport = table_cpt.getSelectionModel().getSelectedItem().getNumeroCompte();
        Utils util = new Utils(cptnoExport);
        
        Parent root = FXMLLoader.load(getClass().getResource(Utils.getViewsPath()+"OperationViews.fxml"));
        Utils.myLoadPage(event,  "OPERATIONS BANCAIRES", root);
    }

    @FXML
    private void act_btn_valider(ActionEvent event) throws RemoteException {
        String txtNo;
        double oldSolde;
        if(btn_valider.getText().equalsIgnoreCase("retrait")){
            Compte cpt = table_cpt.getSelectionModel().getSelectedItem();
            if(cpt != null){
               retraitAction(cpt); 
            }
        }
        else if(btn_valider.getText().equalsIgnoreCase("dépôt")){
            Compte cpt = table_cpt.getSelectionModel().getSelectedItem();
            if(cpt != null){
               depotAction(cpt); 
            }
        }else if (btn_valider.getText().equalsIgnoreCase("modifier")){//updade()
            if (txt_nom.getText().isEmpty() || txt_prenom.getText().isEmpty() || txt_solde.getText().isEmpty()){
                //Erreur
                Utils.showMessage("Erreur", "Veillez remplir correctement tous les champs", Alert.AlertType.ERROR);
                refreshChamps();
            }else{//ok pour la modification
                //on reccupere le numero de compte
                txtNo = table_cpt.getSelectionModel().getSelectedItem().getNumeroCompte();
                oldSolde = table_cpt.getSelectionModel().getSelectedItem().getSolde();
                try {
                    //on fait une requete pour avoir le compte complet
                    Compte cpt= mDb.findOneByCompteNumero(txtNo);
                    cpt.setNom(txt_nom.getText());
                    cpt.setPrenom(txt_prenom.getText());
                    try {
                        cpt.setSolde(Double.parseDouble(txt_solde.getText()));
                        
                    } catch (NumberFormatException e) {
                        Utils.showMessage("Erreur", "Veillez saisir un solde correct", Alert.AlertType.ERROR);
                        txt_solde.setText("");
                        return;//fin
                    }
                    //update
                    isSuccess = mDb.updateCompte(cpt);
                    if(oldSolde != cpt.getSolde()){
                        String typeMessage = "Modification de solde de " + oldSolde +
                                " FCFA à "  + cpt.getSolde() + " FCFA";
                        mAddOp(cpt, typeMessage);
                    }
                    Utils.showMessage("Info", "Modification éffectuée avec succes", Alert.AlertType.INFORMATION);
                    ActulizeAction();
                    refreshChamps();

                } catch (RemoteException ex) {
                    Logger.getLogger(CompteViewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }else{//Creation de nouveau compte
            if (txt_nom.getText().isEmpty() || txt_prenom.getText().isEmpty() || txt_solde.getText().isEmpty() || txt_pass.getText().isEmpty()){
                //Erreur
                Utils.showMessage("Erreur", "Veillez remplir tous les champs", Alert.AlertType.ERROR);
                txt_nom.setText("");
                txt_prenom.setText("");
                txt_solde.setText("");
                txt_pass.setText("");
            }else{//ok pour l'ajout
                //creation
                Compte cpt = new Compte();
                
                cpt.setNom(txt_nom.getText());
                cpt.setPrenom(txt_prenom.getText());
                cpt.setMotDePasse(txt_pass.getText());
                
                try {
                    cpt.setSolde(Double.parseDouble(txt_solde.getText()));
                } catch (NumberFormatException e) {
                    Utils.showMessage("Erreur", "Veillez saisir un solde correct", Alert.AlertType.ERROR);
                    txt_solde.setText("");
                    return;//fin
                }
                
                try {
                    //ajout
                    isSuccess = mDb.addCompte(cpt);
                    if(isSuccess){
                        String typeOp = "Création du compte avec un solde initial de "+ cpt.getSolde() + " FCFA";
                        mAddOp(cpt, typeOp);
                        Utils.showMessage("Info", "Ajout éffectué avec succes", Alert.AlertType.INFORMATION);
                        ActulizeAction();
                        refreshChamps();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(CompteViewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void select_model(MouseEvent event) {
        if(table_cpt.getSelectionModel().getSelectedItem() !=null){
            label_state.setText("Mise à jour");
            try {
                txt_nom.setText(table_cpt.getSelectionModel().getSelectedItem().getNom());
                txt_prenom.setText(table_cpt.getSelectionModel().getSelectedItem().getPrenom());
                txt_solde.setText(table_cpt.getSelectionModel().getSelectedItem().getSolde()+"");
                btn_list_op.setVisible(true);
                btn_list_op.setDisable(false);
            } catch (NullPointerException e) {}
        
            btn_valider.setDisable(false);
            txt_pass.setVisible(false);
            btn_valider.setText("Modifier");
        }
    }

    ObservableList<Compte> listComptes = FXCollections.observableArrayList();
    private void loadTable() throws RemoteException {
        //Liste qui va contenir nos comptes
        mDb.findAll().stream().forEach(c->listComptes.add(c));
        table_cpt.setItems(listComptes);
    }

    private void refreshChamps() { 
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_solde.setText("");
        txt_pass.setText("");
    }

    @FXML
    private void onMenuAddCpt(ActionEvent event) {
        label_state.setText("Nouveau compte");
        txt_pass.setVisible(true);
        txt_nom.setVisible(true);
        txt_prenom .setVisible(true);       
        txt_solde.setVisible(true);
        
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_solde.setText("");
        txt_pass.setText("");
        
        btn_valider.setText("Ajouter");
        btn_valider.setDisable(false);
    }

    @FXML
    private void onMenuDelCpt(ActionEvent event) {
        deleteAction();
    }
    
    private void deleteAction() {
        String title, message;
        Alert.AlertType type;
        try {
            Compte cpt = table_cpt.getSelectionModel().getSelectedItem();
            boolean isOk = mDb.deleteCompte(cpt.getCompteId());
            if(isOk){
                title = "Notification";
                message = "Supprimé avec succès !";
                type = Alert.AlertType.INFORMATION;
                ActulizeAction();
            }else{
                title = "Accès refusé!";
                message = "La tentative de suppression a échoué !";
                type = Alert.AlertType.WARNING;
            }
        } catch (RemoteException ex) {
            title = "Erreur";
            message = "Le serveur ne répond pas !";
            type = Alert.AlertType.ERROR;
        }
        Utils.showMessage(title, message, type);
    }

    @FXML
    private void onMenuActualiser(ActionEvent event) {
        ActulizeAction();
    }
    
    private void ActulizeAction(){
        listComptes.clear();
        try {
            loadTable();
            reinitialiserView();
        } catch (RemoteException ex) {
            Logger.getLogger(CompteViewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onMenuDepot(ActionEvent event) throws RemoteException {
        Compte cpt = table_cpt.getSelectionModel().getSelectedItem();
        if(cpt != null){
            label_state.setText("depôt sur le No: "+ cpt.getNumeroCompte());
            txt_nom.setVisible(false);
            txt_prenom.setVisible(false);
            txt_pass.setVisible(false);
            txt_solde.setText("");
            txt_solde.setPromptText("Montant");
            btn_valider.setText("Dépôt");
        }else{
            Utils.showMessage("Erreur", "Veillez selctionner un compte", Alert.AlertType.ERROR);
        }        
    }

    @FXML
    private void onMenuRetrait(ActionEvent event) {
        Compte cpt = table_cpt.getSelectionModel().getSelectedItem();
        if(cpt != null){
            label_state.setText("depôt sur le No: "+ cpt.getNumeroCompte());
            txt_nom.setVisible(false);
            txt_prenom.setVisible(false);
            txt_pass.setVisible(false);
            txt_solde.setText("");
            txt_solde.setPromptText("montant");
            btn_valider.setText("Retrait");
        }else{
            Utils.showMessage("Erreur", "Veillez selctionner un compte", Alert.AlertType.ERROR);
        } 
    }

    private void depotAction(Compte cpt) throws RemoteException {
        String message;
        boolean isOk;
        double oldSolde = cpt.getSolde();
        try {
            Double depot_solde = Double.parseDouble(txt_solde.getText());
            cpt.setSolde(oldSolde + depot_solde);
        
            isOk = mDb.updateCompte(cpt);
            if(isOk){
                message ="Dépôt de " + depot_solde + " FCFA effectué sur le numero " + cpt.getNumeroCompte();
                String typeOp = "Dépôt d'un montant de: "+ depot_solde + " FCFA";
                mAddOp(cpt, typeOp);
                Utils.showMessage("Info", message, Alert.AlertType.INFORMATION);
                reinitialiserView();
                ActulizeAction();
            }
        } catch (NumberFormatException e) {
            Utils.showMessage("Erreur", "Veillez saisir un solde correct", Alert.AlertType.ERROR);
            txt_solde.setText("");
            return;//fin
        }
    }
    
    private void retraitAction(Compte cpt) throws RemoteException {
        String message;
        boolean isOk;
        double oldSolde = cpt.getSolde();
        try {
            Double retrait_solde = Double.parseDouble(txt_solde.getText());
            if(retrait_solde < oldSolde){
                cpt.setSolde(oldSolde - retrait_solde);
        
                isOk = mDb.updateCompte(cpt);
                if(isOk){
                    message ="Retrait de " + retrait_solde + " FCFA effectué sur le numero " + cpt.getNumeroCompte();
                    Utils.showMessage("Info", message, Alert.AlertType.INFORMATION);
                    String typeOp = "Retrait d'un montant de: "+ retrait_solde + " FCFA";
                    mAddOp(cpt, typeOp);
                    reinitialiserView();
                    ActulizeAction(); 
                }  
            }else{
                Utils.showMessage("Erreur", "Solde insuffisant !", Alert.AlertType.ERROR);
                txt_solde.setText("");
                return;//fin
            }
        } catch (NumberFormatException e) {
            Utils.showMessage("Erreur", "Veillez saisir un solde correct", Alert.AlertType.ERROR);
            txt_solde.setText("");
            return;//fin
        }   
    }
    
    private void reinitialiserView(){
      
        txt_nom.setVisible(true);
        txt_prenom.setVisible(true);
        txt_solde.setVisible(true);
        txt_pass.setVisible(false);
        
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_solde.setText("");
        txt_pass.setText("");
        txt_search.setText("");
        
        btn_valider.setDisable(true);
        btn_valider.setText("Modifier");
        btn_list_op.setVisible(true);
        btn_list_op.setDisable(true);
    }

    @FXML
    private void onMenuAbout(ActionEvent event) {
         Utils.showMessage("A propos", "Projet Java RMI d'une banque,"
                 + "réalisé par:\n ADOUKO Anoh\n ANGUI Bini\n DIE Thiery"
                 + "\n GUIA Rufus \n KONE Okayo", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onMenuExit(ActionEvent event) {
    }

    private void mAddOp(Compte cpt, String typeOp) throws RemoteException {
        Operation op= new Operation();
        op.setCompteNumero(cpt.getNumeroCompte());
        op.setTypeOperation(typeOp);
        boolean isOk = mDbO.addOperation(op);        
    }

    @FXML
    private void onKeyReleasedSearch(KeyEvent event) throws RemoteException {
        ObservableList<Compte> listComptesSearch = FXCollections.observableArrayList();
        //Liste qui va contenir nos comptes
        mDb.findAll().stream().forEach(c->listComptesSearch.add(c));
        table_cpt.setItems(listComptesSearch);
    }
}
