/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author tiago.teixeira
 */
public class CastFactory {

    public static Object cast(Object object, Class<?> classe) {
        try {
            if (Objects.equals(Long.class, classe) | Objects.equals(long.class, classe)) {
                object = object != null ? Long.parseLong(object.toString()) : null;
            } else if (Objects.equals(Integer.class, classe) | Objects.equals(int.class, classe)) {
                object = object != null ? Integer.parseInt(object.toString()) : null;
            } else if (Objects.equals(Boolean.class, classe) | Objects.equals(boolean.class, classe)) {
                object = object != null ? Boolean.parseBoolean(object.toString()) : false;
            } else if (Objects.equals(String.class, classe)) {
                try {
                    if (object instanceof ZonedDateTime) {
                        object = ((ZonedDateTime) object).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } else {
                        object = object != null ? String.valueOf(object) : "";
                    }
                } catch (Exception ex) {
                    object = object.toString();
                }
            } else if (Objects.equals(LocalDate.class, classe)) {
                object = object != null ? LocalDate.parse(object.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
            } else if (Objects.equals(LocalTime.class, classe)) {
                object = object != null ? LocalTime.parse(object.toString(), DateTimeFormatter.ofPattern("HH:mm")) : null;
            } else if (Objects.equals(ZonedDateTime.class, classe)) {
                object = object != null ? ZonedDateTime.of(LocalDate.parse(object.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalTime.now(), ZoneId.systemDefault()): null;
            } else if (Objects.equals(BigDecimal.class, classe)) {
                object = object != null ? new BigDecimal(object.toString().replaceAll("^[\\w][\\p{Punct}][\\p{Space}]", "")) : null;
            }
            return object;
        } catch (Exception e) {
            return object;
        }
    }

    public static Class castReference(Class classe) {
        if (Objects.equals(Integer.class, classe)) {
            return int.class;
        } else if (Objects.equals(Long.class, classe)) {
            return long.class;
        } else if (Objects.equals(Boolean.class, classe)) {
            return boolean.class;
        }
        return classe;
    }

}
