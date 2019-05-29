
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
public class Promocao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private float preco;
    @Temporal(TemporalType.TIMESTAMP)private Date horario;
    


    @ManyToOne @JoinColumn private SiteVendas site;
    @ManyToOne @JoinColumn private SalaTeatro sala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public SiteVendas getSite() {
        return site;
    }

    public void setSite(SiteVendas site) {
        this.site = site;
    }

    public SalaTeatro getSala() {
        return sala;
    }

    public void setSala(SalaTeatro sala) {
        this.sala = sala;
    }

    public void setUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
