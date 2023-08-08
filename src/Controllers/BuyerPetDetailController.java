package Controllers;

import Model.PetData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class BuyerPetDetailController {
	
	@FXML
	private Label nameLbl;
	
	@FXML
	private Label ageLbl;
	
	@FXML
	private Label categoryLbl;
	
	@FXML
	private Label breedLbl;
	
	@FXML
	private Label sexLbl;
	
	@FXML
	private Label priceLbl;
	
	@FXML
	private Label price;
	
	@FXML
	private Button adoptAndBuy;
	
	@FXML
	private AnchorPane messagePane;
	
	private PetData petData;
	
	public void initData(PetData petData) {
		this.petData = petData;
		messagePane.setVisible(false);
		setPetData();
		setPriceVisibility();
		showMessagePane();
	}
	
	public void setPetData() {
		priceLbl.setText(petData.getPrice() + "");
		sexLbl.setText(petData.getSex());
		breedLbl.setText(petData.getBreed());
		categoryLbl.setText(petData.getPetCategory());
		ageLbl.setText(petData.getAge() + "");
		nameLbl.setText(petData.getPetName());
	}

	public void setPriceVisibility() {
		if(petData.getPrice() == 0) {
			priceLbl.setVisible(false);
			price.setVisible(false);
			adoptAndBuy.setText("Adopt");
		}else {
			adoptAndBuy.setText("Buy");
		}
	}
	
	public void showMessagePane() {
		adoptAndBuy.setOnMouseClicked(event ->{
			if(event.getButton()== MouseButton.PRIMARY) {
				messagePane.setVisible(true);
			}
		});
			
		
	}
}