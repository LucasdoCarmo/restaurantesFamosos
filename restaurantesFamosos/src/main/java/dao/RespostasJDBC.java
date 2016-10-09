package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.Perguntas;
import model.Respostas;

public class RespostasJDBC implements RespostasDAO {

	private Conexao conexao;

	public RespostasJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Respostas objeto) {
		String insert = "insert into Respostas (Perguntas_idPerguntas) values(?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, objeto.getPerguntas().getCodigo());
			
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

	public void alterar(Respostas objeto) {
		String update = "update Visita set Perguntas_idPerguntas=?" + "where idRespostas = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setLong(1, objeto.getPerguntas().getCodigo());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from Respostas" + "where idRespostas = ?";
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

	public Collection<Respostas> todos() {
		String sql = "select *from Respostas";
		List<Respostas> respostass = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			respostass = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return respostass;
	}

	public Respostas get(Long codigo) {
		String sql = "select *from Respostas where idRespostas =?";
		Respostas respostas = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				respostas = getRespostas(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return respostas;
	}

	private List<Respostas> getLista(ResultSet rs) throws SQLException {
		List<Respostas> respostass = new ArrayList<>();
		while (rs.next()) {
			respostass.add(getRespostas(rs));
		}
		return respostass;
	}

	private Respostas getRespostas(ResultSet rs) throws SQLException {
		Respostas respostas = new Respostas(rs.getLong("idRespostas"),
					new Perguntas(rs.getLong("idPerguntas")));
		return respostas;
	}

}
