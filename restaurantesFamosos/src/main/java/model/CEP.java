package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CEP implements Entidade {

	private Long codigo;
	private String cep;
	private Cidade cidade;

	public CEP(Long codigo) {
		this.codigo = codigo;
	}
}
