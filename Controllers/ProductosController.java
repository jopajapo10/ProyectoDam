package Controllers;
import Models.Combo;
import Models.Productos;
import Models.ProductosDao;
import Models.Tables;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.PanelAdmin;

public class ProductosController implements ActionListener, MouseListener {
    private Productos pro;
    private ProductosDao proDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ProductosController(Productos pro, ProductosDao proDao, PanelAdmin views) {
        this.pro = pro;
        this.proDao = proDao;
        this.views = views;
        this.views.btnRegistrarPro.addActionListener(this);
        this.views.btnModificarPro.addActionListener(this);
        this.views.btnNuevoPro.addActionListener(this);
        this.views.jMenuEliminarProductos.addActionListener(this);
        this.views.jMenuReingresarProductos.addActionListener(this);
        this.views.TableProductos.addMouseListener(this);
        this.views.JLabelProductos.addMouseListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnRegistrarPro){
            if(views.txtCodigoPro.getText().equals("") 
                    || views.txtDescripcionPro.getText().equals("")
                    || views.txtPrecioCompraPro.getText().equals("")
                    || views.txtPrecioVentaPro.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son oblgatorios.");
            }else{
                pro.setCodigo(views.txtCodigoPro.getText());
                pro.setDescripcion(views.txtDescripcionPro.getText());
                pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
                pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
                Combo itemP = (Combo) views.cbProveedorPro.getSelectedItem();
                Combo itemC = (Combo) views.cbCategoriaPro.getSelectedItem();
                Combo itemM = (Combo) views.cbMedidaPro.getSelectedItem();
                pro.setId_proveedor(itemP.getId());
                pro.setId_categorias(itemC.getId());
                pro.setId_medidas(itemM.getId());
                if(proDao.registrar(pro)){
                    limpiarTable();
                    listarProductos();
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar producto");
                }
                
            }
        }else if(e.getSource() == views.btnModificarPro){
            if(views.txtCodigoPro.getText().equals("") 
                    || views.txtDescripcionPro.getText().equals("")
                    || views.txtPrecioCompraPro.getText().equals("")
                    || views.txtPrecioVentaPro.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblgatorios.");
            }else{
                pro.setCodigo(views.txtCodigoPro.getText());
                pro.setDescripcion(views.txtDescripcionPro.getText());
                pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
                pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
                Combo itemP = (Combo) views.cbProveedorPro.getSelectedItem();
                Combo itemC = (Combo) views.cbCategoriaPro.getSelectedItem();
                Combo itemM = (Combo) views.cbMedidaPro.getSelectedItem();
                pro.setId_proveedor(itemP.getId());
                pro.setId_categorias(itemC.getId());
                pro.setId_medidas(itemM.getId());
                pro.setId(Integer.parseInt(views.txtIdProductos.getText()));
                if(proDao.modificar(pro)){
                    limpiarTable();
                    listarProductos();
//                    limpiar();
                    JOptionPane.showMessageDialog(null, "Productos modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar producto");
                }
                
            }
        }else if(e.getSource() == views.jMenuEliminarProductos){
            if(views.txtIdProductos.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            }else{
                int id = Integer.parseInt(views.txtIdProductos.getText());
                if (proDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarProductos();
//                    limpiar();
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar producto");
                }
            }
        }else if(e.getSource() == views.jMenuReingresarProductos){
            if(views.txtIdProductos.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            }else{
                int id = Integer.parseInt(views.txtIdProductos.getText());
                if (proDao.accion("Activo", id)){
                    limpiarTable();
                    listarProductos();
//                    limpiar();
                    JOptionPane.showMessageDialog(null, "Producto reingresado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar producto");
                }
            }
        }else{
//            limpiar();
        }
        
    }
    
    public void listarProductos(){
        Tables color = new Tables();
        views.TableProductos.setDefaultRenderer(views.TableProductos.getColumnClass(0), color);
        List<Productos> lista = proDao.ListaProductos(views.txtBuscarProductos.getText());
        modelo = (DefaultTableModel) views.TableProductos.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getDescripcion();
            ob[3] = lista.get(i).getPrecio_venta();
            ob[4] = lista.get(i).getCantidad();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableProductos.setModel(modelo);
        JTableHeader header = views.TableProductos.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.white);
    }
    
    public void limpiarTable(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableProductos){
            int fila = views.TableProductos.rowAtPoint(e.getPoint());
            views.txtIdProductos.setText(views.TableProductos.getValueAt(fila, 0).toString());
            pro = proDao.buscarPro(Integer.parseInt(views.txtIdProductos.getText()));
            views.txtCodigoPro.setText(pro.getCodigo());
            views.txtDescripcionPro.setText(pro.getDescripcion());
            views.txtPrecioCompraPro.setText(""+pro.getPrecio_compra());
            views.txtPrecioVentaPro.setText(""+pro.getPrecio_venta());
            views.cbProveedorPro.setSelectedItem(new Combo(pro.getId_proveedor(), pro.getProveedor()));
            views.cbMedidaPro.setSelectedItem(new Combo(pro.getId_medidas(), pro.getMedida()));
            views.cbCategoriaPro.setSelectedItem(new Combo(pro.getId_categorias(), pro.getCategoria()));
        }else if (e.getSource() == views.JLabelProductos){
            views.jTabbedPane1.setSelectedIndex(0);
            limpiarTable();
            listarProductos();
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
    
}
