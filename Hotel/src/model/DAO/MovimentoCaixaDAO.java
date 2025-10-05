package model.DAO;
import java.util.List;
import model.MovimentoCaixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovimentoCaixaDAO implements InterfaceDAO<MovimentoCaixa>{

    @Override
    public void Create(MovimentoCaixa objeto) {
                
        String sqlInstrucao = "Insert into movimentoCaixa ("             
                + " data_hora_movimento, "
                + " valor, "
                + " descricao, "
                + " obs, "
                + " status) "
                + " Values (?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDataHoraMovimento());
            pstm.setFloat(2, objeto.getValor());
            pstm.setString(3, objeto.getDescricao());
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            
            pstm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
    }

    @Override
    public MovimentoCaixa Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_movimento, "
                + " valor, "
                + " descricao, "
                + " obs, "
                + " status "
                + " From movimentoCaixa "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        MovimentoCaixa movimentoCaixa = new MovimentoCaixa();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                movimentoCaixa.setId(rst.getInt("id"));
                movimentoCaixa.setDataHoraMovimento(rst.getString("data hora movimento"));
                movimentoCaixa.setValor(rst.getFloat("valor"));
                movimentoCaixa.setDescricao(rst.getString("descricao"));
                movimentoCaixa.setObs(rst.getString("obs"));
                movimentoCaixa.setStatus(rst.getString("status").charAt(0));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return movimentoCaixa;
        }
        
    }

    @Override
    public List<MovimentoCaixa> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_movimento, "
                + " valor, "
                + " descricao, "
                + " obs, "
                + " status "
                + " From movimentoCaixa "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<MovimentoCaixa> listaMovimentoCaixas = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                MovimentoCaixa movimentoCaixa = new MovimentoCaixa();
                movimentoCaixa.setId(rst.getInt("id"));
                movimentoCaixa.setDataHoraMovimento(rst.getString("data hora movimento"));
                movimentoCaixa.setValor(rst.getFloat("valor"));
                movimentoCaixa.setDescricao(rst.getString("descricao"));
                movimentoCaixa.setObs(rst.getString("obs"));
                movimentoCaixa.setStatus(rst.getString("status").charAt(0));
                listaMovimentoCaixas.add(movimentoCaixa);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaMovimentoCaixas;
        }
    }

    @Override
    public void Update(MovimentoCaixa objeto) {
        
         String sqlInstrucao = "Update movimentoCaixa  "
                + " set "
                + " data_hora_movimento =?, "
                + " valor =?, "
                + " descricao =?, "
                + " obs =?, "
                + " status =? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getDataHoraMovimento());
        pstm.setFloat(2, objeto.getValor());
        pstm.setString(3, objeto.getDescricao());
        pstm.setString(4, objeto.getObs());
        pstm.setString(5, String.valueOf(objeto.getStatus()));
        pstm.setInt(6, objeto.getId());
        
        pstm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
        
    }

    @Override
    public void Delete(MovimentoCaixa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
