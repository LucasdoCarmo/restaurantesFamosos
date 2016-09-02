package factory;

public class DAOFactory {

	private static DAOFactory factory;
	
	/**
	 * Pega a fabrica de DAO, instância única para todo mundo.
	 * Padrão Singleton simples.
	 * @return
	 */
	public static DAOFactory get(){
		if(factory == null){
			factory = new DAOFactory();
		}
		return factory;
	}	
}
