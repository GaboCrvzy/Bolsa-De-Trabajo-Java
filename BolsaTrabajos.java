package bolsatrabajos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class BolsaTrabajos {
    public static void main(String[] args) {
        ArrayList<Puesto> puestos = new ArrayList<>();

        // Datos iniciales (SIA1.4)
        String[] reqs = { "Java:INTERMEDIO", "SQL:INTERMEDIO" };
        Puesto puestoInicial = new Puesto("1234", "PROGRAMADOR", "BANCO DE CHILE", "Viña del Mar", reqs);

        Postulante g = new Postulante("217104491", "Gabriel Fuentes", "Valparaiso", "949033564");
        g.agregarCompetencia("Java", Nivel.AVANZADO);
        g.agregarCompetencia("SQL", Nivel.INTERMEDIO);

        Postulante a = new Postulante("214486320", "Alexis Escobar", "Santiago", "976321235");
        a.agregarCompetencia("Java", Nivel.BASICO);
        a.agregarCompetencia("SQL", Nivel.INTERMEDIO);

        puestoInicial.agregarPostulante(g);
        puestoInicial.agregarPostulante(a);
        puestos.add(puestoInicial);

        // BufferedReader para entrada por consola
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean running = true;

            while (running) {
                System.out.println("\n==== MENÚ ====");
                System.out.println("1. Agregar un nuevo puesto y sus requisitos");
                System.out.println("2. Agregar postulante a un puesto existente");
                System.out.println("3. Mostrar puestos y postulantes seleccionados");
                System.out.println("4. Salir");
                System.out.print("Opción: ");

                String linea = br.readLine();
                if (linea == null) break; // EOF
                int opcion;
                try {
                    opcion = Integer.parseInt(linea.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Ingrese un número (1-4).");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        // Crear nuevo puesto
                        System.out.print("ID del puesto: ");
                        String newId = br.readLine();

                        System.out.print("Título: ");
                        String newTitulo = br.readLine();

                        System.out.print("Empresa: ");
                        String newEmpresa = br.readLine();

                        System.out.print("Ciudad: ");
                        String newCiudad = br.readLine();

                        System.out.print("¿Cuántos requisitos (competencias) tendrá este puesto?: ");
                        String nReqLine = br.readLine();
                        int nReq;
                        try {
                            nReq = Integer.parseInt(nReqLine.trim());
                            if (nReq < 0) nReq = 0;
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido. Se asume 0 requisitos.");
                            nReq = 0;
                        }

                        String[] requisitos = new String[nReq];
                        for (int i = 0; i < nReq; i++) {
                            System.out.print("Nombre de la competencia " + (i + 1) + ": ");
                            String nomReq = br.readLine();
                            if (nomReq == null) nomReq = "";
                            String nivelTxt;
                            while (true) {
                                System.out.print("Nivel (BASICO, INTERMEDIO, AVANZADO) para '" + nomReq + "': ");
                                nivelTxt = br.readLine();
                                if (nivelTxt == null) nivelTxt = "";
                                try {
                                    Nivel.valueOf(nivelTxt.trim().toUpperCase());
                                    break; // válido
                                } catch (Exception ex) {
                                    System.out.println("Nivel inválido. Intente de nuevo.");
                                }
                            }
                            requisitos[i] = nomReq.trim() + ":" + nivelTxt.trim().toUpperCase();
                        }

                        Puesto nuevoPuesto = new Puesto(newId, newTitulo, newEmpresa, newCiudad, requisitos);
                        puestos.add(nuevoPuesto);
                        System.out.println("Puesto agregado: " + nuevoPuesto.getTitulo() + " (ID: " + nuevoPuesto.getId() + ")");
                        break;

                    case 2:
                        // Agregar postulante a puesto (listar puestos disponibles)
                        if (puestos.isEmpty()) {
                            System.out.println("No hay puestos disponibles. Agrega uno primero.");
                            break;
                        }
                        System.out.println("Puestos disponibles:");
                        for (Puesto p : puestos) {
                            System.out.println(" - ID: " + p.getId() + " | " + p.getTitulo() + " - " + p.getEmpresa());
                        }
                        System.out.print("Ingrese id del puesto donde agregar el postulante: ");
                        String idBuscar = br.readLine();
                        Puesto encontrado = null;
                        for (Puesto p : puestos) {
                            if (p.getId().equals(idBuscar)) { encontrado = p; break; }
                        }
                        if (encontrado == null) {
                            System.out.println("Puesto no encontrado.");
                            break;
                        }

                        System.out.print("RUT: ");
                        String rut = br.readLine();
                        System.out.print("Nombre: ");
                        String nombre = br.readLine();
                        System.out.print("Ciudad: ");
                        String ciudad = br.readLine();
                        System.out.print("Contacto: ");
                        String contacto = br.readLine();
                        Postulante nuevo = new Postulante(rut, nombre, ciudad, contacto);

                        System.out.print("¿Cuántas competencias?: ");
                        String nLine = br.readLine();
                        int n;
                        try {
                            n = Integer.parseInt(nLine.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido. Se asume 0 competencias.");
                            n = 0;
                        }

                        for (int i = 0; i < n; i++) {
                            System.out.print("Nombre competencia: ");
                            String nomComp = br.readLine();
                            System.out.print("Nivel (BASICO, INTERMEDIO, AVANZADO): ");
                            String nivelTxt = br.readLine();
                            try {
                                Nivel niv = Nivel.valueOf(nivelTxt.trim().toUpperCase());
                                nuevo.agregarCompetencia(nomComp, niv);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Nivel inválido. Se omitió esa competencia.");
                            }
                        }
                        encontrado.agregarPostulante(nuevo);
                        System.out.println("Postulante agregado al puesto " + encontrado.getTitulo());
                        break;

                    case 3:
                        // Mostrar puestos y postulantes seleccionados
                        for (Puesto p : puestos) {
                            System.out.println("\n" + p);
                            ArrayList<Postulante> seleccion = p.seleccionarPostulantes();
                            if (seleccion.isEmpty()) {
                                System.out.println("Ningún postulante cumple todos los requisitos.");
                            } else {
                                System.out.println("Postulantes que cumplen los requisitos:");
                                for (Postulante ps : seleccion) {
                                    System.out.println(" - " + ps);
                                }
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        running = false;
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura desde consola: " + e.getMessage());
        }

        System.out.println("Programa finalizado.");
    }
}
