package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respostas implements Entidade {
	
	private Long codigo;
	private Perguntas perguntas;

	public Respostas(Long codigo) {
		this.codigo = codigo;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
