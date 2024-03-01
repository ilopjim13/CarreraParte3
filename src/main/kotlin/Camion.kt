class Camion(nombre: String,
             capacidadCombustible: Float,
             combustibleActual: Float,
             kilometrosActuales: Float,
             esHibrido: Boolean,
             var peso :Int) :Automovil(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido) {

    override val marca = ""
    override val modelo = ""

    init {
        require(this.peso in (1000..10000)) {"El peso debe estar entre 1000 y 10000 Kg"}
    }

    override fun calcularAutonomia(): Float {
        val variable = peso / 1000
        val km_por_litro = (100/16) + (variable * 0.2f)

        return combustibleActual * km_por_litro
    }

    override fun toString(): String {
        return "Camion(nombre=$nombre, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido, peso=$peso)"
    }

    override fun obtenerInformacion(): String {
        return "Vehiculo(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"
    }



}