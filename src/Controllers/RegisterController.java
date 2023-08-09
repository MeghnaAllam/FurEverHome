package Controllers;

import Model.Seller;
import Model.Buyer;
import services.BuyerService;
import services.SellerService;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utilities.Constants;

public class RegisterController {
	
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
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void onLogin() {
		Main m = new Main();
		m.changeScene("login.fxml", null);
	}
}
