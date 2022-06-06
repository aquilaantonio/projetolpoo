package dao;


import java.util.List;

import model.Receita;

public interface IRepositorioReceita {
	public void incluir(Receita receita) ; 

	public void alterar(Receita receita) ; 
	
	public Receita buscar(Receita receita) ;
	
	public void excluir(Receita receita) ;
	
	public List<Receita> buscarTodos() throws Exception;
	void init();
}
