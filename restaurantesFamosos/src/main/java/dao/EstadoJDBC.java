package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import conexao.Conexao;
import model.Estado;

public class EstadoJDBC implements EstadoDAO {

	private Conexao conexao;

	public EstadoJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Estado objeto) {
		String insert = "insert into estado (Estado,UF) values(?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getUf());
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

	public void alterar(Estado objeto) {
		String update = "update estado set Estado=?, UF=? where idEstado = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getUf());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String delete = "delete from estado " + "where idEstado = ?";
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

	public Collection<Estado> todos() {
		String sql = "select *from estado";
		List<Estado> Estados = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Estados = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return Estados;
	}

	public Estado get(Long codigo) {
		String sql = "select * from estado where idEstado = ?";
		Estado estado = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			// Passa por todos os registros que vieram do banco.
			while (rs.next()) {
				estado = getEstado(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return estado;
	}

	private List<Estado> getLista(ResultSet rs) throws SQLException {
		List<Estado> estados = new ArrayList<>();
		while (rs.next()) {
			estados.add(getEstado(rs));
		}
		return estados;
	}

	private Estado getEstado(ResultSet rs) throws SQLException {
		Estado estado = new Estado(rs.getLong("idEstado"), rs.getString("Estado"), rs.getString("UF"));
		return estado;
	}

}
