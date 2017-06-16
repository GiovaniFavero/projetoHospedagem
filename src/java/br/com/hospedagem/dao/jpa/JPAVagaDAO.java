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
    public Vaga salvar(Vaga v) {
        EntityManager em = null;
        try{
             Vaga temp;
             em = this.getEntityManager();
             em.getTransaction().begin();
             temp = em.merge(v);
             em.getTransaction().commit();
             return temp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
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
             Vaga vaga = em.merge(v);
             em.remove(vaga);
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
    
    public List<Vaga> buscarPorHospede(Pessoa p){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.hospede = :hospede ");
             q.setParameter("hospede", p);
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

    public List<Vaga> buscarVagasDisponíveis(){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.ativo = :ativo and v.reservado = :reservado ");
             q.setParameter("ativo", true);
             q.setParameter("reservado", false);
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
    
    public List<Vaga> buscarVagasPorCidade(String cidade){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.ativo = :ativo and v.reservado = :reservado "
                     + "and v.cidade = :cidade ");
             q.setParameter("ativo", true);
             q.setParameter("reservado", false);
             q.setParameter("cidade", cidade);
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
    
    public List<Vaga> buscarVagasPorPeriodo(Date periodoDe, Date periodoAte){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.ativo = :ativo and v.reservado = :reservado "
                     + "and (v.periodoInicial between :periodoDe and :periodoAte "
                     + "or v.periodoFinal between :periodoDe1 and :periodoAte1) ");
             q.setParameter("ativo", true);
             q.setParameter("reservado", false);
             q.setParameter("periodoDe", periodoDe);
             q.setParameter("periodoAte", periodoAte);
             q.setParameter("periodoDe1", periodoDe);
             q.setParameter("periodoAte1", periodoAte);
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
    
    public List<Vaga> buscarVagasPorAvaliacaoMinima(int avaliacaoMinima){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.ativo = :ativo and v.reservado = :reservado "
                     + "and :avaliacaoMinima <= (Select min(a.valor) from "
                     + "Avaliacao a where a.vaga = v)");
             q.setParameter("ativo", true);
             q.setParameter("reservado", false);
             q.setParameter("avaliacaoMinima", avaliacaoMinima);
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
    
    public List<Vaga> buscarVagasPorHospede(Pessoa p){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select v from Vaga v "
                     + "where v.hospede = :hospede");
             q.setParameter("hospede", p);
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
