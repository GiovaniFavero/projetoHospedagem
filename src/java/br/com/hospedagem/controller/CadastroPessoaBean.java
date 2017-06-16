/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.CadastroPessoaRN;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.model.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class CadastroPessoaBean {
    
    private Pessoa pessoa = new Pessoa();
    private CadastroPessoaRN cadastroPessoaRN;

    
    public CadastroPessoaBean() {
        this.cadastroPessoaRN = new CadastroPessoaRN();
        editarPerfil();
    }
    
    public String salvar(){
        try{
            cadastroPessoaRN.salvar(this.pessoa);
        }catch(Exception e){
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage(e.getMessage());
                context.addMessage("Não foi possível salvar a pessoa", message);
                return null;
        }
        this.pessoa = new Pessoa();
        return "telaLogin.xhtml";
    }
    
    public void editarPerfil(){
        Pessoa temp = (Pessoa) SessionUtil.getParam("usuario");
        if(temp != null){
            this.pessoa = temp;
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
}
