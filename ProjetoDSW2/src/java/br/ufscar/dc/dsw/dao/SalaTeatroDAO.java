package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.SalaTeatro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class SalaTeatroDAO extends GenericDAO<SalaTeatro> {

    @Override
    public SalaTeatro get(Long id) {
        EntityManager em = this.getEntityManager();
        SalaTeatro sala = em.find(SalaTeatro.class, id);
        em.close();
        return sala;
    }

    @Override
    public List<SalaTeatro> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select s from SalaTeatro s", SalaTeatro.class);
        List<SalaTeatro> salas = q.getResultList();
        em.close();
        return salas;
    }

    @Override
    public void save(SalaTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(sala);
        tx.commit();
        em.close();
    }

    @Override
    public void update(SalaTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(sala);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(SalaTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        sala = em.getReference(SalaTeatro.class, sala.getCNPJ());
        tx.begin();
        em.remove(sala);
        tx.commit();
    }
    
    public SalaTeatro getByCNPJ(String cnpj) {
        EntityManager em = this.getEntityManager();
        String sql = "SELECT s FROM SalaTeatro s "
                + "WHERE s.CNPJ = :cnpj";
        TypedQuery<SalaTeatro> q = em.createQuery(sql, SalaTeatro.class);
        q.setParameter("cnpj", cnpj);
        return q.getSingleResult();
    }
    
    public List<SalaTeatro> getByCity(String city) {
        EntityManager em = this.getEntityManager();
        String sql = "SELECT s FROM SalaTeatro s "
                + "WHERE s.cidade = :city";
        TypedQuery<SalaTeatro> q = em.createQuery(sql, SalaTeatro.class);
        q.setParameter("city", city);
        return q.getResultList();
    }
}
