package top.icss.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.icss.novel.entity.Maici;
import top.icss.novel.service.MaiciService;
import top.icss.novel.utils.PageHelper;

/**
 * @author cd.wang
 * @create 2020-10-19 15:05
 * @since 1.0.0
 */
@Controller
@RequestMapping("maici")
public class MaciController {

    @Autowired
    private MaiciService maiciService;


    @RequestMapping({"", "/"})
    public String index() {
        return "redirect:/maici/list";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                       @RequestParam(value = "search", defaultValue = "") String search) {

        PageHelper maicis = maiciService.list(pageNum, pageSize, search);

        model.addAttribute("maicis", maicis);
        model.addAttribute("search", search);

        return "maici/list";
    }

    @RequestMapping("/details_{id}")
    public String details(Model model, @PathVariable("id") String id) {
        Maici details = maiciService.details(id);
        model.addAttribute("maici", details);
        return "maici/details";
    }


}
