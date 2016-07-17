package br.med.nader.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.med.nader.VO.Prontuario;




public class ProntuarioDAO extends DAO{
	
	public void Salvar(Prontuario prontuario){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(prontuario);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
		
	public void Update(Prontuario prontuario){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Prontuario f = em.find(Prontuario.class, prontuario.getId());
			f.setDatahora(prontuario.getDatahora());
			f.setDiagnostico(prontuario.getDiagnostico());
			f.setObservacao(prontuario.getObservacao());
			f.setTratamento(prontuario.getTratamento());
			f.setPaciente(prontuario.getPaciente());
			
			
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public void Delete(Prontuario prontuario){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Prontuario f = em.find(Prontuario.class, prontuario.getId());
			em.remove(f);
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public List<Prontuario> GetALL(){
		EntityManager em = getEntityManager();
		List<Prontuario> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Prontuario as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
}
}
