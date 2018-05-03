package util;

import javafx.scene.control.Alert;
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
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Caractere ~ interdit");
			alert.showAndWait();
    	}
    	if (getText().contains("§")) {   
    		int index = getText().indexOf("§");
    		if (index != 0) index--;
    		setText(getText().substring(0, index));
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Caractere ~ interdit");
			alert.showAndWait();
    	}
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Les messages doivent faire moins de 256 caracteres");
			alert.showAndWait();
        }
    }
}
