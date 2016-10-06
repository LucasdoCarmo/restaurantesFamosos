package factory;

import conexao.ConexaoMysqlProducao;
import dao.CepDAO;
import dao.CepJDBC;
import dao.CidadeDAO;
import dao.CidadeJDBC;
import dao.EstadoDAO;
import dao.EstadoJDBC;
import dao.PaisDAO;
import dao.PaisJDBC;
import dao.PerguntasDAO;
import dao.PerguntasJDBC;
import dao.RestauranteDAO;
import dao.RestauranteJDBC;

public class DAOFactory {

	private static DAOFactory factory;

	/**
	 * Pega a fabrica de DAO, instância única para todo mundo. Padrão Singleton
	 * simples.
	 * 
	 * @return
	 */
	public static DAOFactory get() {
		if (factory == null) {
			factory = new DAOFactory();
		}
		return factory;
	}

	public PerguntasDAO perguntasDAO() {
		return new PerguntasJDBC(new ConexaoMysqlProducao());
	}

	public PaisDAO paisDAO() {
		return new PaisJDBC(new ConexaoMysqlProducao());
	}

	public EstadoDAO estadoDAO() {
		return new EstadoJDBC(new ConexaoMysqlProducao());
	}

	public CidadeDAO cidadeDAO() {
		return new CidadeJDBC(new ConexaoMysqlProducao());
	}

	public CepDAO cepDAO() {
		return new CepJDBC(new ConexaoMysqlProducao());
	}

	public RestauranteDAO restauranteDAO() {
		return new RestauranteJDBC(new ConexaoMysqlProducao());
	}

}
