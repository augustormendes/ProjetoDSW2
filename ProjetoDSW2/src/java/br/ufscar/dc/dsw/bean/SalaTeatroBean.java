/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.PromocaoDAO;
import br.ufscar.dc.dsw.dao.SalaTeatroDAO;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaTeatro;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author Marcos Felipe
 */
@ManagedBean
@SessionScoped 
public class SalaTeatroBean implements Serializable {
    
    private SalaTeatro teatro;
    
    public String lista(){
        return "/teatro/index.xhtml?faces-redirect=true";
    }
    
    public String cadastra(){
        teatro = new SalaTeatro();
        Usuario u = new Usuario();
        teatro.setUsuario(u);
        
        return "/teatro/form.xhtml?faces-redirect=true";
    }
    
    public String edita(Long id){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        teatro = dao.get(id);
        return "form.xhtml";
    }
   
    public String salva(){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        Papel papel = new Papel();
        papel.setEmail(teatro.getUsuario().getEmail());
        papel.setNome("ROLE_TEATRO");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        teatro.getUsuario().setSenha(encoder.encode(teatro.getUsuario().getSenha()));
        teatro.getUsuario().setPapel(papel);
        teatro.getUsuario().setAtivo(true);
        if(teatro.getId() == null){
            dao.save(teatro);
        }else{
            dao.update(teatro);
        }
        return "/teatro/index.xhtml?faces-redirect=true";
    }
    
    public String delete(SalaTeatro teatro){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        dao.delete(teatro);
        return "index.xhtml?faces-redirect=true";
    }
    
    public String volta(){
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<SalaTeatro> getSalasTeatro() throws SQLException {
        SalaTeatroDAO dao = new SalaTeatroDAO();
        return dao.getAll();
    }
    
    public List<SalaTeatro> getFromCity(String city) throws SQLException {
        SalaTeatroDAO dao = new SalaTeatroDAO();
        return dao.getByCity(city);
    }
    
    public String fromTheater(){
        return"/promocao/indexFromTheater.xhtml";
    }
    
    public String fromCity(){
        return"/teatro/indexForCity.xhtml";
    }
    
    public SalaTeatro getTeatro(){
        return teatro;
    }
}
