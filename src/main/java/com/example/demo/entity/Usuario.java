package com.example.demo.entity;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(columnDefinition = "INT DEFAULT 0")
	private int id_estado;
	
	@NotEmpty
	@Length(min=9,max = 10)
	private String rut;
	
	@NotEmpty(message = "El nombre de comuna es obligatorio.")
	private String Nombres;
	
	@NotEmpty
	private String ap_paterno;
	
	@NotEmpty
	private String ap_materno;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String direccion;
	

	
	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	//Para que no guarde fecha en nulo
	@PrePersist
	protected void onCreatee() {
		created_at = new Date(Calendar.getInstance().getTime().getTime());
	}
	
	/** Relaciones*/
	@OneToOne
	@JoinColumn(name="id_perfil", unique = true)
	private Rol rol;

	

	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public int getId_estado() {
		return id_estado;
	}



	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}



	public String getRut() {
		return rut;
	}



	public void setRut(String rut) {
		this.rut = rut;
	}



	public String getNombres() {
		return Nombres;
	}



	public void setNombres(String nombres) {
		Nombres = nombres;
	}



	public String getAp_paterno() {
		return ap_paterno;
	}



	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}



	public String getAp_materno() {
		return ap_materno;
	}



	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	



	public Date getCreated_at() {
		return created_at;
	}



	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}



	public Rol getRol() {
		return rol;
	}



	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
    public Long getId_perfil() {
        // Puedes devolver el id del perfil a través del objeto Rol si es necesario
        return (rol != null) ? rol.getId() : null;
    }



	@Override
	public String toString() {
		return "Usuario [Id=" + Id + ", id_estado=" + id_estado + ", rut=" + rut + ", Nombres=" + Nombres
				+ ", ap_paterno=" + ap_paterno + ", ap_materno=" + ap_materno + ", email=" + email + ", direccion="
				+ direccion + ", created_at=" + created_at + ", rol=" + rol + "]";
	}



	

	
	
}
