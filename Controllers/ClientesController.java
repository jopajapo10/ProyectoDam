
package Controllers;

import Models.Clientes;
import Models.ClientesDao;
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
import views.PanelAdmin;

public class ClientesController implements ActionListener, MouseListener, KeyListener {
    private Clientes cl;
    private ClientesDao clDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ClientesController(Clientes cl, ClientesDao clDao, PanelAdmin views) {
        this.cl = cl;
        this.clDao = clDao;
        this.views = views;
        this.views.btnRegistrarCliente.addActionListener(this);
        this.views.btnModificarCliente.addActionListener(this);
        this.views.btnNuevoCliente.addActionListener(this);
        this.views.TableClientes.addMouseListener(this);
        this.views.jMenuEliminarClientes.addActionListener(this);
        this.views.jMenuReingresarClientes.addActionListener(this);
        this.views.txtBuscarClientes.addKeyListener(this);
        this.views.JLabelClientes.addMouseListener(this);
        listarClientes();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCliente){
            if (views.txtNombreCliente.getText().equals("")
                    || views.txtTelefonoCliente.getText().equals("")
                    || views.txtDireccionCliente.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                cl.setNombre(views.txtNombreCliente.getText());
                cl.setTelefono(views.txtTelefonoCliente.getText());
                cl.setDireccion(views.txtDireccionCliente.getText());
                if (clDao.registrar(cl)){
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Cliente registrado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al registrar.");
                }
            }
        }else if (e.getSource() == views.btnModificarCliente){
            if (views.txtIdClientes.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona una fila.");
            }else{
                if (views.txtNombreCliente.getText().equals("")
                    || views.txtTelefonoCliente.getText().equals("")
                    || views.txtDireccionCliente.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                cl.setNombre(views.txtNombreCliente.getText());
                cl.setTelefono(views.txtTelefonoCliente.getText());
                cl.setDireccion(views.txtDireccionCliente.getText());
                cl.setId(Integer.parseInt(views.txtIdClientes.getText()));
                if (clDao.modificar(cl)){
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Cliente modificado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar.");
                }
            }
            }
        }else if(e.getSource() == views.jMenuEliminarClientes){
            if (views.txtIdClientes.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdClientes.getText());
                if (clDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente.");
                }
            }
        }else if(e.getSource() == views.jMenuReingresarClientes){
            if (views.txtIdClientes.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdClientes.getText());
                if (clDao.accion("Activo", id)){
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente reingresado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar cliente.");
                }
            }
        }else{
            limpiar();
        }
        
        
        
        
    }
    
        public void listarClientes(){
        Tables color = new Tables();
        views.TableClientes.setDefaultRenderer(views.TableClientes.getColumnClass(0), color);
        List<Clientes> lista = clDao.ListaClientes(views.txtBuscarClientes.getText());
        modelo = (DefaultTableModel) views.TableClientes.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getTelefono();
            ob[3] = lista.get(i).getDireccion();
            ob[4] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableClientes.setModel(modelo);
        JTableHeader header = views.TableClientes.getTableHeader();
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
        if (e.getSource() == views.TableClientes){
            int fila = views.TableClientes.rowAtPoint(e.getPoint());
            views.txtIdClientes.setText(views.TableClientes.getValueAt(fila, 0).toString());
            views.txtNombreCliente.setText(views.TableClientes.getValueAt(fila, 1).toString());
            views.txtTelefonoCliente.setText(views.TableClientes.getValueAt(fila, 2).toString());
            views.txtDireccionCliente.setText(views.TableClientes.getValueAt(fila, 4).toString());
        }else if (e.getSource() == views.JLabelClientes){
            views.jTabbedPane1.setSelectedIndex(1);
            limpiarTable();
            listarClientes();
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
        views.txtNombreCliente.setText("");
        views.txtTelefonoCliente.setText("");
        views.txtDireccionCliente.setText("");
        views.txtIdClientes.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarClientes){
            limpiarTable();
            listarClientes();
        }
    }
}