/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.controleAdmin;

import controle.*;
import dao.RotinaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Alimentador;
import modelo.Rotina;
import modelo.Usuario;
import util.SessionContext;

/**
 *
 * @author Carlos
 */

@ManagedBean
@SessionScoped
public class RotinaControleAdmin {
    
    public int num = 0;
    private List<Rotina> lista;
    private Rotina rotina = new Rotina();
    private boolean salvar = false;
    private int idAlimentador;
    private int idUsuario;
    
    public String preparaIncluir() {
        rotina = new Rotina();
        salvar = true;
        idAlimentador = 0;
        idUsuario = 0;

        
        return "cadastroRotina.xhtml?faces-redirect=true";
    }
        
    public String preparaAlterar() {
        salvar = false;
        idAlimentador = rotina.getAlimentador().getIdAlimentador();
        idUsuario = rotina.getUsuario().getIdUsuario();
        return "cadastroRotina.xhtml?faces-redirect=true";
        
    }
    
        public void buscar() {
        try {
            lista = dao.daoAdimin.RotinaAdminDAO.buscar(rotina);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @PostConstruct
    public void atualizaLista() {
        try {
            lista = dao.daoAdimin.RotinaAdminDAO.getLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    

    
    public String salvar() {
        Alimentador alimentador = new Alimentador();
        alimentador.setIdAlimentador(idAlimentador);
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
        
        rotina.setAlimentador(alimentador);
        rotina.setUsuario(usuario);
        try {
            if (salvar) {
                RotinaDAO.inserir(rotina);
            } else {
                RotinaDAO.alterar(rotina);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "manutencaoRotina.xhtml?faces-redirect=true";
    }
    
    public void excluir() {
        try {
            RotinaDAO.excluir(rotina);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getIdAlimentador() {
        return idAlimentador;
    }

    public void setIdAlimentador(int idAlimentador) {
        this.idAlimentador = idAlimentador;
    }
    
    
    

    public List<Rotina> getLista() {
        return lista;
    }

    public void setLista(List<Rotina> lista) {
        this.lista = lista;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
    
}
