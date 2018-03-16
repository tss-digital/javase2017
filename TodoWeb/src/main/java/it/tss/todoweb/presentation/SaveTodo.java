/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.presentation;

import it.tss.todoweb.business.DateUtils;
import it.tss.todoweb.business.ToDo;
import it.tss.todoweb.business.ToDoStore;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tss
 */
@WebServlet(name = "SaveTodo", urlPatterns = {"/save"})
public class SaveTodo extends HttpServlet {

    @Inject
    ToDoStore store;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            System.out.println("id:" + id);
            String elimina = req.getParameter("elimina");
            if (elimina != null) {
                processElimina(id);
            } else {
                processCreateUpdate(req, id);
            }
            resp.sendRedirect("index.jsp");
        } catch (IOException | ParseException ex) {
            Logger.getLogger(SaveTodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processElimina(String id) {
        store.delete(Long.parseLong(id));
    }

    private void processCreateUpdate(HttpServletRequest req, String id) throws ParseException {
        ToDo tosave = (id == null || "".equals(id.trim())) ? new ToDo()
                : store.find(Long.parseLong(id));
        tosave.setTitolo(req.getParameter("titolo"));
        tosave.setTesto(req.getParameter("testo"));
        tosave.setIl(DateUtils.dateFromString(req.getParameter("il")));
        store.save(tosave);
    }

}
