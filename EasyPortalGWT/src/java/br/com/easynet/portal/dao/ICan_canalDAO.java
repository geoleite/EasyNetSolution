package br.com.easynet.portal.dao;

import br.com.easynet.database.DataSet;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public interface ICan_canalDAO {

    public void insert(Can_canalT can_canalT) throws Exception;

    public void update(Can_canalT can_canalT) throws Exception;

    public void delete(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Can_canalT> getAll() throws Exception;

    public List<Can_canalT> getAllPortal(Por_portalT porT) throws Exception;

    public List<Can_canalT> getAllNaoPortal(Por_portalT porT) throws Exception;

    public List<Can_canalT> getById(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByPortal(Por_portalT portalT) throws Exception;

    public List<Can_canalT> getByCan_tx_nome(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_descricao(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_url(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_status(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_border(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_estado(Can_canalT can_canalT) throws Exception;

    public List<Can_canalT> getByCan_tx_iframe(Can_canalT can_canalT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}