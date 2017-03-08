package br.edu.ifpb.pweb.turmas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.turmas.model.Aluno;
import br.edu.ifpb.pweb.turmas.model.Turma;

public class AlunoDAO extends GenericDAOJPAImpl<Aluno, Long> {

	private static Logger logger = Logger.getLogger(AlunoDAO.class);
	
	public AlunoDAO(EntityManager em) {
		super(em); 
	}	
	
	public AlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	
	public List<Aluno> findAll() throws DAOException{
		List<Aluno> alunos = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Aluno a");
			alunos = (List<Aluno>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Turmas", e);
		}
		return alunos;
	}

}
