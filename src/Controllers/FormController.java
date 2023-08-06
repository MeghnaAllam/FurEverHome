package Controllers;

import Services.petInfoDbConnect;


import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.PetData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.DbConnection;

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
    private TableColumn<PetData, String> petAgetb;

    @FXML
    private TextField petAgeInput;

    @FXML
    private Label petAgeLbl;

    @FXML
    private TableColumn<PetData, String> petBreedtb;
    
    
    @FXML
    private TableColumn<PetData, String> petCategorytb;

    @FXML
    private Label petCategoryLabel;


    @FXML
    private TableColumn<PetData, String> petNametb;

    @FXML
    private TableColumn<PetData, String> petSextb;
    
    @FXML
    private TextField petNameInput;

    @FXML
    private Label petNameLabel;

   

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
    private TableView<PetData> showHome;

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
    private Button activityBtn;
    

    @FXML
    private TextField petBreedInput;

    @FXML
    private Button addPetsBtn;
    @FXML
    private Button homeBtn;
    

    @FXML
	public Button logoutBtn;
    
    
	@FXML
	private ListView listview;
	@FXML
	private ChoiceBox<String> myChoiceBox;
	
	private String[] userOptions = {"Donate", "Sell"};
	
	public void onSubmitbtn(ActionEvent event) {
		
		try {
		
			
			if(petNameInput.getText().isEmpty()
					||petAgeInput.getText().isEmpty()
					||petBreedInput.getText().isEmpty()
		|| myChoiceBox.getSelectionModel().getSelectedItem() == null
		|| (RadioButton)sex.getSelectedToggle() == null
		|| (RadioButton)PetCategory.getSelectedToggle() == null
		|| priceInput.getText().isEmpty()) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all the fields in the form to proceed");
				alert.showAndWait();
				//clears the form
				addPetsClear();
			}
			
			else {

				String petName = petNameInput.getText();
				int petAge = Integer.parseInt(petAgeInput.getText());
				String breed = petBreedInput.getText();
				int price = Integer.parseInt(priceInput.getText());
				String choiceOfSelection = (String)myChoiceBox.getSelectionModel().getSelectedItem();
				String selectedpetSex=null;
				RadioButton selectedRadioButton = (RadioButton)sex.getSelectedToggle(); 
				if (selectedRadioButton != null) {
					selectedpetSex = selectedRadioButton.getText();
				}
				String selectedPetCategory=null;
				RadioButton rb = (RadioButton)PetCategory.getSelectedToggle(); 
				if (selectedRadioButton != null) {
					selectedPetCategory = selectedRadioButton.getText();
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully Added");
				alert.showAndWait();
				
				//clears the form
				addPetsClear();
				
				//show in the table
				addPetsShowListTable();
			

			PetData pd = new PetData(petName,petAge,breed,price, choiceOfSelection,selectedPetCategory,selectedpetSex);
			
String sql = "INSERT INTO `petinfo`(`petCategory`,`petName`,`age`,`breed`,`sellerChoice`,`sellerID`,`sex`,`price`) "

			        + "VALUES ('" + pd.getPetCategory()+ "','" + pd.getPetName()+ "','" + pd.getAge()+ "','" + pd.getBreed()+ "','" 
			+ pd.getChoiceOfSelection()+ "', '10' ,'" + pd.getSex()+"', '" + pd.getPrice()+"')";
System.out.println(sql);
        DbConnection.query(sql);   

		}	
		}	

	    
		catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all the information correctly");
			alert.showAndWait();
			addPetsClear();
			}
		
		catch(Exception e) {
			e.printStackTrace();
			addPetsClear();
		}
	
}
	
	public void addPetsClear() {
		petNameInput.setText("");
		petAgeInput.setText("");
		petBreedInput.setText("");
		priceInput.setText("");
		myChoiceBox.getSelectionModel().clearSelection();
		sex.selectToggle(null);
		PetCategory.selectToggle(null);
	}
	
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
	
	private ObservableList<PetData> addPetsList;
public void addPetsShowListTable() throws SQLException {
	addPetsList = fetchPetDataList();
	petCategorytb.setCellValueFactory(new PropertyValueFactory<>("petCategory"));
	petNametb.setCellValueFactory(new PropertyValueFactory<>("petName"));
	petBreedtb.setCellValueFactory(new PropertyValueFactory<>("breed"));
	petAgetb.setCellValueFactory(new PropertyValueFactory<>("age"));
	petSextb.setCellValueFactory(new PropertyValueFactory<>("sex"));
	showHome.setItems(addPetsList);
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
	
	
	  public ObservableList<PetData> fetchPetDataList() throws SQLException {

	        ObservableList<PetData> petdataList = FXCollections.observableArrayList();

	        String query = "select * from petinfo";


	        System.out.println("query " + query);

	        ResultSet resultSet = DbConnection.selectQuery(query);

	        if (resultSet != null) {

	            while (resultSet.next()) {
	            	

	            String petName = resultSet.getString("petName");
	            int price = Integer.parseInt(resultSet.getString("price"));

	            int age = Integer.parseInt(resultSet.getString("age"));
	            String petCategory = resultSet.getString("petCategory");

	            String sellerChoice = resultSet.getString("sellerChoice");

	            String sex = resultSet.getString("sex");
	            String breed = resultSet.getString("breed");


	           PetData petDataInfo = new PetData(petName,age,breed,price,sellerChoice,petCategory,sex);            

	          //  boolean empStatus = dbStatus.equals("0") ? false : true;

	           // justiceDepartmentEmployee.setStatus(empStatus);

	          //  justiceDepartmentEmployee.setId(id);

	       petdataList.add(petDataInfo);
	       System.out.println(sellerChoice);

	           

	        }  

	        }

	        return petdataList;

	    }
}
