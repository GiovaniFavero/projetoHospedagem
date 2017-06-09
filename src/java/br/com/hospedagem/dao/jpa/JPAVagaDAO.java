/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.VagaDAO;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.Date;
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
             v.setDono((Pessoa)SessionUtil.getParam("usuario"));
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.persist(v);
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
    public void remover(Vaga v) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             em.getTransaction().begin();
             em.remove(v);
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
    public List<Vaga> buscarTodos() {
        EntityManager em = null;
        try{
            em = getEntityManager();
            Query q = em.createQuery("select v from Vaga v");
            return q.getResultList();
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
    public Vaga buscar(Long id) {
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             return em.find(Vaga.class, id);
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
    
    public List<Vaga> buscaPersonalizada(String cidade, Date periodoDe, Date periodoAte, int avaliacao){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.cidade = :cidade or v.cidade = v.cidade ");
             q.setParameter("cidade", cidade);
             return q.getResultList();
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
    
    public List<Vaga> buscarPorPessoa(Pessoa p){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.dono = :dono ");
             q.setParameter("dono", p);
             return q.getResultList();
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
