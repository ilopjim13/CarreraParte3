
/**
 * Representa un vehículo genérico con propiedades básicas y funcionalidades para calcular la autonomía, realizar viajes,
 * y repostar combustible.
 *
 * @property nombre El nombre del vehículo.
 * @property marca La marca del vehículo.
 * @property modelo El modelo del vehículo.
 * @property capacidadCombustible La capacidad total del tanque de combustible en litros. Se inicializa redondeando el valor de combustible actual.
 * @property combustibleActual La cantidad actual de combustible en litros, ajustada y redondeada según el consumo.
 * @property kilometrosActuales El kilometraje actual del vehículo.
 * @constructor Crea una instancia de un vehículo con los parámetros especificados y valida las condiciones iniciales.
 */
open class Vehiculo(
    val nombre: String,
    protected open val marca: String,
    protected open val modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    var kilometrosActuales: Float
) {

    protected val capacidadCombustible = capacidadCombustible.redondear(2)
    var combustibleActual = combustibleActual.redondear(2)
        set(value) {
            // Si por pequeños errores de redondeo el valor es negativo, establecemos el valor 0,
            // sino asignamos el valor redondeado a 2 decimales
            field = if (value < 0) 0f else value.redondear(2)
        }

    var paradas = 0

    init {
        require(capacidadCombustible > 0) { "La capacidad del tanque debe ser un valor positivo." }
        require(combustibleActual >= 0) { "El combustible actual no puede ser negativo." }
        require(this.nombre.isNotBlank()) {"El nombre no puede estar vacío."}
        require(!agregarNombres(this.nombre)) {"El nombre no se puede repetir."}
    }

    companion object {
        const val KM_POR_LITRO = 10.0f // 10 KM por litro.
         val nombres = mutableSetOf("")
        private fun agregarNombres(nombre:String) = !nombres.add(nombre)
    }

    /**
     * Sobrescribe el método toString para proporcionar una representación en cadena de texto
     * del vehículo.
     *
     * @return Una cadena de texto que representa al vehiculo.
     */
    override fun toString(): String {
        return "Vehículo: $nombre, Marca: $marca, Modelo: $modelo, Kilómetros Actuales: $kilometrosActuales, Combustible Actual: $combustibleActual L."
    }

    /**
     * Calcula y devuelve la autonomía del vehículo en kilómetros, basada en la cantidad actual de combustible y
     * la eficiencia de combustible definida.
     *
     * @return La autonomía del vehículo en kilómetros como [Int].
     */
    open fun calcularAutonomia(): Float {
        return (combustibleActual * KM_POR_LITRO).redondear(2) // Cada litro da para 10 km.
    }

    /**
     * Devuelve una cadena de texto con la información de los kilómetros que puede recorrer el vehículo.
     *
     * @return Una cadena de texto que representa la información del vehículo.
     */
    open fun obtenerInformacion(): String {
        return "Vehiculo(km = ${this.kilometrosActuales}, combustible = ${this.combustibleActual} L)"
    }

    /**
     * Intenta realizar un viaje con la distancia especificada. Actualiza el combustible actual y los kilómetros recorridos.
     * Si no hay suficiente combustible para completar el viaje, recorre solo la distancia que el combustible permita.
     *
     * @param distanciaARecorrer La distancia que el vehículo intentará recorrer.
     * @return La distancia restante que no se pudo recorrer por falta de combustible, como [Int].
     */
    fun realizaViaje(distanciaARecorrer: Float): Float {
        val distanciaRecorrida = minOf(calcularAutonomia(), distanciaARecorrer)
        actualizaCombustible(distanciaRecorrida)
        actualizaKilometros(distanciaRecorrida)
        return (distanciaARecorrer - distanciaRecorrida)
    }

    fun retrasarVehiculo(cantidadRetrasar:Int) {
        if(kilometrosActuales - cantidadRetrasar > 0) kilometrosActuales -= cantidadRetrasar
        else kilometrosActuales = 0f
    }

    /**
     * Actualiza el kilometraje actual del vehículo, sumando la distancia recorrida especificada.
     *
     * @param distanciaRecorrida La distancia recorrida que se añadirá al kilometraje actual del vehículo.
     */
    private fun actualizaKilometros(distanciaRecorrida: Float) {
        kilometrosActuales += distanciaRecorrida
    }

    /**
     * Actualiza la cantidad de combustible actual basado en la distancia recorrida, considerando la eficiencia de
     * combustible del vehículo.
     *
     * @param distanciaReal La distancia real recorrida por el vehículo.
     */
    protected open fun actualizaCombustible(distanciaReal: Float) {
        val combustibleGastado = (distanciaReal / KM_POR_LITRO)
        combustibleActual -= combustibleGastado.redondear(2)
    }

    /**
     * Reposta combustible al vehículo. Si se especifica una cantidad, se añade esa cantidad al combustible actual
     * hasta el máximo de la capacidad. Si no se especifica o es menor o igual a cero, se llena el tanque al máximo.
     *
     * @param cantidadARepostar La cantidad de combustible a repostar. Si es menor o igual a cero, se llena el
     * tanque completamente.
     * @return La cantidad de combustible efectivamente repostada, como [Float].
     */
    open fun repostar(cantidadARepostar: Float = 0f): Float {
        val combustiblePrevio = combustibleActual
        paradas++

        if (cantidadARepostar <= 0)
            combustibleActual = capacidadCombustible // LLENO
        else
            combustibleActual = minOf(capacidadCombustible, combustibleActual + cantidadARepostar)

        return combustibleActual - combustiblePrevio
    }
}
