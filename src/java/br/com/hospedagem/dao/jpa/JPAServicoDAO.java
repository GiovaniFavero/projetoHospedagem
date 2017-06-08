/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.dao.core.PessoaDAO;
import br.com.hospedagem.dao.core.ServicoDAO;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Servico;
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
public class JPAServicoDAO implements ServicoDAO{
    
    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospedagemPU");
        return emf.createEntityManager();
    }

    @Override
    public void salvar(Servico p) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.persist(p);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
                
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public void remover(Servico p) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.remove(p);
             em.getTransaction().commit();
        }catch(PersistenceException pE){
              
        }catch(Exception e){
            e.printStackTrace();  
        }finally{
            if(em != null){
                em.close();
            } 
        }
    }

    @Override
    public List<Servico> buscarTodos() {
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("SELECT s FROM Servico s");
             return (List<Servico>) q.getResultList();
        }catch(PersistenceException pE){
                
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }

    @Override
    public Servico buscar(Long id) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             return em.find(Servico.class, id);
        }catch(PersistenceException pE){
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }
    
    public Servico buscarPorDescricao(String desc){
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             Query q = em.createQuery("Select s from Servico s where s.descricao = :desc");
             q.setParameter("desc", desc);
             return (Servico) q.getSingleResult();
        }catch(PersistenceException pE){
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null){
                em.close();
            } 
        }
        return null;
    }
    
}
