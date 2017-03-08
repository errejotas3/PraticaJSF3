package br.edu.ifpb.pweb.turmas.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import java.util.HashMap;

import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="bbean")
@RequestScoped

public class TurmasBean {
	private Turma turma = new Turma();
	private Map<Long, Boolean> editavel;
	private List<Turma> turmas = new ArrayList<Turma>();

	
	
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Map<Long, Boolean> getEditavel() {
		return editavel;
	}

	public void setEditavel(Map<Long, Boolean> editavel) {
		this.editavel = editavel;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	//Listar turmas
	public void listar(ActionEvent e){
		
		TurmaDAO tDao = new TurmaDAO();
		this.turmas = tDao.findAll();
		editavel = new HashMap<Long, Boolean>(this.turmas.size());
		for (Turma t : this.turmas){
			editavel.put(t.getId(), false);
		}
	}
	
	//Salvar turmas
	public void salvar(Turma turma){
		TurmaDAO tDao = new TurmaDAO();
		tDao.beginTransaction();
		tDao.update(turma);
		tDao.commit();
		this.editavel.put(turma.getId(), false);
	}
	
	//Excluir turmas
	public void excluir(Turma turma) {
		TurmaDAO tDao = new TurmaDAO();
		tDao.beginTransaction();
		tDao.delete(turma);
		tDao.commit();
		this.editavel.put(turma.getId(), false);
	}
	
	//Criar uma nova turma
	public void criar() {
		TurmaDAO tDao = new TurmaDAO();
		tDao.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		turma.setDataCriacao(date);
		turma = tDao.insert(turma);
		tDao.commit();
		turma = new Turma();
	}
	
	
}
