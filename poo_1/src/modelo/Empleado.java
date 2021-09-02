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
public class Empleado extends Persona {
    private String codigo;
    private int id;
    Conexion cn;
    public Empleado(){}

    public Empleado(int id,String codigo, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
     public DefaultTableModel leer(){
        
         DefaultTableModel tabla= new DefaultTableModel();
         try{
             String query;
             cn = new Conexion();
             cn.abrir_conexion();
             
             
             query="SELECT id_Empleados as id,codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,id_Puesto FROM empleados;";
             ResultSet consulta =cn.conexionBD.createStatement().executeQuery(query);
              
            String encabezado[] = {"id","Codigo","Nombres","Apellidos","Direccion","Telefono","Nacimiento","id_Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10]; 
             while(consulta.next()){
                 datos[0] = consulta.getString("id"); 
                 datos[1] = consulta.getString("codigo");             
                 datos[2] = consulta.getString("nombres");
                 datos[3] = consulta.getString("apellidos");
                 datos[4] = consulta.getString("direccion");
                 datos[5] = consulta.getString("telefono");
                 datos[6] = consulta.getString("fecha_nacimiento");
                  datos[7] = consulta.getString("id_Puesto");
             
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
            String query="INSERT INTO empleados(codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento)VALUES(?,?,?,?,?,?);"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getCodigo());
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
          String query="UPDATE empleados SET codigo = ?,nombres = ?,apellidos = ?,direccion = ?,telefono = ?,fecha_nacimiento = ? WHERE id_Empleados = ?;"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getCodigo());
            parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
                parametro.setString(4, getDireccion());
                  parametro.setString(5, getTelefono());
                    parametro.setString(6, getFecha_nacimiento());
                       parametro.setInt(7, getId());
                 
                    int executar=parametro.executeUpdate();
            cn.cerrar_conexion();
            if (executar>0){
             JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
                    
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
            query = "delete from empleados WHERE id_Empleados = ?;";
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
    

