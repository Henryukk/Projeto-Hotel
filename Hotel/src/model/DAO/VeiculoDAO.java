package model.DAO;
import java.util.List;
import model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeiculoDAO implements InterfaceDAO<Veiculo>{

    @Override
    public void Create(Veiculo objeto) {
                
        String sqlInstrucao = "Insert into veiculo ("
                + " placa, "
                + " cor,"
                + " modelo_id,"
                + " funcionario_id,"
                + " fornecedor_id,"
                + " hospede_id "
                + " status) "
                + " Values (?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            
            pstm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
    }

    @Override
    public Veiculo Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " placa, "
                + " cor, "
                + " modelo_id, "
                + " funcionario_id, "
                + " fornecedor_id, "
                + " hospede_id, "
                + " status "
                + " From veiculo "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Veiculo veiculo = new Veiculo();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                veiculo.setModelo(service.ModeloService.Carregar(rst.getInt("modelo_id")));
                veiculo.setHospede(service.HospedeService.Carregar(rst.getInt("hospede_id")));
                veiculo.setFuncionario(service.FuncionarioService.Carregar(rst.getInt("funcionario_id")));
                veiculo.setFornecedor(service.FornecedorService.Carregar(rst.getInt("fornecedor_id")));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return veiculo;
        }
        
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " placa, "
                + " cor,"
                + " modelo_id,"
                + " funcionario_id,"
                + " fornecedor_id,"
                + " hospede_id, "
                + " status "
                + " From veiculo "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Veiculo> listaVeiculos = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                listaVeiculos.add(veiculo);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaVeiculos;
        }
    }

    @Override
    public void Update(Veiculo objeto) {
        
         String sqlInstrucao = "Update veiculo  "
                + " set "
                + " placa =?, "
                + " cor =?,"
                + " modelo_id =?,"
                + " funcionario_id =?"
                + " fornecedor_id =?,"
                + " hospede_id =? "
                + " status =? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getPlaca());
        pstm.setString(2, objeto.getCor());
        pstm.setString(3, String.valueOf(objeto.getStatus()));
        pstm.setInt(4, objeto.getId());
        
        pstm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
        
    }

    @Override
    public void Delete(Veiculo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
