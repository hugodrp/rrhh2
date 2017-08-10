/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJB
 *  javax.enterprise.context.SessionScoped
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.inject.Named
 *  javax.servlet.http.HttpSession
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB
 *  py.gov.mca.sisrrhh.utiles.Converciones
 */
package py.gov.mca.sisrrhh.managedbeans;

import java.io.PrintStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB;
import py.gov.mca.sisrrhh.utiles.Converciones;

@Named(value = "loginMB")
@SessionScoped
public class LoginMB
        implements Serializable {

    @EJB
    private FuncionariosSB ejbFuncionariosSB;
    private String loginUsuario;
    private String claveUsuario;
    private boolean menuLiquiHaberes;
    private boolean menuBeneficios;
    private Funcionarios funcionarioUsuario;

    @PostConstruct
    public void init() {
        this.setLoginUsuario("");
        this.setClaveUsuario("");
        this.menuBeneficios = false;
        this.menuLiquiHaberes = false;
    }

    public String btnIngresar() {
        if (this.getLoginUsuario() != null) {
            if (this.getLoginUsuario().equals("") || this.getClaveUsuario().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los campos con (*) no pueden estar vacio.", ""));
                return "/index";
            }
            this.setFuncionarioUsuario(null);
            this.setFuncionarioUsuario(this.ejbFuncionariosSB.findByUsuario(this.getLoginUsuario().trim()));
            if (this.getFuncionarioUsuario() != null) {
                Converciones c = new Converciones();
                String contrasenaMD5 = c.getMD5(this.getClaveUsuario());
                if (contrasenaMD5 == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo ingresar, intentelo de nuevo.", ""));
                    return "/index";
                }
                if (this.getFuncionarioUsuario().getContrasena().equals(this.getClaveUsuario()) && this.getFuncionarioUsuario().getEstadoFuncionario().getCodigo().equals("ACT")) {
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("funcionarioUsuario", (Object) this.getFuncionarioUsuario());
                    switch (this.getFuncionarioUsuario().getRolUsuario().getCodigo()) {
                        case "LQH": {
                            this.menuBeneficios = false;
                            this.menuLiquiHaberes = true;
                            break;
                        }
                        case "BEN": {
                            this.menuBeneficios = true;
                            this.menuLiquiHaberes = false;
                            break;
                        }
                        case "FUN": {
                            this.menuBeneficios = false;
                            this.menuLiquiHaberes = false;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso no permitido", ""));
                            break;
                        }
                        default: {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", ""));
                        }
                    }
                    System.out.println(this.getFuncionarioUsuario().getRolUsuario().getPaginaInicio());
                    return this.getFuncionarioUsuario().getRolUsuario().getPaginaInicio();
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrase\u00f1a no validos, intentelo de nuevo.", ""));
                return "/index";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no exite, intentelo de nuevo.", ""));
            return "/index";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los campos con null.", ""));
        return "/index";
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getClaveUsuario() {
        return this.claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public Funcionarios getFuncionarioUsuario() {
        return this.funcionarioUsuario;
    }

    public void setFuncionarioUsuario(Funcionarios funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public boolean isMenuLiquiHaberes() {
        return this.menuLiquiHaberes;
    }

    public void setMenuLiquiHaberes(boolean menuLiquiHaberes) {
        this.menuLiquiHaberes = menuLiquiHaberes;
    }

    public boolean isMenuBeneficios() {
        return this.menuBeneficios;
    }

    public void setMenuBeneficios(boolean menuBeneficios) {
        this.menuBeneficios = menuBeneficios;
    }
}
