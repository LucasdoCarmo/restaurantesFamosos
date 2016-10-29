package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Usuario extends Respostas implements Entidade {

	private Long codigo;
	private String Nome;
	private String email;
	private String senha;
	
	
	public Usuario(Long codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
