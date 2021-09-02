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
public class Salas extends Controller {
	
	public static void form() {
		List<Agenda> agendas = Aluno.findAll();
		Sala sala = (Sala) Cache.get("sala");
		Cache.clear();
		render(agendas, sala);
	}
	
	public static void salvar(@Valid Sala sala) {
		if(validation.hasErrors()) {
			Cache.add("sala", sala);
			validation.keep();

			form();
		}
		sala.save();
		flash.success("cadastrado");
		listar();
	}
	
	public static void listar() {
		List<Sala> salas = Sala.findAll();
		render(salas);
	}
	
	public static void editar(Long id) {
		Sala sala = Sala.findById(id);
		List<Agenda> agendas = Agenda.findAll();
		renderTemplate("Salas/form.html",sala, agendas);

	}
	
	public static void remover(Long id) {
		Sala sala = Sala.findById(id);
		sala.delete();	
		listar();
	}
	
	public static void detalhes(Long id) {
		Curso curso = Curso.findById(id);
		render(curso);
	}
	
	
	

}
