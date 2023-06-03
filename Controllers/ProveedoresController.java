
package Controllers;

import Models.Combo;
import Models.Proveedores;
import Models.ProveedoresDao;
import Models.Tables;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import views.PanelAdmin;

public class ProveedoresController implements ActionListener, MouseListener, KeyListener {
    private Proveedores pv;
    private ProveedoresDao pvDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ProveedoresController(Proveedores pv, ProveedoresDao pvDao, PanelAdmin views) {
        this.pv = pv;
        this.pvDao = pvDao;
        this.views = views;
        this.views.btnRegistrarProveedor.addActionListener(this);
        this.views.btnModificarProveedor.addActionListener(this);
        this.views.btnNuevoProveedor.addActionListener(this);
        this.views.TableProveedor.addMouseListener(this);
        this.views.jMenuEliminarProveedores.addActionListener(this);
        this.views.jMenuReingresarProveedores.addActionListener(this);
        this.views.txtBuscarProveedor.addKeyListener(this);
        this.views.JLabelProveedor.addMouseListener(this);
        llenarProveedor();
        AutoCompleteDecorator.decorate(views.cbProveedorPro);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarProveedor){
            if (views.txtRucProveedor.getText().equals("") 
                    || views.txtNombreProveedor.getText().equals("")
                    || views.txtTelefonoProveedor.getText().equals("")
                    || views.txtDireccionProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                pv.setRuc(views.txtRucProveedor.getText());
                pv.setNombre(views.txtNombreProveedor.getText());
                pv.setTelefono(views.txtTelefonoProveedor.getText());
                pv.setDireccion(views.txtDireccionProveedor.getText());
                if (pvDao.registrar(pv)){
                    limpiarTable();
                    listarProveedores();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Proveedor registrado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al registrar.");
                }
            }
        }else if (e.getSource() == views.btnModificarProveedor){
            if (views.txtIdProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona una fila.");
            }else{
                if (views.txtRucProveedor.getText().equals("")
                    || views.txtNombreProveedor.getText().equals("")
                    || views.txtTelefonoProveedor.getText().equals("")
                    || views.txtDireccionProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                pv.setRuc(views.txtRucProveedor.getText());
                pv.setNombre(views.txtNombreProveedor.getText());
                pv.setTelefono(views.txtTelefonoProveedor.getText());
                pv.setDireccion(views.txtDireccionProveedor.getText());
                pv.setId(Integer.parseInt(views.txtIdProveedor.getText()));
                if (pvDao.modificar(pv)){
                    limpiarTable();
                    listarProveedores();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Proveedor modificado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar.");
                }
            }
            }
        }else if(e.getSource() == views.jMenuEliminarProveedores){
            if (views.txtIdProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdProveedor.getText());
                if (pvDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarProveedores();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar proveedor.");
                }
            }
        }else if(e.getSource() == views.jMenuReingresarProveedores){
            if (views.txtIdProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdProveedor.getText());
                if (pvDao.accion("Activo", id)){
                    limpiarTable();
                    listarProveedores();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Proveedor reingresado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar proveedor.");
                }
            }
        }else{
            limpiar();
        }
    }
    
    public void listarProveedores(){
        Tables color = new Tables();
        views.TableProveedor.setDefaultRenderer(views.TableProveedor.getColumnClass(0), color);
        List<Proveedores> lista = pvDao.ListaProveedores(views.txtBuscarProveedor.getText());
        modelo = (DefaultTableModel) views.TableProveedor.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getRuc();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getTelefono();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableProveedor.setModel(modelo);
        JTableHeader header = views.TableProveedor.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.white);
    }
    
    
    public void limpiarTable(){
        for (int i =0; i< modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableProveedor){
            int fila = views.TableProveedor.rowAtPoint(e.getPoint());
            views.txtIdProveedor.setText(views.TableProveedor.getValueAt(fila, 0).toString());
            views.txtRucProveedor.setText(views.TableProveedor.getValueAt(fila, 1).toString());
            views.txtNombreProveedor.setText(views.TableProveedor.getValueAt(fila, 2).toString());
            views.txtTelefonoProveedor.setText(views.TableProveedor.getValueAt(fila, 3).toString());
            views.txtDireccionProveedor.setText(views.TableProveedor.getValueAt(fila, 4).toString());
        }else if (e.getSource() == views.JLabelProveedor){
            views.jTabbedPane1.setSelectedIndex(2);
            limpiarTable();
            listarProveedores();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private void limpiar(){
        views.txtRucProveedor.setText("");
        views.txtNombreProveedor.setText("");
        views.txtTelefonoProveedor.setText("");
        views.txtDireccionProveedor.setText("");
        views.txtIdProveedor.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarProveedor){
            limpiarTable();
            listarProveedores();
        }
    }
    
    private void llenarProveedor(){
        List<Proveedores> lista = pvDao.ListaProveedores(views.txtBuscarProveedor.getText());
        for (int i = 0; i < lista.size(); i++){
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbProveedorPro.addItem(new Combo(id, nombre));
        }
    }
    
}
