package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
	
	private Long codigo;
	private Integer notaAtendimento;
	private Integer notaComida;
	private Integer notaAspecto;
	private Integer notaPagamento;
	private String avaliacaoDescritiva;
	private Integer notaGeral;

}
