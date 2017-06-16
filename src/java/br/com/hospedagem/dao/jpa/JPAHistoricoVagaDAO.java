/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.dao.jpa;

import br.com.hospedagem.controller.login.SessionUtil;
import br.com.hospedagem.dao.core.VagaDAO;
import br.com.hospedagem.model.HistoricoVaga;
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
public class JPAHistoricoVagaDAO{
    
    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospedagemPU");
        return emf.createEntityManager();
    }

   public HistoricoVaga buscarHistoricoVagaPorHospedeiro(Vaga vaga, Pessoa pessoa){
        EntityManager em = null;
        try{
             em = getEntityManager();
             Query q = em.createQuery("select h from HistoricoVaga h "
                     + "where h.vaga = :vaga and h.pessoa = :pessoa "
                     + "and h.tipo = 2 ");
             q.setParameter("vaga", vaga);
             q.setParameter("pessoa", pessoa);
             return (HistoricoVaga) q.getSingleResult();
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
