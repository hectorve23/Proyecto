/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package servix.Login_CRUD.Clientes;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import servix.ConexionBBDD;
import servix.FormatoTablas;
import servix.JFrameServix;
import servix.Login_CRUD.Gerente.CargaCombos;

/**
 *
 * @author DAM2Alu15
 */
public class JDialogInterfazClientes extends javax.swing.JDialog{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName());

    /**
     * Creates new form JDialogInterfazClientes
     */
    //Declaracion de variables
    DefaultTableModel dtm;
    DefaultTableModel dtm2;
    ConexionBBDD nueva;
    Connection conexion;
    int id;
    JFrameServix padre;
    CargaCombos cc;
   
    public JDialogInterfazClientes(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        
        this.id = id;
        this.padre = (JFrameServix) parent;
        
        this.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("Servix");
        
        nueva = new ConexionBBDD();
        conexion=nueva.getConnection();
        
        // Mover el JFrame fuera de la pantalla para que no sea visible aunque se restaure
        padre.setLocation(-10000, -10000);
        padre.setSize(0, 0);
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Sirve para la confirmacion de cerrar la aplicacion
        // Al cerrar el dialog muestra confirmacion para cerrar, cierra tambien el padre
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                    JDialogInterfazClientes.this,
                    "¿Seguro que quieres cerrar la aplicación?",
                    "Confirmar cierre",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    JFrameServix.cerrarYReiniciar(JDialogInterfazClientes.this, padre);
                }
            }
        });
        
        this.dtm = new DefaultTableModel();
        this.dtm2 = new DefaultTableModel();
        jTableReservas.setModel(dtm);
        jTableMenu.setModel(dtm2);
        
        // Desactivar edicion de las tablas
        jTableReservas.setDefaultEditor(Object.class, null);
        jTableMenu.setDefaultEditor(Object.class, null);
        
        cc = new CargaCombos();
        cc.cargaComboRestaurantes(jComboBoxRestaurantes);
        cc.cargaComboRestaurantes(jComboBoxSeleccionMenu);
        jComboBoxSeleccionMenu.addActionListener(e -> cargaTablaMenu());
        
        cargaTablaReservas();
        cargaTablaMenu();
        formatoTabla();
       
        atajosTeclado();
        
    }
    
    private void atajosTeclado(){
        //Ayuda
        if (JFrameServix.hb != null) {
            JFrameServix.hb.enableHelpKey(this.getContentPane(), "ayuda_clientes", JFrameServix.hs);
        }
        // Escape - Cerrar
        getRootPane().registerKeyboardAction(
            e -> jButtonCerrarSesion.doClick(),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        // Ctrl+G - Guardar/Validar
        getRootPane().registerKeyboardAction(
            e -> jButtonValidar.doClick(),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        
        
        // Supr - Eliminar
        getRootPane().registerKeyboardAction(
            e -> jButtonAnularReserva.doClick(),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );

        // Ctrl+E - Editar
        getRootPane().registerKeyboardAction(
            e -> jButtonEditarReserva.doClick(),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelVerReservas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();
        jPanelBotonesDeleteUpdate = new javax.swing.JPanel();
        jButtonAnularReserva = new javax.swing.JButton();
        jButtonEditarReserva = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanelNuevaReserva = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxRestaurantes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jSpinnerHora = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerComensales = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jButtonValidar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMenu = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxSeleccionMenu = new javax.swing.JComboBox<>();
        jButtonCerrarSesion = new javax.swing.JButton();
        jButtonBajaCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1002, 439));

        jLabel2.setText("Hector Valdes");

        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableReservas);

        jButtonAnularReserva.setText("Anular reserva");
        jButtonAnularReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnularReservaActionPerformed(evt);
            }
        });
        jPanelBotonesDeleteUpdate.add(jButtonAnularReserva);

        jButtonEditarReserva.setText("Editar reserva");
        jButtonEditarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarReservaActionPerformed(evt);
            }
        });
        jPanelBotonesDeleteUpdate.add(jButtonEditarReserva);

        javax.swing.GroupLayout jPanelVerReservasLayout = new javax.swing.GroupLayout(jPanelVerReservas);
        jPanelVerReservas.setLayout(jPanelVerReservasLayout);
        jPanelVerReservasLayout.setHorizontalGroup(
            jPanelVerReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVerReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotonesDeleteUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelVerReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelVerReservasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelVerReservasLayout.setVerticalGroup(
            jPanelVerReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVerReservasLayout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addComponent(jPanelBotonesDeleteUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelVerReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelVerReservasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Mis reservas", jPanelVerReservas);

        jPanelNuevaReserva.setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        jLabel7.setFont(new java.awt.Font("Sans Serif Collection", 0, 14)); // NOI18N
        jLabel7.setText("Restaurante");
        jPanelNuevaReserva.add(jLabel7);

        jComboBoxRestaurantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanelNuevaReserva.add(jComboBoxRestaurantes);

        jLabel3.setFont(new java.awt.Font("Sans Serif Collection", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");
        jPanelNuevaReserva.add(jLabel3);

        jDateChooser.setMinSelectableDate(new java.util.Date());
        jPanelNuevaReserva.add(jDateChooser);

        jLabel4.setFont(new java.awt.Font("Sans Serif Collection", 0, 14)); // NOI18N
        jLabel4.setText("Hora");
        jPanelNuevaReserva.add(jLabel4);

        jSpinnerHora.setModel(new javax.swing.SpinnerDateModel());
        jSpinnerHora.setEditor(new javax.swing.JSpinner.DateEditor(jSpinnerHora, "HH:mm"));
        jPanelNuevaReserva.add(jSpinnerHora);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Sans Serif Collection", 0, 14)); // NOI18N
        jLabel5.setText("Numero de comensales");
        jPanelNuevaReserva.add(jLabel5);
        jPanelNuevaReserva.add(jSpinnerComensales);
        jPanelNuevaReserva.add(jLabel1);

        jButtonValidar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonValidar.setText("VALIDAR");
        jButtonValidar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarActionPerformed(evt);
            }
        });
        jPanelNuevaReserva.add(jButtonValidar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNuevaReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNuevaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nueva Reserva", jPanel1);

        jTableMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableMenu);

        jLabel8.setText("Despliega para ver los menus:");

        jComboBoxSeleccionMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxSeleccionMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSeleccionMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Menus", jPanel2);

        jButtonCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCerrarSesion.setText("Cerrar sesion");
        jButtonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });

        jButtonBajaCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonBajaCliente.setText("Eliminar cuenta");
        jButtonBajaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBajaClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCerrarSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBajaCliente))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCerrarSesion)
                    .addComponent(jButtonBajaCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnularReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnularReservaActionPerformed
        // TODO add your handling code here:

        if(jTableReservas.getSelectedRowCount()==1){//Si el usuario no ha seleccionado ninguna reserva en el JTable muestra un mensaje
            int fila = jTableReservas.getSelectedRow();
            Object id_reserva_objeto = jTableReservas.getValueAt(fila, 0);
            int id_reserva = Integer.parseInt(id_reserva_objeto.toString());
            try {
                conexion.setAutoCommit(false);
                String sql = "UPDATE reserva SET estado_reserva=? WHERE id_reserva=? AND id_cliente=?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, "cancelada");
                ps.setInt(2, id_reserva);
                ps.setInt(3, id);
                int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estas seguro de que quieres anular la reserva seleccionada?",
                    "Confirmación",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (opcion == JOptionPane.OK_OPTION) {
                    int resultado=ps.executeUpdate();
                    if(resultado==1){
                        System.out.println("entra");
                        conexion.commit();
                        dtm.removeRow(fila);
                    }
                }

            } catch (SQLException ex) {
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
                }
                java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione una reserva para anular.");
        }
    }//GEN-LAST:event_jButtonAnularReservaActionPerformed

    private void jButtonEditarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarReservaActionPerformed
        if(jTableReservas.getSelectedRowCount() == 1){

            int fila = jTableReservas.getSelectedRow();
            int id_reserva = Integer.parseInt(jTableReservas.getValueAt(fila, 0).toString());
            Object fechaHoraObj = jTableReservas.getValueAt(fila, 2);
            Timestamp fechaHora;

            // Convertir a Timestamp dependiendo del tipo de dato que venga
            if (fechaHoraObj instanceof Timestamp) {
                fechaHora = (Timestamp) fechaHoraObj;
            } else if (fechaHoraObj instanceof java.util.Date) {
                fechaHora = new Timestamp(((java.util.Date) fechaHoraObj).getTime());
            } else {
                // Si viene como String, parsearlo
                try {
                    String fechaStr = fechaHoraObj.toString();
                    SimpleDateFormat sdf;

                    // Detectar formato
                    if (fechaStr.contains("/")) {
                        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    } else {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }

                    java.util.Date fecha = sdf.parse(fechaStr);
                    fechaHora = new Timestamp(fecha.getTime());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane,
                        "Error al procesar la fecha: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int n_comensales = Integer.parseInt(jTableReservas.getValueAt(fila, 3).toString());

            this.dispose();
            JDialogEditarReserva jdic = new JDialogEditarReserva(padre, true, id_reserva, fechaHora, n_comensales, id);
            jdic.setVisible(true);
            recargarTabla();
        }
        else{
            JOptionPane.showMessageDialog(this, "Selecciona una reserva para editar.");
        }
    }//GEN-LAST:event_jButtonEditarReservaActionPerformed

    private void jButtonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarActionPerformed
        // TODO add your handling code here:
        int comensales = Integer.parseInt(String.valueOf(jSpinnerComensales.getValue()));
        java.util.Date mfecha = jDateChooser.getDate();
        Object valorHora = jSpinnerHora.getValue();

        //Para la comprobacion exacta de fecha y hora
        Calendar calHoy = Calendar.getInstance();
        Calendar calElegida = Calendar.getInstance();

        if(mfecha!=null){
            calElegida.setTime(mfecha);
        }

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaStr = formatoHora.format(valorHora);
        String[] partesHora = horaStr.split(":");

        calElegida.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partesHora[0]));
        calElegida.set(Calendar.MINUTE, Integer.parseInt(partesHora[1]));
        calElegida.set(Calendar.SECOND, 0);

        //Comprobacion de si algun campo esta vacio
        if(mfecha==null || valorHora==null){
            JOptionPane.showConfirmDialog(rootPane,
                "Rellena todos los campos",
                "Error",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE);
        }
        //Comprobacion de fecha no pasada
        else if(calElegida.before(calHoy)){
            JOptionPane.showMessageDialog(rootPane,
                "La hora no puede ser anterior a la actual",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        //Comprobacion de horario valido
        else if(!validarHorario(valorHora)){
            JOptionPane.showMessageDialog(rootPane,
                "La hora debe estar entre 12:00-16:00 o 21:00-23:59",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        else if(comensales<=0 || comensales>20){
            JOptionPane.showMessageDialog(rootPane,
                "El numero de comensales tiene que estar entre 1 y 20",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        //Si las comprobaciones son correctas, pasa a comprobar que en la base de datos no haya una reserva dos horas antes o despues del mismo usuario y que este dentro del
        //horario de apertura y cierre del restaurante
        else{
            try {
                // Obtener horario del restaurante (consulta que ya necesitas para el INSERT)
                int idRestaurante = comboBoxIdRestaurante();
                PreparedStatement psHorario = conexion.prepareStatement(
                    "SELECT apertura, cierre FROM Restaurante WHERE id_restaurante = ?"
                );
                psHorario.setInt(1, idRestaurante);
                ResultSet rsHorario = psHorario.executeQuery();

                if (rsHorario.next()) {
                    Time apertura = rsHorario.getTime("apertura");
                    Time cierre = rsHorario.getTime("cierre");

                    // Convertimos la hora elegida a Time para comparar
                    Time horaElegidaTime = Time.valueOf(partesHora[0] + ":" + partesHora[1] + ":00");

                    SimpleDateFormat formatoMostrar = new SimpleDateFormat("HH:mm");
                    if (horaElegidaTime.getTime() < apertura.getTime() || horaElegidaTime.getTime() >= cierre.getTime()) {
                        JOptionPane.showMessageDialog(rootPane,
                            "La hora debe estar dentro del horario del restaurante (" + formatoMostrar.format(apertura) + " - " + formatoMostrar.format(cierre) + ")",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                rsHorario.close();
                psHorario.close();
                // Comprobacion de reserva solapada (2 horas antes o despues)
                PreparedStatement psCheck = conexion.prepareStatement(
                    "SELECT COUNT(*) FROM reserva " +
                    "WHERE id_cliente = ? " +
                    "AND NOT estado_reserva = 'cancelada' " +
                    "AND ABS(TIMESTAMPDIFF(MINUTE, fecha_hora, ?)) < 120"
                );

                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaStr = formatoFecha.format(mfecha);
                String fechaHoraCompleta = fechaStr + " " + horaStr;
                Timestamp fechaHoraSQL = Timestamp.valueOf(fechaHoraCompleta);

                psCheck.setInt(1, id);
                psCheck.setTimestamp(2, fechaHoraSQL);
                ResultSet rs = psCheck.executeQuery();
                rs.next();
                int reservasSolapadas = rs.getInt(1);

                if(reservasSolapadas > 0){
                    JOptionPane.showMessageDialog(rootPane,
                        "Ya tienes una reserva en ese intervalo de tiempo.\nDebe haber al menos 2 horas entre reservas.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PreparedStatement ps = conexion.prepareStatement("INSERT INTO reserva"
                    + "(estado_reserva, n_comensales, fecha_hora, id_cliente, id_restaurante)"
                    + " VALUES (?, ?, ?, ?, ?)");

                ps.setString(1, "pendiente");
                ps.setInt(2, comensales);
                ps.setTimestamp(3, fechaHoraSQL);
                ps.setInt(4, id);
                ps.setInt(5, comboBoxIdRestaurante());

                int filas = ps.executeUpdate();
                if(filas==1){
                    JOptionPane.showConfirmDialog(rootPane,
                        "Reserva registrada",
                        "",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                    recargarTabla(); //Metodo para que la nueva reserva aparezca en el JTable de reservas
                    jSpinnerComensales.setValue(0);
                    jDateChooser.setDate(null);
                    Calendar reset = Calendar.getInstance();
                    reset.set(Calendar.HOUR_OF_DAY, 0);
                    reset.set(Calendar.MINUTE, 0);
                    reset.set(Calendar.SECOND, 0);
                    jSpinnerHora.setValue(reset.getTime());

                }
                else{
                    JOptionPane.showConfirmDialog(rootPane,
                        "Ha habido un error",
                        "Error",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonValidarActionPerformed

    private void jButtonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        padre.dispose(); // Esta linea evita que se acumulen ventanas de JFrame en la barra de tareas, se añade solo en los botones de cerrar sesion
        JFrameServix jfs = new JFrameServix();
        jfs.setVisible(true);

    }//GEN-LAST:event_jButtonCerrarSesionActionPerformed

    private void jButtonBajaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBajaClienteActionPerformed
        this.setVisible(false);
        this.dispose();
        JDialogBajaCliente jdbc = new JDialogBajaCliente(padre, true, id);
        jdbc.setVisible(true);
    }//GEN-LAST:event_jButtonBajaClienteActionPerformed
    
   //Este metodo se utiliza para que tras un insert o un update se actualiza el JTable sin que se dupliquen las columnas,
    //por eso los counts a 0
    public void recargarTabla() {
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        dtm2.setRowCount(0);
        cargaTablaReservas();
        formatoTabla();
    }
    
    private int comboBoxIdRestaurante(){ 
        //Este metodo recoje el id_restaurante de la seleccion del combobox, se utiliza en la insercion de reserva
        try {
            
            String contenido = jComboBoxRestaurantes.getItemAt(jComboBoxRestaurantes.getSelectedIndex());
            PreparedStatement ps = conexion.prepareStatement("SELECT id_restaurante FROM restaurante "
                    + "WHERE CONCAT(nombre, ' | ', direccion)=?");
            ps.setString(1, contenido);
            
            ResultSet r = ps.executeQuery();
            
            int id_restaurante=0;
            while(r.next()){
                id_restaurante = r.getInt(1);
            }
            
            return id_restaurante;
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    // Este metodo sirve para controlar si la hora introducida por el usuario entra dentro del horario del restaurante, entre apertura y cierre
    private boolean validarHorario(Object valorHora){
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the FlatLaf look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(new FlatCyanLightIJTheme());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            JFrameServix jfs = new JFrameServix();
            jfs.setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnularReserva;
    private javax.swing.JButton jButtonBajaCliente;
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonEditarReserva;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JComboBox<String> jComboBoxRestaurantes;
    private javax.swing.JComboBox<String> jComboBoxSeleccionMenu;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBotonesDeleteUpdate;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelNuevaReserva;
    private javax.swing.JPanel jPanelVerReservas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerComensales;
    private javax.swing.JSpinner jSpinnerHora;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableMenu;
    private javax.swing.JTable jTableReservas;
    // End of variables declaration//GEN-END:variables
    
    //Este metodo es el que se encarga de rellenar la tabla con la informacion de las reservas de la base de datos
    public void cargaTablaReservas(){
        
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT id_reserva as Nº, CONCAT(restaurante.nombre, ' | ', restaurante.direccion) as Restaurante_direccion, fecha_hora AS Fecha, n_comensales AS Comensales "
                   + "FROM reserva INNER JOIN restaurante "
                   + "ON reserva.id_restaurante = restaurante.id_restaurante "
                   + "WHERE id_cliente=? AND NOT estado_reserva=? AND fecha_hora >= NOW()"
            );
            ps.setInt(1, id);
            ps.setString(2, "cancelada");
            nueva.selectSQL(ps, dtm);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    //Este metodo es el que se encarga de rellenar la tabla con la informacion del menu de la base de datos
    public void cargaTablaMenu(){
        
        String restauranteSeleccionado = (String) jComboBoxSeleccionMenu.getSelectedItem();
        
        dtm2.setRowCount(0);
        dtm2.setColumnCount(0);
        
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT p.nombre AS Nombre, p.precio AS Precio, p.categoria AS Categoria "
                  + "FROM plato p JOIN restaurante r ON p.id_restaurante = r.id_restaurante "
                  + "WHERE CONCAT(r.nombre, ' | ', r.direccion) = ?"
                  + "ORDER BY categoria"
            );
            
            ps.setString(1, restauranteSeleccionado);
            nueva.selectSQL(ps, dtm2);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void formatoTabla(){
        
        FormatoTablas.FormatoInteger formatoInt = new FormatoTablas.FormatoInteger();
        FormatoTablas.FormatoFecha formatoFecha = new FormatoTablas.FormatoFecha();
        
        // Columna 0 - Nº reserva (estrecha)
        jTableReservas.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableReservas.getColumnModel().getColumn(0).setCellRenderer(formatoInt);

        // Columna 1 - Restaurante y dirección (ancha)
        jTableReservas.getColumnModel().getColumn(1).setPreferredWidth(200);

        // Columna 2 - Fecha y hora (ancha)
        jTableReservas.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTableReservas.getColumnModel().getColumn(2).setCellRenderer(formatoFecha);

        // Columna 3 - Comensales (estrecha)
        jTableReservas.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTableReservas.getColumnModel().getColumn(3).setCellRenderer(formatoInt);
    }
    
}
