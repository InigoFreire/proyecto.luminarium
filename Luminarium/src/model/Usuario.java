package model;

import java.time.YearMonth;

/**
 * Clase que representa un usuario en el sistema.
 */
public class Usuario {
    private String dni;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String metodoPago;
    private YearMonth fechaCaducidadTarjeta;
    private boolean adminCheck;
    
    /**
     * Crea una nueva instancia de Usuario con valores predeterminados.
     */
    public Usuario() {
        this.dni = "";
        this.password = "";
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.adminCheck = false;
    }
    
    /**
     * Crea una nueva instancia de Usuario con los valores especificados.
     * 
     * @param dni                   El DNI del usuario.
     * @param passwd                La contraseña del usuario.
     * @param nombre                El nombre del usuario.
     * @param apellido              El apellido del usuario.
     * @param email                 El correo electrónico del usuario.
     * @param metodoPago            El método de pago del usuario.
     * @param fechaCaducidadTarjeta La fecha de caducidad de la tarjeta de crédito del usuario.
     * @param adminCheck            Indica si el usuario es un administrador.
     */
    public Usuario(String dni, String passwd, String nombre, String apellido, String email, String metodoPago, YearMonth fechaCaducidadTarjeta, boolean adminCheck) {
        this.dni = dni;
        this.password = passwd;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.metodoPago = metodoPago;
        this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
        this.adminCheck = adminCheck;
    }
    
    /**
     * Crea una nueva instancia de Usuario con los valores especificados, excluyendo información de pago y administrador.
     * 
     * @param dni      El DNI del usuario.
     * @param passwd   La contraseña del usuario.
     * @param nombre   El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param email    El correo electrónico del usuario.
     */
    public Usuario(String dni, String passwd, String nombre, String apellido, String email) {
        this.dni = dni;
        this.password = passwd;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.metodoPago = "";
        this.fechaCaducidadTarjeta = null;
        this.adminCheck = false;
    }

    /**
     * Obtiene el DNI del usuario.
     * 
     * @return El DNI del usuario.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del usuario.
     * 
     * @param dni El DNI del usuario.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param apellido El apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el método de pago del usuario.
     * 
     * @return El método de pago del usuario.
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago del usuario.
     * 
     * @param metodoPago El método de pago del usuario.
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene la fecha de caducidad de la tarjeta de crédito del usuario.
     * 
     * @return La fecha de caducidad de la tarjeta de crédito del usuario.
     */
    public YearMonth getFechaCaducidadTarjeta() {
        return fechaCaducidadTarjeta;
    }

    /**
     * Establece la fecha de caducidad de la tarjeta de crédito del usuario.
     * 
     * @param fechaCaducidadTarjeta La fecha de caducidad de la tarjeta de crédito del usuario.
     */
    public void setFechaCaducidadTarjeta(YearMonth fechaCaducidadTarjeta) {
        this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
    }

    /**
     * Indica si el usuario es un administrador.
     * 
     * @return true si el usuario es un administrador, false de lo contrario.
     */
    public boolean isAdminCheck() {
        return adminCheck;
    }

    /**
     * Establece si el usuario es un administrador.
     * 
     * @param adminCheck true si el usuario es un administrador, false de lo contrario.
     */
    public void setAdminCheck(boolean adminCheck) {
        this.adminCheck = adminCheck;
    }

    @Override
    public String toString() {
        return "Usuario [dni=" + dni + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido
                + ", email=" + email + ", metodoPago=" + metodoPago + ", fechaCaducidadTarjeta=" + fechaCaducidadTarjeta
                + ", adminCheck=" + adminCheck + "]";
    }    
}
