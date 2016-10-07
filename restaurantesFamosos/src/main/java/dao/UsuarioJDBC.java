package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.Usuario;

public class UsuarioJDBC implements UsuarioDAO {
	private Conexao conexao;

	public UsuarioJDBC(Conexao conexao) {
		this.conexao = conexao;

	}

	public void inserir(Usuario objeto) {
		String insert = "insert into Usuario (nome,idRespostas,email,senha) values(?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setLong(2, objeto.getResposta().getCodigo());
			ps.setString(3, objeto.getEmail());
			ps.setString(4, objeto.getSenha());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			rs.next();
			objeto.setSenha(rs.getString(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void alterar(Usuario objeto) {
		String update = "update Usuario set nome=?,Respostas=?,email=?,senha=?," + "where idUsuario=?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setLong(2, objeto.getResposta().getCodigo());
			ps.setString(3, objeto.getEmail());
			ps.setString(4, objeto.getSenha());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from Usuario" + "where idUsuario = ?";
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

	public Collection<Usuario> todos() {
		String sql = "select *from Usuario";
		List<Usuario> usuarios = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			usuarios = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return usuarios;
	}

	public Usuario get(Long codigo) {
		String sql = "select *from Usuario where idUsuario =?";
		Usuario usuario = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usuario = getUsuario(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();

		}
		return usuario;
	}

	private List<Usuario> getLista(ResultSet rs) throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		while (rs.next()) {
			usuarios.add(getUsuario(rs));
		}
		return usuarios;
	}

	private Usuario getUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario(rs.getLong("idUsuario"));
		return usuario;

	}

	

}
