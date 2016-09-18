package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.PaisDAO;
import factory.DAOFactory;
import model.Pais;

public class PaisDAOtest {

	@Test
	public void deveInserirPaisNovo() {
		Pais pais = new Pais();
		pais.setNome("São Paulo");
		PaisDAO PaisDAO = DAOFactory.get().paisDAO();
		PaisDAO.inserir(pais);
		assertNotNull(pais.getCodigo());
		PaisDAO.excluir(pais.getCodigo());
	}

	@Test
	public void deveAlterarPais() {
		Pais pais = new Pais();
		pais.setNome("Rio de Janeiro");
		PaisDAO PaisDAO = DAOFactory.get().paisDAO();
		PaisDAO.inserir(pais);
		pais.setNome("Rio Grande do Sul");
		PaisDAO.alterar(pais);
		Pais PaisBanco = PaisDAO.get(pais.getCodigo());
		assertEquals("Rio Grande do Sul", PaisBanco.getNome());
		PaisDAO.excluir(PaisBanco.getCodigo());
	}

}
