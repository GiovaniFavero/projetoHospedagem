/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.ConsultaVagasRN;
import br.com.hospedagem.model.Vaga;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class ConsultaVagasBean {
    
    private String cidade;
    private Date periodoDe;
    private Date periodoAte;
    private int avaliacaoMinima;
    
    private List<Vaga> vagasResult;
    
    private ConsultaVagasRN consultaVagasRn;
    
    public ConsultaVagasBean() {
        this.consultaVagasRn = new ConsultaVagasRN();
        this.vagasResult = consultaVagasRn.buscarVagasDisponiveis();
    }
    
    public void buscar(){
        this.vagasResult = this.consultaVagasRn.buscar(cidade, periodoDe, periodoAte, avaliacaoMinima);
        for(Vaga v : this.vagasResult){
            System.out.println(v.toString());
            
        }
    }
    

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getPeriodoDe() {
        return periodoDe;
    }

    public void setPeriodoDe(Date periodoDe) {
        this.periodoDe = periodoDe;
    }

    public Date getPeriodoAte() {
        return periodoAte;
    }

    public void setPeriodoAte(Date periodoAte) {
        this.periodoAte = periodoAte;
    }

    public int getAvaliacaoMinima() {
        return avaliacaoMinima;
    }

    public void setAvaliacaoMinima(int avaliacaoMinima) {
        this.avaliacaoMinima = avaliacaoMinima;
    }

    public List<Vaga> getVagasResult() {
        return vagasResult;
    }

    public void setVagasResult(List<Vaga> vagasResult) {
        this.vagasResult = vagasResult;
    }
    
    
   
    
    
    
}
