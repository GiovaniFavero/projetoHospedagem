/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.core;

import br.com.hospedagem.model.Pessoa;
import java.util.List;

/**
 *
 * @author Favero
 */
public interface PessoaDAO {
    
    public void salvar(Pessoa p);
    public void remover(Pessoa p);
    public List<Pessoa> buscarTodos();
    public Pessoa buscar(int id);
    
}
