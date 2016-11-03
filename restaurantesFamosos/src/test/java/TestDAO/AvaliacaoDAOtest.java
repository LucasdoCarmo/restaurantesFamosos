package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import dao.AvaliacaoDAO;
import factory.DAOFactory;
import model.Avaliacao;
import model.Restaurante;

public class AvaliacaoDAOtest {

	@Test
	public void deveInserirAvaliacaoNova() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setNotaAspecto(1);
		avaliacao.setNotaAtendimento(2);
		avaliacao.setNotaComida(3);
		avaliacao.setNotaGeral(4);
		avaliacao.setNotaPagamento(5);
		avaliacao.setAvaliacaoDescritiva("asdasd");
		avaliacao.setRestaurante(new Restaurante(1L));

		AvaliacaoDAO avaliacaodao = DAOFactory.get().avaliacaoDAO();
		avaliacaodao.inserir(avaliacao);
		assertNotNull(avaliacao.getCodigo());
		avaliacaodao.excluir(avaliacao.getCodigo());
	}

	@Test
	public void deveAlterarAvaliacao() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliacaoDescritiva("lugar bom para visitar");
		avaliacao.setNotaAspecto(7);
		avaliacao.setNotaAtendimento(8);
		avaliacao.setNotaComida(9);
		avaliacao.setNotaGeral(4);
		avaliacao.setNotaPagamento(5);
		avaliacao.setRestaurante(new Restaurante(1L));
		AvaliacaoDAO avaliacaodao = DAOFactory.get().avaliacaoDAO();

		avaliacaodao.inserir(avaliacao);

		avaliacao.setAvaliacaoDescritiva("lugar ruim para visitar");
		avaliacao.setNotaAspecto(10);
		avaliacao.setNotaAtendimento(10);
		avaliacao.setNotaComida(10);
		avaliacao.setNotaGeral(10);
		avaliacao.setNotaPagamento(10);
		avaliacao.setRestaurante(new Restaurante(1L));

		avaliacaodao.alterar(avaliacao);
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
