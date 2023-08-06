package Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Buyer;
import Model.PetData;
import Services.PetService;
import application.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BuyerController implements Initializable {
	
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
	
	@FXML
	private TableView<PetData> tableView;
	
	@FXML
	private TableColumn<PetData, String> petCategoryColumn;
	
	@FXML
	private TableColumn<PetData, String> petNameColumn;
	
	@FXML
	private TableColumn<PetData, String> breedColumn;
	
	@FXML
	private TableColumn<PetData, String> ageColumn;
	
	@FXML
	private TableColumn<PetData, String> sexColumn;
	
	private ObservableList<PetData> petDetailsList;
	
	
	private Buyer buyer;
	
	public void initData(Object obj) {
		this.buyer = (Buyer) obj;
		System.out.println("Buyer details " + buyer.getfName());
		getAllPetDetails();
		fillHomePetTable();
		
	}
	
	public void onMyActivity() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/UI/buyerActivityPane.fxml"));
		mainPane.setCenter(root);
	}
	
	public void onHome() throws IOException {
		mainPane.setCenter(homePane);
		fillHomePetTable();
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
			m.changeScene("login.fxml", null);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {		
		
	}
	
	public void fillHomePetTable() {
		petCategoryColumn.setCellValueFactory(new PropertyValueFactory<PetData, String>("petCategory"));
		petNameColumn.setCellValueFactory(new PropertyValueFactory<PetData, String>("petName"));
		breedColumn.setCellValueFactory(new PropertyValueFactory<PetData, String>("breed"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<PetData, String>("age"));
		sexColumn.setCellValueFactory(new PropertyValueFactory<PetData, String>("sex"));
		
		if (petDetailsList != null && petDetailsList.size() > 0) {
			tableView.setItems(petDetailsList);
		}
	}
	
	
	public void getAllPetDetails() {
		if(buyer != null) {
			try {
				PetService ps = new PetService();
				petDetailsList = ps.fetchBuyerPetDashboardInfo();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
