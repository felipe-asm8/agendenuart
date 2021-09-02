package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Servidor extends Model{
	
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
	
	public void setSenha() {
		senha = Crypto.passwordHash(senha);
	}


}
