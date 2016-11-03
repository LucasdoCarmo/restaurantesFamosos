package dao;

import model.Usuario;

public interface UsuarioDAO extends CrudDAO<Usuario> {

	Usuario getIDPorNome(String nome);

}
