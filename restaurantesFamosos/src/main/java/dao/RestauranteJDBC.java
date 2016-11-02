package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import conexao.Conexao;
import javafx.collections.ObservableList;
import model.Cidade;
import model.Restaurante;

public class RestauranteJDBC implements RestauranteDAO {

	private Conexao conexao;

	public RestauranteJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Restaurante objeto) {
		String insert = "insert into restaurante (Nome,Telefone,Tipo_de_estabelicimento,Rua,Numero,Tema, idCidade) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getTelefone());
			ps.setString(3, objeto.getTipo());
			ps.setString(4, objeto.getRua());
			ps.setString(5, objeto.getNumero());
			ps.setString(6, objeto.getTema());
			ps.setLong(7, objeto.getCidade().getCodigo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			objeto.setCodigo(rs.getLong(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void alterar(Restaurante objeto) {
		String update = "update restaurante set Nome=?,Telefone=?,Tipo_de_estabelicimento=?,Rua =?,Numero=?,Tema=?, idCidade=? where idRestaurante = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getTelefone());
			ps.setString(3, objeto.getTipo());
			ps.setString(4, objeto.getRua());
			ps.setString(5, objeto.getNumero());
			ps.setString(6, objeto.getTema());
			ps.setLong(7, objeto.getCodigo());
			ps.setLong(8, objeto.getCidade().getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from restaurante where idRestaurante = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(del);
			ps.setLong(1, codigo);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}

	}



	public Restaurante get(Long codigo) {
		String sql = "select *from restaurante where idRestaurante =?";
		Restaurante restaurante = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				restaurante = getRestaurante(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurante;
	}

	private List<Restaurante> getLista(ResultSet rs) throws SQLException {
		List<Restaurante> restaurantes = new ArrayList<>();
		while (rs.next()) {
			restaurantes.add(getRestaurante(rs));
		}
		return restaurantes;
	}

	private Restaurante getRestaurante(ResultSet rs) throws SQLException {
		Restaurante restaurante = new Restaurante(rs.getLong("idRestaurante"), rs.getString("Nome"),
				rs.getString("Telefone"), rs.getString("Tipo_de_estabelicimento"), rs.getString("Rua"),
				rs.getString("Numero"), rs.getString("Tema"),
				new Cidade(rs.getLong("idCidade")));
		return restaurante;
	}

	/* _____________________________________________________________________________________________________________________________________ */

	
	//Ainda não está pronto
	@Override
	public Collection<Restaurante> getPorCidade(String cidade) {
		String sql = "select Nome, Telefone, Tipo_de_estabelicimento,Rua,Numero, Tema"
				+ " from Restaurante r join cidade c on  r.idCidade = c.idCidade";
		List<Restaurante> restaurantes = new ArrayList<>();	
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setString(1, cidade);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
						rs.getString("Telefone"), 
						rs.getString("Tipo_de_estabelicimento"), 
						rs.getString("Rua"), 
						rs.getString("Numero"), 
						rs.getString("Tema"), 
						new Cidade(rs.getLong("idCidade")));
				restaurantes.add(restaurante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	/* _____________________________________________________________________________________________________________________________________ */

	//@Override
//	public ObservableList<Restaurante> todosTabela() {
	//	String sql = "select Nome, Telefone, Tipo_de_estabelicimento, Tema"
		//		+ " from Restaurante where Nome =? ";
		//ObservableList<Restaurante> restaurantes = new ArrayList<>();	
	//	try {
		//	PreparedStatement ps = conexao.get().prepareStatement(sql);
		//	ps.setString(1, nome);
	//		ResultSet rs = ps.executeQuery();
	//		while(rs.next()){
	//			Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
	//					rs.getString("Telefone"), 
	//					rs.getString("Tipo_de_estabelicimento"), 
	//					null, 
	//					rs.getString("Tema"), null, null, null, null);
		//		restaurantes.add(restaurante);
	//		}
	//	} catch (SQLException e) {
	//		e.printStackTrace();
	//	} finally {
	//		conexao.close();
	//	}
		//return restaurantes;
	//}

	
	public Collection<Restaurante> todos() {
		String sql = "select *from restaurante";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			restaurantes = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}
	
	/* _____________________________________________________________________________________________________________________________________ */

	
	public Collection<Restaurante> getPorTipo(String tipo) {
		String sql = "select Nome, Telefone, Tipo_de_estabelicimento,Rua,Numero, Tema" + " from Restaurante where Tipo_de_estabelicimento =? ";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setString(1, tipo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
						rs.getString("Telefone"), 
						null, 
						rs.getString("Rua"), 
						rs.getString("Numero"), 
						rs.getString("Tema"), 
						null);
				restaurantes.add(restaurante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	/* _____________________________________________________________________________________________________________________________________ */

	@Override
	public Collection<Restaurante> getPorTema(String tema) {
		String sql = "select Nome, Telefone, Tipo_de_estabelicimento,Rua,Numero, Tema, idCidade" + " from Restaurante where Tema =? ";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setString(1, tema);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
						rs.getString("Telefone"), 
						null, 
						rs.getString("Rua"), 
						rs.getString("Numero"), 
						rs.getString("Tema"), 
						null);
				restaurantes.add(restaurante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	/* _____________________________________________________________________________________________________________________________________ */
	@Override
	public Collection<Restaurante> getPorNome(String nome) {
		String sql = "select Nome, Telefone, Rua, Numero, Tema  from Restaurante where Nome =? ";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
						rs.getString("Telefone"), 
						null, 
						rs.getString("Rua"), 
						rs.getString("Numero"), 
						rs.getString("Tema"), 
						null);
				restaurantes.add(restaurante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	@Override
	public ObservableList<Restaurante> todosTabela() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//Elaborar uma forma de filtrar os melhores
	@Override
	public Collection<Restaurante> get5Melhores() {
		String sql = "select Nome, Telefone, Tipo_de_estabelicimento, Rua, Numero, Tema, idCidade" + " from Restaurante having count(Nome) < 6 ";
		List<Restaurante> restaurantes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurante restaurante = new Restaurante(null, rs.getString("Nome"), 
						rs.getString("Telefone"), 
						rs.getString("Tipo_de_estabelicimento"), 
						rs.getString("Rua"), 
						rs.getString("Numero"), 
						rs.getString("Tema"), 
						new Cidade(rs.getLong("idCidade")));
				restaurantes.add(restaurante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return restaurantes;
	}

	@Override
	public Collection<Restaurante> get10Melhores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> get15Melhores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> get20Melhores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> getMaiorPreco() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> getMenorPreco() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> getEndereco() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Restaurante> getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

}
