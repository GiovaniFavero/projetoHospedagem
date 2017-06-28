/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.ConsultaVagasRN;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.model.Vaga;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.spinner.Spinner;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class ConsultaVagasBean {
    
    private String tipoConsulta;
    private String cidade;
    private Date periodoDe;
    private Date periodoAte;
    private int avaliacaoMinima;
    private InputText cidadeUi = new InputText();
    private Calendar periodoDeUi = new Calendar();
    private Calendar periodoAteUi = new Calendar();
    private Spinner avaliacaoUi = new Spinner();
    
    private Vaga vagaSelecionada;
    
    private List<Vaga> vagasResult;
    
    private ConsultaVagasRN consultaVagasRn;
    
    public ConsultaVagasBean() {
        this.consultaVagasRn = new ConsultaVagasRN();
        this.vagasResult = consultaVagasRn.buscarVagasDisponiveis();
        this.setTipoConsulta();
    }
    
    public String pesquisar(){
        this.vagasResult = this.consultaVagasRn.pesquisar(tipoConsulta, cidade, periodoDe, periodoAte, avaliacaoMinima);
        return "index.xhtml";
    }
    
    public void setTipoConsulta(AjaxBehaviorEvent event){
        this.setTipoConsulta();
    }
    
    public void setTipoConsulta(){
        if(this.tipoConsulta == null){
            this.tipoConsulta = "0";
        }
        if(this.tipoConsulta.equals("1")){
            this.cidadeUi.getAttributes().put("disabled", false);
            this.periodoDeUi.getAttributes().put("disabled", true);
            this.periodoAteUi.getAttributes().put("disabled", true);
            this.avaliacaoUi.getAttributes().put("disabled", true);
        }else if(this.tipoConsulta.equals("2")){
            this.cidadeUi.getAttributes().put("disabled", true);
            this.periodoDeUi.getAttributes().put("disabled", false);
            this.periodoAteUi.getAttributes().put("disabled", false);
            this.avaliacaoUi.getAttributes().put("disabled", true);
        }else if(this.tipoConsulta.equals("3")){
            this.cidadeUi.getAttributes().put("disabled", true);
            this.periodoDeUi.getAttributes().put("disabled", true);
            this.periodoAteUi.getAttributes().put("disabled", true);
            this.avaliacaoUi.getAttributes().put("disabled", false);
        }else{
            this.cidadeUi.getAttributes().put("disabled", true);
            this.periodoDeUi.getAttributes().put("disabled", true);
            this.periodoAteUi.getAttributes().put("disabled", true);
            this.avaliacaoUi.getAttributes().put("disabled", true);
        }
    }
    
    public String vizualizar(Vaga vaga){
        if(SessionUtil.getParam("vaga") != null){
            SessionUtil.remove("vaga");
        }
        SessionUtil.setParam("vaga", vaga);        
        return "detalhesVaga.xhtml";
    }
    
    public String vizualizarPerfil(Vaga vaga){
        
        if(SessionUtil.getParam("perfil") != null){
            SessionUtil.remove("perfil");
        }
        SessionUtil.setParam("perfil", vaga.getDono());        
        return "perfilPessoa.xhtml";
    }
    
    public Calendar getPeriodoDeUi() {
        return periodoDeUi;
    }

    public void setPeriodoDeUi(Calendar periodoDeUi) {
        this.periodoDeUi = periodoDeUi;
    }

    public Calendar getPeriodoAteUi() {
        return periodoAteUi;
    }

    public void setPeriodoAteUi(Calendar periodoAteUi) {
        this.periodoAteUi = periodoAteUi;
    }

    public Spinner getAvaliacaoUi() {
        return avaliacaoUi;
    }

    public void setAvaliacaoUi(Spinner avaliacaoUi) {
        this.avaliacaoUi = avaliacaoUi;
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

    public InputText getCidadeUi() {
        return cidadeUi;
    }

    public void setCidadeUi(InputText cidadeUi) {
        this.cidadeUi = cidadeUi;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Vaga getVagaSelecionada() {
        return vagaSelecionada;
    }

    public void setVagaSelecionada(Vaga vagaSelecionada) {
        this.vagaSelecionada = vagaSelecionada;
    }
   
    
    
    
}
