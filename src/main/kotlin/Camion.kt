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
        val km_por_litro = 100/16
        val variable = peso /

        return combustibleActual * km_por_litro
    }



}