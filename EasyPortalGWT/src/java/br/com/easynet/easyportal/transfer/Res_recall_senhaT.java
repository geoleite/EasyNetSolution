package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Res_recall_senhaT { 
	 private int usu_nr_id;
	 private String res_tx_pergunta;
	 private String res_tx_resposta;
	@Conversion(classe="br.com.easynet.convesion.ConvertTimestamp", format="dd/MM/yyyy HH:mm")
	 private java.sql.Timestamp res_dt_ultimoacesso;
	 private int res_nr_tentativas;
	 public void setUsu_nr_id(int usu_nr_id) {
		 this.usu_nr_id=usu_nr_id;
	}
 
	 public int getUsu_nr_id() {
		 return usu_nr_id;
 	} 
 	 public void setRes_tx_pergunta(String res_tx_pergunta) {
		 this.res_tx_pergunta=res_tx_pergunta;
	}
 
	 public String getRes_tx_pergunta() {
		 return res_tx_pergunta;
 	} 
 	 public void setRes_tx_resposta(String res_tx_resposta) {
		 this.res_tx_resposta=res_tx_resposta;
	}
 
	 public String getRes_tx_resposta() {
		 return res_tx_resposta;
 	} 
 	 public void setRes_dt_ultimoacesso(java.sql.Timestamp res_dt_ultimoacesso) {
		 this.res_dt_ultimoacesso=res_dt_ultimoacesso;
	}
 
	 public java.sql.Timestamp getRes_dt_ultimoacesso() {
		 return res_dt_ultimoacesso;
 	} 
 	 public void setRes_nr_tentativas(int res_nr_tentativas) {
		 this.res_nr_tentativas=res_nr_tentativas;
	}
 
	 public int getRes_nr_tentativas() {
		 return res_nr_tentativas;
 	} 
 }