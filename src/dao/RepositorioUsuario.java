package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.Usuario;

public class RepositorioUsuario implements IRepositorioUsuario {
	private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS  usuario (login VARCHAR(100)  PRIMARY KEY , senha VARCHAR(20) NOT NULL)";
	private final static String INSERT_USUARIO = "INSERT INTO usuario (login,senha) VALUES (?,?)";
	private final static String UPDATE_MERCADORIA = "UPDATE mercadoria SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
	private final static String DELETE_MERCADORIA = "DELETE FROM mercadoria WHERE id = ?";
	private final static String GET_ALL_MERCADORIAS = "SELECT * FROM usuario";
	private final static String GET_USUARIO_BY_NOME = "SELECT * FROM usuario WHERE nome like ?";
	private final static String SELECT_USUARIO = "SELECT LOGIN FROM usuario WHERE login = ? AND  senha = ?";
	
		
	@Override
	public void incluir(Usuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(INSERT_USUARIO);
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
		
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try { conn.rollback(); } catch (Exception sx) {}
			String errorMsg = "Erro ao salvar Mercadoria!";
			
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		}
	}

	@Override
	public void alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean validar(Usuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement( SELECT_USUARIO);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();
			
			if (rs.next()) {				
				return true;
			}
			
			return  false;
		} catch (SQLException e) {
			e.printStackTrace();			
			
		} finally {
			ConnectionManager.closeAll(conn, stmt, rs);
		}
		return false;
	}
	
	public void init() throws PersistenceException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(CREATE_TABLE);
			
		
		} catch (SQLException e) {
			
			throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		}
	}

}
