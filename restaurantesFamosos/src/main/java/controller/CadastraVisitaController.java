package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Visita;

public class CadastraVisitaController {

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private DatePicker dpVisita;

	@FXML
	private TextField tfValor;

	@FXML
	private TextField tfNomeRestaurante;

	@FXML
	private TextField tfUsuario;

	@FXML
	private Button btnFinalizar;

	@FXML
	void Salvar(ActionEvent event) {
		criaVisita();
		Alert alert = new Alert(AlertType.CONFIRMATION, "Avaliação efetuada com sucesso!!", ButtonType.CLOSE);
		alert.show();
		AbreTela("TelaVazia.fxml");

	}

	private Visita criaVisita() {
		Visita visita = new Visita();
		visita.setUsuario(tfUsuario.getText());
		visita.setRestaurante(tfNomeRestaurante.getText());
		visita.setData(dpVisita.getValue().toString());
		visita.setValorGasto(Double.valueOf(tfValor.getText()));

		return visita;
	}

	public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" + tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelSecundario.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
