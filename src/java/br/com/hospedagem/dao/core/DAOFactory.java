/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.core;

import br.com.hospedagem.dao.jpa.JPAPessoaDAO;
import br.com.hospedagem.dao.jpa.JPAServicoDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;

/**
 *
 * @author Favero
 */
public class DAOFactory {
    
    public static PessoaDAO getPessoaDAO(){
        return new JPAPessoaDAO();
    }
    
    public static VagaDAO getVagaDAO(){
        return new JPAVagaDAO();
    }
    
    public static ServicoDAO getServicoDAO(){
        return new JPAServicoDAO();
    }
    
}
