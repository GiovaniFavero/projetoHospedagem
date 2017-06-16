package br.com.hospedagem.controller.login;


import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@WebFilter(servletNames = {"Faces Servlet"})
public class FiltroLogin implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest requisicao = (HttpServletRequest) request;
        HttpSession session = requisicao.getSession();

        if ((session.getAttribute("usuario") != null)
                || (requisicao.getRequestURI().endsWith("telaLogin.xhtml"))
                || (requisicao.getRequestURI().endsWith("cadastroPessoa.xhtml"))
                || (requisicao.getRequestURI().endsWith("index.xhtml"))
                || (requisicao.getRequestURI().endsWith("detalhesVaga.xhtml"))
                || (requisicao.getRequestURI().endsWith("perfilPessoa.xhtml"))
                || (requisicao.getRequestURI().contains("javax.faces.resource/"))) {

            chain.doFilter(request, response);
        } else {
            redireciona("faces/index.xhtml", response);
        }

    }

    private void redireciona(String url, ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}
}
