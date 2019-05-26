/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.SiteVendasDAO;
import br.ufscar.dc.dsw.pojo.SiteVendas;
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
public class SiteVendasBean {
    
    private SiteVendas site;
    public String lista(){
        return "site/index.xhtml";
    }
    
    public String cadastra(){
        site = new SiteVendas();
        Usuario u = new Usuario();
        site.setUsuario(u);
        
        return "site/form.xhtml";
    }
    
    public String edita(Long id){
        SiteVendasDAO dao = new SiteVendasDAO();
        site = dao.get(id);
        return "form.xhtml?faces-redirect=true";    
    }
    
    public String salva(){
        SiteVendasDAO dao = new SiteVendasDAO();
        if (site.getId() == null){
            dao.save(site);
        }else{
            dao.update(site);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String delete(SiteVendas site){
        SiteVendasDAO dao = new SiteVendasDAO();
        dao.delete(site);
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String volta(){
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<SiteVendas> getSiteVendas() throws SQLException {
        SiteVendasDAO dao = new SiteVendasDAO();
        return dao.getAll();
    }
    
    public SiteVendas getSite(){
        return site;
    }
}
