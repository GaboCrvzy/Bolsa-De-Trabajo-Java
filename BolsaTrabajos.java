package bolsatrabajos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BolsaTrabajos {
    public static void main(String[] args) {
        ArrayList<Puesto> puestos = new ArrayList<>();

        // Datos iniciales
        Competencia[] reqs = {new Competencia("Java", Nivel.INTERMEDIO), new Competencia("SQL", Nivel.INTERMEDIO)};
        Puesto puestoInicial = new Puesto("", "PROGRAMADOR", "BANCO DE CHILE", "Viña del Mar", reqs);
        puestoInicial.setId(1234);

        Postulante postulanteUno= new Postulante("266666666", "Juanito Hernandez", "Valparaiso", "949033564");
        postulanteUno.agregarCompetencia("Java", Nivel.AVANZADO);
        postulanteUno.agregarCompetencia("SQL", Nivel.INTERMEDIO);

        Postulante postulanteDos = new Postulante("214486320", "Alexis Escobar", "Santiago", "976321235");
        postulanteDos.agregarCompetencia("Java", Nivel.BASICO);
        postulanteDos.agregarCompetencia("SQL", Nivel.INTERMEDIO);

        puestoInicial.agregarPostulante(postulanteUno);
        puestoInicial.agregarPostulante(postulanteDos);
        puestos.add(puestoInicial);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
        {
            boolean running = true;

            while (running) {
                System.out.println("\n==== MENÚ ====");
                System.out.println("1. Agregar un nuevo puesto y sus requisitos");
                System.out.println("2. Agregar postulante a un puesto existente");
                System.out.println("3. Mostrar puestos y postulantes seleccionados");
                System.out.println("4. Ver información de un postulante");
                System.out.println("5. Eliminar requisito de un puesto");
                System.out.println("6. Salir");
                System.out.print("Opción: ");

                String linea = br.readLine();
                if (linea == null) break; 
                int opcion;
                try {
                    opcion = Integer.parseInt(linea.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Ingrese un número (1-7).");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        // Crear nuevo puesto con validaciones
                        String newId;
                        while (true) {
                            System.out.print("ID del puesto: ");
                            newId = br.readLine();
                            if (newId == null || newId.trim().isEmpty() || !newId.matches("\\d+")) {
                                System.out.println("El ID no puede estar vacío ni ser no numérico.");
                                continue;
                            }
                            boolean idExiste = false;
                            for (Puesto p : puestos) {
                                if (p.getId().equals(newId.trim())) {
                                    idExiste = true;
                                    break;
                                }
                            }
                            if (idExiste) {
                                System.out.println("El ID ya existe. Ingrese uno diferente.");
                            } else {
                                break; 
                            }
                        }
                        String newTitulo;
                        while (true) {
                            System.out.print("Título: ");
                            newTitulo = br.readLine();
                            if (newTitulo == null || newTitulo.trim().isEmpty() || newTitulo.matches("\\d+")) {
                                System.out.println("El título no puede ser vacío ni solo números.");
                            } else {
                                break;
                            }
                        }
                        String newEmpresa;
                        while (true) {
                            System.out.print("Empresa: ");
                            newEmpresa = br.readLine();
                            if (newEmpresa == null || newEmpresa.trim().isEmpty() || newEmpresa.matches("\\d+")) {
                                System.out.println("La empresa no puede ser vacía ni solo números.");
                            } else {
                                break;
                            }
                        }
                        String newCiudad;
                        while (true) {
                            System.out.print("Ciudad: ");
                            newCiudad = br.readLine();
                            if (newCiudad == null || newCiudad.trim().isEmpty() || newCiudad.matches("\\d+")) {
                                System.out.println("La ciudad no puede ser vacía ni solo números.");
                            } else {
                                break;
                            }
                        }
                        int nReq;
                        while (true) {
                            System.out.print("¿Cuántos requisitos (competencias) tendrá este puesto?: ");
                            String nReqLine = br.readLine();
                            if (nReqLine != null && nReqLine.matches("\\d+")) {
                                nReq = Integer.parseInt(nReqLine.trim());
                                break; 
                            } else {
                                System.out.println("Debe ingresar un número entero válido.");
                            }
                        }
                        Competencia[] requisitos = new Competencia[nReq];
                        for (int i = 0; i < nReq; i++) {
                            String nomReq;
                            while (true) {
                                System.out.print("Nombre de la competencia " + (i + 1) + ": ");
                                nomReq = br.readLine();
                                if (nomReq == null || nomReq.trim().isEmpty() || nomReq.matches("\\d+")) {
                                    System.out.println("El nombre de la competencia no puede ser vacío ni solo números.");
                                } else {
                                    break;
                                }
                            }
                            String nivelTxt;
                            Nivel nivelReq;
                            while (true) {
                                System.out.print("Nivel (BASICO, INTERMEDIO, AVANZADO) para '" + nomReq + "': ");
                                nivelTxt = br.readLine();
                                if (nivelTxt == null) nivelTxt = "";
                                try {
                                    nivelReq = Nivel.valueOf(nivelTxt.trim().toUpperCase());
                                    break; 
                                } catch (Exception ex) {
                                    System.out.println("Nivel inválido. Intente de nuevo.");
                                }
                            }
                            requisitos[i] = new Competencia(Objects.requireNonNull(nomReq).trim(), nivelReq);
                        }
                        Puesto nuevoPuesto = new Puesto("", Objects.requireNonNull(newTitulo).trim(),Objects.requireNonNull(newEmpresa).trim(),
                        Objects.requireNonNull(newCiudad).trim(),requisitos);
                        nuevoPuesto.setId(Integer.parseInt(newId.trim()));
                        puestos.add(nuevoPuesto);
                        System.out.println("Puesto agregado: " + nuevoPuesto.getTitulo() + " (ID: " + nuevoPuesto.getId() + ")");
                        break;

                    case 2:
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
                            if (p.getId().equals(idBuscar)) { 
                                encontrado = p; 
                                break; 
                            }
                        }
                        if (encontrado == null) {
                            System.out.println("Puesto no encontrado.");
                            break;
                        }

                        String rut;
                        while (true) {
                            System.out.print("RUT (solo números): ");
                            rut = br.readLine();
                            if (rut != null && rut.matches("\\d+")) {
                                break; 
                            } else {
                                System.out.println("El RUT debe ser numérico.");
                            }
                        }
                        String nombre;
                        while (true) {
                            System.out.print("Nombre: ");
                            nombre = br.readLine();
                            if (nombre != null && !nombre.trim().isEmpty() && !nombre.matches("\\d+")) {
                                break;
                            } else {
                                System.out.println("El nombre no puede estar vacío ni ser solo números.");
                            }
                        }
                        String ciudad;
                        while (true) {
                            System.out.print("Ciudad: ");
                            ciudad = br.readLine();
                            if (ciudad != null && !ciudad.trim().isEmpty() && !ciudad.matches("\\d+")) {
                                break;
                            } else {
                                System.out.println("La ciudad no puede estar vacía ni ser solo números.");
                            }
                        }
                        String contacto;
                        while (true) {
                            System.out.print("Contacto (solo números, 8-9 dígitos): ");
                            contacto = br.readLine();
                            if (contacto != null && contacto.matches("\\d{8,9}")) {
                                break;
                            } else {
                                System.out.println("El contacto debe ser numérico de 8 o 9 dígitos.");
                            }
                        }

                        Postulante nuevo = new Postulante(rut, nombre, ciudad, contacto);

                        int n; 
                        while (true) {
                            System.out.print("¿Cuántas competencias?: ");
                            String nLine = br.readLine();
                            if (nLine != null && nLine.matches("\\d+") && Integer.parseInt(nLine) > 0) {
                                n = Integer.parseInt(nLine.trim());
                                break; 
                            } else {
                                System.out.println("Debe ingresar un número entero válido."); 
                            }
                        }

                        for (int i = 0; i < n; i++) {
                            String nomComp;
                            while (true) {
                                System.out.print("Nombre competencia: ");
                                nomComp = br.readLine();
                                if (nomComp != null && !nomComp.trim().isEmpty() && !nomComp.matches("\\d+")) {
                                    break; // válido
                                } else {
                                    System.out.println("El nombre de la competencia no puede estar vacío ni ser solo números.");
                                }
                            }
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

                    case 4: // Ver info de un postulante
                        System.out.print("Ingrese RUT del postulante a buscar: ");
                        String rutBuscar = br.readLine();
                        boolean encontradoPostulante = false; 
                        for (Puesto p : puestos) 
                        {
                            for (Postulante pos : p.getPostulantes())
                            {
                                if (pos.getRut().equals(rutBuscar))
                                {
                                    System.out.println("Postulante encontrado en puesto: " + p.getTitulo());
                                    System.out.println(pos);
                                    encontradoPostulante = true;
                                    break;
                                }
                            }
                            if (encontradoPostulante) break;
                        }
                        if (!encontradoPostulante) {
                            System.out.println("No se encontró un postulante con ese RUT.");
                        }
                        break;
                        
                    case 5: // Eliminar requisito de un puesto
                        System.out.print("Ingrese ID del puesto: ");
                        String idReq = br.readLine();
                        Puesto puestoReq = null;
                        for (Puesto p : puestos) {
                            if (p.getId().equals(idReq)) {
                                puestoReq = p;
                                break;
                            }
                        }
                        if (puestoReq == null) {
                            System.out.println("Puesto no encontrado.");
                            break;
                        }
                        Competencia[] reqsPuesto  = puestoReq.getRequisitos();
                        if (reqsPuesto .length == 0) {
                            System.out.println("El puesto no tiene requisitos.");
                            break;
                        }
                        System.out.println("Requisitos actuales:");
                        for (int i = 0; i < reqsPuesto .length; i++) {
                            System.out.println((i + 1) + ". " + reqsPuesto [i]);
                        }
                        System.out.print("Ingrese número del requisito a eliminar: ");
                        String num = br.readLine();
                        if (num.matches("\\d+")) {
                            int idx = Integer.parseInt(num) - 1;
                            if (idx >= 0 && idx < reqsPuesto .length) {
                                ArrayList<Competencia> listaReq = new ArrayList<>();
                                for (int i = 0; i < reqsPuesto .length; i++) {
                                    if (i != idx) listaReq.add(reqsPuesto [i]);
                                }
                                puestoReq.setRequisitos(listaReq.toArray(new Competencia[0]));
                                System.out.println("Requisito eliminado.");
                            } else {
                                System.out.println("Número fuera de rango.");
                            }
                        } else {
                            System.out.println("Entrada inválida.");
                        }
                        break;

                    case 6:
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
