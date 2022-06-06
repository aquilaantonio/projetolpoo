package model;

import java.io.Serializable;
import java.util.List;

import sun.security.util.Password;

public class Usuario implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String login;
private String senha;
private List<Despesas> listaDespesas;
private List<Receita> listaReceitas;

public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getSenha() {
	return senha;
}
public void setSenha(String string) {
	this.senha = string;
}
public List<Despesas> getListaDespesas() {
	return listaDespesas;
}
public void setListaDespesas(List<Despesas> listaDespesas) {
	this.listaDespesas = listaDespesas;
}
public List<Receita> getListaReceitas() {
	return listaReceitas;
}
public void setListaReceitas(List<Receita> listaReceitas) {
	this.listaReceitas = listaReceitas;
}

}
