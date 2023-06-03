
package Controllers;

import Models.Combo;
import Models.Medidas;
import Models.MedidasDao;
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

public class MedidasController implements ActionListener, MouseListener, KeyListener {
    private Medidas md;
    private MedidasDao mdDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public MedidasController(Medidas md, MedidasDao mdDao, PanelAdmin views) {
        this.md = md;
        this.mdDao = mdDao;
        this.views = views;
        this.views.btnRegistrarMedida.addActionListener(this);
        this.views.btnModificarMedida.addActionListener(this);
        this.views.btnNuevoMedida.addActionListener(this);
        this.views.TableMedida.addMouseListener(this);
        this.views.jMenuEliminarMedidas.addActionListener(this);
        this.views.jMenuReingresarMedidas.addActionListener(this);
        this.views.txtBuscarMedidas.addKeyListener(this);
        this.views.JLabelMedidas.addMouseListener(this);
        llenarMedidas();
        AutoCompleteDecorator.decorate(views.cbMedidaPro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarMedida){
            if (views.txtNombreMedida.getText().equals("")
                    || views.txtNombreCortoMedida.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                md.setNombre(views.txtNombreMedida.getText());
                md.setNombre_corto(views.txtNombreCortoMedida.getText());
                if (mdDao.registrar(md)){
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Medidas registrado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al registrar.");
                }
            }
        }else if (e.getSource() == views.btnModificarMedida){
            if (views.txtIdMedidas.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona una fila.");
            }else{
                if (views.txtNombreMedida.getText().equals("")
                    || views.txtNombreCortoMedida.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios.");
            }else{
                md.setNombre(views.txtNombreMedida.getText());
                md.setNombre_corto(views.txtNombreCortoMedida.getText());
                md.setId(Integer.parseInt(views.txtIdMedidas.getText()));
                if (mdDao.modificar(md)){
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Medida modificado.");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar.");
                }
            }
            }
        }else if(e.getSource() == views.jMenuEliminarMedidas){
            if (views.txtIdMedidas.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdMedidas.getText());
                if (mdDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medidas eliminado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar medida.");
                }
            }
        }else if(e.getSource() == views.jMenuReingresarMedidas){
            if (views.txtIdMedidas.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecciona la fila.");
            }else{
                int id = Integer.parseInt(views.txtIdMedidas.getText());
                if (mdDao.accion("Activo", id)){
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida reingresado.");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar medidas.");
                }
            }
        }else{
            limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableMedida){
            int fila = views.TableMedida.rowAtPoint(e.getPoint());
            views.txtIdMedidas.setText(views.TableMedida.getValueAt(fila, 0).toString());
            views.txtNombreMedida.setText(views.TableMedida.getValueAt(fila, 1).toString());
            views.txtNombreCortoMedida.setText(views.TableMedida.getValueAt(fila, 2).toString());
        }else if (e.getSource() == views.JLabelMedidas){
            views.jTabbedPane1.setSelectedIndex(5);
            limpiarTable();
            listarMedidas();
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
        if (e.getSource() == views.txtBuscarMedidas){
            limpiarTable();
            listarMedidas();
        }
    }
    
    public void listarMedidas(){
        Tables color = new Tables();
        views.TableMedida.setDefaultRenderer(views.TableMedida.getColumnClass(0), color);
        List<Medidas> lista = mdDao.ListaMedidas(views.txtBuscarMedidas.getText());
        modelo = (DefaultTableModel) views.TableMedida.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getNombre_corto();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableMedida.setModel(modelo);
        JTableHeader header = views.TableMedida.getTableHeader();
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
    
    private void limpiar(){
        views.txtNombreMedida.setText("");
        views.txtNombreCortoMedida.setText("");
        views.txtIdMedidas.setText("");
    }
    
    private void llenarMedidas(){
        List<Medidas> lista = mdDao.ListaMedidas(views.txtBuscarMedidas.getText());
        for (int i = 0; i < lista.size(); i++){
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbMedidaPro.addItem(new Combo(id, nombre));
        }
    }
    
}
