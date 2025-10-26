package model.DAO;
import java.util.List;
import model.CheckQuarto;
import model.Check;
import model.Quarto;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckQuartoDAO implements InterfaceDAO<CheckQuarto>{

    @Override
    public void Create(CheckQuarto objeto) {

        String sqlInstrucao = "INSERT INTO check_quarto ("
                + "data_hora_inicio, "
                + "data_hora_fim, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "quarto_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDataHoraInicio());
            pstm.setString(2, objeto.getDataHoraFim());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus())); // char -> String
            pstm.setInt(5, objeto.getCheck().getId());
            pstm.setInt(6, objeto.getQuarto().getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CheckQuarto Retrieve(int id) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "data_hora_inicio, "
                + "data_hora_fim, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "quarto_id "
                + "FROM check_quarto "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CheckQuarto checkQuarto = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                checkQuarto = new CheckQuarto();
                checkQuarto.setId(rst.getInt("id"));
                checkQuarto.setDataHoraInicio(rst.getString("data_hora_inicio"));
                checkQuarto.setDataHoraFim(rst.getString("data_hora_fim"));
                checkQuarto.setObs(rst.getString("obs"));
                String st = rst.getString("status");
                checkQuarto.setStatus(st != null && !st.isEmpty() ? st.charAt(0) : '\0');

                Check check = new Check();
                check.setId(rst.getInt("check_id"));
                checkQuarto.setCheck(check);

                Quarto quarto = new Quarto();
                quarto.setId(rst.getInt("quarto_id"));
                checkQuarto.setQuarto(quarto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return checkQuarto;
    }

    @Override
    public List<CheckQuarto> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "data_hora_inicio, "
                + "data_hora_fim, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "quarto_id "
                + "FROM check_quarto "
                + "WHERE " + atributo + " LIKE ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CheckQuarto> listaCheckQuartos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                CheckQuarto ch = new CheckQuarto();
                ch.setId(rst.getInt("id"));
                ch.setDataHoraInicio(rst.getString("data_hora_inicio"));
                ch.setDataHoraFim(rst.getString("data_hora_fim"));
                ch.setObs(rst.getString("obs"));
                String st = rst.getString("status");
                ch.setStatus(st != null && !st.isEmpty() ? st.charAt(0) : '\0');

                Check check = new Check();
                check.setId(rst.getInt("check_id"));
                ch.setCheck(check);

                Quarto quarto = new Quarto();
                quarto.setId(rst.getInt("quarto_id"));
                ch.setQuarto(quarto);

                listaCheckQuartos.add(ch);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaCheckQuartos;
    }

    @Override
    public void Update(CheckQuarto objeto) {

        String sqlInstrucao = "UPDATE check_quarto SET "
                + "data_hora_inicio = ?, "
                + "data_hora_fim = ?, "
                + "obs = ?, "
                + "status = ?, "
                + "check_id = ?, "
                + "quarto_id = ? "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDataHoraInicio());
            pstm.setString(2, objeto.getDataHoraFim());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus())); // char -> String
            pstm.setInt(5, objeto.getCheck().getId());
            pstm.setInt(6, objeto.getQuarto().getId());
            pstm.setInt(7, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(CheckQuarto objeto) {
        String sqlInstrucao = "UPDATE check_quarto SET status = ? WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "I"); // Exclusão lógica (ajuste conforme sua regra)
            pstm.setInt(2, objeto.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}