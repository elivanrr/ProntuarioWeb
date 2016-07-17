package br.med.nader.BEAN;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converteData")
public class ConverteData implements Converter {

	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			return df.parse(arg2);
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try {
			df.setLenient(false); 
			return df.format((Date) arg2);
		} catch (Exception e) {
			return "";
		}
	}
}
