package TestDAO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.AvaliacaoDAO;
import factory.DAOFactory;
import model.Avaliacao;

public class AvaliacaoDAOtest {

	/*
	 * @Test public void deveInserirAvaliacaoNova() { Avaliacao avaliacao = new
	 * Avaliacao(); avaliacao.setAvaliacaoDescritiva("lugar bom para visitar");
	 * avaliacao.setNotaAspecto(7); avaliacao.setNotaAtendimento(8);
	 * avaliacao.setNotaComida(9); avaliacao.setNotaGeral(6);
	 * avaliacao.setNotaPagamento(5); AvaliacaoDAO avaliacaodao =
	 * DAOFactory.get().avaliacaoDAO(); avaliacaodao.inserir(avaliacao);
	 * assertNotNull(avaliacao.getCodigo());
	 * avaliacaodao.excluir(avaliacao.getCodigo()); }
	 */
	@Test
	public void deveAlterarAvaliacao() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliacaoDescritiva("lugar bom para visitar");
		avaliacao.setNotaAspecto(7);
		avaliacao.setNotaAtendimento(8);
		avaliacao.setNotaComida(9);
		avaliacao.setNotaGeral(6);
		avaliacao.setNotaPagamento(5);
		AvaliacaoDAO avaliacaodao = DAOFactory.get().avaliacaoDAO();

		avaliacaodao.alterar(avaliacao);

		avaliacao.setAvaliacaoDescritiva("lugar ruim para visitar");
		avaliacao.setNotaAspecto(10);
		avaliacao.setNotaAtendimento(10);
		avaliacao.setNotaComida(10);
		avaliacao.setNotaGeral(10);
		avaliacao.setNotaPagamento(10);

		Avaliacao AvaliacaoBanco = avaliacaodao.get(avaliacao.getCodigo());

		assertEquals("10", AvaliacaoBanco.getNotaAspecto());
		assertEquals("10", AvaliacaoBanco.getNotaAtendimento());
		assertEquals("10", AvaliacaoBanco.getNotaComida());
		assertEquals("10", AvaliacaoBanco.getNotaGeral());
		assertEquals("10", AvaliacaoBanco.getNotaPagamento());
		assertEquals("lugar ruim para visitar", AvaliacaoBanco.getAvaliacaoDescritiva());

		avaliacaodao.excluir(avaliacao.getCodigo());
	}

}
