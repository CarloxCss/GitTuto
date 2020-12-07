/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.daoAdimin;

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
public class AlimentadorAdminDAO {
    
    public static void inserir(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "insert into alimentador (numeroIdentificacaoAlimentador, descricaoAlimentador, Usuario_idUsuario)\n" +
                     "values (?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update alimentador set numeroIdentificacaoAlimentador=?, descricaoAlimentador=?, status=?, Usuario_idUsuario=?\n" +
                     "WHERE idAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.setString(2, alimentador.getDescricaoAlimentador());
        stmt.setString(3, alimentador.getStatus());
        stmt.setInt(4, alimentador.getUsuario().getIdUsuario());
        stmt.setInt(5, alimentador.getIdAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void desvincularAlimentador(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update alimentador set Usuario_idUsuario=NULL, status='desvinculado', descricaoAlimentador=NULL\n" +
                     "WHERE numeroIdentificacaoAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
        public static void getIdUsuario(Alimentador alimentador) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "SELECT\n" +
                     "*\n" +
                     "FROM\n" +
                     "usuario u\n" +
                     "WHERE\n" +
                     "u.email = ?";
         PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, alimentador.getEmailUsuario());
                System.out.println(sql);
        ResultSet rs = stmt.executeQuery();
          while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setNomeDeUsuario(rs.getString("nomeDeUsuario"));
            u.setTipoUsuario(rs.getString("tipoUsuario"));
            alimentador.setUsuario(u);
          }
        System.out.println(sql);
        dao.daoAdimin.AlimentadorAdminDAO.vincularAlimentador(alimentador);
    }   
    
        public static void vincularAlimentador(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update alimentador set Usuario_idUsuario=?, status='vinculado', descricaoAlimentador='Novo Alimentador'\n" +
                     "WHERE numeroIdentificacaoAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, alimentador.getUsuario().getIdUsuario());
        stmt.setString(2, alimentador.getNumeroIdentificacaoAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void excluir(Alimentador alimentador) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "delete from alimentador WHERE idAlimentador=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, alimentador.getIdAlimentador());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static List<Alimentador> getLista() throws SQLException {
        List<Alimentador> lista = new ArrayList<Alimentador>();
        Connection con = Conexao.getConexao();
        String sql = "SELECT \n" +
                     "*\n" +
                     "FROM \n" +
                     "	alimentador a\n" +
                     "LEFT JOIN\n" +
                     "	usuario u ON u.idUsuario = a.Usuario_idUsuario";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        System.out.println(sql);
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
