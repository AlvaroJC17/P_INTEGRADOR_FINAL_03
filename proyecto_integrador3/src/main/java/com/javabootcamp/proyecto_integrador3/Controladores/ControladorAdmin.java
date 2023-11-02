package com.javabootcamp.proyecto_integrador3.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javabootcamp.proyecto_integrador3.Entidades.Usuario;
import com.javabootcamp.proyecto_integrador3.Enums.Rol;
import com.javabootcamp.proyecto_integrador3.Repositorios.RepositorioUsuario;
import com.javabootcamp.proyecto_integrador3.Servicios.ServicioUsuario;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {

	@Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("/dashboard")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String lista(ModelMap modelo) {

        List<Usuario> usuario = new ArrayList<>();
        usuario = repositorioUsuario.buscarUsuarios();

        modelo.put("roles", Rol.values());
        modelo.addAttribute("usuarios", usuario);
        return "panel.html";
    }

    @GetMapping("/baja/{id}")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String bajaUsuario(@PathVariable String id, ModelMap modelo) {
    	servicioUsuario.darBaja(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/alta/{id}")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String altaUsuario(@PathVariable String id, ModelMap modelo) {
    	servicioUsuario.darAlta(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/buscar")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String buscarPorDni(String dni, ModelMap model) {
        Usuario usuario = repositorioUsuario.buscarPorDni(dni);
        model.addAttribute("usuarios", usuario);
        model.put("roles", Rol.values());
        return "panel.html";
    }

    @GetMapping("/rol/{dni}")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String cambiarRolUsuario(@PathVariable String dni, Rol rol, ModelMap model) {
    	servicioUsuario.modificarRol(rol, dni);
        return "redirect:/admin/dashboard";
    }
}
