package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais implements Entidade {

	private Long codigo;
	private String nome;

	public Pais(Long codigo) {
		this.codigo = codigo;
	}

}
