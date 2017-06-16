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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Favero
 */
@ManagedBean
@SessionScoped
public class CadastroVagaBean {
    
    private Vaga vaga = new Vaga();
    private List<Servico> servicos;
    private CadastroVagaRN cadastroVagaRN;
    private List<Vaga> vagas;
    private DataModel<Vaga> vagasModel;

    public CadastroVagaBean() {
        cadastroVagaRN = new CadastroVagaRN();
        this.vagas = cadastroVagaRN.buscarPorPessoa();
        this.vagasModel = new ListDataModel<>(this.vagas);
        this.servicos = cadastroVagaRN.carregaServicos();
    }
    
    public String salvar(){
        try{
            Vaga temp = cadastroVagaRN.salvar(this.vaga);
            if(!vagas.contains(temp)){
                this.vagas.add(temp);
            }
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(e.getMessage());
            context.addMessage("Não foi possível salvar a vaga", message);
            return null;
        }
        this.vaga = new Vaga();
        return "";
    }
    
    public String alterar(){
        this.vaga = vagasModel.getRowData();
        return "";
    }
    
    public String excluir(){
        this.vaga = vagasModel.getRowData();
        this.cadastroVagaRN.remover(this.vaga);
        vagas.remove(this.vaga);
        this.vaga = new Vaga();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso",  "Vaga excluída com sucesso!") );
        return "";
    }
    
    public void saveMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso","Vaga cadastrada/alterada com sucesso!") );
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

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public DataModel<Vaga> getVagasModel() {
        return vagasModel;
    }

    public void setVagasModel(DataModel<Vaga> vagasModel) {
        this.vagasModel = vagasModel;
    }
    
    
    
}
