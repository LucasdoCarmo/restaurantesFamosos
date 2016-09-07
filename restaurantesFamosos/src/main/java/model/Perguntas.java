package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perguntas {

	private Long codigo;
	private String tempo_entrega;
	private String qualidade_atendimento;
	private String espera_mesa;
	private String aparencia_externa;
	private String aparencia_interna;
	private String limpeza;
	private String variedade_cardapio;
	private String bebidas_alcool;
	private String beboidas_sem_alcool;
	private String pagamento;
	private String valor_total;

}
