package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

import Dao.EquipoDAO;
import Dao.PrestamoDao;
import Modelo.Equipo;
import Modelo.Prestamo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Dao.SolicitanteDao;
import Modelo.Solicitante;

public class PanelPrestamos extends JPanel {

    private static final long serialVersionUID = 1L;

    // ATRIBUTOS ==============================================================
    private JTextField txtDniSolicitante;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtArea;
    private JTextField txtCodigoEquipo;
    private JTextField txtDescripcion;
    private JTextField txtMarcaModelo;
    private JTextField txtEstadoActual;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;
    private JTextField txtObservaciones;
    private JButton btnBuscarSolicitante;
    private JButton btnBuscarEquipo;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JButton btnActualizarTabla;
    private JTable table;
    private DefaultTableModel modelo;
    private int idSolicitanteSeleccionado;
    private int idEquipoSeleccionado;

    public PanelPrestamos() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelTitulo = new JPanel();
    	panelTitulo.setBackground(Color.WHITE);
    	panelTitulo.setPreferredSize(new Dimension(0, 50));
    	panelTitulo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
    	panelTitulo.setLayout(new BorderLayout());
    	add(panelTitulo, BorderLayout.NORTH);

    	JLabel lblTitulo = new JLabel("Registro de Préstamo");
    	lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
    	lblTitulo.setForeground(new Color(20, 20, 20));
    	lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
    	panelTitulo.add(lblTitulo, BorderLayout.CENTER);
    	
    	JPanel panelPrincipal = new JPanel();
    	add(panelPrincipal, BorderLayout.CENTER);
    	panelPrincipal.setLayout(new BorderLayout(0, 16));
    	panelPrincipal.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    	panelPrincipal.setBackground(new Color(245, 247, 250));
    	
    	JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPrincipal, BorderLayout.CENTER);
    	
    	JPanel panelRegistro = new JPanel();
    	panelPrincipal.add(panelRegistro, BorderLayout.NORTH);
    	panelRegistro.setLayout(new BorderLayout(0, 14));
    	panelRegistro.setBackground(new Color(245, 247, 250));
    	
    	JPanel panelSuperior = new JPanel();
    	panelSuperior.setOpaque(false);
    	panelSuperior.setLayout(new GridLayout(1, 2, 16, 0));
    	panelRegistro.add(panelSuperior, BorderLayout.CENTER);
    	
    	JPanel panelBuscarSolicitante = new JPanel();
    	panelBuscarSolicitante.setLayout(new GridBagLayout());
    	panelBuscarSolicitante.setBackground(Color.WHITE);
    	panelBuscarSolicitante.setBorder(BorderFactory.createCompoundBorder(
    	        BorderFactory.createLineBorder(new Color(210, 215, 220)),
    	        BorderFactory.createEmptyBorder(12, 12, 12, 12)));

    	JPanel panelBuscarEquipo = new JPanel();
    	panelBuscarEquipo.setLayout(new GridBagLayout());
    	panelBuscarEquipo.setBackground(Color.WHITE);
    	panelBuscarEquipo.setBorder(BorderFactory.createCompoundBorder(
    	        BorderFactory.createLineBorder(new Color(210, 215, 220)),
    	        BorderFactory.createEmptyBorder(12, 12, 12, 12)));

    	panelSuperior.add(panelBuscarSolicitante);
    	panelSuperior.add(panelBuscarEquipo);
    	
    	// BUSCAR SOLICITANTE =================================================
    	JLabel lblBuscarSolicitante = new JLabel("Buscar Solicitante");
    	lblBuscarSolicitante.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	lblBuscarSolicitante.setForeground(new Color(20, 20, 20));
    	GridBagConstraints gbcLblBuscarSolicitante = new GridBagConstraints();
    	gbcLblBuscarSolicitante.gridx = 0; gbcLblBuscarSolicitante.gridy = 0;
    	gbcLblBuscarSolicitante.gridwidth = 3;
    	gbcLblBuscarSolicitante.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblBuscarSolicitante.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lblBuscarSolicitante, gbcLblBuscarSolicitante);

    	JLabel lineaSolicitante = new JLabel();
    	lineaSolicitante.setBorder(new MatteBorder(0, 0, 1, 0, new Color(80, 120, 200)));
    	GridBagConstraints gbcLineaSolicitante = new GridBagConstraints();
    	gbcLineaSolicitante.gridx = 0; gbcLineaSolicitante.gridy = 1;
    	gbcLineaSolicitante.gridwidth = 3;
    	gbcLineaSolicitante.fill = GridBagConstraints.HORIZONTAL;
    	gbcLineaSolicitante.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lineaSolicitante, gbcLineaSolicitante);

    	JLabel lblDniSolicitante = new JLabel("DNI Solicitante");
    	lblDniSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblDni = new GridBagConstraints();
    	gbcLblDni.gridx = 0; gbcLblDni.gridy = 2; gbcLblDni.gridwidth = 3;
    	gbcLblDni.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblDni.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lblDniSolicitante, gbcLblDni);

    	// SIN TIPO — ya es atributo
    	txtDniSolicitante = new JTextField("Ingrese DNI");
    	txtDniSolicitante.setPreferredSize(new Dimension(0, 32));
    	txtDniSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtDniSolicitante.setForeground(Color.GRAY); 
    	GridBagConstraints gbcTxtDni = new GridBagConstraints();
    	gbcTxtDni.gridx = 0; gbcTxtDni.gridy = 3; gbcTxtDni.gridwidth = 2;
    	gbcTxtDni.weightx = 1; gbcTxtDni.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtDni.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(txtDniSolicitante, gbcTxtDni);

    	btnBuscarSolicitante = new JButton("Buscar");
    	btnBuscarSolicitante.setPreferredSize(new Dimension(100, 32));
    	btnBuscarSolicitante.setBackground(new Color(25, 118, 210));
    	btnBuscarSolicitante.setForeground(Color.WHITE);
    	btnBuscarSolicitante.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	btnBuscarSolicitante.setFocusPainted(false);
    	btnBuscarSolicitante.setBorderPainted(false);
    	GridBagConstraints gbcBtnBuscarSolicitante = new GridBagConstraints();
    	gbcBtnBuscarSolicitante.gridx = 2; gbcBtnBuscarSolicitante.gridy = 3;
    	gbcBtnBuscarSolicitante.gridwidth = 1;
    	gbcBtnBuscarSolicitante.fill = GridBagConstraints.HORIZONTAL;
    	gbcBtnBuscarSolicitante.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(btnBuscarSolicitante, gbcBtnBuscarSolicitante);

    	JLabel lblNombres = new JLabel("Nombres");
    	lblNombres.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblNombres = new GridBagConstraints();
    	gbcLblNombres.gridx = 0; gbcLblNombres.gridy = 4; gbcLblNombres.gridwidth = 1;
    	gbcLblNombres.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblNombres.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lblNombres, gbcLblNombres);

    	JLabel lblApellidos = new JLabel("Apellidos");
    	lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblApellidos = new GridBagConstraints();
    	gbcLblApellidos.gridx = 1; gbcLblApellidos.gridy = 4; gbcLblApellidos.gridwidth = 2;
    	gbcLblApellidos.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblApellidos.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lblApellidos, gbcLblApellidos);

    	txtNombres = new JTextField();
    	txtNombres.setPreferredSize(new Dimension(0, 32));
    	txtNombres.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcTxtNombres = new GridBagConstraints();
    	gbcTxtNombres.gridx = 0; gbcTxtNombres.gridy = 5; gbcTxtNombres.gridwidth = 1;
    	gbcTxtNombres.weightx = 1; gbcTxtNombres.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtNombres.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(txtNombres, gbcTxtNombres);

    	txtApellidos = new JTextField();
    	txtApellidos.setPreferredSize(new Dimension(0, 32));
    	txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcTxtApellidos = new GridBagConstraints();
    	gbcTxtApellidos.gridx = 1; gbcTxtApellidos.gridy = 5; gbcTxtApellidos.gridwidth = 2;
    	gbcTxtApellidos.weightx = 1; gbcTxtApellidos.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtApellidos.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(txtApellidos, gbcTxtApellidos);

    	JLabel lblArea = new JLabel("Área/Departamento");
    	lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblArea = new GridBagConstraints();
    	gbcLblArea.gridx = 0; gbcLblArea.gridy = 6; gbcLblArea.gridwidth = 3;
    	gbcLblArea.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblArea.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(lblArea, gbcLblArea);

    	txtArea = new JTextField();
    	txtArea.setPreferredSize(new Dimension(0, 32));
    	txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcTxtArea = new GridBagConstraints();
    	gbcTxtArea.gridx = 0; gbcTxtArea.gridy = 7; gbcTxtArea.gridwidth = 3;
    	gbcTxtArea.weightx = 1; gbcTxtArea.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtArea.insets = new Insets(5, 5, 5, 5);
    	panelBuscarSolicitante.add(txtArea, gbcTxtArea);
    	
    	// BUSCAR EQUIPO ======================================================
    	JLabel lblBuscarEquipo = new JLabel("Buscar Equipo");
    	lblBuscarEquipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	lblBuscarEquipo.setForeground(new Color(20, 20, 20));
    	GridBagConstraints gbcLblBuscarEquipo = new GridBagConstraints();
    	gbcLblBuscarEquipo.gridx = 0; gbcLblBuscarEquipo.gridy = 0; gbcLblBuscarEquipo.gridwidth = 3;
    	gbcLblBuscarEquipo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblBuscarEquipo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblBuscarEquipo, gbcLblBuscarEquipo);

    	JLabel lineaEquipo = new JLabel();
    	lineaEquipo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(80, 120, 200)));
    	GridBagConstraints gbcLineaEquipo = new GridBagConstraints();
    	gbcLineaEquipo.gridx = 0; gbcLineaEquipo.gridy = 1; gbcLineaEquipo.gridwidth = 3;
    	gbcLineaEquipo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLineaEquipo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lineaEquipo, gbcLineaEquipo);

    	JLabel lblCodigoEquipo = new JLabel("Código Equipo");
    	lblCodigoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblCodigoEquipo = new GridBagConstraints();
    	gbcLblCodigoEquipo.gridx = 0; gbcLblCodigoEquipo.gridy = 2; gbcLblCodigoEquipo.gridwidth = 3;
    	gbcLblCodigoEquipo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblCodigoEquipo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblCodigoEquipo, gbcLblCodigoEquipo);

    	txtCodigoEquipo = new JTextField("Ingreso Código");
    	txtCodigoEquipo.setPreferredSize(new Dimension(0, 32));
    	txtCodigoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtCodigoEquipo.setForeground(Color.GRAY); 
    	txtCodigoEquipo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtCodigoEquipo = new GridBagConstraints();
    	gbcTxtCodigoEquipo.gridx = 0; gbcTxtCodigoEquipo.gridy = 3; gbcTxtCodigoEquipo.gridwidth = 2;
    	gbcTxtCodigoEquipo.weightx = 1; gbcTxtCodigoEquipo.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtCodigoEquipo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(txtCodigoEquipo, gbcTxtCodigoEquipo);

    	btnBuscarEquipo = new JButton("Buscar");
    	btnBuscarEquipo.setPreferredSize(new Dimension(100, 32));
    	btnBuscarEquipo.setBackground(new Color(25, 118, 210));
    	btnBuscarEquipo.setForeground(Color.WHITE);
    	btnBuscarEquipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	btnBuscarEquipo.setFocusPainted(false);
    	btnBuscarEquipo.setBorderPainted(false);
    	GridBagConstraints gbcBtnBuscarEquipo = new GridBagConstraints();
    	gbcBtnBuscarEquipo.gridx = 2; gbcBtnBuscarEquipo.gridy = 3; gbcBtnBuscarEquipo.gridwidth = 1;
    	gbcBtnBuscarEquipo.fill = GridBagConstraints.HORIZONTAL;
    	gbcBtnBuscarEquipo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(btnBuscarEquipo, gbcBtnBuscarEquipo);

    	JLabel lblDescripcion = new JLabel("Descripción");
    	lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblDescripcion = new GridBagConstraints();
    	gbcLblDescripcion.gridx = 0; gbcLblDescripcion.gridy = 4; gbcLblDescripcion.gridwidth = 3;
    	gbcLblDescripcion.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblDescripcion.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblDescripcion, gbcLblDescripcion);

    	txtDescripcion = new JTextField();
    	txtDescripcion.setPreferredSize(new Dimension(0, 32));
    	txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtDescripcion.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtDescripcion = new GridBagConstraints();
    	gbcTxtDescripcion.gridx = 0; gbcTxtDescripcion.gridy = 5; gbcTxtDescripcion.gridwidth = 3;
    	gbcTxtDescripcion.weightx = 1; gbcTxtDescripcion.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtDescripcion.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(txtDescripcion, gbcTxtDescripcion);

    	JLabel lblMarcaModelo = new JLabel("Marca / Modelo");
    	lblMarcaModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblMarcaModelo = new GridBagConstraints();
    	gbcLblMarcaModelo.gridx = 0; gbcLblMarcaModelo.gridy = 6; gbcLblMarcaModelo.gridwidth = 1;
    	gbcLblMarcaModelo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblMarcaModelo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblMarcaModelo, gbcLblMarcaModelo);

    	JLabel lblEstadoActual = new JLabel("Estado Actual");
    	lblEstadoActual.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblEstadoActual = new GridBagConstraints();
    	gbcLblEstadoActual.gridx = 1; gbcLblEstadoActual.gridy = 6; gbcLblEstadoActual.gridwidth = 2;
    	gbcLblEstadoActual.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblEstadoActual.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblEstadoActual, gbcLblEstadoActual);

    	txtMarcaModelo = new JTextField();
    	txtMarcaModelo.setPreferredSize(new Dimension(0, 32));
    	txtMarcaModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtMarcaModelo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtMarcaModelo = new GridBagConstraints();
    	gbcTxtMarcaModelo.gridx = 0; gbcTxtMarcaModelo.gridy = 7; gbcTxtMarcaModelo.gridwidth = 1;
    	gbcTxtMarcaModelo.weightx = 1; gbcTxtMarcaModelo.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtMarcaModelo.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(txtMarcaModelo, gbcTxtMarcaModelo);

    	txtEstadoActual = new JTextField();
    	txtEstadoActual.setPreferredSize(new Dimension(0, 32));
    	txtEstadoActual.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	txtEstadoActual.setForeground(new Color(25, 118, 210));
    	txtEstadoActual.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtEstadoActual = new GridBagConstraints();
    	gbcTxtEstadoActual.gridx = 1; gbcTxtEstadoActual.gridy = 7; gbcTxtEstadoActual.gridwidth = 2;
    	gbcTxtEstadoActual.weightx = 1; gbcTxtEstadoActual.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtEstadoActual.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(txtEstadoActual, gbcTxtEstadoActual);

    	JLabel lblNota = new JLabel("* Solo equipos disponibles pueden prestarse.");
    	lblNota.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    	lblNota.setForeground(new Color(90, 90, 90));
    	GridBagConstraints gbcLblNota = new GridBagConstraints();
    	gbcLblNota.gridx = 1; gbcLblNota.gridy = 8; gbcLblNota.gridwidth = 2;
    	gbcLblNota.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblNota.insets = new Insets(5, 5, 5, 5);
    	panelBuscarEquipo.add(lblNota, gbcLblNota);
        
    	// DETALLE DEL PRÉSTAMO ===============================================
    	JPanel panelDetalle = new JPanel();
    	panelDetalle.setLayout(new GridBagLayout());
    	panelDetalle.setBackground(Color.WHITE);
    	panelDetalle.setBorder(BorderFactory.createCompoundBorder(
    	        BorderFactory.createLineBorder(new Color(210, 215, 220)),
    	        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
    	panelRegistro.add(panelDetalle, BorderLayout.SOUTH);
    	
    	JLabel lblDetallePrestamo = new JLabel("Detalle del Préstamo");
    	lblDetallePrestamo.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	lblDetallePrestamo.setForeground(new Color(20, 20, 20));
    	GridBagConstraints gbcLblDetallePrestamo = new GridBagConstraints();
    	gbcLblDetallePrestamo.gridx = 0; gbcLblDetallePrestamo.gridy = 0; gbcLblDetallePrestamo.gridwidth = 6;
    	gbcLblDetallePrestamo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblDetallePrestamo.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(lblDetallePrestamo, gbcLblDetallePrestamo);
    	
    	JLabel lineaDetalle = new JLabel();
    	lineaDetalle.setBorder(new MatteBorder(0, 0, 1, 0, new Color(80, 120, 200)));
    	GridBagConstraints gbcLineaDetalle = new GridBagConstraints();
    	gbcLineaDetalle.gridx = 0; gbcLineaDetalle.gridy = 1; gbcLineaDetalle.gridwidth = 6;
    	gbcLineaDetalle.fill = GridBagConstraints.HORIZONTAL;
    	gbcLineaDetalle.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(lineaDetalle, gbcLineaDetalle);
    	
    	JLabel lblFechaPrestamo = new JLabel("Fecha de Préstamo");
    	lblFechaPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblFechaPrestamo = new GridBagConstraints();
    	gbcLblFechaPrestamo.gridx = 0; gbcLblFechaPrestamo.gridy = 2; gbcLblFechaPrestamo.gridwidth = 1;
    	gbcLblFechaPrestamo.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblFechaPrestamo.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(lblFechaPrestamo, gbcLblFechaPrestamo);

    	JLabel lblFechaDevolucion = new JLabel("Fecha Prev. Devolución");
    	lblFechaDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblFechaDevolucion = new GridBagConstraints();
    	gbcLblFechaDevolucion.gridx = 1; gbcLblFechaDevolucion.gridy = 2; gbcLblFechaDevolucion.gridwidth = 1;
    	gbcLblFechaDevolucion.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblFechaDevolucion.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(lblFechaDevolucion, gbcLblFechaDevolucion);

    	JLabel lblObservaciones = new JLabel("Observaciones");
    	lblObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	GridBagConstraints gbcLblObservaciones = new GridBagConstraints();
    	gbcLblObservaciones.gridx = 2; gbcLblObservaciones.gridy = 2; gbcLblObservaciones.gridwidth = 2;
    	gbcLblObservaciones.fill = GridBagConstraints.HORIZONTAL;
    	gbcLblObservaciones.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(lblObservaciones, gbcLblObservaciones);
    	
    	txtFechaPrestamo = new JTextField("dd/mm/aaaa");
    	txtFechaPrestamo.setPreferredSize(new Dimension(0, 32));
    	txtFechaPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtFechaPrestamo.setForeground(Color.GRAY); 
    	txtFechaPrestamo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtFechaPrestamo = new GridBagConstraints();
    	gbcTxtFechaPrestamo.gridx = 0; gbcTxtFechaPrestamo.gridy = 3; gbcTxtFechaPrestamo.gridwidth = 1;
    	gbcTxtFechaPrestamo.weightx = 1; gbcTxtFechaPrestamo.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtFechaPrestamo.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(txtFechaPrestamo, gbcTxtFechaPrestamo);

    	txtFechaDevolucion = new JTextField("dd/mm/aaaa");
    	txtFechaDevolucion.setPreferredSize(new Dimension(0, 32));
    	txtFechaDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtFechaDevolucion.setForeground(Color.GRAY);
    	txtFechaDevolucion.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtFechaDevolucion = new GridBagConstraints();
    	gbcTxtFechaDevolucion.gridx = 1; gbcTxtFechaDevolucion.gridy = 3; gbcTxtFechaDevolucion.gridwidth = 1;
    	gbcTxtFechaDevolucion.weightx = 1; gbcTxtFechaDevolucion.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtFechaDevolucion.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(txtFechaDevolucion, gbcTxtFechaDevolucion);

    	txtObservaciones = new JTextField();
    	txtObservaciones.setPreferredSize(new Dimension(0, 32));
    	txtObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	txtObservaciones.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));
    	GridBagConstraints gbcTxtObservaciones = new GridBagConstraints();
    	gbcTxtObservaciones.gridx = 2; gbcTxtObservaciones.gridy = 3; gbcTxtObservaciones.gridwidth = 2;
    	gbcTxtObservaciones.weightx = 2; gbcTxtObservaciones.fill = GridBagConstraints.HORIZONTAL;
    	gbcTxtObservaciones.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(txtObservaciones, gbcTxtObservaciones);
    	
    	btnCancelar = new JButton("Cancelar");
    	btnCancelar.setPreferredSize(new Dimension(110, 32));
    	btnCancelar.setBackground(new Color(210, 210, 210));
    	btnCancelar.setForeground(Color.BLACK);
    	btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	btnCancelar.setFocusPainted(false);
    	btnCancelar.setBorderPainted(false);
    	GridBagConstraints gbcBtnCancelar = new GridBagConstraints();
    	gbcBtnCancelar.gridx = 4; gbcBtnCancelar.gridy = 3; gbcBtnCancelar.gridwidth = 1;
    	gbcBtnCancelar.fill = GridBagConstraints.HORIZONTAL;
    	gbcBtnCancelar.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(btnCancelar, gbcBtnCancelar);

    	btnRegistrar = new JButton("Registrar préstamo");
    	btnRegistrar.setPreferredSize(new Dimension(150, 32));
    	btnRegistrar.setBackground(new Color(25, 118, 210));
    	btnRegistrar.setForeground(Color.WHITE);
    	btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	btnRegistrar.setFocusPainted(false);
    	btnRegistrar.setBorderPainted(false);
    	GridBagConstraints gbcBtnRegistrar = new GridBagConstraints();
    	gbcBtnRegistrar.gridx = 5; gbcBtnRegistrar.gridy = 3; gbcBtnRegistrar.gridwidth = 1;
    	gbcBtnRegistrar.fill = GridBagConstraints.HORIZONTAL;
    	gbcBtnRegistrar.insets = new Insets(5, 5, 5, 5);
    	panelDetalle.add(btnRegistrar, gbcBtnRegistrar);
    	
    	// TABLA ==============================================================
    	JPanel panelTabla = new JPanel();
    	panelTabla.setLayout(new BorderLayout());
    	panelTabla.setBackground(Color.WHITE);
    	panelTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));
    	panelPrincipal.add(panelTabla, BorderLayout.CENTER);
    	
    	JPanel panelTituloTabla = new JPanel();
    	panelTituloTabla.setLayout(new BorderLayout());
    	panelTituloTabla.setBackground(new Color(38, 50, 56));
    	panelTituloTabla.setPreferredSize(new Dimension(0, 38));
    	panelTituloTabla.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
    	panelTabla.add(panelTituloTabla, BorderLayout.NORTH);

    	JLabel lblTituloTabla = new JLabel("Préstamos Activos Recientes");
    	lblTituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	lblTituloTabla.setForeground(Color.WHITE);
    	panelTituloTabla.add(lblTituloTabla, BorderLayout.WEST);

    	btnActualizarTabla = new JButton("⟳");
    	btnActualizarTabla.setForeground(Color.WHITE);
    	btnActualizarTabla.setBackground(new Color(38, 50, 56));
    	btnActualizarTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
    	btnActualizarTabla.setFocusPainted(false);
    	btnActualizarTabla.setBorderPainted(false);
    	panelTituloTabla.add(btnActualizarTabla, BorderLayout.EAST);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    	panelTabla.add(scrollPane, BorderLayout.CENTER);

    	table = new JTable();
    	modelo = new DefaultTableModel(
    	        new Object[][] {},
    	        new String[] {"ID", "Solicitante", "Equipo", "F. Préstamo", "F. Prev. Dev.", "Estado"}
    	) {
    	    private static final long serialVersionUID = 1L;
    	    @Override
    	    public boolean isCellEditable(int row, int column) { return false; }
    	};
    	table.setModel(modelo);
    	scrollPane.setViewportView(table);
    	
    	//ESTILOS TABLA ===========================================================================
    	table.setRowHeight(36);
    	table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	table.setForeground(Color.BLACK);
    	table.setGridColor(new Color(230, 230, 230));
    	table.setSelectionBackground(new Color(187, 222, 251));
    	table.setSelectionForeground(Color.BLACK);
    	table.setShowVerticalLines(true);
    	table.setShowHorizontalLines(true);
    	table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    	table.getTableHeader().setBackground(new Color(245, 245, 245));
    	table.getTableHeader().setForeground(new Color(40, 40, 40));
    	table.getTableHeader().setPreferredSize(new Dimension(0, 34));
    	table.getTableHeader().setReorderingAllowed(false);
    	
    	//MEDIDAS DE LA TABLA ===========================================================
    	table.getColumnModel().getColumn(0).setPreferredWidth(80);
    	table.getColumnModel().getColumn(1).setPreferredWidth(220);
    	table.getColumnModel().getColumn(2).setPreferredWidth(260);
    	table.getColumnModel().getColumn(3).setPreferredWidth(120);
    	table.getColumnModel().getColumn(4).setPreferredWidth(130);
    	table.getColumnModel().getColumn(5).setPreferredWidth(100);
    	
    	// EVENTOS DE BOTONES =================================================
    	configurarEventos();
    	// PLACEHOLDER txtDniSolicitante
    	txtDniSolicitante.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent e) {
    	        if (txtDniSolicitante.getText().equals("Ingrese DNI")) {
    	            txtDniSolicitante.setText("");
    	            txtDniSolicitante.setForeground(Color.BLACK);
    	        }
    	    }
    	    public void focusLost(java.awt.event.FocusEvent e) {
    	        if (txtDniSolicitante.getText().isEmpty()) {
    	            txtDniSolicitante.setText("Ingrese DNI");
    	            txtDniSolicitante.setForeground(Color.GRAY);
    	        }
    	    }
    	});
    	
    	// PLACEHOLDER txtCodigoEquipo
    	txtCodigoEquipo .addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent e) {
    	        if (txtCodigoEquipo .getText().equals("Ingrese Código")) {
    	        	txtCodigoEquipo .setText("");
    	        	txtCodigoEquipo .setForeground(Color.BLACK);
    	        }
    	    }
    	    public void focusLost(java.awt.event.FocusEvent e) {
    	        if (txtCodigoEquipo .getText().isEmpty()) {
    	        	txtCodigoEquipo .setText("Ingrese Código");
    	        	txtCodigoEquipo .setForeground(Color.GRAY);
    	        }
    	    }
    	});

    	// PLACEHOLDER txtFechaPrestamo
    	txtFechaPrestamo.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent e) {
    	        if (txtFechaPrestamo.getText().equals("25/10/2023")) {
    	            txtFechaPrestamo.setText("");
    	            txtFechaPrestamo.setForeground(Color.BLACK);
    	        }
    	    }
    	    public void focusLost(java.awt.event.FocusEvent e) {
    	        if (txtFechaPrestamo.getText().isEmpty()) {
    	            txtFechaPrestamo.setText("25/10/2023");
    	            txtFechaPrestamo.setForeground(Color.GRAY);
    	        }
    	    }
    	});

    	// PLACEHOLDER txtFechaDevolucion
    	txtFechaDevolucion.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent e) {
    	        if (txtFechaDevolucion.getText().equals("dd/mm/aaaa")) {
    	            txtFechaDevolucion.setText("");
    	            txtFechaDevolucion.setForeground(Color.BLACK);
    	        }
    	    }
    	    public void focusLost(java.awt.event.FocusEvent e) {
    	        if (txtFechaDevolucion.getText().isEmpty()) {
    	            txtFechaDevolucion.setText("dd/mm/aaaa");
    	            txtFechaDevolucion.setForeground(Color.GRAY);
    	        }
    	    }
    	});
    }
    
    private void configurarEventos() {

        //BOTÓN CANCELAR — LIMPIA CAMPOS =====================================
        btnCancelar.addActionListener(e -> {
			        txtDniSolicitante.setText("Ingrese DNI");
			        txtNombres.setText("");
			        txtApellidos.setText("");
			        txtArea.setText("");
			        txtCodigoEquipo.setText("");
			        txtDescripcion.setText("");
			        txtMarcaModelo.setText("");
			        txtEstadoActual.setText("");
			        txtFechaPrestamo.setText("");
			        txtFechaDevolucion.setText("");
			        txtObservaciones.setText("");
			        idSolicitanteSeleccionado = 0;
			        idEquipoSeleccionado = 0;
			        });

        //BOTÓN BUSCAR EQUIPO =================================================
        btnBuscarEquipo.addActionListener(e -> {
			            String codigo = txtCodigoEquipo.getText().trim();
			            if (codigo.isEmpty()) {
			                JOptionPane.showMessageDialog(this, 
			                    "Ingrese un código de equipo", 
			                    "Aviso", JOptionPane.WARNING_MESSAGE);
			            return;}
			            
			            EquipoDAO equipoDAO = new EquipoDAO();
			            ArrayList<Equipo> lista = equipoDAO.buscarPorNombreOCodigo(codigo);
			            if (lista.isEmpty()) {
			                JOptionPane.showMessageDialog(this, 
			                    "No se encontró ningún equipo", 
			                    "Aviso", JOptionPane.WARNING_MESSAGE);}
			            else {
			                Equipo eq = lista.get(0);
			                if (!eq.getEstado().equals("Disponible")) {
			                    JOptionPane.showMessageDialog(this, 
			                        "El equipo no está disponible", 
			                        "Aviso", JOptionPane.WARNING_MESSAGE);
			                return;}
			                
			                idEquipoSeleccionado = eq.getIdEquipo();
			                txtDescripcion.setText(eq.getNombre());
			                txtMarcaModelo.setText(eq.getMarca() + " / " + eq.getModelo());
			                txtEstadoActual.setText(eq.getEstado());
			            }});

        //BOTÓN ACTUALIZAR TABLA ==============================================
        btnActualizarTabla.addActionListener(e -> {cargarTabla();});

        //BOTÓN REGISTRAR PRÉSTAMO ============================================
        btnRegistrar.addActionListener(e -> {
            
		        	//VALIDACIONES
		            if (idSolicitanteSeleccionado == 0) {JOptionPane.showMessageDialog(this, 
		                "Debe seleccionar un solicitante", 
		                "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;}
		            
		            if (idEquipoSeleccionado == 0) {JOptionPane.showMessageDialog(this, 
		                "Debe seleccionar un equipo", 
		                "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;}
		            
		            if (txtFechaPrestamo.getText().trim().isEmpty()) {JOptionPane.showMessageDialog(this, 
		                "Ingrese la fecha de préstamo", 
		                "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;}
		            
		            if (txtFechaDevolucion.getText().trim().isEmpty() || 
		                txtFechaDevolucion.getText().equals("dd/mm/aaaa")) {JOptionPane.showMessageDialog(this, 
		                "Ingrese la fecha de devolución", 
		                "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;}
            
            try{DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //CONVERTIR FECHAS ---------------
                LocalDate fechaPrestamo = LocalDate.parse(txtFechaPrestamo.getText().trim(), formatter);
                LocalDate fechaDevolucion = LocalDate.parse(txtFechaDevolucion.getText().trim(), formatter);

                //CREAR OBJETO PRESTAMO
                Prestamo prestamo = new Prestamo();
                prestamo.setIdEquipo(idEquipoSeleccionado);
                prestamo.setIdSolicitante(idSolicitanteSeleccionado);
                prestamo.setFechaPrestamo(fechaPrestamo);
                prestamo.setHoraPrestamo(LocalTime.now());
                prestamo.setFechaDevolucionPrevista(fechaDevolucion);
                prestamo.setObservaciones(txtObservaciones.getText());
                prestamo.setEstado("Activo");

                //GUARDAR EN BASE DE DATOS ===============================================
                PrestamoDao dao = new PrestamoDao();
                
                		if (dao.guardar(prestamo))
                			{ EquipoDAO equipoDAO = new EquipoDAO();
                			  equipoDAO.actualizarEstado(idEquipoSeleccionado, "Prestado"); //CAMBIAR EL ESTADO DEL EQUIPO A PRESTADO                  
		                      JOptionPane.showMessageDialog(this, "Préstamo registrado correctamente", "Éxito",
		                      JOptionPane.INFORMATION_MESSAGE);
		                      btnCancelar.doClick(); //LIMPIA CAMPOS
		                      cargarTabla();} //ACTUALIZA TABLA
                      
		                else {JOptionPane.showMessageDialog(this, "Error al registrar el préstamo", "Error", JOptionPane.ERROR_MESSAGE);}}
            
            catch (Exception ex) {JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/mm/aaaa", "Error", JOptionPane.ERROR_MESSAGE);}});
         
        //BOTÓN BUSCAR SOLICITANTE ============================================
        btnBuscarSolicitante.addActionListener(e -> {
            String dni = txtDniSolicitante.getText().trim();
            if (dni.isEmpty() || dni.equals("Ingrese DNI")) {
                JOptionPane.showMessageDialog(this,
                    "Ingrese un DNI para buscar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SolicitanteDao solicitanteDAO = new SolicitanteDao();
            Solicitante s = solicitanteDAO.buscarPorDni(dni);
            if (s == null) {
                JOptionPane.showMessageDialog(this,
                    "No se encontró ningún solicitante con ese DNI",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                idSolicitanteSeleccionado = s.getIdSolicitante();
                txtNombres.setText(s.getNombre());
                txtApellidos.setText(s.getApellidos());
                txtArea.setText(s.getTipo());
            }});
    }
    
    

  //CARGAR TABLA ============================================================
    private void cargarTabla()
    	{modelo.setRowCount(0);
    	 PrestamoDao prestamoDAO = new PrestamoDao();
    	 EquipoDAO equipoDAO = new EquipoDAO();
    	 SolicitanteDao solicitanteDAO = new SolicitanteDao();
    	 
    	 ArrayList<Equipo> equipos = equipoDAO.listar();
    	 ArrayList<Solicitante> solicitantes = solicitanteDAO.listar();
    	 ArrayList<Prestamo> prestamos = prestamoDAO.listar();
    	 
    	 for (Prestamo p : prestamos)
    		 //BUSCAR NOMBRE DE EQUIPO ---------------------------------------------------------------
    	 	 {String codigoEquipo = String.valueOf(p.getIdEquipo());
    	       		 for (Equipo eq : equipos) {
    	        		 if (eq.getIdEquipo() == p.getIdEquipo()) {
    	        			 codigoEquipo = eq.getCodigo() + " - " + eq.getNombre();
    	        		 break;}}
    	        						  
    	      //BUSCAR NOMBRE DE SOLICITANTE ---------------------------------------------------------------   						  
    	      String nombreSolicitante = String.valueOf(p.getIdSolicitante());
    	             for (Solicitante s : solicitantes) {
    	                  if (s.getIdSolicitante() == p.getIdSolicitante()) {
    	                      nombreSolicitante = s.getNombre() + " " + s.getApellidos();
    	                  break;}}					  
    	       		 
			  modelo.addRow(new Object[]{
				     p.getIdPrestamo(),
				     nombreSolicitante,
				     codigoEquipo,
				     p.getFechaPrestamo(),
				     p.getFechaDevolucionPrevista(),
				     p.getEstado()});
			  }
    	}
    
    public void actualizarTabla() {
        cargarTabla();
    }
    
}