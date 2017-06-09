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
    
    public List<Vaga> buscar(String cidade, Date periodoDe, Date periodoAte, int avaliacao){
        
        return vagaDAO.buscaPersonalizada(cidade, periodoDe, periodoAte, avaliacao);
    }
    
    public List<Vaga> buscarTodos(){
        return vagaDAO.buscarTodos();
    }
    
    public List<Vaga> buscarVagasDisponiveis(){
        return vagaDAO.buscarVagasDisponíveis();
    }
    
}
