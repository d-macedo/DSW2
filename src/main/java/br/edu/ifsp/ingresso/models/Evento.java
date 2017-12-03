package br.edu.ifsp.ingresso.models;

import java.util.Date;

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
@Table(name = "EVENTO")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVE_COD")
	private long eve_cod;

	@Column(name = "EVE_TITULO")
	@NotNull
	private String eve_titulo;

	@Column(name = "EVE_DESCRICAO")
	@NotNull
	private String eve_descricao;

	@Column(name = "EVE_DATA")
	@NotNull
	private Date eve_data;

	@Column(name = "EVE_MAX_INTEIRA")
	@NotNull
	private Integer eve_max_inteira;

	@Column(name = "EVE_MAX_MEIA")
	@NotNull
	private Integer eve_max_meia;

	@Column(name = "EVE_VALOR_INTEIRA")
	@NotNull
	private double eve_valor_inteira;
	
	@Column(name = "EVE_VALOR_MEIA")
	@NotNull
	private double eve_valor_meia;
	
	@Column(name = "EVE_TAXA")
	@NotNull
	private Float eve_taxa;

	@ManyToOne
	@JoinColumn(name = "EVE_LOCAL", referencedColumnName="LOC_COD")
	@NotNull
	private Local eve_local;
	
	@ManyToOne
	@JoinColumn(name = "EVE_CATEGORIA", referencedColumnName="CAT_COD")
	private Categoria eve_categoria;
	
	@JoinColumn(name = "EVE_STATUS",referencedColumnName = "EST_COD")
	@NotNull
	@ManyToOne
	private EventoStatus eve_status;

	@Column(name = "EVE_EXECUTOR")
	private Integer eve_executor;

	@Column(name = "EVE_DATA_ICS")
	private Date eve_data_ics;

	@Column(name = "EVE_GERENTE")
	private Integer eve_gerente;
	
	@Column(name = "EVE_DATA_APROV")
	private Date eve_data_aprov;

	@Column(name = "EVE_IMG_PATH")
	private String eve_img_path;

	public Evento() {

	}

	public Evento(String eve_titulo, String eve_descricao, Date eve_data, Integer eve_max_inteira, Integer eve_max_meia,
			Double eve_valor_inteira, Double eve_valor_meia, Float eve_taxa, Local eve_local, Categoria eve_categoria) {
		this.eve_titulo = eve_titulo;
		this.eve_descricao = eve_descricao;
		this.eve_data = eve_data;
		this.eve_max_inteira = eve_max_inteira;
		this.eve_max_meia = eve_max_meia;
		this.eve_valor_inteira = eve_valor_inteira;
		this.eve_valor_meia = eve_valor_meia;
		this.eve_taxa = eve_taxa;
		this.eve_local = eve_local;
		this.eve_categoria = eve_categoria;
	}

	public long getEve_cod() {
		return eve_cod;
	}

	public void setEve_cod(long eve_cod) {
		this.eve_cod = eve_cod;
	}

	public String getEve_titulo() {
		return eve_titulo;
	}

	public void setEve_titulo(String eve_titulo) {
		this.eve_titulo = eve_titulo;
	}

	public String getEve_descricao() {
		return eve_descricao;
	}

	public void setEve_descricao(String eve_descricao) {
		this.eve_descricao = eve_descricao;
	}

	public Date getEve_data() {
		return eve_data;
	}

	public void setEve_data(Date eve_data) {
		this.eve_data = eve_data;
	}

	public Integer getEve_max_inteira() {
		return eve_max_inteira;
	}

	public void setEve_max_inteira(Integer eve_max_inteira) {
		this.eve_max_inteira = eve_max_inteira;
	}

	public Integer getEve_max_meia() {
		return eve_max_meia;
	}

	public void setEve_max_meia(Integer eve_max_meia) {
		this.eve_max_meia = eve_max_meia;
	}
	
	public Double getEve_valor_inteira() {
		return eve_valor_inteira;
	}
	
	public Double getEve_valor_meia() {
		return eve_valor_meia;
	}
	
	public void setEve_valor_inteira(Double eve_valor_inteira) {
		if(eve_valor_inteira == null) {
			this.eve_valor_inteira = 0;
		}
		else {
			this.eve_valor_inteira = eve_valor_inteira;
		}		
	}
	
	public void setEve_valor_meia(Double eve_valor_meia) {
		if (eve_valor_meia == null) {
			this.eve_valor_meia = 0;
		}
		else {
			this.eve_valor_meia = eve_valor_meia;
		}		
	}

	public Float getEve_taxa() {
		return eve_taxa;
	}

	public void setEve_taxa(Float eve_taxa) {
		this.eve_taxa = eve_taxa;
	}

	public Local getEve_local() {
		return eve_local;
	}

	public void setEve_local(Local eve_local) {
		this.eve_local = eve_local;
	}

	public Categoria getEve_categoria() {
		return eve_categoria;
	}

	public void setEve_categoria(Categoria eve_categoria) {
		this.eve_categoria = eve_categoria;
	}

	public Integer getEve_executor() {
		return eve_executor;
	}

	public void setEve_executor(Integer eve_executor) {
		this.eve_executor = eve_executor;
	}
	
	public EventoStatus getEve_status() {
		return eve_status;
	}

	public void setEve_status(EventoStatus eve_status) {
		this.eve_status = eve_status;
	}
	
	

	public Date getEve_data_ics() {
		return eve_data_ics;
	}

	public void setEve_data_ics(Date eve_data_ics) {
		this.eve_data_ics = eve_data_ics;
	}

	public Integer getEve_gerente() {
		return eve_gerente;
	}

	public void setEve_gerente(Integer eve_gerente) {
		this.eve_gerente = eve_gerente;
	}

	public Date getEve_data_aprov() {
		return eve_data_aprov;
	}

	public void setEve_data_aprov(Date eve_data_aprov) {
		this.eve_data_aprov = eve_data_aprov;
	}

	public String getEve_img_path() {
		return eve_img_path;
	}

	public void setEve_img_path(String eve_img_path) {
		this.eve_img_path = eve_img_path;
	}

	@Override
	public String toString() {
		return "Evento [eve_cod=" + eve_cod + ", eve_titulo=" + eve_titulo + ", eve_descricao=" + eve_descricao
				+ ", eve_data=" + eve_data + ", eve_max_inteira=" + eve_max_inteira + ", eve_max_meia=" + eve_max_meia
				+ ", eve_taxa=" + eve_taxa + ", eve_local=" + eve_local + ", eve_categoria=" + eve_categoria
				+ ", eve_status=" + eve_status + ", eve_executor=" + eve_executor + ", eve_data_ics=" + eve_data_ics
				+ ", eve_gerente=" + eve_gerente + ", eve_data_aprov=" + eve_data_aprov + ", eve_img_path="
				+ eve_img_path + "]";
	}

}
