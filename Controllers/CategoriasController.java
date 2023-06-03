
package Controllers;

import Models.Categorias;
import Models.CategoriasDao;
import Models.Combo;
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

public class CategoriasController implements ActionListener, MouseListener, KeyListener {
    private Categorias ct;
    private CategoriasDao ctDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public CategoriasController(Categorias ct, CategoriasDao ctDao, PanelAdmin views) {
        this.ct = ct;
        this.ctDao = ctDao;
        this.views = views;
        this.views.btnRegistrarCategoria.addActionListener(this);
        this.views.btnModificarCategoria.addActionListener(this);
        this.views.btnNuevoCategoria.addActionListener(this);
        this.views.TableCategoria.addMouseListener(this);
        this.views.jMenuEliminarCategorias.addActionListener(this);
        this.views.jMenuReingresarCategorias.addActionListener(this);
        this.views.txtBuscarCategorias.addKeyListener(this);
        this.views.JLabelCategoria.addMouseListener(this);
        AutoCompleteDecorator.decorate(views.cbCategoriaPro);
        listarCategorias();
        llenarCat();
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCategoria){
            if (views.txtNombreCategoria.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                ct.setNombre(views.txtNombreCategoria.getText());
                if (ctDao.registrar(ct)){
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Cateogia registrada.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al registrar.");
                }
            }
        }else if (e.getSource() == views.btnModificarCategoria){
            if (views.txtIdCategorias.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona una fila.");
            }else{
                if (views.txtNombreCategoria.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                ct.setNombre(views.txtNombreCategoria.getText());
                ct.setId(Integer.parseInt(views.txtIdCategorias.getText()));
                if (ctDao.modificar(ct)){
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Categoria modificado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar.");
                }
            }
            }
        }else if(e.getSource() == views.jMenuEliminarCategorias){
            if (views.txtIdCategorias.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdCategorias.getText());
                if (ctDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria eliminado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar categoria.");
                }
            }
        }else if(e.getSource() == views.jMenuReingresarCategorias){
            if (views.txtIdCategorias.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdCategorias.getText());
                if (ctDao.accion("Activo", id)){
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categorias reingresado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar categoria.");
                }
            }
        }else{
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableCategoria){
            int fila = views.TableCategoria.rowAtPoint(e.getPoint());
            views.txtIdCategorias.setText(views.TableCategoria.getValueAt(fila, 0).toString());
            views.txtNombreCategoria.setText(views.TableCategoria.getValueAt(fila, 1).toString());
        }else if (e.getSource() == views.JLabelCategoria){
            views.jTabbedPane1.setSelectedIndex(4);
            limpiarTable();
            listarCategorias();
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCategorias){
            limpiarTable();
            listarCategorias();
        }
    }
    
    public void listarCategorias(){
        Tables color = new Tables();
        views.TableCategoria.setDefaultRenderer(views.TableCategoria.getColumnClass(0), color);
        List<Categorias> lista = ctDao.ListaCategorias(views.txtBuscarCategorias.getText());
        modelo = (DefaultTableModel) views.TableCategoria.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableClientes.setModel(modelo);
        JTableHeader header = views.TableClientes.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.white);
    }
    
    private void limpiar(){
        views.txtNombreCategoria.setText("");
        views.txtIdCategorias.setText("");
    }
    
    public void limpiarTable(){
        for (int i =0; i< modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }

    private void llenarCat(){
        List<Categorias> lista = ctDao.ListaCategorias(views.txtBuscarCategorias.getText());
        for (int i = 0; i < lista.size(); i++){
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbCategoriaPro.addItem(new Combo(id, nombre));
        }
    }
    
}
