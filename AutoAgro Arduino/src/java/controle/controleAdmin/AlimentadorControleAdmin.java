/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.controleAdmin;

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
public class AlimentadorControleAdmin {
    
    private List<Alimentador> lista;
    private Alimentador alimentador = new Alimentador();
    private boolean salvar = false;
    private int idUsuario;
    private String statusAlimentador = "teste";
    private int idAlimentador;
    
    public String preparaIncluir() {
        alimentador = new Alimentador();
        salvar = true;
        idUsuario = 0;
        return "cadastroAlimentadorTesteAdmin.xhtml?faces-redirect=true";   
    }
    
    public String preparaAlterar() {
        salvar = false;
        idUsuario = alimentador.getUsuario().getIdUsuario();
        return "configuracoesAlimentador.xhtml?faces-redirect=true";  
    }
    
    public String paginaDescricao() {

        return "cadastroAlimentador_1.xhtml?faces-redirect=true";
    }
    
    public String desvincularAlimentador() {
        
        try {
            dao.daoAdimin.AlimentadorAdminDAO.desvincularAlimentador(alimentador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return "indexAdmin.xhtml?faces-redirect=true";
    }
    
        public String vincularAlimentador() {
        
        try {
            dao.daoAdimin.AlimentadorAdminDAO.getIdUsuario(alimentador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return "indexAdmin.xhtml?faces-redirect=true";
    }
    
    @PostConstruct
    public void atualizaLista() {
        try {
            lista = dao.daoAdimin.AlimentadorAdminDAO.getLista();
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
                dao.AlimentadorDAO.inserir(alimentador);
            } else {
                dao.AlimentadorDAO.alterar(alimentador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "teste.xhtml?faces-redirect=true";
    }
    
    public String excluir(String statusAlimentador, int idAlimentador) {
        this.statusAlimentador = statusAlimentador;
        this.idAlimentador = idAlimentador;
        System.out.println(statusAlimentador);
        if("vinculado".equals(this.statusAlimentador)){
            return "confirmaExclusao.xhtml?faces-redirect=true"; 
        }else{
        try {
            AlimentadorDAO.excluir(this.idAlimentador);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
                return "alimentadoresAdmin.xhtml?faces-redirect=true";
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public List<Alimentador> getLista() {
        atualizaLista();
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
