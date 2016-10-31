package TestDAO;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.RespostasDAO;
import factory.DAOFactory;
import model.Perguntas;
import model.Respostas;

public class RespostasDAOtest {

	@Test
	public void deveInserirRespostasNova() {
		Respostas resposta = new Respostas();
		resposta.setPerguntas(new Perguntas(1L));
		RespostasDAO respostaDAO = DAOFactory.get().respostasDAO();
		respostaDAO.inserir(resposta);
		assertNotNull(resposta.getCodigo());
		// respostaDAO.excluir(resposta.getCodigo());
	}

}
