/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PaginaNovaDAO;
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
public class PaginaNovaControle {
    
    public int num = 0;
    private List<Rotina> lista;
    private Rotina rotina = new Rotina();
    private boolean salvar = false;
    private int idAlimentador;
    private int idUsuario;
    
    public String preparaIncluir(int idAlimentador) {
        rotina = new Rotina();
        salvar = true;
        this.idAlimentador = idAlimentador;
        System.out.println(idAlimentador);
        idUsuario = 0;
        
        return "cadastroRotina.xhtml?faces-redirect=true";
    }
    
    public void atualizaLista(int idAlimentador) {
        try {
            lista = PaginaNovaDAO.getLista(idAlimentador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String salvar() {
        Alimentador alimentador = new Alimentador();
        alimentador.setIdAlimentador(idAlimentador);
        System.out.println(idAlimentador);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
        
        rotina.setAlimentador(alimentador);
        rotina.setUsuario(usuario);
        try {
            if (salvar) {
                PaginaNovaDAO.inserir(rotina,idAlimentador);
            } else {
                RotinaDAO.alterar(rotina);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista(idAlimentador);
        return "teste.xhtml?faces-redirect=true";
    }
    
        public void excluirRotina() {
        try {
            RotinaDAO.excluir(rotina);
            atualizaLista(idAlimentador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    public List<Rotina> getLista(int idAlimentador) {
        atualizaLista(idAlimentador);
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

    public int getIdAlimentador() {
        return idAlimentador;
    }

    public void setIdAlimentador(int idAlimentador) {
        this.idAlimentador = idAlimentador;
    }
    
    
}
