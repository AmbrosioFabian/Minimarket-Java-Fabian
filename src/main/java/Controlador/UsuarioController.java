package Controlador;

import Modelo.CRUDusuario;
import Modelo.SesionUsuario;
import Modelo.Usuario;
import java.util.Set;
import vista.FrmLogin;
import vista.FrmDashboard;

public class UsuarioController {
    private FrmLogin vista;
    private CRUDusuario modelo;
    private FrmDashboard dashboard;

    public UsuarioController(FrmLogin vista) {
        this.vista = vista;
        this.modelo = new CRUDusuario();
    }
    
    public void setDashboard(FrmDashboard dashboard) {
        this.dashboard = dashboard;
    }
    
    public void abrirDashboard() {
        if (dashboard != null) {
            dashboard.setVisible(true);
        }
    }

    public void iniciar() {
        this.vista.btnIngresar.addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String email = vista.txtEmail.getText();
        String password = new String(vista.txtPassword.getPassword());

        String resultado = modelo.validarUsuario(email, password);
        
        if (resultado.equals("Exito")) {
            Set<String> permisosUsuario = modelo.obtenerPermisosPorUsuario(email);
             
            Usuario usuarioLogeado = modelo.obtenerUsuarioLogeado(email);
            SesionUsuario.getInstancia().setUsuarioLogeado(usuarioLogeado);
            FrmDashboard dashboard = new FrmDashboard(permisosUsuario);
            dashboard.lblUsuario.setText(usuarioLogeado.getNombre());
            dashboard.setVisible(true);
            vista.dispose(); // Cerrar el FrmLogin
        }else{
            vista.lblErrorLogin.setText(resultado);
        }
    }
}