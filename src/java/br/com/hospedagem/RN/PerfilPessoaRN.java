/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.jpa.JPAPessoaDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.List;

/**
 *
 * @author Favero
 */
public class PerfilPessoaRN {
    
    private JPAPessoaDAO pessoaDAO;
    private JPAVagaDAO vagaDAO;

    public PerfilPessoaRN() {
        this.pessoaDAO = new JPAPessoaDAO();
        this.vagaDAO = new JPAVagaDAO();
    }
    
    public Pessoa buscarPessoa(Long id){
        return pessoaDAO.buscar(id);
    }

    public Pessoa buscarPessoa(int i) {
        return null;
    }
    
    public Pessoa getPessoa(){
        return (Pessoa) SessionUtil.getParam("perfil");
    }
    
    public List<Vaga> buscaHospedagensHospede(Pessoa pessoa){
        return vagaDAO.buscarPorHospede(pessoa);
    }
    
    public List<Vaga> buscaHospedagensHospedeiro(Pessoa pessoa){
        return vagaDAO.buscarPorPessoa(pessoa);
    }
    
    
}
