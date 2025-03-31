/**
 * Representa un elemento básico con información de código, precios, nombre y tipo (categoría).
 */
public class Item implements Comparable<Item> {
    private String code;             
    private double retailPrice;      
    private double currentPrice;     
    private String name;             
    private String type;             

    /**
     * Constructor de la clase Item.
     * @param code 
     * @param retailPrice 
     * @param currentPrice 
     * @param name 
     * @param type 
     */
    public Item(String code, double retailPrice, double currentPrice, String name, String type) {
        this.code = code;
        this.retailPrice = retailPrice;
        this.currentPrice = currentPrice;
        this.name = name;
        this.type = type;
    }

    /**
     * Obtiene el código del producto.
     * @return Código del produto (SKU)
     */
    public String getCode() { 
        return code; 
    }

    /**
     * Obtiene el precio de venta al público.
     * @return Precio retail
     */
    public double getRetailPrice() { 
        return retailPrice; 
    }

    /**
     * Obtiene el precio actual del producto.
     * @return Precio actual
     */
    public double getCurrentPrice() { 
        return currentPrice; 
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getName() { 
        return name; 
    }

    /**
     * Obtiene la categoría del producto.
     * @return Categoría o tipo
     */
    public String getType() { 
        return type; 
    }

    /**
     * Compara dos productos por su código SKU.
     * @param other Otro producto a comparar
     * @return Valor de comparación lexicográfica
     */
    @Override
    public int compareTo(Item other) {
        return this.code.compareTo(other.code);
    }

    /**
     * Devuelve una representación en texto del producto.
     * @return Cadena con los detalles principales del producto
     */
    @Override
    public String toString() {
        return "Item{code='" + code + "', name='" + name + "', price=" + currentPrice + "}";
    }
}
