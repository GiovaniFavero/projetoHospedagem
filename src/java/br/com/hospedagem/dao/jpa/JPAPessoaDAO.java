/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.dao.core.PessoaDAO;
import br.com.hospedagem.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Favero
 */
public class JPAPessoaDAO implements PessoaDAO{
    
    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospedagemPU");
        return emf.createEntityManager();
    }

    @Override
    public void salvar(Pessoa p) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.persist(p);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public void remover(Pessoa p) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.remove(p);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public List<Pessoa> buscarTodos() {
        EntityManager em = null;
        try{
             Query q = em.createQuery("select p from Pessoa p");
             return q.getResultList();
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }

    @Override
    public Pessoa buscar(int id) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             return em.find(Pessoa.class, id);
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }
    
}
