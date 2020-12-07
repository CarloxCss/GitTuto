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
public class Rotina {
    
    private int idRotina;
    private String hora;
    private String minuto;
    private String quantia;
    private String dia;
    private String status;
    private String alimentadorRot;
    private String emailUsuario;
    
    private Alimentador alimentador;
    private Usuario usuario;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
      
    public String getAlimentadorRot() {
        return alimentadorRot;
    }

    public void setAlimentadorRot(String alimentadorRot) {
        this.alimentadorRot = alimentadorRot;
    }

    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }
    
    

    public int getIdRotina() {
        return idRotina;
    }

    public void setIdRotina(int idRotina) {
        this.idRotina = idRotina;
    }

    public String getQuantia() {
        return quantia;
    }

    public void setQuantia(String quantia) {
        this.quantia = quantia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    
    
    
}
