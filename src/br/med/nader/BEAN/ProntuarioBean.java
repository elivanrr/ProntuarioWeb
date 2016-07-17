package br.med.nader.BEAN;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.med.nader.DAO.DiagnosticoDAO;
import br.med.nader.DAO.PacienteDAO;
import br.med.nader.DAO.ProntuarioDAO;
import br.med.nader.DAO.TratamentoDAO;
import br.med.nader.VO.Paciente;
import br.med.nader.VO.Prontuario;
import br.med.nader.VO.Tratamento;
import br.med.nader.VO.Diagnostico;



@ManagedBean(name="ptoBean")
@SessionScoped
public class ProntuarioBean {
	private Prontuario prontuario = new Prontuario();
	private DataModel<Prontuario>prontuarios;
	private int diagnostico_id;
	private int paciente_id;
	private int tratamento_id;
		
	public void setProntuario(DataModel<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}
	
	public int getDiagnostico_id() {
	   return diagnostico_id;
	}

	public void setDiagnostico_id(int diagnostico_id) {
		this.diagnostico_id = diagnostico_id;
	}

	public int getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(int paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	public int getTratamento_id() {
		return tratamento_id;
	}

	public void setTratamento_id(int tratamento_id) {
		this.tratamento_id = tratamento_id;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario Prontuario) {
		this.prontuario = Prontuario;
	}

	public void novoProntuario(){
		prontuario = new Prontuario();
	}
	
	public void selecionarProntuario(){
	   this.prontuario = prontuarios.getRowData();	
	
	}
	
	
public DataModel<Prontuario>getProntuarios(){
	ProntuarioDAO dao = new ProntuarioDAO();
	
	
	try {
	List<Prontuario> lista = dao.GetALL();	
	prontuarios = new ListDataModel<Prontuario>(lista);
	} catch (Exception e) {
		
	}	
		
	return prontuarios;
	
}
	public void setProntuarios(DataModel<Prontuario> prontuarios) {
	this.prontuarios = prontuarios;
}

	public String deleteProntuario(){
		this.prontuario = prontuarios.getRowData();	
		String retorno = "erro";
		
		try{
			ProntuarioDAO dao = new ProntuarioDAO();
			dao.Delete(prontuario);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		
		return retorno;
	}
public String updateProntuario(){
		
		String retorno = "erro";
		
		try{
			ProntuarioDAO dao = new ProntuarioDAO();
			dao.Update(prontuario);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		return retorno;
	}
	
public String addProntuario() {

	String retorno = "erro";

	try {
		ProntuarioDAO dao = new ProntuarioDAO();
	    PacienteDAO pacienteDao = new PacienteDAO();
		prontuario.setPaciente(pacienteDao.Get(paciente_id)); 
		DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
	    prontuario.setDiagnostico(diagnosticoDao.Get(diagnostico_id));
		TratamentoDAO tratamentoDao = new TratamentoDAO();
		prontuario.setTratamento(tratamentoDao.Get(tratamento_id));
		dao.Salvar(prontuario);
		retorno = "listar";
		
				
	} catch (Exception ex) {
		System.out.println("ERRO + " + ex.getMessage());
	}

	return retorno;
}

public Collection<SelectItem>getCarregarPaciente(){
	PacienteDAO dao = new PacienteDAO();
	Collection<SelectItem> lista = new ArrayList<SelectItem>();
	lista.add(new SelectItem("--SELECIONE--"));
	List<Paciente> listaPaciente = dao.GetALL();
	
	for (int i = 0; i < listaPaciente.size(); i++) {
	lista.add(new SelectItem(listaPaciente.get(i).getId(), listaPaciente.get(i).getNome()));
	}
return lista;
}


public Collection<SelectItem>getCarregarDiagnostico(){
	DiagnosticoDAO dao = new DiagnosticoDAO();
	Collection<SelectItem> lista = new ArrayList<SelectItem>();
	lista.add(new SelectItem("--SELECIONE--"));
	List<Diagnostico> listaDiagnostico = dao.GetALL();
	
	for (int i = 0; i < listaDiagnostico.size(); i++) {
	lista.add(new SelectItem(listaDiagnostico.get(i).getId(), listaDiagnostico.get(i).getDescricao()));
	}
return lista;

}
public Collection<SelectItem>getCarregarTratamento(){
	TratamentoDAO dao = new TratamentoDAO();
	Collection<SelectItem> lista = new ArrayList<SelectItem>();
	lista.add(new SelectItem("--SELECIONE--"));
	List<Tratamento> listaTratamento = dao.GetALL();
	
	for (int i = 0; i < listaTratamento.size(); i++) {
	lista.add(new SelectItem(listaTratamento.get(i).getId(), listaTratamento.get(i).getDescricao()));
	}
return lista;
}
	
}

