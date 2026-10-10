package Ressources;

import entities.Etudiant;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtudiantRessource {
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
    @Path("{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant){
        if(etudiantBusiness.deleteEtudiant(identifiant)){
            return Response.status(Response.Status.OK)
                    .entity("Deleted successfully")
                    .build();
        }return Response.status(Response.Status.NOT_FOUND)
                .entity("Error")
                .build();


    }



}
