class Quad(nombre: String,
           capacidadCombustible: Float,
           combustibleActual: Float,
           kilometrosActuales: Float,
           cilindrada: Int,
           var tipo :TipoQuad
) :Motocicleta(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, cilindrada) {

    override val marca = ""
    override val modelo = ""

    override fun calcularAutonomia(): Float {
        return super.calcularAutonomia() / 2
    }

    override fun toString(): String {
        return "Quad(nombre=$nombre, tipo=${tipo.desc}, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, cilindrada=$cilindrada)"
    }

    override fun obtenerInformacion(): String {
        return "Quad(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"
    }

}