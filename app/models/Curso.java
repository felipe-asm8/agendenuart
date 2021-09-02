package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Curso extends Model {

	@Required
	public String nome;
	
	
	@Required
	public int numero;
	
	@Required
	public int bloco;
	
	@Required
	public String turno;
	
	//realacionamento bidirecional
	
	
	//um curso para muitos alunos e uma aluno para muitos alunos
	@OneToMany(mappedBy="curso") //mapeado por curso da classe aluno
	public List<Aluno> alunos; 
	
	public Curso(String nome) {
		this.nome = nome;
	}
}
