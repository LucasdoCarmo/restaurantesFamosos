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

	public Visita(Long codigo) {
		this.codigo = codigo;
	}

	public void setUsuarioCOD(String nome) {
		usuario.setNome(nome);
	}

	public void setRestauranteCOD(String nome) {
		restaurante.setNome(nome);
	}

	public void setUsuarioCOD(Long user) {
		usuario.setCodigo(user);

	}

	public void setRestauranteCOD(Long rest) {
		restaurante.getCodigo();

	}
}
