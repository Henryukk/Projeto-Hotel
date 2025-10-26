package model.DAO;
import java.util.List;
import model.CopaQuarto;
import model.CheckQuarto;           // necessário para a FK
import java.util.ArrayList;        // necessário para a lista
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CopaQuartoDAO implements InterfaceDAO<CopaQuarto>{

   @Override
    public void Create(CopaQuarto objeto) {

        String sqlInstrucao = "INSERT INTO copa_quarto ("
                + "quantidade, "
                + "data_hora_pedido, "
                + "obs, "
                + "status, "
                + "check_quarto_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setFloat(1, objeto.getQuantidade());
            pstm.setString(2, objeto.getDataHoraPedido());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus())); // char -> String
            pstm.setInt(5, objeto.getCheckQuarto().getId());       // FK correta

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CopaQuarto Retrieve(int id) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "quantidade, "
                + "data_hora_pedido, "
                + "obs, "
                + "status, "
                + "check_quarto_id "
                + "FROM copa_quarto "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CopaQuarto copaQuarto = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                copaQuarto = new CopaQuarto();
                copaQuarto.setId(rst.getInt("id"));
                copaQuarto.setQuantidade(rst.getFloat("quantidade"));
                copaQuarto.setDataHoraPedido(rst.getString("data_hora_pedido"));
                copaQuarto.setObs(rst.getString("obs"));
                String st = rst.getString("status");
                copaQuarto.setStatus(st != null && !st.isEmpty() ? st.charAt(0) : '\0');

                CheckQuarto cq = new CheckQuarto();
                cq.setId(rst.getInt("check_quarto_id"));
                copaQuarto.setCheckQuarto(cq);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return copaQuarto;
    }

    @Override
    public List<CopaQuarto> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "quantidade, "
                + "data_hora_pedido, "
                + "obs, "
                + "status, "
                + "check_quarto_id "
                + "FROM copa_quarto "
                + "WHERE " + atributo + " LIKE ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CopaQuarto> listaCopaQuartos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                CopaQuarto ch = new CopaQuarto();
                ch.setId(rst.getInt("id"));
                ch.setQuantidade(rst.getFloat("quantidade"));
                ch.setDataHoraPedido(rst.getString("data_hora_pedido"));
                ch.setObs(rst.getString("obs"));
                String st = rst.getString("status");
                ch.setStatus(st != null && !st.isEmpty() ? st.charAt(0) : '\0');

                CheckQuarto cq = new CheckQuarto();
                cq.setId(rst.getInt("check_quarto_id"));
                ch.setCheckQuarto(cq);

                listaCopaQuartos.add(ch);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaCopaQuartos;
    }

    @Override
    public void Update(CopaQuarto objeto) {

        String sqlInstrucao = "UPDATE copa_quarto SET "
                + "quantidade = ?, "
                + "data_hora_pedido = ?, "
                + "obs = ?, "
                + "status = ?, "
                + "check_quarto_id = ? "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setFloat(1, objeto.getQuantidade());
            pstm.setString(2, objeto.getDataHoraPedido());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setInt(5, objeto.getCheckQuarto().getId());
            pstm.setInt(6, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(CopaQuarto objeto) {
        String sqlInstrucao = "UPDATE copa_quarto SET status = ? WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "I"); // Exclusão lógica (se essa for sua regra)
            pstm.setInt(2, objeto.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}