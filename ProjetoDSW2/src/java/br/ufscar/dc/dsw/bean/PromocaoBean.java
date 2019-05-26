/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.PromocaoDAO;
import br.ufscar.dc.dsw.pojo.Promocao;
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
public class PromocaoBean {
    private Promocao promocao;
    
    public String lista(){
        return "promocao/index.xhtml";
    }
    
    public String cadastra(){
        promocao = new Promocao();
        
        return "promocao/form.xhtml";
    }
    
    public String edita(Long id){
        PromocaoDAO dao = new PromocaoDAO();
        promocao = dao.get(id);
        return "form.xhtml?faces-redirect=true";
    }
    
    public String salva(){
        PromocaoDAO dao = new PromocaoDAO();
        if(promocao.getId() == null){
            dao.save(promocao);
        }else{
            dao.update(promocao);
        }
        return "index.xhtml?faces-redirect=true";
    }
    
    public String delete(Promocao promocao){
        PromocaoDAO dao = new PromocaoDAO();
        dao.delete(promocao);
        return "index.xhtml?faces-redirect=true";
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
}
