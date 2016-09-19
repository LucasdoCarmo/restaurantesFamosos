package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cidade implements Entidade {

	private Long codigo;
	private String nome;
	private Estado estado;

	public Cidade(Long codigo) {
		this.codigo = codigo;
	}
}
