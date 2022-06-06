package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Despesas;
import model.Periodos;
import model.Tipo;

public class RepositorioDespesa implements IRepositorioDespesa {

	private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS  despesa (nome VARCHAR(100) , data DATE , tipo VARCHAR(100) , valor  double, numero_parcela integer , periodo VARCHAR(100))";
	private final static String INSERT_DESPESA = "INSERT INTO despesa (nome,data,tipo,valor,numero_parcela,periodo) VALUES (?,?,?,?,?,?)";	
	private final static String DELETE_DESPESA = "DELETE FROM despesa WHERE nome = ?";
	private final static String SELECT_PERIODO_DESPESA = "SELECT * FROM despesa WHERE periodo = ?";
	private final static String SELECT_TIPO_DESPESA = "SELECT * FROM despesa WHERE tipo = ?";
	private final static String SELECT_VALOR_DESPESA = "SELECT * FROM despesa WHERE valor = ?";
	private final static String SELECT_DESPESA = "SELECT * FROM despesa ";


	@Override
	public void incluir(Despesas despesa) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(INSERT_DESPESA);
			stmt.setString(1,despesa.getNome());
			stmt.setDate(2, convertFromJAVADateToSQLDate(despesa.getData()));
			stmt.setString(3, despesa.getTipo().toString());
			stmt.setDouble(4, despesa.getValor());
			stmt.setInt(5, despesa.getNumeroParcela());
			stmt.setString(6, despesa.getPeriodo().toString());
			
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try { conn.rollback(); } catch (Exception sx) {}
			String errorMsg = "Erro ao salvar DESPESA!";
			
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		}
	}

	@Override
	public void alterar(Despesas despesa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Despesas buscar(Despesas despesa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Despesas despesa) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(DELETE_DESPESA);
			stmt.setString(1,despesa.getNome());
						
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try { conn.rollback(); } catch (Exception sx) {}
			String errorMsg = "Erro ao DELETAR DESPESA";
			
			throw new PersistenceException(errorMsg, e);
		} finally {
			ConnectionManager.closeAll(conn, stmt);
		}
		
	}
	
	@Override
	public List<Despesas> buscarTodos(String tipoConsulta, Object valor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Despesas> listaDespesas = null;
		try {
			conn = ConnectionManager.getConnection();
			if (tipoConsulta.equals("PERIODO")) {
				stmt = conn.prepareStatement( SELECT_PERIODO_DESPESA);
				stmt.setString(1, valor.toString());
			} else if (tipoConsulta.equals("TIPO")) {
				stmt = conn.prepareStatement( SELECT_TIPO_DESPESA);
				stmt.setString(1, valor.toString());
			} else if (tipoConsulta.equals("VALOR")) {						
				stmt = conn.prepareStatement( SELECT_VALOR_DESPESA);
				stmt.setDouble(1, Double.parseDouble(valor.toString()));
			}else {
				stmt = conn.prepareStatement( SELECT_DESPESA);
			}
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (listaDespesas == null) {
					listaDespesas = new ArrayList<Despesas>();
				}				
				Despesas d = new Despesas();				
				d.setNome(rs.getString(1));
				d.setData(rs.getDate(2));
				d.setTipo(rs.getString(3));
				d.setValor(rs.getDouble(4));
				d.setNumeroParcela(rs.getInt(5));
				d.setPeriodo(rs.getString(6));
				listaDespesas.add(d);
				
			}
			
			return  listaDespesas;
		} catch (SQLException e) {
			String errorMsg = "Erro ao consultar as Despesas!";
			
			throw new PersistenceException(errorMsg, e);			
			
		} finally {
			ConnectionManager.closeAll(conn, stmt, rs);
		}
		
		
	}

	@Override
	public void init() {
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
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime() );
		}
		return javaDate;
	}
	
	public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date sqlDate) {
		java.sql.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new java.sql.Date(sqlDate.getTime());
		}
		return javaDate;
	}


}
