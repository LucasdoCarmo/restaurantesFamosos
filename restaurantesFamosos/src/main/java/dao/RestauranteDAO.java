package dao;

import java.util.List;

import javafx.collections.ObservableList;
import model.Cidade;
import model.Restaurante;

public interface RestauranteDAO extends CrudDAO<Restaurante>{

	List<Restaurante> getPorTipo(Restaurante restaurante);
	List<Restaurante> getPorTema(String tema);
	List<Restaurante> getPorNome(String nome);
	List<Restaurante> getPorCidade(Cidade cidade);
	ObservableList<Restaurante> todosTabela();
	
}
