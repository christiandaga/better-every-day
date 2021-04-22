package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLExampleController {

	@FXML 
	protected void exampleScreenButtonAction(ActionEvent event) {
		try {
			FXRouter.goTo("anotherExampleScreen");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	protected void anotherExampleScreenButtonAction(ActionEvent event) {
		try {
			FXRouter.goTo("exampleScreen");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
