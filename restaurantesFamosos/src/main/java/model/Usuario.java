package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Entidade {

	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	
	
	public Usuario(Long codigo) {
		this.codigo = codigo;
	}
	
}
