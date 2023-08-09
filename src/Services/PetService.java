package Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.PetBuyer;
import Model.PetData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.Constants;
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
	
	public void addBuyerInterest(String msg, int petId, int buyerId) {
		String sql = "INSERT INTO `petbuyer`(`id`, `buyerId`, `status`, `buyerMessage`)"
				+ "VALUES ('" + petId + "','" + buyerId + "','" + Constants.PENDING + "','" + msg + "')";
		System.out.println("query " + sql);
		DbConnection.query(sql);
	}
	

	public ObservableList<PetData> fetchBuyerActivityData(int buyerId) throws SQLException{
		ObservableList<PetData> petDataList = FXCollections.observableArrayList();
		String query = "SELECT pi.*\r\n"
				+ "FROM petinfo pi\r\n"
				+ "JOIN petbuyer pb ON pi.id = pb.id\r\n"
				+ "WHERE pb.buyerId =" + buyerId+ ";";
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
	
	public PetBuyer retrievePetBuyerData(int petId, int buyerId) throws NumberFormatException, SQLException {
		PetBuyer pb = null;
		String sql = "select * from petbuyer where id = "+petId+" and buyerId = "+buyerId+";";
		System.out.println(sql);
		ResultSet resultSet = DbConnection.selectQuery(sql);
		if(resultSet != null) {
			while(resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				int bId = Integer.parseInt(resultSet.getString("buyerId"));
				String msg = resultSet.getString("buyerMessage");
				String status = resultSet.getString("status");
				
				pb = new PetBuyer(id, bId, msg, status);
				return pb;
			}
		}
		return pb;
		
	}

}
