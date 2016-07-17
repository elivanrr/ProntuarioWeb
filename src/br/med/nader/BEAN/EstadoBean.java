package br.med.nader.BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.med.nader.DAO.EstadoDAO;
import br.med.nader.VO.Estado;



@ManagedBean(name="estBean")
@SessionScoped
public class EstadoBean {
	private Estado estado = new Estado();
	private DataModel<Estado>estados;

	public void setEstados(DataModel<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novoEstado() {
		estado = new Estado();
	}
	
	public void selecionarEstado(){
		this.estado = estados.getRowData();
	}
	
	public DataModel<Estado>getEstados(){
		EstadoDAO dao = new EstadoDAO();
		
		try {
			List<Estado> lista = dao.GetALL();
			estados = new ListDataModel<Estado>(lista);
		} catch (Exception e) {
			
		}
		
		return estados;
	}

	
	public String updateEstado() {

		String retorno = "erro";

		try {
			EstadoDAO dao = new EstadoDAO();
			dao.Update(estado);
			retorno = "listar";
		} catch (Exception ex) {
		}

		return retorno;
	}

	public String addEstado() {

		String retorno = "erro";

		try {
			EstadoDAO dao = new EstadoDAO();
			dao.Salvar(estado);
			retorno = "listar";
		} catch (Exception ex) {
			System.out.println("ERRO + " + ex.getMessage());
		}

		return retorno;
	}
}



