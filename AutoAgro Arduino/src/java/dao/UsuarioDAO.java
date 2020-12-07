/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import util.Conexao;

/**
 *
 * @author Carlos
 */
public class UsuarioDAO {
    
    public static void inserir(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into usuario (nome, email, senha, nomeDeUsuario, tipoUsuario) values (?, ?, password(md5(?)), ?, 'comum')";
        System.out.println(sql);
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getNomeDeUsuario());    
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, nomeDeUsuario = ?, tipoUsuario = ?\n" +
                     "WHERE idUsuario = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getNomeDeUsuario()); 
        stmt.setString(5, usuario.getTipoUsuario());  
        stmt.setInt(6, usuario.getIdUsuario());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
        
    public static void excluir(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "delete from usuario where idUsuario = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, usuario.getIdUsuario());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
        
    public static List<Usuario> getLista() throws SQLException{
            List<Usuario> lista = new ArrayList<Usuario>();
            Connection con = Conexao.getConexao();
            
            String sql = "select * from usuario ORDER BY nomeDeUsuario";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setNomeDeUsuario(rs.getString("nomeDeUsuario"));
                u.setTipoUsuario(rs.getString("tipoUsuario"));
                
                lista.add(u);           
            }
            return lista;
    }
        
    public static Usuario getLogin(Usuario usuarioLogin) throws SQLException {
        Usuario usuario = null;
        Connection con = Conexao.getConexao();
        String sql = "SELECT * FROM usuario u\n" +
                     "WHERE u.email = ? AND\n" +
                     "u.senha = password(md5(?))\n" +
                     "ORDER BY\n" +
                     "u.nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuarioLogin.getEmail());
        stmt.setString(2, usuarioLogin.getSenha());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {

                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNomeDeUsuario(rs.getString("nomeDeUsuario"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
            
        }
        return usuario;
    }
    
}
