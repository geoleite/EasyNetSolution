package br.com.easynet.portal.transfer;

import br.com.easynet.annotation.Conversion;

public class Por_portalT { 
	 private int por_nr_id;
	 private String por_tx_nome;
	 private String por_tx_status="A";
	 private int por_nr_colunas=1;
	 public void setPor_nr_id(int por_nr_id) {
		 this.por_nr_id=por_nr_id;
	}
 
	 public int getPor_nr_id() {
		 return por_nr_id;
 	} 
 	 public void setPor_tx_nome(String por_tx_nome) {
		 this.por_tx_nome=por_tx_nome;
	}
 
	 public String getPor_tx_nome() {
		 return por_tx_nome;
 	} 
 	 public void setPor_tx_status(String por_tx_status) {
		 this.por_tx_status=por_tx_status;
	}
 
	 public String getPor_tx_status() {
		 return por_tx_status;
 	} 
 	 public void setPor_nr_colunas(int por_nr_colunas) {
		 this.por_nr_colunas=por_nr_colunas;
	}
 
	 public int getPor_nr_colunas() {
		 return por_nr_colunas;
 	} 
 }