package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Controller.UsuarioController;

import javax.swing.border.EmptyBorder;
import Dao.SolicitanteDAO;
import Modelo.Solicitante;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PanelUsuarios extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtUsername;
    private JTextField txtNombre;
    private JTextField txtBuscar;
    private JPasswordField txtNuevaPass;
    private JPasswordField txtConfirmarPass; 
    
    private JLabel lblCambiarPass ;
    private JComboBox<String> cboRol;
    private JComboBox<String> cboEstado;

    private JButton btnActualizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnNuevo;
    

    private JTable table;
    private DefaultTableModel modelo;
    private boolean cambiandoPassword = false;
    private int idSeleccionado = 0;
    
    public PanelUsuarios() {
    	
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // =======================
        // PANEL TÍTULO
        // =======================

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setPreferredSize(new Dimension(0, 50));
        panelTitulo.setLayout(new BorderLayout());
        add(panelTitulo, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Gestión de Usuarios");
        lblTitulo.setOpaque(true);
        lblTitulo.setPreferredSize(new Dimension(10, 10));
        lblTitulo.setMinimumSize(new Dimension(10, 10));
        lblTitulo.setMaximumSize(new Dimension(32767, 32767));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(20, 20, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // =======================
        // PANEL PRINCIPAL CON SCROLL
        // =======================

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(16, 0));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPrincipal, BorderLayout.CENTER);

        // =======================
        // PANEL FORMULARIO
        // =======================

        JPanel panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(310, 650));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));
        panelFormulario.setLayout(null);
        panelPrincipal.add(panelFormulario, BorderLayout.WEST);

        JLabel lblDetalleUsuario = new JLabel("Detalle del Usuario");
        lblDetalleUsuario.setBounds(16, 12, 250, 25);
        lblDetalleUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDetalleUsuario.setForeground(new Color(55, 65, 81));
        panelFormulario.add(lblDetalleUsuario);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 45, 310, 2);
        panelFormulario.add(separator);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(16, 62, 120, 20);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(16, 85, 278, 32);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtUsername);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(16, 130, 120, 20);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(16, 153, 278, 32);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtNombre);

        JLabel lblRol = new JLabel("Rol de Usuario");
        lblRol.setBounds(16, 196, 120, 20);
        lblRol.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblRol);

        cboRol = new JComboBox<>();
        cboRol.setModel(new DefaultComboBoxModel<>(new String[] {"Administrador", "Operador"}));
        cboRol.setBounds(16, 221, 278, 32);
        cboRol.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(cboRol);

        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setBounds(16, 265, 120, 20);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblEstado);

        cboEstado = new JComboBox<>();
        cboEstado.setModel(new DefaultComboBoxModel<>(
                new String[]{"Activo", "Inactivo"}));
        cboEstado.setBounds(16, 290, 278, 32);
        cboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(cboEstado);
        
        
        JSeparator separatorContraseña = new JSeparator();
        separatorContraseña.setBounds(0, 340, 310, 2);
        panelFormulario.add(separatorContraseña);

        JLabel lblSeguridad = new JLabel("Seguridad");
        lblSeguridad.setBounds(16, 355, 100, 20);
        lblSeguridad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSeguridad.setForeground(new Color(55, 65, 81));
        panelFormulario.add(lblSeguridad);

        lblCambiarPass = new JLabel("<HTML><U>Cambiar Contraseña</U></HTML>");
        lblCambiarPass.setBounds(170, 355, 130, 20);
        lblCambiarPass.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblCambiarPass.setForeground(new Color(25, 118, 210));
        lblCambiarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(lblCambiarPass);
        
        

        // Nueva Contraseña
        JLabel lblNuevaPass = new JLabel("Nueva Contraseña");
        lblNuevaPass.setBounds(16, 390, 150, 20);
        lblNuevaPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(lblNuevaPass);

        txtNuevaPass = new JPasswordField();
        txtNuevaPass.setBounds(16, 413, 278, 32);
        txtNuevaPass.setEnabled(false);
        txtNuevaPass.setBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtNuevaPass);

        // Confirmar Contraseña
        JLabel lblConfirmarPass = new JLabel("Confirmar Contraseña");
        lblConfirmarPass.setBounds(16, 455, 150, 20);
        
        lblConfirmarPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(lblConfirmarPass);

        txtConfirmarPass = new JPasswordField();
        txtConfirmarPass.setBounds(16, 478, 278, 32);
        txtConfirmarPass.setEnabled(false);
        txtConfirmarPass.setBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtConfirmarPass);
        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(16, 545, 90, 35);
        btnActualizar.setBackground(new Color(25, 118, 210));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        panelFormulario.add(btnActualizar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(115, 545, 90, 35);
        btnLimpiar.setBackground(new Color(130, 130, 130));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        panelFormulario.add(btnLimpiar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(214, 545, 80, 35);
        btnEliminar.setBackground(new Color(211, 47, 47));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        panelFormulario.add(btnEliminar);

        // =======================
        // PANEL TABLA
        // =======================

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // =======================
        // PANEL BUSCADOR
        // =======================

        JPanel panelBuscador = new JPanel();
        panelBuscador.setLayout(new BorderLayout(10, 0));
        panelBuscador.setBackground(Color.WHITE);
        panelBuscador.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelTabla.add(panelBuscador, BorderLayout.NORTH);

        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(260, 32));
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelBuscador.add(txtBuscar, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel();
        panelAcciones.setOpaque(false);
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 32));
        btnBuscar.setBackground(new Color(25, 118, 210));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setPreferredSize(new Dimension(100, 32));
        btnNuevo.setBackground(new Color(26, 35, 126));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBorderPainted(false);

        panelAcciones.add(btnBuscar);
        panelAcciones.add(btnNuevo);
        panelBuscador.add(panelAcciones, BorderLayout.EAST);

        // =======================
        // TABLA
        // =======================

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();

        modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "ID",
                        "Username",
                        "Nombre Completo",
                        "Rol",
                        "Estado",
                }
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(modelo);
        scrollPane.setViewportView(table);

        table.setRowHeight(36);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(38, 50, 56));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));
        table.getTableHeader().setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);
        table.getColumnModel().getColumn(4).setPreferredWidth(180);

        listarUsuarios();

        lblCambiarPass.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                txtNuevaPass.setEnabled(true);
                txtConfirmarPass.setEnabled(true);

                txtNuevaPass.requestFocus();

                lblCambiarPass.setText("Cancelar");
            }
        });
        
        lblCambiarPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                cambiandoPassword = !cambiandoPassword;

                txtNuevaPass.setEnabled(cambiandoPassword);
                txtConfirmarPass.setEnabled(cambiandoPassword);

                if (cambiandoPassword) {
                    lblCambiarPass.setText("<HTML><U>Cancelar</U></HTML>");
                    txtNuevaPass.requestFocus();
                } else {
                    lblCambiarPass.setText("<HTML><U>Cambiar Contraseña</U></HTML>");

                    txtNuevaPass.setText("");
                    txtConfirmarPass.setText("");
                }
            }
        });
         
        btnActualizar.addActionListener(e -> actualizar());
        btnNuevo.addActionListener(e -> mostrarRegistroUsuario());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarFila();
            }
        });
        
    }
        
    	
    private void cargarFila() {

        int fila = table.getSelectedRow();
        if (fila == -1) {
            return;
        }

        idSeleccionado = Integer.parseInt(table.getValueAt(fila, 0).toString());
        txtUsername.setText(table.getValueAt(fila, 1).toString());
        txtNombre.setText(table.getValueAt(fila, 2).toString());
        cboRol.setSelectedItem(table.getValueAt(fila, 3).toString());
        cboEstado.setSelectedItem(table.getValueAt(fila, 4).toString());

    }
    
    private void listarUsuarios() {

           UsuarioController usuarioController = new UsuarioController();
           modelo.setRowCount(0);

           List<Usuario> lista = usuarioController.procesarListado();

           for (Usuario user : lista) {
               modelo.addRow(new Object[]{
            		   user.getIdUsuario(),
                       user.getUsername(),
                       user.getNombresCompletos(),
                       user.getRol(),
                       user.getEstado()
                      
                });
            }
    }
       
    private void mostrarRegistroUsuario() {
    	FrmRegistroUsuario formRegistroUsuario = new FrmRegistroUsuario();
    	formRegistroUsuario.setVisible(true);    	
    }
    
    private void limpiarCampos() {

            txtUsername.setText("");
            txtNombre.setText("");
            cboRol.setSelectedIndex(0);
            table.clearSelection();
    }
    
    private void buscar() {
        String termino = txtBuscar.getText();
        
        UsuarioController controller = new UsuarioController();
        List<Usuario> listaEncontrada = controller.procesarBusqueda(termino);
        
        modelo.setRowCount(0);
        
        for (Usuario user : listaEncontrada) {
            modelo.addRow(new Object[]{
                user.getIdUsuario(),
                user.getUsername(),
                user.getNombresCompletos(),
                user.getRol(),
                user.getEstado()
            });
        }
    }
    
    private void actualizar() {
    	if(idSeleccionado == 0) {
    		JOptionPane.showMessageDialog(null, "Por favor seleccione un usuario para actualizar");
    		return;
    	}
    	
    	String username = txtUsername.getText();
        String nombre = txtNombre.getText();
        String rol = cboRol.getSelectedItem().toString();
        String estado = cboEstado.getSelectedItem().toString();
        
        if (username.trim().isEmpty() || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos Username y Nombre no pueden estar vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Usuario usuarioModificado = new Usuario();
        usuarioModificado.setIdUsuario(idSeleccionado); 
        usuarioModificado.setUsername(username);
        usuarioModificado.setNombresCompletos(nombre);
        usuarioModificado.setRol(rol);
        usuarioModificado.setEstado(estado);
        
        UsuarioController controller = new UsuarioController();
        
        if (cambiandoPassword) {

            String nuevaPass = new String(txtNuevaPass.getPassword());
            String confirmarPass = new String(txtConfirmarPass.getPassword());

            if (nuevaPass.trim().isEmpty() || !nuevaPass.equals(confirmarPass)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden o están vacías.", "Error de Seguridad", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            
            boolean exitoClave = controller.procesarCambioPassword(idSeleccionado, nuevaPass);
            
            if (!exitoClave) {
                JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña (debe tener mínimo 6 caracteres).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }
        
        boolean exito = controller.procesarActualizacion(usuarioModificado);
        
        if (exito) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            listarUsuarios();
            limpiarCampos(); 
            idSeleccionado = 0; 
            
            if (cambiandoPassword) {
                cambiandoPassword = false;
                lblCambiarPass.setText("<HTML><U>Cambiar Contraseña</U></HTML>");
                txtNuevaPass.setText("");
                txtConfirmarPass.setText("");
                txtNuevaPass.setEnabled(false);
                txtConfirmarPass.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminar() {
    	
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un usuario de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro que desea desactivar al usuario seleccionado?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            
            UsuarioController controller = new UsuarioController();
            boolean exito = controller.procesarEliminacion(idSeleccionado);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Usuario desactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                listarUsuarios(); 
                limpiarCampos();
                idSeleccionado = 0; 
                
                if (cambiandoPassword) {
                    cambiandoPassword = false;
                    lblCambiarPass.setText("<HTML><U>Cambiar Contraseña</U></HTML>");
                    txtNuevaPass.setText("");
                    txtConfirmarPass.setText("");
                    txtNuevaPass.setEnabled(false);
                    txtConfirmarPass.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
  }
