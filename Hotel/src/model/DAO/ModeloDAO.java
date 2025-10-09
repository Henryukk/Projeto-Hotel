package model.DAO;
import java.util.List;
import model.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Marca;

public class ModeloDAO implements InterfaceDAO<Modelo>{

  @Override
    public void Create(Modelo modelo) {

        String sqlInstrucao = "Insert into modelo("
                + " descricao, "
                + " status,"
                + " marca_id) "
                + " values (?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {

            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, modelo.getDescricao());
            pstm.setString(2, String.valueOf(modelo.getStatus()));
            pstm.setInt(3, modelo.getMarca().getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

public Modelo Retrieve(int id) {
    String sqlInstrucao = "SELECT "
            + " modelo.id as modelo_id, "
            + " modelo.descricao as modelo_descricao, "
            + " modelo.status as modelo_status, "
            + " marca.id as marca_id, "
            + " marca.descricao as marca_descricao, "
            + " marca.status as marca_status "
            + " FROM modelo "
            + " INNER JOIN marca ON modelo.marca_id = marca.id "
            + " WHERE modelo.id = ?";

    Connection conexao = model.DAO.ConnectionFactory.getConnection();
    PreparedStatement pstm = null;
    ResultSet rst = null;
    Modelo modelo = null;

    try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();

        if (rst.next()) {

            Marca marca = new Marca();
            marca.setId(rst.getInt("marca_id"));
            marca.setDescricao(rst.getString("marca_descricao"));
            marca.setStatus(rst.getString("marca_status").charAt(0));

            // Cria o modelo
            modelo = new Modelo();
            modelo.setId(rst.getInt("modelo_id"));
            modelo.setDescricao(rst.getString("modelo_descricao"));
            modelo.setStatus(rst.getString("modelo_status").charAt(0));
            modelo.setMarca(marca);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        ConnectionFactory.closeConnection(conexao, pstm);
    }

    return modelo;
}

    @Override
    public List<Modelo> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + " modelo.id as modelo_id, "
                + " modelo.descricao as modelo_descricao, "
                + " modelo.status as modelo_status,"
                + " marca.id as marca_id,"
                + " marca.descricao as marca_descricao,"
                + " marca.status as marca_status"
                + " from modelo"
                + " inner join marca"
                + " on modelo.marca_id= marca.id"
                + " WHERE " + atributo + " LIKE?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Modelo> ListaModelos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));
                marca.setStatus(rst.getString("marca_status").charAt(0));
                
                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                modelo.setDescricao(rst.getString("modelo_descricao"));
                modelo.setStatus(rst.getString("modelo_status").charAt(0));
                modelo.setMarca(marca);

                ListaModelos.add(modelo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return ListaModelos;
    }

    @Override
    public void Update(Modelo modelo) {

        String sqlInstrucao = "UPDATE modelo SET "
                + " descricao = ?, "
                + " status = ? "
                + " marca_id = ?"
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, modelo.getDescricao());
            pstm.setString(2, String.valueOf(modelo.getStatus()));
            pstm.setInt(3, modelo.getMarca().getId());
            pstm.setInt(4, modelo.getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Modelo modelo) {
        // Implementação opcional
    }
}