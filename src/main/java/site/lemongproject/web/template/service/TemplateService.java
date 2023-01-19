package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.vo.Template;

public interface TemplateService {
    Template loadInsertPage(int memberNo);

    int upload(int userNo);
}
