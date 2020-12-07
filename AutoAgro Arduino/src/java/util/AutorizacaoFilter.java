/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
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
import modelo.Usuario;


@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

    private static final String[][] DIREITO_ACESSO = {
        {"admin",
        "/faces/login.xhtml",
        "/faces/index.xhtml",
        "/faces/indexAdmin.xhtml",
        "/faces/cadastroAlimentador.xhtml",
        "/faces/cadastroAlimentadorTesteAdmin.xhtml",
        "/faces/cadastroAlimentador_1.xhtml",
        "/faces/alimentadoresAdmin.xhtml",
        "/faces/confirmaExclusao.xhtml",
        "/faces/cadastroRotina.xhtml",
        "/faces/cadastroUsuario.xhtml",
        "/faces/desvincularAlimentadorAdmin.xhtml",
        "/faces/vincularAlimentadorAdmin.xhtml",
        "/faces/rotinasAdmin.xhtml",
        "/faces/teste_USER.xhtml",
        "/faces/teste_USER_2.xhtml",
        "/faces/teste.xhtml",
        },

        {"comum",
        "/faces/login.xhtml",
        "/faces/index.xhtml",
        "/faces/cadastroAlimentador.xhtml",
        "/faces/cadastroAlimentador_1.xhtml",
        "/faces/cadastroRotina.xhtml",
        "/faces/cadastroUsuario.xhtml",
        "/faces/teste_USER.xhtml",
        "/faces/teste_USER_2.xhtml",
        "/faces/teste.xhtml",}
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        Usuario user = null;

        HttpSession sess = ((HttpServletRequest) req).getSession();

        if (sess != null) {
            user = (Usuario) sess.getAttribute("usuario");

        }
        
        if ((user != null)
                && request.getRequestURI().endsWith("/faces/login.xhtml")
                && request.getRequestURI().endsWith("/faces/cadastroUsuario.xhtml")) {
            response.sendRedirect(request.getContextPath() + "/faces/index.xhtml");
        }

        if ((user == null)
                && !request.getRequestURI().endsWith("/faces/login.xhtml")
                && !request.getRequestURI().endsWith("/faces/cadastroUsuario.xhtml")) {
            response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
        }else{
            try {
                boolean foi = false;
                if (user.getTipoUsuario().equals("admin")) {
                    for (int i = 1; i < DIREITO_ACESSO[0].length; i++) {
                        if (request.getRequestURI().endsWith(DIREITO_ACESSO[0][i])) {
                            chain.doFilter(req, res);
                            foi = false;
                            break;
                        } else {
                            foi = true;
                        }
                    }
                    if (foi) {
                        response.sendRedirect(request.getContextPath() + "/faces/p0.xhtml");
                    }
                } else if (user.getTipoUsuario().equals("comum")) {
                    for (int i = 1; i < DIREITO_ACESSO[1].length; i++) {
                        if (request.getRequestURI().endsWith(DIREITO_ACESSO[1][i])) {
                            chain.doFilter(req, res);
                            foi = false;
                            break;
                        } else {
                            foi = true;
                        }
                    }
                    if (foi) {
                        response.sendRedirect(request.getContextPath() + "/faces/p0.xhtml");
                    }

                }
            } catch (NullPointerException e) {
                chain.doFilter(req, res);
            }

        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}