package org.example.commands;

import org.example.ImageItem;
import org.example.ImageRepo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command to generate and open an HTML report using FreeMarker.
 * Expected argument: outputFilename.
 */
public class ReportCommand implements Command {
    private final String outputFilename;

    public ReportCommand(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    @Override
    public void execute(ImageRepo repo) throws CommandException {
        Configuration cfg = new Configuration(new Version("2.3.31"));
        try {
            // Set template directory (assumes templates are in src/main/resources/templates)
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
            Template template = cfg.getTemplate("report.ftl");
            Map<String, Object> data = new HashMap<>();
            List<ImageItem> images = repo.getImages();
            data.put("images", images);
            Writer fileWriter = new FileWriter(new File(outputFilename));
            template.process(data, fileWriter);
            fileWriter.close();
            System.out.println("Report generated: " + outputFilename);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(outputFilename));
            }
        } catch (IOException | TemplateException e) {
            throw new CommandException("Error generating report: " + e.getMessage());
        }
    }
}