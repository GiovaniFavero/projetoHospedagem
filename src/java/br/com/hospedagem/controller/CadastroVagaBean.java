/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.CadastroVagaRN;
import br.com.hospedagem.model.Servico;
import br.com.hospedagem.model.Vaga;
import java.util.List;
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
public class CadastroVagaBean {
    
    private Vaga vaga = new Vaga();
    private List<Servico> servicos;
    private CadastroVagaRN cadastroVagaRN;

    public CadastroVagaBean() {
        cadastroVagaRN = new CadastroVagaRN();
        this.servicos = cadastroVagaRN.carregaServicos();
    }
    
    public String salvar(){
        try{
            System.out.println(vaga.toString());
            cadastroVagaRN.salvar(this.vaga);
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(e.getMessage());
            context.addMessage("Não foi possível salvar a vaga", message);
            return null;
        }
        this.vaga = new Vaga();
        return "cadastroVaga.xhtml";
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    
    
}
