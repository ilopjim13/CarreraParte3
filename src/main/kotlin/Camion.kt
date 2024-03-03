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

    override fun calcularAutonomia() = combustibleActual * kmFinal

    override fun actualizaCombustible(distanciaReal: Float) {
        val combustibleGastado = (distanciaReal / KM_L)
        combustibleActual -= combustibleGastado.redondear(2)
    }

    override fun toString() ="Camion(nombre=$nombre, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido, peso=$peso)"


    override fun obtenerInformacion() = "Vehiculo(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"




}