package services;

import java.sql.SQLException;

import Model.Buyer;
import utilities.DbConnection;

public class BuyerService {
	private Buyer buyerService;
	
	public BuyerService(Buyer buyerService) {
		this.buyerService = buyerService;
	}
	
	public void addBuyerToDb()throws SQLException {
		String sql = "INSERT INTO `buyer`(`firstName`, `lastName`, `emailId`, `password`, `city`, `state`)"
				+ "VALUES ('" + buyerService.getfName() + "','" + buyerService.getlName() + "','" + buyerService.getEmailId() + "','" + buyerService.getPassword() + "','" + buyerService.getCity() + "','" +buyerService.getState() + "')";
		DbConnection.query(sql);
	}
	
}
