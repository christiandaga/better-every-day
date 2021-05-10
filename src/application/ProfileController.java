package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController implements Initializable {
	
	@FXML
	private Label level;
	
	@FXML
	private Label username;
	
	@FXML
	private Label daysOnApp;
	
	@FXML
	private ImageView diet1;
	@FXML
	private ImageView diet2;
	@FXML
	private ImageView custom1;
	@FXML
	private ImageView custom2;
	@FXML
	private ImageView exer1;
	@FXML
	private ImageView exer2;
	@FXML
	private ImageView educ1;
	@FXML
	private ImageView educ2;

	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
	
	// Logs the user out of their account when the Log Out button is clicked.
	@FXML
	protected void logOut() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		username.setText(Auth.currentUser.getUsername());
		level.setText(String.valueOf(Auth.currentUser.getUserLevel()));
		List<Document> cats = Db.db.findMany("categories", Filters.eq("username", Auth.currentUser.getUsername()));

		try {
			FileInputStream badgeComplete = new FileInputStream("src/resources/badgeComplete.png");
			Image badgeCompleteImg = new Image(badgeComplete);
			for (Document cat : cats) {
				Category category = new Category(cat);
				switch(category.getName()) {
				case "diet":
					if (category.getAchievements() >= 2) {
						diet1.setImage(badgeCompleteImg);
						diet2.setImage(badgeCompleteImg);
					} else if (category.getAchievements() == 1)
						diet1.setImage(badgeCompleteImg);
					break;
				case "custom":
					if (category.getAchievements() >= 2) {
						custom1.setImage(badgeCompleteImg);
						custom2.setImage(badgeCompleteImg);
					} else if (category.getAchievements() == 1)
						custom1.setImage(badgeCompleteImg);
					break;
				case "exercise":
					if (category.getAchievements() >= 2) {
						exer1.setImage(badgeCompleteImg);
						exer2.setImage(badgeCompleteImg);
					} else if (category.getAchievements() == 1)
						exer1.setImage(badgeCompleteImg);
					break;
				case "education":
					if (category.getAchievements() >= 2) {
						educ1.setImage(badgeCompleteImg);
						educ2.setImage(badgeCompleteImg);
					} else if (category.getAchievements() == 1)
						educ1.setImage(badgeCompleteImg);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}