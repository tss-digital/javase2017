/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.employeefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tss
 */
public class EditImpiegatoController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMansione;
    @FXML
    private ComboBox<Ufficio> cboUffici;
    @FXML
    private ComboBox<Impiegato> cboResponsabile;

    private Impiegato impiegato;
    private Stage stage;
    private ImpiegatoService impService;
    private UfficioService uffService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        impService = new ImpiegatoService();
        uffService = new UfficioService();
        ObservableList<Ufficio> uffici = FXCollections.observableArrayList();
        uffici.addAll(uffService.selezionaTutti());
        cboUffici.setItems(uffici);
        cboUffici.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::onSelectUfficio);
        ObservableList<Impiegato> impiegati = FXCollections.observableArrayList();
        impiegati.addAll(impService.selezionaTutti());
        cboResponsabile.setItems(impiegati);
        cboResponsabile.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::onSelectResponsabile);
    }

    public void onSelectUfficio(ObservableValue<? extends Ufficio> ov, Ufficio oldValue, Ufficio newValue) {
        impiegato.setUfficio(newValue.getCodice());
    }

    public void onSelectResponsabile(ObservableValue<? extends Impiegato> ov, Impiegato oldValue, Impiegato newValue) {
        impiegato.setResponsabile(String.valueOf(newValue.getNumero()));
    }

    public void onSalva(ActionEvent e) {
        viewToCurrent();
        impService.save(impiegato);
        stage.hide();
    }

    public void onAnnulla(ActionEvent e) {
        stage.hide();
    }

    private void currentToView() {
        if (impiegato == null) {
            return;
        }
        txtNome.setText(impiegato.getNome());
        txtCognome.setText(impiegato.getCognome());
        txtEmail.setText(impiegato.getEmail());
        txtMansione.setText(impiegato.getMansione());
        cboUffici.getSelectionModel().select(uffService.selezionaPerCodice(impiegato.getUfficio()));
        if (impiegato.getResponsabile() != null) {
            cboResponsabile.getSelectionModel().select(impService.selezionaPerCodice(Integer.parseInt(impiegato.getResponsabile())));
        }

    }

    private void viewToCurrent() {
        impiegato.setNome(txtNome.getText());
        impiegato.setCognome(txtCognome.getText());
        impiegato.setEmail(txtEmail.getText());
        impiegato.setMansione(txtMansione.getText());
    }

    /*
    getters and setters
     */
    public Impiegato getImpiegato() {
        return impiegato;
    }

    public void setImpiegato(Impiegato impiegato) {
        this.impiegato = impiegato;
        currentToView();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
