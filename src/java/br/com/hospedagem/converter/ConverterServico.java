/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.converter;

import br.com.hospedagem.dao.core.DAOFactory;
import br.com.hospedagem.dao.jpa.JPAServicoDAO;
import br.com.hospedagem.dao.jpa.JPAVagaDAO;
import br.com.hospedagem.model.Servico;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Favero
 */
@FacesConverter(value = "converterServico", forClass = Servico.class)
public class ConverterServico implements Converter{

    private JPAServicoDAO servicoDAO = (JPAServicoDAO) DAOFactory.getServicoDAO();
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Servico retorno = null;
        if (value != null && !value.equals("")) {
            retorno = servicoDAO.buscarPorDescricao(value);
                
            if (retorno == null) {
                String msg = "Servico não existe!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        msg, msg);
                throw new ConverterException(message);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        Servico s = (Servico) o;
        return s.getDescricao();
    }
    
}
