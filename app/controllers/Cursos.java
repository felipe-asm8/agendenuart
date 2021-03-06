package controllers;

import java.util.List;

import models.Aluno;
import models.Curso;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Cursos extends Controller {
	
	public static void form() {
		List<Aluno> alunos = Aluno.findAll();
		render(alunos);
	}
	
	public static void salvar(Curso curso) {
		curso.save();
		listar();
	}
	
	public static void listar() {
		List<Curso> cursos = Curso.findAll();
		render(cursos);
	}
	
	public static void editar(Long id) {
		Curso curso = Curso.findById(id);
		List<Aluno> alunosNaoMatriculados = Aluno.find("curso = null").fetch();		
		renderTemplate("Cursos/form.html", curso, alunosNaoMatriculados);
	}
	
	public static void remover(Long id) {
		Curso curso = Curso.findById(id);
		curso.delete();	
		listar();
	}
	
	public static void detalhes(Long id) {
		Curso curso = Curso.findById(id);
		render(curso);
	}
	
	

}
