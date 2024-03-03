class Quad(nombre: String,
           capacidadCombustible: Float,
           combustibleActual: Float,
           kilometrosActuales: Float,
           cilindrada: Int,
           private var tipo :TipoQuad
) :Motocicleta(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, cilindrada) {

    override val marca = ""
    override val modelo = ""

    /**
     * Calcula y devuelve la autonomía del automóvil en kilómetros. Si el automóvil es eléctrico,
     * se ajusta la eficiencia de combustible restando el ahorro eléctrico.
     *
     * @return La autonomía del automóvil en kilómetros como [Int].
     */
    override fun calcularAutonomia() =  super.calcularAutonomia() / 2

    /**
     * Sobrescribe el método toString de la clase [Quad] para proporcionar una representación en cadena de texto
     * específica del automóvil, incluyendo su estado de ser eléctrico además de los detalles heredados de Quad.
     *
     * @return Una cadena de texto que representa al Quad.
     */
    override fun toString() = "Quad(nombre=$nombre, tipo=${tipo.desc}, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, cilindrada=$cilindrada)"

    /**
     * Devuelve una cadena de texto con la información de los kilómetros que puede recorrer el Quad.
     *
     * @return Una cadena de texto que representa la información del Quad.
     */
    override fun obtenerInformacion() = "Quad(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"


}