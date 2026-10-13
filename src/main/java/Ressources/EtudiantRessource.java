package Ressources;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.RescaleOp;
import java.util.List;


@Path("/etudiants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtudiantRessource {
    private static final OptionBusiness optionBusiness = new OptionBusiness();
    EtudiantBusiness etudiantBusiness=new EtudiantBusiness();
    @POST
    public Response addEtudiant(Etudiant etudiant){
        if(etudiantBusiness.addEtudiant(etudiant)){
            return Response.status(Response.Status.CREATED)
                    .entity("Student added successfully")
                    .build();
        }return Response.status(Response.Status.NOT_FOUND)
                .entity("Error").build();

    }
    @GET
    public List<Etudiant> getAllEtudiant(){
        return etudiantBusiness.getAllEtudiants();
    }
    @GET
    @Path("{identifiant}")
    public Response getEtudiantByIdentifiant(@PathParam("identifiant")String identifiant){
        Etudiant etudiant=etudiantBusiness.getEtudiantByIdentifiant(identifiant);
        if (etudiant!=null){
            return Response.status(Response.Status.OK)
                    .entity(etudiantBusiness.getEtudiantByIdentifiant(identifiant))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(etudiantBusiness.getEtudiantByIdentifiant(identifiant))
                .build();

    }
    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant){
        if(etudiantBusiness.deleteEtudiant(identifiant)){
            return Response.status(Response.Status.OK)
                    .entity("Deleted successfully")
                    .build();
        }return Response.status(Response.Status.NOT_FOUND)
                .entity("Error")
                .build();


    }
    @PUT
    @Path("/{identifiant}")
    public Response updateEtudiant(@PathParam("identifiant")String identifiant, Etudiant etudiant){
        if(etudiantBusiness.updateEtudiant(identifiant,etudiant)){
            return Response.status(Response.Status.OK)
                    .entity("Student updated successfully")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR")
                .build();
    }

    @GET
    @Path("/option/{codeOption}")
    public Response getEtudiantsByOption(@PathParam("codeOption") int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Option not found").build();
        }
        return Response.ok(etudiantBusiness.getEtudiantsByOption(option)).build();
    }





}
