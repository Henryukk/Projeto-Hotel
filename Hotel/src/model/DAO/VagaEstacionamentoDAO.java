package model.DAO;
import java.util.List;
import model.VagaEstacionamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento>{

  @Override
    public void Create(VagaEstacionamento objeto) {

        String sqlInstrucao = "Insert into vaga_estacionamento("
                + " descricao, "
                + " obs, "
                + " metragem_vaga, "
                + " status) "
                + " values (?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {

            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

     public VagaEstacionamento Retrieve(int id) {
        String sqlInstrucao = "SELECT "
                + " id, "
                + " descricao, "
                + " obs,"
                + " metragem_vaga, "
                + " status "
                + " FROM vaga_estacionamento WHERE id = ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            while (rst.next()) {
                vagaEstacionamento.setId(rst.getInt("id"));
                vagaEstacionamento.setDescricao(rst.getString("descricao"));
                vagaEstacionamento.setObs(rst.getString("obs"));
                vagaEstacionamento.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vagaEstacionamento.setStatus(rst.getString("status").charAt(0));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return vagaEstacionamento;
    }

    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + " id,"
                + " descricao,"
                + " obs, "
                + " metragem_vaga,"
                + " status "
                + "FROM vaga_estacionamento WHERE " + atributo + " LIKE ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<VagaEstacionamento> ListaVagaEstacionamentos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                vagaEstacionamento.setId(rst.getInt("id"));
                vagaEstacionamento.setDescricao(rst.getString("descricao"));
                vagaEstacionamento.setObs(rst.getString("obs"));
                vagaEstacionamento.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vagaEstacionamento.setStatus(rst.getString("status").charAt(0));
                

                ListaVagaEstacionamentos.add(vagaEstacionamento);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return ListaVagaEstacionamentos;
    }

    @Override
    public void Update(VagaEstacionamento objeto) {

        String sqlInstrucao = "UPDATE vaga_estacionamento SET "
                + " descricao = ?, "
                + " obs = ?, "
                + " metragem_vaga = ?, "
                + " status = ? "
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            pstm.setInt(5, objeto.getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(VagaEstacionamento objeto) {
        // Implementação opcional
    }
}