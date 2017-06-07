/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.hospedagem.model.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Esley
 */
@ManagedBean(name="PessoaBean")
@SessionScoped
public class PessoaBean {

    Pessoa pessoa = new Pessoa();
    
    
    public PessoaBean() {
    }
    
    public void salvar(){}

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
 
    
    
}
