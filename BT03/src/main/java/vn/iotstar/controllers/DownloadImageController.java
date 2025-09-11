package vn.iotstar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import vn.iotstar.utils.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/images") // Truy cập: /image?fname=abc.png
public class DownloadImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String fileName = req.getParameter("fname");
        if (fileName == null || fileName.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name is required");
            return;
        }

        File file = new File(Constant.DIR, fileName);
        
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        resp.setContentType("image/jpeg"); 
        resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

        try (FileInputStream fis = new FileInputStream(file)) {
            IOUtils.copy(fis, resp.getOutputStream());
        }
    }
}
