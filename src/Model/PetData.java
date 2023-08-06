package Model;

public class PetData {

	private Integer age;
	private String petCategory;
	private String petName;
	private String sex;
	private Integer price;
	private String choiceOfSelection;
	private String breed;
	
	
	public PetData(String petName,Integer age, String breed, Integer price, String choiceOfSelection, String petCategory, String sex) {
		//this.listingId = listingId;
		this.age = age;
		this.choiceOfSelection = choiceOfSelection;
		this.price = price;
		this.petCategory = petCategory;
		this.petName = petName;
		this.sex = sex;
		this.breed = breed;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public String getChoiceOfSelection() {
		return choiceOfSelection;
	}



	public void setChoiceOfSelection(String choiceOfSelection) {
		this.choiceOfSelection = choiceOfSelection;
	}



	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getPetCategory() {
		return petCategory;
	}


	public void setPetCategory(String petCategory) {
		this.petCategory = petCategory;
	}


	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBreed() {
		return breed;
	}


	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	
}
