package dao;

import java.util.List;

import javafx.collections.ObservableList;
import model.Cidade;
import model.Restaurante;

public interface RestauranteDAO extends CrudDAO<Restaurante>{

	List<Restaurante> getPorNome(String nome);
	List<Restaurante> getPorCidade(Cidade cidade);
	List<Restaurante> getPorTipo(String tipo);
	ObservableList<Restaurante> todosTabela();
}
