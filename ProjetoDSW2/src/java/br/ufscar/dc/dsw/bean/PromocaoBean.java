/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.PromocaoDAO;
import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaTeatro;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Marcos Felipe
 */
@ManagedBean
@SessionScoped
public class PromocaoBean {
    private Promocao promocao;
    
    public String lista(){
        return "/promocao/index.xhtml";
    }
    
    public String cadastra(){
        promocao = new Promocao();
        
        return "/promocao/form.xhtml?faces-redirect=true";
    }
    
    public String edita(Long id){
        PromocaoDAO dao = new PromocaoDAO();
        promocao = dao.get(id);
        return "form.xhtml?faces-redirect=true";
    }
    
    public String salva(){
        PromocaoDAO dao = new PromocaoDAO();
        User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SalaTeatro t = dao.getSalaFromEmail(u.getUsername());
        promocao.setSala(t);
        if(promocao.getId() == null){
            dao.save(promocao);
        }else{
            dao.update(promocao);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String delete(Promocao promocao){
        PromocaoDAO dao = new PromocaoDAO();
        dao.delete(promocao);
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String volta(){
        return "/index.html?faces-redirect=true";
    }
    
    public List<Promocao> getPromocoes() throws SQLException {
        PromocaoDAO dao = new PromocaoDAO();
        return dao.getAll();
    }
    
    public Promocao getPromocao(){
        return promocao;
    }
    public Date getCurrentDate(){
        return new Date();
    }
}
