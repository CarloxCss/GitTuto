/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.controleAdmin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.SessionContext;

/**
 *
 * @author Carlos
 */

@ManagedBean
@SessionScoped
public class IndexAdminControle {
    
        public String index() {
            
        return "index.xhtml?faces-redirect=true";

    }
        
        public String alimentadoresAdmin(){
        
        return "alimentadoresAdmin.xhtml?faces-redirect=true";
            
    }
        
        public String desvincularAlimentadores(){
        
        return "desvincularAlimentadorAdmin.xhtml?faces-redirect=true";
            
    }
        
        public String vincularAlimentadores(){
        
        return "vincularAlimentadorAdmin.xhtml?faces-redirect=true";
            
    }    
        
        public String rotinasAdmin(){
        
        return "rotinasAdmin.xhtml?faces-redirect=true";
            
    }
        
    public String teste() {
            
        return "teste.xhtml?faces-redirect=true";

    }
    
        public String alimentadorNovo() {
            
        return "alimentadorNovo.xhtml?faces-redirect=true";

    }
    
        public String manutencaoUsuario() {
            
        return "manutencaoUsuario.xhtml?faces-redirect=true";

    }
        
        public String manutencaoAlimentador() {
            
        return "manutencaoAlimentador.xhtml?faces-redirect=true";

    }

        public String manutencaoRotina() {
            
        return "manutencaoRotina.xhtml?faces-redirect=true";

    }
     
        public String testeUser() {
            
        return "teste_USER.xhtml?faces-redirect=true";

    }

        public String testeUser2() {
            
        return "teste_USER_2.xhtml?faces-redirect=true";

    }
        
        public String sair() {
            
            SessionContext.getInstance().encerrarSessao();
            
        return "login.xhtml?faces-redirect=true";

    }
    
}
