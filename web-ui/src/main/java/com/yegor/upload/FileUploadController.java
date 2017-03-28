package com.yegor.upload;

import com.yegor.entity.LaptopEntity;
import com.yegor.service.LaptopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by YegorKost on 25.03.2017.
 */
@Controller
public class FileUploadController {

    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private final LaptopService<LaptopEntity> laptopService;

    @Autowired
    public FileUploadController(LaptopService<LaptopEntity> laptopService) {
        this.laptopService = laptopService;
    }

    @RequestMapping(value = "/laptopAction", method = RequestMethod.POST)
    public ModelAndView uploadImage(@RequestParam("file") MultipartFile file,
                                    HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("add") != null) {
            String pathToImage = saveImage(file, request, response);
            String make = request.getParameter("make");
            String model =request.getParameter("model");
            Float screen = Float.parseFloat(request.getParameter("screen"));
            String processor = request.getParameter("processor");
            Integer memory = Integer.parseInt(request.getParameter("memory"));
            Integer amount = Integer.parseInt(request.getParameter("amount"));
            Double price = Double.parseDouble(request.getParameter("price"));

            LaptopEntity laptopEntity = new LaptopEntity();
            LOGGER.info("Set image for \'" + laptopEntity.getModel() + "\'");
            laptopEntity.setImage(pathToImage);
            laptopEntity.setMake(make);
            laptopEntity.setModel(model);
            laptopEntity.setScreen(screen);
            laptopEntity.setProcessor(processor);
            laptopEntity.setMemory(memory);
            laptopEntity.setAmount(amount);
            laptopEntity.setPrice(price);

            LOGGER.info("Add new \'LaptopEntity\'");
            laptopService.addLaptop(laptopEntity);
        } else if (request.getParameter("uploadImage") != null) {
            String pathToImage = saveImage(file, request, response);
            LOGGER.info("Get \'LaptopEntity\'");
            LaptopEntity laptopEntity = laptopService.getLaptop(request.getParameter("model"));
            LOGGER.info("Set image for \'" + laptopEntity.getModel() + "\'");
            laptopEntity.setImage(pathToImage);
            laptopService.updateLaptop(laptopEntity);
            LOGGER.info("Update \'LaptopEntity\'");
        }

        return new ModelAndView("redirect:/storePage");
    }

    private String saveImage(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        ServletContext context = request.getSession().getServletContext();
        String path = context.getInitParameter("uploadLocation");
        File imageDir = new File(path);

        if (!imageDir.exists()) {
            if (imageDir.mkdirs()) {
                LOGGER.info("Create directory for images - " + imageDir.getPath());
            }
        }

        String fileName =  file.getOriginalFilename();
        File imageFile = new File(imageDir.getPath() + File.separator + fileName);
        LOGGER.info("Write file - " + imageFile.getPath());
        try (FileOutputStream fOut = new FileOutputStream(imageFile);
             BufferedOutputStream buff = new BufferedOutputStream(fOut)){

            byte[] bytes = file.getBytes();
            buff.write(bytes);
            buff.flush();

        } catch (IOException e) {
            LOGGER.info("IOException. Redirect to \'/storePage\'");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/storePage");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
        }
        return imageFile.getPath();
    }

}
