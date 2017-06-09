/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.RN.login;

import br.com.hospedagem.controller.TemplateBean;
import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.jpa.JPAPessoaDAO;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.util.UsuarioAtual;
import com.sun.faces.config.processor.FacesConfigExtensionProcessor;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author Favero
 */
public class TelaLoginRN {
    
    private JPAPessoaDAO pessoaDAO;

    public TelaLoginRN() {
        this.pessoaDAO = (JPAPessoaDAO) DAOFactory.getPessoaDAO();
        this.persistencia();
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
            Pessoa p = new Pessoa("",null,"","admin","","admin");
            this.pessoaDAO.salvar(p);
        }
        
      
    }
    
    
}
