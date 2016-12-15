package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Sis_sistemaT { 
	 private int sis_nr_id;
	 private String sis_tx_nome;
	 private String sis_tx_descricao;
	 private String sis_tx_status;
	 public void setSis_nr_id(int sis_nr_id) {
		 this.sis_nr_id=sis_nr_id;
	}
 
	 public int getSis_nr_id() {
		 return sis_nr_id;
 	} 
 	 public void setSis_tx_nome(String sis_tx_nome) {
		 this.sis_tx_nome=sis_tx_nome;
	}
 
	 public String getSis_tx_nome() {
		 return sis_tx_nome;
 	} 
 	 public void setSis_tx_descricao(String sis_tx_descricao) {
		 this.sis_tx_descricao=sis_tx_descricao;
	}
 
	 public String getSis_tx_descricao() {
		 return sis_tx_descricao;
 	} 
 	 public void setSis_tx_status(String sis_tx_status) {
		 this.sis_tx_status=sis_tx_status;
	}
 
	 public String getSis_tx_status() {
		 return sis_tx_status;
 	} 
 }