package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Met_metodoT { 
	 private int ope_nr_id;
	 private int met_nr_id;
	 private String met_tx_metodo;
	 private String met_tx_status;
	 private int sis_nr_id;
         private String met_tx_descricao;
	 public void setOpe_nr_id(int ope_nr_id) {
		 this.ope_nr_id=ope_nr_id;
	}
 
	 public int getOpe_nr_id() {
		 return ope_nr_id;
 	} 
 	 public void setMet_nr_id(int met_nr_id) {
		 this.met_nr_id=met_nr_id;
	}
 
	 public int getMet_nr_id() {
		 return met_nr_id;
 	} 
 	 public void setMet_tx_metodo(String met_tx_metodo) {
		 this.met_tx_metodo=met_tx_metodo;
	}
 
	 public String getMet_tx_metodo() {
		 return met_tx_metodo;
 	} 
 	 public void setMet_tx_status(String met_tx_status) {
		 this.met_tx_status=met_tx_status;
	}
 
	 public String getMet_tx_status() {
		 return met_tx_status;
 	} 
 	 public void setSis_nr_id(int sis_nr_id) {
		 this.sis_nr_id=sis_nr_id;
	}
 
	 public int getSis_nr_id() {
		 return sis_nr_id;
 	}

    /**
     * @return the met_tx_descricao
     */
    public String getMet_tx_descricao() {
        return met_tx_descricao;
    }

    /**
     * @param met_tx_descricao the met_tx_descricao to set
     */
    public void setMet_tx_descricao(String met_tx_descricao) {
        this.met_tx_descricao = met_tx_descricao;
    }
 }