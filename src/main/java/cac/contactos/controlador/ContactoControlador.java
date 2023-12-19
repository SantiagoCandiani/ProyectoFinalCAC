package cac.contactos.controlador;

import cac.contactos.modelo.Contacto;
import cac.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoControlador {

    private static final Logger logguer = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo) {
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logguer.info(contacto.toString()));
        modelo.put("contactos", contactos);
        return "index"; //index.hmtl
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar"; //agregar.hmtl
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForm") Contacto contacto) {
        logguer.info("Contacto a agregar:" + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirijo al controlador el path "/"
    }


}//Class
