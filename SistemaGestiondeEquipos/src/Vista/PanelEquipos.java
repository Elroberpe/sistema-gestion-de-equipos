package Vista;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

import Dao.EquipoDAO;
import Modelo.Equipo;

public class PanelEquipos extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo, txtNombre, txtMarca, txtModelo, txtSerie, txtBuscar;
    private JComboBox<String> cboTipo, cboEstado;
    private JTable table;
    private DefaultTableModel modelo;
    private EquipoDAO equipoDAO = new EquipoDAO();
    private int idEquipoSeleccionado = -1;

    public PanelEquipos() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // PANEL TÍTULO
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setPreferredSize(new Dimension(0, 50));
        panelTitulo.setLayout(new BorderLayout());
        add(panelTitulo, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Gestión de Equipos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(20, 20, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // PANEL PRINCIPAL
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

        // PANEL FORMULARIO
        JPanel panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(310, 590));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));
        panelFormulario.setLayout(null);
        panelPrincipal.add(panelFormulario, BorderLayout.WEST);

        JLabel lblDetalle = new JLabel("Detalle del Equipo");
        lblDetalle.setBounds(16, 12, 250, 25);
        lblDetalle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDetalle.setForeground(new Color(55, 65, 81));
        panelFormulario.add(lblDetalle);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 45, 310, 2);
        panelFormulario.add(separator);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(16, 62, 120, 20);
        lblCodigo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(16, 85, 278, 32);
        txtCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCodigo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(16, 130, 120, 20);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(16, 153, 278, 32);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtNombre);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(16, 198, 120, 20);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblTipo);

        cboTipo = new JComboBox<>();
        cboTipo.setModel(new DefaultComboBoxModel<>(
                new String[]{"Laptop", "Monitor", "Proyector", "Accesorio", "Impresora"}));
        cboTipo.setBounds(16, 221, 278, 32);
        cboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(cboTipo);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(16, 266, 120, 20);
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(16, 289, 132, 32);
        txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtMarca.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtMarca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(162, 266, 120, 20);
        lblModelo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setBounds(162, 289, 132, 32);
        txtModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtModelo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtModelo);

        JLabel lblSerie = new JLabel("Número de Serie");
        lblSerie.setBounds(16, 334, 150, 20);
        lblSerie.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblSerie);

        txtSerie = new JTextField();
        txtSerie.setBounds(16, 357, 278, 32);
        txtSerie.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtSerie.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelFormulario.add(txtSerie);

        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setBounds(16, 402, 120, 20);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFormulario.add(lblEstado);

        cboEstado = new JComboBox<>();
        cboEstado.setModel(new DefaultComboBoxModel<>(
                new String[]{"Disponible", "Prestado", "Mantenimiento", "Baja"}));
        cboEstado.setBounds(16, 425, 278, 32);
        cboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelFormulario.add(cboEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(16, 485, 130, 35);
        btnGuardar.setBackground(new Color(25, 118, 210));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        panelFormulario.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(164, 485, 130, 35);
        btnLimpiar.setBackground(new Color(130, 130, 130));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        panelFormulario.add(btnLimpiar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(16, 532, 278, 35);
        btnEliminar.setBackground(new Color(211, 47, 47));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        panelFormulario.add(btnEliminar);

        // PANEL TABLA
        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));
        panelTabla.setLayout(new BorderLayout());
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // PANEL BUSCADOR
        JPanel panelBuscar = new JPanel();
        panelBuscar.setBackground(Color.WHITE);
        panelBuscar.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelBuscar.setLayout(new BorderLayout(10, 0));
        panelTabla.add(panelBuscar, BorderLayout.NORTH);

        txtBuscar = new JTextField();
        txtBuscar.setText("Buscar equipo...");
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setPreferredSize(new Dimension(250, 32));
        txtBuscar.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
        panelBuscar.add(txtBuscar, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel();
        panelAcciones.setOpaque(false);
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setPreferredSize(new Dimension(100, 32));
        btnFiltrar.setBackground(new Color(130, 130, 130));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.setBorderPainted(false);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setPreferredSize(new Dimension(100, 32));
        btnActualizar.setBackground(new Color(25, 118, 210));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);

        panelAcciones.add(btnFiltrar);
        panelAcciones.add(btnActualizar);
        panelBuscar.add(panelAcciones, BorderLayout.EAST);

        // TABLA
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Código", "Nombre", "Tipo", "Marca", "Modelo", "Serie", "Estado"}
        ) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(modelo);
        scrollPane.setViewportView(table);

        // Ocultar columna ID
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        table.setRowHeight(38);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(38, 50, 56));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));
        table.getTableHeader().setReorderingAllowed(false);

        // ACCIONES BOTONES
        btnGuardar.addActionListener(e -> guardarEquipo());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEliminar.addActionListener(e -> eliminarEquipo());
        btnFiltrar.addActionListener(e -> buscarEquipo());
        btnActualizar.addActionListener(e -> cargarTabla());

        // Seleccionar fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarFormulario();
        });

        cargarTabla();
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        ArrayList<Equipo> lista = equipoDAO.listar();
        for (Equipo eq : lista) {
            modelo.addRow(new Object[]{
                eq.getIdEquipo(), eq.getCodigo(), eq.getNombre(),
                eq.getTipo(), eq.getMarca(), eq.getModelo(),
                eq.getNumeroSerie(), eq.getEstado()
            });
        }
    }

    private void guardarEquipo() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String marca = txtMarca.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Código, Nombre y Marca son obligatorios.");
            return;
        }

        Equipo eq = new Equipo();
        eq.setCodigo(codigo);
        eq.setNombre(nombre);
        eq.setTipo(cboTipo.getSelectedItem().toString());
        eq.setMarca(marca);
        eq.setModelo(txtModelo.getText().trim());
        eq.setNumeroSerie(txtSerie.getText().trim());
        eq.setEstado(cboEstado.getSelectedItem().toString());

        if (idEquipoSeleccionado == -1) {
            if (equipoDAO.guardar(eq)) {
                JOptionPane.showMessageDialog(this, "Equipo guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar.");
            }
        } else {
            eq.setIdEquipo(idEquipoSeleccionado);
            if (equipoDAO.actualizar(eq)) {
                JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.");
            }
        }
        limpiarCampos();
        cargarTabla();
    }

    private void eliminarEquipo() {
        if (idEquipoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo de la tabla.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este equipo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (equipoDAO.eliminar(idEquipoSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Equipo eliminado.");
                limpiarCampos();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar. Tiene préstamos registrados.");
            }
        }
    }

    private void buscarEquipo() {
        String texto = txtBuscar.getText().trim();
        if (texto.equals("Buscar equipo...") || texto.isEmpty()) {
            cargarTabla();
            return;
        }
        modelo.setRowCount(0);
        ArrayList<Equipo> lista = equipoDAO.buscarPorNombreOCodigo(texto);
        for (Equipo eq : lista) {
            modelo.addRow(new Object[]{
                eq.getIdEquipo(), eq.getCodigo(), eq.getNombre(),
                eq.getTipo(), eq.getMarca(), eq.getModelo(),
                eq.getNumeroSerie(), eq.getEstado()
            });
        }
    }

    private void cargarFormulario() {
        int fila = table.getSelectedRow();
        if (fila == -1) return;
        idEquipoSeleccionado = (int) modelo.getValueAt(fila, 0);
        txtCodigo.setText(modelo.getValueAt(fila, 1).toString());
        txtNombre.setText(modelo.getValueAt(fila, 2).toString());
        cboTipo.setSelectedItem(modelo.getValueAt(fila, 3).toString());
        txtMarca.setText(modelo.getValueAt(fila, 4).toString());
        txtModelo.setText(modelo.getValueAt(fila, 5) != null ? modelo.getValueAt(fila, 5).toString() : "");
        txtSerie.setText(modelo.getValueAt(fila, 6) != null ? modelo.getValueAt(fila, 6).toString() : "");
        cboEstado.setSelectedItem(modelo.getValueAt(fila, 7).toString());
    }

    private void limpiarCampos() {
        idEquipoSeleccionado = -1;
        txtCodigo.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtSerie.setText("");
        cboTipo.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
        table.clearSelection();
    }
}