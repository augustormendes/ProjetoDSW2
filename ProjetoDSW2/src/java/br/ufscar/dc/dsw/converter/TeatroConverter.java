/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.converter;

import br.ufscar.dc.dsw.dao.SalaTeatroDAO;
import br.ufscar.dc.dsw.pojo.SalaTeatro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcos Felipe
 */
@FacesConverter(value = "TeatroConverter")
public class TeatroConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id = Long.parseLong(string);
        SalaTeatroDAO dao = new SalaTeatroDAO();
        return dao.get(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        SalaTeatro sala = (SalaTeatro) o;
        return sala.getId().toString();
    }
}
