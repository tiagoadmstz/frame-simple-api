/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.dal;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tiago D. Teixeira
 * @param <T>
 */
public interface EntityManagerHelper<T> {

    public final static int SALVAR = 0, ATUALIZAR = 1, DELETAR = 2;
    public final static String DERBYDB_PU = "DERBYDB_PU", ORACLE11G_PU = "ORACLE11G_PU";

    public boolean getOperation(int operation_type, Object object, String persistence_unit);

    public EntityManager getSession(String persistence_unit);

    public void closeSession(String persistence_unit);

    public void closeAll();

    public Connection getConnection(String persistence_unit);

    public List<?> getObjectList(String strHQL, String persistence_unit);

    public Optional<List<?>> getObjectList(Class entity, String strHQL, Map<String, Object> parameters, String persistence_unit);

    public List<?> getObjectList(String strHQL, String strParam, Object valor, String persistence_unit);

    public Optional<List<?>> getObjectListNamedQuery(Class classType, String namedQuery, String[] strParam, Object[] valor, String persistence_unit);

    public Optional<?> getObjectNamedQuery(Class classType, String namedQuery, String strParam, Object valor, String persistence_unit);

    public List<?> getObjectList(String strHQL, String strParam, Boolean valor, String persistence_unit);

    public Object getObject(String strHQL, String persistence_unit);

    public Object getObject(String strHQL, String strParam, Object valor, String persistence_unit);

    public Object getObject(String strHQL, String[] strParam, String[] valor, String persistence_unit);

    public default Optional<List<?>> getDinamicSearch(Class entity, String strHQL, Map<String, Object> parameters, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            Query q = session.createQuery(strHQL, entity);
            session.getEntityManagerFactory().addNamedQuery("dynamicquery", q);
            Query query = session.createNamedQuery("dynamicquery", entity);
            parameters.forEach((k, v) -> query.setParameter(k, v));
            session.getTransaction().begin();
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return Optional.ofNullable(objects);
        } catch (Exception e) {
            e.printStackTrace();
            this.closeSession(persistence_unit);
            return Optional.empty();
        }
    }

}
