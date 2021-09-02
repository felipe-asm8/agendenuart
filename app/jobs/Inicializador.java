package jobs;

import models.Curso;
import models.Sala;
import models.Servidor;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart 
public class Inicializador extends Job { //classe que inicia assim que inicia o cod
	
	@Override
	public void doJob() throws Exception {
		if(Servidor.count() == 0) {
			Servidor servidor = new Servidor();
			servidor.nome = "Ellena Mendes";
			servidor.email = "maria.ellena@escolar.ifrn.edu.br";
			servidor.matricula = "12323445555";
			servidor.cpf = "14567823";
			servidor.senha = "123";
			servidor.setSenha();
			servidor.save();
			
			Servidor servidor2 = new Servidor();
			servidor2.email = "felipe.augusto@escolar.ifrn.edu.br";
			servidor2.matricula = "12323445554";
			servidor2.cpf = "14567825";
			servidor2.senha = "123";
			servidor2.nome = "Felipe Morais";
			servidor2.setSenha();
			servidor2.save();						
		}
		
		Curso curso = null;
		Curso curso2 = null;
		Curso curso3 = null;
		
		if(Curso.count() == 0) {
			curso = new Curso("Informática");
			curso.save();
			
			curso2 = new Curso("Química");
			curso2.save();
			
			curso3 = new Curso("Recursos Pesqueiros");
			curso3.save();
		}
		
		Sala sala = null;
		
		if(Sala.count() == 0) {
			sala = new Sala("Auditório");
			sala.save();
			
		}
	
	}
}
