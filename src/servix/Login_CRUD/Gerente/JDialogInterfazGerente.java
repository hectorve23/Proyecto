/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package servix.Login_CRUD.Gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import servix.ConexionBBDD;
import servix.FormatoTablas;
import servix.JFrameServix;
import servix.Seguridad;

/**
 *
 * @author usuario
 */
public class JDialogInterfazGerente extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName());

    /**
     * Creates new form JDialogAdministrarRestaurantes
     */
    
    DefaultTableModel dtm;
    DefaultTableModel dtm2;
    DefaultTableModel dtm3;
    ConexionBBDD nueva;
    Connection conexion;
    JFrameServix padre;
    CargaCombos cc;
    boolean editando = false;
    int idEncargado = 0, idRestaurante = 0;
    
    public JDialogInterfazGerente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        //setSize(1031, 420);
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("Servix");
        
        this.padre = (JFrameServix) parent;
        
        nueva = new ConexionBBDD();
        conexion=nueva.getConnection();
        
        this.dtm = new DefaultTableModel();
        jTableRestaurantes.setModel(dtm);
        this.dtm2 = new DefaultTableModel();
        jTableEncargados.setModel(dtm2);
        this.dtm3 = new DefaultTableModel();
        jTableAsignaciones.setModel(dtm3);
        
        // Desactivar edicion de las tablas
        jTableRestaurantes.setDefaultEditor(Object.class, null);
        jTableEncargados.setDefaultEditor(Object.class, null);
        jTableAsignaciones.setDefaultEditor(Object.class, null);
        
        // Mover el JFrame fuera de la pantalla para que no sea visible aunque se restaure
        padre.setLocation(-10000, -10000);
        padre.setSize(0, 0);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Sirve para la confirmacion de cerrar la aplicacion
        // Al cerrar el dialog muestra confirmacion para cerrar, cierra tambien el padre
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                    JDialogInterfazGerente.this,
                    "¿Seguro que quieres cerrar la aplicación?",
                    "Confirmar cierre",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    JFrameServix.cerrarYReiniciar(JDialogInterfazGerente.this, padre);
                }
            }
        });
        
        this.padre = (JFrameServix) parent;
        this.cc = new CargaCombos();
        cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
        
        cargaTablaRestaurantes();
        cargaTablaEncargados();
        cargaTablaAsignaciones();
        formatoTabla();
        camposObligatorios();
        atajosTeclado();
    }
    
    private void atajosTeclado() {
        //Ayuda
        if (JFrameServix.hb != null) {
            JFrameServix.hb.enableHelpKey(this.getContentPane(), "ayuda_gerente", JFrameServix.hs);
        }
        
        // Ctrl+G - Validar/Asignar según pestaña
        getRootPane().registerKeyboardAction(e -> {
            switch (jTabbedPane.getSelectedIndex()) {
                case 0:
                    jButtonValidarRestaurante.doClick();
                    break;
                case 1:
                    jButtonValidarEncargado.doClick();
                    break;
                case 2:
                    jButtonAsignar.doClick();
                    break;
            }
        }, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK),
           javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Ctrl+E - Editar según pestaña
        getRootPane().registerKeyboardAction(e -> {
            switch (jTabbedPane.getSelectedIndex()) {
                case 0:
                    jButtonEditarRestaurante.doClick();
                    break;
                case 1:
                    jButtonEditarEncargado.doClick();
                    break;
            }
        }, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK),
           javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Supr - Eliminar según pestaña
        getRootPane().registerKeyboardAction(e -> {
            switch (jTabbedPane.getSelectedIndex()) {
                case 0:
                    jButtonEliminarRestaurante.doClick();
                    break;
                case 1:
                    jButtonEliminarEncargado.doClick();
                    break;
                case 2:
                    jButtonEliminarAsignacion.doClick();
                    break;
            }
        }, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0),
           javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Escape - Cerrar sesión (igual en todas las pestañas)
        getRootPane().registerKeyboardAction(e -> jButtonCerrarSesion.doClick(),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Ctrl+1/2/3 - Cambiar de pestaña
        getRootPane().registerKeyboardAction(e -> jTabbedPane.setSelectedIndex(0),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e -> jTabbedPane.setSelectedIndex(1),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e -> jTabbedPane.setSelectedIndex(2),
            KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelNuevo = new javax.swing.JPanel();
        jPanelRestaurantes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombreRestaurante = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTelefonoRestaurante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCorreoRestaurante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerCapacidad = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jSpinnerApertura = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jSpinnerCierre = new javax.swing.JSpinner();
        jButtonValidarRestaurante = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRestaurantes = new javax.swing.JTable();
        jButtonEliminarRestaurante = new javax.swing.JButton();
        jButtonCerrarSesion = new javax.swing.JButton();
        jButtonEditarRestaurante = new javax.swing.JButton();
        jButtonImportarDatos = new javax.swing.JButton();
        jPanelEncargados = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEncargados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNombreEncargado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldApellido1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldApellido2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldTelefonoEncargado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCorreoEncargado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPasswordFieldContrasenya = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jPasswordFieldConfirmar = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButtonValidarEncargado = new javax.swing.JButton();
        jButtonEditarEncargado = new javax.swing.JButton();
        jButtonEliminarEncargado = new javax.swing.JButton();
        jButtonImportarDatos1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAsignaciones = new javax.swing.JTable();
        jButtonEliminarAsignacion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxRestaurantes = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxEncargados = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButtonAsignar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jButtonCerrarSesion1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelRestaurantes.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        jLabel1.setText("Nombre");
        jPanelRestaurantes.add(jLabel1);
        jPanelRestaurantes.add(jTextFieldNombreRestaurante);

        jLabel2.setText("Direccion");
        jPanelRestaurantes.add(jLabel2);
        jPanelRestaurantes.add(jTextFieldDireccion);

        jLabel3.setText("Telefono");
        jPanelRestaurantes.add(jLabel3);
        jPanelRestaurantes.add(jTextFieldTelefonoRestaurante);

        jLabel4.setText("Correo");
        jPanelRestaurantes.add(jLabel4);
        jPanelRestaurantes.add(jTextFieldCorreoRestaurante);

        jLabel5.setText("Capacidad");
        jPanelRestaurantes.add(jLabel5);
        jPanelRestaurantes.add(jSpinnerCapacidad);

        jLabel6.setText("Apertura");
        jPanelRestaurantes.add(jLabel6);

        jSpinnerApertura.setModel(new javax.swing.SpinnerDateModel());
        jSpinnerApertura.setEditor(new javax.swing.JSpinner.DateEditor(jSpinnerApertura, "HH:mm"));
        jPanelRestaurantes.add(jSpinnerApertura);

        jLabel7.setText("Cierre");
        jPanelRestaurantes.add(jLabel7);

        jSpinnerCierre.setModel(new javax.swing.SpinnerDateModel());
        jSpinnerCierre.setEditor(new javax.swing.JSpinner.DateEditor(jSpinnerCierre, "HH:mm"));
        jPanelRestaurantes.add(jSpinnerCierre);

        jButtonValidarRestaurante.setText("Validar");
        jButtonValidarRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarRestauranteActionPerformed(evt);
            }
        });

        jTableRestaurantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableRestaurantes);

        jButtonEliminarRestaurante.setText("Eliminar");
        jButtonEliminarRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarRestauranteActionPerformed(evt);
            }
        });

        jButtonCerrarSesion.setText("Cerrar sesion");
        jButtonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });

        jButtonEditarRestaurante.setText("Editar");
        jButtonEditarRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarRestauranteActionPerformed(evt);
            }
        });

        jButtonImportarDatos.setText("Importar datos");
        jButtonImportarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevoLayout = new javax.swing.GroupLayout(jPanelNuevo);
        jPanelNuevo.setLayout(jPanelNuevoLayout);
        jPanelNuevoLayout.setHorizontalGroup(
            jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoLayout.createSequentialGroup()
                .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNuevoLayout.createSequentialGroup()
                        .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelRestaurantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelNuevoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonImportarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonValidarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNuevoLayout.createSequentialGroup()
                                .addComponent(jButtonEditarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNuevoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCerrarSesion)))
                .addContainerGap())
        );
        jPanelNuevoLayout.setVerticalGroup(
            jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNuevoLayout.createSequentialGroup()
                .addComponent(jButtonCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelRestaurantes, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonValidarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonImportarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEditarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonEliminarRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        jTabbedPane.addTab("Restaurantes", jPanelNuevo);

        jTableEncargados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableEncargados);

        jPanel1.setLayout(new java.awt.GridLayout(8, 2, 10, 10));

        jLabel8.setText("Nombre");
        jPanel1.add(jLabel8);
        jPanel1.add(jTextFieldNombreEncargado);

        jLabel9.setText("Primer apellido");
        jPanel1.add(jLabel9);
        jPanel1.add(jTextFieldApellido1);

        jLabel10.setText("Segundo apellido");
        jPanel1.add(jLabel10);
        jPanel1.add(jTextFieldApellido2);

        jLabel11.setText("Telefono");
        jPanel1.add(jLabel11);
        jPanel1.add(jTextFieldTelefonoEncargado);

        jLabel12.setText("Correo");
        jPanel1.add(jLabel12);
        jPanel1.add(jTextFieldCorreoEncargado);

        jLabel13.setText("Usuario");
        jPanel1.add(jLabel13);
        jPanel1.add(jTextFieldUsuario);

        jLabel14.setText("Contraseña");
        jPanel1.add(jLabel14);
        jPanel1.add(jPasswordFieldContrasenya);

        jLabel15.setText("Confirmar contraseña");
        jPanel1.add(jLabel15);
        jPanel1.add(jPasswordFieldConfirmar);

        jButton1.setText("Cerrar sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });

        jButtonValidarEncargado.setText("Validar");
        jButtonValidarEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarEncargadoActionPerformed(evt);
            }
        });

        jButtonEditarEncargado.setText("Editar");
        jButtonEditarEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarEncargadoActionPerformed(evt);
            }
        });

        jButtonEliminarEncargado.setText("Eliminar");
        jButtonEliminarEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarEncargadoActionPerformed(evt);
            }
        });

        jButtonImportarDatos1.setText("Importar datos");
        jButtonImportarDatos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportarDatos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEncargadosLayout = new javax.swing.GroupLayout(jPanelEncargados);
        jPanelEncargados.setLayout(jPanelEncargadosLayout);
        jPanelEncargadosLayout.setHorizontalGroup(
            jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                        .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                                .addComponent(jButtonImportarDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonValidarEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE))
                            .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEditarEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminarEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEncargadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanelEncargadosLayout.setVerticalGroup(
            jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEncargadosLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEliminarEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEditarEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelEncargadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonValidarEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImportarDatos1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Encargados", jPanelEncargados);

        jTableAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableAsignaciones);

        jButtonEliminarAsignacion.setText("Eliminar");
        jButtonEliminarAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarAsignacionActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        jLabel16.setText("Restaurantes sin asignar");
        jPanel3.add(jLabel16);

        jComboBoxRestaurantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBoxRestaurantes);
        jPanel3.add(jLabel17);
        jPanel3.add(jLabel18);

        jLabel19.setText("Encargados sin asignar");
        jPanel3.add(jLabel19);

        jComboBoxEncargados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBoxEncargados);
        jPanel3.add(jLabel21);
        jPanel3.add(jLabel22);
        jPanel3.add(jLabel20);

        jButtonAsignar.setText("Asignar");
        jButtonAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsignarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonAsignar);

        jLabel23.setText("Encargados con sus restaurantes asignados");

        jButtonCerrarSesion1.setText("Cerrar sesion");
        jButtonCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonEliminarAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(158, 158, 158))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCerrarSesion1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel23))
                    .addComponent(jButtonCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminarAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Asignaciones", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        padre.dispose(); // Esta linea evita que se acumulen ventanas de JFrame en la barra de tareas, se añade solo en los botones de cerrar sesion
        JFrameServix jfs = new JFrameServix();
        jfs.setVisible(true);
    }//GEN-LAST:event_jButtonCerrarSesionActionPerformed
    private boolean direccionExiste(String direccion, int idExcluir) {
        try {
            String sql = "SELECT COUNT(*) FROM Restaurante WHERE direccion = ? AND id_restaurante != ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, direccion);
            ps.setInt(2, idExcluir); // Si es -1 excluye un id que no existe, es decir no excluye nada
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            ps.close();
            return count > 0;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        }
    }
    private void jButtonValidarRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarRestauranteActionPerformed
        // TODO add your handling code here:
        String nombre = jTextFieldNombreRestaurante.getText();
        String direccion = jTextFieldDireccion.getText();
        String telefono = jTextFieldTelefonoRestaurante.getText();
        String correo = jTextFieldCorreoRestaurante.getText();
        int capacidad = (int) jSpinnerCapacidad.getValue();
        Time apertura = new Time(((java.util.Date) jSpinnerApertura.getValue()).getTime());
        Time cierre = new Time(((java.util.Date) jSpinnerCierre.getValue()).getTime());
        java.time.LocalTime horaApertura = apertura.toLocalTime();
        java.time.LocalTime horaCierre = cierre.toLocalTime();
        
        if(nombre.isEmpty() || direccion.isEmpty() || capacidad<=0){
            JOptionPane.showConfirmDialog(rootPane,
                                                "No puede haber campos vacios o que sean menor o igual a 0", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
        }
        else if(!horaApertura.isBefore(horaCierre)){
            JOptionPane.showConfirmDialog(rootPane,
                                                "La hora de apertura no puede ser posterior a la de cierre", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
        }
        else if(direccionExiste(direccion, editando ? idRestaurante : -1)){
            JOptionPane.showConfirmDialog(rootPane,
                                                "Ya existe un restaurante con esa dirección", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(validarTelefono(telefono)){ //Comprobamos si el formato de telefono es valido
                if(validarEmail(correo)){ //Comprobamos si el formato de correo es valido
                    try {
                        if (editando) {
                            // UPDATE
                            String sql = "UPDATE restaurante SET nombre=?, direccion=?, telefono=?, correo=?, " +
                                         "capacidad=?, apertura=?, cierre=? WHERE id_restaurante=?";
                            PreparedStatement ps = conexion.prepareStatement(sql);
                            ps.setString(1, nombre);
                            ps.setString(2, direccion);
                            ps.setString(3, telefono);
                            ps.setString(4, correo);
                            ps.setInt(5, capacidad);
                            ps.setTime(6, apertura);
                            ps.setTime(7, cierre);
                            ps.setInt(8, idRestaurante);

                            int filas = ps.executeUpdate();
                            if (filas == 1) {
                                JOptionPane.showMessageDialog(rootPane, "Restaurante actualizado");
                                editando = false;
                                idRestaurante = -1;
                                jButtonValidarRestaurante.setText("Validar");
                                recargarTablaRestaurantes();
                                cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                                limpiarFormularioRestaurante();
                            }

                        } else {
                            // INSERT - tu código original
                            PreparedStatement ps = conexion.prepareStatement(
                                "INSERT INTO restaurante (nombre, direccion, telefono, correo, capacidad, apertura, cierre) " +
                                "VALUES (?,?,?,?,?,?,?)"
                            );
                            ps.setString(1, nombre);
                            ps.setString(2, direccion);
                            ps.setString(3, telefono);
                            ps.setString(4, correo);
                            ps.setInt(5, capacidad);
                            ps.setTime(6, apertura);
                            ps.setTime(7, cierre);

                            int filas = ps.executeUpdate();
                            if (filas == 1) {
                                JOptionPane.showMessageDialog(rootPane, "Restaurante registrado");
                                recargarTablaRestaurantes();
                                cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                                limpiarFormularioRestaurante();
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Ha habido un error", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                else{
                    JOptionPane.showConfirmDialog(rootPane,
                                                        "Formato de correo no valido", 
                                                        "Error", 
                                                        JOptionPane.OK_CANCEL_OPTION, 
                                                        JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showConfirmDialog(rootPane,
                                                        "Formato de telefono no valido", 
                                                        "Error", 
                                                        JOptionPane.OK_CANCEL_OPTION, 
                                                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonValidarRestauranteActionPerformed

    private void jButtonValidarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarEncargadoActionPerformed
        // TODO add your handling code here:
        String nombre = jTextFieldNombreEncargado.getText();
        String apellido1 = jTextFieldApellido1.getText();
        String apellido2 = jTextFieldApellido2.getText();
        String telefono = jTextFieldTelefonoEncargado.getText();
        String correo = jTextFieldCorreoEncargado.getText();
        String usuario = jTextFieldUsuario.getText();
        String contrasena = jPasswordFieldContrasenya.getText();
        String confirmacion_contrasena = jPasswordFieldConfirmar.getText();
        
        
        if(nombre.isEmpty() || apellido1.isEmpty() || telefono.isEmpty() || correo.isEmpty()
                || usuario.isEmpty()){
            JOptionPane.showConfirmDialog(rootPane,
                                                "No puede haber campos vacios", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
        }
        else if(!editando && contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "La contraseña no puede estar vacia", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(!(contrasena.equals(confirmacion_contrasena))){
            JOptionPane.showConfirmDialog(rootPane,
                                                "Las contraseñas no coinciden", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(!editando && existeUsuario(usuario)){
            JOptionPane.showConfirmDialog(rootPane,
                                                "El usuario " + usuario + " ya existe", 
                                                "Error", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.ERROR_MESSAGE);
            jTextFieldUsuario.setText("");
            }
            else{
                if(validarTelefono(telefono)){ //Comprobamos si el formato de telefono es valido
                    if(validarEmail(correo)){ //Comprobamos si el formato de correo es valido
                        String contrasenaEncriptada = Seguridad.hashPassword(contrasena);
                        try {
                            if (editando) {
                                // UPDATE
                                String sql;
                                PreparedStatement ps;

                                if (!contrasena.isEmpty()) {
                                    // Actualiza también la contraseña
                                    sql = "UPDATE Usuario SET nombre=?, apellido1=?, apellido2=?, telefono=?, correo=?, " +
                                          "usuario_login=?, contrasenya_login=? WHERE id=? AND rol='encargado'";
                                    ps = conexion.prepareStatement(sql);
                                    ps.setString(1, nombre);
                                    ps.setString(2, apellido1);
                                    ps.setString(3, apellido2);
                                    ps.setString(4, telefono);
                                    ps.setString(5, correo);
                                    ps.setString(6, usuario);
                                    ps.setString(7, Seguridad.hashPassword(contrasena));
                                    ps.setInt(8, idEncargado);
                                } else {
                                    // No cambia la contraseña
                                    sql = "UPDATE Usuario SET nombre=?, apellido1=?, apellido2=?, telefono=?, correo=?, " +
                                          "usuario_login=? WHERE id=? AND rol='encargado'";
                                    ps = conexion.prepareStatement(sql);
                                    ps.setString(1, nombre);
                                    ps.setString(2, apellido1);
                                    ps.setString(3, apellido2);
                                    ps.setString(4, telefono);
                                    ps.setString(5, correo);
                                    ps.setString(6, usuario);
                                    ps.setInt(7, idEncargado);
                                }
                                jLabel14.setText("<html>Contraseña <font color='red'>*</font></html>");
                                jLabel15.setText("<html>Introduzca de nuevo la contraseña <font color='red'>*</font></html>");
                                int filas = ps.executeUpdate();
                                if (filas == 1) {
                                    JOptionPane.showMessageDialog(rootPane, "Encargado actualizado");
                                    editando = false;
                                    idEncargado = -1;
                                    jButtonValidarEncargado.setText("Validar");
                                    recargarTablaEncargados();
                                    cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                                    limpiarFormularioEncargado();
                                }
                            } else {
                                String sql = "INSERT INTO Usuario(nombre, apellido1, apellido2, telefono, correo, usuario_login, contrasenya_login, rol, fecha_creacion) "
                                        + " VALUES (?,?,?,?,?,?,?,?, CURDATE())";
                                PreparedStatement ps = conexion.prepareStatement(sql);
                                ps.setString(1, nombre);
                                ps.setString(2, apellido1);
                                ps.setString(3, apellido2);
                                ps.setString(4, telefono);
                                ps.setString(5, correo);
                                ps.setString(6, usuario);
                                ps.setString(7, contrasenaEncriptada);
                                ps.setString(8, "encargado");


                                int filas = ps.executeUpdate();
                                if(filas==1){
                                    JOptionPane.showConfirmDialog(rootPane,
                                                                "Encargado registrado", 
                                                                "", 
                                                                JOptionPane.OK_CANCEL_OPTION, 
                                                                JOptionPane.INFORMATION_MESSAGE);
                                    recargarTablaEncargados(); 
                                    cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                                    jTextFieldNombreEncargado.setText("");
                                    jTextFieldApellido1.setText("");
                                    jTextFieldApellido2.setText("");
                                    jTextFieldTelefonoEncargado.setText("");
                                    jTextFieldCorreoEncargado.setText("");
                                    jTextFieldUsuario.setText("");
                                    jPasswordFieldContrasenya.setText("");
                                    jPasswordFieldConfirmar.setText("");
                                }
                                else{
                                    JOptionPane.showConfirmDialog(rootPane,
                                                                "Ha habido un error", 
                                                                "Error", 
                                                                JOptionPane.OK_CANCEL_OPTION, 
                                                                JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        JOptionPane.showConfirmDialog(rootPane,
                                                            "Formato de correo no valido", 
                                                            "Error", 
                                                            JOptionPane.OK_CANCEL_OPTION, 
                                                            JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showConfirmDialog(rootPane,
                                                            "Formato de telefono no valido", 
                                                            "Error", 
                                                            JOptionPane.OK_CANCEL_OPTION, 
                                                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
    }//GEN-LAST:event_jButtonValidarEncargadoActionPerformed

    public boolean existeUsuario(String usuario){
        boolean existe= false;
        try {    
            String sql = "SELECT * FROM Usuario WHERE usuario_login = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) 
                existe = true;
                        
            rs.close();
            ps.close();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        return existe;
    }
    
    private void jButtonEliminarAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarAsignacionActionPerformed
        // TODO add your handling code here:
        if(jTableAsignaciones.getSelectedRowCount()==1){//Si el usuario no ha seleccionado ninguna reserva en el JTable muestra un mensaje
            int fila = jTableAsignaciones.getSelectedRow();
            String nombre_apellidos_usuario = String.valueOf(jTableAsignaciones.getValueAt(fila, 0));
            //String nombre_direccion_restaurante = String.valueOf(jTableAsignaciones.getValueAt(fila, 1));
            
             try {
                conexion.setAutoCommit(false);
                String sql = "UPDATE Usuario SET restaurante_asociado = NULL " +
                             "WHERE CONCAT(nombre,' ',apellido1,' ',apellido2) = ? " +
                             "AND rol = 'encargado'";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, nombre_apellidos_usuario);
                //ps.setString(2, nombre_direccion_restaurante);
                int opcion = JOptionPane.showConfirmDialog(
                                                null,
                                                "¿Estas seguro de eliminar la asignacion seleccionada?",
                                                "Confirmación",
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE
                );
                if (opcion == JOptionPane.OK_OPTION) {
                    int resultado=ps.executeUpdate();
                    if(resultado==1){
                        System.out.println("entra");
                        conexion.commit();
                        conexion.setAutoCommit(true);
                        //dtm3.removeRow(fila);
                        cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                        recargarTablaAsignaciones();
                    }
                }
                
                
            } catch (SQLException ex) {
                try {
                    conexion.rollback();
                }catch (SQLException e) {
                    java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
                java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione una asignacion para eliminar.");
        }
    }//GEN-LAST:event_jButtonEliminarAsignacionActionPerformed

    private void jButtonEliminarRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarRestauranteActionPerformed
        // TODO add your handling code here:
        if(jTableRestaurantes.getSelectedRowCount()==1){//Si el usuario no ha seleccionado ninguna reserva en el JTable muestra un mensaje
            int fila = jTableRestaurantes.getSelectedRow();
            Object id_restaurante_objeto = jTableRestaurantes.getValueAt(fila, 0);
            int id_restaurante = Integer.parseInt(id_restaurante_objeto.toString());
             try {
                conexion.setAutoCommit(false);
                String sql = "DELETE FROM restaurante WHERE id_restaurante=?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_restaurante);
                int opcion = JOptionPane.showConfirmDialog(
                                                null,
                                                "¿Estas seguro de eliminar el restaurante seleccionado?",
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
                        cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                        recargarTablaAsignaciones();
                    }
                }
                
                
            } catch (SQLException ex) {
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
                }
                java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione un restaurante para eliminar.");
        }
    }//GEN-LAST:event_jButtonEliminarRestauranteActionPerformed

    private void jButtonEliminarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarEncargadoActionPerformed
        // TODO add your handling code here:
        if(jTableEncargados.getSelectedRowCount()==1){//Si el usuario no ha seleccionado ninguna reserva en el JTable muestra un mensaje
            int fila = jTableEncargados.getSelectedRow();
            Object id_restaurante_objeto = jTableEncargados.getValueAt(fila, 0);
            int id_restaurante = Integer.parseInt(id_restaurante_objeto.toString());
             try {
                conexion.setAutoCommit(false);
                String sql = "DELETE FROM usuario WHERE id=? AND rol=?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_restaurante);
                ps.setString(2, "encargado");
                int opcion = JOptionPane.showConfirmDialog(
                                                null,
                                                "¿Estas seguro de eliminar el encargado seleccionado?",
                                                "Confirmación",
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE
                );
                if (opcion == JOptionPane.OK_OPTION) {
                    int resultado=ps.executeUpdate();
                    if(resultado==1){
                        System.out.println("entra");
                        conexion.commit();
                        dtm2.removeRow(fila);
                        cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados);
                        recargarTablaAsignaciones();
                    }
                }
                
                
            } catch (SQLException ex) {
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
                }
                java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione un encargado para eliminar.");
        }
    }//GEN-LAST:event_jButtonEliminarEncargadoActionPerformed

    private void jButtonEditarEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarEncargadoActionPerformed
        if (jTableEncargados.getSelectedRowCount() == 1) {
            jLabel14.setText("<html>Contraseña</html>");
            jLabel15.setText("<html>Introduzca de nuevo la contraseña</html>");
            int fila = jTableEncargados.getSelectedRow();
            idEncargado = Integer.parseInt(jTableEncargados.getValueAt(fila, 0).toString());

            jTextFieldNombreEncargado.setText(jTableEncargados.getValueAt(fila, 1).toString());
            jTextFieldApellido1.setText(jTableEncargados.getValueAt(fila, 2).toString());
            jTextFieldApellido2.setText(jTableEncargados.getValueAt(fila, 3).toString());
            jTextFieldTelefonoEncargado.setText(jTableEncargados.getValueAt(fila, 4).toString());
            jTextFieldCorreoEncargado.setText(jTableEncargados.getValueAt(fila, 5).toString());
            jTextFieldUsuario.setText(jTableEncargados.getValueAt(fila, 6).toString());
            jPasswordFieldContrasenya.setText("");
            jPasswordFieldConfirmar.setText("");

            editando = true;
            jButtonValidarEncargado.setText("Actualizar");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un encargado para editarlo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEditarEncargadoActionPerformed

    private void jButtonAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAsignarActionPerformed
        // TODO add your handling code here:
        try {
            conexion.setAutoCommit(true);
            String sql = "UPDATE Usuario SET restaurante_asociado = " +
                         "(SELECT id_restaurante FROM Restaurante WHERE CONCAT(nombre,' | ',direccion) = ?) " +
                         "WHERE CONCAT(nombre,' ',apellido1,' ',apellido2) = ? " +
                         "AND rol = 'encargado'";
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            // Inserta en la tabla usuario_restaurante los id del restaurante y el usuario seleccionados en los combobox
            ps.setString(1, jComboBoxRestaurantes.getSelectedItem().toString());
            ps.setString(2, jComboBoxEncargados.getSelectedItem().toString());

            int filas = ps.executeUpdate();

            if(filas == 1) {
                JOptionPane.showMessageDialog(rootPane, "Asignación confirmada");
                recargarTablaAsignaciones();
                cc.cargaCombos(jComboBoxRestaurantes, jComboBoxEncargados); // Recarga los combobox porque al realizarse la asignacion tienen que desaparecer del mismo
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAsignarActionPerformed

    private void jButtonEditarRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarRestauranteActionPerformed
        if (jTableRestaurantes.getSelectedRowCount() == 1) {
            int fila = jTableRestaurantes.getSelectedRow();
            idRestaurante = Integer.parseInt(jTableRestaurantes.getValueAt(fila, 0).toString());

            jTextFieldNombreRestaurante.setText(jTableRestaurantes.getValueAt(fila, 1).toString());
            jTextFieldDireccion.setText(jTableRestaurantes.getValueAt(fila, 2).toString());
            jTextFieldTelefonoRestaurante.setText(jTableRestaurantes.getValueAt(fila, 3).toString());
            jTextFieldCorreoRestaurante.setText(jTableRestaurantes.getValueAt(fila, 4).toString());
            jSpinnerCapacidad.setValue(Integer.parseInt(jTableRestaurantes.getValueAt(fila, 5).toString()));

            // Cargar apertura y cierre en los spinners
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                jSpinnerApertura.setValue(sdf.parse(jTableRestaurantes.getValueAt(fila, 6).toString()));
                jSpinnerCierre.setValue(sdf.parse(jTableRestaurantes.getValueAt(fila, 7).toString()));
            } catch (Exception e) {
                logger.log(java.util.logging.Level.SEVERE, null, e);
            }

            editando = true;
            jButtonValidarRestaurante.setText("Actualizar");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un restaurante para editarlo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEditarRestauranteActionPerformed

    private void jButtonImportarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportarDatosActionPerformed
        JDialogDatosDePrueba jddp = new JDialogDatosDePrueba(padre, true);
        jddp.setVisible(true);
        recargarTablaRestaurantes();
        recargarTablaEncargados();
        recargarTablaAsignaciones();
    }//GEN-LAST:event_jButtonImportarDatosActionPerformed

    private void jButtonImportarDatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportarDatos1ActionPerformed
        JDialogDatosDePrueba jddp = new JDialogDatosDePrueba(padre, true);
        jddp.setVisible(true);
        recargarTablaRestaurantes();
        recargarTablaEncargados();
        recargarTablaAsignaciones();
    }//GEN-LAST:event_jButtonImportarDatos1ActionPerformed
    
    public void recargarTablaRestaurantes() {
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        cargaTablaRestaurantes();
        formatoTabla();
    }
    public void recargarTablaEncargados() {
        dtm2.setRowCount(0);
        dtm2.setColumnCount(0);
        cargaTablaEncargados();
        formatoTabla();
    }
    public void recargarTablaAsignaciones() {
        dtm3.setRowCount(0);
        dtm3.setColumnCount(0);
        cargaTablaAsignaciones();
    }
    public void cargaTablaRestaurantes(){
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT id_restaurante as Id, nombre as Nombre, direccion as Direccion, telefono as Telefono, correo as Correo, capacidad as Capacidad,"
                            + " apertura as Apertura, cierre as Cierre"
                            + " FROM restaurante"
            );
            nueva.selectSQL(ps, dtm);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    private void cargaTablaEncargados() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT id as Id, nombre as Nombre, apellido1 as Apellido_1, apellido2 as Apellido_2, telefono as Telefono, correo as Correo,"
                            + " usuario_login as Usuario"
                            + " FROM usuario WHERE rol=?"
            );
            ps.setString(1, "encargado");
            nueva.selectSQL(ps, dtm2);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    private void cargaTablaAsignaciones() {
        try {
            String sql = "SELECT CONCAT(u.nombre,' ',u.apellido1,' ',u.apellido2) AS 'Encargado', " +
                         "CONCAT(r.nombre,' | ',r.direccion) AS 'Restaurante' " +
                         "FROM Usuario u " +
                         "INNER JOIN Restaurante r ON u.restaurante_asociado = r.id_restaurante " +
                         "WHERE u.rol = 'encargado' AND u.restaurante_asociado IS NOT NULL";
            PreparedStatement ps = conexion.prepareStatement(sql);
            //ps.setString(1, "encargado");
            nueva.selectSQL(ps, dtm3);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDialogInterfazGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void camposObligatorios(){
        jLabel1.setText("<html>Nombre <font color='red'>*</font></html>");
        jLabel2.setText("<html>Direccion <font color='red'>*</font></html>");
        jLabel3.setText("<html>Telefono <font color='red'>*</font></html>");
        jLabel4.setText("<html>Correo <font color='red'>*</font></html>");
        jLabel5.setText("<html>Capacidad <font color='red'>*</font></html>");
        jLabel6.setText("<html>Apertura <font color='red'>*</font></html>");
        jLabel7.setText("<html>Cierre <font color='red'>*</font></html>");
        jLabel8.setText("<html>Nombre <font color='red'>*</font></html>");
        jLabel9.setText("<html>Primer apellido <font color='red'>*</font></html>");
        jLabel11.setText("<html>Telefono <font color='red'>*</font></html>");
        jLabel12.setText("<html>Correo <font color='red'>*</font></html>");
        jLabel13.setText("<html>Usuario <font color='red'>*</font></html>");
        jLabel14.setText("<html>Contraseña <font color='red'>*</font></html>");
        jLabel15.setText("<html>Introduzca de nuevo la contraseña <font color='red'>*</font></html>");
    }
    
    public void formatoTabla(){ // Configuracion para que los campos de la tabla se vea bien
        FormatoTablas.FormatoInteger formatoInt = new FormatoTablas.FormatoInteger();
        
        jTableRestaurantes.getColumnModel().getColumn(0).setCellRenderer(formatoInt);
        jTableRestaurantes.getColumnModel().getColumn(3).setCellRenderer(formatoInt);
        jTableRestaurantes.getColumnModel().getColumn(5).setCellRenderer(formatoInt);
        jTableRestaurantes.getColumnModel().getColumn(6).setCellRenderer(formatoInt);
        jTableRestaurantes.getColumnModel().getColumn(7).setCellRenderer(formatoInt);
        
        // Ajuste de anchos de columna
        // Columnas estrechas (numeros)
        jTableRestaurantes.getColumnModel().getColumn(0).setPreferredWidth(40);  
        jTableRestaurantes.getColumnModel().getColumn(5).setPreferredWidth(70);  
        jTableRestaurantes.getColumnModel().getColumn(6).setPreferredWidth(60);  
        jTableRestaurantes.getColumnModel().getColumn(7).setPreferredWidth(60);  

        // Columnas anchas (lo demas)
        jTableRestaurantes.getColumnModel().getColumn(1).setPreferredWidth(150); 
        jTableRestaurantes.getColumnModel().getColumn(2).setPreferredWidth(200); 
        jTableRestaurantes.getColumnModel().getColumn(3).setPreferredWidth(90);  
        jTableRestaurantes.getColumnModel().getColumn(4).setPreferredWidth(180); 
        
        // Columna estrecha (id)
        jTableEncargados.getColumnModel().getColumn(0).setPreferredWidth(70);   // Id

        // Columnas medias
        jTableEncargados.getColumnModel().getColumn(1).setPreferredWidth(120);  // Nombre
        jTableEncargados.getColumnModel().getColumn(2).setPreferredWidth(120);  // Primer_Apellido
        jTableEncargados.getColumnModel().getColumn(3).setPreferredWidth(120);  // Segundo_Apellido
        jTableEncargados.getColumnModel().getColumn(4).setPreferredWidth(90);   // Telefono

        jTableEncargados.getColumnModel().getColumn(5).setPreferredWidth(200);  // Correo
        jTableEncargados.getColumnModel().getColumn(6).setPreferredWidth(90);
    }
    
    private static final Pattern PATTERN_EMAIL = Pattern.compile( //Patron para validar el email
        "^[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$" // se permiten letras, simbolos, tiene que haber un @
                                                          // y un . , el dominio tiene que tener 2 letras
    );
    
    private static final Pattern PATTERN_TELEFONO = Pattern.compile( //Patron para validar el telefono
        "^[0-9]{9}$" // 9 Numeros del 0-9
    );

     public static boolean validarEmail(String correo) { //Compobamos que el correo cumpla el patron
        if (!PATTERN_EMAIL.matcher(correo).matches()) { 
            return false;
        }
        return true;
    }
     
    public static boolean validarTelefono(String telefono) { //Comprobamos que el telefono cumpla el patron
        if (!PATTERN_TELEFONO.matcher(telefono).matches()) {
            return false;
        }
        return true;
    }
    
    private void limpiarFormularioEncargado() {
        jTextFieldNombreEncargado.setText("");
        jTextFieldApellido1.setText("");
        jTextFieldApellido2.setText("");
        jTextFieldTelefonoEncargado.setText("");
        jTextFieldCorreoEncargado.setText("");
        jTextFieldUsuario.setText("");
        jPasswordFieldContrasenya.setText("");
        jPasswordFieldConfirmar.setText("");
    }
    
    private void limpiarFormularioRestaurante() {
        jTextFieldNombreRestaurante.setText("");
        jTextFieldDireccion.setText("");
        jTextFieldTelefonoRestaurante.setText("");
        jTextFieldCorreoRestaurante.setText("");
        jSpinnerCapacidad.setValue(0);
        jSpinnerApertura.setValue(new java.util.Date(0));
        jSpinnerCierre.setValue(new java.util.Date(0));
    }
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            JFrameServix jfs = new JFrameServix();
            jfs.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAsignar;
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonCerrarSesion1;
    private javax.swing.JButton jButtonEditarEncargado;
    private javax.swing.JButton jButtonEditarRestaurante;
    private javax.swing.JButton jButtonEliminarAsignacion;
    private javax.swing.JButton jButtonEliminarEncargado;
    private javax.swing.JButton jButtonEliminarRestaurante;
    private javax.swing.JButton jButtonImportarDatos;
    private javax.swing.JButton jButtonImportarDatos1;
    private javax.swing.JButton jButtonValidarEncargado;
    private javax.swing.JButton jButtonValidarRestaurante;
    private javax.swing.JComboBox<String> jComboBoxEncargados;
    private javax.swing.JComboBox<String> jComboBoxRestaurantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelEncargados;
    private javax.swing.JPanel jPanelNuevo;
    private javax.swing.JPanel jPanelRestaurantes;
    private javax.swing.JPasswordField jPasswordFieldConfirmar;
    private javax.swing.JPasswordField jPasswordFieldContrasenya;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerApertura;
    private javax.swing.JSpinner jSpinnerCapacidad;
    private javax.swing.JSpinner jSpinnerCierre;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableAsignaciones;
    private javax.swing.JTable jTableEncargados;
    private javax.swing.JTable jTableRestaurantes;
    private javax.swing.JTextField jTextFieldApellido1;
    private javax.swing.JTextField jTextFieldApellido2;
    private javax.swing.JTextField jTextFieldCorreoEncargado;
    private javax.swing.JTextField jTextFieldCorreoRestaurante;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldNombreEncargado;
    private javax.swing.JTextField jTextFieldNombreRestaurante;
    private javax.swing.JTextField jTextFieldTelefonoEncargado;
    private javax.swing.JTextField jTextFieldTelefonoRestaurante;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

    
    
}
