package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.CepDAO;
import factory.DAOFactory;
import model.CEP;
import model.Cidade;

public class CEPDAOtest {

	@Test
	public void deveInserirCepNova() {
		CEP cep = new CEP();
		cep.setCep("89694-000");
		cep.setCidade(new Cidade(1L));
		CepDAO cepdao = DAOFactory.get().cepDAO();
		cepdao.inserir(cep);
		assertNotNull(cep.getCodigo());
		cepdao.excluir(cep.getCodigo());
	}

	@Test
	public void deveAlterarCep() {
		CEP cep = new CEP();
		cep.setCep("89694-000");
		cep.setCidade(new Cidade(1L));
		CepDAO cepdao = DAOFactory.get().cepDAO();
		cepdao.inserir(cep);
		cep.setCep("89694-111");
		cepdao.alterar(cep);
		CEP cepBanco = cepdao.get(cep.getCodigo());
		assertEquals("89694-111", cepBanco.getCep());
		cepdao.excluir(cepBanco.getCodigo());
	}

}
