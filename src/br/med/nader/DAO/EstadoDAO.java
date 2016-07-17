package br.med.nader.DAO;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.med.nader.VO.Estado;
import br.med.nader.VO.Estado;

public class EstadoDAO extends DAO{
	
	public void Salvar(Estado estado){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(estado);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	public Estado Get(int idEstado){
		EntityManager em = getEntityManager();
		return em.find(Estado.class, idEstado);
	}
	public void Update(Estado estado){
		EntityManager em = getEntityManager();
		
		try{
			em.getTransaction().begin();
			Estado f = em.find(Estado.class, estado.getId());
			f.setEstado(estado.getEstado());
			em.getTransaction().commit();
		} catch(Exception ex){
			em.getTransaction().rollback();
		}
	}
	public List<Estado> GetALL(){
		EntityManager em = getEntityManager();
		List<Estado> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Estado as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
	}	
}
