package Model;

public class ActivityData {

	private Integer petBuyerId;
private Integer buyerId;
private String message;
private String activityStatus;
private String buyerFirstName;
private String buyerLastName;
	
	
	public ActivityData(Integer petBuyerId, Integer buyerId, String message, String activityStatus, String buyerFirstName, String buyerLastName) {
      
		this.petBuyerId = petBuyerId;
		this.buyerId = buyerId;
		this.message = message;
		this.activityStatus = activityStatus;
		this.buyerFirstName = buyerFirstName;
		this.buyerLastName = buyerLastName;
	}


	public String getBuyerLastName() {
		return buyerLastName;
	}


	public void setBuyerLastName(String buyerLastName) {
		this.buyerLastName = buyerLastName;
	}


	public Integer getPetBuyerId() {
		return petBuyerId;
	}


	public String getBuyerName() {
		return buyerFirstName;
	}


	public void setBuyerName(String buyerName) {
		this.buyerFirstName = buyerFirstName;
	}


	public void setPetBuyerId(Integer petBuyerId) {
		this.petBuyerId = petBuyerId;
	}


	public Integer getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getActivityStatus() {
		return activityStatus;
	}


	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	
}
