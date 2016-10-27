package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.Pais;

public class PaisJDBC implements PaisDAO {

	private Conexao conexao;

	/**
	 * Conexão com o banco de dados.
	 * 
	 * @param conexao.get()
	 */
	public PaisJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Pais objeto) {
		String insert = "insert into pais (pais) values(?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());

			ps.executeUpdate();
			// Popular o objeto com o codigo gerado.
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			objeto.setCodigo(rs.getLong(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void alterar(Pais objeto) {
		String update = "update pais set pais=?" + "where id_pais = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setLong(2, objeto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}

	}

	@Override
	public void excluir(Long codigo) {
		String delete = "delete from pais " + "where id_pais = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(delete);
			ps.setLong(1, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	@Override
	public Collection<Pais> todos() {
		String sql = "select * from pais";
		List<Pais> Paiss = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Paiss = getLista(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return Paiss;
	}

	@Override
	public Pais get(Long codigo) {
		String sql = "select * from pais where id_pais = ?";
		Pais Pais = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			// Passa por todos os registros que vieram do banco.
			while (rs.next()) {
				Pais = getPais(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return Pais;
	}

	private List<Pais> getLista(ResultSet rs) throws SQLException {
		List<Pais> Paiss = new ArrayList<>();
		while (rs.next()) {
			Paiss.add(getPais(rs));
		}
		return Paiss;
	}

	private Pais getPais(ResultSet rs) throws SQLException {
		Pais pais = new Pais(rs.getLong("id_pais"), rs.getString("pais"));
		return pais;
	}

}
