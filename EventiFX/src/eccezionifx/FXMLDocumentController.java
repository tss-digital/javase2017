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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    private Button cmdEsegui;

    private void onEsegui(ActionEvent event) {
        int somma = 0;
        try {
            if (txtOp1.getLength() == 0 || txtOp2.getLength() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Operatore mancante");
                alert.showAndWait();
                return;
            }
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

        cmdEsegui.setOnAction(this::onEsegui);

        txtOp1.textProperty().addListener(new CheckNumericField(txtOp1));

        txtOp2.textProperty().addListener(new CheckNumericField(txtOp2));
    }

}
