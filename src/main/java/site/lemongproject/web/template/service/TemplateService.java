package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Template;

public interface TemplateService {
    Template loadInsertPage(int memberNo);

    int saveTemplate(int userNo);
}
