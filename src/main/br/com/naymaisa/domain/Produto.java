/**
 * @author nayara.maisa
 */

package main.br.com.naymaisa.domain;

public class Produto {
    private Integer id;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String codigo;
    private String nome;
}
