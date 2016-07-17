package br.med.nader.BEAN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.med.nader.DAO.CidadeDAO;
import br.med.nader.DAO.EstadoDAO;
import br.med.nader.DAO.PacienteDAO;
import br.med.nader.VO.Cidade;
import br.med.nader.VO.Estado;
import br.med.nader.VO.Paciente;



@ManagedBean(name="pacBean")
@SessionScoped
public class PacienteBean {
	private Paciente paciente = new Paciente();
	private DataModel<Paciente>pacientes;
	private int cidade_id;
	private int estado_id;
	
	public void setPaciente(DataModel<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public int getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(int cidade_id) {
		this.cidade_id = cidade_id;
		
	}
	
	
	public int getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}

	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente Paciente) {
		this.paciente = Paciente;
	}

	public void novoPaciente(){
		paciente = new Paciente();
	}
	
	public void selecionarPaciente(){
	   this.paciente = pacientes.getRowData();	
	
	}
	
	
public DataModel<Paciente>getPacientes(){
	PacienteDAO dao = new PacienteDAO();
	
	
	try {
	List<Paciente> lista = dao.GetALL();	
	pacientes = new ListDataModel<Paciente>(lista);
	} catch (Exception e) {
		
	}	
		
	return pacientes;
	
}
	public void setPacientes(DataModel<Paciente> pacientes) {
	this.pacientes = pacientes;
}

	public String deletePaciente(){
		this.paciente = pacientes.getRowData();	
		String retorno = "erro";
		
		try{
			PacienteDAO dao = new PacienteDAO();
			dao.Delete(paciente);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		
		return retorno;
	}
public String updatePaciente(){
		
		String retorno = "erro";
		
		try{
			PacienteDAO dao = new PacienteDAO();
			dao.Update(paciente);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		return retorno;
	}
	
public String addPaciente() {

	String retorno = "erro";

	try {
		PacienteDAO dao = new PacienteDAO();
		CidadeDAO cidadeDao = new CidadeDAO();
		paciente.setCidade(cidadeDao.Get(cidade_id));
		EstadoDAO estadoDao = new EstadoDAO();
		paciente.setEstado(estadoDao.Get(estado_id));
		dao.Salvar(paciente);
		retorno = "listar";
		
				
	} catch (Exception ex) {
		System.out.println("ERRO + " + ex.getMessage());
	}

	return retorno;
}

public Collection<SelectItem>getCarregarCidades(){
	CidadeDAO dao = new CidadeDAO();
	Collection<SelectItem> lista = new ArrayList<SelectItem>();
	lista.add(new SelectItem("--SELECIONE--"));
	List<Cidade> listaCidade = dao.GetALL();
	
	for (int i = 0; i < listaCidade.size(); i++) {
	lista.add(new SelectItem(listaCidade.get(i).getId(), listaCidade.get(i).getCidade()));
	}
return lista;

}
public void redirect() throws IOException {
    // ...

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    externalContext.redirect("http://localhost:8080/ProntuarioMedico/faces/cadpaciente.xhtml");
}

public Collection<SelectItem>getCarregarEstados(){
	EstadoDAO dao = new EstadoDAO();
	Collection<SelectItem> lista = new ArrayList<SelectItem>();
	lista.add(new SelectItem("--SELECIONE--"));
	List<Estado> listaEstado = dao.GetALL();
	
	for (int i = 0; i < listaEstado.size(); i++) {
	lista.add(new SelectItem(listaEstado.get(i).getId(), listaEstado.get(i).getEstado()));
	}
return lista;
}

}