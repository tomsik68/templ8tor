package sk.tomsik68.templator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class TemplatorApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }
        if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help") || args[0].equalsIgnoreCase("?")) {
            printHelp();
            return;
        }

        Path templateStore = Paths.get(System.getProperty("user.home")).resolve(".templ8");
        if(!Files.exists(templateStore)) {
            try {
                Files.createDirectories(templateStore);
            } catch(IOException e) {
                System.err.println("Failed to create template storage directory.");
                e.printStackTrace();
                System.exit(1);
            }
        }
        Path template = templateStore.resolve(args[0].concat(".templ"));

        if (!Files.exists(template)) {
            System.err.println("Template at " + template.toAbsolutePath().toString() + " not found.");
            System.exit(1);
        }

        Map<String, Object> variables = ArgumentReparser.reparse(args);
        try {
            TemplateProcessor processor = new TemplateProcessor(templateStore);
            Writer output = new OutputStreamWriter(System.out);
            processor.process(template.getFileName().toString(), variables, output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.err.println("Templ8tor - template tool");
        System.err.println("Usage: java -jar templ8tor.jar [template] [arguments]");
        System.err.println("\tTemplates are saved to [user_home]/.templ8");
        System.err.println("Arguments are then passed to the template model.");
        System.err.println("After processing is done, template is printed to stdout.");
    }
}
