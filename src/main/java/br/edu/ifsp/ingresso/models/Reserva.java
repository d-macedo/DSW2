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
@Table(name = "RESERVA")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RES_COD")
	@NotNull
	private long res_cod_int;
	
	@Column(name = "RES_COMPRADOR")
	@JoinColumn(name  = "USU_COD")
	@ManyToOne
	private Usuario res_comprador;
	
	@Column(name = "RES_EVENTO")
	@JoinColumn(name = "EVE_COD")
	@ManyToOne
	private Evento res_evento;
	
	@Column(name = "RES_DATA_ICS")
	@NotNull
	private Date res_data_ics;
	
	@Column(name = "RES_DATA_EXP")
	@NotNull
	private Date res_data_exp;
	
	@Column(name = "RES_QTD_INTEIRA")
	@NotNull
	private int res_qtd_inteira;
	
	@Column(name = "RES_QTD_MEIA")
	@NotNull
	private int res_qtd_meia;
	
	@Column(name = "RES_VALOR_TOTAL")
	@NotNull
	private double res_valor_total;
	
	@Column(name = "RES_STATUS")
	@JoinColumn( name = "RST_COD")
	@ManyToOne
	private ReservaStatus res_status;
	
	

	public long getRes_cod_int() {
		return res_cod_int;
	}

	public void setRes_cod_int(long res_cod_int) {
		this.res_cod_int = res_cod_int;
	}

	public Usuario getRes_comprador() {
		return res_comprador;
	}

	public void setRes_comprador(Usuario res_comprador) {
		this.res_comprador = res_comprador;
	}

	public Evento getRes_evento() {
		return res_evento;
	}

	public void setRes_evento(Evento res_evento) {
		this.res_evento = res_evento;
	}

	public Date getRes_data_ics() {
		return res_data_ics;
	}

	public void setRes_data_ics(Date res_data_ics) {
		this.res_data_ics = res_data_ics;
	}

	public Date getRes_data_exp() {
		return res_data_exp;
	}

	public void setRes_data_exp(Date res_data_exp) {
		this.res_data_exp = res_data_exp;
	}

	public int getRes_qtd_inteira() {
		return res_qtd_inteira;
	}

	public void setRes_qtd_inteira(int res_qtd_inteira) {
		this.res_qtd_inteira = res_qtd_inteira;
	}

	public int getRes_qtd_meia() {
		return res_qtd_meia;
	}

	public void setRes_qtd_meia(int res_qtd_meia) {
		this.res_qtd_meia = res_qtd_meia;
	}

	public double getRes_valor_total() {
		return res_valor_total;
	}

	public void setRes_valor_total(double res_valor_total) {
		this.res_valor_total = res_valor_total;
	}
	
	public ReservaStatus getRes_status() {
		return res_status;
	}

	public void setRes_status(ReservaStatus res_status) {
		this.res_status = res_status;
	}
	
	public Reserva() {
		super();
	}
	
	public Reserva(Date res_data_ics, Date res_data_exp, int res_qtd_inteira, int res_qtd_meia,
			double res_valor_total) {
		super();
		this.res_data_ics = res_data_ics;
		this.res_data_exp = res_data_exp;
		this.res_qtd_inteira = res_qtd_inteira;
		this.res_qtd_meia = res_qtd_meia;
		this.res_valor_total = res_valor_total;
	}

	
	@Override
	public String toString() {
		return "Reserva [res_cod_int=" + res_cod_int + ", res_comprador=" + res_comprador + ", res_evento=" + res_evento
				+ ", res_data_ics=" + res_data_ics + ", res_data_exp=" + res_data_exp + ", res_qtd_inteira="
				+ res_qtd_inteira + ", res_qtd_meia=" + res_qtd_meia + ", res_valor_total=" + res_valor_total
				+ ", res_status=" + res_status + "]";
	}
	
	
	
	
}
