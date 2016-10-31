package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import conexao.Conexao;
import model.Cidade;
import model.Estado;

public class CidadeJDBC implements CidadeDAO {

	private Conexao conexao;

	public CidadeJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Cidade objeto) {
		String insert = "insert into cidade (Cidade,idEstado) values(?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setLong(2, objeto.getEstado().getCodigo());
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

	public void alterar(Cidade objeto) {
		String update = "update cidade set Cidade=?, idEstado = ?" + " where idCidade = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getNome());
			ps.setLong(2, objeto.getEstado().getCodigo());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String delete = "delete from cidade where idCidade = ?";
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

	public Collection<Cidade> todos() {
		String sql = "select *from cidade";
		List<Cidade> cidades = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cidades = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return cidades;
	}

	public Cidade get(Long codigo) {
		String sql = "select *from cidade where idCidade =?";
		Cidade cidade = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cidade = getCidade(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return cidade;
	}

	private List<Cidade> getLista(ResultSet rs) throws SQLException {
		List<Cidade> cidades = new ArrayList<>();
		while (rs.next()) {
			cidades.add(getCidade(rs));
		}
		return cidades;
	}

	private Cidade getCidade(ResultSet rs) throws SQLException {
		Cidade cidade = new Cidade(rs.getLong("idCidade"), rs.getString("Cidade"),
				new Estado(rs.getLong("idEstado")));
		return cidade;
	}

	@Override
	public List<Cidade> getPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> porEstado(Long estado) {
		String sql = "select * from cidade c join estado e on "
				+ "c.idEstado = e.idEstado where idEstado =?";
		List<Cidade> cidades = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, estado);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidades.add(cidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return cidades;
	}
}
