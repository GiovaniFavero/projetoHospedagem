/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Favero
 */
@Entity
@Table(name="pessoas")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    
    @Column(name="telefone")
    private String telefone;
    
    @Column(name="email",unique = true)
    private String email;
    
    @Column(name="profissao")
    private String profissao;
    
    @Column(name="senha")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.hospedagem.model.Pessoa[ id=" + id + " ]";
    }
    
}
