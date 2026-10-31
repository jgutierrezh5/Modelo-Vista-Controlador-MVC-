package modelo;

public class Cliente {

    private final int id;
    private final String nombre;
    private final String telefono;
    private final String correo;

    public Cliente(int id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Cliente(String nombre, String telefono, String correo) {
        this(0, nombre, telefono, correo);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}
