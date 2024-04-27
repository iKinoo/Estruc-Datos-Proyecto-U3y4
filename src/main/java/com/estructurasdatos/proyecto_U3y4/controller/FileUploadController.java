package com.estructurasdatos.proyecto_U3y4.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    
    private Tokenizer tokenizer; // Asegúrate de importar o definir correctamente la clase Tokenizer

    @PostMapping("/proyecto3y4") // Ruta a la que Dropzone.js enviará la solicitud POST
    @ResponseBody
    public String handleFileUpload(MultipartFile file, Model model) {
        tokenizer = new Tokenizer(); // Esto debe estar correctamente definido en tu proyecto
        ArrayList<String> palabras = tokenizer.tokenize(file); // Esto debe estar correctamente definido en tu proyecto
        model.addAttribute("palabras", palabras); // Esto solo es válido si cambias el tipo de retorno del método

        // Aquí puedes procesar el archivo subido, por ejemplo, guardarlo en el sistema de archivos
        // o almacenarlo en la base de datos
        // En este ejemplo, simplemente imprimimos el nombre del archivo
        System.out.println("Archivo subido: " + file.getOriginalFilename());
        return "Archivo subido: " + file.getOriginalFilename(); // Puedes cambiar este tipo de retorno a String o ModelAndView
    }
}
