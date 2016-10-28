package dao;

import java.util.List;
import model.Restaurante;

public interface RestauranteDAO extends CrudDAO<Restaurante>{

	List<Restaurante> getPorTipo(String tipo);
	List<Restaurante> getPorTema(String tema);
	List<Restaurante> getPorNome(String nome);
}
