/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author tss
 */
@Path("/files")
public class FileResources {

    private static final String LOCATION = "/home/tss/Scrivania/";

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(FormDataMultiPart form) {
        try {
            FormDataBodyPart filePart = form.getField("file");
            ContentDisposition contentDispositionHeader =  filePart.getContentDisposition();

            InputStream fileInputStream = filePart.getValueAs(InputStream.class);

            Files.copy(fileInputStream,
                    Paths.get(LOCATION + contentDispositionHeader.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
            
            String output = "File saved to server..";
            return Response.ok(output).build();
        } catch (IOException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    
    @POST
    @Path("/upload1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile1(
            @FormDataParam("file") InputStream is,
            @FormDataParam("file") ContentDisposition contentDispositionHeader) {
        try {
            Files.copy(is,
                    Paths.get(LOCATION + contentDispositionHeader.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
           
            return Response.ok().build();
        } catch (IOException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    @GET
    @Path("/download/{fname}")
    public Response download(@PathParam("fname") String fname){
        try{
            Response.ResponseBuilder rb = 
                    Response.ok(Files.readAllBytes(Paths.get(LOCATION + fname)));
            rb.type(MediaType.APPLICATION_OCTET_STREAM);
            rb.header("Content-Disposition", "attachment; filename=\"" + fname + "\"");
            return rb.build();
        }catch(IOException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
    }
}
