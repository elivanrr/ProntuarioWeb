package br.med.nader.UTILS;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.med.nader.DAO.PacienteDAO;
import br.med.nader.VO.Paciente;



@FacesConverter(value="pacienteConverter")
public class PacienteConverter implements Converter {

 @Override
 public Object getAsObject(FacesContext fc, UIComponent uic, String nome) {
 FacesContext context = FacesContext.getCurrentInstance();
 PacienteDAO pacienteDAO = (PacienteDAO) context.getELContext().getELResolver()
		 .getValue(context.getELContext(), null, "pacienteDAO");
 return pacienteDAO.Get(nome).toString();
 }
 @Override
 public String getAsString(FacesContext fc, UIComponent uic, Object o) {
 return o.toString();
 }

}



