
package Controllers;

import Models.UsuarioDao;
import Models.Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import views.PanelAdmin;
import views.login;

public class LoginControllers implements ActionListener {
    //declaramos las variables de las clases creadas anteriormente, añadiendo 
    //una variable usuario, otra consulta de usuario y por ultimo el mismo diseño
    //que usaremos de login, para poder acceder al boton.
    private Usuarios us;
    private UsuarioDao usDao;
    private login views;
    //constructor
    public LoginControllers(Usuarios us, UsuarioDao usDao, login views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnConectar.addActionListener(this);
    }

    @Override
    //aqui definiremos qué ocurrirá cuando pulsemos el boton Conectar
    public void actionPerformed(ActionEvent e) {
        //si la accion viene de btnConector, nuestro boton conectar, va a hacer
        //una comprobacion para saber si alguno de ls campos(usuario y contraseña)
        //estan vacios.
        if(e.getSource() == views.btnConectar){
            if(views.username.getText().equals("") || String.valueOf(views.pass.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Los campos estan vacios...");
            }else{
                //si loscampos no estan vacios, guardaremos en variables los datos
                //introducidos y llamando a la clase UsuarioDao, pasando los datos
                //recogidos por referncia y haciendo la consulta a la bbdd.
                String usuario = views.username.getText();
                String clave = String.valueOf(views.pass.getPassword());
                //llamamos al metodo login de la clase UsuarioDao.
                us = usDao.login(usuario, clave);
                //si se ha realizado correctamente la consulta y podemos obtener el usuario,
                //abrimos nuestra interfaz y la hacemos visible.
                if (us.getUsuario() != null){
                    PanelAdmin admin  = new PanelAdmin();
                    admin.setVisible(true);
                    this.views.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
                }
            }
        }else{
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(pregunta == 0){
                System.exit(0);
            }
        }
    }
    
    
}
