/*
 * Copyright (C) 2023 victor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package vista.ventanas;

/**
 *
 * @author victor
 */
public class VentanaHome extends javax.swing.JFrame {

    /**
     * Creates new form VentanaHome
     */
    public VentanaHome() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jButton1 = new javax.swing.JButton();
        jPanelBackground1 = new vista.componentes.jpanelbackground.JPanelBackground();
        jPanelBackground2 = new vista.componentes.jpanelbackground.JPanelBackground();
        jPanelBackground3 = new vista.componentes.jpanelbackground.JPanelBackground();
        mensaje1 = new vista.componentes.etiqueta.Mensaje();
        campoDatos1 = new vista.componentes.campos.CampoDatos();
        campoDatos2 = new vista.componentes.campos.CampoDatos();
        mensaje2 = new vista.componentes.etiqueta.Mensaje();
        mensaje3 = new vista.componentes.etiqueta.Mensaje();
        btnCoumidad = new vista.componentes.boton.Boton();
        btnAmigos = new vista.componentes.boton.Boton();
        btnConversaciones = new vista.componentes.boton.Boton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanelBackground4 = new vista.componentes.jpanelbackground.JPanelBackground();
        jPanelBackground5 = new vista.componentes.jpanelbackground.JPanelBackground();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        mensaje4 = new vista.componentes.etiqueta.Mensaje();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menuItemComunidad = new javax.swing.JMenuItem();
        menuItemAmigos = new javax.swing.JMenuItem();
        menuItemConversaciones = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemCambiarFoto = new javax.swing.JMenuItem();
        menuItemVaciarMural = new javax.swing.JMenuItem();
        menuItemVaciarNotificaciones = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenu();
        menuItemDatos = new javax.swing.JMenuItem();
        menuItemPassword = new javax.swing.JMenuItem();
        menuItemEliminarCuenta = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        btnHomeCerrarSesion = new javax.swing.JMenuItem();
        btnItemSalir = new javax.swing.JMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelBackground1.setImgBackgroundEnabled(true);
        jPanelBackground1.setImgBackgroundIn_Ex(false);
        jPanelBackground1.setImgRutaInterno("/storage/img/bkg03.png");

        jPanelBackground2.setImgBackgroundEnabled(true);
        jPanelBackground2.setImgRutaExterno(new java.io.File("C:\\Program Files\\NetBeans-12.4\\<Not Set>"));
        jPanelBackground2.setImgRutaInterno("/storage/img/user_default.png");

        javax.swing.GroupLayout jPanelBackground2Layout = new javax.swing.GroupLayout(jPanelBackground2);
        jPanelBackground2.setLayout(jPanelBackground2Layout);
        jPanelBackground2Layout.setHorizontalGroup(
            jPanelBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanelBackground2Layout.setVerticalGroup(
            jPanelBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelBackground3.setImgBackgroundEnabled(true);
        jPanelBackground3.setImgBackgroundIn_Ex(false);
        jPanelBackground3.setImgOpacidad(0.3F);
        jPanelBackground3.setImgRutaInterno("/storage/img/bkg04.jpg");

        mensaje1.setText("Nombre (s)");

        campoDatos1.setText("campoDatos1");
        campoDatos1.setEnabled(false);

        campoDatos2.setText("campoDatos2");
        campoDatos2.setEnabled(false);

        mensaje2.setText("Apellido (s)");

        mensaje3.setText("Bio");

        btnCoumidad.setImgButtonType("info");
        btnCoumidad.setTexto("Comunidad");

        btnAmigos.setImgButtonType("danger");
        btnAmigos.setTexto("Amigos");

        btnConversaciones.setImgButtonType("peace");
        btnConversaciones.setTexto("Conversaciones");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Ing. Sistemas de computaciones\nDesarrollador web\nTrabajo en Google");
        jTextArea1.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanelBackground3Layout = new javax.swing.GroupLayout(jPanelBackground3);
        jPanelBackground3.setLayout(jPanelBackground3Layout);
        jPanelBackground3Layout.setHorizontalGroup(
            jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackground3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mensaje2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(campoDatos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCoumidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAmigos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConversaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelBackground3Layout.setVerticalGroup(
            jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackground3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackground3Layout.createSequentialGroup()
                        .addComponent(btnCoumidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConversaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanelBackground3Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelBackground3Layout.createSequentialGroup()
                                .addComponent(campoDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(campoDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );

        jPanelBackground4.setOpaque(false);

        javax.swing.GroupLayout jPanelBackground4Layout = new javax.swing.GroupLayout(jPanelBackground4);
        jPanelBackground4.setLayout(jPanelBackground4Layout);
        jPanelBackground4Layout.setHorizontalGroup(
            jPanelBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        jPanelBackground4Layout.setVerticalGroup(
            jPanelBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        jPanelBackground5.setBackground(new java.awt.Color(51, 51, 51));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin notificaciones..." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setEnabled(false);
        jScrollPane1.setViewportView(jList1);

        mensaje4.setForeground(new java.awt.Color(255, 255, 255));
        mensaje4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje4.setText("Notificaciones");

        javax.swing.GroupLayout jPanelBackground5Layout = new javax.swing.GroupLayout(jPanelBackground5);
        jPanelBackground5.setLayout(jPanelBackground5Layout);
        jPanelBackground5Layout.setHorizontalGroup(
            jPanelBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
            .addGroup(jPanelBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensaje4, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBackground5Layout.setVerticalGroup(
            jPanelBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensaje4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBackground1Layout = new javax.swing.GroupLayout(jPanelBackground1);
        jPanelBackground1.setLayout(jPanelBackground1Layout);
        jPanelBackground1Layout.setHorizontalGroup(
            jPanelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackground1Layout.createSequentialGroup()
                        .addComponent(jPanelBackground5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
                    .addGroup(jPanelBackground1Layout.createSequentialGroup()
                        .addComponent(jPanelBackground2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBackground3, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelBackground1Layout.setVerticalGroup(
            jPanelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBackground3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jPanelBackground2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jPanelBackground5, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu3.setText("Red social");

        menuItemComunidad.setText("Ver comunidad");
        jMenu3.add(menuItemComunidad);

        menuItemAmigos.setText("Ver amigos");
        jMenu3.add(menuItemAmigos);

        menuItemConversaciones.setText("Ver conversaciones");
        jMenu3.add(menuItemConversaciones);

        jMenuItem8.setText("Ver solicitud de amistad");
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Perfil");

        menuItemCambiarFoto.setText("Cambiar foto");
        jMenu2.add(menuItemCambiarFoto);

        menuItemVaciarMural.setText("Vaciar mural");
        jMenu2.add(menuItemVaciarMural);

        menuItemVaciarNotificaciones.setText("Vaciar notificaciones");
        jMenu2.add(menuItemVaciarNotificaciones);

        jMenuBar1.add(jMenu2);

        menuItemSalir.setText("Cuenta");

        menuItemDatos.setText("Cambiar datos");
        menuItemSalir.add(menuItemDatos);

        menuItemPassword.setText("Cambiar contraseña");
        menuItemSalir.add(menuItemPassword);

        menuItemEliminarCuenta.setText("Eliminar cuenta");
        menuItemSalir.add(menuItemEliminarCuenta);
        menuItemSalir.add(jSeparator2);

        btnHomeCerrarSesion.setText("Cerrar sesión");
        menuItemSalir.add(btnHomeCerrarSesion);

        btnItemSalir.setText("Salir");
        menuItemSalir.add(btnItemSalir);

        jMenuBar1.add(menuItemSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public vista.componentes.boton.Boton btnAmigos;
    public vista.componentes.boton.Boton btnConversaciones;
    public vista.componentes.boton.Boton btnCoumidad;
    public javax.swing.JMenuItem btnHomeCerrarSesion;
    public javax.swing.JMenuItem btnItemSalir;
    private vista.componentes.campos.CampoDatos campoDatos1;
    private vista.componentes.campos.CampoDatos campoDatos2;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem8;
    private vista.componentes.jpanelbackground.JPanelBackground jPanelBackground1;
    private vista.componentes.jpanelbackground.JPanelBackground jPanelBackground2;
    private vista.componentes.jpanelbackground.JPanelBackground jPanelBackground3;
    private vista.componentes.jpanelbackground.JPanelBackground jPanelBackground4;
    private vista.componentes.jpanelbackground.JPanelBackground jPanelBackground5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private vista.componentes.etiqueta.Mensaje mensaje1;
    private vista.componentes.etiqueta.Mensaje mensaje2;
    private vista.componentes.etiqueta.Mensaje mensaje3;
    private vista.componentes.etiqueta.Mensaje mensaje4;
    public javax.swing.JMenuItem menuItemAmigos;
    public javax.swing.JMenuItem menuItemCambiarFoto;
    public javax.swing.JMenuItem menuItemComunidad;
    public javax.swing.JMenuItem menuItemConversaciones;
    public javax.swing.JMenuItem menuItemDatos;
    public javax.swing.JMenuItem menuItemEliminarCuenta;
    public javax.swing.JMenuItem menuItemPassword;
    public javax.swing.JMenu menuItemSalir;
    public javax.swing.JMenuItem menuItemVaciarMural;
    public javax.swing.JMenuItem menuItemVaciarNotificaciones;
    // End of variables declaration//GEN-END:variables
}
