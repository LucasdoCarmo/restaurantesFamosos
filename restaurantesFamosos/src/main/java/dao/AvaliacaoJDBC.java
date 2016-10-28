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

public class AvaliacaoJDBC implements AvaliacaoDAO {

	private Conexao conexao;

	public AvaliacaoJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Avaliacao objeto) {
		String insert = "insert into avaliacao (Nota_Atendimento,Nota_Comida,Nota_aspecto,Nota_pagamento,Avaliacao_Descritiva,Nota_Geral) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, objeto.getNotaAtendimento());
			ps.setInt(2, objeto.getNotaComida());
			ps.setInt(3, objeto.getNotaAspecto());
			ps.setInt(4, objeto.getNotaPagamento());
			ps.setString(5, objeto.getAvaliacaoDescritiva());
			ps.setInt(6, objeto.getNotaGeral());
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

	public void alterar(Avaliacao objeto) {
		String update = "update avaliacao set Nota_Atendimento=?,Nota_Comida=?,Nota_aspecto=?,Nota_pagamento=?,Avaliacao_Descritiva=?,Nota_Geral=? where idAvaliacao=?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setInt(1, objeto.getNotaAtendimento());
			ps.setInt(2, objeto.getNotaComida());
			ps.setInt(3, objeto.getNotaAspecto());
			ps.setInt(4, objeto.getNotaPagamento());
			ps.setString(5, objeto.getAvaliacaoDescritiva());
			ps.setInt(6, objeto.getNotaGeral());
			ps.setLong(7, objeto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from avaliacao where idAvaliacao = ?";
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

	public Collection<Avaliacao> todos() {
		String sql = "select *from avaliacao";
		List<Avaliacao> avaliacoes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			avaliacoes = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return avaliacoes;
	}

	public Avaliacao get(Long codigo) {
		String sql = "select *from avaliacao where idAvaliacao =?";
		Avaliacao avaliacao = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				avaliacao = getAvaliacao(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return avaliacao;
	}

	private List<Avaliacao> getLista(ResultSet rs) throws SQLException {
		List<Avaliacao> avaliacoes = new ArrayList<>();
		while (rs.next()) {
			avaliacoes.add(getAvaliacao(rs));
		}
		return avaliacoes;
	}

	private Avaliacao getAvaliacao(ResultSet rs) throws SQLException {
		Avaliacao avaliacao = new Avaliacao(rs.getLong("idAvaliacao"), rs.getInt("Nota_Atendimento"),
				rs.getInt("Nota_Comida"), rs.getInt("Nota_aspecto"), rs.getInt("Nota_pagamento"),
				rs.getString("Avaliacao_Descritiva"), rs.getInt("Nota_Geral"));
		return avaliacao;
	}
	
}






