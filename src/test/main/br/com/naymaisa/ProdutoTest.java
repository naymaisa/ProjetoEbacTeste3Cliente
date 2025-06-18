package test.main.br.com.naymaisa;

import main.br.com.naymaisa.dao.IProdutoDAO;
import main.br.com.naymaisa.dao.ProdutoDAO;
import main.br.com.naymaisa.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    private IProdutoDAO dao;

    @BeforeEach
    public void setup() throws Exception {
        dao = new ProdutoDAO();
        Produto existente = dao.consultar("01");
        if (existente != null) {
            dao.excluir(existente);
        }
        Produto existente10 = dao.consultar("10");
        if (existente10 != null) {
            dao.excluir(existente10);
        }
        Produto existente20 = dao.consultar("20");
        if (existente20 != null) {
            dao.excluir(existente20);
        }
    }

    @Test
    public void cadastrarTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto Teste");

        Integer qtd = dao.cadastrar(produto);
        assertEquals(1, qtd);

        Produto produtoBD = dao.consultar("01");
        assertNotNull(produtoBD);
        assertEquals("01", produtoBD.getCodigo());

        Integer qtdDel = dao.excluir(produtoBD);
        assertEquals(1, qtdDel);
    }

    @Test
    public void buscarTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Produto Exemplo");

        Integer countCad = dao.cadastrar(produto);
        assertEquals(1, countCad);

        Produto produtoBD = dao.consultar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        Integer countDel = dao.excluir(produtoBD);
        assertEquals(1, countDel);
    }

    @Test
    public void excluirTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Produto Exemplo");

        Integer countCad = dao.cadastrar(produto);
        assertEquals(1, countCad);

        Produto produtoBD = dao.consultar("10");
        assertNotNull(produtoBD);

        Integer countDel = dao.excluir(produtoBD);
        assertEquals(1, countDel);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        Produto produto1 = new Produto();
        produto1.setCodigo("10");
        produto1.setNome("Produto 1");
        assertEquals(1, dao.cadastrar(produto1));

        Produto produto2 = new Produto();
        produto2.setCodigo("20");
        produto2.setNome("Produto 2");
        assertEquals(1, dao.cadastrar(produto2));

        List<Produto> list = dao.buscarTodos();
        assertNotNull(list);
        assertTrue(list.size() >= 2);

        int countDel = 0;
        for (Produto p : list) {
            dao.excluir(p);
            countDel++;
        }

        List<Produto> listAfterDelete = dao.buscarTodos();
        assertTrue(listAfterDelete.isEmpty() || listAfterDelete.size() == 0);
    }

    @Test
    public void atualizarTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Produto Exemplo");
        assertEquals(1, dao.cadastrar(produto));

        Produto produtoBD = dao.consultar("10");
        assertNotNull(produtoBD);

        produtoBD.setCodigo("20");
        produtoBD.setNome("Produto Atualizado");
        assertEquals(1, dao.atualizar(produtoBD));

        Produto produtoBD1 = dao.consultar("10");
        assertNull(produtoBD1);

        Produto produtoBD2 = dao.consultar("20");
        assertNotNull(produtoBD2);
        assertEquals(produtoBD.getId(), produtoBD2.getId());
        assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
        assertEquals(produtoBD.getNome(), produtoBD2.getNome());

        for (Produto p : dao.buscarTodos()) {
            dao.excluir(p);
        }
    }
}
