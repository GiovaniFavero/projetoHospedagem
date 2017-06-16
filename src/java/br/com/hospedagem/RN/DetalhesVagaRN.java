/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.HistoricoVaga;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.Date;

/**
 *
 * @author Favero
 */
public class DetalhesVagaRN {
    
    JPAVagaDAO vagaDAO;

    public DetalhesVagaRN() {
        vagaDAO = new JPAVagaDAO();
    }
    
    public Vaga buscarVaga(Long id){
        return vagaDAO.buscar(id);
    }
    
    public void reservar(Vaga v) throws Exception{
        Pessoa p = (Pessoa)SessionUtil.getParam("usuario");
        if(p != null){
            if(!v.isReservado()){
                v.setHospede(p);
                v.setReservado(true);
                Date data = new Date();
                HistoricoVaga historico = new HistoricoVaga(v, p, 1, data);
                v.addHistorico(historico);
                vagaDAO.salvar(v);
            }else{
                throw new Exception("A vaga já está reservada!");
            }
        }else{
            throw new Exception("Você precisa realizar a autenticação para reservar!");
        }
        
        
    }
    
    public Vaga getVagaAtualizada(Long id){
        return vagaDAO.buscar(id);
    }
    
    
}
