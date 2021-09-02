package controllers;

import java.util.List;


import models.Agenda;
import models.Aluno;
import models.Curso;
import models.Sala;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Agendas extends Controller {

	public static void form() {
		List<Sala> salas = Sala.findAll(); // passa a lista de salas p/ o form
		Agenda agenda = (Agenda) Cache.get("agenda");
		Cache.clear();
		render(salas, agenda);
	}

	public static void agendar() {
		render();
	}

	public static void index() {
		render();
	}

	public static void salvar(@Valid Agenda agenda, String email) {
		
		
		if(validation.hasErrors()) {
			Cache.add("agenda", agenda);
			validation.keep();
			flash.success("agendado com sucesso!");
			form();
			
		}
		
		if (session.contains("aluno.email")) {
			Long alunoSessao = Long.parseLong(session.get("aluno.id")); //parselong tranforma o o sseion de string em long que rotna o valor do id
			Aluno aluno = Aluno.findById(alunoSessao);
			agenda.aluno = aluno;
		}/*else {
			Aluno aluno = Aluno.find("email = ?1", email).first();
			if(aluno.email != null) {
				agenda.aluno = aluno;
			}else {
				flash.success("ocorreu um erro inesperado!");
				form();
			}*/
				
			
	
		
		
		agenda.save();
		if (session.contains("aluno.email")) {
			Alunos.index();
		} else {
			Application.index();
		}

	}

	public static void listar() {
		List<Agenda> agendas = Agenda.findAll();
		render(agendas);
	}

	public static void editar(Long id) {
		Agenda agenda = Agenda.findById(id);
		List<Sala> salas = Sala.findAll();
		renderTemplate("Agendas/form.html", agenda, salas);

	}

	public static void remover(Long id) {
		Agenda agenda = Agenda.findById(id);
		agenda.delete();
		listar();
	}
}
