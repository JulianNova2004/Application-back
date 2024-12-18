package Los_Jsons.sistemas_reservas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LABORATORISTA")
public class Laboratorista {
    @Id
    private Integer id_codigo;

    @Column(name = "CORREO")
    private String correo;

    @Column (name = "CONTRASENA")
    private String contrasena;

    public Integer getId_codigo() {return id_codigo;}

    public String getCorreo() {return correo;}

    public String getContrasena() {return contrasena;}

    public void setId_codigo(Integer id_codigo) {this.id_codigo = id_codigo;}

    public void setCorreo(String correo) {this.correo = correo;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
}
