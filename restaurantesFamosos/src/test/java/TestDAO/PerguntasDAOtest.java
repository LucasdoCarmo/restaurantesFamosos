package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.PerguntasDAO;
import factory.DAOFactory;
import model.Perguntas;

public class PerguntasDAOtest {

	@Test
	public void deveInserirPerguntasNovo() {
		Perguntas perguntas = new Perguntas();
		perguntas.setAparencia_externa("bom");
		perguntas.setAparencia_interna("bom");
		perguntas.setBebidas_alcool("sim");
		perguntas.setBeboidas_sem_alcool("nao");
		perguntas.setEspera_mesa("sim");
		perguntas.setLimpeza("bom");
		perguntas.setPagamento("dinheiro");
		perguntas.setQualidade_atendimento("bom");
		perguntas.setTempo_entrega("bom");
		perguntas.setValor_total("adequado");
		perguntas.setVariedade_cardapio("diversos");
		PerguntasDAO PerguntasDAO = DAOFactory.get().perguntasDAO();
		PerguntasDAO.inserir(perguntas);
		assertNotNull(perguntas.getCodigo());
		PerguntasDAO.excluir(perguntas.getCodigo());
	}

	@Test
	public void deveAlterarPerguntas() {
		Perguntas perguntas = new Perguntas();
		perguntas.setAparencia_externa("bom");
		perguntas.setAparencia_interna("bom");
		perguntas.setBebidas_alcool("sim");
		perguntas.setBeboidas_sem_alcool("nao");
		perguntas.setEspera_mesa("sim");
		perguntas.setLimpeza("bom");
		perguntas.setPagamento("dinheiro");
		perguntas.setQualidade_atendimento("bom");
		perguntas.setTempo_entrega("bom");
		perguntas.setValor_total("adequado");
		perguntas.setVariedade_cardapio("diversos");

		PerguntasDAO perguntasDAO = DAOFactory.get().perguntasDAO();
		perguntasDAO.inserir(perguntas);

		perguntas.setAparencia_externa("ruim");
		perguntas.setAparencia_interna("ruim");
		perguntas.setBebidas_alcool("nao");
		perguntas.setBeboidas_sem_alcool("sim");
		perguntas.setEspera_mesa("nao");
		perguntas.setLimpeza("ruim");
		perguntas.setPagamento("cartao");
		perguntas.setQualidade_atendimento("ruim");
		perguntas.setTempo_entrega("ruim");
		perguntas.setValor_total("inadequado");
		perguntas.setVariedade_cardapio("pouco");
		perguntasDAO.alterar(perguntas);

		Perguntas PerguntasBanco = perguntasDAO.get(perguntas.getCodigo());
		assertEquals("ruim", PerguntasBanco.getAparencia_externa());
		assertEquals("ruim", PerguntasBanco.getAparencia_interna());
		assertEquals("nao", PerguntasBanco.getBebidas_alcool());
		assertEquals("sim", PerguntasBanco.getBeboidas_sem_alcool());
		assertEquals("nao", PerguntasBanco.getEspera_mesa());
		assertEquals("ruim", PerguntasBanco.getLimpeza());
		assertEquals("cartao", PerguntasBanco.getPagamento());
		assertEquals("ruim", PerguntasBanco.getQualidade_atendimento());
		assertEquals("ruim", PerguntasBanco.getTempo_entrega());
		assertEquals("inadequado", PerguntasBanco.getValor_total());
		assertEquals("pouco", PerguntasBanco.getVariedade_cardapio());
		perguntasDAO.excluir(PerguntasBanco.getCodigo());
	}

}
