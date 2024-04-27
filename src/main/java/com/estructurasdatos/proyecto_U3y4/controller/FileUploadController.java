package com.estructurasdatos.proyecto_U3y4.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @PostMapping("/proyecto3y4") // Ruta a la que Dropzone.js enviará la solicitud POST
    @ResponseBody
    public String handleFileUpload(MultipartFile file) {
        
        // Aquí puedes procesar el archivo subido, por ejemplo, guardarlo en el sistema de archivos
        // o almacenarlo en la base de datos
        // En este ejemplo, simplemente imprimimos el nombre del archivo
        System.out.println("Archivo subido: " + file.getOriginalFilename());
        return "Archivo subido: " + file.getOriginalFilename();
    }
}
