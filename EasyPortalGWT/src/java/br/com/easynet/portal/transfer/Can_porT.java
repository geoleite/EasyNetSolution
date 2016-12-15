package br.com.easynet.portal.transfer;

import br.com.easynet.annotation.Conversion;

public class Can_porT { 
	 private int por_nr_id;
	 private int can_nr_id;
	 private int cp_nr_ordem;
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
 	 public void setCp_nr_ordem(int cp_nr_ordem) {
		 this.cp_nr_ordem=cp_nr_ordem;
	}
 
	 public int getCp_nr_ordem() {
		 return cp_nr_ordem;
 	} 
 }