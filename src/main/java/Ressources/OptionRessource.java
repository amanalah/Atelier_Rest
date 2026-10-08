package Ressources;


import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
public class OptionRessource {
    OptionBusiness optionBusiness= new OptionBusiness();
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getOption(){
        return optionBusiness.getListeOptions();
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionByCode(@PathParam("code")int code){
        return Response.status(Response.Status.CREATED)
                        .entity(optionBusiness.getOptionByCode(code))
                        .build();
                //optionBusiness.getOptionByCode(code);
    }
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option option){
        if(optionBusiness.addOption(option)){
            return Response.status(Response.Status.CREATED)
                    .entity("added successfully")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Not found").build();
    }
    @PUT
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam( "code")int code, Option option){
        if(optionBusiness.updateOption(code,option))
            return Response.status(Response.Status.OK)
                    .entity("Modified Succefully").build();
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Update Failed")
                .build();
    }
    /*@DELETE
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOption(@QueryParam("code") int code){
        if(optionBusiness.deleteOption(code))
            return Response.status(Response.Status.OK)
                    .entity("Deleted Succefully").build();
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Delete Failed")
                .build();

    }*/
    //ou
    @DELETE
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOption(@PathParam("code") int code){
        if(optionBusiness.deleteOption(code))
            return Response.status(Response.Status.OK)
                    .entity("Deleted Succefully").build();
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Delete Failed")
                .build();

    }

}
