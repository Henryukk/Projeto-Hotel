package model.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import model.Veiculo;
import model.Modelo;
import model.Marca;
import model.Funcionario;
import model.Fornecedor;
import model.Hospede;

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    @Override
    public void Create(Veiculo veiculo) {
        String sqlInstrucao = "INSERT INTO veiculo ("
                + " placa, "
                + " cor, "
                + " modelo_id, "
                + " funcionario_id, "
                + " fornecedor_id, "
                + " hospede_id, "
                + " status"
                + " ) VALUES (?,?,?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, veiculo.getPlaca());
            pstm.setString(2, veiculo.getCor());
            pstm.setInt(3, veiculo.getModelo().getId());

            if (veiculo.getFuncionario() != null) {
                pstm.setInt(4, veiculo.getFuncionario().getId());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER);
            }

            if (veiculo.getFornecedor() != null) {
                pstm.setInt(5, veiculo.getFornecedor().getId());
            } else {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }

            if (veiculo.getHospede() != null) {
                pstm.setInt(6, veiculo.getHospede().getId());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }

            pstm.setString(7, String.valueOf(veiculo.getStatus()));

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Veiculo Retrieve(int id) {
        String sqlInstrucao = "SELECT "
                + " veiculo.id                AS veiculo_id, "
                + " veiculo.placa             AS veiculo_placa, "
                + " veiculo.cor               AS veiculo_cor, "
                + " veiculo.modelo_id         AS veiculo_modelo_id, "
                + " veiculo.funcionario_id    AS veiculo_funcionario_id, "
                + " veiculo.fornecedor_id     AS veiculo_fornecedor_id, "
                + " veiculo.hospede_id        AS veiculo_hospede_id, "
                + " veiculo.status            AS veiculo_status, "
                + " modelo.id                 AS modelo_id, "
                + " modelo.descricao          AS modelo_descricao, "
                + " modelo.status             AS modelo_status, "
                + " modelo.marca_id           AS modelo_marca_id, "
                + " marca.id                  AS marca_id, "
                + " marca.descricao           AS marca_descricao, "
                + " marca.status              AS marca_status "
                + " FROM veiculo "
                + " INNER JOIN modelo ON veiculo.modelo_id = modelo.id "
                + " INNER JOIN marca  ON modelo.marca_id  = marca.id "
                + " WHERE veiculo.id = ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;

        Veiculo veiculo = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));
                marca.setStatus(rst.getString("marca_status").charAt(0));

                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                modelo.setDescricao(rst.getString("modelo_descricao"));
                modelo.setStatus(rst.getString("modelo_status").charAt(0));
                modelo.setMarca(marca);

                veiculo = new Veiculo();
                veiculo.setId(rst.getInt("veiculo_id"));
                veiculo.setPlaca(rst.getString("veiculo_placa"));
                veiculo.setCor(rst.getString("veiculo_cor"));
                veiculo.setModelo(modelo);
                veiculo.setStatus(rst.getString("veiculo_status").charAt(0));

                // IDs relacionais opcionais
                int idFuncionario = rst.getInt("veiculo_funcionario_id");
                if (!rst.wasNull()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(idFuncionario);
                    veiculo.setFuncionario(funcionario);
                }

                int idFornecedor = rst.getInt("veiculo_fornecedor_id");
                if (!rst.wasNull()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(idFornecedor);
                    veiculo.setFornecedor(fornecedor);
                }

                int idHospede = rst.getInt("veiculo_hospede_id");
                if (!rst.wasNull()) {
                    Hospede hospede = new Hospede();
                    hospede.setId(idHospede);
                    veiculo.setHospede(hospede);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return veiculo;
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT "
                + " veiculo.id                AS veiculo_id, "
                + " veiculo.placa             AS veiculo_placa, "
                + " veiculo.cor               AS veiculo_cor, "
                + " veiculo.modelo_id         AS veiculo_modelo_id, "
                + " veiculo.funcionario_id    AS veiculo_funcionario_id, "
                + " veiculo.fornecedor_id     AS veiculo_fornecedor_id, "
                + " veiculo.hospede_id        AS veiculo_hospede_id, "
                + " veiculo.status            AS veiculo_status, "
                + " modelo.id                 AS modelo_id, "
                + " modelo.descricao          AS modelo_descricao, "
                + " modelo.status             AS modelo_status, "
                + " modelo.marca_id           AS modelo_marca_id, "
                + " marca.id                  AS marca_id, "
                + " marca.descricao           AS marca_descricao, "
                + " marca.status              AS marca_status "
                + " FROM veiculo "
                + " INNER JOIN modelo ON veiculo.modelo_id = modelo.id "
                + " INNER JOIN marca  ON modelo.marca_id  = marca.id "
                + " WHERE " + atributo + " LIKE ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;

        List<Veiculo> listaVeiculos = new ArrayList<>();

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

                Veiculo veiculo = new Veiculo();
                veiculo.setId(rst.getInt("veiculo_id"));
                veiculo.setPlaca(rst.getString("veiculo_placa"));
                veiculo.setCor(rst.getString("veiculo_cor"));
                veiculo.setModelo(modelo);
                veiculo.setStatus(rst.getString("veiculo_status").charAt(0));

                // IDs relacionais opcionais
                int idFuncionario = rst.getInt("veiculo_funcionario_id");
                if (!rst.wasNull()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(idFuncionario);
                    veiculo.setFuncionario(funcionario);
                }

                int idFornecedor = rst.getInt("veiculo_fornecedor_id");
                if (!rst.wasNull()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(idFornecedor);
                    veiculo.setFornecedor(fornecedor);
                }

                int idHospede = rst.getInt("veiculo_hospede_id");
                if (!rst.wasNull()) {
                    Hospede hospede = new Hospede();
                    hospede.setId(idHospede);
                    veiculo.setHospede(hospede);
                }

                listaVeiculos.add(veiculo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }

        return listaVeiculos;
    }

    @Override
    public void Update(Veiculo veiculo) {
        String sqlInstrucao = "UPDATE veiculo SET "
                + " placa = ?, "
                + " cor = ?, "
                + " modelo_id = ?, "
                + " funcionario_id = ?, "
                + " fornecedor_id = ?, "
                + " hospede_id = ?, "
                + " status = ? "
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, veiculo.getPlaca());
            pstm.setString(2, veiculo.getCor());
            pstm.setInt(3, veiculo.getModelo().getId());

            if (veiculo.getFuncionario() != null) {
                pstm.setInt(4, veiculo.getFuncionario().getId());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER);
            }

            if (veiculo.getFornecedor() != null) {
                pstm.setInt(5, veiculo.getFornecedor().getId());
            } else {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }

            if (veiculo.getHospede() != null) {
                pstm.setInt(6, veiculo.getHospede().getId());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }

            pstm.setString(7, String.valueOf(veiculo.getStatus()));
            pstm.setInt(8, veiculo.getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Veiculo veiculo) {
        // Implementação opcional (delete físico) ou delete lógico alterando status.
    }
}
