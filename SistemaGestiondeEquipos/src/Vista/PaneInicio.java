package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class PaneInicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

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
        contenedorBotones.add(btnPrestamo);
        
        JButton btnDevolucion = new JButton("Devolucion");
        contenedorBotones.add(btnDevolucion);
        
        JButton btnReportes = new JButton("Reportes");
        contenedorBotones.add(btnReportes);
        
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
        
        llenarCard(cardEquiposRegistrados, "EQUIPOS", "REGISTRADOS", "1,248", "↗ +12 este mes", new Color(46, 125, 50));
        llenarCard(cardEquiposDisponibles, "EQUIPOS", "DISPONIBLES", "842", "67.4% del total", new Color(70, 70, 70));
        llenarCard(cardEquiposPrestados, "EQUIPOS", "PRESTADOS", "391", "31.3% del total", new Color(70, 70, 70));
        llenarCard(cardPrestamosActivos, "PRÉSTAMOS", "ACTIVOS", "156", "Requieren seguimiento", new Color(70, 70, 70));
        llenarCard(cardPrestamosVencidos, "PRÉSTAMOS", "VENCIDOS", "15", "Acción requerida", new Color(211, 47, 47));
        
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

        DefaultTableModel modelo = new DefaultTableModel(
            new Object[][] {
                {"PR-\n1042", "Laptop Dell Latitude 7420", "Ana Martínez (Ventas)", "24 Oct 2023", "31 Oct 2023", "Activo"},
                {"PR-\n1041", "Proyector Epson WXGA", "Carlos Gómez (Marketing)", "23 Oct 2023", "24 Oct 2023", "Vencido"},
                {"PR-\n1040", "MacBook Pro M1 16\"", "Laura Sánchez (Diseño)", "20 Oct 2023", "20 Nov 2023", "Activo"},
                {"PR-\n1039", "Monitor LG 27\" 4K", "Roberto Díaz (Desarrollo)", "18 Oct 2023", "18 Ene 2024", "Activo"},
                {"PR-\n1038", "iPad Pro 11\"", "Elena Ruiz (Dirección)", "15 Oct 2023", "22 Oct 2023", "Devuelto"}
            },
            new String[] {
                "ID", "EQUIPO", "SOLICITANTE", "FECHA PRÉSTAMO", "FECHA DEVOLUCIÓN", "ESTADO"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(modelo);
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
        
	}
	private void llenarCard(JPanel card, String titulo1, String titulo2, String numero, String detalle, Color colorDetalle) {
	    card.setLayout(null);
	    card.setBackground(Color.WHITE);
	    card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));

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

	    JLabel lblNumero = new JLabel(numero);
	    lblNumero.setBounds(14, 55, 130, 30);
	    lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    lblNumero.setForeground(Color.BLACK);
	    card.add(lblNumero);

	    JLabel lblDetalle = new JLabel(detalle);
	    lblDetalle.setBounds(14, 90, 140, 18);
	    lblDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    lblDetalle.setForeground(colorDetalle);
	    card.add(lblDetalle);
	}
}
