package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Vehiculo;
import modelo.Vehiculos;

public class Practicas {

	// SEGUNDA EVALUACION

	// Paso 1: Leer comunidades.txt y crear {array / Lista}
	// String[] paso1 (String ficheroCA)
	// HashMap(Integer,ArrayList);
	// Paso 2: Leer provincias.txt creando un HashMap (K=>Valor) donde K= -> CA {id
	// / nombre); V -> listaProvincia Devolver el HashMap
	// String "BISCCAYA#1500"
	// Paso 3: Recorrer y listar el HahMap creado en paso 2

	public String[] LeerFicheroArrayString(String fichero) {
		String[] resultado = new String[20];
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("%");
				int id = Integer.parseInt(campos[0]);
				String comunidad = campos[1];
				resultado[id] = comunidad;
			}
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

	public HashMap<Integer, String> LeerFicheroHashMapIntegerString(String fichero) {
		HashMap<Integer, String> provincias = new HashMap<Integer, String>();
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("%");
				int id = Integer.parseInt(campos[0]);
				String cadena = campos[1] + "#" + campos[2] + "#" + campos[3];
				provincias.put(id, cadena);
			}
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
		return provincias;
	}

	public HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>> LeerFicheroHashMapIntegerLinkedHashMap(
			String ficheroComunidades, String ficheroProvincias) {
		HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>> resultado = new HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>>();
		String[] comunidades = LeerFicheroArrayString(ficheroComunidades);
		HashMap<Integer, String> provincia = LeerFicheroHashMapIntegerString(ficheroProvincias);
		Set<Integer> claves = provincia.keySet();
		System.out.println("Comunidades Autonomas");
		for (int i = 1; i < comunidades.length; i++) {
			System.out.println("Comunidad Autonoma: " + comunidades[i]);
			LinkedHashMap<Integer, ArrayList<String>> provincias = new LinkedHashMap<Integer, ArrayList<String>>();
			for (int clave : claves) {
				ArrayList<String> datos = new ArrayList<String>();
				String[] campos = provincia.get(clave).split("#");
				datos.add(campos[0]);
				datos.add(campos[2]);
				if (Integer.parseInt(campos[1]) == i) {
					provincias.put(clave, datos);
				}

			}
			resultado.put(i, provincias);
		}
		System.out.println(resultado);
		return resultado;
	}

	public HashMap<Integer, ArrayList<ArrayList<String>>> LeerFicheroHashMapArrayListString(String ficheroComunidades,
			String ficheroProvincias) {
		HashMap<Integer, ArrayList<ArrayList<String>>> resultado = new HashMap<Integer, ArrayList<ArrayList<String>>>();
		String[] comunidades = LeerFicheroArrayString(ficheroComunidades);
		HashMap<Integer, String> provincia = LeerFicheroHashMapIntegerString(ficheroProvincias);
		Set<Integer> claves = provincia.keySet();
		ArrayList<Integer> orden = new ArrayList<Integer>();
		System.out.println("Comunidades Autonomas");
		for (int i = 1; i < comunidades.length; i++) {
			//System.out.println("Comunidad Autonoma: " + comunidades[i]);
			ArrayList<ArrayList<String>> provincias = new ArrayList<ArrayList<String>>();
			for (int clave : claves) {
				ArrayList<String> datos = new ArrayList<String>();
				String[] campos = provincia.get(clave).split("#");
				datos.add(String.valueOf(clave));
				datos.add(campos[0]);
				datos.add(campos[2]);
				boolean insertar = true;
				for (int j = 0; j < orden.size(); j++) {
					if (orden.get(j)==Integer.parseInt(campos[1])) {
						insertar = false;
					}
				}
				if (insertar == true)
					orden.add(Integer.parseInt(campos[1]));
				if (Integer.parseInt(campos[1]) == i) {
					//System.out.println(campos[0] + "   " + campos[2]);
					provincias.add(datos);
					
				}
			}
			
			//System.out.println("Total de CCAA: " + suma);
			resultado.put(i, provincias);

		}
		//System.out.println(orden);
		
		for (int i = 0; i<orden.size(); i++) {
			int suma = 0;
			ArrayList<ArrayList<String>> lista = resultado.get(orden.get(i));
			for (int j=0; j<lista.size(); j++) {
				if (j==0)
					System.out.println("Comunidad Autonoma: "+comunidades[orden.get(i)]);
				System.out.println(lista.get(j).get(1)+"\t\t"+lista.get(j).get(2));
				suma += Integer.parseInt(lista.get(j).get(2));
			}
			System.out.println("Total de CCAA: "+suma+"\n");
		}
		return resultado;
	}

	public Estudiante crearEstudianteLeido(String[] datos) {
		int grupo = Integer.parseInt(datos[0]);
		Estudiante estudiante = new Estudiante(grupo);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate fechaNacimiento = LocalDate.parse(datos[4], fmt);
		estudiante.setNif(datos[1]);
		estudiante.setNombre(datos[2]);
		estudiante.setSexo(datos[3].charAt(0));
		estudiante.setFecha(fechaNacimiento);
		estudiante.setAltura(Integer.parseInt(datos[5]));
		estudiante.setMadre(null);
		estudiante.setPadre(null);
		return estudiante;
	}

	public void copiarEstudiantestxtAObjetos(String rutaTxt, String rutaObj) {
		/*
		 * ArrayList<Estudiante> estNew = new ArrayList<Estudiante>();
		 * ArrayList<Estudiante> estFich = new ArrayList<Estudiante>();
		 */
		try {
			FileReader fr = new FileReader(rutaTxt);
			BufferedReader br = new BufferedReader(fr);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaObj));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("#");
				Estudiante estudiante = crearEstudianteLeido(campos);
				/*
				 * Estudiante estudiante = new Estudiante(Integer.parseInt(campos[0]));
				 * DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd"); LocalDate
				 * fechaNacimiento = LocalDate.parse(campos[4], fmt);
				 * estudiante.setNif(campos[1]); estudiante.setNombre(campos[2]);
				 * estudiante.setSexo(campos[3].charAt(0));
				 * estudiante.setFecha(fechaNacimiento);
				 * estudiante.setAltura(Integer.parseInt(campos[5]));
				 */
				// estNew.add(estudiante);
				oos.writeObject(estudiante);
			}
			// while (fis.available() > 0)
			// estFich = (ArrayList<Estudiante>) ois.readObject();
			/*
			 * for (Estudiante estudiante : estNew) estFich.add(estudiante);
			 */
			// estFich.addAll(estNew);
			// oos.writeObject(estFich);
			fr.close();
			br.close();
			oos.close();
			// ois.close();
			copiaEstudiantesObjATxt(rutaObj, "ficheros/NewEstObj.txt");
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void copiaEstudiantesObjATxt(String rutaObj, String rutaTxt) {
		try {
			FileInputStream fis = new FileInputStream(rutaObj);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BufferedWriter fb = new BufferedWriter(new FileWriter(rutaTxt));
			Estudiante estudiante = null;
			while (fis.available() > 0) {
				estudiante = (Estudiante) ois.readObject();
				String fechaCompleta = estudiante.getFecha().toString();
				String[] fecha = fechaCompleta.split("-");
				String registro = estudiante.getCodGrupo() + "#" + estudiante.getNif() + "#"+estudiante.getNombre() + "#" + estudiante.getSexo()
						+ "#" + fecha[0] + fecha[1] + fecha[2] + "#" + estudiante.getAltura() + "\n";
				fb.write(registro);
			}
			ois.close();
			fis.close();
			fb.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void leerFicheroOrdenados(String rutaFichero) {
		String codAnterior = null;
		String codLeido = null;
		int contadorGrupo = 0;
		int contadorTotal = 0;
		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("&&");
				codLeido = campos[0];
				if (codAnterior == null)
					codAnterior = codLeido;
				if (!codLeido.equals(codAnterior)) {
					System.out.println("\nHay " + contadorGrupo + " alumnos en el grupo " + codAnterior);
					contadorTotal += contadorGrupo;
					contadorGrupo = 0;
					codAnterior = codLeido;
				}
				contadorGrupo++;
			}
			System.out.println("Hay " + contadorGrupo + " alumnos en el grupo " + codAnterior);
			contadorTotal += contadorGrupo;
			System.out.println("Hay " + contadorTotal + " alumnos en total");
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

	public ArrayList<Vehiculo> LeerFicheroArrayListVehiculos(String nombreFichero) {
		ArrayList<Vehiculo> resultado = new ArrayList<Vehiculo>();
		try {
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("##");
				Vehiculo vehiculo = new Vehiculo();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate fechaMatricula = LocalDate.parse(campos[3], fmt);
				vehiculo.setId(Integer.parseInt(campos[0]));
				vehiculo.setMatricula(campos[1]);
				vehiculo.setMarcaModelo(Integer.parseInt(campos[2]));
				vehiculo.setFechaMatricula(fechaMatricula);
				vehiculo.setPrecio(Float.parseFloat(campos[4]));
				resultado.add(vehiculo);
			}
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

	public void grabarObjetosVehiculoEnFichero(String nombreFichero) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate fechaMatriculaV1 = LocalDate.parse("20180106", fmt);
		LocalDate fechaMatriculaV2 = LocalDate.parse("20170805", fmt);
		Vehiculo v1 = new Vehiculo(005, "3489RFG", 1290, fechaMatriculaV1, 45.89f);
		Vehiculo v2 = new Vehiculo(006, "2387GTH", 1243, fechaMatriculaV2, 28.76f);
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			fObj.writeObject(v1);
			fObj.writeObject(v2);
			fObj.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
		}
	}

	public void grabarObjetosArrayListVehiculoEnFichero(String nombreFichero) {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate fechaMatriculaV1 = LocalDate.parse("20180106", fmt);
		LocalDate fechaMatriculaV2 = LocalDate.parse("20170805", fmt);
		Vehiculo v1 = new Vehiculo(005, "3489RFG", 1290, fechaMatriculaV1, 45.89f);
		Vehiculo v2 = new Vehiculo(006, "2387GTH", 1243, fechaMatriculaV2, 28.76f);
		vehiculos.add(v1);
		vehiculos.add(v2);
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			fObj.writeObject(vehiculos);
			fObj.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
		}
	}

	public void leerObjetosVehiculoEnFichero(String nombreFichero) {
		// leer el fichero de objetos
		try {
			FileInputStream fis = new FileInputStream(nombreFichero);
			ObjectInputStream fObj = new ObjectInputStream(fis);
			// Vehiculo vehiculo = null;
			Vehiculo vehiculo = null;
			while (fis.available() > 0) {
				vehiculo = (Vehiculo) fObj.readObject();
				System.out.println(vehiculo.getId());
			}
			fObj.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFound");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			// return false;
		}
	}

	public void leerObjetosVehiculoArrayListEnFichero(String nombreFichero) {
		// leer el fichero de objetos
		try {
			FileInputStream fis = new FileInputStream(nombreFichero);
			ObjectInputStream fObj = new ObjectInputStream(fis);
			// Vehiculo vehiculo = null;
			ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
			while (fis.available() > 0) {
				vehiculos = (ArrayList<Vehiculo>) fObj.readObject();
				for (Vehiculo vehiculo : vehiculos)
					System.out.println(vehiculo.getMarcaModelo());
			}
			fObj.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFound");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			// return false;
		}
	}

	public static void grabarListaObjetosEnFichero(String nombreFichero) {
		// public boolean grabarObjetosEnFichero(String nombreFichero) {
		/*
		 * Vehiculo v1 = new Vehiculo(); Vehiculo v2 = new Vehiculo(); Vehiculo v3 = new
		 * Vehiculo();
		 */
		Estudiante est = new Estudiante(456, "45363715X", "Lorena", 'F', null, 169, null, null);
		Estudiante est1 = new Estudiante(123, "43753117", "Teresa", 'F', null, 163, null, null);
		Estudiante est2 = new Estudiante(789, "42586114S", "Joaquin", 'M', null, 175, null, null);
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		estudiantes.add(est);
		estudiantes.add(est1);
		estudiantes.add(est2);
		/*
		 * v2.setMarca("Opel"); v1.setMatricula("GC 3208 X"); v1.setMarca("Seat");
		 */
		// abrir el fichero de objetos
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			// Guardar los objetos de vehiculos
			/*
			 * fObj.writeObject(v1); fObj.writeObject(v2); fObj.writeObject(v3);
			 */
			fObj.writeObject(estudiantes);
			fObj.close();
			// return true;
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			// return false;
		}
	}

	public void leerListaObjetosArrayListEstudianteEnFichero(String nombreFichero) {
		// leer el fichero de objetos
		try {
			FileInputStream fis = new FileInputStream(nombreFichero);
			ObjectInputStream fObj = new ObjectInputStream(fis);
			// Vehiculo vehiculo = null;
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			while (fis.available() > 0)
				estudiantes = (ArrayList<Estudiante>) fObj.readObject();
			for (int i = 0; i < estudiantes.size(); i++)
				System.out.println(estudiantes.get(i).getNombre());
			fObj.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFound");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			// return false;
		}
	}

	public void grabarObjetosEnFichero(String nombreFichero) {
		// public boolean grabarObjetosEnFichero(String nombreFichero) {
		/*
		 * Vehiculo v1 = new Vehiculo(); Vehiculo v2 = new Vehiculo(); Vehiculo v3 = new
		 * Vehiculo();
		 */
		Estudiante est = new Estudiante(456, "45363715X", "Lorena", 'F', null, 169, null, null);
		/*
		 * v2.setMarca("Opel"); v1.setMatricula("GC 3208 X"); v1.setMarca("Seat");
		 */
		// abrir el fichero de objetos
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			// Guardar los objetos de vehiculos
			/*
			 * fObj.writeObject(v1); fObj.writeObject(v2); fObj.writeObject(v3);
			 */
			fObj.writeObject(est);
			fObj.close();
			// return true;
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			// return false;
		}
	}

	public void leerObjetosEnFichero(String nombreFichero) {
		// leer el fichero de objetos
		try {
			FileInputStream fis = new FileInputStream(nombreFichero);
			ObjectInputStream fObj = new ObjectInputStream(fis);
			// Vehiculo vehiculo = null;
			Estudiante estudiante = null;
			while (fis.available() > 0) {
				estudiante = (Estudiante) fObj.readObject();
				System.out.println(estudiante.getNombre());
			}
			fObj.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFound");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			// return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			// return false;
		}
	}

	public boolean generaFicheroLanzamientoDados(int cantidad, String nombreFichero) {
		try {
			BufferedWriter fb = new BufferedWriter(new FileWriter(nombreFichero));
			Random rnd = new Random();
			for (int i = 0; i < cantidad; i++) {
				int numero = 1 + rnd.nextInt(6);
				String registro = System.currentTimeMillis() + "#" + i + "#" + numero + "\n";
				int retardo = 1 + rnd.nextInt(1000);
				// Thread.sleep(retardo);
				fb.write(registro);
			}
			fb.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return false;
		} /*
			 * catch (InterruptedException e) { // TODO Auto-generated catch block
			 * System.out.println(e.getMessage()); return false; }
			 */
	}

	public void leerFicheroTextoLanzamientoDados() {
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader("ficheros/LanzamientoDados.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				// while(true) {
				// Print the content on the console
				// linea = br.readLine();
				String[] campos = linea.split("#");
				long milisegundos = Long.parseLong(campos[0]);
				double segundos = milisegundos / 1000;
				double minutos = segundos / 60;
				double horas = minutos / 60;
				double dias = horas / 24;
				double meses = dias / 365;
				double años = meses / 12;
				System.out.println("Milisegundos: " + milisegundos + " " + campos[1]);
				System.out.println("Segundos: " + segundos + " " + campos[1]);
				System.out.println("Minutos: " + minutos + " " + campos[1]);
				System.out.println("Horas: " + horas + " " + campos[1]);
				System.out.println("Dias: " + dias + " " + campos[1]);
				System.out.println("Meses: " + meses + " " + campos[1]);
				System.out.println("Años: " + años + " " + campos[1]);
				System.out.println("-------------------------------");
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
		for (int i = 0; i < matriz.size(); i++) {
			for (int j = 0; j < matriz.get(0).size(); j++)
				resultado[i][j] = matriz.get(i).get(j);
		}
		return resultado;
	}

	// EJERCICIO: Convertir un fichero en Arrayist ArrayList<String>
	// LeerFicheroArrayList (String nombreFichero)
	public ArrayList<String> LeerFicheroArrayList(String nombreFichero) {
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
				for (int i = 0; i < campos.length; i++)
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

	public HashMap<String, String> LeerFicheroHashMapYCalcularEdadMedia(String nombreFichero, String dni) {
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
				for (int i = 0; i < lineas.length; i++)
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
		System.out.println("Edad Media: " + acumulado / contador);
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

	public int[] obtenerClasificacion(ArrayList<ArrayList<String>> goles) {
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

	public int[] obtenerClasificacion2(ArrayList<ArrayList<String>> goles) {
		// la diferencia con el anterior es que recorre la
		// matriz por columnas
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int j = 0; j < goles.get(0).size(); j++)
			for (int i = 0; i < goles.size(); i++)
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

	public Equipo[] obtenerClasificacion3(ArrayList<ArrayList<Integer>> puntosJornadas) {
		Equipo[] clasificacion = new Equipo[5];
		String[] equipos = new Datos().getEquipos();
		for (int j = 0; j < puntosJornadas.get(0).size(); j++) {
			Equipo e = new Equipo();
			e.setNombre(equipos[j]);
			e.setPuntos(0);
			for (int i = 0; i < clasificacion.length; i++)
				e.setPuntos(e.getPuntos() + puntosJornadas.get(i).get(j));
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

	public void ordenaCadenas(ArrayList<String> cadenas) {
		for (int i = 0; i < cadenas.size() - 1; i++)
			for (int j = i + 1; j < cadenas.size(); j++)
				if (cadenas.get(i).compareTo(cadenas.get(j)) > 0) {
					String aux = cadenas.get(i);
					cadenas.set(i, cadenas.get(j));
					cadenas.set(j, aux);
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

	public void ordenaEstudiantes(ArrayList<Estudiante> estudiantes) {
		// ejemplo de uso de la interfaz Comparable
		// debe implementarse el método compareTo

		for (int i = 0; i < estudiantes.size() - 1; i++)
			for (int j = i + 1; j < estudiantes.size(); j++)
				if (estudiantes.get(i).compareTo(estudiantes.get(j)) > 0) {
					Estudiante aux = estudiantes.get(i);
					estudiantes.set(i, estudiantes.get(j));
					estudiantes.set(j, aux);
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
		for (float movimiento : movimientos)
			saldoFinal += movimiento;
		return saldoFinal;
	}

	public float calculaSaldo(float saldoInicial, String nombreFichero) {
		float saldoFinal = saldoInicial;
		LocalDate fechaHoy = LocalDate.now();
		try {
			FileReader fr = new FileReader("ficheros/Movimientos.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("#");
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate fechasMov = LocalDate.parse(campos[0], fmt);
				saldoFinal += Float.parseFloat(campos[1]);
				System.out.println(fechasMov + " : " + saldoFinal);
			}
			System.out.println("-------------------------");
			System.out.println("Hoy: " + fechaHoy + " : " + saldoFinal);
			// Close the input stream
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
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

	public void listaEstudiantes(ArrayList<Estudiante> lista) {
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

	public int visitantesIslaYear(int isla, ArrayList<ArrayList<Integer>> v) {
		int acu = 0;
		for (int j = 0; j < v.get(0).size(); j++)
			acu += v.get(isla).get(j);
		return acu;
	}

	public void inicializaVisitantesIsla(HashMap<Integer, ArrayList<Float>> resultado) {
		ArrayList<Float> visitantesMeses;
		for (int isla = 0; isla < 7; isla++) { // recorre cada isla
			visitantesMeses = new ArrayList<Float>();
			for (int mes = 0; mes < 12; mes++) // pone a cero cada uno de los meses
				visitantesMeses.add(0.0f);
			resultado.put(isla, visitantesMeses);
		}

	}

	public void listadoIslasMeses(String rutaFicheroVisitantes) {
		HashMap<Integer, ArrayList<Float>> hm = visitantesIslaMes(rutaFicheroVisitantes);
		// recorrrer hm
		String[] islas = { "Gran Canaria", "Lanzarote", "Fuerteventura", "Tenerife", "La Palma", "La Gomera",
				"El Hierro" };
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septie",
				"Octubre", "Noviem", "Diciembre" };
		Set<Integer> claves = hm.keySet();
		float[] acumuladoMes = new float[12];
		System.out.print("\t\t");
		for (int i = 0; i < meses.length; i++)
			System.out.print(meses[i] + "\t");
		System.out.println();
		ArrayList<Float> visitantesIsla;
		for (Integer clave : claves) {
			visitantesIsla = hm.get(clave);
			System.out.print(islas[clave] + "\t");
			float acumuladoIsla = 0f;
			for (int i = 0; i < visitantesIsla.size(); i++) {
				acumuladoIsla += visitantesIsla.get(i);
				acumuladoMes[i] += visitantesIsla.get(i);
				System.out.printf("%.0f\t", visitantesIsla.get(i) * 1000);
			}
			System.out.println(" Total visitantes " + islas[clave] + " = " + acumuladoIsla);
			System.out.println();

		}
		System.out.print("Visitantes: ");
		for (Float valor : acumuladoMes) {
			System.out.print("\t" + valor);
		}

	}

	public HashMap<Integer, ArrayList<Float>> visitantesIslaMes(String nombreFichero) {
		HashMap<Integer, ArrayList<Float>> resultado = new HashMap<Integer, ArrayList<Float>>();
		HashMap<Integer, Float> total = new HashMap<Integer, Float>();
		try {
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			inicializaVisitantesIsla(resultado); // Pone a cero todos los valores del HashMap resultado
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("@");
				int isla = Integer.parseInt(campos[0]);
				int mes = Integer.parseInt(campos[1]);
				float numeroVisitantes = Float.parseFloat(campos[2]);
				resultado.get(isla - 1).set(mes - 1, numeroVisitantes);
			}
			fr.close();
			br.close();
			Set<Integer> claves = resultado.keySet();
			for (Integer clave : claves) {
				ArrayList<Float> listaVisitantes = resultado.get(clave);
				float visitantes = 0;
				for (float cantidad : listaVisitantes) {
					visitantes += cantidad;
				}
				total.put(clave, visitantes);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println("Visitantes en cada isla en cada mes: " + resultado + " = " + total);
		return resultado;
	}

	public HashMap<Integer, ArrayList<Float>> visitantesMesYear(String nombreFichero) {
		HashMap<Integer, ArrayList<Float>> resultado = new HashMap<Integer, ArrayList<Float>>();
		HashMap<Integer, Float> total = new HashMap<Integer, Float>();
		try {
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("@");
				int clave = Integer.parseInt(campos[1]);
				if (resultado.get(clave) == null)
					resultado.put(clave, new ArrayList<Float>());
				resultado.get(clave).add(Float.parseFloat(campos[2]));
			}
			fr.close();
			br.close();
			Set<Integer> claves = resultado.keySet();
			for (Integer clave : claves) {
				ArrayList<Float> listaVisitantes = resultado.get(clave);
				float visitantes = 0;
				for (float cantidad : listaVisitantes) {
					visitantes += cantidad;
				}
				total.put(clave, visitantes);
			}
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}

		System.out.println("Visitantes al mes: " + resultado + " = " + total);
		return resultado;

	}

	public int visitantesMesYear(int mes, int[][] v) {
		int acu = 0;
		for (int i = 0; i < v.length; i++)
			acu += v[i][mes];
		return acu;
	}

	public int visitantesMesYear(int mes, ArrayList<ArrayList<Integer>> v) {
		int acu = 0;
		for (ArrayList<Integer> visitas : v)
			acu += visitas.get(mes);
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

	public void recorrerMatrizIrregularPorColumnas(ArrayList<ArrayList<Integer>> matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (ArrayList<Integer> filas : matriz) {
			if (filas.size() > JMAX)
				JMAX = matriz.size();
		}
		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.size(); i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz.get(i).get(j));
				} catch (IndexOutOfBoundsException e) {
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

	public void recorrerMatrizIrregularPorColumnas2(ArrayList<ArrayList<Integer>> matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.size(); i++) {
			if (matriz.get(i).size() > JMAX)
				JMAX = matriz.get(i).size();
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.size(); i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz.get(i).get(j).byteValue());
				} catch (IndexOutOfBoundsException e) {
					continue;
				} catch (NullPointerException e) {
					continue;
				}

			}
		}
	}
}
