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
	
	@Column(name = "PAG_RESERVA")
	@JoinColumn(name = "RES_COD")
	@ManyToOne
	private Reserva pag_reserva;
	
	@Column(name = "PAG_STATUS")
	@JoinColumn(name = "PST_COD")
	@ManyToOne
	private PagamentoStatus pag_status;
	
	@Column(name = "PAG_DATA_ICS")
	@NotNull
	private Date pag_data_ics;
	
	@Column(name = "PAG_DATA_STATUS")
	@NotNull
	private Date pag_data_status;
	
	@Column(name = "PAG_METODO")
	private String pag_metodo;

}
