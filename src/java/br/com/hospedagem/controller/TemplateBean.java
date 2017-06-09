/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.controller.login.SessionUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class TemplateBean {
    
    private String botao;

    public TemplateBean() {
        if(SessionUtil.getParam("login") == null){
            SessionUtil.setParam("login", "Entrar/Cadastrar-se");
        }
        botao = (String) SessionUtil.getParam("login");
    }

    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }
    
    public void alterar(){
        if(this.botao.equals("Entrar/Cadastrar-se")){
            this.botao = "Sair";
        }else{
            this.botao = "Entrar/Cadastrar-se";
        }
    }
    
    public String acao(){
        if(this.botao.equals("Entrar/Cadastrar-se")){
            return "telaLogin.xhtml";
        }else{
            SessionUtil.remove("usuario");
            SessionUtil.setParam("login", "Entrar/Cadastrar-se");
            this.botao = "Entrar/Cadastrar-se";
            return "telaLogin.xhtml";
        }
    }
}
