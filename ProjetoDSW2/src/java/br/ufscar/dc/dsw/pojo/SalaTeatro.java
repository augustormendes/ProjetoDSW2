
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SalaTeatro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String CNPJ;
    private String nome;
    private String cidade;
    
    
    @OneToOne (cascade=CascadeType.PERSIST) @JoinColumn
    private Usuario usuario;
    @OneToMany (mappedBy = "sala") private Set<Promocao> promocoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public Set<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(Set<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    
    public String toString(){
    return CNPJ;
    }
    
      public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof SalaTeatro))
		return false;
	SalaTeatro other = (SalaTeatro) obj;
	return other.CNPJ.equals(this.CNPJ);
    }
}
