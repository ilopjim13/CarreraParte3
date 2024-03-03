class Camion(nombre: String,
             capacidadCombustible: Float,
             combustibleActual: Float,
             kilometrosActuales: Float,
             esHibrido: Boolean,
             private var peso :Int) :Automovil(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido) {

    override val marca = ""
    override val modelo = ""
    private val variable = this.peso / 1000
    private val kmFinal = KM_L + (variable * 0.2f)

    init {
        require(this.peso in (1000..10000)) {"El peso debe estar entre 1000 y 10000 Kg"}
    }

    companion object {

        const val KM_L = (100/16)
    }

    /**
     * Calcula y devuelve la autonomía del automóvil en kilómetros. Si el camion es eléctrico,
     * se ajusta la eficiencia de combustible restando el ahorro eléctrico.
     *
     * @return La autonomía del camion en kilómetros como [Int].
     */
    override fun calcularAutonomia() = combustibleActual * kmFinal

    /**
     * Actualiza la cantidad de combustible actual basado en la distancia recorrida, considerando la eficiencia de
     * combustible del camion.
     *
     * @param distanciaReal La distancia real recorrida por el vehículo.
     */
    override fun actualizaCombustible(distanciaReal: Float) {
        val combustibleGastado = (distanciaReal / KM_L)
        combustibleActual -= combustibleGastado.redondear(2)
    }

    /**
     * Sobrescribe el método toString de la clase [Camion] para proporcionar una representación en cadena de texto
     * específica del automóvil, incluyendo su estado de ser eléctrico además de los detalles heredados de Vehiculo.
     *
     * @return Una cadena de texto que representa al automóvil.
     */
    override fun toString() ="Camion(nombre=$nombre, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido, peso=$peso)"

    /**
     * Devuelve una cadena de texto con la información de los kilómetros que puede recorrer el Camion.
     *
     * @return Una cadena de texto que representa la información del Camion.
     */
    override fun obtenerInformacion() = "Vehiculo(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"




}