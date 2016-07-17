package br.med.nader.UTILS;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.SessionFactory;

import br.med.nader.DAO.DAO;
import br.med.nader.DAO.PacienteDAO;
import br.med.nader.VO.Paciente;



@ManagedBean(name = "cptBean")
@RequestScoped
public class CompleteNome {
	@SuppressWarnings("unused")
	private static SessionFactory sessionFactory;
	private PacienteDAO pacienteDAO = new PacienteDAO();
    private List<Paciente> pacientes = new ArrayList<Paciente>();
 
    public List<Paciente> getPacientes(){
    	return pacientes;
    }
        
    public void setPacientes(List<Paciente> pacientes){
    this.pacientes = pacientes;
    }
    
	public List<Paciente> completaNome(String query) {
		this.pacientes = pacienteDAO.GetALL();
		List<Paciente> sugestoes = new ArrayList<Paciente>();
		for (Paciente p : this.pacientes){
			if (p.getNome().startsWith(query)){
				sugestoes.add(p);		
			}		
			}
		return sugestoes;
	}
}


