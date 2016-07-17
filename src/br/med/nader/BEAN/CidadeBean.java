package br.med.nader.BEAN;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.med.nader.DAO.CidadeDAO;
import br.med.nader.VO.Cidade;

@ManagedBean(name = "cidBean")
@SessionScoped
public class CidadeBean {
	private Cidade cidade = new Cidade();
	private DataModel<Cidade>cidades;

	public void setCidadees(DataModel<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void novoCidade() {
		cidade = new Cidade();
	}
	
	public void selecionarCidade(){
		this.cidade = cidades.getRowData();
	}
	
	public DataModel<Cidade>getCidadees(){
		CidadeDAO dao = new CidadeDAO();
		
		try {
			List<Cidade> lista = dao.GetALL();
			cidades = new ListDataModel<Cidade>(lista);
		} catch (Exception e) {
			
		}
		
		return cidades;
	}

	public String deleteCidade() {
		this.cidade = cidades.getRowData();

		String retorno = "erro";

		try {
			CidadeDAO dao = new CidadeDAO();
			dao.Delete(cidade);
			retorno = "listar";
		} catch (Exception ex) {
			System.out.println("ERRO + " + ex.getMessage());
		}

		return retorno;
	}

	public String updateCidade() {

		String retorno = "erro";

		try {
			CidadeDAO dao = new CidadeDAO();
			dao.Update(cidade);
			retorno = "listar";
		} catch (Exception ex) {
		}

		return retorno;
	}

	public String addCidade() {

		String retorno = "erro";

		try {
			CidadeDAO dao = new CidadeDAO();
			dao.Salvar(cidade);
			retorno = "listar";
		} catch (Exception ex) {
			System.out.println("ERRO + " + ex.getMessage());
		}

		return retorno;
	}
}

	
	
	
		
		
		
	


