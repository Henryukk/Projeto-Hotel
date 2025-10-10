package model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Caixa;

public class CaixaDAO implements InterfaceDAO<Caixa>{

    public void Create(Caixa objeto) {

        String sqlInstrucao = "Insert into caixa("
                + " valor_de_abertura,"
                + " valor_de_fechamento,"
                + " data_hora_abertura,"
                + " data_hora_fechamento,"
                + " obs, "
                + " status) "
                + " values (?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {

            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setFloat(1, objeto.getValorDeAbertura());
            pstm.setFloat(2, objeto.getValorDeFechamento());
            pstm.setString(3, objeto.getDataHoraAbertura());
            pstm.setString(4, objeto.getDataHoraFechamento());
            pstm.setString(5, objeto.getObs());
            pstm.setString(2, String.valueOf(objeto.getStatus()));

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
     public Caixa Retrieve(int id) {
        String sqlInstrucao = "SELECT "
                + " id, "
                + " valor_de_abertura,"
                + " valor_de_fechamento,"
                + " data_hora_abertura,"
                + " data_hora_fechamento,"
                + " obs, "
                + " status "
                + " FROM caixa WHERE id = ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Caixa caixa = new Caixa();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            while (rst.next()) {
                caixa.setId(rst.getInt("id"));
                caixa.setValorDeAbertura(rst.getFloat("valor_de_abertura"));
                caixa.setValorDeFechamento(rst.getFloat("valor_de_fechamento"));
                caixa.setDataHoraAbertura(rst.getString("data_hora_abertura"));
                
                caixa.setStatus(rst.getString("status").charAt(0));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return caixa;
    }

    @Override
    public List<Caixa> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + " id,"
                + " descricao,"
                + " status "
                + "FROM caixa WHERE " + atributo + " LIKE ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Caixa> ListaCaixas = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rst.getInt("id"));
                caixa.setStatus(rst.getString("status").charAt(0));
                

                ListaCaixas.add(caixa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return ListaCaixas;
    }

    @Override
    public void Update(Caixa objeto) {

        String sqlInstrucao = "UPDATE caixa SET "
                + " descricao = ?, "
                + " status = ? "
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setInt(3, objeto.getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Caixa objeto) {
        // Implementação opcional
    }
}