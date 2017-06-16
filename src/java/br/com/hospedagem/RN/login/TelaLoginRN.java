/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN.login;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.jpa.JPAPessoaDAO;
import br.com.hospedagem.model.Pessoa;
import java.util.List;

/**
 *
 * @author Favero
 */
public class TelaLoginRN {
    
    private JPAPessoaDAO pessoaDAO;

    public TelaLoginRN() {
        this.pessoaDAO = (JPAPessoaDAO) DAOFactory.getPessoaDAO();
    }
    
    public Pessoa login(String email, String senha) throws Exception{

        Pessoa temp = null;
        temp = pessoaDAO.buscarLogin(email, senha);
        if(temp != null){
            SessionUtil.setParam("login", "Sair");
            return temp;
        }else{
            throw new Exception("Usuário e senha incorretos");
        }
    }
    
    public void persistencia(){
        try{
            List<Pessoa> pessoas = this.pessoaDAO.buscarTodos();
            if(pessoas.size() == 0){
                Pessoa p = new Pessoa("",null,"","admin","","admin");
                 this.pessoaDAO.salvar(p);
            }
        }catch(Exception e){
            
        }
        
      
    }
    
    
}
