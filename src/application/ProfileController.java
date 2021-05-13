package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
	private ProgressBar pBar;
	@FXML
	private Label numAchieved;
	
	@FXML
	private ImageView diet1;
	@FXML
	private ImageView diet2;
	@FXML
	private ImageView diet3;
	@FXML
	private ImageView diet4;
	
	@FXML
	private ImageView custom1;
	@FXML
	private ImageView custom2;
	@FXML
	private ImageView custom3;
	@FXML
	private ImageView custom4;
	
	@FXML
	private ImageView exer1;
	@FXML
	private ImageView exer2;
	@FXML
	private ImageView exer3;
	@FXML
	private ImageView exer4;
	
	@FXML
	private ImageView educ1;
	@FXML
	private ImageView educ2;
	@FXML
	private ImageView educ3;
	@FXML
	private ImageView educ4;

	// Navigation bar.
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
	
	// initializes with username, level, progress bar, and graphically all the badges achieved.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Initializes username and level.
		username.setText(Auth.currentUser.getUsername());
		level.setText(String.valueOf(Auth.currentUser.getUserLevel()));
		
		List<Document> cats = Db.db.findMany("categories", Filters.eq("username", Auth.currentUser.getUsername()));
		
		// Initializes progress bar.
		int numAchv = 0;
		for (Document cat : cats) {
			Category category = new Category(cat);
			numAchv += category.getAchievements();
		}
		pBar.setProgress((double) numAchv / 16);
		numAchieved.setText(numAchv + "/16 Badges Achieved");
		
		// Initializes all the achievements.
		try {
			FileInputStream badgeComplete = new FileInputStream("src/resources/badgeComplete.png");
			Image badgeCompleteImg = new Image(badgeComplete);
			for (Document cat : cats) {
				Category category = new Category(cat);
				switch(category.getName()) {
				case "diet":
					if (category.getAchievements() >= 1)
						diet1.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 2)
						diet2.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 3)
						diet3.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 4)
						diet4.setImage(badgeCompleteImg);
					break;
				case "custom":
					if (category.getAchievements() >= 1)
						custom1.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 2)
						custom2.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 3)
						custom3.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 4)
						custom4.setImage(badgeCompleteImg);
					break;
				case "exercise":
					if (category.getAchievements() >= 1)
						exer1.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 2)
						exer2.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 3)
						exer3.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 4)
						exer4.setImage(badgeCompleteImg);
					break;
				case "education":
					if (category.getAchievements() >= 1)
						educ1.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 2)
						educ2.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 3)
						educ3.setImage(badgeCompleteImg);
					if (category.getAchievements() >= 4)
						educ4.setImage(badgeCompleteImg);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}