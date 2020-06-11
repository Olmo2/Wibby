package com.olmo.wibby.desarrollador;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.olmo.wibby.servicios.FicherosDB;

@Controller
public class RutasDesarrollador {

	@Autowired
	private DesarrolladorDAO desaDAO;

	@Autowired
	private FicherosDB ficheroDB;

	@GetMapping("/desarrolladores")
	public ModelAndView desarrolladores() throws InterruptedException {

		ModelAndView mav = new ModelAndView();
		List<Desarrollador> listaDesa = (List<Desarrollador>) desaDAO.findAll();
		mav.addObject("desarrolladores", listaDesa);
		try {
			ficheroDB.guardarListaDesa(listaDesa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("desarrolladores"); /* Nombre del html que se muestra */
		mav.addObject("desa", new Desarrollador());

		return mav;
	}
	
	@GetMapping("/desarrolladores/{filtro}")
	public ModelAndView desarrollador(@PathVariable String filtro) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("desarrollador");
		Desarrollador desa = desaDAO.findById(filtro).get();
		mav.addObject("desa", desa);
		
		
		return mav;
	}
	
	

	@PostMapping("/desarrolladores/anadir")
	public String desaAnadir(@ModelAttribute Desarrollador desa, @RequestParam(value = "file") MultipartFile file) {

		try {
			desa.setImg(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		desaDAO.save(desa);

		return "redirect:/desarrolladores";
	}

	@GetMapping("/desarrolladores/borrar/{id}")
	public String desaBorrar(@PathVariable String id) {

		desaDAO.deleteById(id);

		return "redirect:/desarrolladores";
	}

	@PostMapping("/desarrolladores/editar")
	public String desarrolladoresEditar(@ModelAttribute("dist") Desarrollador desa,
										@RequestParam(value = "file") MultipartFile file) {

		try {
			if (file.isEmpty()) {
				desa.setImg(desaDAO.findById(desa.getId()).get().getImg());
			} else {
				desa.setImg(file.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		desaDAO.save(desa);

		return "redirect:/desarrolladores";
	}

	@GetMapping("/desarrolladores/editar/{id}")
	public String desarrolladoresEditar(@PathVariable String id, @ModelAttribute Desarrollador desa) {

		desa = desaDAO.findById(id).get();

		return "/desarrolladores/editar";
	}
	
}
