package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ExampleScreenController implements Initializable {
	
	@FXML
	ListView<String> list;
	
	@FXML 
	protected void exampleScreenButtonAction(ActionEvent event) {
		try {
			FXRouter.goTo("anotherExampleScreen");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> items = FXCollections.observableArrayList();
		list.setItems(items);
		
		Document u = Db.db.findOne("users", Filters.eq("username", FXRouter.getData()));
		User user = new User(u);
		items.add(user.getUsername());
		items.add(user.getEmail());
		items.add(String.valueOf(user.getUserLevel()));
	}
	
}
