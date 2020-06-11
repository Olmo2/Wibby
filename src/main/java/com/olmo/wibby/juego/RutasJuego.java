package com.olmo.wibby.juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.olmo.wibby.desarrollador.Desarrollador;
import com.olmo.wibby.desarrollador.DesarrolladorDAO;
import com.olmo.wibby.distribuidor.Distribuidor;
import com.olmo.wibby.distribuidor.DistribuidorDAO;
import com.olmo.wibby.plataforma.Plataforma;
import com.olmo.wibby.plataforma.PlataformaDAO;
import com.olmo.wibby.servicios.FicherosDB;

@Controller
public class RutasJuego {

	@Autowired
	private JuegoDAO juegoDAO;

	@Autowired
	private PlataformaDAO plataformaDAO;

	@Autowired
	private DistribuidorDAO distDAO;

	@Autowired
	private DesarrolladorDAO desaDAO;

	@Autowired
	private FicherosDB ficheroDB;

	

	@GetMapping("/juegos")
	public ModelAndView todosLosProductos() throws InterruptedException {

		ModelAndView mav = new ModelAndView();
		List<Juego> listaJuegos = (List<Juego>) juegoDAO.findAll();
		mav.addObject("juegos", listaJuegos);
		try {
			ficheroDB.guardarListaJuegos(listaJuegos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("juegos"); /* Nombre del html que se muestra */
		mav.addObject("juego", new Juego());

		List<Plataforma> listaPlataformas = (List<Plataforma>) plataformaDAO.findAll();
		mav.addObject("plataformas", listaPlataformas);

		List<Distribuidor> listaDistribuidores = (List<Distribuidor>) distDAO.findAll();
		mav.addObject("distribuidores", listaDistribuidores);

		List<Desarrollador> listaDesarrolladores = (List<Desarrollador>) desaDAO.findAll();
		mav.addObject("desarrolladores", listaDesarrolladores);

//		ArrayList<Juego> lista = new ArrayList<Juego>();
//		lista = (ArrayList<Juego>) juegoDAO.findAll();
//		System.out.println(lista);
//		System.out.println(juegoDAO.findAll());

		return mav;
	}

	@GetMapping("/juegos/{filtro}")
	public ModelAndView productosPlataforma(@PathVariable String filtro) {

		boolean contienePlat = false;
		boolean contieneDist = false;
		boolean contieneDesa = false;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("juegosfiltrados"); /* Nombre del html que se muestra */
		mav.addObject("juego", new Juego());
		List<Juego> listaJuegos = new ArrayList<Juego>();

		List<Plataforma> listaPlataformas = (List<Plataforma>) plataformaDAO.findAll();
		mav.addObject("plataformas", listaPlataformas);

		List<Distribuidor> listaDistribuidores = (List<Distribuidor>) distDAO.findAll();
		mav.addObject("distribuidores", listaDistribuidores);

		List<Desarrollador> listaDesarrolladores = (List<Desarrollador>) desaDAO.findAll();
		mav.addObject("desarrolladores", listaDesarrolladores);

		for (int i = 0; i < listaPlataformas.size(); i++) {
			if (listaPlataformas.get(i).getId().equals(filtro)) {
				contienePlat = true;
			}
		}

		for (int i = 0; i < listaDistribuidores.size(); i++) {
			if (listaDistribuidores.get(i).getId().equals(filtro)) {
				contieneDist = true;
			}
		}

		for (int i = 0; i < listaDesarrolladores.size(); i++) {
			if (listaDesarrolladores.get(i).getId().equals(filtro)) {
				contieneDesa = true;
			}
		}

		if (filtro.equals("noPlat")) {
			listaJuegos = (List<Juego>) juegoDAO.findAllByPlat_id(null);
		} else if (filtro.equals("noDist")) {
			listaJuegos = (List<Juego>) juegoDAO.findAllByDist_id(null);
		} else if (filtro.equals("noDesa")) {
			listaJuegos = (List<Juego>) juegoDAO.findAllByDesa_id(null);
		} else if (contienePlat) {

			listaJuegos = (List<Juego>) juegoDAO.findAllByPlat_id(filtro);

		} else if (contieneDist) {

			listaJuegos = (List<Juego>) juegoDAO.findAllByDist_id(filtro);

		} else if (contieneDesa) {

			listaJuegos = (List<Juego>) juegoDAO.findAllByDesa_id(filtro);

		}

		mav.addObject("juegos", listaJuegos);

		return mav;

	}

	@GetMapping("/juego/{filtro}")
	public ModelAndView juego(@PathVariable Integer filtro) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("juego");
		Juego juego = juegoDAO.findById(filtro).get();
		mav.addObject("juego", juego);

		return mav;
	}

	@PostMapping("/juegos/anadir")
	public String juegosAnadir(@ModelAttribute Juego juego, @RequestParam(value = "file") MultipartFile file) {
		System.out.println(juego.getPlat());

		Plataforma plataforma = new Plataforma();
		plataforma.setId(null);
		System.out.println(juego.getPlat().toString());
		if (juego.getPlat().toString().equals("null")) {
			juego.setPlat(plataforma);
		}

		Distribuidor dist = new Distribuidor();
		dist.setId(null);
		if (juego.getDist().toString().equals("null")) {
			juego.setDist(dist);
		}

		Desarrollador desa = new Desarrollador();
		desa.setId(null);
		if (juego.getDesa().toString().equals("null")) {
			juego.setDesa(desa);
		}
		System.out.println(file);
		try {
			juego.setImg(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		juegoDAO.save(juego);

		return "redirect:/juegos";
	}

	@GetMapping("/juegos/borrar/{id}")
	public String juegosBorrar(@PathVariable Integer id) {

		juegoDAO.deleteById(id);

		return "redirect:/juegos";
	}

	@PostMapping("/juegos/editar")
	public String juegosEditar(@Valid Juego juego, Errors errores,
			/* ModelMap map */
			@ModelAttribute("juego") Juego game, @RequestParam(value = "file") MultipartFile file) {
		System.out.println(game.getPlat().getId());

		Plataforma plataforma = new Plataforma();
		plataforma.setId(null);
		System.out.println(juego.getPlat().toString());
		if (juego.getPlat() == null) {
			juego.setPlat(plataforma);
		}

		Distribuidor dist = new Distribuidor();
		dist.setId(null);
		if (juego.getDist().toString().equals("null")) {
			juego.setDist(dist);
		}

		Desarrollador desa = new Desarrollador();
		desa.setId(null);
		if (juego.getDesa().toString().equals("null")) {
			juego.setDesa(desa);
		}

		try {
			if (file.isEmpty()) {
				juego.setImg(juegoDAO.findById(juego.getRef()).get().getImg());
			} else {
				juego.setImg(file.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		juegoDAO.save(game);

		return "redirect:/juegos";
	}

	@GetMapping("/juegos/editar/{id}")
	public String juegosEditar(@PathVariable Integer id, @ModelAttribute Juego juego) {

		juego = juegoDAO.findById(id).get();
		System.out.println(juego.getPlat().getId());

		return "/juegos/editar";
	}

}
