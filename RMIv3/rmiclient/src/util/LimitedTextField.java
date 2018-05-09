package util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LimitedTextField extends TextField {

    private final int limit;

    public LimitedTextField(int limit) {
        this.limit = limit;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
    	if (getText().contains("~")) {   
    		int index = getText().indexOf("~");
    		if (index != 0) index--;
    		setText(getText().substring(0, index));
    		Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog", "Caractere ~ interdit");
    	}
    	if (getText().contains("|")) {   
    		int index = getText().indexOf("|");
    		if (index != 0) index--;
    		setText(getText().substring(0, index));
			Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog", "Caractere | interdit");
    	}
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
			Fenetre.creatAlert(AlertType.INFORMATION,"Information Dialog", "Les messages doivent faire moins de 256 caracteres");
        }
    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
}
