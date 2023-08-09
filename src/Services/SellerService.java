package Services;

import java.sql.SQLException;

import Model.Seller;
import utilities.DbConnection;

public class SellerService {
	private Seller sellerService;
	
	public SellerService(Seller sellerService) {
		this.sellerService = sellerService;
	}
	
	public void addSellerToDb()throws SQLException {
		String sql = "INSERT INTO `seller`(`firstName`, `lastName`, `emailId`, `password`, `city`, `state`)"
				+ "VALUES ('" + sellerService.getfName() + "','" + sellerService.getlName() + "','" + sellerService.getEmailId() + "','" + sellerService.getPassword() + "','" + sellerService.getCity() + "','" + sellerService.getState() + "')";
		DbConnection.query(sql);
	}
	
}