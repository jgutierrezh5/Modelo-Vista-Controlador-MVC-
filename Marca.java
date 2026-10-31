package modelo;

public class Marca {

    private final int id;
    private final String nombre;
    private final String pais;

    public Marca(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Marca(String nombre, String pais) {
        this(0, nombre, pais);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }
}
