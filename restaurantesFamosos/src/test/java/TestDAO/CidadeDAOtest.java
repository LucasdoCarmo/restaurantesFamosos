package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.CidadeDAO;
import factory.DAOFactory;
import model.Cidade;
import model.Estado;

public class CidadeDAOtest {

	@Test
	public void deveInserirCidadeNova() {
		Cidade cidade = new Cidade();
		cidade.setNome("Lucas do rio verde");
		cidade.setEstado(new Estado(1L));
		CidadeDAO cidadedao = DAOFactory.get().cidadeDAO();
		cidadedao.inserir(cidade);
		assertNotNull(cidade.getCodigo());
		cidadedao.excluir(cidade.getCodigo());
	}

	@Test
	public void deveAlterarCidade() {
		Cidade cidade = new Cidade();
		cidade.setNome("Xaxim");
		cidade.setEstado(new Estado(1L));
		CidadeDAO cidadedao = DAOFactory.get().cidadeDAO();
		cidadedao.inserir(cidade);
		cidade.setNome("Xaxim alterado");
		cidadedao.alterar(cidade);
		Cidade EstadoBanco = cidadedao.get(cidade.getCodigo());
		assertEquals("Xaxim alterado", EstadoBanco.getNome());
		cidadedao.excluir(EstadoBanco.getCodigo());
	}

}
