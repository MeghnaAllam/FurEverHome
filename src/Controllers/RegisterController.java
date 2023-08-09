package Controllers;

import Model.Seller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Buyer;
import Services.BuyerService;
import Services.SellerService;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import utilities.Constants;

public class RegisterController implements Initializable {
	
	@FXML
	private PasswordField password;
	
	@FXML
	private PasswordField cPassword;
	
	@FXML
	private TextField fName;
	
	@FXML
	private TextField lName;
	
	@FXML
	private TextField emailId;
	
	@FXML
	private TextField city;
	
	@FXML
	private TextField state;
	
	@FXML
	private RadioButton sellerType;
	
	@FXML
	private RadioButton buyerType;
	
	public void onRegister() {
		
		String firstName = fName.getText();
		String lastName = lName.getText();
		String emailID = emailId.getText();
		String passwordField = password.getText();
		String stateField = state.getText();
		String cityField = city.getText();
		
		//Seller Type selected
		if(sellerType.isSelected()) {
			String type = Constants.SELLER;
			Seller seller = new Seller(firstName, lastName, emailID, passwordField, stateField, cityField, type);
			SellerService ss = new SellerService(seller);
			registeredSuccessfully();
			clearAllFields();
			try{
				ss.addSellerToDb();
			}catch(Exception e) {
				System.out.println(e);
			}	
		}
		
		//Buyer type selected
		if(buyerType.isSelected()) {
			String type = Constants.BUYER;
			Buyer buyer = new Buyer(firstName, lastName, emailID, passwordField, stateField, cityField, type);
			BuyerService bs = new BuyerService(buyer);
			try{
				bs.addBuyerToDb();
				clearAllFields();
				registeredSuccessfully();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	private void registeredSuccessfully() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success Message");
		alert.setHeaderText(null);
		alert.setContentText("Pet Info Deleted Successfully");
		
		Optional<ButtonType> option = alert.showAndWait();
		login();
	}
	
	public void clearAllFields() {
		fName.setText("");
		lName.setText("");
		emailId.setText("");
		password.setText("");
		state.setText("");
		city.setText("");
		cPassword.setText("");
		buyerType.setSelected(false);
		sellerType.setSelected(false);
	}
	
	public void onLogin() {
		login();
	}
	
	public void login() {
		Main m = new Main();
		m.changeScene("login.fxml", null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ToggleGroup toggleGroup = new ToggleGroup();
		sellerType.setToggleGroup(toggleGroup);
		buyerType.setToggleGroup(toggleGroup);
		
	}
}
