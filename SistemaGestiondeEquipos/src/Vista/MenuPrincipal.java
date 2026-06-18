package Vista;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuPrincipal extends JFrame {
	
	private Usuario usuarioActual;
	
	private JPanel contenedor;
	private CardLayout cardLayout;
	private JButton botonSeleccionado;
	private JButton btnPrestamos;
	private JButton btnDevoluciones;
	private JButton btnReportes;
	private PanelEquipos panelEquipos;
	private PanelSolicitantes panelSolicitantes;
	private PanelPrestamos panelPrestamos;
	private PanelDevoluciones panelDevoluciones;
	private PanelReportes panelReportes;
	private PanelUsuarios panelUsuarios;
	
	
	private final Color COLOR_NORMAL = new Color(26, 35, 126);
	private final Color COLOR_HOVER = new Color(40, 53, 147);
	private final Color COLOR_ACTIVO = new Color(48, 63, 159);
	
	private static final long serialVersionUID = 1L;

	
	public MenuPrincipal(Usuario usuarioLogueado ) {
		this.usuarioActual = usuarioLogueado;
	
	    setTitle("Sistema de Gestión de Equipos");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    setPreferredSize(new Dimension(1200, 650));
	

	    getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerLocation(220);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);
		
		 getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		 JPanel sidebar = new JPanel();
		 sidebar.setPreferredSize(new Dimension(220, 0));
		 sidebar.setBackground(new Color(26, 35, 126));
		 sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		 splitPane.setLeftComponent(sidebar);
		
		
		 JPanel panelTitulo = new JPanel();
		 panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
		 panelTitulo.setBackground(new Color(26, 35, 126));
		 panelTitulo.setBorder(BorderFactory.createEmptyBorder(16, 20, 20, 0));
		 panelTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 86));

		 JLabel lblTitulo = new JLabel("Gestión TI");
		 lblTitulo.setForeground(Color.WHITE);
		 lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		 lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

		 JLabel lblRol = new JLabel("Administrador");
		 lblRol.setForeground(new Color(230, 230, 230));
		 lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		 lblRol.setAlignmentX(Component.LEFT_ALIGNMENT);

		 panelTitulo.add(lblTitulo);
		 panelTitulo.add(Box.createVerticalStrut(2));
		 panelTitulo.add(lblRol);

		 sidebar.add(panelTitulo);
		
		//Botones
		
		 JButton btnInicio = crearBotonSidebar("Inicio", "/Imagenes/Iconos/inicio.png");
		 JButton btnEquipos = crearBotonSidebar("Equipos", "/Imagenes/Iconos/equipos.png");
		 JButton btnSolicitantes = crearBotonSidebar("Solicitantes", "/Imagenes/Iconos/solicitantes.png");
		 btnPrestamos = crearBotonSidebar("Préstamos", "/Imagenes/Iconos/prestamos.png");
		 btnDevoluciones = crearBotonSidebar("Devoluciones", "/Imagenes/Iconos/devoluciones.png");
		 btnReportes = crearBotonSidebar("Reportes", "/Imagenes/Iconos/reportes.png");
		 JButton btnUsuarios = crearBotonSidebar("Usuarios", "/Imagenes/Iconos/usuarios.png");	
		 
		 if (usuarioActual.getRol().equals("Operador")) {
			 btnUsuarios.setVisible(false); 
		 }
		 
		 sidebar.add(btnInicio);
		 sidebar.add(btnEquipos);
		 sidebar.add(btnSolicitantes);
		 sidebar.add(btnPrestamos);
		 sidebar.add(btnDevoluciones);
		 sidebar.add(btnReportes);
		 sidebar.add(btnUsuarios);
	
		 JButton btnSalir = crearBotonSidebar("Salir", "/Imagenes/Iconos/salir.png");

		 sidebar.add(Box.createVerticalGlue());
		 sidebar.add(btnSalir);
		
		cardLayout = new CardLayout();
		
		contenedor =new JPanel();
		contenedor.setLayout(cardLayout);
		splitPane.setRightComponent(contenedor);
		
		
		PaneInicio paneInicio = new PaneInicio();
		contenedor.add(paneInicio, "INICIO");
		
		btnInicio.addActionListener(e -> {
		    mostrarPanel("INICIO");
		    seleccionarBoton(btnInicio);
		});

		btnEquipos.addActionListener(e -> {
			if(panelEquipos == null) {
				panelEquipos = new PanelEquipos();
				contenedor.add(panelEquipos, "EQUIPOS");
			}
			
		    mostrarPanel("EQUIPOS");
		    seleccionarBoton(btnEquipos);
		});

		btnSolicitantes.addActionListener(e -> {
			if(panelSolicitantes == null) {
				panelSolicitantes = new PanelSolicitantes();
				contenedor.add(panelSolicitantes, "SOLICITANTES");
			}

		    mostrarPanel("SOLICITANTES");
		    seleccionarBoton(btnSolicitantes);
		});

		btnPrestamos.addActionListener(e -> {
			if(panelPrestamos == null) {
				panelPrestamos = new PanelPrestamos();
				contenedor.add(panelPrestamos, "PRESTAMOS");
			}

		    mostrarPanel("PRESTAMOS");
		    seleccionarBoton(btnPrestamos);
		});

		btnDevoluciones.addActionListener(e -> {

		    if (panelDevoluciones == null) {
		        panelDevoluciones = new PanelDevoluciones();
		        contenedor.add(panelDevoluciones, "DEVOLUCIONES");
		    }

		    mostrarPanel("DEVOLUCIONES");
		    seleccionarBoton(btnDevoluciones);
		});

		btnReportes.addActionListener(e -> {

		    if (panelReportes == null) {
		        panelReportes = new PanelReportes();
		        contenedor.add(panelReportes, "REPORTES");
		    }

		    mostrarPanel("REPORTES");
		    seleccionarBoton(btnReportes);
		});

		btnUsuarios.addActionListener(e -> {

		    if (panelUsuarios == null) {
		        panelUsuarios = new PanelUsuarios();
		        contenedor.add(panelUsuarios, "USUARIOS");
		    }

		    mostrarPanel("USUARIOS");
		    seleccionarBoton(btnUsuarios);
		});
		
		btnSalir.addActionListener(e ->{
			FrmLogin login = new FrmLogin();
			login.setVisible(true);
			dispose();
		});
		
		mostrarPanel("INICIO");
		seleccionarBoton(btnInicio);
		
		pack();
		setLocationRelativeTo(null);

	}
	
	private JButton crearBotonSidebar(String texto, String rutaIcono) {
	    JButton boton = new JButton(texto);

	    boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    boton.setForeground(Color.WHITE);
	    boton.setBackground(COLOR_NORMAL);

	    boton.setHorizontalAlignment(SwingConstants.LEFT);
	    boton.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

	    boton.setFocusPainted(false);
	    boton.setBorderPainted(false);
	    boton.setContentAreaFilled(true);
	    boton.setOpaque(true);

	    boton.setIcon(cargarIcono(rutaIcono, 20, 20));
	    boton.setIconTextGap(14);

	    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

	    boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
	    boton.setPreferredSize(new Dimension(220, 48));
	    boton.setMinimumSize(new Dimension(220, 48));
	    boton.setAlignmentX(Component.LEFT_ALIGNMENT);

	    boton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            if (boton != botonSeleccionado) {
	                boton.setBackground(COLOR_HOVER);
	            }
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            if (boton != botonSeleccionado) {
	                boton.setBackground(COLOR_NORMAL);
	            }
	        }
	    });

	    return boton;
	}
	
	public void seleccionarBoton(JButton boton) {
	    if (botonSeleccionado != null) {
	        botonSeleccionado.setBackground(COLOR_NORMAL);
	    }

	    botonSeleccionado = boton;
	    botonSeleccionado.setBackground(COLOR_ACTIVO);
	}
	

	public void irAlPanel(String panel) {

	    switch (panel) {

	        case "PRESTAMOS":
	            if (panelPrestamos == null) {
	                panelPrestamos = new PanelPrestamos();
	                contenedor.add(panelPrestamos, "PRESTAMOS");
	            }
	            seleccionarBoton(btnPrestamos);
	            break;

	        case "DEVOLUCIONES":
	            if (panelDevoluciones == null) {
	                panelDevoluciones = new PanelDevoluciones();
	                contenedor.add(panelDevoluciones, "DEVOLUCIONES");
	            }
	            seleccionarBoton(btnDevoluciones);
	            break;

	        case "REPORTES":
	            if (panelReportes == null) {
	                panelReportes = new PanelReportes();
	                contenedor.add(panelReportes, "REPORTES");
	            }
	            seleccionarBoton(btnReportes);
	            break;
	    }

	    mostrarPanel(panel);
	}
	
	private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
	    ImageIcon iconoOriginal = new ImageIcon(MenuPrincipal.class.getResource(ruta));

	    Image imagen = iconoOriginal.getImage().getScaledInstance(
	            ancho,
	            alto,
	            Image.SCALE_SMOOTH
	    );

	    return new ImageIcon(imagen);
	}
	
	public void mostrarPanel(String nombrePanel) {
		cardLayout.show(contenedor, nombrePanel);
	}
}
