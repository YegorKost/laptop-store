package com.yegor.controllers;

import com.yegor.entity.LaptopEntity;
import com.yegor.service.LaptopService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by YegorKost on 23.03.2017.
 */
@Controller
public class StoreController {

    private static Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    private LaptopService<LaptopEntity> laptopService;

    @Autowired
    public StoreController(LaptopService<LaptopEntity> laptopService) {
        this.laptopService = laptopService;
    }

    @RequestMapping("/storePage")
    public String anotherPage(Model model) {
        List<LaptopEntity> list = laptopService.getAllLaptops();
        Collections.sort(list);
        model.addAttribute("laptops", list);
        return "storePage";
    }

    @RequestMapping("/addItem")
    public ModelAndView addItem(HttpServletRequest request) {
        LaptopEntity laptopEntity = laptopService.getLaptop(request.getParameter("model"));
        int items = Integer.parseInt(request.getParameter("items"));
        laptopEntity.setAmount(laptopEntity.getAmount() + items);
        LOGGER.info("addItem - update \'LaptopEntity\'");
        laptopService.updateLaptop(laptopEntity);
        return new ModelAndView("redirect:/storePage");
    }

    @RequestMapping("/delItem")
    public ModelAndView delItem(HttpServletRequest request) {
        LaptopEntity laptopEntity = laptopService.getLaptop(request.getParameter("model"));
        int items = Integer.parseInt(request.getParameter("items"));
        if ((laptopEntity.getAmount() - items) < 0) {
            laptopEntity.setAmount(0);
        } else {
            laptopEntity.setAmount(laptopEntity.getAmount() - items);
        }
        LOGGER.info("delItem - update \'LaptopEntity\'");
        laptopService.updateLaptop(laptopEntity);
        return new ModelAndView("redirect:/storePage");
    }

    @RequestMapping("/buyLaptop")
    public ModelAndView buyLaptop(HttpServletRequest request) {
        return new ModelAndView("redirect:/delItem")
                .addObject("model", request.getParameter("model"))
                .addObject("items", request.getParameter("items"));
    }

    @RequestMapping("/removeLaptop")
    public ModelAndView removeLaptop(HttpServletRequest request) {
        LaptopEntity laptopEntity = laptopService.getLaptop(request.getParameter("model"));
        LOGGER.info("Remove \'LaptopEntity\'");
        laptopService.deleteLaptop(laptopEntity);
        return new ModelAndView("redirect:/storePage");
    }

}
