package dao;

import java.util.List;

import model.Despesas;


public interface IRepositorioDespesa {
	public void incluir(Despesas despesa) ; 

	public void alterar(Despesas despesa) ; 
	
	public Despesas buscar(Despesas despesa) ;
	
	public void excluir(Despesas despesa) ;
	
	public List<Despesas> buscarTodos(String tipo , Object valor);
	
	void init();
}
