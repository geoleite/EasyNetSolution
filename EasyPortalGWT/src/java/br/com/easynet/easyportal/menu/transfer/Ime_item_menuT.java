package br.com.easynet.easyportal.menu.transfer;

import br.com.easynet.annotation.Conversion;

public class Ime_item_menuT { 
	 private int men_nr_id;
	 private int ime_nr_id;
	 private int ope_nr_id;
	 private String ime_tx_status;
	 private String ime_tx_descricao;
	 private int sis_nr_id;
	 private int ime_nr_ordem;
	 private String ime_tx_op;
         private String ime_tx_url;
	 public void setMen_nr_id(int men_nr_id) {
		 this.men_nr_id=men_nr_id;
	}
 
	 public int getMen_nr_id() {
		 return men_nr_id;
 	} 
 	 public void setIme_nr_id(int ime_nr_id) {
		 this.ime_nr_id=ime_nr_id;
	}
 
	 public int getIme_nr_id() {
		 return ime_nr_id;
 	} 
 	 public void setOpe_nr_id(int ope_nr_id) {
		 this.ope_nr_id=ope_nr_id;
	}
 
	 public int getOpe_nr_id() {
		 return ope_nr_id;
 	} 
 	 public void setIme_tx_status(String ime_tx_status) {
		 this.ime_tx_status=ime_tx_status;
	}
 
	 public String getIme_tx_status() {
		 return ime_tx_status;
 	} 
 	 public void setIme_tx_descricao(String ime_tx_descricao) {
		 this.ime_tx_descricao=ime_tx_descricao;
	}
 
	 public String getIme_tx_descricao() {
		 return ime_tx_descricao;
 	} 
 	 public void setSis_nr_id(int sis_nr_id) {
		 this.sis_nr_id=sis_nr_id;
	}
 
	 public int getSis_nr_id() {
		 return sis_nr_id;
 	} 
 	 public void setIme_nr_ordem(int ime_nr_ordem) {
		 this.ime_nr_ordem=ime_nr_ordem;
	}
 
	 public int getIme_nr_ordem() {
		 return ime_nr_ordem;
 	} 
 	 public void setIme_tx_op(String ime_tx_op) {
		 this.ime_tx_op=ime_tx_op;
	}
 
	 public String getIme_tx_op() {
		 return ime_tx_op;
 	}

    public String getIme_tx_url() {
        return ime_tx_url;
    }

    public void setIme_tx_url(String ime_tx_url) {
        this.ime_tx_url = ime_tx_url;
    }
 }