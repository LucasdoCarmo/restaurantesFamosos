package dao;

import java.util.Collection;
import javafx.collections.ObservableList;
import model.Restaurante;

public interface RestauranteDAO extends CrudDAO<Restaurante> {

	Collection<Restaurante> getPorNome(String nome);

	Collection<Restaurante> getPorCidade(String cidade);

	Collection<Restaurante> getPorTipo(String tipo);

	Collection<Restaurante> getPorTema(String tema);

	ObservableList<Restaurante> todosTabela();

}
