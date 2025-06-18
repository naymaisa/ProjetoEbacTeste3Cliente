package main.br.com.naymaisa.dao;

import main.br.com.naymaisa.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDAO {
    Integer cadastrar(Produto produto) throws SQLException;

    Produto consultar(String codigo) throws SQLException;

    Integer atualizar(Produto produto) throws SQLException;

    Integer excluir(Produto produto) throws SQLException;

    List<Produto> buscarTodos() throws SQLException;
}
