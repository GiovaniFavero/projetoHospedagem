/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.controller;

import br.com.hospedagem.RN.PerfilPessoaRN;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.model.Avaliacao;
import br.com.hospedagem.model.HistoricoVaga;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Favero
 */
@ManagedBean
@RequestScoped
public class PerfilPessoaBean {
    
    private Long id;
    private Pessoa pessoa;
    private PerfilPessoaRN pessoaRN;
    private List<HistoricoVaga> vagas;
    private DataModel<HistoricoVaga> vagasModel;
    private List<Avaliacao> avaliacoes;
    private DataModel<Avaliacao> avaliacoesModel;
    
    public PerfilPessoaBean() {
        this.pessoaRN = new PerfilPessoaRN();
        pessoa = pessoaRN.getPessoa();
        this.vagas = pessoa.getHisoticoVagas();
        this.vagasModel = new ListDataModel<>(this.vagas);
        this.avaliacoes = pessoa.getAvaliacoes();
        this.avaliacoesModel = new ListDataModel<>(this.avaliacoes);
        getAvaliacoesVagas();
    }
    
    public List<Vaga> buscaHospedagensHospede(Pessoa pessoa){
        return pessoaRN.buscaHospedagensHospede(pessoa);
    }
    
    public List<Vaga> buscaHospedagensHospedeiro(Pessoa pessoa){
        return pessoaRN.buscaHospedagensHospedeiro(pessoa);
    }
    
    public String vizualizar(Vaga vaga){
        if(SessionUtil.getParam("vaga") != null){
            SessionUtil.remove("vaga");
        }
        SessionUtil.setParam("vaga", vaga);        
        return "detalhesVaga.xhtml";
    }
    
    public String getTipo(HistoricoVaga vaga){
        if(vaga.getTipo() == 1){
            return "Hóspede";
        }else{
            return "Hospedeiro";
        }
    }
    
    public String getPapelAvaliacao(Avaliacao avaliacao){
        if(avaliacao.getAvaliador() == this.pessoa){
            return "Avaliador";
        }else{
            return "Avaliado";
        }
        
    }
    
    public void getAvaliacoesVagas(){
        for(Vaga vaga : this.pessoa.getVagasProprias()){
            List<Avaliacao> temp1 = vaga.getAvaliacoes();
            for(Avaliacao a : temp1){
                if(!this.avaliacoes.contains(a)){
                    this.avaliacoes.add(a);
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DataModel<HistoricoVaga> getVagasModel() {
        return vagasModel;
    }

    public DataModel<Avaliacao> getAvaliacoesModel() {
        return avaliacoesModel;
    }
    
    
    
    
}
