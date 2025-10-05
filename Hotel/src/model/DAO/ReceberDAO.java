package model.DAO;
import java.util.List;
import model.Receber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Receber;

public class ReceberDAO implements InterfaceDAO<Receber>{

    @Override
    public void Create(Receber objeto) {
                
        String sqlInstrucao = "Insert into receber ("             
                + " data_hora_cadastro, "
                + " valor_original, "
                + " desconto, "
                + " acrescimo, "
                + " valor_pago, "
                + " obs, "
                + " status) "
                + " Values (?,?,?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDataHoraCadastro());
            pstm.setFloat(2, objeto.getValorOriginal());
            pstm.setFloat(3, objeto.getDesconto());
            pstm.setFloat(4, objeto.getAcrescimo());
            pstm.setFloat(5, objeto.getValorPago());
            pstm.setString(6, objeto.getObs());
            pstm.setString(7, String.valueOf(objeto.getStatus()));
            
            pstm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
    }

    @Override
    public Receber Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_cadastro, "
                + " valor_original, "
                + " desconto, "
                + " acrescimo, "
                + " valor_pago, "
                + " obs, "
                + " status "
                + " From movimentoCaixa "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Receber receber = new Receber();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                receber.setId(rst.getInt("id"));
                receber.setDataHoraCadastro(rst.getString("data hora cadastro"));
                receber.setValorOriginal(rst.getFloat("valor original"));
                receber.setDesconto(rst.getFloat("desconto"));
                receber.setAcrescimo(rst.getFloat("acrescimo"));
                receber.setValorPago(rst.getFloat("valor pago"));
                receber.setObs(rst.getString("obs"));
                receber.setStatus(rst.getString("status").charAt(0));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return receber;
        }
        
    }

    @Override
    public List<Receber> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_cadastro, "
                + " valor_original, "
                + " desconto, "
                + " acrescimo, "
                + " valor_pago, "
                + " obs, "
                + " status "
                + " From receber "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Receber> listaRecebers = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                Receber receber = new Receber();
                receber.setId(rst.getInt("id"));
                receber.setDataHoraCadastro(rst.getString("data hora cadastro"));
                receber.setValorOriginal(rst.getFloat("valor original"));
                receber.setDesconto(rst.getFloat("desconto"));
                receber.setAcrescimo(rst.getFloat("acrescimo"));
                receber.setValorPago(rst.getFloat("valor pago"));
                receber.setObs(rst.getString("obs"));
                receber.setStatus(rst.getString("status").charAt(0));
                listaRecebers.add(receber);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaRecebers;
        }
    }

    @Override
    public void Update(Receber objeto) {
        
         String sqlInstrucao = "Update receber  "
                + " set "
                + " data_hora_cadastro =?, "
                + " valor_original =?, "
                + " desconto =?, "
                + " acrescimo =?, "
                + " valor_pago =?, "
                + " obs =?, "
                + " status =? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getDataHoraCadastro());
        pstm.setFloat(2, objeto.getValorOriginal());
        pstm.setFloat(3, objeto.getDesconto());
        pstm.setFloat(4, objeto.getAcrescimo());
        pstm.setFloat(5, objeto.getValorPago());
        pstm.setString(6, String.valueOf(objeto.getStatus()));
        pstm.setInt(7, objeto.getId());
        
        pstm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
        
    }

    @Override
    public void Delete(Receber objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
