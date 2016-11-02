package model;

import componente.RenderizaCombo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cidade implements Entidade , RenderizaCombo {

	private Long codigo;
	private String nome;
	private Estado estado;

	public Cidade(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public Cidade(String nome) {
		this.nome = nome;
	}
}
