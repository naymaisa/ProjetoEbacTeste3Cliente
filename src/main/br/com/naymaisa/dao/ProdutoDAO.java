/**
 * @author nayara.maisa
 */

package main.br.com.naymaisa.dao;

import main.br.com.naymaisa.dao.jdbc.ConnectionFactory;
import main.br.com.naymaisa.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO  implements  IProdutoDAO{
    @Override
    public Integer cadastrar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (codigo, nome) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            return ps.executeUpdate();
        }
    }

    @Override
    public Produto consultar(String codigo) throws SQLException {
        String sql = "SELECT id, codigo, nome FROM produto WHERE codigo = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setCodigo(rs.getString("codigo"));
                    p.setNome(rs.getString("nome"));
                    return p;
                }
                return null;
            }
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET codigo = ?, nome = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public Integer excluir(Produto produto) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, produto.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public List<Produto> buscarTodos() throws SQLException {
        String sql = "SELECT id, codigo, nome FROM produto";
        List<Produto> produtos = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNome(rs.getString("nome"));
                produtos.add(p);
            }
        }
        return produtos;
    }
}
