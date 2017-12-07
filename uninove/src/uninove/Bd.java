package uninove;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bd {
    static Connection conexao = null;
    static Statement consulta;
    static ResultSet resultadoDaConsulta;
    static String nomeDaTabela = "aluno";
    static String servidor = "756b402d-1ba1-480b-abaa-a81700a75699.mysql.sequelizer.com";
    static String banco = "db756b402d1ba1480babaaa81700a75699";
    static String usuario = "vrirryonardnhzpk";
    static String senha = "hQj7K7yEZGt2YaTTySg7UCNVgh5nNy8nbMXw4nB2rWXgLPQa4whFVLLMcwYCbmZz";
    static String url = "jdbc:mysql://" + servidor + "/" + banco;
    
    public static void testeDoDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver instalado corretamente.");
        } 
        catch (Exception e) {
            System.out.println("Falha no teste do driver!");
            e.printStackTrace();
        }
    }

    public static void testeDaConexao() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao com o banco realizada com sucesso.");
        } 
        catch (Exception e) {
            System.out.println("Falha durante o teste de conexão!");
            e.printStackTrace();
        } 
        finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    System.out.println("Conexão com o banco encerrada com sucesso.");
                } 
                catch (SQLException e) {
                    System.out.println("Falha no encerramento da conexão com o banco!");
                    e.printStackTrace();
                }
            }
        }
    }

    static void pesquisar() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            consulta = conexao.createStatement();
            resultadoDaConsulta = consulta.executeQuery("SELECT * FROM " + nomeDaTabela);

            while (resultadoDaConsulta.next()) {
                //Aluno a = new Aluno();
                ElementosTabela tvlinha = new ElementosTabela();
                tvlinha.setRa(resultadoDaConsulta.getString("ra"));
                tvlinha.setNome(resultadoDaConsulta.getString("nome"));
                tvlinha.setCurso(resultadoDaConsulta.getString("curso"));
                Tela02_Controller.obl.add(tvlinha);
            }
            
            consulta.close();
            resultadoDaConsulta.close();
        } 
        catch (SQLException e) {
            System.out.println("Falha na consulta ao banco!");
            e.printStackTrace();
        }
    }
    
    static void inserir(String ra, String nome, String curso){
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            consulta = conexao.createStatement();
            consulta.executeUpdate("INSERT INTO " + nomeDaTabela +
                    "(ra, nome, curso)" + " VALUES " + "('" +
                    ra + "','" +
                    nome + "','" +
                    curso  + "')");
            consulta.close();
            System.out.println("Aluno Cadastrado com Sucesso!");
        } 
        catch (SQLException e) {
            System.out.println("Falha ao inserir dados na tabela!");
            e.printStackTrace();
        }
    }
    
    static void atualizar (String nome, String curso, String ra) {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement atualizacao = null;
            String comando = "UPDATE " + nomeDaTabela + " SET nome = ?, curso = ? WHERE ra = ?";
            atualizacao = conexao.prepareStatement(comando);
            atualizacao.setString(1,nome);
            atualizacao.setString(2,curso);
            atualizacao.setString(3,ra);
            atualizacao.executeUpdate();
            conexao.close();
            System.out.println("Dados Alterados com Sucesso!");
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha ao alterar dados na tabela!");
        }
    }

    static void apagar (String ra) {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement delecao = null;
            String comando = "DELETE FROM " + nomeDaTabela + " WHERE ra = ?";
            delecao = conexao.prepareStatement(comando);
            delecao.setString(1,ra);
            delecao.executeUpdate();
            conexao.close();
            System.out.println("Cadastro Excluído com sucesso!");
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha ao alterar dados na tabela!");
        }
    }
}
