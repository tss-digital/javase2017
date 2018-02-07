package it.tss.customersfx;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

    
    @FXML
    TableView<Customer> tblCustomers;

    @FXML
    TableColumn<Customer, String> colNumber;
    @FXML
    TableColumn<Customer, String> colName;
    @FXML
    TableColumn<Customer, String> colContactFirst;
    @FXML
    TableColumn<Customer, String> colContactLast;
    @FXML
    TableColumn<Customer, String> colPhone;
    @FXML
    TableColumn<Customer, String> colAddress;
    @FXML
    TableColumn<Customer, String> colCountry;
    
    private ObservableList<Customer> dati;
    
    CustomerService customerService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colContactFirst.setCellValueFactory(new PropertyValueFactory<>("contactFirst"));
            colContactLast.setCellValueFactory(new PropertyValueFactory<>("contactLast"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            
            dati = FXCollections.observableArrayList();
            customerService = new CustomerService();
            dati.addAll(customerService.findAll());
            tblCustomers.setItems(dati);
        } catch (RuntimeException ex) {
            System.out.println("Ops...errore.." + ex.getMessage());
            System.out.println(ex.getCause().getMessage());
        }
    }

    private void loadData() {
       
    }
}
