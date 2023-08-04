package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormController implements Initializable {
	FileChooser fileChooser = new FileChooser();

    @FXML
    private RadioButton birds;

    @FXML
    private RadioButton cats;

    @FXML
    private RadioButton dogs;

    @FXML
    private RadioButton female;
    
    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup PetCategory;
    
    
    @FXML
    private TableColumn<?, ?> petAge;

    @FXML
    private TextField petAgeInput;

    @FXML
    private Label petAgeLbl;

    @FXML
    private TableColumn<?, ?> petBreed;

    @FXML
    private TableColumn<?, ?> petCategory;

    @FXML
    private Label petCategoryLabel;

    @FXML
    private TableColumn<?, ?> petId;

    @FXML
    private TableColumn<?, ?> petName;

    @FXML
    private TextField petNameInput;

    @FXML
    private Label petNameLabel;

    @FXML
    private TableColumn<?, ?> petSex;

    @FXML
    private TextField priceInput;

    @FXML
    private Label priceLbl;

    @FXML
    private ToggleGroup sex;

    @FXML
    private Label sexLbl;

    @FXML
    private AnchorPane showActivity;

    @FXML
    private TableView<?> showHome;

    @FXML
    private Button submitBtn;

    @FXML
    private Button uploadPicturesBtn;

    @FXML
    private Label uploadPicturesLbl;
    
    


    @FXML
    private AnchorPane addPetsPage;
    


    @FXML
    private AnchorPane homePage;

    @FXML
    private TableColumn<?, ?> listingId;

    @FXML
    private Button activityBtn;

    @FXML
    private Button addPetsBtn;
    @FXML
    private Button homeBtn;
    

    @FXML
    private Button logoutBtn;
    
    
	@FXML
	private ListView listview;
	@FXML
	private ChoiceBox<String> myChoiceBox;
	
	private String[] userOptions = {"Donate", "Sell"};
	
	
	
	public void switchForm(ActionEvent event) {
		if(event.getSource() == homeBtn ) {
			
			showHome.setVisible(true);
			addPetsPage.setVisible(false);
			showActivity.setVisible(false);
		}
		else if(event.getSource() == addPetsBtn) {
			showHome.setVisible(false);
			addPetsPage.setVisible(true);
			showActivity.setVisible(false);
		}
		
		else if(event.getSource() == activityBtn) {
			showHome.setVisible(false);
			addPetsPage.setVisible(false);
			showActivity.setVisible(true);
		}
	}
	

	private double x= 0;
	private double y=0;
	
	public void logout() {
		
		try {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout");
		
		Optional<ButtonType> option = alert.showAndWait();
		
		if(option.get().equals(ButtonType.OK)) {
			
			homePage.getScene().getWindow().hide();
			
			Parent root = FXMLLoader.load(getClass().getResource("/UI/register.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			root.setOnMousePressed((MouseEvent event) -> {
				x= event.getSceneX();
				y= event.getSceneY();
			});
			
			root.setOnMouseDragged((MouseEvent event) -> {
				stage.setX(event.getSceneX() -x);
				stage.setY(event.getSceneY() -y);
				
				stage.setOpacity(.8);
			});
			
			root.setOnMouseReleased((MouseEvent event) -> {
				stage.setOpacity(1);
			});
			
			stage.initStyle(StageStyle.TRANSPARENT);
			
			stage.setScene(scene);
			stage.show();
		}
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myChoiceBox.getItems().addAll(userOptions);
		
	}
	
	@FXML
    void uploadImages(MouseEvent event) {
		 fileChooser.setInitialDirectory(new File("C:\\Program Files"));
		 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG Files", "*.png"));
  List<File> selectedFile = fileChooser.showOpenMultipleDialog(null);		
		
		if(selectedFile!=null) {
			for(int i=0;i<selectedFile.size();i++) {
			listview.getItems().add(selectedFile.get(i).getAbsolutePath());
		}
		}
		else {
			System.out.println("File is not valid");
		}

}
}
