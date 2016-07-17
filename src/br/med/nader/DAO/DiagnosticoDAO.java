package br.med.nader.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.med.nader.VO.Diagnostico;

public class DiagnosticoDAO extends DAO{
	
	public void Salvar(Diagnostico diagnostico){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(diagnostico);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	
	public Diagnostico Get(int iddiagnostico){
		EntityManager em = getEntityManager();
		return em.find(Diagnostico.class, iddiagnostico);
	}
	public void Update(Diagnostico diagnostico){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Diagnostico f = em.find(Diagnostico.class, diagnostico.getId());
			f.setDescricao(diagnostico.getDescricao());
			
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public void Delete(Diagnostico diagnostico){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Diagnostico f = em.find(Diagnostico.class, diagnostico.getId());
			em.remove(f);
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	
	public List<Diagnostico> GetALL(){
		EntityManager em = getEntityManager();
		List<Diagnostico> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Diagnostico as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
}
}