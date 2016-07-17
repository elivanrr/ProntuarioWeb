package br.med.nader.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.med.nader.VO.Tratamento;


public class TratamentoDAO extends DAO{
	
	public void Salvar(Tratamento tratamento){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(tratamento);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	
	public Tratamento Get(int idtratamento){
		EntityManager em = getEntityManager();
		return em.find(Tratamento.class, idtratamento);
	}
	public void Update(Tratamento tratamento){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Tratamento f = em.find(Tratamento.class, tratamento.getId());
			f.setDescricao(tratamento.getDescricao());
			
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public void Delete(Tratamento tratamento){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Tratamento f = em.find(Tratamento.class, tratamento.getId());
			em.remove(f);
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	
	public List<Tratamento> GetALL(){
		EntityManager em = getEntityManager();
		List<Tratamento> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Tratamento as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
}
}