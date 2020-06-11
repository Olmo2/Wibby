package com.olmo.wibby.servicios;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olmo.wibby.desarrollador.Desarrollador;
import com.olmo.wibby.distribuidor.Distribuidor;
import com.olmo.wibby.juego.Juego;
import com.olmo.wibby.plataforma.Plataforma;

@Service
public class FicherosDB {

//	@Autowired
//	 private JuegoDAO juegoDAO;

	public void guardarListaJuegos(List<Juego> lista) throws IOException {
		byte[] file;
		Path ruta;
		OutputStream os;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getImg() != null) {
				file = lista.get(i).getImg();

				// Path ruta = Paths.get("D:\\Descargas\\esmeralda.jpg");
			//	ruta = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + lista.get(i).getRef() + ".jpg");
			//ruta = Paths.get("img\\" + lista.get(i).getRef() + ".jpg");
			ruta = Paths.get(System.getProperty("catalina.base") + "\\webapps\\Wibby\\WEB-INF\\classes\\static\\img\\" + lista.get(i).getRef() + ".jpg");
				System.out.println(ruta);
				try {
					os = Files.newOutputStream(ruta);
					os.write(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("no hay imagen para ti");
			}

		}
	}

	public void guardarListaPlataformas(List<Plataforma> lista) throws IOException {
		byte[] file;
		Path ruta;
		OutputStream os;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getImg() != null) {
				file = lista.get(i).getImg();

				// Path ruta = Paths.get("D:\\Descargas\\esmeralda.jpg");
				//ruta = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + lista.get(i).getId() + ".jpg");
				ruta = Paths.get(System.getProperty("catalina.base") + "\\webapps\\Wibby\\WEB-INF\\classes\\static\\img\\" + lista.get(i).getId() + ".jpg");
				try {
					os = Files.newOutputStream(ruta);
					os.write(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("no hay imagen para ti");
			}

		}
	}

	public void guardarListaDist(List<Distribuidor> lista) throws IOException {
		byte[] file;
		Path ruta;
		OutputStream os;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getImg() != null) {
				file = lista.get(i).getImg();

				// Path ruta = Paths.get("D:\\Descargas\\esmeralda.jpg");
				//ruta = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\"	+ lista.get(i).getId() + ".jpg");
				ruta = Paths.get(System.getProperty("catalina.base") + "\\webapps\\Wibby\\WEB-INF\\classes\\static\\img\\" + lista.get(i).getId() + ".jpg");
				
				try {
					os = Files.newOutputStream(ruta);
					os.write(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("no hay pana para ti");
			}

		}
	}

	public void guardarListaDesa(List<Desarrollador> lista) throws IOException {
		byte[] file;
		Path ruta;
		OutputStream os;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getImg() != null) {
				file = lista.get(i).getImg();

				// Path ruta = Paths.get("D:\\Descargas\\esmeralda.jpg");
//				ruta = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + lista.get(i).getId() + ".jpg");
				ruta = Paths.get(System.getProperty("catalina.base") + "\\webapps\\Wibby\\WEB-INF\\classes\\static\\img\\" + lista.get(i).getId() + ".jpg");
				
				try {
					os = Files.newOutputStream(ruta);
					os.write(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("no hay pana para ti");
			}

		}
	}
}
