/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.UsuarioDaoImpl;
import Modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.Login;
import Vistas.Principal;
import javax.swing.JOptionPane;
/**
 *
 * @author miguel
 */
public class LoginController implements ActionListener {
   
    Login viewLogin = new Login();
    UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
    
    public LoginController(Login viewLogin) {
        this.viewLogin = viewLogin;
        
        this.viewLogin.btnCancelar.addActionListener(this);
        this.viewLogin.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewLogin.btnEntrar) {
            
            Usuario usuario = new Usuario();
            usuario.setUsuario(this.viewLogin.txtUsuario.getText());
            usuario.setClave(this.viewLogin.txtClave.getText());
            if (this.usuarioDao.login(usuario)) {
                Principal viewPrincipal = new Principal();
                PrincipalController principalController = new PrincipalController(viewPrincipal);
                viewPrincipal.setVisible(true);
                this.viewLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Información errada por favor verificar...");
            }
        }
        
        if (e.getSource() == this.viewLogin.btnCancelar) {
            System.exit(0);
        }
    }
    
    
    
}
