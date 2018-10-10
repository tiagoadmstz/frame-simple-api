/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author tiago.teixeira
 */
public abstract class GlasgowUtil {

    public static final int ABERTURA_OCULAR = 0, RESPOSTA_VERBAL = 1, RESPOSTA_MOTORA = 2;

    public static String calcularEscalaComaGlasgow(String aberturaOcular, String respostaVerbal, String respostaMotora) {
        return calcularEscalaComaGlasgow(Integer.parseInt(aberturaOcular), Integer.parseInt(respostaVerbal), Integer.parseInt(respostaMotora));
    }

    public static String calcularEscalaComaGlasgow(Integer aberturaOcular, Integer respostaVerbal, Integer respostaMotora) {
        Integer soma = aberturaOcular + respostaVerbal + respostaMotora;
        return soma.toString();
    }

    public static List<Map<String, List<String>>> montarComboBoxEscalas(JComboBox aberturaOcular, JComboBox respostaVerbal, JComboBox respostaMotora) {
        Map<String, List<String>> mapAbOcular = new HashMap();
        Map<String, List<String>> mapRespVerbal = new HashMap();
        Map<String, List<String>> mapRespMotora = new HashMap();

        aberturaOcular.addItem("< 1 ano");
        aberturaOcular.addItem("> 1 ano");
        respostaVerbal.addItem("0-2 anos");
        respostaVerbal.addItem("2-5 anos");
        respostaVerbal.addItem("> 5 anos");
        respostaMotora.addItem("< 1 ano");
        respostaMotora.addItem("> 1 ano");

        //Abertura Ocular
        //> 1 ano
        mapAbOcular.put("> 1 ano", Arrays.asList(new String[]{
            "4-Espontânea",
            "3-À solicitação verbal",
            "2-À dor",
            "1-Ausente"
        }));
        //< 1 ano
        mapAbOcular.put("< 1 ano", Arrays.asList(new String[]{
            "4-Espontânea",
            "3-Ao estímulo verbal alto",
            "2-À dor",
            "1-Ausente"
        }));
        //Resposta Verbal
        //> 5 anos
        mapRespVerbal.put("> 5 anos", Arrays.asList(new String[]{
            "5-Orientado",
            "4-Confuso",
            "3-Palavras desconexas",
            "2-Sons incompreensíveis",
            "1-Ausente"
        }));
        // 2-5 anos
        mapRespVerbal.put("2-5 anos", Arrays.asList(new String[]{
            "5-Frases e palavras apropriadas",
            "4-Palavras inapropriadas",
            "3-Choros e gritos",
            "2-Grunhidos",
            "1-Ausente"
        }));
        // 0-2 anos
        mapRespVerbal.put("0-2 anos", Arrays.asList(new String[]{
            "5-Sorri, balbucia, choro Apropriado",
            "4-Choro",
            "3-Choro inapropriado",
            "2-Grunhidos",
            "1-Ausente"
        }));
        //Resposta Motora
        //> 1 ano
        mapRespMotora.put("> 1 ano", Arrays.asList(new String[]{
            "6-Obedece a comandos",
            "5-Localiza a dor",
            "4-Retirada à dor",
            "3-Flexão à dor",
            "2-Extensão à dor",
            "1-Ausente"
        }));
        //< 1 ano
        mapRespMotora.put("< 1 ano", Arrays.asList(new String[]{
            "6-Movimentos espontâneos",
            "5-Localiza a dor",
            "4-Retirada à dor",
            "3-Flexão à dor",
            "2-Extensão à dor",
            "1-Ausente"
        }));

        return Arrays.asList(mapAbOcular, mapRespVerbal, mapRespMotora);
    }

}
