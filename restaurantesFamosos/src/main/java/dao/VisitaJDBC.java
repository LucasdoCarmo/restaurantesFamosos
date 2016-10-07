package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.Avaliacao;
import model.Restaurante;
import model.Usuario;
import model.Visita;

public class VisitaJDBC implements VisitaDAO {

	private Conexao conexao;

	public VisitaJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Visita objeto) {
		String insert = "insert into Visita (Data,Valor_Gasto,Restaurante_idRestaurante,Usuario_idUsuario,Avaliacao_idAvaliacao) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getData());
			ps.setDouble(2, objeto.getValorGasto());
			ps.setLong(3, objeto.getRestaurante().getCodigo());
			ps.setLong(4, objeto.getUsuario().getCodigo());
			ps.setLong(5, objeto.getAvaliacao().getCodigo());

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

	public void alterar(Visita objeto) {
		String update = "update Visita set Data=?, set Valor_Gasto=?, set Restaurante_idRestaurante=?, set Usuario_idUsuario=?, set Avaliacao_idAvaliacao=?"
				+ "where idVisita = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getData());
			ps.setDouble(2, objeto.getValorGasto());
			ps.setLong(3, objeto.getRestaurante().getCodigo());
			ps.setLong(4, objeto.getUsuario().getCodigo());
			ps.setLong(5, objeto.getAvaliacao().getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from Visita" + "where idVisita = ?";
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

	public Collection<Visita> todos() {
		String sql = "select *from Visita";
		List<Visita> visitas = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			visitas = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return visitas;
	}

	public Visita get(Long codigo) {
		String sql = "select *from Visita where idVisita =?";
		Visita visita = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				visita = getVisita(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return visita;
	}

	private List<Visita> getLista(ResultSet rs) throws SQLException {
		List<Visita> visitas = new ArrayList<>();
		while (rs.next()) {
			visitas.add(getVisita(rs));
		}
		return visitas;
	}

	private Visita getVisita(ResultSet rs) throws SQLException {
		Visita visita = new Visita(rs.getLong("idVisita"), rs.getString("Data"), rs.getDouble("Valor_Gasto"),
				new Restaurante(rs.getLong("idRestaurante")), new Usuario(rs.getLong("idUsuario")),
				new Avaliacao(rs.getLong("idAvaliacao")));
		return visita;
	}

}
