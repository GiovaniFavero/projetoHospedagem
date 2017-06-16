/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.model.Avaliacao;
import br.com.hospedagem.model.Pessoa;
import br.com.hospedagem.model.Vaga;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Favero
 */
public class JPAAvaliacaoDAO {
    
    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospedagemPU");
        return emf.createEntityManager();
    }


    public Avaliacao salvar(Avaliacao v) {
        EntityManager em = null;
        try{
             Avaliacao temp;
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
    
     public List<Avaliacao> buscarAvaliacoesVagaPessoa(Vaga vaga, Pessoa pessoa){
        EntityManager em = null;
        try{
             em = this.getEntityManager();
             Query q = em.createQuery("Select a from Avaliacao a where a.avaliador = :pessoa "
                     + "and a.vaga = :vaga");
             q.setParameter("pessoa", pessoa);
             q.setParameter("vaga", vaga);
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
