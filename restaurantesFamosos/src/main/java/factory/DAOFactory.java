package factory;

import conexao.ConexaoMysqlProducao;
import dao.PaisDAO;
import dao.PaisJDBC;
import dao.PerguntasDAO;
import dao.PerguntasJDBC;

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

}
