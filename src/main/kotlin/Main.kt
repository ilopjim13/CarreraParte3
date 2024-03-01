
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Extiende la clase [Float] para permitir el redondeo del número a un número específico de posiciones decimales.
 *
 * @param posiciones El número de posiciones decimales a las que se redondeará el valor.
 * @return Un [Float] redondeado al número de posiciones decimales especificadas.
 */
fun Float.redondear(posiciones: Int): Float {
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

/**
 * Funcion extendida de String que permite eliminar los espacios sobrantes al principio y fina de la cadena de caracteres
 * y capitaliza todas las palabras seguida de otra
 * @return String retorna el string sin espacios a la izquierda y derecha y las palabras capitalizadas
 */
fun String.espacios(): String {
    val espacios = this.split(" ").toMutableList()
    val palabras:MutableList<String> = mutableListOf()
    espacios.forEach{if (it.isNotBlank()) palabras.add(it)}
    return palabras.joinToString(" ") { i -> i.replaceFirstChar { it.uppercase() } }
    //return this.trim().split(" ").joinToString(" ") { i -> i.replaceFirstChar { it.uppercase() } }
}

fun pedirParticipantes() :Int {
    var numero = -1
    do {
        print("Introduce el número de participantes: ")
        try {
            numero = readln().toInt()
            if(numero < 0) {
                println("**ERROR** El número debe ser mayor a 0.")
            }
        } catch (e:NumberFormatException){
            println("**ERROR** Debe de ser un número.")
        }
    }while (numero <= 0)

    return numero
}

fun pedirNombre() :String {
    var nombre: String
    do {
        nombre = readln().espacios()
        if(nombre in Vehiculo.nombres) {
            println("**ERROR** El nombre no puede repetirse ni estar vacío")
            print("* Intoducelo de nuevo -> ")
        }
    } while(nombre in Vehiculo.nombres)
    return  nombre
}

fun automovilAleatorio(nombre:String):Vehiculo{
    val marcas = listOf("Seat","BMW","Cintroen","Renault").random()
    val modelos = listOf("Panda","M8","Sor","Espacio").random()
    val capacidad = (30..60).random().toFloat()
    val actual = capacidad * (2..10).random()/10
    return Automovil(nombre, marcas, modelos, capacidad , actual, 0f, listOf(false, true).random())
}

fun motocicletaAleatorio(nombre:String):Vehiculo{
    val marcas = listOf("Seat","BMW","Cintroen","Renault").random()
    val modelos = listOf("Panda","M8","Sor","Espacio").random()
    val capacidad = (15..30).random().toFloat()
    val actual = capacidad * (2..10).random()/10
    val cilindradas = listOf(125,250,400,500,750,900,1000).random()
    return Motocicleta(nombre, marcas, modelos, capacidad , actual, 0f, cilindradas )
}

fun camionAleatorio(nombre:String):Vehiculo{
    val capacidad = (15..30).random().toFloat()
    val actual = capacidad * (2..10).random()/10
    val peso = (1000..10000).random()
    return Camion(nombre, capacidad , actual, 0f, listOf(true,false).random(), peso)
}

fun quadAleatorio(nombre:String):Vehiculo{
    val capacidad = (15..30).random().toFloat()
    val actual = capacidad * (2..10).random()/10
    val cilindradas = listOf(125,250,400,500,750,900,1000).random()
    val tipo = listOf(TipoQuad.ESPECIALES, TipoQuad.LIGEROS, TipoQuad.NOLIGEROS).random()
    return Quad(nombre, capacidad , actual, 0f, cilindradas, tipo )
}

fun elegirVehiculosAleatorios(cantidad:Int) :List<Vehiculo> {
    var vehiculo:Vehiculo
    val vehiculos = mutableListOf<Vehiculo>()
    var cont = 0
    for (i in 1..cantidad) {

        print("\n* Nombre del vehículo ${++cont} -> ")
        val nombre = pedirNombre()
        val alAzar = (1..4).random()

        when (alAzar) {
            1 -> { vehiculo = automovilAleatorio(nombre)
                println("\nTe ha tocado un $vehiculo")
                vehiculos.add(vehiculo) }

            2-> { vehiculo = motocicletaAleatorio(nombre)
                println("\nTe ha tocado un $vehiculo")
                vehiculos.add(vehiculo) }

            3-> { vehiculo = camionAleatorio(nombre)
                println("\nTe ha tocado un $vehiculo")
                vehiculos.add(vehiculo) }

            4-> { vehiculo = quadAleatorio(nombre)
                println("\nTe ha tocado un $vehiculo")
                vehiculos.add(vehiculo) }
        }
    }
    return vehiculos
}

/**
 * Punto de entrada del programa. Crea una lista de vehículos y una carrera, e inicia la carrera mostrando
 * los resultados al finalizar.
 */
fun main() {

    /*val vehiculos = listOf(
        Automovil("aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true),
        Automovil("Boreal m8", "BMW", "M8", 80f, 80f * 0.1f, 0f, false),
        Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500),
        Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true),
        Automovil("eclipse negro", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false),
        Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250)
    )*/

    println("** ELEGIR PARTICIPANTES **\n")

    val cantidad = pedirParticipantes()

    val vehiculos = elegirVehiculosAleatorios(cantidad)

    val carrera = Carrera("Gran Carrera de Filigranas", 1000f, vehiculos)

    println("\n*** ${carrera.nombreCarrera} ***\n")
    carrera.iniciarCarrera()

    val resultados = carrera.obtenerResultados()

    println("* Clasificación:\n")
    resultados.forEach { println("${it.posicion} -> ${it.vehiculo.nombre} (${it.vehiculo.kilometrosActuales} kms)") }

    println("\n" + resultados.joinToString("\n") { it.toString() })

    println("\n* Historial Detallado:\n")
    resultados.forEach { println("${it.posicion} -> ${it.vehiculo.nombre}\n${it.historialAcciones.joinToString("\n")}\n") }
}

