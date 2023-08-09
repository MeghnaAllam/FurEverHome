package Controllers;



import java.sql.SQLException;

import Model.Buyer;
import Model.Seller;
import services.LoginService;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
	
    @FXML
    private Hyperlink forgotPasswordBtn;
    
    

	
	public void onLogin() {
		String emailId = emailIdField.getText();
		String password = passwordField.getText();
		if (validateInputFields()) {
			LoginService ls = new LoginService(password, emailId);
			try {
				Seller s = ls.loginSeller();
				if (s != null) {
					signInSeller(s);
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
		m.changeScene("register.fxml", null);
	}
	
	public boolean validateInputFields() {
		return true;
	}
	
	public void signInBuyer(Buyer buyer) {
		Main m = new Main();
		m.changeScene("buyerDashboard.fxml", buyer);
	}
	
	public void signInSeller(Seller seller) {
		Main m = new Main();
		m.changeScene("SellerDashboard.fxml", seller);
	}
	
    @FXML
    void handleForgotPassword(ActionEvent event) {
    	Main m = new Main();
           m.changeScene("ForgotPassword.fxml",null);
    } 
}
