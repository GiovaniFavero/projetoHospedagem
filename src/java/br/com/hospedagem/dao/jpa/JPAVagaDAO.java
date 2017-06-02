/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.dao.core.VagaDAO;
import br.com.hospedagem.model.Vaga;
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
public class JPAVagaDAO implements VagaDAO{
    
    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospedagemPU");
        return emf.createEntityManager();
    }

    @Override
    public void salvar(Vaga v) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.persist(v);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public void remover(Vaga v) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.remove(v);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public List<Vaga> buscarTodos() {
        EntityManager em = null;
        try{
             Query q = em.createQuery("select v from Vaga v");
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
    public Vaga buscar(int id) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             return em.find(Vaga.class, id);
        }catch(PersistenceException pE){
            
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }
    
}
