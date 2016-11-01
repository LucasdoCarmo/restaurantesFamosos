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

	public PaisJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Pais objeto) {
		String insert = "insert into pais (pais) values(?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
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

	public void alterar(Pais objeto) {
		String update = "update pais set pais=? where idpais = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String delete = "delete from pais " + "where idpais = ?";
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

	public Collection<Pais> todos() {
		String sql = "select *from pais";
		List<Pais> Paises = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Paises = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return Paises;
	}

	public Pais get(Long codigo) {
		String sql = "select * from pais where idpais = ?";
		Pais pais = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			// Passa por todos os registros que vieram do banco.
			while (rs.next()) {
				pais = getPais(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return pais;
	}

	private List<Pais> getLista(ResultSet rs) throws SQLException {
		List<Pais> paises = new ArrayList<>();
		while (rs.next()) {
			paises.add(getPais(rs));
		}
		return paises;
	}

	private Pais getPais(ResultSet rs) throws SQLException {
		Pais pais = new Pais(rs.getLong("idpais"), rs.getString("pais"));
		return pais;
	}

	
	
}
