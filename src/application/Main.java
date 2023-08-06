package application;
	
import java.io.IOException;

import Controllers.BuyerController;
import Model.Buyer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stage;

	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/UI/login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeScene(String fxmlFile, Object o) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/" + fxmlFile));
			Parent root = loader.load();
			if (o instanceof Buyer) {
				BuyerController bc = loader.getController();
				bc.initData(o);
			}
			stage.getScene().setRoot(root);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
