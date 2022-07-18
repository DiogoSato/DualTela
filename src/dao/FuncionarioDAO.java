
package dao;

import dualtela.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;

/**
 *
 * @author Corsaryo
 * Classe DAO vai receber da classe Controller os dados e envia para o BD
 */
public class FuncionarioDAO {
    private static Connection c;
    
    public FuncionarioDAO(){
        FuncionarioDAO.c = ConnectionFactory.GetConnection();
    }
    
    public static void insereFuncionario(Funcionario fun){
        String sql = "INSERT INTO funcionario(nome,cargo,tipo,salario,telefone,data)"
        + "VALUES (?,?,?,?,?,?);";
        
        try{
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, fun.getNome());
            stmt.setString(2, fun.getCargo());
            stmt.setString(3, fun.getData());
            stmt.setString(4, fun.getTipo());
            stmt.setDouble(5, fun.getSalario());
            stmt.setInt(6, fun.getTelefone());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deletaFuncionario(Funcionario fun){
        String sql = "DELETE FROM funcionario WHERE idFunc=?";
        try{
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, fun.getIdFunc());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public ObservableList <Funcionario> getFuncs(){
        try{
            ObservableList<Funcionario> funcs = FXCollections.observableArrayList();
            PreparedStatement stmt = this.c.prepareStatement("SELECT * FROM funcionario");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setData(rs.getString("data"));
                f.setTipo(rs.getString("tipo"));
                f.setSalario(rs.getDouble("salario"));
                f.setTelefone(rs.getInt("telefone"));
                f.setIdFunc(rs.getInt("idFunc"));
                
                funcs.add(f);
            }
            
    stmt.executeQuery();
    rs.close();
    stmt.close();
    return funcs;
    
    }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    private static void fecharConexao(){
        try{
            c.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}    