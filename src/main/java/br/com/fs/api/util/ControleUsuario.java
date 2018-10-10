package br.com.fs.api.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.fs.api.interfaces.ManipulaFrames;
import br.com.fs.api.interfaces.entities.Usuario;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class ControleUsuario {

    private static Usuario usuarioAtual;
    //private static EntityManager em;
    private static final Map<String, Integer> USUARIOS = new HashMap();

    public static Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public static void setUsuarioAtual(Usuario usuarioAtual) {
        ControleUsuario.usuarioAtual = usuarioAtual;
    }

    /**
     * Faz a verificação de tentativas de login de um usuário que exista ou não
     * após dez tentativas caso o usuário esteja cadastrado o mesmo é bloqueado.
     *
     * @param frame tela que utilizará este método para mostrar mensagens
     * @param usuario um objeto usuário para verificação
     */
    //public static void tentativasAcesso(JFrame frame, Usuario usuario, EntityManager em) {
    /*this.em = em;
        
        if (USUARIOS.get(usuario.getUsuario()) == null) {
        USUARIOS.put(usuario.getUsuario(), 0);
        }
        
        if (USUARIOS.get(usuario.getUsuario()) < 10) {
        USUARIOS.replace(usuario.getUsuario(), USUARIOS.get(usuario.getUsuario()) + 1);
        JOptionPane.showMessageDialog(frame, "Login de usuário ou senha não conferem!\nVocê tem " + (10 - USUARIOS.get(usuario.getUsuario())) + " tentativas restantes");
        }
        
        if (USUARIOS.get(usuario.getUsuario()) == 10) {
        EntityManagerHelper emh = new EntityManagerHelper();
        Usuario user = (Usuario) emh.getObject("from Usuario as user where user.usuario = :paramUser", "paramUser", usuario.getUsuario(), EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
        if (user != null) {
        user.setBloqueado(true);
        emh.getOperation(EntityManagerHelper.OPERATION_TYPE.UPDATE, user, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
        }
        USUARIOS.replace(usuario.getUsuario(), USUARIOS.get(usuario.getUsuario()) + 1);
        }
        
        if (USUARIOS.get(usuario.getUsuario()) > 10) {
        JOptionPane.showMessageDialog(frame, "Usuário bloqueado por ecesso de tentativas");
        }*/
    //}
    /**
     * Verifica se duas senhas digitadas são iguais e pode controlar um campo
     * JLabel se for informado
     *
     * @param senha1 campo de senha
     * @param senha2 campo de redigitar senha
     * @param lbInformacao label para informar usuário sobre o erro, se não
     * nulo.
     *
     */
    public static void verificaSenhasCoinscidentes(JTextField senha1, JTextField senha2, JLabel lbInformacao) {
        senha2.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (e.getMark() > 0 && lbInformacao != null) {
                    lbInformacao.setText(!Objects.equals(senha1.getText(), senha2.getText()) ? "As senhas digitadas não são iguais" : "Repetir Senha");
                }
            }
        });
    }

    /**
     * Método que mede a força de uma senha digita, verificando se ela possuí
     * uma letra maiúscula, um número e um carácter especial.
     *
     * @param senha campo onde a senha será digitada
     * @param lbForca label para informar ao usuário
     * @param barraForca um JProgressBar para retorno visual
     *
     */
    public static void verificaForcaSenha(JTextField senha, JLabel lbForca, JProgressBar barraForca) {
        CaretListener ct = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                switch (forcarSenha(senha.getText())) {
                    case 1:
                        lbForca.setText("Senha Fraca");
                        barraForca.setForeground(Color.red);
                        barraForca.setValue(33);
                        break;
                    case 2:
                        lbForca.setText("Senha de Força Média");
                        barraForca.setForeground(Color.yellow);
                        barraForca.setValue(66);
                        break;
                    case 3:
                        lbForca.setText("Senha Forte");
                        barraForca.setForeground(Color.green);
                        barraForca.setValue(100);
                        break;
                    default:
                        break;
                }

                if (e.getMark() <= 0) {
                    lbForca.setText("A Senha deve ter 1 letra maiuscula, 1 número e  1 carácter especial");
                    barraForca.setForeground(Color.blue);
                    barraForca.setValue(0);
                } else if (e.getMark() < 8) {
                    lbForca.setText("Mínimo de 8 Carácteres");
                }
            }
        };

        senha.addCaretListener(ct);
    }

    /**
     * Método interno que mede a força de uma senha digita, verificando se ela
     * possuí uma letra maiúscula, um número e um carácter especial.
     *
     * @param senha String contendo a senha do usuário
     * @return um boolean true caso a senha contenha todos os requisitos
     */
    public static int forcarSenha(String senha) {
        //verifica se tem letras maiusculas, números e caracteresEspeciais
        int resultado = 0;
        String[] mt = new String[]{".*[A-Z].*", ".*[\\d].*", ".*[\\p{Punct}].*"};
        if (senha.matches(mt[0]) || senha.matches(mt[1]) || senha.matches(mt[2])) {
            resultado = 1;
        }

        if (senha.matches(mt[0]) && senha.matches(mt[1])
                || senha.matches(mt[1]) && senha.matches(mt[2])
                || senha.matches(mt[0]) && senha.matches(mt[2])) {
            resultado = 2;
        }

        if (senha.matches(mt[0]) && senha.matches(mt[1]) && senha.matches(mt[2])) {
            resultado = 3;
        }

        return resultado;
    }

    public static void getPermissoesFrames(ManipulaFrames frame) {
        if (usuarioAtual != null && usuarioAtual.getPermissoes() != null) {
            usuarioAtual.getPermissoes().getTelas().forEach(tela -> {
                frame.getListMenus().ifPresent(lista -> lista.stream().filter(menu -> menu.getText().equals(tela.getNomeTela())).forEach(menu -> menu.setEnabled(true)));
            });
        }
    }
    
}
