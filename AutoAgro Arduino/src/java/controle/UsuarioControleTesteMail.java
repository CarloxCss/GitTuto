/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Usuario;
import util.SessionContext;

/**
 *
 * @author Carlos
 */

@ManagedBean
@SessionScoped
public class UsuarioControleTesteMail {
    
    
    private List<Usuario> lista;
    private Usuario usuario = new Usuario();
    private Usuario usuarioLogado = new Usuario();
    private boolean salvar = false;
    
    public String preparaIncluir(){
        usuario = new Usuario();
        salvar = true;
        return "cadastroUsuario.xhtml?faces-redirect=true";
    
    }
    public String preparaAdmin(){
        
        return "alimentadoresAdmin.xhtml?faces-redirect=true";
    
    }
    
    public String preparaAlterar(){
        salvar = false;
        
        usuario = SessionContext.getInstance().getUsuarioLogado();
        
        return "configuracoesUsuario.xhtml?faces-redirect=true";
        
    }
    
    @PostConstruct
    public void atualizaLista (){
        try {
            lista = UsuarioDAO.getLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enviaMail(){
        
    Properties props = new Properties();
    /** Parâmetros de conexão com servidor Gmail */
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
    "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication("testeenvio123741@gmail.com",
                 "minelegal");
           }
      });

    /** Ativa Debug para sessão */
    session.setDebug(true);

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("testeenvio123741@gmail.com"));
      //Remetente

      Address[] toUser = InternetAddress //Destinatário(s)
                 .parse(usuario.getEmail());

      message.setRecipients(Message.RecipientType.TO, toUser);
      message.setSubject("Enviando email com JavaMail");//Assunto
      message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
      /**Método para enviar a mensagem criada*/
      Transport.send(message);

      System.out.println("Feito!!!");

     } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
  
    }
    
    public String salvar(){
        try {
            if(salvar){
                UsuarioDAO.inserir(usuario);
                enviaMail();
                return "login.xhtml?faces-redirect=true";
            }else{
                UsuarioDAO.alterar(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "teste.xhtml?faces-redirect=true";
    }
    
    public String login() {
        try {
            usuarioLogado = UsuarioDAO.getLogin(usuario);
            String tipoDeUsuario = usuarioLogado.getTipoUsuario();
            if (usuarioLogado == null) {
                System.out.println("USUÁRIO NÃO ENCONTRADO");
                return "";
            } else {

                System.out.println("USUÁRIO ENCONTRADO");
                SessionContext.getInstance().setAttribute("usuario", usuarioLogado);
                    if("admin".equals(tipoDeUsuario)){
                        return "indexAdmin.xhtml?faces-redirect=true";
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "teste.xhtml?faces-redirect=true";
    }
    
    public void excluir(){
        try {
            UsuarioDAO.excluir(usuario);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
