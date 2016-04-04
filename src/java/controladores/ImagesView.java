/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Postgrago
 */
@ManagedBean
public class ImagesView {

    /**
     * Creates a new instance of ImagesView
     */
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            images.add("img" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
    
}
