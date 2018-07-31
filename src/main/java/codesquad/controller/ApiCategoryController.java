package codesquad.controller;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    private static final Logger log = LoggerFactory.getLogger(ApiAdminController.class);

    @Resource
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Category> list() {
        return categoryService.getParentList();
    }
}
