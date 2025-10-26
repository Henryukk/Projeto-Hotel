package model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Caixa;

public class CaixaDAO implements InterfaceDAO<Caixa>{

    @Override
    public void Create(Caixa objeto) {

        String sqlInstrucao = "INSERT INTO caixa ("
                + "valor_de_abertura, "
                + "valor_de_fechamento, "
                + "data_hora_abertura, "
                + "data_hora_fechamento, "
                + "obs, "
                + "status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setFloat(1, objeto.getValorDeAbertura());
            pstm.setFloat(2, objeto.getValorDeFechamento());
            pstm.setString(3, objeto.getDataHoraAbertura());
            pstm.setString(4, objeto.getDataHoraFechamento());
            pstm.setString(5, objeto.getObs());
            pstm.setString(6, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Caixa Retrieve(int id) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "valor_de_abertura, "
                + "valor_de_fechamento, "
                + "data_hora_abertura, "
                + "data_hora_fechamento, "
                + "obs, "
                + "status "
                + "FROM caixa WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Caixa caixa = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                caixa = new Caixa();
                caixa.setId(rst.getInt("id"));
                caixa.setValorDeAbertura(rst.getFloat("valor_de_abertura"));
                caixa.setValorDeFechamento(rst.getFloat("valor_de_fechamento"));
                caixa.setDataHoraAbertura(rst.getString("data_hora_abertura"));
                caixa.setDataHoraFechamento(rst.getString("data_hora_fechamento"));
                caixa.setObs(rst.getString("obs"));
                caixa.setStatus(rst.getString("status").charAt(0));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return caixa;
    }

    @Override
    public List<Caixa> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + "id, "
                + "valor_de_abertura, "
                + "valor_de_fechamento, "
                + "data_hora_abertura, "
                + "data_hora_fechamento, "
                + "obs, "
                + "status "
                + "FROM caixa WHERE " + atributo + " LIKE ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Caixa> listaCaixas = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rst.getInt("id"));
                caixa.setValorDeAbertura(rst.getFloat("valor_de_abertura"));
                caixa.setValorDeFechamento(rst.getFloat("valor_de_fechamento"));
                caixa.setDataHoraAbertura(rst.getString("data_hora_abertura"));
                caixa.setDataHoraFechamento(rst.getString("data_hora_fechamento"));
                caixa.setObs(rst.getString("obs"));
                caixa.setStatus(rst.getString("status").charAt(0));
                listaCaixas.add(caixa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaCaixas;
    }

    @Override
    public void Update(Caixa objeto) {

        String sqlInstrucao = "UPDATE caixa SET "
                + "valor_de_abertura = ?, "
                + "valor_de_fechamento = ?, "
                + "data_hora_abertura = ?, "
                + "data_hora_fechamento = ?, "
                + "obs = ?, "
                + "status = ? "
                + "WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setFloat(1, objeto.getValorDeAbertura());
            pstm.setFloat(2, objeto.getValorDeFechamento());
            pstm.setString(3, objeto.getDataHoraAbertura());
            pstm.setString(4, objeto.getDataHoraFechamento());
            pstm.setString(5, objeto.getObs());
            pstm.setString(6, String.valueOf(objeto.getStatus()));
            pstm.setInt(7, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Caixa objeto) {
        
        String sqlInstrucao = "UPDATE caixa SET status = ? WHERE id = ?";

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "I");
            pstm.setInt(2, objeto.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}