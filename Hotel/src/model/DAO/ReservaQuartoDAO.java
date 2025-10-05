package model.DAO;
import java.util.List;
import model.ReservaQuarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaQuartoDAO implements InterfaceDAO<ReservaQuarto>{

    @Override
    public void Create(ReservaQuarto objeto) {
                
        String sqlInstrucao = "Insert into reservaQuarto ("
                + " data_hora_inicio, "
                + " data_hora_fim, "
                + " obs, "
                + " status) "
                + " Values (?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDataHoraInicio());
            pstm.setString(2, objeto.getDataHoraFim());
            pstm.setString(3, objeto.getObs());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            
            pstm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
    }

    @Override
    public ReservaQuarto Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_inicio, "
                + " data_hora_fim, "
                + " obs, "
                + " status "
                + " From reservaQuarto "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ReservaQuarto reservaQuarto = new ReservaQuarto();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                reservaQuarto.setId(rst.getInt("id"));
                reservaQuarto.setDataHoraInicio(rst.getString("data hora inicio"));
                reservaQuarto.setDataHoraFim(rst.getString("data hora fim"));
                reservaQuarto.setObs(rst.getString("obs"));
                reservaQuarto.setStatus(rst.getString("status").charAt(0));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return reservaQuarto;
        }
        
    }

    @Override
    public List<ReservaQuarto> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_inicio, "
                + " data_hora_fim, "
                + " obs, "
                + " status "
                + " From reservaQuarto "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ReservaQuarto> listaReservaQuartos = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                ReservaQuarto reservaQuarto = new ReservaQuarto();
                reservaQuarto.setId(rst.getInt("id"));
                reservaQuarto.setDataHoraInicio(rst.getString("data hora inicio"));
                reservaQuarto.setDataHoraFim(rst.getString("data_hora_fim"));
                reservaQuarto.setObs(rst.getString("obs"));
                reservaQuarto.setStatus(rst.getString("status").charAt(0));
                listaReservaQuartos.add(reservaQuarto);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaReservaQuartos;
        }
    }

    @Override
    public void Update(ReservaQuarto objeto) {
        
         String sqlInstrucao = "Update reservaQuarto  "
                + " set "
                + " data_hora_inicio =?, "
                + " data_hora_fim =?, "
                + " obs =?, "
                + " status =? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getDataHoraInicio());
        pstm.setString(2, objeto.getDataHoraFim());
        pstm.setString(3, objeto.getObs());
        pstm.setString(4, String.valueOf(objeto.getStatus()));
        pstm.setInt(5, objeto.getId());
        
        pstm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
        
    }

    @Override
    public void Delete(ReservaQuarto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
