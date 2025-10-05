package model.DAO;
import java.util.List;
import model.OrdemServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrdemServicoDAO implements InterfaceDAO<OrdemServico>{

    @Override
    public void Create(OrdemServico objeto) {
                
        String sqlInstrucao = "Insert into ordemServico ("
                + " data_hora_cadastro, "
                + " data_hora_previsao_inicio, "
                + " data_hora_previsao_termino, "
                + " obs, "
                + " status,"
                + " check_id,"
                + " servico_id,"
                + " quarto_id) "
                + " Values (?,str_to_date(?, '%d/%m/%Y'),str_to_date(?, '%d/%m/%Y'),str_to_date(?, '%d/%m/%Y'),?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDataHoraCadastro());
            pstm.setString(2, objeto.getDataPrevisaoInicio());
            pstm.setString(3, objeto.getDataHoraPrevisaoTermino());
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setInt(6, objeto.getCheckId());
            pstm.setInt(7, objeto.getServicoId());
            pstm.setInt(8, objeto.getQuartoID());
            
            pstm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
    }

    @Override
    public OrdemServico Retrieve(int id) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_cadastro, "
                + " data_hora_previsao_inicio, "
                + " data_hora_previsao_termino, "
                + " obs, "
                + " status, "
                + " check_id, "
                + " servico_id, "
                + " quarto_id "
                + " From ordemServico "
                + " Where id = ? ";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        OrdemServico ordemServico = new OrdemServico();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setInt(1, id);
        rst = pstm.executeQuery();
        
            while (rst.next()) {                
                ordemServico.setId(rst.getInt("id"));
                ordemServico.setDataHoraCadastro(rst.getString("data hora cadastro"));
                ordemServico.setDataPrevisaoInicio(rst.getString("data hora previsao inicio"));
                ordemServico.setDataHoraPrevisaoTermino(rst.getString("data hora previsao termino"));
                ordemServico.setObs(rst.getString("obs"));
                ordemServico.setStatus(rst.getString("status").charAt(0));
                ordemServico.setCheckId(rst.getInt("check_id"));
                ordemServico.setServicoId(rst.getInt("servico_id"));
                ordemServico.setQuartoID(rst.getInt("quarto_id"));
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return ordemServico;
        }
        
    }

    @Override
    public List<OrdemServico> Retrieve(String atributo, String valor) {
        
        String sqlInstrucao = "Select "
                + " id, "
                + " data_hora_cadastro, "
                + " data_hora_previsao_inicio, "
                + " data_hora_previsao_termino, "
                + " obs, "
                + " status, "
                + " check_id, "
                + " servico_id, "
                + " quarto_id "
                + " From ordemServico "
                + " Where " + atributo + " like ?";        

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<OrdemServico> listaOrdemServicos = new ArrayList<>();
        
        try{
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, "%" + valor + "%");
        rst = pstm.executeQuery();
        
            while (rst.next()) {   
                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(rst.getInt("id"));
                ordemServico.setDataHoraCadastro(rst.getString("data hora cadastro"));
                ordemServico.setDataPrevisaoInicio(rst.getString("data hora previsao inicio"));
                ordemServico.setDataHoraPrevisaoTermino(rst.getString("data hora previsao termino"));
                ordemServico.setObs(rst.getString("obs"));
                ordemServico.setStatus(rst.getString("status").charAt(0));
                ordemServico.setCheckId(rst.getInt("check_id"));
                ordemServico.setServicoId(rst.getInt("servico_id"));
                ordemServico.setQuartoID(rst.getInt("quarto_id"));
                listaOrdemServicos.add(ordemServico);
                
            }
        
        } catch(SQLException ex) {

            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaOrdemServicos;
        }
    }

    @Override
    public void Update(OrdemServico objeto) {
        
         String sqlInstrucao = "Update ordemServico  "
                + " set "
                + " data_hora_cadastro = ?, "
                + " data_hora_previsao_inicio = ?, "
                + " data_hora_previsao_termino = ?, "
                + " obs = ?, "
                + " status = ?, "
                + " check_id = ?, "
                + " servico_id = ?, "
                + " quarto_id = ? "
                + " Where id = ? ";
         
        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        
        try {
        pstm = conexao.prepareStatement(sqlInstrucao);
        pstm.setString(1, objeto.getDataHoraCadastro());
        pstm.setString(2, objeto.getDataPrevisaoInicio());
        pstm.setString(3, objeto.getDataHoraPrevisaoTermino());
        pstm.setString(4, objeto.getObs());
        pstm.setString(5, String.valueOf(objeto.getStatus()));
        pstm.setInt(6, objeto.getCheckId());
        pstm.setInt(7, objeto.getServicoId());
        pstm.setInt(8, objeto.getQuartoID());
        pstm.setInt(9, objeto.getId());
        
        pstm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        
        
    }

    @Override
    public void Delete(OrdemServico objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
