package br.com.easynet.portal.transfer;

import br.com.easynet.annotation.Conversion;

public class Can_canalT { 
	 private int can_nr_id;
	 private String can_tx_nome;
	 private String can_tx_descricao;
	 private String can_tx_url;
	 private String can_tx_status="A";
	 private String can_tx_border="N";
	 private String can_tx_estado="NOR";
	 private String can_tx_iframe = "N";
	 public void setCan_nr_id(int can_nr_id) {
		 this.can_nr_id=can_nr_id;
	}
 
	 public int getCan_nr_id() {
		 return can_nr_id;
 	} 
 	 public void setCan_tx_nome(String can_tx_nome) {
		 this.can_tx_nome=can_tx_nome;
	}
 
	 public String getCan_tx_nome() {
		 return can_tx_nome;
 	} 
 	 public void setCan_tx_descricao(String can_tx_descricao) {
		 this.can_tx_descricao=can_tx_descricao;
	}
 
	 public String getCan_tx_descricao() {
		 return can_tx_descricao;
 	} 
 	 public void setCan_tx_url(String can_tx_url) {
		 this.can_tx_url=can_tx_url;
	}
 
	 public String getCan_tx_url() {
		 return can_tx_url;
 	} 
 	 public void setCan_tx_status(String can_tx_status) {
		 this.can_tx_status=can_tx_status;
	}
 
	 public String getCan_tx_status() {
		 return can_tx_status;
 	} 
 	 public void setCan_tx_border(String can_tx_border) {
		 this.can_tx_border=can_tx_border;
	}
 
	 public String getCan_tx_border() {
		 return can_tx_border;
 	} 
 	 public void setCan_tx_estado(String can_tx_estado) {
		 this.can_tx_estado=can_tx_estado;
	}
 
	 public String getCan_tx_estado() {
		 return can_tx_estado;
 	} 
 	 public void setCan_tx_iframe(String can_tx_iframe) {
		 this.can_tx_iframe=can_tx_iframe;
	}
 
	 public String getCan_tx_iframe() {
		 return can_tx_iframe;
 	} 
 }