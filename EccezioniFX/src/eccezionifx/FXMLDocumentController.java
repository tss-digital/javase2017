/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezionifx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tss
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtOp1;

    @FXML
    private TextField txtOp2;

    @FXML
    private Label result;

    private Funzioni f;

    @FXML
    private void onEsegui(ActionEvent event) {
        int somma = 0;
        try {
            somma = f.sommaNumeriPositivi(Integer.parseInt(txtOp1.getText()), Integer.parseInt(txtOp2.getText()));
        } catch (SommaException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.showAndWait();
        } finally {
            result.setText(String.valueOf(somma));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        f = new Funzioni();
    }

}
