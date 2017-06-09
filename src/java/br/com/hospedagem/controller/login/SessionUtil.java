package br.com.hospedagem.controller.login;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 9584013
 */
public class SessionUtil {

    public static HttpSession getSession() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        return sessao;
    }

    public static void setParam(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getParam(String key) {
        return getSession().getAttribute(key);
    }

    public static void remove(String key) {
        getSession().removeAttribute(key);
    }

    public static void invalidate() {
        getSession().invalidate();
    }
}
