/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.dao;

/**
 *
 * @author geoleite
 */
public interface IEntityDAO {

    public void beginTransaction();
    public void commit();
    public void rollback();
}
