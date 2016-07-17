package br.med.nader.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.med.nader.VO.Paciente;


public class PacienteDAO extends DAO{
	
	public void Salvar(Paciente paciente){
	EntityManager em = getEntityManager();
		
	try{
		em.getTransaction().begin();
		em.persist(paciente);
		em.getTransaction().commit();
		} catch (Exception ex){
			em.getTransaction().rollback();
		}
}
	
	public Paciente Get(String nome){
		EntityManager em = getEntityManager();
		return em.find(Paciente.class, nome);
	}
	public Paciente Get(int idpaciente){
		EntityManager em = getEntityManager();
		return em.find(Paciente.class, idpaciente);
	}
	public void Update(Paciente paciente){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Paciente f = em.find(Paciente.class, paciente.getId());
			f.setNome(paciente.getNome());
			f.setEndereco(paciente.getEndereco());
			f.setTelefone(paciente.getTelefone());
			
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public void Delete(Paciente paciente){
		EntityManager em = getEntityManager();
			
		try{
			em.getTransaction().begin();
			Paciente f = em.find(Paciente.class, paciente.getId());
			em.remove(f);
			em.getTransaction().commit();
			} catch (Exception ex){ 
				em.getTransaction().rollback();
			}
	}
	public List<Paciente> GetALL(){
		EntityManager em = getEntityManager();
		List<Paciente> lista = null;
			
		try{
			Query q = em.createQuery("select object(f) from Paciente as f");
			return q.getResultList();
		} catch (Exception ex){ 
			em.close();
				
			}
		return lista;
}
}
