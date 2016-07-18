package br.med.nader.BEAN;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.SessionFactory;

import br.med.nader.DAO.DAO;
import br.med.nader.DAO.PacienteDAO;
import br.med.nader.UTILS.CompleteNome;
import br.med.nader.VO.Paciente;

@ManagedBean(name = "relpacBean")
@RequestScoped
public class RelatorioPacBean {
	@SuppressWarnings("unused")
	private static SessionFactory sessionFactory;
    private Paciente paciente;


  //  private PacienteDAO pacienteDAO = new PacienteDAO();
  //  private List<Paciente> pacientes = new ArrayList<Paciente>();
  	
    public Paciente getPaciente() {
    	if (paciente == null) {
			paciente = new Paciente();
		}
		return paciente;
	}
    public void setPaciente(Paciente paciente){
    	this.paciente = paciente;
    }
   /*
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
*/
				
	public void emitir() {
		
		try {

				
			String reportSource = "/home/elivan/git/ProntuarioWeb/WebContent/WEB-INF/report/relProntuarioPaciente.jasper";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("PacienteNome", this.paciente.getNome());
			// Connection connection = (Connection) new
			// ConnectionFactory().getConnection();
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(reportSource);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					reportSource, params, DAO.getConnection());

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();
			ServletOutputStream responseStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"Relatorio de Prontuario.pdf\"");

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					responseStream);
			responseStream.flush();
			responseStream.close();
			context.renderResponse();
			context.responseComplete();

			// JasperViewer.viewReport(jasperPrint);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	

}
