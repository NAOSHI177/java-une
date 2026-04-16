package colecciones.banco.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Persona {

    private String nombre;
    private String documento;
    private BigDecimal rendimientoAnual;
    private TipoPersona tipo = TipoPersona.FISICA;
    private LocalDateTime fechaUltimaAtualizacion = LocalDateTime.now();

    public Persona() {
    }

    public Persona(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }
///get y setters
   
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", tipo=" + tipo +
                '}';
    }
    

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public BigDecimal getRendimientoAnual() {
		return rendimientoAnual;
	}

	public void setRendimientoAnual(BigDecimal rendimientoAnual) {
		this.rendimientoAnual = rendimientoAnual;
	}

	public TipoPersona getTipo() {
		return tipo;
	}

	public void setTipo(TipoPersona tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getFechaUltimaAtualizacion() {
		return fechaUltimaAtualizacion;
	}

	public void setFechaUltimaAtualizacion(LocalDateTime fechaUltimaAtualizacion) {
		this.fechaUltimaAtualizacion = fechaUltimaAtualizacion;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(documento, persona.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

}