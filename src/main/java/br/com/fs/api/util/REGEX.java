/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

/**
 *
 * @author Tiago
 */
public enum REGEX {
    
    COMECACOM(0), CONTEM(1), IGUAL(2), DIFERENTE(3), TERMINACOM(4), NAOCONTEM(5),
    APENAS_NUMEROS(6), APENAS_TEXTO(7);
    
    private int value;

    private REGEX(int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
