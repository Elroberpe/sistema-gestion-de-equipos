package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import Controller.EquipoController;
import Controller.PrestamoController;
import Modelo.Equipo;
import Modelo.Prestamo;

public class PaneInicio extends JPanel {
	
	

	private static final long serialVersionUID = 1L;
	private JTable table;

	private EquipoController equipoController = new EquipoController();
	private PrestamoController prestamoController = new PrestamoController();

	private JLabel lblNumEquiposRegistrados;
	private JLabel lblDetalleEquiposRegistrados;
	private JLabel lblNumEquiposDisponibles;
	private JLabel lblDetalleEquiposDisponibles;
	private JLabel lblNumEquiposPrestados;
	private JLabel lblDetalleEquiposPrestados;
	private JLabel lblNumPrestamosActivos;
	private JLabel lblNumPrestamosVencidos;

	private DefaultTableModel modeloTabla;

	public PaneInicio() {
        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel contenedorBotones = new JPanel();
        contenedorBotones.setBorder(null);
        contenedorBotones.setPreferredSize(new Dimension(0, 50));
        add(contenedorBotones, BorderLayout.NORTH);
        contenedorBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        
        JButton btnPrestamo = new JButton("Prestamos");
        btnPrestamo.addActionListener(e -> {
        	irAlPanelSeleccionado("PRESTAMOS");
		});
        

        
        contenedorBotones.add(btnPrestamo);
        
        JButton btnDevolucion = new JButton("Devolucion");
        contenedorBotones.add(btnDevolucion);
        
        btnDevolucion.addActionListener(e -> {
        	irAlPanelSeleccionado("DEVOLUCIONES");
        });
        
        
        JButton btnReportes = new JButton("Reportes");
        contenedorBotones.add(btnReportes);
        btnReportes.addActionListener(e -> {
        	irAlPanelSeleccionado("REPORTES");
        });

        JButton btnActualizar = new JButton("Actualizar");
        contenedorBotones.add(btnActualizar);
        
        btnPrestamo.setBackground(new Color(25,118,210));
        btnPrestamo.setForeground(Color.WHITE);
        btnPrestamo.setFocusPainted(false);
        btnPrestamo.setBorderPainted(false);
        
        btnDevolucion.setBackground(new Color(130,130,130));
        btnDevolucion.setForeground(Color.WHITE);
        btnDevolucion.setFocusPainted(false);
        btnDevolucion.setBorderPainted(false);
        
        btnReportes.setBackground(new Color(130, 130, 130));
        btnReportes.setForeground(Color.WHITE);
        btnReportes.setFocusPainted(false);
        btnReportes.setBorderPainted(false);

        btnActualizar.setBackground(new Color(25, 118, 210));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        
        
        JPanel contenedorPrincipal = new JPanel();
        add(contenedorPrincipal, BorderLayout.CENTER);
        contenedorPrincipal.setLayout(new BorderLayout(0, 20));
        
        JPanel panelCards = new JPanel();
        panelCards.setBorder(null);
        panelCards.setPreferredSize(new Dimension(0, 130));
        contenedorPrincipal.add(panelCards, BorderLayout.NORTH);
        panelCards.setLayout(new GridLayout(1, 5, 10, 0));
        
        JPanel cardEquiposRegistrados = new JPanel();
        cardEquiposRegistrados.setBorder(new LineBorder(Color.LIGHT_GRAY));
        cardEquiposRegistrados.setBackground(Color.WHITE);
        panelCards.add(cardEquiposRegistrados);
        
        JPanel cardEquiposDisponibles = new JPanel();
        cardEquiposDisponibles.setBorder(new LineBorder(Color.LIGHT_GRAY));
        cardEquiposDisponibles.setBackground(Color.WHITE);
        panelCards.add(cardEquiposDisponibles);
        
        JPanel cardEquiposPrestados = new JPanel();
        cardEquiposPrestados.setBorder(new LineBorder(Color.LIGHT_GRAY));
        cardEquiposPrestados.setBackground(Color.WHITE);
        panelCards.add(cardEquiposPrestados);
        
        JPanel cardPrestamosActivos = new JPanel();
        cardPrestamosActivos.setBorder(new LineBorder(Color.LIGHT_GRAY));
        cardPrestamosActivos.setBackground(Color.WHITE);
        panelCards.add(cardPrestamosActivos);
        
        JPanel cardPrestamosVencidos = new JPanel();
        cardPrestamosVencidos.setBorder(new LineBorder(Color.LIGHT_GRAY));
        cardPrestamosVencidos.setBackground(Color.WHITE);
        panelCards.add(cardPrestamosVencidos);
        
        // Se crean las tarjetas guardando referencias a los labels para poder actualizarlas
        lblNumEquiposRegistrados = crearNumeroCard(cardEquiposRegistrados);
        lblDetalleEquiposRegistrados = crearDetalleCard(cardEquiposRegistrados);
        crearTitulosCard(cardEquiposRegistrados, "EQUIPOS", "REGISTRADOS");

        lblNumEquiposDisponibles = crearNumeroCard(cardEquiposDisponibles);
        lblDetalleEquiposDisponibles = crearDetalleCard(cardEquiposDisponibles);
        crearTitulosCard(cardEquiposDisponibles, "EQUIPOS", "DISPONIBLES");

        lblNumEquiposPrestados = crearNumeroCard(cardEquiposPrestados);
        lblDetalleEquiposPrestados = crearDetalleCard(cardEquiposPrestados);
        crearTitulosCard(cardEquiposPrestados, "EQUIPOS", "PRESTADOS");

        lblNumPrestamosActivos = crearNumeroCard(cardPrestamosActivos);
        JLabel lblDetalleActivos = crearDetalleCard(cardPrestamosActivos);
        lblDetalleActivos.setText("Requieren seguimiento");
        lblDetalleActivos.setForeground(new Color(70, 70, 70));
        crearTitulosCard(cardPrestamosActivos, "PRÉSTAMOS", "ACTIVOS");

        lblNumPrestamosVencidos = crearNumeroCard(cardPrestamosVencidos);
        JLabel lblDetalleVencidos = crearDetalleCard(cardPrestamosVencidos);
        lblDetalleVencidos.setText("Acción requerida");
        lblDetalleVencidos.setForeground(new Color(211, 47, 47));
        crearTitulosCard(cardPrestamosVencidos, "PRÉSTAMOS", "VENCIDOS");
        
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(null);
        
        contenedorPrincipal.add(panelTabla, BorderLayout.CENTER);
        
        JPanel panelTituloTabla = new JPanel(new BorderLayout());
        panelTituloTabla.setBackground(Color.WHITE);
        panelTituloTabla.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lblTituloTabla = new JLabel("Últimos préstamos registrados");
        lblTituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloTabla.setForeground(Color.BLACK);

        JLabel lblVerTodos = new JLabel("Ver todos");
        lblVerTodos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblVerTodos.setForeground(new Color(25, 118, 210));
        lblVerTodos.setHorizontalAlignment(SwingConstants.RIGHT);

        panelTituloTabla.add(lblTituloTabla, BorderLayout.WEST);
        panelTituloTabla.add(lblVerTodos, BorderLayout.EAST);

        panelTabla.add(panelTituloTabla, BorderLayout.NORTH);
        

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();

        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "EQUIPO", "ID SOLICITANTE", "FECHA PRÉSTAMO", "FECHA DEVOLUCIÓN", "ESTADO"
            }
        ) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(modeloTabla);
        scrollPane.setViewportView(table);
        
        table.setRowHeight(42);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        table.getTableHeader().setBackground(new Color(38, 50, 56));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        
        table.getTableHeader().setPreferredSize(new Dimension(
                table.getTableHeader().getPreferredSize().width,
                35
        ));

        btnActualizar.addActionListener(e -> cargarDatos());

        cargarDatos();
        
	}

	private void cargarDatos() {
        ArrayList<Equipo> equipos = equipoController.listar();
        ArrayList<Prestamo> prestamos = prestamoController.listar();

        int totalEquipos = equipos.size();
        int disponibles = 0;
        int prestados = 0;

        for (Equipo eq : equipos) {
            if ("Disponible".equalsIgnoreCase(eq.getEstado())) {
                disponibles++;
            } else if ("Prestado".equalsIgnoreCase(eq.getEstado())) {
                prestados++;
            }
        }

        int activos = 0;
        int vencidos = 0;
        LocalDate hoy = LocalDate.now();

        for (Prestamo p : prestamos) {
            if ("Activo".equalsIgnoreCase(p.getEstado())) {
                activos++;
                if (p.getFechaDevolucionPrevista() != null && p.getFechaDevolucionPrevista().isBefore(hoy)) {
                    vencidos++;
                }
            } else if ("Vencido".equalsIgnoreCase(p.getEstado())) {
                vencidos++;
            }
        }

        lblNumEquiposRegistrados.setText(String.valueOf(totalEquipos));
        lblNumEquiposDisponibles.setText(String.valueOf(disponibles));
        lblNumEquiposPrestados.setText(String.valueOf(prestados));
        lblNumPrestamosActivos.setText(String.valueOf(activos));
        lblNumPrestamosVencidos.setText(String.valueOf(vencidos));

        double pctDisponibles = totalEquipos > 0 ? (disponibles * 100.0 / totalEquipos) : 0;
        double pctPrestados = totalEquipos > 0 ? (prestados * 100.0 / totalEquipos) : 0;

        lblDetalleEquiposRegistrados.setText("Total de equipos en el sistema");
        lblDetalleEquiposDisponibles.setText(String.format("%.1f%% del total", pctDisponibles));
        lblDetalleEquiposPrestados.setText(String.format("%.1f%% del total", pctPrestados));

        // Cargar tabla con los ultimos prestamos (los de mayor id primero)
        modeloTabla.setRowCount(0);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("es", "ES"));

        prestamos.sort((a, b) -> Integer.compare(b.getIdPrestamo(), a.getIdPrestamo()));

        int max = Math.min(5, prestamos.size());
        for (int i = 0; i < max; i++) {
            Prestamo p = prestamos.get(i);

            String nombreEquipo = "Equipo #" + p.getIdEquipo();
            for (Equipo eq : equipos) {
                if (eq.getIdEquipo() == p.getIdEquipo()) {
                    nombreEquipo = eq.getNombre();
                    break;
                }
            }

            String fechaPrestamo = p.getFechaPrestamo() != null ? p.getFechaPrestamo().format(formato) : "-";
            String fechaDevolucion = p.getFechaDevolucionPrevista() != null ? p.getFechaDevolucionPrevista().format(formato) : "-";

            modeloTabla.addRow(new Object[]{
                "PR-" + p.getIdPrestamo(),
                nombreEquipo,
                "Solicitante #" + p.getIdSolicitante(),
                fechaPrestamo,
                fechaDevolucion,
                p.getEstado()
            });
        }
	}

	private JLabel crearNumeroCard(JPanel card) {
	    card.setLayout(null);
	    card.setBackground(Color.WHITE);
	    card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));

	    JLabel lblNumero = new JLabel("0");
	    lblNumero.setBounds(14, 55, 130, 30);
	    lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    lblNumero.setForeground(Color.BLACK);
	    card.add(lblNumero);
	    return lblNumero;
	}

	private JLabel crearDetalleCard(JPanel card) {
	    JLabel lblDetalle = new JLabel("");
	    lblDetalle.setBounds(14, 90, 200, 18);
	    lblDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    lblDetalle.setForeground(new Color(70, 70, 70));
	    card.add(lblDetalle);
	    return lblDetalle;
	}

	private void crearTitulosCard(JPanel card, String titulo1, String titulo2) {
	    JLabel lblTitulo1 = new JLabel(titulo1);
	    lblTitulo1.setBounds(14, 10, 130, 18);
	    lblTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    lblTitulo1.setForeground(new Color(55, 65, 81));
	    card.add(lblTitulo1);

	    JLabel lblTitulo2 = new JLabel(titulo2);
	    lblTitulo2.setBounds(14, 28, 130, 18);
	    lblTitulo2.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    lblTitulo2.setForeground(new Color(55, 65, 81));
	    card.add(lblTitulo2);
	}
	
	private void irAlPanelSeleccionado(String panel) {
		
		  MenuPrincipal menuPrincipal= (MenuPrincipal) SwingUtilities.getWindowAncestor(this);		  
		  menuPrincipal.irAlPanel(panel);
	}
}