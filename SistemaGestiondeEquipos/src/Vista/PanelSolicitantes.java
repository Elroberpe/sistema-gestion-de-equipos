package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PanelSolicitantes extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelSolicitantes() {
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

        JLabel lblTitulo = new JLabel("Gestión de Solicitantes");
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

        JLabel lblDetalleSolicitante = new JLabel("Detalle del Solicitante");
        lblDetalleSolicitante.setBounds(16, 12, 250, 25);
        lblDetalleSolicitante.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDetalleSolicitante.setForeground(new Color(55, 65, 81));
        panelFormulario.add(lblDetalleSolicitante);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 45, 310, 2);
        panelFormulario.add(separator);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setBounds(16, 62, 120, 20);
        lblDni.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblDni);

        JTextField txtDni = new JTextField();
        txtDni.setText("Ej: 42345678");
        txtDni.setBounds(16, 85, 278, 32);
        txtDni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDni.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtDni);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(16, 130, 120, 20);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(16, 153, 278, 32);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(16, 198, 120, 20);
        lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblApellidos);

        JTextField txtApellidos = new JTextField();
        txtApellidos.setBounds(16, 221, 278, 32);
        txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtApellidos.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtApellidos);

        JLabel lblTipo = new JLabel("Tipo de Solicitante");
        lblTipo.setBounds(16, 266, 160, 20);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblTipo);

        JComboBox<String> cboTipo = new JComboBox<>();
        cboTipo.setModel(new DefaultComboBoxModel<String>(
                new String[] { "Docente", "Estudiante", "Administrativo" }
        ));
        cboTipo.setBounds(16, 289, 278, 32);
        cboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(cboTipo);

        JLabel lblAreaCarrera = new JLabel("Área/Carrera");
        lblAreaCarrera.setBounds(16, 334, 150, 20);
        lblAreaCarrera.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblAreaCarrera);

        JTextField txtAreaCarrera = new JTextField();
        txtAreaCarrera.setBounds(16, 357, 278, 32);
        txtAreaCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtAreaCarrera.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtAreaCarrera);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setBounds(16, 402, 120, 20);
        lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblTelefono);

        JTextField txtTelefono = new JTextField();
        txtTelefono.setBounds(16, 425, 278, 32);
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtTelefono.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setBounds(16, 470, 160, 20);
        lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(16, 493, 278, 32);
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCorreo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtCorreo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(16, 545, 130, 35);
        btnGuardar.setBackground(new Color(25, 118, 210));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        panelFormulario.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(164, 545, 130, 35);
        btnLimpiar.setBackground(new Color(130, 130, 130));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        panelFormulario.add(btnLimpiar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(16, 590, 278, 35);
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

        JTextField txtBuscar = new JTextField("Buscar solicitante...");
        txtBuscar.setPreferredSize(new Dimension(260, 32));
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelBuscador.add(txtBuscar, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel();
        panelAcciones.setOpaque(false);
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 32));
        btnBuscar.setBackground(new Color(25, 118, 210));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setPreferredSize(new Dimension(100, 32));
        btnActualizar.setBackground(new Color(97, 97, 97));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);

        panelAcciones.add(btnBuscar);
        panelAcciones.add(btnActualizar);
        panelBuscador.add(panelAcciones, BorderLayout.EAST);

        // =======================
        // TABLA
        // =======================

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        JTable table = new JTable();

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][] {
                        { "42345678", "Juan Carlos", "Ramírez Torres", "Estudiante", "Computación e Informática", "987654321", "juan.ramirez@gmail.com" },
                        { "71234567", "María Elena", "Gómez Vargas", "Docente", "Matemática", "912345678", "maria.gomez@institucion.edu" },
                        { "45678901", "Jorge Luis", "Mendoza Castro", "Administrativo", "Soporte TI", "945678901", "jorge.mendoza@institucion.edu" },
                        { "76543210", "Ana Lucía", "Pérez Rojas", "Estudiante", "Administración", "976543210", "ana.perez@gmail.com" },
                        { "41238945", "Roberto", "Díaz Morales", "Docente", "Contabilidad", "981234567", "roberto.diaz@institucion.edu" }
                },
                new String[] {
                        "DNI", "Nombres", "Apellidos", "Tipo", "Área/Carrera", "Teléfono", "Correo"
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
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
    }
}