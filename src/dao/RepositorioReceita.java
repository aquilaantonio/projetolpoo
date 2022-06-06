package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Receita;

public class RepositorioReceita implements IRepositorioReceita {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS  receita (id INTEGER IDENTITY PRIMARY KEY NOT NULL,nome VARCHAR(100) , dataInicio DATE ,dataFim DATE, tipo VARCHAR(100),periodo VARCHAR(100) , valor  double)";
    private final static String INSERT_RECEITA = "INSERT INTO receita (nome,dataInicio,dataFim,tipo,periodo,valor) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE_RECEITA = "UPDATE receita SET nome = ?, dataInicio = ?, dataFim = ?, tipo = ? ,periodo = ? ,valor = ? WHERE id = ?";
    private final static String DELETE_RECEITA = "DELETE FROM receita WHERE nome = ?";
    private final static String SELECT_RECEITAS = "SELECT * FROM receita ";

    @Override
    public void incluir(Receita receita) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            if (receita.getId() != null) {
                stmt = getStatementUpdate(conn, receita);
            } else {
                stmt = getStatementInsert(conn, receita);
            }

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception sx) {
            }
            String errorMsg = "Erro ao salvar Mercadoria!";

            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }

    }

    @Override
    public void alterar(Receita receita) {
        // TODO Auto-generated method stub

    }

    @Override
    public Receita buscar(Receita receita) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void excluir(Receita receita) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(DELETE_RECEITA);
            stmt.setString(1, receita.getNome());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception sx) {
            }
            String errorMsg = "Erro ao DELETAR Receita";

            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }

    }

    @Override
    public List<Receita> buscarTodos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Receita> listaReceitas = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(SELECT_RECEITAS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                if (listaReceitas == null) {
                    listaReceitas = new ArrayList<Receita>();
                }
                Receita r = new Receita();
                r.setId(rs.getInt(1));
                r.setNome(rs.getString(2));
                r.setDataIncio(rs.getDate(3));
                r.setDataFim(rs.getDate(4));
                r.setTipo(rs.getString(5));
                r.setPeriodo(rs.getString(6));
                r.setValor(rs.getDouble(7));

                listaReceitas.add(r);

            }

            return listaReceitas;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }

        return listaReceitas;
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

    private PreparedStatement getStatementInsert(Connection conn, Receita r) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(INSERT_RECEITA);
        stmt.setString(1, r.getNome());
        stmt.setDate(2, convertFromJAVADateToSQLDate(r.getDataIncio()));
        stmt.setDate(3, convertFromJAVADateToSQLDate(r.getDataFim()));
        stmt.setString(4, r.getTipo().toString());
        stmt.setString(5, r.getPeriodo().toString());
        stmt.setDouble(6, r.getValor());

        return stmt;
    }

    private PreparedStatement getStatementUpdate(Connection conn, Receita r) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(UPDATE_RECEITA);
        stmt.setString(1, r.getNome());
        stmt.setDate(2, convertFromJAVADateToSQLDate(r.getDataIncio()));
        stmt.setDate(3, convertFromJAVADateToSQLDate(r.getDataFim()));
        stmt.setString(4, r.getTipo().toString());
        stmt.setString(5, r.getPeriodo().toString());
        stmt.setDouble(6, r.getValor());
        stmt.setInt(7, r.getId());
        return stmt;
    }

    public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date sqlDate) {
        java.sql.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new java.sql.Date(sqlDate.getTime());
        }
        return javaDate;
    }
}
