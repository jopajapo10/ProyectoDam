
package Models;

public class Productos {
    private int id;
    private String codigo; 
    private String descripcion;
    private int cantidad;
    private double precio_compra;
    private double precio_venta;
    private int id_proveedor;
    private int id_medidas;
    private int id_categorias;
    private String estado;
    private String proveedor;
    private String medida;
    private String categoria;

    public Productos(){
        
    }

    public Productos(int id, String codigo, String descripcion, int cantidad, double precio_compra, double precio_venta, int id_proveedor, int id_medidas, int id_categorias, String estado, String proveedor, String medida, String categoria) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.id_proveedor = id_proveedor;
        this.id_medidas = id_medidas;
        this.id_categorias = id_categorias;
        this.estado = estado;
        this.proveedor = proveedor;
        this.medida = medida;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_medidas() {
        return id_medidas;
    }

    public void setId_medidas(int id_medidas) {
        this.id_medidas = id_medidas;
    }

    public int getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(int id_categorias) {
        this.id_categorias = id_categorias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}