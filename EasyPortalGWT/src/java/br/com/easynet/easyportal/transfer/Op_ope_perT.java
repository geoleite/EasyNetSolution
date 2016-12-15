package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Op_ope_perT { 
	 private int per_nr_id;
	 private int ope_nr_id;
	 private int sis_nr_id;
	 private int met_nr_id;
	 public void setPer_nr_id(int per_nr_id) {
		 this.per_nr_id=per_nr_id;
	}
 
	 public int getPer_nr_id() {
		 return per_nr_id;
 	} 
 	 public void setOpe_nr_id(int ope_nr_id) {
		 this.ope_nr_id=ope_nr_id;
	}
 
	 public int getOpe_nr_id() {
		 return ope_nr_id;
 	} 
 	 public void setSis_nr_id(int sis_nr_id) {
		 this.sis_nr_id=sis_nr_id;
	}
 
	 public int getSis_nr_id() {
		 return sis_nr_id;
 	}

    public int getMet_nr_id() {
        return met_nr_id;
    }

    public void setMet_nr_id(int met_nr_id) {
        this.met_nr_id = met_nr_id;
    }
 }