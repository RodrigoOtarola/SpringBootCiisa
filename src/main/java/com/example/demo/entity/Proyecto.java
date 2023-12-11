package com.example.demo.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="proyectos")
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_comuna",unique = false)
	private Comuna comuna;
	
	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	//Para que no guarde fecha en nulo
	@PrePersist
	protected void onCreatee() {
		created_at = new Date(Calendar.getInstance().getTime().getTime());
	}	
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Comuna getComuna() {
		return comuna;
	}



	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}



	public Date getCreated_at() {
		return created_at;
	}



	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}



	public Long getId_perfil() {
        // Puedes devolver el id de la comuna a través del objeto Rol si es necesario
        return (comuna != null) ? comuna.getId() : null;
    }



	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", comuna=" + comuna + ", created_at=" + created_at + "]";
	}

		
	
}
