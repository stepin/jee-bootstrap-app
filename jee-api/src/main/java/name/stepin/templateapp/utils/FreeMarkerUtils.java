package name.stepin.templateapp.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@Stateless
public class FreeMarkerUtils {

    @Context
    private ServletContext context;

    public String getViewTemplateResult(String templateName, Map<String, Object> params) {
        String filename = "/WEB-INF/views/" + templateName;
        filename = context.getRealPath(filename);
        return getTemplateResult(filename, params);
    }

    private String getTemplateResult(String filename, Map<String, Object> data) {
        try {
            Template template = getTemplate(filename);

            try (Writer writer = new StringWriter()) {
                template.process(data, writer);
                return writer.toString();
            }
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private Template getTemplate(String filename) throws IOException {
        Configuration freemarkerConfiguration = new Configuration();
        File file = new File(filename);
        freemarkerConfiguration.setDirectoryForTemplateLoading(file.getParentFile());
        return freemarkerConfiguration.getTemplate(file.getName());
    }
}
