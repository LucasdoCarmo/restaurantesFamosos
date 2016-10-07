package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Entidade {

	private String senha;
	private String Nome;
	private String email;
	private Long codigo;
	private Respostas resposta;

	public Usuario(Long codigo) {
		this.codigo = codigo;
	}

}
