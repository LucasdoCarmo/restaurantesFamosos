package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.RestauranteDAO;
import factory.DAOFactory;
import model.CEP;
import model.Restaurante;

public class RestauranteDAOtest {

	@Test
	public void deveInserirRestauraneNova() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("comi");
		restaurante.setCep(new CEP(1L));
		restaurante.setNumero("12");
		restaurante.setRua("Fernando de Noronha");
		restaurante.setTelefone("3436-0965");
		restaurante.setTema("luxo");
		restaurante.setTipo("pizzaria");
		RestauranteDAO restaurantedao = DAOFactory.get().restauranteDAO();
		restaurantedao.inserir(restaurante);
		assertNotNull(restaurante.getCodigo());
		restaurantedao.excluir(restaurante.getCodigo());
	}

	@Test
	public void deveAlterarRestaurante() {
		Restaurante restaurane = new Restaurante();
		restaurane.setNome("comi");
		restaurane.setCep(new CEP(1L));
		restaurane.setNumero("12");
		restaurane.setRua("Fernando de Noronha");
		restaurane.setTelefone("3436-0965");
		restaurane.setTema("luxo");
		restaurane.setTipo("pizzaria");

		RestauranteDAO restaurantedao = DAOFactory.get().restauranteDAO();

		restaurantedao.inserir(restaurane);
		restaurane.setNome("comi alterado");
		restaurane.setCep(new CEP(1L));
		restaurane.setNumero("24");
		restaurane.setRua("joão pessoa");
		restaurane.setTelefone("8906-5634");
		restaurane.setTema("ar livre");
		restaurane.setTipo("sorveteria");
		restaurantedao.alterar(restaurane);

		Restaurante RestauranteBanco = restaurantedao.get(restaurane.getCodigo());

		assertEquals("comi alterado", RestauranteBanco.getNome());
		assertEquals("24", RestauranteBanco.getNumero());
		assertEquals("joão pessoa", RestauranteBanco.getRua());
		assertEquals("8906-5634", RestauranteBanco.getTelefone());
		assertEquals("ar livre", RestauranteBanco.getTema());
		assertEquals("sorveteria", RestauranteBanco.getTipo());

		restaurantedao.excluir(RestauranteBanco.getCodigo());
	}

}
