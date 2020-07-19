package ec.edu.ups.rest;

import ec.edu.ups.ejb.*;
import ec.edu.ups.entidad.*;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/pedido/")
public class PedidoResource {

    @EJB
    PedidoFacade pedidoFacade;

    @EJB
    PersonaFacade personaFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    FacturaCabeceraFacade facturaCabeceraFacade;
    @EJB
    FacturaDetalleFacade facturaDetalleFacade;

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createPedido(@FormParam("personaId") String id, @FormParam("productos") String productos, @FormParam("cantidades") String cantidades) throws Exception{
        GregorianCalendar currentDate = getCurrentDate();
        Persona persona = personaFacade.find(id);
        Pedido pedido = new Pedido("ENVIADO", currentDate, persona, null);
        pedidoFacade.create(pedido);
        pedido = pedidoFacade.getUltimoPedido(persona, currentDate);

        String[] productosArray = productos.split(";");
        String[] cantidadesArray = cantidades.split(";");

        FacturaCabecera facturaCabecera = new FacturaCabecera(currentDate, 'N', 0, 0, 0, 0, null, persona, pedido);
        facturaCabeceraFacade.create(facturaCabecera);

        List<FacturaDetalle> detalleList = new ArrayList<>();

        for (int i = 0; i < productosArray.length; i++) {
            Producto producto = productoFacade.find(Integer.parseInt(productosArray[i]));
            producto.setStock(producto.getStock() - Integer.parseInt(cantidadesArray[i]));
            FacturaDetalle facturaDetalle = new FacturaDetalle(Integer.parseInt(cantidadesArray[i]),
                    (Integer.parseInt(cantidadesArray[i])*producto.getPrecioVenta()), facturaCabecera, producto);
            detalleList.add(facturaDetalle);
        }
        double [] totalSubtotalIva = getTotalSubtotalIva(detalleList);
        facturaCabecera.setListaFacturasDetalles(detalleList);
        facturaCabecera.setTotal(totalSubtotalIva[0]);
        facturaCabecera.setSubtotal(totalSubtotalIva[1]);
        facturaCabecera.setIva_total(totalSubtotalIva[2]);

        pedido.setFacturaCabecera(facturaCabecera);
        pedidoFacade.edit(pedido);

        return Response.ok("OK!" + id + " <--> " + productos)
                .header("Access-Control-Allow-Origins", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .build();
    }

    private double subtotal, iva_total;
    private double[] getTotalSubtotalIva(List<FacturaDetalle> detalleList){
        subtotal = 0;
        iva_total = 0;
        detalleList.forEach(facturaDetalle -> {
            Producto producto = facturaDetalle.getProducto();
            double precioVenta = producto.getPrecioVenta()*facturaDetalle.getCantidad();
            subtotal += (producto.getIva() == 'S') ? (precioVenta-precioVenta*0.12) : precioVenta;
            iva_total += (producto.getIva() == 'S') ? precioVenta*0.12 : 0;
        });
        return new double[]{subtotal+iva_total, subtotal, iva_total};
    }

    private GregorianCalendar getCurrentDate() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(){

                Jsonb jsonb = JsonbBuilder.create();
                List<Pedido> pedidoList = pedidoFacade.findAll();

                System.out.println("Estos son todos los pedidos" + pedidoList);
                System.out.println(jsonb.toJson(pedidoList));

                pedidoList = Pedido.serializePedidos(pedidoList);
                pedidoList.forEach(System.out::println);


                return Response.ok(jsonb.toJson(pedidoList))
                        .header("Access-Control-Allow-Origins", "*")
                        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                        .build();



                //return Response.status(Response.Status.BAD_REQUEST).entity("Error al obtener los pedidos ->" + e.getMessage()).build();




    }

}
