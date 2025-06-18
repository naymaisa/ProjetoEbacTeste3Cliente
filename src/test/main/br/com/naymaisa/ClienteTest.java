package test.main.br.com.naymaisa;

import main.br.com.naymaisa.dao.ClienteDAO;
import main.br.com.naymaisa.dao.IClienteDAO;
import main.br.com.naymaisa.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private IClienteDAO dao;

    @BeforeEach
    public void setup() throws Exception {
        dao = new ClienteDAO();
        Cliente existente = dao.consultar("01");
        if (existente != null) {
            dao.excluir(existente);
        }
    }

    @Test
    public void cadastrarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Nayara Maisa");

        Integer qtd = dao.cadastrar(cliente);
        assertEquals(1, qtd);

        Cliente clienteBD = dao.consultar("01");
        assertNotNull(clienteBD);
        assertEquals("01", clienteBD.getCodigo());

        Integer qtdDel = dao.excluir(clienteBD);
        assertEquals(1, qtdDel);
    }

    @Test
    public void buscarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Nayara Maisa");

        Integer countCad = dao.cadastrar(cliente);
        assertEquals(1, countCad);

        Cliente clienteBD = dao.consultar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = dao.excluir(clienteBD);
        assertEquals(1, countDel);
    }

    @Test
    public void excluirTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Nayara Maisa");

        Integer countCad = dao.cadastrar(cliente);
        assertEquals(1, countCad);

        Cliente clienteBD = dao.consultar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = dao.excluir(clienteBD);
        assertEquals(1, countDel);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("10");
        cliente1.setNome("Nayara Maisa");
        assertEquals(1, dao.cadastrar(cliente1));

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("20");
        cliente2.setNome("Teste");
        assertEquals(1, dao.cadastrar(cliente2));

        List<Cliente> list = dao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Cliente cli : list) {
            dao.excluir(cli);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = dao.buscarTodos();
        assertEquals(0, list.size());
    }

    @Test
    public void atualizarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Nayara Maisa");
        assertEquals(1, dao.cadastrar(cliente));

        Cliente clienteBD = dao.consultar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCodigo("20");
        clienteBD.setNome("Outro nome");
        assertEquals(1, dao.atualizar(clienteBD));

        Cliente clienteBD1 = dao.consultar("10");
        assertNull(clienteBD1);

        Cliente clienteBD2 = dao.consultar("20");
        assertNotNull(clienteBD2);
        assertEquals(clienteBD.getId(), clienteBD2.getId());
        assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());

        for (Cliente cli : dao.buscarTodos()) {
            dao.excluir(cli);
        }
    }
}
