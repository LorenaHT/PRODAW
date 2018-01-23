package auxiliar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;

public class Practicas {

	// SEGUNDA EVALUACION

	public ArrayList<Estudiante> introListas() {

		ArrayList<Estudiante> listaEstudiante;
		listaEstudiante = new ArrayList<Estudiante>();
		Estudiante est1 = new Estudiante(123);
		Estudiante est2 = new Estudiante(321);
		listaEstudiante.add(est1);
		listaEstudiante.add(est1);
		listaEstudiante.add(est1);
		listaEstudiante.add(est2);
		listaEstudiante.add(est1);
		listaEstudiante.add(0, est2);
		listaEstudiante.remove(listaEstudiante.size() - 1);
		listaEstudiante.set(0, est1);
		int tam = listaEstudiante.size();
		for (Estudiante estudiante : listaEstudiante) {
			System.out.println(estudiante.getCodGrupo());
		}

		for (int i = 0; i < listaEstudiante.size(); i++) {
			System.out.println(listaEstudiante.get(i));
		}
		return listaEstudiante;
		// System.out.println("fin introListas");

	}

	public void listaEstudiantes(ArrayList<Estudiante> lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public HashMap<String, Estudiante> introMapas() {
		// la clave representa el nif del estudiante
		HashMap<String, Estudiante> resultado = new HashMap<String, Estudiante>();
		Estudiante est = new Estudiante(123, "45363715X", "Lorena", 'F', LocalDate.now(), 169, null, null);
		Estudiante est1 = new Estudiante(456, "43753117X", "Teresa", 'F', LocalDate.now(), 163, null, null);
		resultado.put(est.getNif(), est);
		resultado.put(est1.getNif(), est1);
		resultado.put("123T", new Estudiante(789, "123T", "Pepe", 'M', null, 180, null, null));
		Estudiante estudiante = resultado.get("45363715X");
		return resultado;

	}

	public void leerFicheroTexto() {
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader("ficheros/Persona.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			LocalDate fechaHoy;
			System.out.println(LocalDate.now());
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				// while(true) {
				// Print the content on the console
				// linea = br.readLine();
				String[] campos = linea.split("&&");
				System.out.println(calcularEdad(campos[2]));
				System.out.println(calcularEdad1(campos[2]));
				System.out.println(calcularEdad2(campos[2]));
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public int calcularEdad(String fechaNacimiento) { // ddmmaaaa

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}

	public int calcularEdad1(String fechaNacimiento) {
		Calendar cal = Calendar.getInstance();

		// cal.set(1985, Calendar.JANUARY, 30);
		int year = Integer.parseInt(fechaNacimiento.substring(4, 8));
		int mes = Integer.parseInt(fechaNacimiento.substring(2, 4));
		int dia = Integer.parseInt(fechaNacimiento.substring(0, 2));
		System.out.println(dia + "," + mes + "," + year);
		Date hoy = cal.getTime();
		cal.set(year, mes, dia);
		Date birthday = cal.getTime();
		long diferenciaMilisegundos = hoy.getTime() - birthday.getTime();
		long time = 1000 * 60 * 60 * 24;
		return (int) ((diferenciaMilisegundos / time) / 365);
	}

	public String calcularEdad2(String fechaNacimiento) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		String mensaje = "Tu edad es: " + periodo.getYears() + " años " + periodo.getMonths() + " meses y "
				+ periodo.getDays() + " días";
		return mensaje;
	}

	public ArrayList<ArrayList<Integer>> convierteMatrizArrayLista(int[][] matriz) {
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		for (int[] filaMatriz : matriz) {
			ArrayList<Integer> filaLista = new ArrayList<Integer>();
			for (int numero : filaMatriz)
				filaLista.add(numero);
			resultado.add(filaLista);
		}
		return resultado;
	}
	
	public int[][] convierteArrayListEnMatriz(ArrayList<ArrayList<Integer>> matriz) {
		int[][] resultado = new int[matriz.get(0).size()][matriz.size()];
		for (int i = 0; i<matriz.size(); i++) {
			for (int j = 0; j<matriz.get(0).size(); j++)
				resultado[i][j] = matriz.get(i).get(j);
		}
		return resultado;
	}
	
	//EJERCICIO: Convertir un fichero en Arrayist ArrayList<String> LeerFicheroArrayList (String nombreFichero)
	public ArrayList<String> LeerFicheroArrayList (String nombreFichero){
		ArrayList<String> resultado = new ArrayList<String>(); 
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				// while(true) {
				// Print the content on the console
				// linea = br.readLine();
				String[] campos = linea.split("   ");
				for (int i=0; i<campos.length; i++)
				resultado.add(campos[i]);
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return resultado;
		
	}
	
	public HashMap<String, String> LeerFicheroHashMapYCalcularEdadMedia (String nombreFichero, String dni){
		HashMap<String, String> resultado = new HashMap<String, String>();
		int acumulado = 0;
		int contador = 0;
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				// while(true) {
				// Print the content on the console
				// linea = br.readLine();
				String[] campos = linea.split("&&");
				String[] lineas = linea.split("\\n");
				acumulado += calcularEdad(campos[2]);
				contador++;
				for (int i=0; i<lineas.length; i++)	 
					if (campos[0].equals(dni))
				resultado.put(campos[0], lineas[i]);
				System.out.println(campos[0]);
				
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println("Edad Media: "+ acumulado/contador);
		return resultado;
	}
	

	// PRIMERA EVALUACION
	// private static String[] diasSemana = { "lunes", "martes", "miercoles",
	// "jueves", "viernes", "sábado", "domingo" };

	public boolean esPrimo(int numero) {

		for (int i = 2; i < numero; i++) {
			if (numero % i == 0)
				return false;
		}

		return true;
	}

	public int[] numerosPrimos(int cuantos) {
		int[] primos = new int[cuantos];
		int i = 0;
		int j = 1;
		while (i < cuantos) {
			if (esPrimo(j))
				primos[i++] = j;
			j++;
		}
		return primos;
	}

	public ArrayList<Integer> numerosPrimosArrayList(int cuantos) {
		ArrayList<Integer> primos = new ArrayList<Integer>(cuantos);
		int i = 0;
		int j = 1;
		while (i < cuantos) {
			if (esPrimo(j))
				primos.add(j);
			j++;
			i++;
		}
		return primos;
	}

	public int[] numerosFibonacci(int cuantos) {
		int[] numeros = new int[cuantos];
		int x = 0;
		int y = 1;
		int z;
		numeros[0] = x;
		numeros[1] = y;
		for (int i = 2; i < cuantos; i++) {
			z = x + y;
			numeros[i] = z;
			x = y;
			y = z;
		}
		return numeros;
	}

	public ArrayList<Integer> numerosFibonacciArrayList(int cuantos) {
		ArrayList<Integer> numeros = new ArrayList<Integer>(cuantos);
		int x = 0;
		int y = 1;
		int z;
		numeros.add(x);
		numeros.add(y);
		for (int i = 2; i < cuantos; i++) {
			z = x + y;
			numeros.add(z);
			x = y;
			y = z;
		}
		return numeros;
	}

	// LIGA: Obtener clasificación a partir de resultados
	public int[] obtenerClasificacion(String[][] goles) {
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int i = 0; i < goles.length; i++)
			for (int j = 0; j < goles[i].length; j++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}
	
	public int[] obtenerClasificacion(ArrayList<ArrayList<String>>goles) {
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int i = 0; i < goles.size(); i++)
			for (int j = 0; j < goles.get(i).size(); j++)
				if (goles.get(i).get(j).indexOf('-') != -1) {
					resultado = goles.get(i).get(j).split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
						
					}
				}
		return puntos;
	}



	public int[] obtenerClasificacion2(String[][] goles) {
		// la diferencia con el anterior es que recorre la
		// matriz por columnas
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int j = 0; j < goles[0].length; j++)
			for (int i = 0; i < goles.length; i++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}

	public Equipo[] obtenerClasificacion3(int[][] puntosJornadas) {
		Equipo[] clasificacion = new Equipo[5];
		String[] equipos = new Datos().getEquipos();
		for (int j = 0; j < puntosJornadas[0].length; j++) {
			Equipo e = new Equipo();
			e.setNombre(equipos[j]);
			e.setPuntos(0);
			for (int i = 0; i < clasificacion.length; i++)
				e.setPuntos(e.getPuntos() + puntosJornadas[i][j]);
			clasificacion[j] = e;
		}

		return clasificacion;
	}

	public boolean validarNif(String nif) {
		char[] letrasValidas = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		if (nif.length() != 9)
			return false;
		String numeros = nif.substring(0, 8);
		// System.out.println(numeros);
		int numerosOK;
		try {
			numerosOK = Integer.parseInt(numeros);
		} catch (NumberFormatException e) {
			return false;
		}
		int resto = numerosOK % 23;
		if (letrasValidas[resto] != nif.charAt(8))
			return false;
		return true;
	}

	// ORDENACION
	public void ordenaEnteros(int[] numeros) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] > numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
				}
	}

	public void ordenaEnteros(ArrayList<Integer> numeros) {
		for (int i = 0; i < numeros.size() - 1; i++)
			for (int j = i + 1; j < numeros.size(); j++)
				if (numeros.get(i) > numeros.get(j)) {
					int aux = numeros.get(i);
					numeros.set(i, numeros.get(j));
					numeros.set(j, aux);
				}
	}

	public void ordenaClasificacion(int[] numeros, String[] equipos) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] < numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
					String aux2 = equipos[i];
					equipos[i] = equipos[j];
					equipos[j] = aux2;
				}
	}

	public void ordenaClasificacion(ArrayList<Integer> numeros, ArrayList<String> equipos) {
		for (int i = 0; i < numeros.size() - 1; i++)
			for (int j = i + 1; j < numeros.size(); j++)
				if (numeros.get(i) < numeros.get(j)) {
					int aux = numeros.get(i);
					numeros.set(i, numeros.get(j));
					numeros.set(j, aux);
					String aux2 = equipos.get(i);
					equipos.set(i, equipos.get(j));
					equipos.set(j, aux2);
				}
	}
	// mezcla dos arrays ordenados

	public int[] mezclaArrays(int[] l1, int[] l2) {
		int i = 0, j = 0, k = 0;
		int[] resultado = new int[l1.length + l2.length];

		while (l1[i] != Integer.MAX_VALUE || l2[j] != Integer.MAX_VALUE) {
			if (l1[i] < l2[j])
				resultado[k] = l1[i++];
			else
				resultado[k] = l2[j++];
			k++;

			if (i == l1.length)
				l1[--i] = Integer.MAX_VALUE;

			if (j == l2.length)
				l2[--j] = Integer.MAX_VALUE;
		}
		return resultado;
	}

	public ArrayList<Integer> mezclaArrays(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> resultado = new ArrayList<Integer>(l1.size() + l2.size());

		while (l1.get(i) != Integer.MAX_VALUE || l2.get(j) != Integer.MAX_VALUE) {
			if (l1.get(i) < l2.get(j))
				resultado.add(k, l1.get(i++));
			else
				resultado.add(k, l2.get(j++));
			k++;

			if (i == l1.size())
				l1.set(--i, Integer.MAX_VALUE);

			if (j == l2.size())
				l2.set(--j, Integer.MAX_VALUE);
		}
		return resultado;
	}

	public void ordenaCadenas(String[] cadenas) {
		for (int i = 0; i < cadenas.length - 1; i++)
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) > 0) {
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}

	}

	public void ordenaEstudiantes(Estudiante[] estudiantes) {
		// ejemplo de uso de la interfaz Comparable
		// debe implementarse el método compareTo

		for (int i = 0; i < estudiantes.length - 1; i++)
			for (int j = i + 1; j < estudiantes.length; j++)
				if (estudiantes[i].compareTo(estudiantes[j]) > 0) {
					Estudiante aux = estudiantes[i];
					estudiantes[i] = estudiantes[j];
					estudiantes[j] = aux;
				}
	}

	public float calculaSaldo(float saldoInicial, float[] movimientos) {
		float saldoFinal = saldoInicial;
		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];
		return saldoFinal;
	}
	
	public float calculaSaldo(float saldoInicial, ArrayList<Float> movimientos) {
		float saldoFinal = saldoInicial;
		for(float movimiento : movimientos)
			saldoFinal += movimiento;
		return saldoFinal;
	}

	public int[] convierteCadenasANumeros(String[] cadenas) {
		int[] resultado = new int[cadenas.length];
		for (int i = 0; i < resultado.length; i++) {

			try {

				resultado[i] = Integer.parseInt(cadenas[i]);
			} catch (NumberFormatException e) {

				resultado[i] = -1;
			}
		}
		return resultado;
	}

	public ArrayList<Integer> convierteCadenasANumeros(ArrayList<String> cadenas) {
		ArrayList<Integer> resultado = new ArrayList<Integer>(cadenas.size());
		for (String string : cadenas) {
			try {
				resultado.add(Integer.parseInt(string));
			} catch (NumberFormatException e) {
				resultado.add(-1);
			}
		}
		/*
		 * for (int i = 0; i < cadenas.size(); i++) { try {
		 * resultado.add(Integer.parseInt(cadenas.get(i))); } catch
		 * (NumberFormatException e) { resultado.add(i, -1); } }
		 */
		return resultado;
	}

	public void muestraNumeros() {

		int x = 0;
		while (x <= 1000) {
			System.out.println("x: " + x);
			x++;
		}
	}

	public void muestraNumeros(int min, int max) {

		if (min <= max) {
			int x = min;
			while (x <= max) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros2(int min, int max) {

		if (min <= max) {
			int x = min;
			do {
				System.out.println("x: " + x);
				x++;
			} while (x <= max);
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros3(int min, int max) {
		int x = min;
		if (min <= max) {
			// for (int x = min; x < max; x++) {
			// for (;;) {
			while (true) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void generaAleatorios(int cuantos, int inferior, int superior) // max 30, min 10
	{

		for (int i = 0; i < cuantos; i++)
			System.out.println(inferior + (int) (Math.random() * (superior - inferior + 1)));

	}

	public void generaAleatorios2(int cuantos, int inferior, int superior) // max 30, min 10
	{

		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			System.out.println(inferior + rnd.nextInt(superior - inferior + 1));
	}

	public int[] generaAleatorios3(int cuantos, int inferior, int superior) // max 30, min 10
	{
		int[] resultado = new int[cuantos];
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			// System.out.println(inferior + rnd.nextInt(superior - inferior + 1));
			resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
		return resultado;
	}

	public int[] frecuenciaAparicion(int cuantos, int inferior, int superior) {
		int[] resultado = new int[superior - inferior + 1];
		int[] lanzamientos = this.generaAleatorios3(cuantos, inferior, superior);
		for (int i = 0; i < lanzamientos.length; i++) {
			resultado[lanzamientos[i] - 1]++;
		}
		return resultado;

	}

	public void estadisticaCadena(String cadena) {
		int contadorVocales = 0;
		int contadorMayusculas = 0;
		int contadorEspeciales = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == 'a' || cadena.charAt(i) == 'e' || cadena.charAt(i) == 'i' || cadena.charAt(i) == 'o'
					|| cadena.charAt(i) == 'u' || cadena.charAt(i) == 'A' || cadena.charAt(i) == 'E'
					|| cadena.charAt(i) == 'I' || cadena.charAt(i) == 'O' || cadena.charAt(i) == 'U')
				contadorVocales++;
			if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z')
				contadorMayusculas++;
			int caracterAscii = cadena.charAt(i);
			if ((caracterAscii >= 0 && caracterAscii <= 47) || (caracterAscii >= 58 && caracterAscii <= 64) ||

					(caracterAscii >= 91) && (caracterAscii <= 96))

				contadorEspeciales++;
		}
		// System.out.println("Hay " + contadorVocales + " vocales en " + cadena);
		System.out.println("Hay " + contadorEspeciales + " caracteres especiales en " + cadena);

	}

	public void listaDiasSemana() {
		Datos datos = new Datos();
		// String[] diasSemana = { "lunes", "martes", "miercoles", "jueves", "viernes",
		// "sábado", "domingo" };
		// for (int i = 0; i < datos.getDiasSemana().length; i++)
		for (String dia : datos.getDiasSemana())
			// System.out.println(datos.getDiasSemana()[i]);
			System.out.println(dia);
	}

	public void listaEstudiantes(Estudiante[] lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public int visitantesIslaYear(int isla, int[][] v) {
		int acu = 0;
		for (int j = 0; j < v[0].length; j++)
			acu += v[isla][j];
		return acu;
	}

	public int visitantesMesYear(int mes, int[][] v) {
		int acu = 0;
		for (int i = 0; i < v.length; i++)
			acu += v[i][mes];
		return acu;
	}

	public void recorrerMatrizIrregularPorColumnas(int[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}

			}
		}
	}

	public void recorrerMatrizIrregularPorColumnas2(Integer[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j].byteValue());
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				} catch (NullPointerException e) {
					continue;
				}

			}
		}
	}
}
