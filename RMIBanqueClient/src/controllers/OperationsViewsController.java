/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operation;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bini
 */
public class OperationsViewsController implements Initializable {

    @FXML
    private Label aff_list_op_cptno;
    @FXML
    private TableView<Operation> table_op;
    @FXML
    private TableColumn<Operation, Integer> col_num;
    @FXML
    private TableColumn<Operation, ?> col_type_op;
    @FXML
    private TableColumn<Operation, ?> col_date_op;
    @FXML
    private Button btn_exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*column_num.setCellValueFactory(new PropertyValueFactory<Departement, Integer>("deptno"));
        column_nom.setCellValueFactory(new PropertyValueFactory<Departement, String>("dname"));
        column_loc.setCellValueFactory(new PropertyValueFactory<Departement, String>("loc"));
        btn_add_emp.setVisible(false);
        btn_modifier.setVisible(false);
        btn_supp.setVisible(false);
        try {
            loadTable();
        } catch (RemoteException ex) {}*/

    
    }
}
    
