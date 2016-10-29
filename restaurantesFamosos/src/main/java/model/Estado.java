package model;

import componente.RenderizaCombo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado implements Entidade, RenderizaCombo {

	private Long codigo;
	private String nome;
	private String uf;

	public Estado(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
