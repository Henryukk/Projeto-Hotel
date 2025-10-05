package model.DAO;
import java.util.List;
import model.ProdutoCopa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoCopaDAO implements InterfaceDAO<ProdutoCopa>{

  @Override
    public void Create(ProdutoCopa objeto) {

        String sqlInstrucao = "Insert into produto_copa("
                + " descricao, "
                + " obs, "
                + " valor, "
                + " status) "
                + " values (?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {

            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getValor());
            pstm.setString(4, String.valueOf(objeto.getStatus()));

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

     public ProdutoCopa Retrieve(int id) {
        String sqlInstrucao = "SELECT "
                + " id, "
                + " descricao, "
                + " obs,"
                + " valor, "
                + " status "
                + " FROM produto_copa WHERE id = ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ProdutoCopa produtoCopa = new ProdutoCopa();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            while (rst.next()) {
                produtoCopa.setId(rst.getInt("id"));
                produtoCopa.setDescricao(rst.getString("descricao"));
                produtoCopa.setObs(rst.getString("obs"));
                produtoCopa.setValor(rst.getFloat("metragem_vaga"));
                produtoCopa.setStatus(rst.getString("status").charAt(0));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return produtoCopa;
    }

    @Override
    public List<ProdutoCopa> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + " id,"
                + " descricao,"
                + " obs, "
                + " valor,"
                + " status "
                + "FROM produto_copa WHERE " + atributo + " LIKE ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ProdutoCopa> ListaProdutoCopas = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                ProdutoCopa produtoCopa = new ProdutoCopa();
                produtoCopa.setId(rst.getInt("id"));
                produtoCopa.setDescricao(rst.getString("descricao"));
                produtoCopa.setObs(rst.getString("obs"));
                produtoCopa.setValor(rst.getFloat("metragem_vaga"));
                produtoCopa.setStatus(rst.getString("status").charAt(0));
                

                ListaProdutoCopas.add(produtoCopa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return ListaProdutoCopas;
    }

    @Override
    public void Update(ProdutoCopa objeto) {

        String sqlInstrucao = "UPDATE produto_copa SET "
                + " descricao = ?, "
                + " obs = ?, "
                + " valor = ?, "
                + " status = ? "
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getValor());
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
    public void Delete(ProdutoCopa objeto) {
        // Implementação opcional
    }
}