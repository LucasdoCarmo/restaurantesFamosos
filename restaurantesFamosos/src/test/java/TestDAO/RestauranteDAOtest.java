package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import dao.RestauranteDAO;
import factory.DAOFactory;
import model.Cidade;
import model.Restaurante;

public class RestauranteDAOtest {

	@Test
	public void deveInserirRestauraneNova() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("comi");
		restaurante.setTelefone("3436-0965");
		restaurante.setTipo("pizzaria");
		restaurante.setRua("Fernando de Noronha");
		restaurante.setNumero("12");
		restaurante.setTema("luxo");
		restaurante.setCidade(new Cidade(1L));
		RestauranteDAO restaurantedao = DAOFactory.get().restauranteDAO();
		restaurantedao.inserir(restaurante);
		assertNotNull(restaurante.getCodigo());
		restaurantedao.excluir(restaurante.getCodigo());
	}

	@Test
	public void deveAlterarRestaurante() {
		Restaurante restaurane = new Restaurante();
		restaurane.setNome("comi");
		restaurane.setNumero("12");
		restaurane.setRua("Fernando de Noronha");
		restaurane.setTelefone("3436-0965");
		restaurane.setTema("luxo");
		restaurane.setTipo("pizzaria");
		restaurane.setCidade(new Cidade(1L));

		RestauranteDAO restaurantedao = DAOFactory.get().restauranteDAO();

		restaurantedao.inserir(restaurane);
		restaurane.setNome("comi alterado");
		restaurane.setTelefone("8906-5634");
		restaurane.setTipo("sorveteria");
		restaurane.setRua("joão pessoa");
		restaurane.setNumero("24");
		restaurane.setTema("ar livre");
		restaurane.setCidade(new Cidade(1L));

		restaurantedao.alterar(restaurane);

		Restaurante RestauranteBanco = restaurantedao.get(restaurane.getCodigo());

		assertEquals("comi alterado", RestauranteBanco.getNome());
		assertEquals("8906-5634", RestauranteBanco.getTelefone());
		assertEquals("sorveteria", RestauranteBanco.getTipo());
		assertEquals("joão pessoa", RestauranteBanco.getRua());
		assertEquals("24", RestauranteBanco.getNumero());
		assertEquals("ar livre", RestauranteBanco.getTema());
		assertEquals("1", RestauranteBanco.getCidade());

		restaurantedao.excluir(RestauranteBanco.getCodigo());
	}

}
