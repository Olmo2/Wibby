package com.olmo.wibby.distribuidor;

import java.io.IOException;
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

import com.olmo.wibby.juego.Juego;
import com.olmo.wibby.juego.JuegoDAO;
import com.olmo.wibby.servicios.FicherosDB;

@Controller
public class RutasDistribuidor {

	@Autowired
	private JuegoDAO juegoDAO;

	@Autowired
	private DistribuidorDAO distDAO;

	@Autowired
	private FicherosDB ficheroDB;

	@GetMapping("/distribuidores")
	public ModelAndView distribuidores() throws InterruptedException {

		ModelAndView mav = new ModelAndView();
		List<Distribuidor> listaDist = (List<Distribuidor>) distDAO.findAll();
		mav.addObject("distribuidores", listaDist);
		try {
			ficheroDB.guardarListaDist(listaDist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("distribuidores"); /* Nombre del html que se muestra */
		mav.addObject("dist", new Distribuidor());

		return mav;
	}
	
	@GetMapping("/distribuidores/{filtro}")
	public ModelAndView distribuidor(@PathVariable String filtro) {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("distribuidor");
		Distribuidor dist = distDAO.findById(filtro).get();
		mav.addObject("dist", dist);
		
		
		
		
		return mav;
	}
	
	

	@PostMapping("/distribuidores/anadir")
	public String distAnadir(@ModelAttribute Distribuidor dist, @RequestParam(value = "file") MultipartFile file) {

		try {
			dist.setImg(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		distDAO.save(dist);

		return "redirect:/distribuidores";
	}

	@GetMapping("/distribuidores/borrar/{id}")
	public String distBorrar(@PathVariable String id) {

		distDAO.deleteById(id);

		return "redirect:/distribuidores";
	}

	@PostMapping("/distribuidores/editar")
	public String distribuidoresEditar(@Valid Juego juego, Errors errores,
			/* ModelMap map */
			@ModelAttribute("dist") Distribuidor dist, @RequestParam(value = "file") MultipartFile file) {

		try {
			if (file.isEmpty()) {
				dist.setImg(distDAO.findById(dist.getId()).get().getImg());
			} else {
				dist.setImg(file.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		distDAO.save(dist);

		return "redirect:/distribuidores";
	}

	@GetMapping("/distribuidores/editar/{id}")
	public String distribuidoresEditar(@PathVariable String id, @ModelAttribute Distribuidor dist) {

		dist = distDAO.findById(id).get();

		return "/distribuidores/editar";
	}

}