package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Ope_operacaoT { 
	 private int ope_nr_id;
	 private int sis_nr_id;
	 private String ope_tx_nome;
	 private String ope_tx_status;
	 private String ope_tx_url;
	 private String ope_tx_descricao;
	 private String ope_tx_classe;
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
 	 public void setOpe_tx_nome(String ope_tx_nome) {
		 this.ope_tx_nome=ope_tx_nome;
	}
 
	 public String getOpe_tx_nome() {
		 return ope_tx_nome;
 	} 
 	 public void setOpe_tx_status(String ope_tx_status) {
		 this.ope_tx_status=ope_tx_status;
	}
 
	 public String getOpe_tx_status() {
		 return ope_tx_status;
 	} 
 	 public void setOpe_tx_url(String ope_tx_url) {
		 this.ope_tx_url=ope_tx_url;
	}
 
	 public String getOpe_tx_url() {
		 return ope_tx_url;
 	} 
 	 public void setOpe_tx_descricao(String ope_tx_descricao) {
		 this.ope_tx_descricao=ope_tx_descricao;
	}
 
	 public String getOpe_tx_descricao() {
		 return ope_tx_descricao;
 	}

    public String getOpe_tx_classe() {
        return ope_tx_classe;
    }

    public void setOpe_tx_classe(String ope_tx_classe) {
        this.ope_tx_classe = ope_tx_classe;
    }
 }