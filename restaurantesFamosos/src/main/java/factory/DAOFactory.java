package factory;

import conexao.ConexaoMysqlProducao;
import dao.AvaliacaoDAO;
import dao.AvaliacaoJDBC;
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
import dao.UsuarioDAO;
import dao.UsuarioJDBC;

public class DAOFactory {

	private static DAOFactory factory;

	/**
	 * Pega a fabrica de DAO, instancia unica para todo mundo. Padrao
	 * Singleton simples.
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

	public RestauranteDAO restauranteDAO() {
		return new RestauranteJDBC(new ConexaoMysqlProducao());
	}

	public AvaliacaoDAO avaliacaoDAO() {
		return new AvaliacaoJDBC(new ConexaoMysqlProducao());
	}

	public UsuarioDAO usuarioDAO() {
		return new UsuarioJDBC(new ConexaoMysqlProducao());
	}


}
