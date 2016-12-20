package br.com.i9.portal.server.relatorios.res_recall_senha;

import br.com.i9.portal.server.bl.*;
import br.com.i9.portal.server.relatorios.RelatorioBase;
import br.com.i9.portal.client.portal.portal.transfer.*;
import java.util.List;
import java.util.ArrayList;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author i9ti
 */
public class Res_recall_senhaReport extends RelatorioBase {

    private Res_recall_senhaTGWT res_recall_senhaT = new Res_recall_senhaTGWT();

    @Override
    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    @Override	
    public void imprimir() {
        try {

            List<String> lisStr = new ArrayList<String>();
            lisStr.add("uma ocorrencia");

            jRDataSource = new JRBeanCollectionDataSource(lisStr);

            Res_recall_senhaBL res_recall_senhaBL = new Res_recall_senhaBL();
            List<Res_recall_senhaTGWT> list = res_recall_senhaBL.consult();
            //Definindo o dataset passando a lista de clientes
            JRBeanCollectionDataSource dataSetRes_recall_senha = new JRBeanCollectionDataSource(list);
            //Atribuindo o dataSet ao parametro "listRes_recall_senha"
            getParameters().put("listRes_recall_senha", dataSetRes_recall_senha);

            if (getTipo().equalsIgnoreCase("XLS")) {
                url = Res_recall_senhaReport.class.getResource("Res_recall_senha.jasper");
            } else {
                is = Res_recall_senhaReport.class.getResourceAsStream("Res_recall_senha.jasper");
            }

            defineParametros();
            print("Res_recall_senha", "Relacao de Res_recall_senha");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void defineParametros() {
        try {
	  //TODO: Parametros para o relatorio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRes_recall_senhaTGWT(Res_recall_senhaTGWT res_recall_senhaT) {
    	this.res_recall_senhaT = res_recall_senhaT;  
    }

    public Res_recall_senhaTGWT getRes_recall_senhaTGWT() {
    	return res_recall_senhaT;  
    }

}
