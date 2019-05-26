/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.SalaTeatroDAO;
import br.ufscar.dc.dsw.pojo.SalaTeatro;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcos Felipe
 */
@ManagedBean
@SessionScoped 
public class SalaTeatroBean implements Serializable {
    
    private SalaTeatro teatro;
    
    public String lista(){
        return "/teatro/index.xhtml";
    }
    
    public String cadastra(){
        teatro = new SalaTeatro();
        Usuario u = new Usuario();
        teatro.setUsuario(u);
        
        return "/teatro/form.xhtml";
    }
    
    public String edita(Long id){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        teatro = dao.get(id);
        return "form.xhtml";
    }
   
    public String salva(){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        if(teatro.getId() == null){
            dao.save(teatro);
        }else{
            dao.update(teatro);
        }
        return "/teatro/index.xhtml";
    }
    
    public String delete(SalaTeatro teatro){
        SalaTeatroDAO dao = new SalaTeatroDAO();
        dao.delete(teatro);
        return "index.xhtml";
    }
    
    public String volta(){
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<SalaTeatro> getSalasTeatro() throws SQLException {
        SalaTeatroDAO dao = new SalaTeatroDAO();
        return dao.getAll();
    }
    
    public SalaTeatro getTeatro(){
        return teatro;
    }
}
