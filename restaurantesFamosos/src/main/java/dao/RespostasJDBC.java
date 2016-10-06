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
		String insert = "insert into Respostas (idRespostas,idPerguntas) values(?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, objeto.getCodigo());
			ps.setLong(2, objeto.getPerguntas().getCodigo());

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
		String update = "update Respostas set idRespostas=?,idPergunas=?" + "where idRespostas = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setLong(1, objeto.getCodigo());
			ps.setLong(2, objeto.getPerguntas().getCodigo());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from Respostas" + "where idResposta = ?";
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
		List<Respostas> respostas = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			respostas = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return respostas;
	}

	public Respostas get(Long codigo) {
		String sql = "select *from CEP where idCEP =?";
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
		List<Respostas> respostas = new ArrayList<>();
		while (rs.next()) {
			respostas.add(getRespostas(rs));
		}
		return respostas;
	}

	private Respostas getRespostas(ResultSet rs) throws SQLException {
		Respostas respostas = new Respostas(rs.getLong("idRespostas"), new Perguntas(rs.getLong("idPerguntas")));
		return respostas;
	}
}
