/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;

/**
 *
 * @author tiago.teixeira
 */
public class Cadastro_Telas {

    public static void main(String[] args) {
        new Cadastro_Telas().cadastrarTelas();
    }

    public void cadastrarTelas() {
        try {
            File dir = new File(Thread.currentThread().getContextClassLoader().getResource("br/com/sres/frames").getFile());
            Arrays.asList(dir.listFiles()).stream()
                    .map(File::getName)
                    .filter(nm -> nm.contains("Form_") && nm.contains(".class") && !nm.contains("$"))
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
