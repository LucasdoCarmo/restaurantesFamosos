package model;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Auxiliar {

	
	public void AbreTela(ActionEvent event, String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + tela));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			AnchorPane produtoView = (AnchorPane) loader.load();
			Scene scene = new Scene(produtoView);
			scene.getStylesheets().add("/css/style.css");
			stage.setScene(scene);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
