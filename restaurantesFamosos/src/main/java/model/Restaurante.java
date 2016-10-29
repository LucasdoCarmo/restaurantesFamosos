package model;

import componente.RenderizaCombo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper =false)
public class Restaurante extends Visita implements Entidade ,RenderizaCombo {

	private Long codigo;
	private String nome;
	private String telefone;
	private String tipo;
	private String rua;
	private String numero;
	private String tema;
	private CEP cep;
	private Pais pais;
	private Estado estado;
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



