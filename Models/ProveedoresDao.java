
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProveedoresDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List ListaProveedores(String valor) {
        List<Proveedores> listaProveedores = new ArrayList();
        String sql = "SELECT * FROM proveedor ORDER BY estado ASC";
        String buscar = "SELECT * FROM proveedor WHERE ruc LIKE '%"+valor+"%'";
        try{
            con = cn.getConexion ();
            if ("".equalsIgnoreCase(valor)){
                ps = con.prepareStatement (sql);
                rs = ps.executeQuery ();
            }else{
                ps = con.prepareStatement (buscar);
            rs = ps.executeQuery ();
            }
            
            while(rs.next()){
                Proveedores pv = new Proveedores();
                pv.setId(rs.getInt("id"));
                pv.setRuc(rs.getString("ruc"));
                pv.setNombre(rs.getString("proveedor"));
                pv.setTelefono(rs.getString("telefono"));
                pv.setDireccion(rs.getString("direccion"));
                pv.setEstado(rs.getString("estado"));

                listaProveedores.add(pv);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProveedores;
    }
    
    public boolean registrar(Proveedores pv){
        String sql = "INSERT INTO proveedor (ruc, proveedor, telefono, direccion) VALUES (?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pv.getRuc());
            ps.setString(2, pv.getNombre());
            ps.setString(3, pv.getTelefono());
            ps.setString(4, pv.getDireccion());
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
     
    public boolean modificar( Proveedores pv){
        String sql = "UPDATE proveedor SET ruc = ?, proveedor = ?, telefono = ?, direccion = ? WHERE id = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pv.getRuc());
            ps.setString(2, pv.getNombre());
            ps.setString(3, pv.getTelefono());
            ps.setString(4, pv.getDireccion());
            ps.setInt(5, pv.getId());
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public boolean accion(String estado, int id){
        String sql = "UPDATE proveedor SET estado = ? WHERE id = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
