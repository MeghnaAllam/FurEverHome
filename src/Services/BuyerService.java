package Services;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Buyer;
import utilities.DbConnection;

public class BuyerService {
	private Buyer buyerService;
	
	public BuyerService(Buyer buyerService) {
		this.buyerService = buyerService;
	}
	
	public BuyerService() {
		// TODO Auto-generated constructor stub
	}

	public void addBuyerToDb()throws SQLException {
		String sql = "INSERT INTO `buyer`(`firstName`, `lastName`, `emailId`, `password`, `city`, `state`)"
				+ "VALUES ('" + buyerService.getfName() + "','" + buyerService.getlName() + "','" + buyerService.getEmailId() + "','" + buyerService.getPassword() + "','" + buyerService.getCity() + "','" +buyerService.getState() + "')";
		DbConnection.query(sql);
	}
	
	public void setStatus(ArrayList<Integer> arrList, String status, int petId) {
		if (arrList != null && arrList.size() > 0) {
			String sql = "update petbuyer set status = " + status + " where id = "+ petId + " and ";
			for(int i=0; i<arrList.size(); i++) {
				sql += "buyerId = " + arrList.get(i);
				if(i != arrList.size()-1) {
					sql += ",";
				}
			}
			System.out.println(sql);
		}
	}
	
}
