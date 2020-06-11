package com.olmo.wibby.plataforma;

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
public class RutasPlataforma {

	
	@Autowired
	PlataformaDAO plataformaDAO;
	
	@Autowired
	JuegoDAO juegoDAO;
	
	@Autowired 
	FicherosDB ficheroDB;
	
	@GetMapping("/plataformas")
	public ModelAndView todasLasPlataformas() {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("plataformas");
		mav.addObject("plat",new Plataforma());
		
		List<Plataforma> listaPlataformas = (List<Plataforma>)plataformaDAO.findAll();
		mav.addObject("plataformas",listaPlataformas);
		try {
			ficheroDB.guardarListaPlataformas(listaPlataformas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return mav;
	}
	
	@GetMapping("/plataformas/{filtro}")
	public ModelAndView plataforma(@PathVariable String filtro) {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("plataforma");
		Plataforma plat = plataformaDAO.findById(filtro).get();
		mav.addObject("plat",plat);
		
		
		
		
		return mav;
	}
	
	
	@PostMapping("/plataformas/anadir")
	public String plataformasAnadir(@ModelAttribute Plataforma plataforma,
			@RequestParam(value="file") MultipartFile file) {
		try {
			plataforma.setImg(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plataformaDAO.save(plataforma);
		
		return "redirect:/plataformas";
	}
	
	@GetMapping("/plataformas/borrar/{id}")
	public String plataformasBorrar(@PathVariable String id) {

		plataformaDAO.deleteById(id);
		
		return "redirect:/plataformas";
	}
	
	@PostMapping("/plataformas/editar")
	public String plataformasEditar(@Valid Juego juego, Errors errores,
			/* ModelMap map */
			@ModelAttribute("plat") Plataforma plat, @RequestParam(value = "file") MultipartFile file) {

		try {
			if (file.isEmpty()) {
				plat.setImg(plataformaDAO.findById(plat.getId()).get().getImg());
			} else {
				plat.setImg(file.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		plataformaDAO.save(plat);

		return "redirect:/plataformas";
	}

	@GetMapping("/plataformas/editar/{id}")
	public String plataformasEditar(@PathVariable String id, @ModelAttribute Plataforma plat) {

		plat = plataformaDAO.findById(id).get();

		return "redirect:/plataforma/editar";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
