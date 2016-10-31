package model;

import componente.RenderizaCombo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante implements Entidade ,RenderizaCombo {

	private Long codigo;
	private String nome;
	private String telefone;
	private String tipo;
	private String rua;
	private String numero;
	private String tema;
	private Cidade cidade;
	
	public Restaurante(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}


	
}



