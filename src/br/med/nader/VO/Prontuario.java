package br.med.nader.VO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Prontuario")
public class Prontuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;	
	
	@Column(name="datahora")
	private Date datahora;
				
	@Column (name="observacao")
	private String observacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
		
	/* Um pra um com paciente*/
	@OneToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	/* Um pra um com diagnsotico*/
	@ManyToOne
	@JoinColumn(name="diagnostico_id")
	private Diagnostico diagnostico;
	
		
	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	
	/* N pra 1 com Tratamento*/
	@ManyToOne
	@JoinColumn(name="tratamento_id")
	private Tratamento tratamento;
	
	public Tratamento getTratamento() {
		return tratamento;
		
	} public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}
		
	

}
	