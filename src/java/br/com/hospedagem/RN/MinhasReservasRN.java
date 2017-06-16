/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.dao.jpa.JPAAvaliacaoDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.Avaliacao;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.List;

/**
 *
 * @author Favero
 */
public class MinhasReservasRN {
    
    private JPAVagaDAO vagaDAO;
    private JPAAvaliacaoDAO avaliacaoDAO;

    public MinhasReservasRN() {
        this.vagaDAO = new JPAVagaDAO();
        this.avaliacaoDAO = new JPAAvaliacaoDAO();
    }
    
    public List<Vaga> buscarMinhasReservas(Pessoa pessoa){
        return vagaDAO.buscarPorHospede(pessoa);
    }
    
    public void enviarAvaliacao(Avaliacao a, Vaga vaga, Pessoa pessoa) throws Exception{
        if(vaga != null){
            a.setAvaliador(pessoa);
            a.setVaga(vaga);
            avaliacaoDAO.salvar(a);
        }else{
            throw new Exception("Nenhuma reserva selecionada");
        }
        
    }
    
    public void finalizarReserva(Vaga vaga, Pessoa pessoa) throws Exception{
        List<Avaliacao> temp = avaliacaoDAO.buscarAvaliacoesVagaPessoa(vaga, pessoa);
        if(temp.isEmpty()){
            throw new Exception("Você precisa avaliar para finalizar a reserva");
        }else{
            vaga.setHospede(null);
            vaga.setReservado(false);
            vagaDAO.salvar(vaga);
        }
    }
    
}
