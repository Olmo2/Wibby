package com.olmo.wibby.juego;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.olmo.wibby.desarrollador.Desarrollador;
import com.olmo.wibby.distribuidor.Distribuidor;
import com.olmo.wibby.plataforma.Plataforma;

@Entity
@Table(name = "juegos")
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ref;

	@Lob
	private byte[] img;

	@Column
	private String nombre;

	@Column
	private String pegi;

	@Column
	private String anio;

	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Plataforma plat = new Plataforma();

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Distribuidor dist = new Distribuidor();

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Desarrollador desa = new Desarrollador();

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPegi() {
		return pegi;
	}

	public void setPegi(String pegi) {
		this.pegi = pegi;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Plataforma getPlat() {
		return plat;
	}

	public void setPlat(Plataforma plat) {
		this.plat = plat;
	}

	public Distribuidor getDist() {
		return dist;
	}

	public void setDist(Distribuidor dist) {
		this.dist = dist;
	}

	public Desarrollador getDesa() {
		return desa;
	}

	public void setDesa(Desarrollador desa) {
		this.desa = desa;
	}

	@Override
	public String toString() {
		return "Juego [ref=" + ref + ", img=" + img + ", nombre=" + nombre + ", pegi=" + pegi + ", anio=" + anio
				+ ", plat=" + plat + "]";
	}

}
