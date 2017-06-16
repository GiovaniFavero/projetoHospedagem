/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.Vaga;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Favero
 */
public class ConsultaVagasRN {
    
    JPAVagaDAO vagaDAO = (JPAVagaDAO) DAOFactory.getVagaDAO();
    
    public List<Vaga> pesquisar(String tipoConsulta, String cidade, Date periodoDe, Date periodoAte, int avaliacaoMinima){
        if(tipoConsulta.equals("0")){
            return this.buscarVagasDisponiveis();
        }else if(tipoConsulta.equals("1")){
            return vagaDAO.buscarVagasPorCidade(cidade);
        }else if(tipoConsulta.equals("2")){
            return vagaDAO.buscarVagasPorPeriodo(periodoDe, periodoAte);
        }else{
            return vagaDAO.buscarVagasPorAvaliacaoMinima(avaliacaoMinima);
        }      
    }
    
    public List<Vaga> buscarVagasDisponiveis(){
        return vagaDAO.buscarVagasDisponíveis();
    }
    
    
    
}

