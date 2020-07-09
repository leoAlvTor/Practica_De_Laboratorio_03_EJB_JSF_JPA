package ec.edu.ups.rest;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Persona;
import ec.edu.ups.entidad.Usuario;

import javax.ejb.EJB;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/usuario/")
public class UsuarioResource {

    @EJB
    PersonaFacade personaFacade;

    @POST
    @Path("/login/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUsuario(@FormParam("cedula") String cedula, @FormParam("password") String password) throws IOException {

        if (personaFacade.verificarUsuario(cedula, password)){
            System.out.println("USUARIO EXISTENTE");
            return Response.ok("USUARIO EXISTENTE").build();
        }else{
            System.out.println("NO EXISTE EL USUARIO!");
            return Response.ok("NO EXISTE EL USUARIO O ESTA ANULADO!").build();
        }
    }

    @POST
    @Path("/register/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido,
                             @FormParam("direccion") String direccion, @FormParam("telefono") String telefono,
                             @FormParam("correo") String correo, @FormParam("password") String password){
        Persona persona = new Persona(cedula, nombre, apellido, direccion, telefono, correo, password, 'F');
        try {
            personaFacade.create(persona);
            return Response.ok("Se ha creado el usuario correctamente").build();
        }catch (Exception e){
        return Response.status(Response.Status.BAD_REQUEST).entity("Ha ocurrido un error!" + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre,
                             @FormParam("apellido") String apellido, @FormParam("direccion") String direccion, @FormParam("telefono") String telefono,
                             @FormParam("correo") String correo, @FormParam("password") String password){

        try{
            Persona persona = personaFacade.find(cedula);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDireccion(direccion);
            persona.setTelefono(telefono);
            persona.setCorreo(correo);
            persona.setPassword(password);
            personaFacade.edit(persona);
            return Response.ok("Se ha actualizado el usuario correctamente!").build();
        }catch (Exception e){
            return Response.status(418).entity("No se ha podido actualizar el usuario" + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/anular/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response anular(@FormParam("cedula") String cedula){
        try{
            Persona persona = personaFacade.find(cedula);
            persona.setAnulado('T');
            personaFacade.edit(persona);
            return Response.status(Response.Status.BAD_REQUEST).entity("Se ha anulado el usuario correctamente!").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al anular la cuenta del usuario" + e.getCause()).build();
        }
    }


}
