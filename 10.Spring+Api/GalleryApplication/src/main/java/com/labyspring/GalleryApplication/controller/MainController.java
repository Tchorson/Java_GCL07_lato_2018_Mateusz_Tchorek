package com.labyspring.GalleryApplication.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.labyspring.GalleryApplication.properties.GlobalProperties;
import com.labyspring.GalleryApplication.model.ListOfImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.InitializingBean;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.core.io.ClassPathResource;


@RestController
public class MainController implements InitializingBean{

    ListOfImages li = new ListOfImages();

    @Autowired
    private GlobalProperties globalProperties;

    @Override
    public void afterPropertiesSet() {
        li.setPath(globalProperties.getGalleryPath());
        li.putImagesToMap();
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String homePage(ModelMap model){
        return "home";
    }
    @RequestMapping(value = "/refresh")
    public String refresh(){
        li.putImagesToMap();
        return "a dupa nie dziala";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/gallery/pictures")
    public String getAllPictures() {
        return li.getAllImages();
    }
    @RequestMapping(method = RequestMethod.GET,value = "/gallery/pictures/{index}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getPicture(@PathVariable int index, HttpServletResponse response) throws IOException {
        if(li.checkIndex(index)==true) {
            ClassPathResource imgFile = new ClassPathResource( "static\\assets\\picture"+ (index + 1) + ".jpg"); //li.getImagePath(index)
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        }
        else{
            ClassPathResource imgFile = new ClassPathResource("static\\assets\\picture0.jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        }
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/gallery/pictures/{index}")
    public String deletePicture(@PathVariable int index) {
        return li.deleteImage(index);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/upload")
    public String uploadPicture(){
        return "aa";
    }
}