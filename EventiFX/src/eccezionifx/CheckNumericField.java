/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezionifx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author tss
 */
public class CheckNumericField implements ChangeListener<String> {

    private final TextField txt;

    public CheckNumericField(TextField txt) {
        this.txt = txt;
    }

    @Override
    public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        try {
            if (newValue.length() == 0) {
                txt.setText("0");
                return;
            }
            if (newValue.equals("-")) {
                return;
            }
            Integer.parseInt(newValue);
        } catch (NumberFormatException e) {
            txt.setText(oldValue);
        }
    }

}

