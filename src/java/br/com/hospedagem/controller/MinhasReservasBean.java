/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.MinhasReservasRN;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.model.Avaliacao;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class MinhasReservasBean {

    private Vaga vaga = new Vaga();
    private List<Vaga> vagas;
    private MinhasReservasRN reservasRN;
    private Pessoa pessoa;
    private DataModel<Vaga> vagasModel;
    private Avaliacao avaliacao = new Avaliacao();
    
    public MinhasReservasBean() {
        pessoa = (Pessoa) SessionUtil.getParam("usuario");
        this.reservasRN = new MinhasReservasRN();
        vagas = reservasRN.buscarMinhasReservas(pessoa);
        vagasModel = new ListDataModel<>(vagas);
    }
    
    public void avaliar(){
        this.avaliacao = new Avaliacao();
        this.vaga = vagasModel.getRowData();
    }
    
    public void enviarAvaliacao(){
        try{
            this.vaga = vagasModel.getRowData();
            reservasRN.enviarAvaliacao(avaliacao, vaga, pessoa);
            this.avaliacao = new Avaliacao();
            this.vaga = new Vaga();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sucesso","Avaliação enviada com sucesso!") );
        } catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Nenhuma reserva selecionada") );
        }
        
    }
    
    public String vizualizar(){
        Vaga v = vagasModel.getRowData();
        if(SessionUtil.getParam("vaga") != null){
            SessionUtil.remove("vaga");
        }
        SessionUtil.setParam("vaga", v);        
        return "detalhesVaga.xhtml";
    }
    
    public void finalizarReserva(){
        Vaga temp = vagasModel.getRowData();
        try{
            reservasRN.finalizarReserva(temp, pessoa);
            vagas.remove(temp);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sucesso","Reserva finalizada com sucesso!"));
        }catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", e.getMessage()));
        }
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
    
    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
    
}
