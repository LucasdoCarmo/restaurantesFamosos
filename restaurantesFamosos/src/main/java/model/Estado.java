package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado implements Entidade {

	private Long codigo;
	private String nome;
	private Pais pais;

	public Estado(Long codigo) {
		this.codigo = codigo;
	}
}
