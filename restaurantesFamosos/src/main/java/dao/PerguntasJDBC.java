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

public class PerguntasJDBC implements PerguntasDAO {

	private Conexao conexao;

	/**
	 * Conexão com o banco de dados.
	 * 
	 * @param conexao.get()
	 */
	public PerguntasJDBC(Conexao conexao) {
		this.conexao = conexao;
	}

	public void inserir(Perguntas objeto) {
		String insert = "insert into perguntas (aparencia_externa,aparencia_interna,bebidas_alcoolicas,bebidas_sem_alcool ,"
				+ "houve_espera_por_mesa,limpeza,pagamento,"
				+ "qualidade_do_atendimento,tempo_de_entrega,qualidade_pelo_valor_pago ,variedade_cardapio) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getAparencia_externa());
			ps.setString(2, objeto.getAparencia_interna());
			ps.setString(3, objeto.getBebidas_alcool());
			ps.setString(4, objeto.getBeboidas_sem_alcool());
			ps.setString(5, objeto.getEspera_mesa());
			ps.setString(6, objeto.getLimpeza());
			ps.setString(7, objeto.getPagamento());
			ps.setString(8, objeto.getQualidade_atendimento());
			ps.setString(9, objeto.getTempo_entrega());
			ps.setString(10, objeto.getValor_total());
			ps.setString(11, objeto.getVariedade_cardapio());

			ps.executeUpdate();
			// Popular o objeto com o código gerado.
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			objeto.setCodigo(rs.getLong(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
	}

	public void alterar(Perguntas objeto) {
		String update = "update perguntas set aparencia_externa=?,aparencia_interna=?,bebidas_alcoolicas=?,bebidas_sem_alcool=?, houve_espera_por_mesa=?,limpeza=?,pagamento=?,qualidade_do_atendimento=?,tempo_de_entrega=?,qualidade_pelo_valor_pago=?,variedade_cardapio=? where idPerguntas = ?";
		try {
			PreparedStatement ps = conexao.get().prepareStatement(update);
			ps.setString(1, objeto.getAparencia_externa());
			ps.setString(2, objeto.getAparencia_interna());
			ps.setString(3, objeto.getBebidas_alcool());
			ps.setString(4, objeto.getBeboidas_sem_alcool());
			ps.setString(5, objeto.getEspera_mesa());
			ps.setString(6, objeto.getLimpeza());
			ps.setString(7, objeto.getPagamento());
			ps.setString(8, objeto.getQualidade_atendimento());
			ps.setString(9, objeto.getTempo_entrega());
			ps.setString(10, objeto.getValor_total());
			ps.setString(11, objeto.getVariedade_cardapio());
			ps.setLong(12, objeto.getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}

	}

	@Override
	public void excluir(Long codigo) {
		String delete = "delete from perguntas " + "where idPerguntas = ?";
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

	@Override
	public Collection<Perguntas> todos() {
		String sql = "select * from perguntas";
		List<Perguntas> Perguntass = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Perguntass = getLista(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return Perguntass;
	}

	@Override
	public Perguntas get(Long codigo) {
		String sql = "select * from perguntas where idPerguntas = ?";
		Perguntas perguntas = null;
		try {
			PreparedStatement ps = conexao.get().prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			// Passa por todos os registros que vieram do banco.
			while (rs.next()) {
				perguntas = getPerguntas(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}
		return perguntas;
	}

	private List<Perguntas> getLista(ResultSet rs) throws SQLException {
		List<Perguntas> Perguntass = new ArrayList<>();
		while (rs.next()) {
			Perguntass.add(getPerguntas(rs));
		}
		return Perguntass;
	}

	private Perguntas getPerguntas(ResultSet rs) throws SQLException {
		Perguntas Perguntas = new Perguntas(rs.getLong("idPerguntas"), rs.getString("tempo_de_entrega"),

				rs.getString("qualidade_do_atendimento"), rs.getString("houve_espera_por_mesa"),
				rs.getString("aparencia_externa"), rs.getString("aparencia_interna"), rs.getString("limpeza"),
				rs.getString("variedade_cardapio"), rs.getString("bebidas_alcoolicas"),
				rs.getString("bebidas_sem_alcool"), rs.getString("pagamento"),
				rs.getString("qualidade_pelo_valor_pago"));
		return Perguntas;
	}
}
