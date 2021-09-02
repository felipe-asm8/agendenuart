package controllers;

import models.Aluno;
import models.Servidor;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {
	
	public static void form () {
		render();
		
	}
	public static void logar(String email, String senha) {
		
			senha = Crypto.passwordHash(senha);
		

		Aluno aluno = Aluno.find("email = ?1 and senha = ?2 ", email, senha).first();
		Servidor servidor = Servidor.find("email = ?1 and senha = ?2 ", email, senha).first();
		
	if (aluno == null && servidor == null) {
		form();
	}else { 
		if (aluno != null && servidor == null) {
			session.put("aluno.email", aluno.email);
			session.put("aluno.nome", aluno.nome);
			session.put("aluno.id", aluno.id);
			Alunos.index();
		}else {
			session.put("servidor.email", servidor.email);
			session.put("servidor.nome", servidor.nome);
			session.put("servidor.id", servidor.id);
			Application.index();
			
		}
		//session.put("aluno.nivel", aluno.nivel);
		
	}
	}
	public static void sair () {
		session.clear();
		form();
		
	}
}
