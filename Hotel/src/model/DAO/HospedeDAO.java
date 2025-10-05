package model.DAO;

import java.util.List;
import model.Hospede;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HospedeDAO implements InterfaceDAO<Hospede> {

    @Override
    public void Create(Hospede objeto) {

        String sqlInstrucao = "Insert into hospede("
                + " nome, "
                + " fone, "
                + " fone2, "
                + " email, "
                + " cep, "
                + " logradouro, "
                + " bairro, "
                + " cidade, "
                + " complemento, "
                + " data_cadastro, "
                + " cpf, "
                + " rg, "
                + " obs, "
                + " status, "
                + " razao_social, "
                + " cnpj, "
                + " inscricao_estadual, "
                + " contato, "
                + " sexo) "
                + " values (?,?,?,?,?,?,?,?,?,str_to_date(?, '%d/%m/%Y'),?,?,?,?,?,?,?,?,?)";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        try {

            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone1());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setString(10, objeto.getDataCadastro());
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getRazaoSocial());
            pstm.setString(16, objeto.getCnpj());
            pstm.setString(17, objeto.getInscricaoEstadual());
            pstm.setString(18, objeto.getContato());
            pstm.setString(19, objeto.getSexo());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

     public Hospede Retrieve(int id) {
        String sqlInstrucao = "SELECT "
                + " id, "
                + " nome, "
                + " fone, "
                + " fone2, "
                + " email, "
                + " cep, "
                + " logradouro, "
                + " bairro, "
                + " cidade, "
                + " complemento, "
                + " DATE_FORMAT(data_cadastro, '%d/%m/%Y') AS data_cadastro, "
                + " cpf, "
                + " rg, "
                + " obs, "
                + " status, "
                + " razao_social, "
                + " cnpj, "
                + " inscricao_estadual, "
                + " contato,"
                + " sexo "
                + " FROM hospede WHERE id = ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Hospede hospede = new Hospede();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            while (rst.next()) {
                hospede.setId(rst.getInt("id"));
                hospede.setNome(rst.getString("nome"));
                hospede.setFone1(rst.getString("fone"));
                hospede.setFone2(rst.getString("fone2"));
                hospede.setEmail(rst.getString("email"));
                hospede.setCep(rst.getString("cep"));
                hospede.setLogradouro(rst.getString("logradouro"));
                hospede.setBairro(rst.getString("bairro"));
                hospede.setCidade(rst.getString("cidade"));
                hospede.setComplemento(rst.getString("complemento"));
                hospede.setDataCadastro(rst.getString("data_cadastro"));
                hospede.setCpf(rst.getString("cpf"));
                hospede.setRg(rst.getString("rg"));
                hospede.setObs(rst.getString("obs"));
                hospede.setRazaoSocial(rst.getString("razao_social"));
                hospede.setCnpj(rst.getString("cnpj"));
                hospede.setInscricaoEstadual(rst.getString("inscricao_estadual"));
                hospede.setContato(rst.getString("contato"));
                hospede.setSexo(rst.getString("sexo"));
                hospede.setStatus(rst.getString("status").charAt(0));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return hospede;
    }

    @Override
    public List<Hospede> Retrieve(String atributo, String valor) {

        String sqlInstrucao = "SELECT "
                + " id,"
                + " nome,"
                + " fone, "
                + " fone2, "
                + " email, "
                + " cep, "
                + " logradouro, "
                + " bairro, "
                + " cidade, "
                + " complemento, "
                + " DATE_FORMAT(data_cadastro, '%d/%m/%Y') AS data_cadastro, "
                + " cpf, "
                + " rg, "
                + " obs, "
                + " status, "
                + " razao_social, "
                + " cnpj, "
                + " inscricao_estadual, "
                + " contato, "
                + " sexo "
                + "FROM hospede WHERE " + atributo + " LIKE ?";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Hospede> ListaHospedes = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, "%" + valor + "%");
            rst = pstm.executeQuery();

            while (rst.next()) {
                Hospede hospede = new Hospede();
                hospede.setId(rst.getInt("id"));
                hospede.setNome(rst.getString("nome"));
                hospede.setFone1(rst.getString("fone"));
                hospede.setFone2(rst.getString("fone2"));
                hospede.setEmail(rst.getString("email"));
                hospede.setCep(rst.getString("cep"));
                hospede.setLogradouro(rst.getString("logradouro"));
                hospede.setBairro(rst.getString("bairro"));
                hospede.setCidade(rst.getString("cidade"));
                hospede.setComplemento(rst.getString("complemento"));
                hospede.setDataCadastro(rst.getString("data_cadastro"));
                hospede.setCpf(rst.getString("cpf"));
                hospede.setRg(rst.getString("rg"));
                hospede.setObs(rst.getString("obs"));
                hospede.setRazaoSocial(rst.getString("razao_social"));
                hospede.setCnpj(rst.getString("cnpj"));
                hospede.setInscricaoEstadual(rst.getString("inscricao_estadual"));
                hospede.setContato(rst.getString("contato"));
                hospede.setSexo(rst.getString("sexo"));
                hospede.setStatus(rst.getString("status").charAt(0));

                ListaHospedes.add(hospede);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

        return ListaHospedes;
    }

    @Override
    public void Update(Hospede objeto) {

        String sqlInstrucao = "UPDATE hospede SET "
                + " nome = ?, "
                + " fone = ?, "
                + " fone2 = ?, "
                + " email = ?, "
                + " cep = ?, "
                + " logradouro = ?, "
                + " bairro = ?, "
                + " cidade = ?, "
                + " complemento = ?, "
                + " data_cadastro = str_to_date(?, '%d/%m/%Y'), "
                + " cpf = ?, "
                + " rg = ?, "
                + " obs = ?, "
                + " status = ?, "
                + " razao_social = ?, "
                + " cnpj = ?, "
                + " inscricao_estadual = ?, "
                + " contato = ?, "
                + " sexo = ? "
                + " WHERE id = ? ";

        Connection conexao = model.DAO.ConnectionFactory.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone1());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setString(10, objeto.getDataCadastro());
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getRazaoSocial());
            pstm.setString(16, objeto.getCnpj());
            pstm.setString(17, objeto.getInscricaoEstadual());
            pstm.setString(18, objeto.getContato());
            pstm.setString(19, objeto.getSexo());
            pstm.setInt(20, objeto.getId());

            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Hospede objeto) {
        // Implementação opcional
    }
}