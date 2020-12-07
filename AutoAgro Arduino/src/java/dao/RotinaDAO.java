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
import modelo.Rotina;
import modelo.Usuario;
import util.Conexao;
import util.SessionContext;

/**
 *
 * @author Carlos
 */
public class RotinaDAO {
    
    public static void inserir(Rotina rotina) throws SQLException {
        
        String alimentadorRot = "a"+ rotina.getAlimentador().getIdAlimentador();
        System.out.println(rotina.getAlimentador().getIdAlimentador());
        System.out.println(rotina.getUsuario().getIdUsuario());
        Connection con = Conexao.getConexao();
        String sql = "insert into rotina (hora, minuto, quantia, dia, status, alimentador, Alimentador_idAlimentador, Usuario_idUsuario)\n" +
"                     values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, rotina.getHora());
        stmt.setString(2, rotina.getMinuto());
        stmt.setString(3, rotina.getQuantia());
        stmt.setString(4, rotina.getDia());
        stmt.setString(5, rotina.getStatus());
        stmt.setString(6, alimentadorRot);        
        stmt.setInt(7, rotina.getAlimentador().getIdAlimentador());
        stmt.setInt(8, rotina.getUsuario().getIdUsuario());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Rotina rotina) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "update rotina set hora=?, minuto=?, quantia=?, dia=?, status=?, Alimentador_idAlimentador=?, Usuario_idUsuario=?\n" +
                     "WHERE idRotina=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, rotina.getHora());
        stmt.setString(2, rotina.getMinuto());
        stmt.setString(3, rotina.getQuantia());
        stmt.setString(4, rotina.getDia());
        stmt.setString(5, rotina.getStatus());
        stmt.setInt(6, rotina.getAlimentador().getIdAlimentador());
        stmt.setInt(7, rotina.getUsuario().getIdUsuario());
        stmt.setInt(8, rotina.getIdRotina());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void excluir(Rotina rotina) throws SQLException {

        Connection con = Conexao.getConexao();
        String sql = "delete from rotina WHERE idRotina=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rotina.getIdRotina());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static List<Rotina> getLista() throws SQLException {
        List<Rotina> lista = new ArrayList<Rotina>();
        Connection con = Conexao.getConexao();
        String sql = "SELECT * FROM rotina r, usuario u, alimentador a\n" +
"                     WHERE u.idUsuario = r.Usuario_idUsuario AND\n" +
"                     r.Alimentador_idAlimentador = a.idAlimentador AND\n" +
"                     r.Usuario_idUsuario = ?\n" +
"                     ORDER BY r.idRotina";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, SessionContext.getInstance().getUsuarioLogado().getIdUsuario());
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
            a.setUsuario(u);

            Rotina r = new Rotina();
            r.setIdRotina(rs.getInt("idRotina"));
            r.setHora(rs.getString("hora"));
            r.setMinuto(rs.getString("minuto"));
            r.setQuantia(rs.getString("quantia"));
            r.setDia(rs.getString("dia"));
            r.setStatus(rs.getString("status"));
            r.setAlimentador(a);
            r.setUsuario(u);
          
            lista.add(r);
        }
        return lista;
    }
    
}
