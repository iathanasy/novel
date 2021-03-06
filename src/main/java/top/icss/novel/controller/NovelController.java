package top.icss.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.icss.novel.entity.Novel;
import top.icss.novel.entity.NovelChapter;
import top.icss.novel.service.NovelChapterService;
import top.icss.novel.service.NovelService;
import top.icss.novel.utils.PageHelper;

import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 15:05
 * @since 1.0.0
 */
@Controller
@RequestMapping
public class NovelController {

    @Autowired
    private NovelService novelService;
    @Autowired
    private NovelChapterService novelChapterService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       @RequestParam(value = "cateId", defaultValue = "") String cateId,
                       @RequestParam(value = "search", defaultValue = "") String search) {

        PageHelper novels = novelService.list(pageNum, pageSize, cateId, search);
        model.addAttribute("novels", novels);

        model.addAttribute("cateId", cateId);
        model.addAttribute("search", search);

        return "novel/list";
    }

    @RequestMapping("/book_{novId}")
    public String book(Model model, @PathVariable("novId") String novId) {
        Novel novel = novelService.book(novId);
        List<NovelChapter> chapters = novelChapterService.list(novId);

        model.addAttribute("novel", novel);
        model.addAttribute("chapters", chapters);

        return "novel/book";
    }

    @RequestMapping("/book_{novId}/{chapterId}")
    public String details(Model model, @PathVariable("novId") String novId,
                          @PathVariable("chapterId") String chapterId) {

        Novel novel = novelService.book(novId);
        NovelChapter chapter = novelChapterService.details(novId, chapterId);

        NovelChapter pre = novelChapterService.pre(novId, chapterId);
        NovelChapter next = novelChapterService.next(novId, chapterId);

        model.addAttribute("novel", novel);
        model.addAttribute("chapter", chapter);

        model.addAttribute("pre", pre);
        model.addAttribute("next", next);

        return "novel/details";
    }

}
