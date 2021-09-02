package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Sala extends Model {
	
	@Required (message="campo obrigatorio")
	public String nome;
	
	@Min(0)
	@Max(1000)
	@Required (message="campo obrigatorio")
	public Integer numero;
	
	@Min(0)
	@Max(5)
	@Required (message="campo obrigatorio")
	public Integer bloco;
	
	@Required (message="campo obrigatorio")
	public boolean status;
	
	@OneToMany(mappedBy="sala") //mapea o atributo sala
	public List<Agenda> agendas; 
	
	public Sala() {
		this.status = true;
	}
	
	public Sala(String nome) {
		this.nome = nome;
	}
	

}
