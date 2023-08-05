package services;

import application.petData;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.petData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DbConnection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class petInfoDbConnect {
	
	private Connection connect;
	private Statement statement;
	private ResultSet resultset;
	private PreparedStatement prepare;
	//private DbConnection database;
	
	  public ArrayList<petData> fetchJusticeDeptEmpData(int listingId, int age, String petCategory,String petName, String sex, String breed) throws SQLException {

	        ArrayList<petData> petdataList = new ArrayList<petData>();

	        String query = "select * from petinfo";


	        System.out.println("query" + query);

	        ResultSet resultSet = DbConnection.selectQuery(query);

	        if (resultSet != null) {

	            while (resultSet.next()) {

	            String name = resultSet.getString("petNameInput");

	            int petAge = Integer.parseInt(resultSet.getString("petAgeInput"));

	            String typeOfChoice = resultSet.getString("myChoiceBox");

	            String petSex = resultSet.getString("sex");
	            String petBreed = resultSet.getString("breedInput");


	            petData petDataInfo = new petData(petAge, typeOfChoice,name, petSex, petBreed);            

	          //  boolean empStatus = dbStatus.equals("0") ? false : true;

	           // justiceDepartmentEmployee.setStatus(empStatus);

	          //  justiceDepartmentEmployee.setId(id);

	            petdataList.add(petDataInfo);

	           

	        }  

	        }

	        return petdataList;

	    }
	  
	  
	  
	  

	}



