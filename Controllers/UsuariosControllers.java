package Controllers;

import Models.Tables;
import Models.UsuarioDao;
import Models.Usuarios;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.PanelAdmin;

/**
 *
 * @author josepa
 */
public class UsuariosControllers implements ActionListener, MouseListener, KeyListener{
    
    private Usuarios us;
    private UsuarioDao usDao;
    private PanelAdmin views;

    DefaultTableModel modelo = new DefaultTableModel();
    public UsuariosControllers(Usuarios us, UsuarioDao usDao, PanelAdmin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnRegistrarUsuarios.addActionListener(this);
        this.views.btnModificarUsuarios.addActionListener(this);
        this.views.jMenuItemEliminarUsuarios.addActionListener(this);
        this.views.jMenuItemReingresarUsuarios.addActionListener(this);
        this.views.btnNuevoUsuarios.addActionListener(this);
        this.views.txtBuscarUsuario.addKeyListener(this);
        this.views.TableUsuarios.addMouseListener(this);
        this.views.JLabelUsuarios.addMouseListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnRegistrarUsuarios){
            if(views.txtUsuarioUsuarios.getText().equals("") 
                    || views.txtNombreUsuarios.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son oblgatorios.");
            }else{
                us.setUsuario(views.txtUsuarioUsuarios.getText());
                us.setNombre(views.txtNombreUsuarios.getText());
                us.setClave(String.valueOf(views.txtContraseñaUsuarios.getPassword()));
                us.setCaja(views.cbCajaUsuarios.getSelectedItem().toString());
                us.setRol(views.cbRolUsuarios.getSelectedItem().toString());
                if(usDao.registrar(us)){
                    limpiarUsuarios();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar usuario");
                }
                
            }
        }else if(e.getSource() == views.btnModificarUsuarios){
            if(views.txtUsuarioUsuarios.getText().equals("") 
                    || views.txtNombreUsuarios.getText().equals("")
                    || String.valueOf(views.txtContraseñaUsuarios.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblgatorios.");
            }else{
                us.setUsuario(views.txtUsuarioUsuarios.getText());
                us.setNombre(views.txtNombreUsuarios.getText());
                us.setCaja(views.cbCajaUsuarios.getSelectedItem().toString());
                us.setRol(views.cbRolUsuarios.getSelectedItem().toString());
                us.setId(Integer.parseInt(views.txtIdUsuarios.getText()));
                if(usDao.modificar(us)){
                    limpiarUsuarios();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar usuario");
                }
                
            }
        }else if(e.getSource() == views.jMenuItemEliminarUsuarios){
            if(views.txtIdUsuarios.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            }else{
                int id = Integer.parseInt(views.txtIdUsuarios.getText());
                if (usDao.accion("Inactivo", id)){
                    limpiarUsuarios();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                }
            }
        }else if(e.getSource() == views.jMenuItemReingresarUsuarios){
            if(views.txtIdUsuarios.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            }else{
                int id = Integer.parseInt(views.txtIdUsuarios.getText());
                if (usDao.accion("Activo", id)){
                    limpiarUsuarios();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario reingresado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar usuario");
                }
            }
        }else{
            limpiar();
        }
    }
    
    public void listarUsuarios(){
        Tables color = new Tables();
        views.TableUsuarios.setDefaultRenderer(views.TableUsuarios.getColumnClass(0), color);
        List<Usuarios> lista = usDao.ListaUsuarios(views.txtBuscarUsuario.getText());
        modelo = (DefaultTableModel) views.TableUsuarios.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getUsuario();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getCaja();
            ob[4] = lista.get(i).getRol();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableUsuarios.setModel(modelo);
        JTableHeader header = views.TableUsuarios.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.white);
    }
    
    public void limpiarUsuarios(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiarTable(){
        for (int i =0; i< modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableUsuarios){
            int fila = views.TableUsuarios.rowAtPoint(e.getPoint());
            views.txtIdUsuarios.setText(views.TableUsuarios.getValueAt(fila, 0).toString());
            views.txtUsuarioUsuarios.setText(views.TableUsuarios.getValueAt(fila, 1).toString());
            views.txtNombreUsuarios.setText(views.TableUsuarios.getValueAt(fila, 2).toString());
            views.cbCajaUsuarios.setSelectedItem(views.TableUsuarios.getValueAt(fila, 4).toString());
            views.cbRolUsuarios.setSelectedItem(views.TableUsuarios.getValueAt(fila, 5).toString());
                    views.txtContraseñaUsuarios.setEnabled(false);
        }else if (e.getSource() == views.JLabelUsuarios){
            views.jTabbedPane1.setSelectedIndex(3);
            limpiarTable();
            listarUsuarios();
        }
    }

    @Override
    public void mousePressed(MouseEvent em) {
    }

    @Override
    public void mouseReleased(MouseEvent em) {
    }

    @Override
    public void mouseEntered(MouseEvent em) {
    }

    @Override
    public void mouseExited(MouseEvent em) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == views.txtBuscarUsuario){
            limpiarUsuarios();
            listarUsuarios();
        }
    }
    
    private void limpiar(){
        views.txtIdUsuarios.setText("");
        views.txtUsuarioUsuarios.setText("");
        views.txtNombreUsuarios.setText("");
        views.txtContraseñaUsuarios.setText("");
    }
}
