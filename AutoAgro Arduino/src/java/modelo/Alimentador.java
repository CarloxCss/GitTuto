/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Carlos
 */
public class Alimentador {
    
    private int idAlimentador;
    private String numeroIdentificacaoAlimentador;
    private String descricaoAlimentador;
    private String status;
    private String emailUsuario;
    
    private Usuario usuario;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    
    
    public String getDescricaoAlimentador() {
        return descricaoAlimentador;
    }

    public void setDescricaoAlimentador(String descricaoAlimentador) {
        this.descricaoAlimentador = descricaoAlimentador;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdAlimentador() {
        return idAlimentador;
    }

    public void setIdAlimentador(int idAlimentador) {
        this.idAlimentador = idAlimentador;
    }

    public String getNumeroIdentificacaoAlimentador() {
        return numeroIdentificacaoAlimentador;
    }

    public void setNumeroIdentificacaoAlimentador(String numeroIdentificacaoAlimentador) {
        this.numeroIdentificacaoAlimentador = numeroIdentificacaoAlimentador;
    }
    
    
    
}
