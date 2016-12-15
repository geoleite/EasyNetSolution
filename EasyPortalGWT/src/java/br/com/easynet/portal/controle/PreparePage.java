/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.portal.controle;

/**
 *
 * @author geoleite
 */
public class PreparePage {

    private String page;
    private StringBuffer sbu = new StringBuffer();
    private StringBuilder sb = new StringBuilder();
    public String prepare(String pathDefault, String page) {
        
        return null;
    } 
    
    /**
     * Muda os links
     * @return
     */
    private String replaceLink() {
        int pos = page.indexOf("href");
        while (pos > -1) {
            
        }
        return null;
    }

    public String getPage() {
        return page;
    }
}
