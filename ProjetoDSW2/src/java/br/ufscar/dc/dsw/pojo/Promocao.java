
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity 
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"horario","site"}),@UniqueConstraint(columnNames={"horario","sala"})})
public class Promocao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private float preco;
    private String nome;
    @Column(name = "horario")
    @Temporal(TemporalType.TIMESTAMP)private Date horario;
    


    @ManyToOne @JoinColumn(name="site") private SiteVendas site;
    @ManyToOne @JoinColumn(name="sala") private SalaTeatro sala;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
 
}
