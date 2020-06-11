package com.olmo.wibby.plataforma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.olmo.wibby.juego.Juego;


@Entity
@Table(name="plataformas")
public class Plataforma {

	@Id
	private String id;
	
	@Column
	private String nombre;
	
	@Lob
	private byte[] img;
	
	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;
	
	@Column
	private Integer anio;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "plat")
	List<Juego> juegos = new ArrayList<Juego>();

	public void addProducto(Juego juego) {

		if (!juegos.contains(juego)) {

			juegos.add(juego);
		}
	}
	

	@PreRemove
	public final void reasignarPlataforma() {
		System.out.println("Destrucción");
		// recorrer la lista de usuarios reasignando los roles
		for (Juego juego : juegos) {

			juego.setPlat(null);
		}
	}

	

	


	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Juego> getJuegos() {
		return juegos;
	}


	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}


	

	@Override
	public String toString() {
		return id ;
	}
	

}
