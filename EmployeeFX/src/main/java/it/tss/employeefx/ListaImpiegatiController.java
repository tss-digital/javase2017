package it.tss.employeefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListaImpiegatiController implements Initializable {

    @FXML
    TableView<Impiegato> tblImpiegati;

    @FXML
    TableColumn<Impiegato, Integer> numero;
    @FXML
    TableColumn<Impiegato, String> nome;
    @FXML
    TableColumn<Impiegato, String> cognome;
    @FXML
    TableColumn<Impiegato, String> email;
    @FXML
    TableColumn<Impiegato, String> ufficio;
    @FXML
    TableColumn<Impiegato, String> mansione;
    @FXML
    TableColumn<Impiegato, String> responsabile;

    private Impiegato selectedImpiegato;

    ImpiegatoService impService;

    ObservableList<Impiegato> dati;

    private Stage mainStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bindTable();

        impService = new ImpiegatoService();
        dati = FXCollections.observableArrayList();
        loadImpiegati();
        tblImpiegati.setItems(dati);
        tblImpiegati.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::onSelectImpiegato);
    }

    private void loadImpiegati() {
        dati.clear();
        dati.addAll(impService.selezionaTutti());
    }

    public void onSelectImpiegato(ObservableValue<? extends Impiegato> ov, Impiegato oldSel, Impiegato newSel) {
        this.selectedImpiegato = newSel;
        System.out.println(newSel);
    }

    public void onElimina(ActionEvent event) {
        System.out.println("onElimina");
        boolean result = Alerts.showConfirm("Sei sicuro di voler eliminare l'impiegato selezionato?");
        if (result) {
            impService.delete(selectedImpiegato.getNumero());
            dati.remove(selectedImpiegato);
        }

    }

    @FXML
    public void onCrea(ActionEvent e) {
        openDialogEditImpiegato(true);
        loadImpiegati();
    }

    @FXML
    public void onModifica(ActionEvent e) {
        openDialogEditImpiegato(false);
        loadImpiegati();
    }

    @FXML
    public void onClose(ActionEvent e) {
        boolean result = Alerts.showConfirm("Sei sicuro di voler uscire dall'applicazione?");
        if (result) {
            System.exit(0);
        }
    }

    private void openDialogEditImpiegato(boolean crea) {

        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/editImpiegato.fxml"));
            Scene s = new Scene(loader.load());
            st.setScene(s);
            EditImpiegatoController controller = loader.getController();
            controller.setStage(st);
            if(crea){
                controller.setImpiegato(new Impiegato());
            }else{
                controller.setImpiegato(selectedImpiegato);
            }
            st.setTitle("Crea / Modifica dati Impiegato");
            st.initModality(Modality.WINDOW_MODAL);
            st.initOwner(mainStage);
            st.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private void bindTable() {
        numero.setCellValueFactory(new PropertyValueFactory<Impiegato, Integer>("numero"));
        nome.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("nome"));
        cognome.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("cognome"));
        email.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("email"));
        ufficio.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("ufficio"));
        mansione.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("mansione"));
        responsabile.setCellValueFactory(new PropertyValueFactory<Impiegato, String>("responsabile"));
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
    
}
