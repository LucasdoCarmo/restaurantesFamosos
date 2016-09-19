package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import conexao.Conexao;
import model.CEP;
import model.Cidade;

public class CepJDBC implements CepDAO {

	private Conexao conexao;

	public CepJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(CEP objeto) {
		String insert = "insert into CEP (numero,idCidade) values(?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getCep());
			ps.setLong(2, objeto.getCidade().getCodigo());

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

	public void alterar(CEP objeto) {
		String update = "update CEP set numero=?,idCidade=?" + "where idCEP = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getCep());
			ps.setLong(2, objeto.getCidade().getCodigo());
			ps.setLong(3, objeto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void excluir(Long codigo) {
		String del = "delete from CEP" + "where idCEP = ?";
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

	public Collection<CEP> todos() {
		String sql = "select *from Estado";
		List<CEP> ceps = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ceps = getLista(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return ceps;
	}

	public CEP get(Long codigo) {
		String sql = "select *from CEP where idCEP =?";
		CEP cep = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cep = getCep(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return cep;
	}

	private List<CEP> getLista(ResultSet rs) throws SQLException {
		List<CEP> ceps = new ArrayList<>();
		while (rs.next()) {
			ceps.add(getCep(rs));
		}
		return ceps;
	}

	private CEP getCep(ResultSet rs) throws SQLException {
		CEP cep = new CEP(rs.getLong("idCEP"), rs.getString("numero"), new Cidade(rs.getLong("idCidade")));
		return cep;
	}
}
