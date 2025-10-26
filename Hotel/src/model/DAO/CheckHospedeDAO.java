package model.DAO;
import java.util.List;
import model.CheckHospede;
import model.Check;
import model.Hospede;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckHospedeDAO implements InterfaceDAO<CheckHospede>{

    @Override
    public void Create(CheckHospede objeto) {

        String sqlInstrucao = "INSERT INTO check_hospede ("
                + "tipo_hospede, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "hospede_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getTipoHospede());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, objeto.getStatus());
            pstm.setInt(4, objeto.getCheck().getId());
            pstm.setInt(5, objeto.getHospede().getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public CheckHospede Retrieve(int id) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "tipo_hospede, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "hospede_id "
                + "FROM check_hospede "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CheckHospede checkHospede = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                checkHospede = new CheckHospede();
                checkHospede.setId(rst.getInt("id"));
                checkHospede.setTipoHospede(rst.getString("tipo_hospede"));
                checkHospede.setObs(rst.getString("obs"));
                checkHospede.setStatus(rst.getString("status"));

                Check check = new Check();
                check.setId(rst.getInt("check_id"));
                checkHospede.setCheck(check);

                Hospede hospede = new Hospede();
                hospede.setId(rst.getInt("hospede_id"));
                checkHospede.setHospede(hospede);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return checkHospede;
    }

    @Override
    public List<CheckHospede> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "tipo_hospede, "
                + "obs, "
                + "status, "
                + "check_id, "
                + "hospede_id "
                + "FROM check_hospede "
                + "WHERE " + atributo + " LIKE ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CheckHospede> listaCheckHospedes = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                CheckHospede ch = new CheckHospede();
                ch.setId(rst.getInt("id"));
                ch.setTipoHospede(rst.getString("tipo_hospede"));
                ch.setObs(rst.getString("obs"));
                ch.setStatus(rst.getString("status"));

                Check check = new Check();
                check.setId(rst.getInt("check_id"));
                ch.setCheck(check);

                Hospede hospede = new Hospede();
                hospede.setId(rst.getInt("hospede_id"));
                ch.setHospede(hospede);

                listaCheckHospedes.add(ch);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaCheckHospedes;
    }

    @Override
    public void Update(CheckHospede objeto) {

        String sqlInstrucao = "UPDATE check_hospede SET "
                + "tipo_hospede = ?, "
                + "obs = ?, "
                + "status = ?, "
                + "check_id = ?, "
                + "hospede_id = ? "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getTipoHospede());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, objeto.getStatus());
            pstm.setInt(4, objeto.getCheck().getId());
            pstm.setInt(5, objeto.getHospede().getId());
            pstm.setInt(6, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(CheckHospede objeto) {
        String sqlInstrucao = "UPDATE check_hospede SET status = ? WHERE id = ?";

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