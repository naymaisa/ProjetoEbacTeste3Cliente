/**
 * @author nayara.maisa
 */

package main.br.com.naymaisa.dao;

import main.br.com.naymaisa.domain.Cliente;

import java.util.List;

public interface IClienteDAO {

    public Integer cadastrar(Cliente cliente) throws Exception;

    public Integer atualizar(Cliente cliente) throws Exception;

    public Cliente buscar(String codigo) throws Exception;

    public List<Cliente> buscarTodos() throws Exception;

    Cliente consultar(String codigo) throws Exception;

    public Integer excluir(Cliente cliente) throws Exception;
}
