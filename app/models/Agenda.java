package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
public class Agenda extends Model {
	
	@Required
	public String turno;
	
	@Required
	public String data;
	
	@Required
	public String horario;
	
	
	public boolean deferido;
	
	//relacionamento bidirecional
	
	@Required
	@ManyToOne
	@JoinColumn(name="sala_id")
	public Sala sala;
	
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	public Aluno aluno;
	
	public Agenda() {
		this.deferido = false;
	}
	 
	public String getDeferido() {
		if (deferido)
			return "DEFERIDO";
		else 
			return "INDEFERIDO";
		
			
	}
	
	

}
