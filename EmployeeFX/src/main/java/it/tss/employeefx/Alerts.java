/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.employeefx;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author alfonso
 */
public class Alerts {

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showWarning(String msg) {
        showWarning(msg, "");
    }

    public static void showWarning(String msg, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static boolean showConfirm(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                msg,
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.NO) == ButtonType.YES;
    }
}
