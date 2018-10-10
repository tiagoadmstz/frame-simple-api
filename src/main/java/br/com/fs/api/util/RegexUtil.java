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
public abstract class RegexUtil {

    private static String[] listaRegex = new String[]{
        "^(?i:(<>.*))$",
        "^(?i:.*<>.*)$",
        "^(?i:<>)$",
        "^(?!(?i:<>)$)",
        "^(?i:.*<>)$",
        "^(?!(?i:.*<>.*)$)",
        "\\d*",
        "\\D*",
        "(^(.*[A-Z].*)&&(.*[\\d].*)&&(.*[\\p{Punct}].*)$)"
    };

    /**
     * Método estático que devolve uma expressão regular incensitive case
     * conforme solicitado para realização de pesquisa ou filtros especiais
     *
     * @param regex Constante contendo regex solicitado
     * <ul>
     * <li>COMECACOM - Pesquisa somente inicio da string</li>
     * <li>CONTEM - Pesquisa toda a string obdecendo a ordem em que os
     * caracteres aparecem</li>
     * <li>IGUAL - Pesquisa exatamente o que contem a string</li>
     * <li>DIFERENTE - Pesquisa somente o que a string não contem</li>
     * <li>TERMINACOM - Pesquisa somente o fim a string</li>
     * <li>NAOCONTEM - Pesquisa o que a string não contem obedecendo a ordem em
     * que os caracteres aparecem</li>
     * </ul>
     * @param texto Texto que utilizará a expressão regular
     * @return Texto modificado contendo a expressão regular
     */
    public static String getRegex(REGEX regex, String texto) {
        texto = regex == REGEX.CONTEM || regex == REGEX.NAOCONTEM ? texto.replaceAll("\\s", ".*") : texto;
        return listaRegex[regex.getValue()].replace("<>", texto);
    }

    public static boolean verificar(REGEX regex, String texto){
        //Pattern p = Pattern.compile(getRegex(regex, ""));
        //return p.matcher(texto).find();
        return texto.matches(getRegex(regex, texto));
    }
    
    public static void ignorar_ascentuacao(String texto){
        if(texto.matches("[AaEeIiOoUu]")){
            //return "AÁÀÂÃÄaáàâãä", "EÉÈÊËeéèêë", "IÍÌÎÏiíìîï", "OÓÒÔÕÖoóòôõö", "UÚÙÛÜuúùûü";
        }
    }
    
    /**
     * <ul>Metacaracteres
     * <li>. -> busca qualquer caracter </li>
     * <li>\d -> busca qualquer número [0-9] </li>
     * <li>\D -> busca qualquer caracter que não seja número [^0-9]</li>
     * <li>\w -> busca qualquer caracter de letras e números [a-zA-Z_0-9] </li>
     * <li>\W -> busca qualquer caracter que não sejam letras e números [^\w]</li>
     * <li>\s -> busca qualquer caracter de espaço em branco, tabulações [\t\n\x0B\f\r] </li>
     * <li>\S -> busca qualquer caracter sem espaço em branco [^\s]</li>
     * <li>\0n -> busca caracteres com valor octal 0n (0 <= n <= 7)</li>
     * <li>\0nn -> busca caracteres com valor octal 0nn (0 <= n <= 7)</li>
     * <li>\0mnn -> busca caracteres com valor octal 0mnn (0 <= m <= 3, 0 <= n <= 7)</li>
     * <li>\xhh -> busca caracteres com valor hexadecimal 0xhh </li>
     * <li>\ uhhhh -> busca caracteres com valor hexadecimal com valor hexadecimal 0xhhhh</li>
     * <li>\x{h...h} -> busca caracteres com valor hexadecimal 0xh...h (Character.MIN_CODE_POINT <= 0xh...h <=  Character.MAX_CODE_POINT)</li>
     * <li>\t -> busca o caracter de tabulação tab ('\u0009')</li>
     * <li>\n -> busca o caracter de quebra de linha ENTER ('\u000A')</li>
     * <li>\r -> busca o caracter de carriage-return ('\u000D')</li>
     * <li>\f -> busca o caracter de marcação rodapé de formulário form-feed ('\u000C')</li>
     * <li>\a -> busca o caracter de alerta (bell) ('\u0007')</li>
     * <li>\e -> busca o caracter de espaço ('\u001B')</li>
     * <li>\cx -> busca o caracter de controle representado por x</li>
     * </ul>
     * <ul>Modificadores
     * <li>(?i:) -> ignora maisculas e minusculas </li>
     * <li>(?m:) -> trabalha multilinhas</li>
     * <li>(?s:) -> faz com que o caracter encontre novas linhas </li>
     * <li>(?x:) -> permite a inclusão de espaços e comentários</li>
     * </ul>
     * <ul>Quantificadores 
     * <li>X{n} -> X procura a ocorrencia de caracteres n vezes </li>
     * <li>X{n,} -> X pelo menos n vezes </li>
     * <li>X{n,m} -> X pelo menos n vezes não mais que m vezes </li>
     * <li>X? -> 0 ou 1 vezes </li>
     * <li>X* -> 0 ou mais vezes </li>
     * <li>X+ -> 1 ou mais vezes </li>
     * </ul>
     * <ul>Metacaracteres de fronteira 
     * <li>^ -> inicia</li>
     * <li>$ -> finaliza </li>
     * <li>| -> ou (condição)</li>
     * </ul>
     * <ul>Agrupadores 
     * <li>[...] -> Agrupamento </li>
     * <li>[a-z] -> alcance </li>
     * <li>[a-e][i-u] -> união </li>
     * <li>[a-z&&[aeiou]] -> interseção </li>
     * <li>[^abc] -> exeção </li>
     * <li>[a-z&&^[m-p]] -> subtração </li>
     * <li>\x -> fuga literal (usa um caracter propriamente dito sem interromper a expressão)</li>
     * </ul>
     *<ul>POSIX
     * <li>\p{Lower} busca caracteres de letras em minusculo: [a-z]</li>
     * <li>\p{Upper} busca caracteres de letras em maisculo:[A-Z]</li>
     * <li>\p{ASCII} busca qualquer caracter da tabela ASCII:[\x00-\x7F]</li>
     * <li>\p{Alpha} busca qualquer caracter de letras maisculas ou minusculas:[\p{Lower}\p{Upper}] ou [a-zA-Z]</li>
     * <li>\p{Digit} busca qualquer número: [0-9]</li>
     * <li>\p{Alnum} busca qualquer caracter alphanumerico:[\p{Alpha}\p{Digit}]</li>
     * <li>\p{Punct} busca por caracteres especiais de pontuação: !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~</li>
     * <li>\p{Graph} busca por caracteres gráficos ou seja letras com acentuação: [\p{Alnum}\p{Punct}]</li>
     * <li>\p{Print} busca por caracteres imprimivéis: [\p{Graph}\x20]</li>
     * <li>\p{Blank} busca por espaços ou tabulação: [ \t]</li>
     * <li>\p{Cntrl} busca por caracteres de controle: [\x00-\x1F\x7F]</li>
     * <li>\p{XDigit} busca por digitos hexadecimais: [0-9a-fA-F]</li>
     * <li>\p{Space} busca por qualquer tipo de espaço em branco: [ \t\n\x0B\f\r]</li>
     *</ul>
     *<ul>java.lang.Character classes (simple java character type)
     *\p{javaLowerCase} 	Equivalent to java.lang.Character.isLowerCase()
     *\p{javaUpperCase} 	Equivalent to java.lang.Character.isUpperCase()
     *\p{javaWhitespace} 	Equivalent to java.lang.Character.isWhitespace()
     *\p{javaMirrored} 	Equivalent to java.lang.Character.isMirrored()
     * </ul>
     *<ul>Classes for Unicode scripts, blocks, categories and binary properties
     *\p{IsLatin} 	A Latin script character (script)
     *\p{InGreek} 	A character in the Greek block (block)
     *\p{Lu} 	An uppercase letter (category)
     *\p{IsAlphabetic} 	An alphabetic character (binary property)
     *\p{Sc} 	A currency symbol
     *\P{InGreek} 	Any character except one in the Greek block (negation)
     *[\p{L}&&[^\p{Lu}]]  	Any letter except an uppercase letter (subtraction)
     * </ul>
     *<ul>Boundary matchers
     *^ 	The beginning of a line
     *$ 	The end of a line
     *\b 	A word boundary
     *\B 	A non-word boundary
     *\A 	The beginning of the input
     *\G 	The end of the previous match
     *\Z 	The end of the input but for the final terminator, if any
     *\z 	The end of the input
     * </ul>
     *<ul>Reluctant quantifiers
     *X?? 	X, once or not at all
     *X*? 	X, zero or more times
     *X+? 	X, one or more times
     *X{n}? 	X, exactly n times
     *X{n,}? 	X, at least n times
     *X{n,m}? 	X, at least n but not more than m times
     * </ul>
    *<ul>Possessive quantifiers
     *X?+ 	X, once or not at all
     *X*+ 	X, zero or more times
     *X++ 	X, one or more times
     *X{n}+ 	X, exactly n times
     *X{n,}+ 	X, at least n times
     *X{n,m}+ 	X, at least n but not more than m times
     * </ul>
     *<ul>Back references
     *\n 	Whatever the nth capturing group matched
     *\k<name> 	Whatever the named-capturing group "name" matched
     * </ul>
     *<ul>Quotation
     *\ 	Nothing, but quotes the following character
     *\Q 	Nothing, but quotes all characters until \E
     *\E 	Nothing, but ends quoting started by \Q
     * </ul>
     *<ul>Special constructs (named-capturing and non-capturing)
     *(?<name>X) 	X, as a named-capturing group
     *(?:X) 	X, as a non-capturing group
     *(?idmsuxU-idmsuxU)  	Nothing, but turns match flags i d m s u x U on - off
     *(?idmsux-idmsux:X)   	X, as a non-capturing group with the given flags i d m s u x on - off
     *(?=X) 	X, via zero-width positive lookahead
     *(?!X) 	X, via zero-width negative lookahead
     *(?<=X) 	X, via zero-width positive lookbehind
     *(?<!X) 	X, via zero-width negative lookbehind
     *(?>X) 	X, as an independent, non-capturing group
     *</ul>
    * [a-z] -> representa qualquer letra minúscula do alfabeto [A-Z] ->
     * representa qualquer letra maiúscula do alfabeto [a-zA-Z] -> representa
     * qualquer letra do alfabeto, seja maiúscula ou minúscula | -> representa o
     * 'ou'. "a|b" casa com 'a' ou com 'b', ou com os dois
     *
     * Vejamos alguns quantificadores . -> substitui qualquer caractere -> o
     * caractere anterior aparece nenhuma ou mais vezes + -> o caractere
     * anterior aparece uma ou mais vezes {a} -> o caractere anterior se repete
     * 'a' vezes {a,} -> o caractere anterior se repete pelo menos 'a' vezes
     * {a,b} -> o caractere anterior se repete entre 'a' e 'b' vezes
     *
     * A representação de dígito é '\d', mas dentro de strings, para '\' valer,
     * temos que escapá-la. Logo, podemos representar um CEP como:
     * "\\d\\d\\d\\d\\d-\\d\\d\\d" Ou "\\d{5}-\\d{3}" O método ficaria:
     *
     * meu_cep.matches("\\d{5}-\\d{3}") -> retorna 'true' se o CEP for digitado
     * correto, e 'false' caso errado
     *
     * Para validar um nome, sabemos que o primeiro caractere é maiúsculo:
     * nome.matches("[A-Z][a-zA-Z]*") -> retorna 'true' se a primeira letra do
     * nome for maiúscula, e 'false' caso errado
     *
     * O Java provém alguns métodos para o uso de regex e substituições: std =
     * std.replaceAll("a", "b") -> substitui todas as ocorrências de 'a' por 'b'
     *
     * Também podemos usar expressões regulares no método 'replaceAll': std =
     * std.replaceAll("\\w+","a") -> substitui todas as palavras por 'a' std =
     * std.replaceFirst("\\d","a") -> substitui a primeira ocorrência de um
     * dígito por 'a' String[] pedacos = std.split("a") -> divide a string 'std'
     * em partes, cujo separador é 'a' e armazena no vetor de strings 'pedacos'.
     * Por exemplo, para separarmos as palavras de uma string, usamos "," ou
     * ",\\s*" como separador
     */
}
