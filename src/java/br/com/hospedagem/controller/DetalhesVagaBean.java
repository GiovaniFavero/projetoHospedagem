/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.DetalhesVagaRN;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.model.Vaga;
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
public class DetalhesVagaBean {

    private Vaga vaga;
    private Long id;
    
    private DetalhesVagaRN vagasRN;
    
    public DetalhesVagaBean() {
        vagasRN = new DetalhesVagaRN();
        this.vaga = (Vaga) SessionUtil.getParam("vaga");
        this.vaga = vagasRN.getVagaAtualizada(this.vaga.getId());
    }
    
    public void reservar(){
        try{
            vagasRN.reservar(vaga);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sucesso", "Reserva efetuada. Acesse a tela 'Minhas Reservas' para mais detalhes.") );
        } catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro",  e.getMessage()) );
        }
        
    }

    public Vaga getVaga() {
        return vaga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
    

    
}
