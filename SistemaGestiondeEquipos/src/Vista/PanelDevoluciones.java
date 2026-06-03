package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Controller.DevolucionController;
import DTO.PrestamoActivoDTO;
import DAO.DevolucionDao;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelDevoluciones extends JPanel {
	
	private JTable tablePrestamos;
	
	private JLabel lblValorSolicitante;
	private JLabel lblValorEquipo;
	private JLabel lblValorFechaPrestamo;
	private JLabel lblValorFechaLimite;
	private JLabel lblValorFechaActual;
	private JLabel lblValorEstadoDevolucion;
	
	private JTextField txtCodigoPrestamo;
	private JTextField txtDniSolicitante;
	private JTextField txtCodigoEquipo; 
	
	private JButton btnBuscar;
	
	private DevolucionController devolucionController;
	
    private static final long serialVersionUID = 1L;

    public PanelDevoluciones() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setPreferredSize(new Dimension(0, 50));
        panelTitulo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        panelTitulo.setLayout(new BorderLayout());
        add(panelTitulo, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Registro de Devolución");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(20, 20, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPrincipal, BorderLayout.CENTER);
        
        //panel de busqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new BorderLayout(0, 12));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        panelBusqueda.setAlignmentX(LEFT_ALIGNMENT);
        panelBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        panelPrincipal.add(panelBusqueda);
        panelPrincipal.add(Box.createVerticalStrut(16)); // GAP
        
        //contenido del panel de busqueda 
        JLabel lblBuscarPrestamo = new JLabel("Buscar Préstamo Activo");
        lblBuscarPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblBuscarPrestamo.setForeground(new Color(20, 20, 20));
        panelBusqueda.add(lblBuscarPrestamo, BorderLayout.NORTH);
        
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridBagLayout());
        panelFiltros.setBackground(Color.WHITE);
        panelBusqueda.add(panelFiltros, BorderLayout.CENTER);
        
        JLabel lblCodigoPrestamo = new JLabel("Código de Préstamo");
        lblCodigoPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcLblCodigoPrestamo = new GridBagConstraints();
        gbcLblCodigoPrestamo.gridx = 0;
        gbcLblCodigoPrestamo.gridy = 0;
        gbcLblCodigoPrestamo.fill = GridBagConstraints.HORIZONTAL;
        gbcLblCodigoPrestamo.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(lblCodigoPrestamo, gbcLblCodigoPrestamo);

        JLabel lblDniSolicitante = new JLabel("DNI del Solicitante");
        lblDniSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcLblDniSolicitante = new GridBagConstraints();
        gbcLblDniSolicitante.gridx = 1;
        gbcLblDniSolicitante.gridy = 0;
        gbcLblDniSolicitante.fill = GridBagConstraints.HORIZONTAL;
        gbcLblDniSolicitante.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(lblDniSolicitante, gbcLblDniSolicitante);

        JLabel lblCodigoEquipo = new JLabel("Código del Equipo");
        lblCodigoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcLblCodigoEquipo = new GridBagConstraints();
        gbcLblCodigoEquipo.gridx = 2;
        gbcLblCodigoEquipo.gridy = 0;
        gbcLblCodigoEquipo.fill = GridBagConstraints.HORIZONTAL;
        gbcLblCodigoEquipo.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(lblCodigoEquipo, gbcLblCodigoEquipo);
        
        txtCodigoPrestamo = new JTextField();
        txtCodigoPrestamo.setMinimumSize(new Dimension(0, 32));
        txtCodigoPrestamo.setPreferredSize(new Dimension(0, 32));
        txtCodigoPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCodigoPrestamo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));

        GridBagConstraints gbcTxtCodigoPrestamo = new GridBagConstraints();
        gbcTxtCodigoPrestamo.gridx = 0;
        gbcTxtCodigoPrestamo.gridy = 1;
        gbcTxtCodigoPrestamo.weightx = 1;
        gbcTxtCodigoPrestamo.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtCodigoPrestamo.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(txtCodigoPrestamo, gbcTxtCodigoPrestamo);

        txtDniSolicitante = new JTextField();
        txtDniSolicitante.setMinimumSize(new Dimension(0, 32));
        txtDniSolicitante.setPreferredSize(new Dimension(0, 32));
        txtDniSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDniSolicitante.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));

        GridBagConstraints gbcTxtDniSolicitante = new GridBagConstraints();
        gbcTxtDniSolicitante.gridx = 1;
        gbcTxtDniSolicitante.gridy = 1;
        gbcTxtDniSolicitante.weightx = 1;
        gbcTxtDniSolicitante.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtDniSolicitante.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(txtDniSolicitante, gbcTxtDniSolicitante);

        txtCodigoEquipo = new JTextField();
        txtCodigoEquipo.setMinimumSize(new Dimension(0, 32));
        txtCodigoEquipo.setPreferredSize(new Dimension(0, 32));
        txtCodigoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCodigoEquipo.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));

        GridBagConstraints gbcTxtCodigoEquipo = new GridBagConstraints();
        gbcTxtCodigoEquipo.gridx = 2;
        gbcTxtCodigoEquipo.gridy = 1;
        gbcTxtCodigoEquipo.weightx = 1;
        gbcTxtCodigoEquipo.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtCodigoEquipo.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(txtCodigoEquipo, gbcTxtCodigoEquipo);

        btnBuscar = new JButton("Buscar");
        
        btnBuscar.addActionListener(e -> {
            buscarPrestamo();
        });
        
        btnBuscar.setMaximumSize(new Dimension(65, 32));
        btnBuscar.setMinimumSize(new Dimension(65, 32));
        btnBuscar.setPreferredSize(new Dimension(120, 32));
        btnBuscar.setBackground(new Color(25, 118, 210));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);

        GridBagConstraints gbcBtnBuscar = new GridBagConstraints();
        gbcBtnBuscar.gridx = 3;
        gbcBtnBuscar.gridy = 1;
        gbcBtnBuscar.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnBuscar.insets = new Insets(2, 5, 2, 5);
        panelFiltros.add(btnBuscar, gbcBtnBuscar);
        
        //tabla de prestamos

        JScrollPane scrollPrestamos = new JScrollPane();
        scrollPrestamos.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPrestamos.setPreferredSize(new Dimension(0, 130));
        panelBusqueda.add(scrollPrestamos, BorderLayout.SOUTH);

        tablePrestamos = new JTable();

        DefaultTableModel modeloPrestamos = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "ID", "Solicitante","Dni", "Equipo", "Fecha Préstamo", "Fecha Límite"
                }
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePrestamos.setModel(modeloPrestamos);
        scrollPrestamos.setViewportView(tablePrestamos);
        listarPrestamosActivos();

        tablePrestamos.setRowHeight(34);
        tablePrestamos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablePrestamos.setForeground(Color.BLACK);
        tablePrestamos.setGridColor(new Color(230, 230, 230));
        tablePrestamos.setSelectionBackground(new Color(187, 222, 251));
        tablePrestamos.setSelectionForeground(Color.BLACK);
        tablePrestamos.setShowVerticalLines(true);
        tablePrestamos.setShowHorizontalLines(true);

        tablePrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablePrestamos.getTableHeader().setBackground(new Color(38, 50, 56));
        tablePrestamos.getTableHeader().setForeground(Color.WHITE);
        tablePrestamos.getTableHeader().setPreferredSize(new Dimension(0, 34));
        tablePrestamos.getTableHeader().setReorderingAllowed(false);

        tablePrestamos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablePrestamos.getColumnModel().getColumn(1).setPreferredWidth(180);
        tablePrestamos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablePrestamos.getColumnModel().getColumn(3).setPreferredWidth(130);
        tablePrestamos.getColumnModel().getColumn(4).setPreferredWidth(130);
        
        tablePrestamos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallePrestamoSeleccionado();
            }
        });
        
        //panel prestamo selecionado

        JPanel panelDetallePrestamo = new JPanel();
        panelDetallePrestamo.setLayout(new BorderLayout(0, 12));
        panelDetallePrestamo.setBackground(Color.WHITE);
        panelDetallePrestamo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        panelDetallePrestamo.setAlignmentX(LEFT_ALIGNMENT);
        panelDetallePrestamo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        panelPrincipal.add(panelDetallePrestamo);
        
        panelPrincipal.add(Box.createVerticalStrut(16)); // GAP
        
        // Título
        JLabel lblDetallePrestamo = new JLabel("Detalles del Préstamo Seleccionado");
        lblDetallePrestamo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDetallePrestamo.setForeground(new Color(20, 20, 20));

        panelDetallePrestamo.add(lblDetallePrestamo, BorderLayout.NORTH);

        // Contenedor de datos
        JPanel panelDatosDetalle = new JPanel();
        panelDatosDetalle.setLayout(new GridLayout(3, 2, 40, 10));
        panelDatosDetalle.setBackground(Color.WHITE);
        panelDatosDetalle.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        panelDetallePrestamo.add(panelDatosDetalle, BorderLayout.CENTER);
        
     // Fila 1 izquierda
        JPanel filaSolicitante = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaSolicitante.setBackground(Color.WHITE);

        JLabel lblDatosSolicitante = new JLabel("Datos del Solicitante:");
        lblDatosSolicitante.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorSolicitante = new JLabel("-");
        lblValorSolicitante.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        filaSolicitante.add(lblDatosSolicitante);
        filaSolicitante.add(lblValorSolicitante);
        panelDatosDetalle.add(filaSolicitante);


        // Fila 1 derecha
        JPanel filaEquipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaEquipo.setBackground(Color.WHITE);

        JLabel lblDatosEquipo = new JLabel("Datos del Equipo:");
        lblDatosEquipo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorEquipo = new JLabel("-");
        lblValorEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        filaEquipo.add(lblDatosEquipo);
        filaEquipo.add(lblValorEquipo);
        panelDatosDetalle.add(filaEquipo);


        // Fila 2 izquierda
        JPanel filaFechaPrestamo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaFechaPrestamo.setBackground(Color.WHITE);

        JLabel lblFechaPrestamo = new JLabel("Fecha de Préstamo:");
        lblFechaPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorFechaPrestamo = new JLabel("-");
        lblValorFechaPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        filaFechaPrestamo.add(lblFechaPrestamo);
        filaFechaPrestamo.add(lblValorFechaPrestamo);
        panelDatosDetalle.add(filaFechaPrestamo);


        // Fila 2 derecha
        JPanel filaFechaLimite = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaFechaLimite.setBackground(Color.WHITE);

        JLabel lblFechaLimite = new JLabel("Fecha Límite:");
        lblFechaLimite.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorFechaLimite = new JLabel("-");
        lblValorFechaLimite.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        filaFechaLimite.add(lblFechaLimite);
        filaFechaLimite.add(lblValorFechaLimite);
        panelDatosDetalle.add(filaFechaLimite);


        // Fila 3 izquierda
        JPanel filaFechaActual = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaFechaActual.setBackground(Color.WHITE);

        JLabel lblFechaActual = new JLabel("Fecha Actual:");
        lblFechaActual.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorFechaActual = new JLabel("-");
        lblValorFechaActual.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        filaFechaActual.add(lblFechaActual);
        filaFechaActual.add(lblValorFechaActual);
        panelDatosDetalle.add(filaFechaActual);


        // Fila 3 derecha
        JPanel filaEstado = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        filaEstado.setBackground(Color.WHITE);

        JLabel lblEstadoDevolucion = new JLabel("Estado de Devolución:");
        lblEstadoDevolucion.setFont(new Font("Segoe UI", Font.BOLD, 12));

        lblValorEstadoDevolucion = new JLabel("-");
        lblValorEstadoDevolucion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblValorEstadoDevolucion.setForeground(new Color(198, 40, 40));
        lblValorEstadoDevolucion.setOpaque(true);
        lblValorEstadoDevolucion.setBackground(new Color(255, 235, 238));
        lblValorEstadoDevolucion.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        filaEstado.add(lblEstadoDevolucion);
        filaEstado.add(lblValorEstadoDevolucion);
        panelDatosDetalle.add(filaEstado);
        
        //panel formulario de recpcion
        
        JPanel panelFormularioRecepcion = new JPanel();
        panelFormularioRecepcion.setLayout(new BorderLayout(0, 6));
        panelFormularioRecepcion.setBackground(Color.WHITE);
        panelFormularioRecepcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        panelFormularioRecepcion.setAlignmentX(LEFT_ALIGNMENT);
        panelFormularioRecepcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));
        panelPrincipal.add(panelFormularioRecepcion);
        
        //contenido de formulario de recepcion
        JLabel lblFormularioRecepcion = new JLabel("Formulario de Recepción");
        lblFormularioRecepcion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblFormularioRecepcion.setForeground(new Color(20, 20, 20));

        panelFormularioRecepcion.add(lblFormularioRecepcion, BorderLayout.NORTH);
        
        JPanel panelContenidoRecepcion = new JPanel();
        panelContenidoRecepcion.setLayout(new GridBagLayout());
        panelContenidoRecepcion.setBackground(Color.WHITE);

        panelFormularioRecepcion.add(panelContenidoRecepcion, BorderLayout.CENTER);
        
        JLabel lblObservacion = new JLabel("Observación de Devolución");
        lblObservacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcLblObservacion = new GridBagConstraints();
        gbcLblObservacion.gridx = 0;
        gbcLblObservacion.gridy = 0;
        gbcLblObservacion.gridwidth = 4;
        gbcLblObservacion.fill = GridBagConstraints.HORIZONTAL;
        gbcLblObservacion.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(lblObservacion, gbcLblObservacion);
        
        JTextArea txtObservacion = new JTextArea("Ingrese detalles sobre el estado del equipo al recibirlo, faltantes o incidencias...");
        txtObservacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtObservacion.setRows(4);
        txtObservacion.setLineWrap(true);
        txtObservacion.setWrapStyleWord(true);
        txtObservacion.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210)));

        JScrollPane scrollObservacion = new JScrollPane(txtObservacion);
        scrollObservacion.setBorder(null);
        scrollObservacion.setPreferredSize(new Dimension(0, 60));

        GridBagConstraints gbcScrollObservacion = new GridBagConstraints();
        gbcScrollObservacion.gridx = 0;
        gbcScrollObservacion.gridy = 1;
        gbcScrollObservacion.gridwidth = 4;
        gbcScrollObservacion.weightx = 1;
        gbcScrollObservacion.fill = GridBagConstraints.HORIZONTAL;
        gbcScrollObservacion.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(scrollObservacion, gbcScrollObservacion);
        
        JLabel lblEstadoEquipo = new JLabel("Estado del equipo tras devolución");
        lblEstadoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcLblEstadoEquipo = new GridBagConstraints();
        gbcLblEstadoEquipo.gridx = 0;
        gbcLblEstadoEquipo.gridy = 2;
        gbcLblEstadoEquipo.gridwidth = 2;
        gbcLblEstadoEquipo.fill = GridBagConstraints.HORIZONTAL;
        gbcLblEstadoEquipo.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(lblEstadoEquipo, gbcLblEstadoEquipo);
        
        JComboBox<String> cboEstadoEquipo = new JComboBox<>();
        cboEstadoEquipo.setModel(new DefaultComboBoxModel<String>(
                new String[] {
                        "Disponible para nuevo préstamo",
                        "Requiere mantenimiento",
                        "Equipo dañado",
                        "Equipo incompleto",
                        "Dar de baja"
                }
        ));
        cboEstadoEquipo.setPreferredSize(new Dimension(0, 32));
        cboEstadoEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        GridBagConstraints gbcCboEstadoEquipo = new GridBagConstraints();
        gbcCboEstadoEquipo.gridx = 0;
        gbcCboEstadoEquipo.gridy = 3;
        gbcCboEstadoEquipo.gridwidth = 2;
        gbcCboEstadoEquipo.weightx = 1;
        gbcCboEstadoEquipo.fill = GridBagConstraints.HORIZONTAL;
        gbcCboEstadoEquipo.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(cboEstadoEquipo, gbcCboEstadoEquipo);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(110, 34));
        btnCancelar.setBackground(new Color(150, 150, 150));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);

        GridBagConstraints gbcBtnCancelar = new GridBagConstraints();
        gbcBtnCancelar.gridx = 2;
        gbcBtnCancelar.gridy = 3;
        gbcBtnCancelar.gridwidth = 1;
        gbcBtnCancelar.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnCancelar.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(btnCancelar, gbcBtnCancelar);
        
        JButton btnRegistrarDevolucion = new JButton("Registrar Devolución");
        btnRegistrarDevolucion.setPreferredSize(new Dimension(170, 34));
        btnRegistrarDevolucion.setBackground(new Color(25, 118, 210));
        btnRegistrarDevolucion.setForeground(Color.WHITE);
        btnRegistrarDevolucion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrarDevolucion.setFocusPainted(false);
        btnRegistrarDevolucion.setBorderPainted(false);

        GridBagConstraints gbcBtnRegistrarDevolucion = new GridBagConstraints();
        gbcBtnRegistrarDevolucion.gridx = 3;
        gbcBtnRegistrarDevolucion.gridy = 3;
        gbcBtnRegistrarDevolucion.gridwidth = 1;
        gbcBtnRegistrarDevolucion.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnRegistrarDevolucion.insets = new Insets(2, 5, 2, 5);

        panelContenidoRecepcion.add(btnRegistrarDevolucion, gbcBtnRegistrarDevolucion);
    }
    
    private void listarPrestamosActivos() {
    	devolucionController = new DevolucionController();
    	
    	ArrayList<PrestamoActivoDTO> lista = devolucionController.listarPrestamosActivos();
    	DefaultTableModel modelo = new DefaultTableModel(
    			new Object[][] {},
    			new String[] {
    					"ID", "Solicitante","Dni", "Equipo", "Fecha Préstamo", "Fecha Límite" , "Estado"
    			}
    			
    			) {
    		
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    	};
    	
    	for(PrestamoActivoDTO p : lista) {
    		Object[] fila = {
    				p.getIdPrestamo(),
    				p.getSolicitante(),
    				p.getdni(),
    				p.getNombreEquipo(),
    				p.getFechaPrestamo(),
    				p.getFechaDevolucionPrevista(),
    				p.getEstadoPrestamo()
    		};
    		modelo.addRow(fila);
    	}
    		tablePrestamos.setModel(modelo);
    		
    		//Ocultar DNI
    		
    		tablePrestamos.getColumnModel().getColumn(2).setMinWidth(0);
    		tablePrestamos.getColumnModel().getColumn(2).setMaxWidth(0);
    		tablePrestamos.getColumnModel().getColumn(2).setPreferredWidth(0);
    		
    		//Ocultar estado 
    		tablePrestamos.getColumnModel().getColumn(6).setMinWidth(0);
    		tablePrestamos.getColumnModel().getColumn(6).setMaxWidth(0);
    		tablePrestamos.getColumnModel().getColumn(6).setPreferredWidth(0);
    		
    	    // Tamaños de columnas visibles
    	    tablePrestamos.getColumnModel().getColumn(0).setPreferredWidth(80);
    	    tablePrestamos.getColumnModel().getColumn(1).setPreferredWidth(180);
    	    tablePrestamos.getColumnModel().getColumn(3).setPreferredWidth(300);
    	    tablePrestamos.getColumnModel().getColumn(4).setPreferredWidth(130);
    	    tablePrestamos.getColumnModel().getColumn(5).setPreferredWidth(130);
    	
    }
    
    private void mostrarDetallePrestamoSeleccionado() {
        int fila = tablePrestamos.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int filaModelo = tablePrestamos.convertRowIndexToModel(fila);

        DefaultTableModel modelo = (DefaultTableModel) tablePrestamos.getModel();

        String solicitante = modelo.getValueAt(filaModelo, 1).toString();
        String dni = modelo.getValueAt(fila, 2).toString();
        String equipo = modelo.getValueAt(filaModelo, 3).toString();
        String fechaPrestamo = modelo.getValueAt(filaModelo, 4).toString();
        String fechaLimite = modelo.getValueAt(filaModelo, 5).toString();
        String estado = modelo.getValueAt(filaModelo, 6).toString();

        lblValorSolicitante.setText(solicitante+" ("+ "DNI: "+  dni + ")");
        lblValorEquipo.setText(equipo);
        lblValorFechaPrestamo.setText(fechaPrestamo);
        lblValorFechaLimite.setText(fechaLimite);
        lblValorFechaActual.setText(LocalDateTime.now().toLocalDate().toString());
        lblValorEstadoDevolucion.setText(estado);
        
        actualizarColorEstado(estado);

    }
    
    private void actualizarColorEstado(String estado) {
        lblValorEstadoDevolucion.setOpaque(true);
        lblValorEstadoDevolucion.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        if (estado.equalsIgnoreCase("Vencido")) {
            lblValorEstadoDevolucion.setText("⚠ Vencido");
            lblValorEstadoDevolucion.setForeground(new Color(198, 40, 40));
            lblValorEstadoDevolucion.setBackground(new Color(255, 235, 238));
        } else if (estado.equalsIgnoreCase("Activo")) {
            lblValorEstadoDevolucion.setText("Activo");
            lblValorEstadoDevolucion.setForeground(new Color(46, 125, 50));
            lblValorEstadoDevolucion.setBackground(new Color(232, 245, 233));
        } else if (estado.equalsIgnoreCase("Devuelto")) {
            lblValorEstadoDevolucion.setText("Devuelto");
            lblValorEstadoDevolucion.setForeground(new Color(25, 118, 210));
            lblValorEstadoDevolucion.setBackground(new Color(227, 242, 253));
        } else {
            lblValorEstadoDevolucion.setText(estado);
            lblValorEstadoDevolucion.setForeground(Color.DARK_GRAY);
            lblValorEstadoDevolucion.setBackground(new Color(245, 245, 245));
        }
    }
    
    private void buscarPrestamo() {
    	devolucionController = new DevolucionController();
    	
    	String idPrestamo = txtCodigoPrestamo.getText();
    	String dni = txtDniSolicitante.getText();
    	String codigoEquipo = txtCodigoEquipo.getText();
    	
    	ArrayList<PrestamoActivoDTO> lista = devolucionController.buscarPrestamoActivos(idPrestamo, dni,codigoEquipo);	
    	
    	cargarTablaPrestamo(lista);
    }
    
    private void cargarTablaPrestamo(ArrayList<PrestamoActivoDTO> lista) {
    	DefaultTableModel modelo = new DefaultTableModel(
    			new Object[][] {},
    			new String[] {
    					"ID", "Solicitante","Dni", "Equipo", "Fecha Préstamo", "Fecha Límite" , "Estado"
    			}
    			
    			) {
    		
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    	};
    	
    	for(PrestamoActivoDTO p : lista) {
    		Object[] fila = {
    				p.getIdPrestamo(),
    				p.getSolicitante(),
    				p.getdni(),
    				p.getNombreEquipo(),
    				p.getFechaPrestamo(),
    				p.getFechaDevolucionPrevista(),
    				p.getEstadoPrestamo()
    		};
    		modelo.addRow(fila);
    	}
    		tablePrestamos.setModel(modelo);
    		
    		//Ocultar DNI
    		
    		tablePrestamos.getColumnModel().getColumn(2).setMinWidth(0);
    		tablePrestamos.getColumnModel().getColumn(2).setMaxWidth(0);
    		tablePrestamos.getColumnModel().getColumn(2).setPreferredWidth(0);
    		
    		//Ocultar estado 
    		tablePrestamos.getColumnModel().getColumn(6).setMinWidth(0);
    		tablePrestamos.getColumnModel().getColumn(6).setMaxWidth(0);
    		tablePrestamos.getColumnModel().getColumn(6).setPreferredWidth(0);
    		
    	    // Tamaños de columnas visibles
    	    tablePrestamos.getColumnModel().getColumn(0).setPreferredWidth(80);
    	    tablePrestamos.getColumnModel().getColumn(1).setPreferredWidth(180);
    	    tablePrestamos.getColumnModel().getColumn(3).setPreferredWidth(300);
    	    tablePrestamos.getColumnModel().getColumn(4).setPreferredWidth(130);
    	    tablePrestamos.getColumnModel().getColumn(5).setPreferredWidth(130);
    }
}