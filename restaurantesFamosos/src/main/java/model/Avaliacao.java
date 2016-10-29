package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao implements Entidade {
	
	private Long codigo;
	private String notaAtendimento;
	private String notaComida;
	private String notaAspecto;
	private String notaPagamento;
	private String avaliacaoDescritiva;
	private String notaGeral;

	public Avaliacao(Long codigo) {
		this.codigo = codigo;
	}
}
