
import Controlador.LoginController;
import Vistas.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author miguel
 */
public class MainApp {
    public static void main(String[] args) {
        Login viewLogin = new Login();
        LoginController loginController = new LoginController(viewLogin);
        viewLogin.setVisible(true);
    }
}
