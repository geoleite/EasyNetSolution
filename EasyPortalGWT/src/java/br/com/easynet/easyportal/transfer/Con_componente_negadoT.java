package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Con_componente_negadoT { 
	 private int con_nr_id;
	 private int int_nr_id;
	 private int per_nr_id;
         private String con_tx_nome;
	 private String con_tx_visivel;
	 private String con_tx_ativo;
	 public void setCon_nr_id(int con_nr_id) {
		 this.con_nr_id=con_nr_id;
	}
 
	 public int getCon_nr_id() {
		 return con_nr_id;
 	} 
 	 public void setInt_nr_id(int int_nr_id) {
		 this.int_nr_id=int_nr_id;
	}
 
	 public int getInt_nr_id() {
		 return int_nr_id;
 	} 
 	 public void setPer_nr_id(int per_nr_id) {
		 this.per_nr_id=per_nr_id;
	}
 
	 public int getPer_nr_id() {
		 return per_nr_id;
 	} 
 	 public void setCon_tx_visivel(String con_tx_visivel) {
		 this.con_tx_visivel=con_tx_visivel;
	}
 
	 public String getCon_tx_visivel() {
		 return con_tx_visivel;
 	} 
 	 public void setCon_tx_ativo(String con_tx_ativo) {
		 this.con_tx_ativo=con_tx_ativo;
	}
 
	 public String getCon_tx_ativo() {
		 return con_tx_ativo;
 	}

    /**
     * @return the con_tx_nome
     */
    public String getCon_tx_nome() {
        return con_tx_nome;
    }

    /**
     * @param con_tx_nome the con_tx_nome to set
     */
    public void setCon_tx_nome(String con_tx_nome) {
        this.con_tx_nome = con_tx_nome;
    }
 }