package br.med.nader.BEAN;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.med.nader.DAO.TratamentoDAO;
import br.med.nader.VO.Tratamento;
import br.med.nader.BEAN.TratamentoBean;




@ManagedBean(name="tratBean")
@SessionScoped
public class TratamentoBean {
	private Tratamento tratamento = new Tratamento();
	private DataModel<Tratamento>tratamentos;
	
		
	public void setTratamentos(DataModel<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public void novoTratamento(){
		tratamento = new Tratamento();
	}
	
	public void selecionarTratamento(){
	   this.tratamento = tratamentos.getRowData();	
	
	}
	
	public DataModel<Tratamento>getTratamentos(){
	TratamentoDAO dao = new TratamentoDAO();
		
	try {
	List<Tratamento> lista = dao.GetALL();	
	tratamentos = new ListDataModel<Tratamento>(lista);
	} catch (Exception e) {
		
	}	
		
	return tratamentos;
	
}

	public String deleteTratamento(){
		this.tratamento = tratamentos.getRowData();	
		String retorno = "erro";
		
		try{
			TratamentoDAO dao = new TratamentoDAO();
			dao.Delete(tratamento);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		
		return retorno;
	}
public String updateTratamento(){
		
		String retorno = "erro";
		
		try{
			TratamentoDAO dao = new TratamentoDAO();
			dao.Update(tratamento);
			retorno = "listar";
		}catch (Exception ex) {
			System.out.println("ERRO +" + ex.getMessage());
		}
		return retorno;
	}

public String addTratamento() {

String retorno = "erro";

try {
	TratamentoDAO dao = new TratamentoDAO();
	dao.Salvar(tratamento);
	retorno = "listar";
	
			
} catch (Exception ex) {
	System.out.println("ERRO + " + ex.getMessage());
}

return retorno;
}
}

