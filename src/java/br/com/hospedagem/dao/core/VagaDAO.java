/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.core;

import br.com.hospedagem.model.Vaga;
import java.util.List;

/**
 *
 * @author Favero
 */
public interface VagaDAO {
    
    public void salvar(Vaga p);
    public void remover(Vaga p);
    public List<Vaga> buscarTodos();
    public Vaga buscar(Long id);
    
}
