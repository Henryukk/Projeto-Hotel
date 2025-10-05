package model.DAO;
import java.util.List;
import model.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaDAO implements InterfaceDAO<Reserva>{

    @Override
    public void Create(Reserva objeto) {
                
        String sqlInstrucao = "Insert into reserva ("
                + " data_hora_reserva, "
                + " data_prevista_entrada, "
                + " data_prevista_saida, "
                + " obs, "
                + " status) "
                + " Values (?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDataHoraReserva());
            pstm.setString(2, objeto.getDataPrevistaEntrada());
            pstm.setString(3, objeto.getDataPrevistaSaida());
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
    public Reserva Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_reserva, "
                + " data_prevista_entrada, "
                + " data_prevista_saida, "
                + " obs, "
                + " status "
                + " From reserva "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Reserva reserva = new Reserva();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                reserva.setId(rst.getInt("id"));
                reserva.setDataHoraReserva(rst.getString("data hora reserva"));
                reserva.setDataPrevistaEntrada(rst.getString("data prevista entrada"));
                reserva.setDataPrevistaSaida(rst.getString("data prevista saida"));
                reserva.setObs(rst.getString("obs"));
                reserva.setStatus(rst.getString("status").charAt(0));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return reserva;
        }
        
    }

    @Override
    public List<Reserva> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_reserva, "
                + " data_prevista_entrada, "
                + " data_prevista_saida, "
                + " obs, "
                + " status "
                + " From reserva "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Reserva> listaReservas = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                Reserva reserva = new Reserva();
                reserva.setId(rst.getInt("id"));
                reserva.setDataHoraReserva(rst.getString("data hora reserva"));
                reserva.setDataPrevistaEntrada(rst.getString("data prevista entrada"));
                reserva.setDataPrevistaSaida(rst.getString("data prevista saida"));
                reserva.setObs(rst.getString("obs"));
                reserva.setStatus(rst.getString("status").charAt(0));
                listaReservas.add(reserva);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaReservas;
        }
    }

    @Override
    public void Update(Reserva objeto) {
        
         String sqlInstrucao = "Update reserva  "
                + " set "
                + " data_hora_reserva =?, "
                + " data_prevista_entrada =?, "
                + " data_prevista_saida =?, "
                + " obs =?, "
                + " status =? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getDataHoraReserva());
        pstm.setString(2, objeto.getDataPrevistaEntrada());
        pstm.setString(3, objeto.getDataPrevistaSaida());
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
    public void Delete(Reserva objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
