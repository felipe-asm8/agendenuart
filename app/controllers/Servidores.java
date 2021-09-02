package controllers;

import java.util.List;


import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
import models.Agenda;
import models.Aluno;
import models.Servidor;

@With(Seguranca.class)
public class Servidores extends Controller {

	public static void form() {
		Servidor servidor = (Servidor)Cache.get("servidor");
		Cache.set("servidor", null);
		render(servidor);
	}
	

	public static void listar() {
		List<Servidor> servidores = Servidor.findAll();
		
		Long servidorSessao = Long.parseLong(session.get("servidor.id")); //parselong tranforma o o sseion de string em long que rotna o valor do id
		
		
		render(servidores, servidorSessao);

	}
	
	public static void agendar() {
		render();
	}
	

	public static void salvar(@Valid Servidor servidor) {
		if (validation.hasErrors()) {
			validation.keep();
			
			flash.error("falha ao editar");
			Cache.set("servidor", servidor);
			
				form();
		}
		servidor.setSenha();
		servidor.save();
		flash.success("cadastrado");
		Login.form();

	}

	public static void editar(Long id) {
		Servidor servidor = Servidor.findById(id);
		renderTemplate("Servidores/form.html", servidor);

	}

	public static void remover(Long id) {
		Servidor servidor = Servidor.findById(id);
		servidor.delete();
		
		if(session.contains("servidor.email")) {
			session.clear();
			Login.form();
		}else {
			Servidores.listar();
		}
	}
	
	public static void deferir(Long id) {
		Agenda agenda = Agenda.findById(id);
		agenda.deferido = true;
		agenda.save();
		Agendas.listar();
	}
	
	public static void indeferir(Long id) {
		Agenda agenda = Agenda.findById(id);
		agenda.deferido = false;
		agenda.save();
		Agendas.listar();
	}
}
