/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.core.ServicoDAO;
import br.com.hospedagem.dao.core.VagaDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Servico;
import br.com.hospedagem.model.Vaga;
import br.com.hospedagem.util.UsuarioAtual;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Favero
 */
public class CadastroVagaRN {
    
    private JPAVagaDAO vagaDAO = new JPAVagaDAO();
    private ServicoDAO servicoDAO;

    public CadastroVagaRN() {
    }
    
    public void salvar(Vaga vaga){
        vaga.setDono((Pessoa) SessionUtil.getParam("usuario"));
        vagaDAO.salvar(vaga);
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
}
