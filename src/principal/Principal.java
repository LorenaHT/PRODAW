package principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import auxiliar.Practicas;
import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Profesor;

public class Principal {
	// metodo por el que debe empezar la ejecución ..
	public static void main(String[] args) {

		// PRIMERA EVALUACION

		/*
		 * Persona persona; persona = new Persona();
		 * 
		 * persona.setNombre("Manolo"); System.out.println("nif : " + persona.getNif());
		 * System.out.println("nombre : " + persona.getNombre());
		 * System.out.println("altura : " + persona.getAltura());
		 * System.out.println("persona1 creada");
		 */
		/*
		 * Persona padre = new Persona(); padre.setNombre("JorgePadre");
		 * padre.setSexo('M'); Persona madre = new Persona();
		 * madre.setNombre("MariaMadre"); madre.setNif("44567981H"); madre.setSexo('F');
		 */

		/*
		 * Persona persona2 = new Persona("43897124R", "PEPE", 'F', LocalDate.of(1999,
		 * 9, 21), 170, padre, madre); System.out.println("persona2 creada");
		 * System.out.println("Nombre madre : " + persona2.getMadre().getNombre());
		 */
		// crear un Estudiante

		Estudiante estAnonimo = new Estudiante(123);

		Estudiante estudiante = new Estudiante(111, "44556677G", "Rafael", 'M', LocalDate.now(), 187, estAnonimo, null);
		Estudiante estudiante2 = new Estudiante(111, "44556674T", "Javier", 'M', LocalDate.now(), 187, estAnonimo,
				null);
		Estudiante estudiante3 = new Estudiante(111, "44556672X", "Marcos", 'M', LocalDate.now(), 187, estAnonimo,
				null);

		Estudiante[] listaEstudiantes = { estAnonimo, estudiante, estudiante2, estudiante3 };

		Practicas practicas = new Practicas();
		practicas.ordenaEstudiantes(listaEstudiantes);

		// new Practicas().muestraNumerosDe1A1000();
		// practicas.muestraNumerosDe1A1000();
		// practicas.muestraNumeros3(20, 25);
		// practicas.generaAleatorios2(3,10,11);
		// practicas.estadisticaCadena("Las&%=\" PalmAs\\ de Gran CanarIa");
		// int [] numeros=practicas.generaAleatorios3(100, 1, 6);
		// int[] frecuencia = practicas.frecuenciaAparicion(100, 1, 8);
		// practicas.listaDiasSemana();
		// Estudiante[] lista = new Datos().getEstudiantes();
		// practicas.listaEstudiantes(new Datos().getEstudiantes());
		int[][] visitantesYear = { { 2, 4, 5, 1, 3, 2, 1, 1, 1, 3, 5, 1 },
				{ 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 13 }, { 23, 3, 56, 12, 32, 23, 17, 12, 11, 34, 45, 12 },
				{ 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 13 }, { 23, 4, 56, 12, 32, 23, 17, 12, 11, 34, 45, 10 },
				{ 23, 3, 56, 12, 32, 23, 17, 12, 11, 34, 45, 45 }, { 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 37 }

		};
		String[] islas = { "GC", "LTE", "FTV", "TFE", "LPA", "GOM", "HIE" };
		String[] meses = { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" };

		int isla = 2;
		int mes = 7;
		/*
		 * System.out.println("Visitantes en "+ islas[isla] +" : " +
		 * practicas.visitantesIslaYear(isla, visitantesYear));
		 * System.out.println("Visitantes  en Canarias en el mes " + meses[mes]+ " : " +
		 * practicas.visitantesMesYear(mes, visitantesYear));
		 */

		/*
		 * String[] misDatos = { "123","abc", "345", "1x2", "990" }; //int[] res =
		 * practicas.convierteCadenasANumeros(misDatos); float saldo= 1000.0f; float[]
		 * movimientos= {10.0f, -5.0f,20.5f};
		 * 
		 * float saldoFinal = practicas.calculaSaldo(saldo, movimientos);
		 */
		int[] datos = practicas.generaAleatorios3(500, 1, 500);
		// practicas.ordenaEnteros(datos);
		// Arrays.sort(datos);
		String[] cadenas = { "perro", "gato", "alce" };
		practicas.ordenaCadenas(cadenas);
		int[] array2 = { 3, 6, 9, 9, 9, 15, 29 };
		int[] array1 = { 1, 2, 9, 9, 25, 39, 56, 67, 99 };
		int[] arrayMezclado = practicas.mezclaArrays(array1, array2);
		// liga
		String[][] goles = new Datos().getResultados();

		int[] clasificacion = practicas.obtenerClasificacion2(goles);
		String[] equipos = new Datos().getEquipos();
		practicas.ordenaClasificacion(clasificacion, equipos);
		for (int i = 0; i < equipos.length; i++) {
			// System.out.println(equipos[i] + " \t" + clasificacion[i]);

		}
		int[][] puntosJornadas = new Datos().getPuntosJornada();
		Equipo[] clasi = practicas.obtenerClasificacion3(puntosJornadas);
		String nif = "345239";
		// System.out.println(practicas.validarNif(nif)?"OK":"KO");
		int x = 67;
		// System.out.println("El numero "+ x + (practicas.esPrimo(x)?" ES ":" NO ES ")+
		// " PRIMO" );
		int[][] matriz = { { 3, 4, 8 }, { 6 }, { 5, 9 } };
		Integer[][] matriz2 = { { 3, 4, null, 8, null, 12, 37, null }, { 6, 7, 12, null, 34, 21, null, 11 },
				{ 5, null, 9 } };
		// practicas.recorrerMatrizIrregularPorColumnas(matriz);
		practicas.recorrerMatrizIrregularPorColumnas2(matriz2);
		// int[] primos = practicas.numerosPrimos(100);
		// int [] fibonacci = practicas.numerosFibonacci(20);
		System.out.println("fin");

		// SEGUNDA EVALUACION

		practicas.listaEstudiantes(practicas.introListas());
		System.out.println("---");
		ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
		Estudiante est1 = new Estudiante(321);
		Estudiante est2 = new Estudiante(123);
		lista.add(est1);
		lista.add(est2);
		practicas.listaEstudiantes(lista);

		System.out.println("-----FIN------");

		ArrayList<String> cadenasArrayList = new ArrayList<String>();
		String cadena1 = new String("123");
		String cadena2 = new String("456");
		String cadena3 = new String("Hola");
		cadenasArrayList.add(cadena1);
		cadenasArrayList.add(cadena2);
		cadenasArrayList.add(cadena3);
		System.out.println(practicas.convierteCadenasANumeros(cadenasArrayList));
		System.out.println("-----FIN------");

		int numero = 12;
		System.out.println(practicas.numerosPrimosArrayList(numero));
		System.out.println("-----FIN------");

		int cuantosFibonacci = 15;
		System.out.println(practicas.numerosFibonacciArrayList(cuantosFibonacci));
		System.out.println("-----FIN------");

		ArrayList<Integer> enteros = new ArrayList<Integer>();
		int entero1 = 45;
		int entero2 = 678;
		int entero3 = 23;
		enteros.add(entero1);
		enteros.add(entero2);
		enteros.add(entero3);
		practicas.ordenaEnteros(enteros);
		System.out.println(enteros);
		System.out.println("-----FIN------");

		ArrayList<Integer> clasificacionLista = new ArrayList<Integer>();
		clasificacionLista.add(23);
		clasificacionLista.add(2);
		clasificacionLista.add(56);
		ArrayList<String> equiposLista = new ArrayList<String>();
		equiposLista.add("Barcelona");
		equiposLista.add("Madrid");
		equiposLista.add("Deportivo");
		practicas.ordenaClasificacion(clasificacionLista, equiposLista);
		System.out.println(clasificacionLista + "" + equiposLista);
		System.out.println("-----FIN------");
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		l1.add(23);
		l1.add(34);
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		l2.add(45);
		l2.add(1);
		System.out.println(practicas.mezclaArrays(l1, l2));
		System.out.println("-----FIN------");
		ArrayList<ArrayList<Integer>> resultado = practicas.convierteMatrizArrayLista(matriz);
		System.out.println(resultado);
		System.out.println("-----FIN------");
		System.out.println(practicas.introMapas());
		System.out.println("-----FIN------");
		ArrayList<String> lista2 = new ArrayList<String>();
		lista2.add("12");
		lista2.add("19");
		lista2.add("-12");
		lista2.add("1x2");
		ArrayList<Integer> numeros = practicas.convierteCadenasANumeros(lista2);
		HashMap<String, Estudiante> mapa = practicas.introMapas();
		Estudiante noexisto = mapa.get("noexisto");
		Set<String> claves = mapa.keySet();
		for (String clave : claves) {
			System.out.println(mapa.get(clave).getNombre());
		}

		practicas.leerFicheroTexto();
		System.out.println(practicas.LeerFicheroArrayList("ficheros/Persona.txt"));
		System.out.println(practicas.LeerFicheroHashMapYCalcularEdadMedia("ficheros/Persona.txt", "45363715X"));
		ArrayList<Float> movimientos = new ArrayList<Float>();
		movimientos.add(1000f);
		movimientos.add(300.23f);
		movimientos.add(5.76f);
		movimientos.add(456f);
		System.out.println(practicas.calculaSaldo(1500, movimientos));
		ArrayList<ArrayList<Integer>> matrizArrayList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> numerosArrayList1 = new ArrayList<Integer>();
		ArrayList<Integer> numerosArrayList2 = new ArrayList<Integer>();
		numerosArrayList1.add(32);
		numerosArrayList1.add(56);
		numerosArrayList2.add(22);
		numerosArrayList2.add(53);
		matrizArrayList.add(numerosArrayList1);
		matrizArrayList.add(numerosArrayList2);
		System.out.println(practicas.convierteArrayListEnMatriz(matrizArrayList));
		ArrayList<ArrayList<String>> goles2 = new ArrayList<ArrayList<String>>();
		ArrayList<String> golMadrid = new ArrayList<String>();
		golMadrid.add("3-2");
		golMadrid.add("1-1");
		ArrayList<String> golBarcelona = new ArrayList<String>();
		golBarcelona.add("1-3");
		golBarcelona.add("2-1");
		goles2.add(golMadrid);
		goles2.add(golBarcelona);
		int[] puntos = practicas.obtenerClasificacion(goles2);
		System.out.println(puntos);
		puntos = practicas.obtenerClasificacion2(goles2);
		System.out.println(puntos);
		ArrayList<String> cadenasLista = new ArrayList<String>();
		cadenasLista.add("Hola");
		cadenasLista.add("soy");
		cadenasLista.add("Lorena");
		practicas.ordenaCadenas(cadenasLista);
		System.out.println(cadenasLista);
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		estudiantes.add(est2);
		estudiantes.add(est1);
		practicas.ordenaEstudiantes(estudiantes);
		System.out.println(estudiantes);
		ArrayList<ArrayList<Integer>> visitantes = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> visitasIslaL = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaF = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaG = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaT = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaLG = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaP = new ArrayList<Integer>();
		ArrayList<Integer> visitasIslaH = new ArrayList<Integer>();
		visitasIslaL.add(12);
		visitasIslaL.add(20);
		visitasIslaL.add(19);
		visitasIslaL.add(14);
		visitasIslaL.add(12);
		visitasIslaL.add(1);
		visitasIslaL.add(12);
		visitasIslaL.add(20);
		visitasIslaL.add(19);
		visitasIslaL.add(14);
		visitasIslaL.add(12);
		visitasIslaL.add(1);
		visitasIslaF.add(21);
		visitasIslaF.add(2);
		visitasIslaF.add(34);
		visitasIslaF.add(15);
		visitasIslaF.add(18);
		visitasIslaF.add(35);
		visitasIslaF.add(21);
		visitasIslaF.add(2);
		visitasIslaF.add(34);
		visitasIslaF.add(15);
		visitasIslaF.add(18);
		visitasIslaF.add(35);
		visitasIslaG.add(2);
		visitasIslaG.add(21);
		visitasIslaG.add(32);
		visitasIslaG.add(45);
		visitasIslaG.add(65);
		visitasIslaG.add(7);
		visitasIslaG.add(2);
		visitasIslaG.add(21);
		visitasIslaG.add(32);
		visitasIslaG.add(45);
		visitasIslaG.add(65);
		visitasIslaG.add(7);
		visitasIslaT.add(12);
		visitasIslaT.add(2);
		visitasIslaT.add(33);
		visitasIslaT.add(76);
		visitasIslaT.add(89);
		visitasIslaT.add(5);
		visitasIslaT.add(12);
		visitasIslaT.add(2);
		visitasIslaT.add(33);
		visitasIslaT.add(76);
		visitasIslaT.add(89);
		visitasIslaT.add(5);
		visitasIslaLG.add(19);
		visitasIslaLG.add(24);
		visitasIslaLG.add(34);
		visitasIslaLG.add(15);
		visitasIslaLG.add(78);
		visitasIslaLG.add(76);
		visitasIslaLG.add(19);
		visitasIslaLG.add(24);
		visitasIslaLG.add(34);
		visitasIslaLG.add(15);
		visitasIslaLG.add(78);
		visitasIslaLG.add(76);
		visitasIslaP.add(34);
		visitasIslaP.add(54);
		visitasIslaP.add(1);
		visitasIslaP.add(98);
		visitasIslaP.add(12);
		visitasIslaP.add(32);
		visitasIslaP.add(34);
		visitasIslaP.add(54);
		visitasIslaP.add(1);
		visitasIslaP.add(98);
		visitasIslaP.add(12);
		visitasIslaP.add(32);
		visitasIslaH.add(13);
		visitasIslaH.add(45);
		visitasIslaH.add(66);
		visitasIslaH.add(32);
		visitasIslaH.add(12);
		visitasIslaH.add(9);
		visitasIslaH.add(13);
		visitasIslaH.add(45);
		visitasIslaH.add(66);
		visitasIslaH.add(32);
		visitasIslaH.add(12);
		visitasIslaH.add(9);
		visitantes.add(visitasIslaL);
		visitantes.add(visitasIslaF);
		visitantes.add(visitasIslaG);
		visitantes.add(visitasIslaT);
		visitantes.add(visitasIslaLG);
		visitantes.add(visitasIslaP);
		visitantes.add(visitasIslaH);
		isla = 3;
		int visitas = practicas.visitantesIslaYear(isla, visitantes);
		System.out.println(visitas);
		mes = 4;
		int visitasMes = practicas.visitantesMesYear(mes, visitantes);
		System.out.println(visitasMes);
		

	}

}
