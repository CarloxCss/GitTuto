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
import modelo.Alimentador;
import modelo.Usuario;
import util.Conexao;
import util.SessionContext;

/**
 *
 * @author Carlos
 */
public class AlimentadorDAO {
    
        public static void vincularAlimentador(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update alimentador set Usuario_idUsuario=?, status='vinculado', descricaoAlimentador=?\n" +
                     "WHERE numeroIdentificacaoAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
        stmt.setString(2, alimentador.getDescricaoAlimentador());
        stmt.setString(3, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }    
    
        public static void inserir(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "insert into alimentador (numeroIdentificacaoAlimentador, descricaoAlimentador, Usuario_idUsuario)\n" +
                     "values (? , ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.setString(2, alimentador.getDescricaoAlimentador());
        stmt.setInt(3, alimentador.getUsuario().getIdUsuario());
        stmt.execute();
        stmt.close();
        con.close();
    }
        
        public static void alterar(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update alimentador set numeroIdentificacaoAlimentador=?, descricaoAlimentador=?, Usuario_idUsuario=?\n" +
                     "WHERE idAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.setString(2, alimentador.getDescricaoAlimentador());
        stmt.setInt(3, alimentador.getUsuario().getIdUsuario());
        stmt.setInt(4, alimentador.getIdAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
        
        public static void excluir(int idAlimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "delete from alimentador WHERE idAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idAlimentador);
        System.out.println(sql);
        stmt.execute();
        stmt.close();
        con.close();
    }
        
        public static List<Alimentador> getLista() throws SQLException {
        List<Alimentador> lista = new ArrayList<Alimentador>();
        Connection con = Conexao.getConexao();
        String sql = "SELECT * FROM alimentador a, usuario u\n" +
                     "WHERE u.idUsuario = a.Usuario_idUsuario AND\n" +
                     "a.Usuario_idUsuario = ?\n" +
                     "ORDER BY a.idAlimentador";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setNomeDeUsuario(rs.getString("nomeDeUsuario"));
            u.setTipoUsuario(rs.getString("tipoUsuario"));

            Alimentador a = new Alimentador();
            a.setIdAlimentador(rs.getInt("idAlimentador"));
            a.setDescricaoAlimentador(rs.getString("descricaoAlimentador"));
            a.setNumeroIdentificacaoAlimentador(rs.getString("numeroIdentificacaoAlimentador"));
            a.setStatus(rs.getString("status"));
            a.setUsuario(u);
          
            lista.add(a);
        }
        return lista;
    }
        
    
}
