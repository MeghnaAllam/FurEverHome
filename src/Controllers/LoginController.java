package Controllers;



import java.sql.SQLException;

import Model.Buyer;
import Model.Seller;
import Services.LoginService;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	public LoginController () {
		
	}
	
	@FXML
    private Button button;
	
	@FXML
	private TextField emailIdField;
	
	@FXML
	private PasswordField passwordField;

	
	public void onLogin() {
		String emailId = emailIdField.getText();
		String password = passwordField.getText();
		if (validateInputFields()) {
			LoginService ls = new LoginService(password, emailId);
			try {
				Seller s = ls.loginSeller();
				if (s != null) {
					signInSeller();
				} else {
					Buyer b = ls.loginBuyer();
					
					if (b != null) {
						signInBuyer(b);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void onSignUp() {
		Main m = new Main();
		m.changeScene("register.fxml");
	}
	
	public boolean validateInputFields() {
		return true;
	}
	
	public void signInBuyer(Buyer buyer) {
		Main m = new Main();
		m.changeScene("buyerDashboard.fxml");
		BuyerController b = new BuyerController(buyer);
	}
	
	public void signInSeller() {
		Main m = new Main();
		m.changeScene("SellerDashboard.fxml");
	}
}
