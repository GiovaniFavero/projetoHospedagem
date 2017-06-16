/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN;

import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.core.PessoaDAO;
import br.com.hospedagem.model.Pessoa;

/**
 *
 * @author Favero
 */
public class CadastroPessoaRN {
    
    private PessoaDAO pessoaDAO;

    public CadastroPessoaRN() {
        pessoaDAO = DAOFactory.getPessoaDAO();
    }
        
    public void salvar(Pessoa p) throws Exception{
        try{
            pessoaDAO.salvar(p);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        
    }
    
}
