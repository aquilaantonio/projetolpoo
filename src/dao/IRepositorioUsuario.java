package dao;



import model.Usuario;

public interface IRepositorioUsuario {
	public void incluir(Usuario usuario) ; 

	public void alterar(Usuario usuario) ; 
	
	public Boolean validar(Usuario usuario) ;
	
	void init();
	
}
