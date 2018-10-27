/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para mapeamento de campos de formulários utilizados por classes com
 * atributos simples
 *
 * @author tiago.teixeira
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapSqlField {

    /**
     * Mapeamento utilizado na query JPQL
     *
     * @return
     */
    public String mapeamento() default "";
    public String condition() default "";
    public String between_partiner() default "";
    public boolean ignore() default false;
    public String as_prefix() default "";
    
}
