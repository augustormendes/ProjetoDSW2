/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class UsuarioDAO extends GenericDAO<Usuario> {

    @Override
   public Usuario get(Long id) {
 EntityManager em = this.getEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;    }

    @Override
   public List<Usuario> getAll() {
EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select site from SiteVendas site", Usuario.class);
        List<Usuario> usuarios = q.getResultList();
        em.close();
        return usuarios;   
    }

    @Override
   public void save(Usuario t) {
 EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(t);
        tx.commit();
        em.close();    }

    @Override
   public void update(Usuario t) {
EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(t);
        tx.commit();
        em.close();    }

    @Override
   public void delete(Usuario t) {
EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        t = em.getReference(Usuario.class, t.getId());
        tx.begin();
        em.remove(t);
        tx.commit();    }
    
}
