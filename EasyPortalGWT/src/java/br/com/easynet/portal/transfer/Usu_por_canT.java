package br.com.easynet.portal.transfer;

import br.com.easynet.annotation.Conversion;

public class Usu_por_canT { 
	 private int usu_nr_id;
	 private int por_nr_id;
	 private int can_nr_id;
	 private String upc_tx_status;
	 private int upc_nr_ordem;
	 public void setUsu_nr_id(int usu_nr_id) {
		 this.usu_nr_id=usu_nr_id;
	}
 
	 public int getUsu_nr_id() {
		 return usu_nr_id;
 	} 
 	 public void setPor_nr_id(int por_nr_id) {
		 this.por_nr_id=por_nr_id;
	}
 
	 public int getPor_nr_id() {
		 return por_nr_id;
 	} 
 	 public void setCan_nr_id(int can_nr_id) {
		 this.can_nr_id=can_nr_id;
	}
 
	 public int getCan_nr_id() {
		 return can_nr_id;
 	} 
 	 public void setUpc_tx_status(String upc_tx_status) {
		 this.upc_tx_status=upc_tx_status;
	}
 
	 public String getUpc_tx_status() {
		 return upc_tx_status;
 	} 
 	 public void setUpc_nr_ordem(int upc_nr_ordem) {
		 this.upc_nr_ordem=upc_nr_ordem;
	}
 
	 public int getUpc_nr_ordem() {
		 return upc_nr_ordem;
 	} 
 }