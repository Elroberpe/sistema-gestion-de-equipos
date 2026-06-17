package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.UsuarioController;
import Modelo.Usuario;

public class FrmRegistroUsuario extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombres;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cboRol;

   

    public FrmRegistroUsuario() {
        setTitle("Registrar Nuevo Usuario");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Registro de Personal");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(20, 30, 400, 30);
        contentPane.add(lblTitulo);

        // --- NOMBRES COMPLETOS ---
        JLabel lblNombres = new JLabel("Nombres Completos");
        lblNombres.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombres.setBounds(40, 90, 150, 20);
        contentPane.add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNombres.setBounds(40, 115, 360, 40);
        txtNombres.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtNombres);

        // --- USERNAME ---
        JLabel lblUsername = new JLabel("Nombre de Usuario (Login)");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsername.setBounds(40, 175, 200, 20);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBounds(40, 200, 360, 40);
        txtUsername.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtUsername);

        // --- CONTRASEÑA ---
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPassword.setBounds(40, 260, 100, 20);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBounds(40, 285, 360, 40);
        txtPassword.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtPassword);

        // --- ROL (ComboBox) ---
        JLabel lblRol = new JLabel("Rol en el Sistema");
        lblRol.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRol.setBounds(40, 345, 150, 20);
        contentPane.add(lblRol);

        cboRol = new JComboBox<String>();
        cboRol.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboRol.setBounds(40, 370, 360, 40);
        cboRol.addItem("Operador");
        cboRol.addItem("Administrador");
        cboRol.setBackground(Color.WHITE);
        contentPane.add(cboRol);

        // --- BOTÓN REGISTRAR ---
        JButton btnRegistrar = new JButton("Guardar Usuario");
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBackground(new Color(25, 118, 210)); // Verde estilo "Success"
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setBounds(40, 440, 360, 45);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnRegistrar);

        // =======================================================
        // LÓGICA DEL BOTÓN REGISTRAR
        // =======================================================
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombres = txtNombres.getText();
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String rol = cboRol.getSelectedItem().toString();

                
                if (nombres.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombresCompletos(nombres);
                nuevoUsuario.setUsername(username);
                nuevoUsuario.setRol(rol);

                UsuarioController controlador = new UsuarioController();
                boolean exito = controlador.procesarRegistro(nuevoUsuario, password);

                if (exito) {

                    txtPassword.setText("");
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                                     
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar. Verifique que el usuario no exista o que la contraseña tenga mínimo 6 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}