package model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Check;
import model.CheckQuarto;

public class CheckDAO implements InterfaceDAO<Check>{

    @Override
    public void Create(Check objeto) {

        String sqlInstrucao = "INSERT INTO check ("
                + "data_hora_cadastro, "
                + "data_hora_entrada, "
                + "data_hora_saida, "
                + "obs, "
                + "status, "
                + "check_quarto_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDataHoraCadastro());
            pstm.setString(2, objeto.getDataHoraEntrada());
            pstm.setString(3, objeto.getDataHoraSaida());
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setInt(6, objeto.getCheckQuarto().getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Check Retrieve(int id) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "data_hora_cadastro, "
                + "data_hora_entrada, "
                + "data_hora_saida, "
                + "obs, "
                + "status, "
                + "check_quarto_id "
                + "FROM check "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Check check = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                check = new Check();
                check.setId(rst.getInt("id"));
                check.setDataHoraCadastro(rst.getString("data_hora_cadastro"));
                check.setDataHoraEntrada(rst.getString("data_hora_entrada"));
                check.setDataHoraSaida(rst.getString("data_hora_saida"));
                check.setObs(rst.getString("obs"));
                check.setStatus(rst.getString("status").charAt(0));

                CheckQuarto cq = new CheckQuarto();
                cq.setId(rst.getInt("check_quarto_id"));
                check.setCheckQuarto(cq);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return check;
    }

    @Override
    public List<Check> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "data_hora_cadastro, "
                + "data_hora_entrada, "
                + "data_hora_saida, "
                + "obs, "
                + "status, "
                + "check_quarto_id "
                + "FROM check "
                + "WHERE " + atributo + " LIKE ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Check> listaChecks = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                Check check = new Check();
                check.setId(rst.getInt("id"));
                check.setDataHoraCadastro(rst.getString("data_hora_cadastro"));
                check.setDataHoraEntrada(rst.getString("data_hora_entrada"));
                check.setDataHoraSaida(rst.getString("data_hora_saida"));
                check.setObs(rst.getString("obs"));
                check.setStatus(rst.getString("status").charAt(0));

                CheckQuarto cq = new CheckQuarto();
                cq.setId(rst.getInt("check_quarto_id"));
                check.setCheckQuarto(cq);

                listaChecks.add(check);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaChecks;
    }

    @Override
    public void Update(Check objeto) {

        String sqlInstrucao = "UPDATE check SET "
                + "data_hora_cadastro = ?, "
                + "data_hora_entrada = ?, "
                + "data_hora_saida = ?, "
                + "obs = ?, "
                + "status = ?, "
                + "check_quarto_id = ? "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDataHoraCadastro());
            pstm.setString(2, objeto.getDataHoraEntrada());
            pstm.setString(3, objeto.getDataHoraSaida());
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setInt(6, objeto.getCheckQuarto().getId());
            pstm.setInt(7, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Check objeto) {
        String sqlInstrucao = "UPDATE check SET status = ? WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "I"); // Exclusão lógica
            pstm.setInt(2, objeto.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}