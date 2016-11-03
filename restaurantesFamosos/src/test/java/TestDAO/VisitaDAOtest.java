package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import dao.VisitaDAO;
import factory.DAOFactory;
import model.Restaurante;
import model.Usuario;
import model.Visita;

public class VisitaDAOtest {

	@Test
	public void deveInserirNovaVisita() {
		Visita visita = new Visita();
		visita.setData("T");
		visita.setRestaurante(new Restaurante(1L));
		visita.setUsuario(new Usuario(1L));
		visita.setValorGasto(123.00);
		VisitaDAO visitaDAO = DAOFactory.get().visitaDAO();
		visitaDAO.inserir(visita);
		assertNotNull(visita.getCodigo());
		visitaDAO.excluir(visita.getCodigo());
	}

	@Test
	public void deveAlterarVisita() {
		Visita visita = new Visita();
		visita.setData("T");
		visita.setRestaurante(new Restaurante(1L));
		visita.setUsuario(new Usuario(1L));
		visita.setValorGasto(123.00);
		VisitaDAO visitaDAO = DAOFactory.get().visitaDAO();
		visitaDAO.inserir(visita);
		visita.setData("T alterado");
		//visita.setRestaurante(new Restaurante(2L));
		//visita.setUsuario(new Usuario(2L));
		visita.setValorGasto(12.00);
		visitaDAO.alterar(visita);
		Visita EstadoBanco = visitaDAO.get(visita.getCodigo());
		assertEquals("asdas", EstadoBanco.getData());
		assertEquals("dasd", EstadoBanco.getRestaurante());
		assertEquals("asdasd", EstadoBanco.getUsuario());
		assertEquals("asdasd", EstadoBanco.getValorGasto());
		visitaDAO.excluir(EstadoBanco.getCodigo());
		
		
		
	}
	
	
	
}
