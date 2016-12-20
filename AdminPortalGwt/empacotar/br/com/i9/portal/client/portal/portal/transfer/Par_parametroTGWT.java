
/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.transfer;

import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.Date;

/**
 *
 * @author geoleite
 */
public class Par_parametroTGWT extends BaseModel {

    public Par_parametroTGWT() {
    }

    public int getSis_nr_id() {
        return ((Integer) get("sis_nr_id")).intValue();
    }

    public void setSis_nr_id(int sis_nr_id) {
        set("sis_nr_id", sis_nr_id);
    }

    public long getPar_nr_id() {
        return ((Long) get("par_nr_id")).longValue();
    }

    public void setPar_nr_id(long par_nr_id) {
        set("par_nr_id", par_nr_id);
    }

    public String getPar_tx_nome() {
        return get("par_tx_nome");
    }

    public void setPar_tx_nome(String par_tx_nome) {
        set("par_tx_nome", par_tx_nome);
    }

    public String getPar_tx_valor() {
        return get("par_tx_valor");
    }

    public void setPar_tx_valor(String par_tx_valor) {
        set("par_tx_valor", par_tx_valor);
    }
    
    //
        public String getPar_tx_secreto() {
        return get("par_tx_secreto");
    }

    public void setPar_tx_secreto(String par_tx_secreto) {
        set("par_tx_secreto", par_tx_secreto);
    }
}
