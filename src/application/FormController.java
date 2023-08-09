package application;


import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.DbConnection;

public class FormController implements Initializable {
	FileChooser fileChooser = new FileChooser();
	
	 List<File> selectedFile = new ArrayList<>();
	

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
    private TableView<ActivityData> activityTbl;
 
    @FXML
    private TableColumn<ActivityData, String> buyerName;
    
    @FXML
    private TableColumn<ActivityData, String> message;
    

    @FXML
    private TableColumn<ActivityData, String> status;

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
    private ListView<String> listview;   

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
		|| (RadioButton)PetCategory.getSelectedToggle() == null) {
				
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
				int price=0;
				if(!priceInput.getText().isEmpty()) {
					price = Integer.parseInt(priceInput.getText());
				}

				List<File> allPhotoItems = selectedFile;
	            System.out.println("All Items:");
	            for (File item : allPhotoItems) {
	            	 byte[] fileData = readFileAsBytes(item);
	            }
	            
	           
				String choiceOfSelection = (String)myChoiceBox.getSelectionModel().getSelectedItem();
				//String photoTextBox = listview.getSelectionModel().getSelectedItem();;
				
				//System.out.println(photoTextBox);
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
			
			PetData pd = new PetData(petName,petAge,breed,price, choiceOfSelection,selectedPetCategory,selectedpetSex,allPhotoItems);
			
String sql = "INSERT INTO `petinfo`(`petCategory`,`petName`,`age`,`breed`,`sellerChoice`,`sellerID`,`sex`,`price`, `image`) "

			        + "VALUES ('" + pd.getPetCategory()+ "','" + pd.getPetName()+ "','" + pd.getAge()+ "','" + pd.getBreed()+ "','" 
			+ pd.getChoiceOfSelection()+ "', '10' ,'" + pd.getSex()+"', '" + pd.getPrice()+"','" + pd.getImage()+"')";
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
    private byte[] readFileAsBytes(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputStream.read(buffer);
            inputStream.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        
        
	public void addPetsClear() {
        ObservableList<String> items = FXCollections.observableArrayList();
        listview.setItems(items);
		petNameInput.setText("");
		petAgeInput.setText("");
		petBreedInput.setText("");
		priceInput.setText("");
		items.clear();
		myChoiceBox.getSelectionModel().clearSelection();
		sex.selectToggle(null);
		PetCategory.selectToggle(null);
	}
	
	public void switchForm(ActionEvent event) throws SQLException {
		if(event.getSource() == homeBtn ) {
			
			addPetsShowListTable();
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
		try {
			addPetsShowListTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myChoiceBox.getItems().addAll(userOptions);
		
		myChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if((String)myChoiceBox.getSelectionModel().getSelectedItem()== "Sell") {
				priceLbl.setVisible(true);
				priceInput.setVisible(true);
			}
			else {
				priceLbl.setVisible(false);
				priceInput.setVisible(false);
			}
			});
		try {
			getActivityData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//fetching activity details
	
	  public ObservableList<ActivityData> getActivityData() throws SQLException {

	        ObservableList<ActivityData> activityDataList = FXCollections.observableArrayList();

	        String query = "select * from petbuyer";


	        System.out.println("query " + query);

	        ResultSet resultSet = DbConnection.selectQuery(query);

	        if (resultSet != null) {

	            while (resultSet.next()) {
	            	

	            int petBuyerId = Integer.parseInt(resultSet.getString("id"));
	            String buyerFirstName = resultSet.getString("buyerFirstName");
	            String buyerLastName = resultSet.getString("buyerLastName");
	            int buyerId = Integer.parseInt(resultSet.getString("buyerId"));
	            String status = resultSet.getString("status");
	            String buyerMessage = resultSet.getString("buyerMessage");

System.out.println(buyerMessage);
	           ActivityData activitydata = new ActivityData(petBuyerId,buyerId,buyerMessage,status,buyerFirstName, buyerLastName);            


	           activityDataList.add(activitydata);
	      System.out.println(activitydata);

	           

	        }  

	        }

	        return activityDataList;

	    }
	  
	  //displaying activity data
		private ObservableList<ActivityData> activityTable;
		public void showActivityTable() throws SQLException {
			activityTable = getActivityData();
			buyerName.setCellValueFactory(new PropertyValueFactory<>("buyerFirstName"));
			message.setCellValueFactory(new PropertyValueFactory<>("message"));
			status.setCellValueFactory(new PropertyValueFactory<>("status"));
			activityTbl.setItems(activityTable);
		}
			
    
	
	@FXML
    void uploadImages(MouseEvent event) {
		 fileChooser.setInitialDirectory(new File("C:\\Program Files"));
		 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG Files", "*.png"));
  selectedFile = fileChooser.showOpenMultipleDialog(null);		
		
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


	        //System.out.println("query " + query);

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
	            
	            List<File> allPhotoItems = new ArrayList<>();
	            while (resultSet.next()) {
	                String filePath = resultSet.getString("image");
	                File file = new File(filePath);
	                allPhotoItems.add(file);
	            }
//	        	
//	            for (File file : allPhotoItems) {
//	                System.out.println(file.getAbsolutePath());
//	            }
	           PetData petDataInfo = new PetData(petName,age,breed,price,sellerChoice,petCategory,sex,allPhotoItems);            

	          //  boolean empStatus = dbStatus.equals("0") ? false : true;

	           // justiceDepartmentEmployee.setStatus(empStatus);

	          //  justiceDepartmentEmployee.setId(id);

	       petdataList.add(petDataInfo);
	      // System.out.println(sellerChoice);

	           

	        }  

	        }

	        return petdataList;

	    }
}
