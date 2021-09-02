package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Aluno extends Model{

	@Required (message="campo obrigatorio")
	public String nome;
	
	@Required (message="campo obrigatorio")
	public String matricula;
	
	@Required (message="campo obrigatorio")
	public String cpf;
	
	@Required (message="campo obrigatorio")
	public String senha;
	
	@Required (message="campo obrigatorio")
	public String email;
	
	@ManyToOne //muitos alunos para um curso
	@JoinColumn(name="curso_id")
	public Curso curso;
	
	@OneToMany(mappedBy="aluno") //mapeado por curso da classe aluno
	public List<Agenda> agendas;
	
	
	public void setSenha() {
		senha = Crypto.passwordHash(senha);
	}


}