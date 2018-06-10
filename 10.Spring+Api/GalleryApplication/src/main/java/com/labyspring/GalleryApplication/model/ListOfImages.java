package com.labyspring.GalleryApplication.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.nio.file.attribute.BasicFileAttributes;
import com.google.gson.Gson;
import com.labyspring.GalleryApplication.properties.GlobalProperties;

public class ListOfImages implements Serializable{

    private HashMap<Integer, Image> collectionOfImages = new HashMap<>();
    private Image im;
    private BasicFileAttributes myFileAttribiutes = null; // zwraca info o zdjeciu, specjalna biblioteka
    private int indexCounter = 0;
    private Path p = null;
    private File tmpFile = null;
    private BufferedImage tmpImage = null;
    private String tmpName;
    private String tmpResolution;
    private long tmpSize;
    private FileTime tmpCreated;
    Gson gson = new Gson(); //konwersja kolekcje do jsona
    String s, ss; //s sciezka


    public void setPath(String galPath){
        this.s = galPath;
    }

    public void putImagesToMap() {
            if(collectionOfImages == null) //
                return;

            for(int i = 1; i <= 9; i++) {
                try {
                    ss = s;
                    ss += i + ".jpg";
                    tmpFile = new File(ss);
                    tmpImage = ImageIO.read(tmpFile);
                    p = tmpFile.toPath();
                    myFileAttribiutes = Files.readAttributes(p, BasicFileAttributes.class);
                    tmpSize = myFileAttribiutes.size();
                    tmpResolution = tmpImage.getWidth() + "x" + tmpImage.getHeight();
                    tmpName = tmpFile.getName();
                    tmpCreated = myFileAttribiutes.creationTime();
                    im = new Image(indexCounter, tmpName, tmpResolution, tmpSize, tmpCreated);
                    collectionOfImages.put(indexCounter,im);
                    indexCounter++;
                    ss="";
                } catch (IOException e) {
                    System.out.println("Error with reading image file");
                    e.printStackTrace();
                }
            }
    }

    public String addImage(){
        return "aa";
    }

    public String deleteImage(int indx){

        if(collectionOfImages.containsKey(indx)!=true) {
            return Response.result(false);
        }
            collectionOfImages.remove(indx);
            return Response.result(true);

    }

    public String getImage(int indx){
        String jsonResult = null;
        jsonResult = gson.toJson(collectionOfImages.get(indx));
        return jsonResult;
    }

    public boolean checkIndex(int indx){
        return collectionOfImages.containsKey(indx);
    }

    //public String getImagePath(int indx){
       //     return "static\\assets\\picture";
    //}
    public String getAllImages(){
        String jsonResult = null;
        jsonResult = gson.toJson(collectionOfImages);
        return jsonResult;
    }

}

