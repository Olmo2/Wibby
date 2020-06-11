package com.olmo.wibby.distribuidor;

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
@Table(name="distribuidores")
public class Distribuidor {

	@Id
	private String id;

	@Column
	private String nombre;

	@Column
	private String pais;

	@Lob
	private byte[] img;

	@Column
	private Integer anio;

	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dist")
	List<Juego> juegos = new ArrayList<Juego>();

	public void addJuego(Juego juego) {

		if (!juegos.contains(juego)) {

			juegos.add(juego);
		}
	}

	@PreRemove
	public final void reasignarDistribuidor() {
		System.out.println("Destrucción");
		// recorrer la lista de usuarios reasignando los Distribuidores
		for (Juego juego : juegos) {

			juego.setDist(null);
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
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

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}

	@Override
	public String toString() {
		return id;
	}

}
