package com.example.board.post;

import com.example.board.post.dto.PostRequest;
import com.example.board.post.dto.PostResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService service;
    public PostController(PostService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", service.list());
        return "post-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new PostRequest());
        model.addAttribute("mode", "create");
        return "post-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("post") PostRequest req, BindingResult br) {
        if (br.hasErrors()) return "post-form";
        Long id = service.create(req);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("post", new PostResponse(service.get(id)));
        return "post-view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Post p = service.get(id);
        PostRequest dto = new PostRequest();
        dto.setTitle(p.getTitle());
        dto.setAuthor(p.getAuthor());
        dto.setContent(p.getContent());
        model.addAttribute("post", dto);
        model.addAttribute("mode", "edit");
        model.addAttribute("id", id);
        return "post-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("post") PostRequest req, BindingResult br) {
        if (br.hasErrors()) return "post-form";
        service.update(id, req);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/posts";
    }
}
