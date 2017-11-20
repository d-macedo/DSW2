package br.edu.ifsp.ingresso.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "PAG_COD")
	private int pag_cod;
	
	
	@JoinColumn(name = "PAG_RESERVA", referencedColumnName = "RES_COD")
	@ManyToOne
	private Reserva pag_reserva;
	
	
	@JoinColumn(name = "PAG_STATUS",referencedColumnName = "PST_COD")
	@ManyToOne
	private PagamentoStatus pag_status;
	
	@Column(name = "PAG_DATA_ICS")
	@NotNull
	private Date pag_data_ics;
	
	@Column(name = "PAG_DATA_STATUS")
	private Date pag_data_status;
	
	@Column(name = "PAG_METODO")
	private String pag_metodo;

	public int getPag_cod() {
		return pag_cod;
	}

	public void setPag_cod(int pag_cod) {
		this.pag_cod = pag_cod;
	}

	public Reserva getPag_reserva() {
		return pag_reserva;
	}

	public void setPag_reserva(Reserva pag_reserva) {
		this.pag_reserva = pag_reserva;
	}

	public PagamentoStatus getPag_status() {
		return pag_status;
	}

	public void setPag_status(PagamentoStatus pag_status) {
		this.pag_status = pag_status;
	}

	public Date getPag_data_ics() {
		return pag_data_ics;
	}

	public void setPag_data_ics(Date pag_data_ics) {
		this.pag_data_ics = pag_data_ics;
	}

	public Date getPag_data_status() {
		return pag_data_status;
	}

	public void setPag_data_status(Date pag_data_status) {
		this.pag_data_status = pag_data_status;
	}

	public String getPag_metodo() {
		return pag_metodo;
	}

	public void setPag_metodo(String pag_metodo) {
		this.pag_metodo = pag_metodo;
	}

	public Pagamento() {
		super();
	}

	public Pagamento(int pag_cod, Reserva pag_reserva, PagamentoStatus pag_status, Date pag_data_ics,
			Date pag_data_status, String pag_metodo) {
		super();
		this.pag_cod = pag_cod;
		this.pag_reserva = pag_reserva;
		this.pag_status = pag_status;
		this.pag_data_ics = pag_data_ics;
		this.pag_data_status = pag_data_status;
		this.pag_metodo = pag_metodo;
	}

}
