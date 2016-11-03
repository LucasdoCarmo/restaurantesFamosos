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
		String insert = "insert into usuario (Nome,senha) values(?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getSenha());

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

	public void alterar(Usuario objeto) {
		String update = "update usuario set Nome=? , senha=? where idUsuario =?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getSenha());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from usuario where idUsuario = ?";
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
		String sql = "select * from usuario";
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
		String sql = "select * from usuario where idUsuario =?";
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
		Usuario usuario = new Usuario(rs.getLong("idUsuario"), rs.getString("Nome"), rs.getString("senha"));
		return usuario;

	}

	public Usuario login(String login, String senha) throws SQLException {
		Usuario usuario = null;
		String sql = "SELECT * FROM usuario WHERE Nome=? AND senha=?";
		PreparedStatement ps = conexao.get().prepareStatement(sql);
		ps.setObject(1, login);
		ps.setObject(2, senha);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			usuario = new Usuario(rs.getLong("isUsuario"), rs.getString("Nome"), rs.getString("senha"));
		}
		return usuario;
	}

	@Override
	public Usuario getIDPorNome(String nome) {
		String sql = "select idUsuario from usuario where Nome = ?";
		Usuario usuario = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql); // ,
																		// Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			// ps.getGeneratedKeys();
			while (rs.next()) {
				usuario = ((UsuarioJDBC) rs).getUsuario(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();

		}
		return usuario;
	}

}
