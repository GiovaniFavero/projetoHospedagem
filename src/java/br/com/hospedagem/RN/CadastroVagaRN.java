/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.core.ServicoDAO;
import br.com.hospedagem.dao.jpa.JPAHistoricoVagaDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.HistoricoVaga;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Servico;
import br.com.hospedagem.model.Vaga;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Favero
 */
public class CadastroVagaRN {
    
    private JPAVagaDAO vagaDAO = new JPAVagaDAO();
    private ServicoDAO servicoDAO;
    private JPAHistoricoVagaDAO historicoDAO;

    public CadastroVagaRN() {
        historicoDAO = new JPAHistoricoVagaDAO();
    }
    
    public Vaga salvar(Vaga vaga){
        Pessoa p = (Pessoa) SessionUtil.getParam("usuario");
        if(historicoDAO.buscarHistoricoVagaPorHospedeiro(vaga, p) == null){
            Date data = new Date();
            HistoricoVaga historico = new HistoricoVaga(vaga, p, 2, data);
            vaga.addHistorico(historico);
        }
        vaga.setDono(p);
        return vagaDAO.salvar(vaga);
    }
    
    
    public List<Servico> carregaServicos(){
        servicoDAO = DAOFactory.getServicoDAO();
        List<Servico> servicos = servicoDAO.buscarTodos();
        if(servicos.size() == 0){
            servicos = new ArrayList<Servico>();
            servicos.add(new Servico("Wifi"));
            servicos.add(new Servico("TV"));
            servicos.add(new Servico("Café da Manhã"));
            servicos.add(new Servico("Limpeza"));
            servicos.add(new Servico("Estacionamento"));
            servicos.add(new Servico("Serviço de Quarto"));
            servicos.add(new Servico("Lavanderia"));
            for (Servico servico : servicos) {
                servicoDAO.salvar(servico);
            }
        }
        return servicos;
    }
    
    public List<Vaga> buscarPorPessoa(){
        return this.vagaDAO.buscarPorPessoa((Pessoa) SessionUtil.getParam("usuario"));
    }
    
    public void remover(Vaga v){
        this.vagaDAO.remover(v);
    }
}
