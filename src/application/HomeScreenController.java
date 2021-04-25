package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HomeScreenController implements Initializable {

	@FXML
	private ListView habitList;
	
	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	@FXML
	protected void goToSettings() {
		
	}
	
	@FXML
	protected void createHabit() throws IOException {
		FXRouter.goTo("createHabit");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Document> habitDocs = Db.db.findMany("habits", Filters.eq("username", Auth.currentUser.getUsername()));
		habitList.getItems().addAll(habitDocs.stream().map((Document habit) -> habit.getString("name")).toArray());
	}
	
}
