package controllers;

import java.util.List;


import models.Aluno;
import models.Curso;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Alunos extends Controller {

	public static void form() {
		List<Curso> cursos = Curso.findAll(); // passa a lista de cusro p/ o form
		Aluno aluno = (Aluno) Cache.get("aluno");
		Cache.clear();
		render(cursos, aluno);
	}

	public static void agendar() {
		render();
	}

	public static void index() {
		render();
	}

	public static void salvar(@Valid Aluno aluno) {
		if(validation.hasErrors()) {
			Cache.add("aluno", aluno);
			validation.keep();

			form();
		}
		aluno.setSenha();
		aluno.save();
		flash.success("cadastrado");
		
		if (session.contains("aluno.email")) {
			Alunos.detalhes();
		} else if(session.contains("servidor.email")) {
		Application.index();
		
		}else {
			Login.form();
		}
		

	}

	public static void listar() {
		List<Aluno> alunos = Aluno.findAll();
		
		Long alunoSessao;
		if (session.contains("aluno.email")) {
			alunoSessao = Long.parseLong(session.get("aluno.id")); //parselong tranforma o o sseion de string em long que rotna o valor do id
			
		} else {
			alunoSessao = null;
		}
		
		render(alunos, alunoSessao);
	}

	public static void editar(Long id) {
		Aluno aluno = Aluno.findById(id);
		List<Curso> cursos = Curso.findAll();
		renderTemplate("Alunos/form.html", aluno, cursos);

	}

	public static void remover(Long id) {
		Aluno aluno = Aluno.findById(id);
		aluno.delete();
		
		if(session.contains("aluno.email")) {
			session.clear();
			Login.form();
		}else {
			Alunos.listar();
		}
		
		
		
			
		
		
	}
	
	public static void detalhes() {
		Long alunoSessao = Long.parseLong(session.get("aluno.id")); //parselong tranforma o o sseion de string em long que rotna o valor do id
		Aluno aluno = Aluno.findById(alunoSessao);
		render(aluno, alunoSessao);
	}
	
}