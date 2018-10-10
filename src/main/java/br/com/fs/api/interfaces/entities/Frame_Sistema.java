/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.interfaces.entities;

import br.com.fs.api.interfaces.ManipulaBean;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_TELA_SISTEMA", indexes = {
    @Index(name = "XPKCAD_TELA_SISTEMA_ID", columnList = "TELA")
    ,@Index(name = "XFICAD_TELA_SISTEMA_NOMETELA", columnList = "NOME_TELA")
    ,@Index(name = "XFICAD_TELA_SISTEMA_ID_NOMETELA", columnList = "ID, NOME_TELA")
})
@SequenceGenerator(name = "frame_sistema_seq", initialValue = 1, allocationSize = 1)
public class Frame_Sistema extends ManipulaBean<Frame_Sistema> {

    private static final long serialVersionUID = 3818589118011970724L;
    private Long id;
    private String grupo;
    private String nomeTela;

    public Frame_Sistema() {
    }

    public Frame_Sistema(Long id, String grupo, String nomeTela) {
        this.id = id;
        this.grupo = grupo;
        this.nomeTela = nomeTela;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frame_sistema_seq")
    @Column(name = "TELA")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "GRUPO", length = 150)
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Column(name = "NOME_TELA", length = 255)
    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.grupo);
        hash = 71 * hash + Objects.hashCode(this.nomeTela);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Frame_Sistema other = (Frame_Sistema) obj;
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.nomeTela, other.nomeTela)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Frame_Sistema{" + "id=" + id + ", grupo=" + grupo + ", nomeTela=" + nomeTela + '}';
    }

}
