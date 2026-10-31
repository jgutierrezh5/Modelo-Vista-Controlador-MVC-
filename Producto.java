package modelo;

import java.math.BigDecimal;

public class Producto {

    private final int id;
    private final String nombre;
    private final BigDecimal precio;
    private final int stock;
    private final int marcaId;
    private final String marcaNombre;

    public Producto(int id, String nombre, BigDecimal precio, int stock, int marcaId, String marcaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.marcaId = marcaId;
        this.marcaNombre = marcaNombre;
    }

    public Producto(String nombre, BigDecimal precio, int stock, int marcaId) {
        this(0, nombre, precio, stock, marcaId, null);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }
}
