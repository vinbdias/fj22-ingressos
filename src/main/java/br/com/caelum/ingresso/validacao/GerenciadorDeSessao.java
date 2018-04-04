package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}
	
	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		LocalDate hojeDate = LocalDate.now();
		
		LocalTime horarioExistente = sessaoExistente.getHorario();
		LocalTime horarioNova = sessaoNova.getHorario();
		
		boolean terminaAntes = sessaoNova.getHorarioTermino()
				.isBefore(horarioExistente);
		
		boolean comecaDepois = sessaoExistente.getHorarioTermino()
				.isBefore(horarioNova);
		
		if(terminaAntes || comecaDepois) {
			return false;
		}
		
		return true;
	}
	
	public boolean cabe(Sessao sessaoNova) {
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> 
		horarioIsConflitante(sessaoExistente, sessaoNova));
	}

}
