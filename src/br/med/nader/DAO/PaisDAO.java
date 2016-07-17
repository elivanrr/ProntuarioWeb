package br.med.nader.DAO;

import javax.persistence.EntityManager;

import br.med.nader.VO.Pais;


public class PaisDAO extends DAO{
	
	public void Salvar(Pais pais){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(pais);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	
	
}

