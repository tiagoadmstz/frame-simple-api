/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.dal;

import java.util.Map;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joaosavio
 *
 */
public interface EntityManagerFactoryService {
    
    public EntityManagerFactory getEntityManagerFactory(String persistenceUnitName, Map<String, String> propMap);
    public void closeFactories();

}
