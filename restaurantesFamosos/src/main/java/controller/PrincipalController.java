package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class PrincipalController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private Button btnSair;

	@FXML
	private MenuItem btnBuscar;

	@FXML
	private MenuItem menuNovo;
	
    @FXML
    private MenuItem menuUsuarioNovo;

	@FXML
	private MenuItem menuEditar;

	@FXML
	private MenuItem btnRelatorio;

	@FXML
	void Buscar(ActionEvent event) {
		AbreTela("Buscar.fxml");
	}

	//Cadastra novo restaurante
	@FXML
	void CadastrarNovo(ActionEvent event) {
		AbreTela("CadastraRestaurante.fxml");

	}

	//Cadastra novo Usuario
	@FXML
	void NovoUsuario(ActionEvent event){
		AbreTela("CadastraUsuario.fxml");
	}
	
	@FXML
	void EditarPerfil(ActionEvent event) {
		AbreTela("EditaPerfil.fxml");
	}

	@FXML
	void GerarRelatorio(ActionEvent event) {
		AbreTela("Relatorio.fxml");
	}

	@FXML
	void Sair(ActionEvent event) {
		AbreTela("Login.fxml");

	}

	// public void AbreTela(ActionEvent event, String tela) {
	// //ActionEvent event = null;
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(getClass().getResource(Main.PATH_VIEW + tela));
	// Node node = (Node) event.getSource();
	// Stage stage = (Stage) node.getScene().getWindow();
	// Parent root = null;
	// try {
	// AnchorPane produtoView = (AnchorPane) loader.load();
	// Scene scene = new Scene(produtoView);
	// scene.getStylesheets().add("/css/style.css");
	// stage.setScene(scene);
	//
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }

	public void AbreTela(String tela) {
		stack.getChildren().clear();
		stack.getChildren().add(getNode(tela));
	}

	public Node getNode(String node) {
		Node no = null;
		try {
			no = FXMLLoader.load(getClass().getResource(Main.PATH_VIEW + node));
		} catch (Exception e) {
		}
		return no;
	}

	// tela.setTitle("Segunda janela");
	// StackPane sp = new StackPane();
	//// Scene sc = new Scene();
	//// tela.setScene(sc);
	// tela.show();

	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(getClass().getResource(Main.PATH_VIEW + tela));
	// try {
	// AnchorPane produtoView = (AnchorPane) loader.load();
	// panelPrincipal.setCenter(produtoView);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// }
}
