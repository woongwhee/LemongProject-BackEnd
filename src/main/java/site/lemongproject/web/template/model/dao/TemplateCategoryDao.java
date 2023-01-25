package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.TemplateCategory;

import java.util.List;

public interface TemplateCategoryDao {
    List<TemplateCategory> findList();
}
