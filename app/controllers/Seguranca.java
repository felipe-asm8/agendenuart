package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	@Before(unless={"Alunos.form","Alunos.salvar","Servidores.salvar"})
	
	static void verificar() {
		if (session.contains("aluno.email") == false && session.contains("servidor.email") == false) {
			Login.form();
			
		}
	}
}
