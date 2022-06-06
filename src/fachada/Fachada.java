package fachada;

import java.util.List;

import dao.RepositorioDespesa;
import dao.RepositorioReceita;
import dao.RepositorioUsuario;
import model.Despesas;
import model.Receita;
import model.Usuario;

public class Fachada {

    private static Fachada fachada = null;
    private static RepositorioDespesa repoDespesa = new RepositorioDespesa();
    private static RepositorioReceita repoReceita = new RepositorioReceita();
    private static RepositorioUsuario repoUsuario = new RepositorioUsuario();

    public static Fachada getInstance() {
        if (fachada == null) {

            fachada = new Fachada();

        }
        return fachada;
    }

    public Boolean pesquisaUsuario(Usuario usuario) {
        repoUsuario.init();
        return repoUsuario.validar(usuario);
    }

    public void incluirUsuario(Usuario usuario) {
        repoUsuario.init();
        repoUsuario.incluir(usuario);
    }

    public void saveReceita(Receita receita) {
        repoReceita.incluir(receita);
    }

    public List<Receita> buscarTodasReceitas() {
        return repoReceita.buscarTodos();
    }

    public void deleteReceita(Receita receita) {
        repoReceita.excluir(receita);
    }

    public void saveDespesa(Despesas despesa) {
        repoDespesa.incluir(despesa);
    }

    public List<Despesas> buscarTodasDespesas(String tipoConsulta, String valor) {
        return repoDespesa.buscarTodos(tipoConsulta, valor);
    }

    public void deleteDespesa(Despesas despesa) {
        repoDespesa.excluir(despesa);
    }

}
