import java.io.*;
import java.lang.Math;

//Autor Gabriela Hidalgo Prendas & Andrés Orué Moraga
public class GabrielaHidalgo_AndresOrue_EntregaFinal {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static String[] equipo;
    static double numJuegos[];
    static int numOponentes[];
    static String resultados[];
    static double puntajeTotal[];
    static boolean validarInicializacion = false;
    static int acumuladorCasa[];
    

    public static void main(String[] args) throws IOException {
        out.println("||------------------------------------------------------------------||");
        out.println("||-----  Bienvenido al sistema del torneo de Quidditch -------------||");
        out.println("||------------------------------------------------------------------||");

        int opcion = -1;
        do {
            opcion = mostrarMenu();
            procesarOpcion(opcion);

        } while (opcion != 10);
    }

    static int mostrarMenu() throws IOException {
        int opcion;
        out.println("||------------------------------------------------------------------||");
        out.println("||------------MENU DIGITE LA OPCIÓN A REALIZAR----------------------||");
        out.println("||------------------------------------------------------------------||");
        out.println("1- Registrar la información de los equipos");
        out.println("2- Hacer reporte de resultado por cada equipo y calcular el puntaje total");
        out.println("3- Imprimir reporte de resultados de cada equipo");
        out.println("4- Imprimir puntaje Total de cada Equipo");
        out.println("5- Calcular el promedio de oponentes diferentes");
        out.println("6- Imprimir el Equipo con mayor partidos");
        out.println("7- Imprimir el Equipo con menor puntaje");
        out.println("8- Imprimir equipos con más de 5 partidos");
        out.println("9- Imprimir equipos con más oponentes que el promedio");
        out.println("10- SALIR");
        opcion = Integer.parseInt(in.readLine());
        return opcion;
    }

    static void procesarOpcion(int opcion) throws IOException {
        switch (opcion) {
            case 1:
            out.println("=====================================================");
            out.println("||      REGISTRE LA INFORMACIÓN DE CADA EQUIPO     ||");
            out.println("=====================================================");
                InicializarArreglos();
                registrarInformacion();
                validarInicializacion = true;
                break;
            case 2:
                    
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||      INTRODUZCA RESULTADOS Y CALCULE PUNTAJE    ||");
                out.println("=====================================================");
                resultadoCadaEquipo();
                puntajeTotal();     
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 3:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("|| Impresión Detallada de Resultados de cada Equipo ||");
                out.println("=====================================================");
                ReporteResultadosCadaEquipo();
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 4:
            if (validarInicializacion !=false){
                out.println("=========================================================");
                out.println("|| Impresión Detallada de Puntaje Total de cada Equipo ||");
                out.println("=========================================================");
                reportePuntajeTotal(); 
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 5:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||                  PROMEDIO DE OPONENTES          ||");
                out.println("=====================================================");
               double promedioOponentes = promedioOponentes();
               out.println("el promedio de oponentes es: " + promedioOponentes + " oponentes.");
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 6:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||              EQUIPO CON MAYOR PARTIDOS          ||");
                out.println("=====================================================");
                // EquipoMayorPartidos
                mayorPartidos ();
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 7:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||            EQUIPO CON MENOR PUNTAJE             ||");
                out.println("=====================================================");
                equipoMenorPuntaje();
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 8:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||         EQUIPO CON MAS DE CINCO PARTIDOS        ||");
                out.println("=====================================================");
                equipoConMasCincoPartidos();
               
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 9:
            if (validarInicializacion !=false){
                out.println("=====================================================");
                out.println("||             MAS OPONENTES QUE PROMEDIO           ||");
                out.println("=====================================================");
                // EquipoMasOponentesQuePromedio
                String equiposPromedio = EquiposConMasOponentesQuePromedio();
                out.println("Los equipos con más oponentes que el promedio fueron" + equiposPromedio);
                
            }
            else {out.println("Opción no válida, no has inicializado los arreglos");
        }
                break;
            case 10:
                // SalirDelPrograma
                opcion = 10;
                break;
            default:
                out.println("Opción inváida");
                break;
        }
    }

	public static void InicializarArreglos() throws IOException {
        out.println("Por favor digite el numero total de Equipos del Torneo: ");
        int numEquipos = Integer.parseInt(in.readLine());
        equipo = new String[numEquipos];
        numJuegos = new double [numEquipos];
        numOponentes = new int[numEquipos];
        resultados = new String[numEquipos];
        puntajeTotal = new double[numEquipos];
        acumuladorCasa = new int[100];

    }

    public static void registrarInformacion() throws IOException {
        for (int i = 0; i < equipo.length; i++) {
            out.println("Por favor ingrese el nombre del equipo " + (i + 1) + ":  ");
            equipo[i] = in.readLine();
            out.println("Por favor ingrese el número de partidos jugados por " + equipo[i] + ":  ");
            numJuegos[i] = Double.parseDouble(in.readLine());
            out.println("Por favor ingrese el número de oponentes distintos que " + equipo[i] + " enfrentó:  ");
            numOponentes[i] = Integer.parseInt(in.readLine());
        }
    }

    public static void resultadoCadaEquipo() throws IOException {
        for (int l = 0; l < equipo.length; l++) {
            String preResultado = "";
            String resultadoAcumulado = "";
            double resultadoCasa = 0;
            
            out.println("=====================================================");
            out.println("||     Reporte de Partidos de " + equipo[l] + "   ||");
            out.println("=====================================================");

            for (int m = 0; m < numJuegos[l]; m++) {
                out.println("=====================================================");
                out.println("||             Partido " + (m + 1) + "                       ||");
                out.println("=====================================================");
                out.println("Digite el nombre del oponente " + (m + 1) + ": ");
                String nombreOponente = in.readLine();
                out.println("Digite cuántos goles anotó " + nombreOponente + ": ");
                double resultadoOponente = (Double.parseDouble(in.readLine())*10);
                out.println("Digite cuántos goles anotó " + equipo[l] + ": ");
                resultadoCasa = (Double.parseDouble(in.readLine())*10);
                out.println("Digite si " + equipo[l] + " atrapó o no el snitch (Digite si o no): ");
                String snitch = in.readLine();
                if (snitch.equals("si")) {
                    resultadoCasa += 30;
                    preResultado += equipo[l] + " " + "vs" + " " + nombreOponente + " " + ":" + " " + "*"
                            + (resultadoCasa + "" + "-" + "" + resultadoOponente + "\n");
                        

                } else {
                    resultadoOponente += 30;
                    preResultado += equipo[l] + " " + "vs" + " " + nombreOponente + " " + ":" + " " + resultadoCasa + ""
                            + "-" + "" + (resultadoOponente + "*" + "\n");
                    
                }
            
            }
            acumuladorCasa[l] += resultadoCasa;
            resultadoAcumulado += preResultado;
            resultados[l] = resultadoAcumulado;

        }
    }

    public static void ReporteResultadosCadaEquipo() throws IOException {
        String reporteResultado = " ";
        for (int r = 0; r < equipo.length; r++) {
            reporteResultado += ("El equipo " + equipo[r] + " obtuvo los siguientes resultados: " + resultados[r]
                    + "\n \n");
        }
        out.println(reporteResultado);
    }

    public static void reportePuntajeTotal() throws IOException {
        String reportePuntajeTotal = " ";
        for (int r = 0; r < equipo.length; r++) {
            reportePuntajeTotal += ("El equipo " + equipo[r] + " obtuvo el puntaje Total de : " + puntajeTotal[r]
                    + " \n \n");
        }
        out.println(reportePuntajeTotal);
    }


    public static void puntajeTotal() throws IOException {
        
        for (int p = 0; p < equipo.length; p++) {
            double puntajeJuegos = 0;
            double puntajeNumOponentes = 0;

            if (numJuegos[p] >= 5) {
                puntajeJuegos =1;
            }
            else {
                puntajeJuegos = ((Math.sqrt(numJuegos[p]))/2.25);
            }
            if (numOponentes[p] == 1){
                puntajeNumOponentes = (1/3);
            }
            else if (numOponentes[p]== 2) {
                puntajeNumOponentes = (2/3);
            }
            else if (numOponentes[p] >= 3) {
                puntajeNumOponentes = 1;
            }
            puntajeTotal[p] = puntajeNumOponentes + puntajeJuegos + acumuladorCasa[p];
            
    }
    

    
}


public static double promedioOponentes() {
    double sum = 0;
    for (int i = 0; i < numOponentes.length; i++) {
        sum+= numOponentes[i];
    }
    
    return sum / numOponentes.length; 
}
public static String EquiposConMasOponentesQuePromedio() {
    String masOponentesQuePromedio = "";
    double oponentesProm = promedioOponentes();
    for (int i = 0; i < numOponentes.length; i++) {
        if (numOponentes[i] >= oponentesProm) {
            masOponentesQuePromedio += equipo[i] + ", ";
        }
    }
    return masOponentesQuePromedio;
}
public static void equipoConMasCincoPartidos() {
    String masCincoPartidos = "";
    for (int i = 0; i < equipo.length; i++) {
        if (numJuegos[i] >= 5) {
            masCincoPartidos += equipo[i] + ", ";
        }
    }
    out.println(masCincoPartidos);
}
public static void mayorPartidos (){
    double mayorPartidos = 0;
    String equipoMayorPartido = "";
for (int i = 0; i < equipo.length; i++) {
    if (mayorPartidos < numJuegos[i]) {
        mayorPartidos = numJuegos[i];
        equipoMayorPartido = equipo[i];
}
}
out.println ("El equipo con mayor partidos es: " +  equipoMayorPartido + " con " + mayorPartidos + ". "); 
}
public static void equipoMenorPuntaje(){
    double puntajeMenor = 0;
    String equipoPuntajeMenor = ""; 
    for (int i = 0; i < equipo.length; i++){
        for (int j = i + 1; j < equipo.length;j++)
            if (puntajeTotal[i] < puntajeTotal[j]){
                puntajeMenor = puntajeTotal[i];
                equipoPuntajeMenor = equipo[i];
            }
    }
    out.println ("El equipo con menor puntaje es: " +  equipoPuntajeMenor + " con " + puntajeMenor + ". "); 
}

}
