package map.web;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import map.service.MapService;

@Controller
@RequestMapping("/map")
public class ApimapController {

    MapService mapService;

    ServletContext sc;

    
    public ApimapController(
            MapService mapService,
            ServletContext sc) {
        this.mapService = mapService;
        this.sc = sc;
    }

    @GetMapping("list")
    public void list() throws Exception {
    	
    }

    
    @PostMapping("add")
    public String add(
            MapService mapService) 
                    throws Exception{

        return "redirect:list";
    }

}

