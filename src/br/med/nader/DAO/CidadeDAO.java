package br.med.nader.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.med.nader.VO.Cidade;


public class CidadeDAO extends DAO{
	
	public void Salvar(Cidade cidade){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(cidade);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	public Cidade Get(int idCidade){
		EntityManager em = getEntityManager();
		return em.find(Cidade.class, idCidade);
	}
	public void Update(Cidade cidade){
		EntityManager em = getEntityManager();
		
		try{
			em.getTransaction().begin();
			Cidade f = em.find(Cidade.class, cidade.getId());
			f.setCidade(cidade.getCidade());
			em.getTransaction().commit();
		} catch(Exception ex){
			em.getTransaction().rollback();
		}
	}
	public void Delete(Cidade cidade){
		EntityManager em = getEntityManager();
		
		try{
			em.getTransaction().begin();
			Cidade f = em.find(Cidade.class, cidade.getId());
			em.remove(f);
			em.getTransaction().commit();
		} catch(Exception ex){
			em.getTransaction().rollback();
		}
	}
	public List<Cidade> GetALL(){
		EntityManager em = getEntityManager();
		List<Cidade> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Cidade as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
}
	
}
