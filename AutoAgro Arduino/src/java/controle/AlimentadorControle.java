/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlimentadorDAO;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Alimentador;
import modelo.Usuario;
import util.SessionContext;

/**
 *
 * @author Carlos
 */

@ManagedBean
@SessionScoped
public class AlimentadorControle {
    
    private List<Alimentador> lista;
    private Alimentador alimentador = new Alimentador();
    private boolean salvar = false;
    private int idUsuario;
    
    public String preparaIncluir() {
        alimentador = new Alimentador();
        salvar = true;
        idUsuario = 0;
        return "cadastroAlimentador.xhtml?faces-redirect=true";
        
    }
    
   public String preparaAlterar() {
        salvar = false;
        idUsuario = alimentador.getUsuario().getIdUsuario();
        return "configuracoesAlimentador.xhtml?faces-redirect=true";  
    } 

    public String paginaDescricao() {

        return "cadastroAlimentador_1.xhtml?faces-redirect=true";
        
    }
    
    @PostConstruct
    public void atualizaLista() {
        try {
            lista = AlimentadorDAO.getLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String salvar() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
        alimentador.setUsuario(usuario);
        try {
            if (salvar) {
                AlimentadorDAO.vincularAlimentador(alimentador);
            } else {
                AlimentadorDAO.alterar(alimentador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "teste.xhtml?faces-redirect=true";
    }
    
    public void excluir() {
        try {
            AlimentadorDAO.excluir(alimentador);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Alimentador> getLista() {
        return lista;
    }

    public void setLista(List<Alimentador> lista) {
        this.lista = lista;
    }

    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
