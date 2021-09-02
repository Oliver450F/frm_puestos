/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author olive
 */
public class Cliente extends Persona{
    private String nit;
    private int id;
    Conexion cn;
    public Cliente(){}

    public Cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id=id;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
        public DefaultTableModel leer(){
        
         DefaultTableModel tabla= new DefaultTableModel();
         try{
             String query;
             cn = new Conexion();
             cn.abrir_conexion();
             
             
             query="SELECt id_Clientes as id,Nit,nombres,apellidos,Direccion,Telefono,Fecha_Nacimiento FROM clientes;";
             ResultSet consulta =cn.conexionBD.createStatement().executeQuery(query);
              
            String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[7]; 
             while(consulta.next()){
                 datos[0] = consulta.getString("id");
                 datos[1] = consulta.getString("nit");
                 datos[2] = consulta.getString("nombres");
                 datos[3] = consulta.getString("apellidos");
                 datos[4] = consulta.getString("direccion");
                 datos[5] = consulta.getString("telefono");
                 datos[6] = consulta.getString("fecha_nacimiento");
             
                     tabla.addRow(datos);
             
             }
             
             
               
            
             cn.cerrar_conexion();
             
             
         }catch(Exception ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
         return tabla;
         
         
        }
    public void agregar(){
        
        
        try{
            PreparedStatement parametro;
            String query="INSERT INTO clientes(Nit,nombres,apellidos,Direccion,Telefono,Fecha_Nacimiento)VALUES(?,?,?,?,?,?);"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
                parametro.setString(4, getDireccion());
                  parametro.setString(5, getTelefono());
                    parametro.setString(6, getFecha_nacimiento());
                    
                    int executar=parametro.executeUpdate();
            cn.cerrar_conexion();
            if (executar>0){
             JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
                    
            }
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
       
    }
 public void modificar(){
     
      try{
            PreparedStatement parametro;
            String query="UPDATE clientes SET nit = ?,nombres = ?,apellidos = ?,Direccion = ?,Telefono = ?,Fecha_Nacimiento = ? WHERE id_Clientes = ?;"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
                parametro.setString(4, getDireccion());
                  parametro.setString(5, getTelefono());
                    parametro.setString(6, getFecha_nacimiento());
                    parametro.setInt(7, getId());
             
                    int executar=parametro.executeUpdate();
            cn.cerrar_conexion();
            if (executar>0){
             JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Modificado","mensaje",JOptionPane.INFORMATION_MESSAGE);
                    
            }
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
 }  
 
public void eliminar(){
    
     try{
             PreparedStatement parametro;
        String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "delete from clientes WHERE id_Cliente = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());    
            int executar=parametro.executeUpdate();
            cn.cerrar_conexion();
            if (executar>0){
             JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ " Reegistro Eliminado","mensaje",JOptionPane.INFORMATION_MESSAGE);
                    
            }
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
    
    
}
}