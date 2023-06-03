
package views;

import Controllers.LoginControllers;
import Models.Conexion;
import Models.UsuarioDao;
import Models.Usuarios;
import views.perfil;
import java.awt.Color;
import java.sql.*;

public class login extends javax.swing.JFrame {
    int xmouse, ymouse;
    Conexion cn;
    Usuarios us = new Usuarios();
    UsuarioDao usDao = new UsuarioDao();
    public login() {
        initComponents();
        this.setLocationRelativeTo(null);
        LoginControllers users = new LoginControllers(us, usDao, this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        exit = new javax.swing.JPanel();
        exitbutton = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        titulopass = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        titulouser = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        botonentrar = new javax.swing.JPanel();
        entrar = new javax.swing.JLabel();
        empresa = new javax.swing.JLabel();
        minilogo = new javax.swing.JLabel();
        btnConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("background"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exit.setBackground(new java.awt.Color(255, 255, 255));

        exitbutton.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitbutton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitbutton.setText("X");
        exitbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitbuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitbuttonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitLayout = new javax.swing.GroupLayout(exit);
        exit.setLayout(exitLayout);
        exitLayout.setHorizontalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitbutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 754, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 40));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/250px-Simple_cardboard_box.svg.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 250, 170));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo.jpg"))); // NOI18N
        fondo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        fondo.setName("imagen1"); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 250, 470));

        titulo.setFont(new java.awt.Font("Roboto", 1, 27)); // NOI18N
        titulo.setText("INICIAR SESIÓN");
        jPanel1.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 300, 50));

        titulopass.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        titulopass.setText("Contraseña");
        titulopass.setToolTipText("");
        jPanel1.add(titulopass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        username.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(204, 204, 204));
        username.setText("Ingrese su nombre de usuario");
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                usernameMousePressed(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 370, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 370, 10));

        titulouser.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        titulouser.setText("Usuario");
        jPanel1.add(titulouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        pass.setForeground(new java.awt.Color(204, 204, 204));
        pass.setText("********");
        pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passMousePressed(evt);
            }
        });
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 370, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 370, -1));

        botonentrar.setBackground(new java.awt.Color(78, 78, 78));

        entrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        entrar.setForeground(new java.awt.Color(255, 255, 255));
        entrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entrar.setText("CONECTAR");
        entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                entrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                entrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout botonentrarLayout = new javax.swing.GroupLayout(botonentrar);
        botonentrar.setLayout(botonentrarLayout);
        botonentrarLayout.setHorizontalGroup(
            botonentrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonentrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        botonentrarLayout.setVerticalGroup(
            botonentrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonentrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(botonentrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 120, 30));

        empresa.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empresa.setText("BOXCLOUD");
        jPanel1.add(empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 250, -1));

        minilogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/box-solid-24.png"))); // NOI18N
        jPanel1.add(minilogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 40, 40));

        btnConectar.setText("CONECTAR");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xmouse = evt.getX();
        ymouse= evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xmouse, y-ymouse);
    }//GEN-LAST:event_headerMouseDragged

    private void exitbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbuttonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitbuttonMouseClicked

    private void exitbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbuttonMouseEntered
        exit.setBackground(new Color(248,14,55));
        exitbutton.setForeground(Color.white);
    }//GEN-LAST:event_exitbuttonMouseEntered

    private void exitbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbuttonMouseExited
        exit.setBackground(Color.white);
        exitbutton.setForeground(Color.black);
    }//GEN-LAST:event_exitbuttonMouseExited

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked

    }//GEN-LAST:event_usernameMouseClicked

    private void usernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMousePressed
        if(username.getText().equals("Ingrese su nombre de usuario")){
            username.setText("");
            username.setForeground(Color.black);
        }
        if(String.valueOf(pass.getPassword()).isEmpty()){
            pass.setText("********");
            pass.setForeground(Color.gray);
            
        }
    }//GEN-LAST:event_usernameMousePressed

    private void passMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passMousePressed
        if(String.valueOf(pass.getPassword()).equals("********")){
            pass.setText("");
            pass.setForeground(Color.black);
        }
        if(username.getText().equals("")){
            username.setText("Ingrese su nombre de usuario");
            username.setForeground(Color.gray);
        }
    }//GEN-LAST:event_passMousePressed

    private void entrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseEntered
        botonentrar.setBackground(new Color(232,232,232));
        entrar.setForeground(Color.black);
    }//GEN-LAST:event_entrarMouseEntered

    private void entrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseExited
        botonentrar.setBackground(new Color(78,78,78));
        entrar.setForeground(Color.white);
    }//GEN-LAST:event_entrarMouseExited

    private void entrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseClicked
    }//GEN-LAST:event_entrarMouseClicked

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConectarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonentrar;
    public javax.swing.JButton btnConectar;
    private javax.swing.JLabel empresa;
    public javax.swing.JLabel entrar;
    private javax.swing.JPanel exit;
    private javax.swing.JLabel exitbutton;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel minilogo;
    public javax.swing.JPasswordField pass;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulopass;
    private javax.swing.JLabel titulouser;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
