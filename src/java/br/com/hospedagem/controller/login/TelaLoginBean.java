/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller.login;

import br.com.hospedagem.RN.login.TelaLoginRN;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.util.UsuarioAtual;
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
public class TelaLoginBean {
    
    private String email;
    private String senha;
    private Pessoa pessoa = new Pessoa();
    private TelaLoginRN telaLoginRn;
    
    public TelaLoginBean() {
        this.telaLoginRn = new TelaLoginRN();
    }
    
    public String login(){
        try{
            this.pessoa = telaLoginRn.login(email, senha);
            if(this.pessoa != null){
                SessionUtil.setParam("usuario", this.pessoa);
                return "index.xhtml";
            }else{
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Usuário e senha incorretos");
                context.addMessage("Erro: ", message);
                return null;
            }
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(e.getMessage());
            context.addMessage("", message);
            return null;
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
