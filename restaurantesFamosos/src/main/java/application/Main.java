package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;


public class Main extends Application {
	public final static String PATH_VIEW = "../view/";

	@Getter
	private static AnchorPane rootLayout;
	@Getter
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Main.rootLayout = (AnchorPane) FXMLLoader.load(getClass().getResource(PATH_VIEW + "Login.fxml"));

			primaryStage.setTitle("Restaurantes Famosos");
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
