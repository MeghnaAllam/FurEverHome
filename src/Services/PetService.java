package Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.PetData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DbConnection;

public class PetService {
	
	public PetService() {
		
	}
	
	public ObservableList<PetData> fetchBuyerPetDashboardInfo() throws SQLException {
		ObservableList<PetData> petDataList = FXCollections.observableArrayList();
		String query = "select * from petinfo";
		System.out.println("query " + query);
		ResultSet resultSet = DbConnection.selectQuery(query);
		if(resultSet != null) {
			while(resultSet.next()) {
				int petId = Integer.parseInt(resultSet.getString("id"));
				Integer age = Integer.parseInt(resultSet.getString("age"));
				String petCategory = resultSet.getString("petCategory");
				String petName = resultSet.getString("petName");
				String sex = resultSet.getString("sex");
				Integer price = (resultSet.getString("price") != null)?( Integer.parseInt(resultSet.getString("price"))): 0;
				String choiceOfSelection = resultSet.getString("sellerChoice");
				String breed = resultSet.getString("breed");
				int sellerId = Integer.parseInt(resultSet.getString("sellerId"));
				PetData pd = new PetData(petName, age, breed, price, choiceOfSelection, petCategory, sex);
				pd.setPetId(petId);
				pd.setSellerId(sellerId);
				petDataList.add(pd);
			}
		}
		return petDataList;
	}

}
