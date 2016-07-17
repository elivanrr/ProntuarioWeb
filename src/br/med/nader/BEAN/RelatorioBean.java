package br.med.nader.BEAN;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.SessionFactory;

import br.med.nader.DAO.DAO;
import br.med.nader.VO.Paciente;

@ManagedBean(name = "relBean")
@RequestScoped
public class RelatorioBean {
	@SuppressWarnings("unused")
	private static SessionFactory sessionFactory;

	
	public void emitir(Object NomePaciente) throws IOException, SQLException {
		
		try {

				
			String reportSource = "/home/elivan/Documentos/Desenvolvimento/JAVAJSF/workspace/ProntuarioWeb/WebContent/WEB-INF/report/relProntuario.jasper";
			Map<String, Object> params = new HashMap<String, Object>();
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
