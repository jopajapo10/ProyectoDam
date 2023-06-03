package Controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.PanelAdmin;

public class ConfigControllers implements MouseListener {
    private PanelAdmin views;

    public ConfigControllers(PanelAdmin views) {
        this.views = views;
        this.views.JLabelCategoria.addMouseListener(this);
        this.views.JLabelClientes.addMouseListener(this);
        this.views.JLabelConfig.addMouseListener(this);
        this.views.JLabelProductos.addMouseListener(this);
        this.views.JLabelNuevaCompra.addMouseListener(this);
        this.views.JLabelNuevaVenta.addMouseListener(this);
        this.views.JLabelUsuarios.addMouseListener(this);
        this.views.JLabelMedidas.addMouseListener(this);
        this.views.JLabelProveedor.addMouseListener(this);

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.JLabelConfig){
            views.jTabbedPane1.setSelectedIndex(9);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //inicio efecto hover menú
       if(e.getSource() == views.JLabelCategoria){
           views.JPanelCategorias.setBackground(new Color(255,51,51));
       }else if (e.getSource() == views.JLabelClientes){
           views.JPanelClientes.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelConfig){
           views.JPanelConfiguracion.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelProductos){
           views.JPanelProductos.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelNuevaCompra){
           views.JPanelNuevaCompra.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelNuevaVenta){
           views.JPanelNuevaVenta.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelUsuarios){
           views.JPanelUsuarios.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelMedidas){
           views.JPanelMedidas.setBackground(new Color(255,51,51));
       }else if(e.getSource() == views.JLabelProveedor){
           views.JPanelProovedor.setBackground(new Color(255,51,51));
       }//fin efeco hover menú
       }
        

    @Override
    public void mouseExited(MouseEvent e) {
        //inicio efecto hover menú
       if(e.getSource() == views.JLabelCategoria){
           views.JPanelCategorias.setBackground(new Color(51,51,51));
       }else if (e.getSource() == views.JLabelClientes){
           views.JPanelClientes.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelConfig){
           views.JPanelConfiguracion.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelProductos){
           views.JPanelProductos.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelNuevaCompra){
           views.JPanelNuevaCompra.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelNuevaVenta){
           views.JPanelNuevaVenta.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelUsuarios){
           views.JPanelUsuarios.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelMedidas){
           views.JPanelMedidas.setBackground(new Color(51,51,51));
       }else if(e.getSource() == views.JLabelProveedor){
           views.JPanelProovedor.setBackground(new Color(51,51,51));
       }//fin efeco hover menú
           
       }
    }
    
