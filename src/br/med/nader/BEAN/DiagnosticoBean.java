package br.med.nader.BEAN;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.med.nader.DAO.DiagnosticoDAO;
import br.med.nader.VO.Diagnostico;
import br.med.nader.VO.Paciente;
import br.med.nader.BEAN.DiagnosticoBean;




@ManagedBean(name="diagBean")
@SessionScoped
public class DiagnosticoBean {
	private Diagnostico diagnostico = new Diagnostico();
	private DataModel<Diagnostico>diagnosticos;
	
		
	public void setDiagnosticos(DataModel<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}


	public Diagnostico getDiagnostico() {
		return diagnostico;
	}


	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void novoDiagnostico(){
		diagnostico = new Diagnostico();
	}
	
	public void selecionarDiagnostico(){
	   this.diagnostico = diagnosticos.getRowData();	
	
	}
	
	
public DataModel<Diagnostico>getDiagnosticos(){
	DiagnosticoDAO dao = new DiagnosticoDAO();
	
	
	try {
	List<Diagnostico> lista = dao.GetALL();	
	diagnosticos = new ListDataModel<Diagnostico>(lista);
	} catch (Exception e) {
		
	}	
		
	return diagnosticos;
}
	public void setDiagnsoticos(DataModel<Diagnostico> diagnosticos) { 
		this.diagnosticos = diagnosticos;
	}

	public String deleteDiagnostico(){
		this.diagnostico = diagnosticos.getRowData();	
		String retorno = "erro";
		
		try{
			DiagnosticoDAO dao = new DiagnosticoDAO();
			dao.Delete(diagnostico);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		
		return retorno;
	}
public String updateDiagnostico(){
		
		String retorno = "erro";
		
		try{
			DiagnosticoDAO dao = new DiagnosticoDAO();
			dao.Update(diagnostico);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		return retorno;
	}

public String addDiagnostico() {

String retorno = "erro";

try {
	DiagnosticoDAO dao = new DiagnosticoDAO();
	dao.Salvar(diagnostico);
	retorno = "listar";
	
			
} catch (Exception ex) {
	System.out.println("ERRO + " + ex.getMessage());
}

return retorno;
}
}

