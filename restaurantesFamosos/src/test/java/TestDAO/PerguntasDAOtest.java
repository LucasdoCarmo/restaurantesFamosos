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

		assertEquals("bom", PerguntasBanco.getAparencia_externa());
		assertEquals("bom", PerguntasBanco.getAparencia_interna());
		assertEquals("sim", PerguntasBanco.getBebidas_alcool());
		assertEquals("nao", PerguntasBanco.getBeboidas_sem_alcool());
		assertEquals("sim", PerguntasBanco.getEspera_mesa());
		assertEquals("bom", PerguntasBanco.getLimpeza());
		assertEquals("dinheiro", PerguntasBanco.getPagamento());
		assertEquals("bom", PerguntasBanco.getQualidade_atendimento());
		assertEquals("bom", PerguntasBanco.getTempo_entrega());
		assertEquals("adequado", PerguntasBanco.getValor_total());
		assertEquals("diversos", PerguntasBanco.getVariedade_cardapio());
		perguntasDAO.excluir(PerguntasBanco.getCodigo());
	}

}
