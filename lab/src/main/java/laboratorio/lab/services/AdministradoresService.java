package main.java.laboratorio.lab.services;

import main.java.laboratorio.lab.models.Estudiantes;
import main.java.laboratorio.lab.models.Laboratorista;
import main.java.laboratorio.lab.models.Reservas;
import main.java.laboratorio.lab.repositories.EstudiantesRepository;
import main.java.laboratorio.lab.repositories.LaboratoristaRepository;
import main.java.laboratorio.lab.repositories.ReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradoresService {
    private LaboratoristaRepository administradoresrepository;
    private EstudiantesRepository estudiantesRepository;
    private ReservasRepository reservasRepository;

    public AdministradoresService(LaboratoristaRepository administradoresrepository, EstudiantesRepository estudiantesRepository, ReservasRepository reservasRepository) {
        this.administradoresrepository = administradoresrepository;
        this.estudiantesRepository = estudiantesRepository;
        this.reservasRepository = reservasRepository;
    }

    public boolean autenticar(String correo, String contrasena) {
        Optional<Laboratorista> laboratorista = administradoresrepository.findByCorreo(correo);
        if (laboratorista.isPresent()) {
            Laboratorista laboratorista1 = laboratorista.get();
            return laboratorista1.getContrasena().equals(contrasena);
        }
        return false;
    }

    public List<Estudiantes> obtenerTodosLosLaboratoristas() {
        return estudiantesRepository.findAll();
    }

    public boolean actualizarCodigoCarnet(Integer idCodigo, String nuevoCodigoCarnet) {
        Optional<Estudiantes> estudianteOpt = estudiantesRepository.findById(idCodigo);
        if (estudianteOpt.isPresent()) {
            Estudiantes estudiante = estudianteOpt.get();
            estudiante.setCodigoCarnet(nuevoCodigoCarnet);
            estudiantesRepository.save(estudiante);
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(Integer idCodigo) {
        List<Reservas> reservas = reservasRepository.findReservasByEstudianteId(idCodigo);
        if (reservas != null && !reservas.isEmpty()) {
            reservasRepository.deleteAll(reservas);
        }

        if (estudiantesRepository.existsById(idCodigo)) {
            estudiantesRepository.deleteById(idCodigo);
            return true;
        }
        return false;
    }
}
