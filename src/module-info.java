module FurEverHome {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controllers to javafx.fxml;
}
