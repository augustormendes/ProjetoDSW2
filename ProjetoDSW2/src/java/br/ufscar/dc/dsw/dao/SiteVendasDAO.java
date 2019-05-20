
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.SiteVendas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class SiteVendasDAO extends GenericDAO<SiteVendas> {

    @Override
    public SiteVendas get(Long id) {
        EntityManager em = this.getEntityManager();
        SiteVendas site = em.find(SiteVendas.class, id);
        em.close();
        return site;
    }

    @Override
    public List<SiteVendas> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select site from SiteVendas site", SiteVendas.class);
        List<SiteVendas> sites = q.getResultList();
        em.close();
        return sites;
    }

    @Override
    public void save(SiteVendas site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(site);
        tx.commit();
        em.close();
    }

    @Override
    public void update(SiteVendas site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(site);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(SiteVendas site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        site = em.getReference(SiteVendas.class, site.getUrl());
        tx.begin();
        em.remove(site);
        tx.commit();
    }
    
    public SiteVendas getByUrl(String url) {
        EntityManager em = this.getEntityManager();
        String sql = "SELECT s FROM SiteVendas s "
                + "WHERE s.url = :url";
        TypedQuery<SiteVendas> q = em.createQuery(sql, SiteVendas.class);
        q.setParameter("url", url);
        return q.getSingleResult();
    }
    
}
