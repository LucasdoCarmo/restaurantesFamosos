package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Respostas implements Entidade {

	private Long codigo;
	private Perguntas perguntas;

	public Respostas(Long codigo) {
		this.codigo = codigo;
	}
}