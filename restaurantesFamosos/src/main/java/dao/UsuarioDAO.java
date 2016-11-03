package dao;

import model.Usuario;

public interface UsuarioDAO extends CrudDAO<Usuario> {

	Long getIDPorNome(String nome);

}
