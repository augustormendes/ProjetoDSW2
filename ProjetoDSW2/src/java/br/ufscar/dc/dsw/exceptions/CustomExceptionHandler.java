/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.exceptions;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
/**
 *
 * @author Marcos Felipe
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    
    private ExceptionHandler wrapped;
 
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
 
        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
 
            Throwable exception = context.getException();
 
            try {

                requestMap.put("exceptionMessage", exception.getMessage());
 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, "O sistema se recuperou de um erro inesperado.", ""));
 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));
 
                navigationHandler.handleNavigation(facesContext, null, null);
 
                facesContext.renderResponse();
            } finally {
                iterator.remove();
            }
        }
        getWrapped().handle();
    }
    
}
