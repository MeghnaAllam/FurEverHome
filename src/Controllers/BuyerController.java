package Controllers;


import java.io.IOException;
import java.util.Optional;

import Model.Buyer;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BuyerController {
	
	@FXML 
	private Button homeBtn;
	
	@FXML
	private Button myActivityBtn;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private AnchorPane homePane;
	
	@FXML
	private Label userDetailLabel;
	
	private Buyer buyer;
	
	public BuyerController() {
		
	}
	
	public BuyerController(Buyer b) {
		this.buyer = b;
	}
	
	public void onMyActivity() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/UI/buyerActivityPane.fxml"));
		mainPane.setCenter(root);
	}
	
	public void onHome() throws IOException {
		mainPane.setCenter(homePane);
	}
	
	public void onLogOut() throws IOException {
		try {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout");
		
		Optional<ButtonType> option = alert.showAndWait();
		
		if(option.get().equals(ButtonType.OK)) {
			Main m = new Main();
			m.changeScene("login.fxml");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
