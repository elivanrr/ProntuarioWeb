package br.med.nader.UTILS;

import java.util.List;




import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.med.nader.DAO.PacienteDAO;
import br.med.nader.VO.Paciente;


@ManagedBean(name = "selBean")
@RequestScoped
public class SelecionaNome {
private PacienteDAO dao = new PacienteDAO();
private Paciente selected;

public Paciente getSelected() {
	return selected;
}
public void setSelected(Paciente selected) {
	this.selected = selected;
}

public List completePaciente(){
	return dao.GetALL();
}

public String clear(){
	this.selected = null;
	return"";
}


	
}
