package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante implements Entidade {
	
	private Long codigo;
	private String nome;
	private String telefone;
	private String tipo;
	private String rua;
	private String numero;
	private String tema;
	
	public Restaurante(Long codigo) {
		this.codigo = codigo;
	}
}
