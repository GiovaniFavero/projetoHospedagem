/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.core;

import br.com.hospedagem.model.Servico;
import java.util.List;

/**
 *
 * @author Favero
 */
public interface ServicoDAO {
    
    public void salvar(Servico s);
    public void remover(Servico s);
    public List<Servico> buscarTodos();
    public Servico buscar(Long id);
    
}
