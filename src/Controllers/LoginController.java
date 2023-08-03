package Controllers;



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
	private TextField emailId;
	
	@FXML
	private PasswordField password;
	
	public void onLogin() {
		Main m = new Main();
		m.changeScene("sellerDashboard.fxml");
	}

}
