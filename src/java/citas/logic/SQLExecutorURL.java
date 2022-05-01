package citas.logic;

import java.sql.*;

public class SQLExecutorURL {

    private String sqlServerConnectionURL;

    private Connection dbConn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public SQLExecutorURL(String port, String dbName, String user, String pwd){
        sqlServerConnectionURL = "jdbc:sqlserver://localhost:"+port+";databaseName="+dbName+";user="+user+";password="
                +pwd+";encrypt=false";
    }

    public void abreConexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dbConn = DriverManager.getConnection(sqlServerConnectionURL);
        }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet ejecutaSQL(String sql){
        try{
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }
    
    public void ejecutaSQLUpdate(String sql){
        try{
            stmt = dbConn.createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void cierraConexion(){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        if(dbConn != null){
            try{
                dbConn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String args[]){

        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        //SELECT * FROM [dbo].[Medico] AS M INNER JOIN [dbo].[Usuario] AS U ON M.cedula = U.cedula WHERE nombreEspecialidad LIKE 'Cardiologia' AND nombreLocalidad LIKE 'Guadalupe'
        //ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Usuario");
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Medico AS M INNER JOIN Usuario AS U ON M.cedula = U.cedula WHERE nombreEspecialidad LIKE 'Cardiologia' AND nombreLocalidad LIKE 'Guadalupe'");
        try{
            while(rs.next()){
                    System.out.print(rs.getString("nombre")+"\n");
                    //System.out.print(rs.getString("presentacion")+"\n");

                }
            }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
