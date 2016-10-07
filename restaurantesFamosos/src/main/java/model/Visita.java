package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visita implements Entidade {

	private Long codigo;
	private String data;
	private Double valorGasto;
	private Restaurante restaurante;
	private Usuario usuario;
	private Avaliacao avaliacao;

	public Visita(Long codigo) {
		this.codigo = codigo;
	}
}
