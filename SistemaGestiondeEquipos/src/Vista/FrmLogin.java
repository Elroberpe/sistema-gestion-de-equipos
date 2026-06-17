package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import Dao.UsuarioDAO;
import Modelo.Usuario;

public class FrmLogin extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        try {
            FrmLogin frame = new FrmLogin();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmLogin() {

        setTitle("Login - Sistema de Gestión de Equipos Electronicos");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 500); 
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblIcono = new JLabel("💻"); 
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        lblIcono.setBounds(150, 20, 80, 60);
        lblIcono.setBorder(new LineBorder(new Color(200, 220, 255), 2)); 
        contentPane.add(lblIcono);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Sistema de Préstamo de Equipos");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(20, 100, 340, 30);
        contentPane.add(lblTitulo);

        // --- USUARIO ---
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsuario.setBounds(30, 160, 100, 20);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBounds(30, 185, 320, 40);
        txtUsuario.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtUsuario);

        // --- CONTRASEÑA ---
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPassword.setBounds(30, 240, 100, 20);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBounds(30, 265, 320, 40);
        txtPassword.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtPassword);

        // --- BOTÓN INICIAR SESIÓN ---
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(24, 119, 242)); 
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBounds(30, 330, 320, 45);
        btnLogin.setBorderPainted(false); 
        btnLogin.setFocusPainted(false); 
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnLogin);

        // --- SEPARADOR ---
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(220, 220, 220));
        separator.setBounds(30, 400, 320, 10);
        contentPane.add(separator);

        // --- INFO FOOTER ---
        JLabel lblInfo = new JLabel("Acceso para administrador y encargado");
        lblInfo.setForeground(new Color(100, 100, 100));
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBounds(30, 415, 320, 20);
        contentPane.add(lblInfo);

   
        btnLogin.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                String username = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuarioLogueado = dao.login(username, password);

                if (usuarioLogueado != null) {
                    txtPassword.setText(""); 
                    JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuarioLogueado.getNombresCompletos() + "!");
                   
                    MenuPrincipal menu = new MenuPrincipal(usuarioLogueado);
                    menu.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}