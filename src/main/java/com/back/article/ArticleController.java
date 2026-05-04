package com.back.article;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleService.findAll();
        model.addAttribute(articleList);
        return "article_list";
    }

    @GetMapping("/article/create")
    public String create() {
        return "article_create";
    }

    @PostMapping("/article/create")
    public String createArticle(@PathParam("title") String title, @PathParam("content") String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        articleService.save(article);
        return "redirect:/article/list";
    }
}
