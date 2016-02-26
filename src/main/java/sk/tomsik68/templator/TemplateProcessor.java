package sk.tomsik68.templator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Map;

public class TemplateProcessor {
    private final Configuration cfg;

    TemplateProcessor(Path templatesFolder) throws IOException {
        this.cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(templatesFolder.toFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void process(String templateName, Map<String, Object> variables, Writer writer) throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateName);

        template.process(variables, writer);
    }
}
