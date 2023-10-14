package icca.generator;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws IOException {


        System.out.println("Hello world!");

        final var path = "/home/omarbarra/Downloads/autos.pdf";


        getTextDD(path);

        /*
        int numberOfPages = getNumberOfPages(path);
        System.out.println(numberOfPages);


        PDDocumentInformation info = getInfo(path);
        System.out.println(info.getTitle());

        var passwordRequired = isPasswordRequired(path);
        System.out.println(passwordRequired);

        var text = getText(path);
        System.out.println(text);
        *
         */


    }


    public static String getText(final String pdfFile) throws IOException {
        File file = new File(pdfFile);
        PDDocument document = Loader.loadPDF(file);
        var pdfTextStripper = new PDFTextStripper();
        var text = pdfTextStripper.getText(document);
        document.close();
        return text;
    }

    public static String getTextDD(final String pdfFile) throws RuntimeException, IOException {
        File file = new File(pdfFile);
        PDDocument document = Loader.loadPDF(file);
        PDPage page = document.getPage(177);
        InputStream contents = page.getContents();
        String text = new String(contents.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(page.getCropBox().toString());


        /**
         document.getPages().forEach(p -> {
         try {
         InputStream contents = p.getContents();
         String text = new String(contents.readAllBytes(), StandardCharsets.UTF_8);
         System.out.println(text);
         } catch (IOException e) {
         throw new RuntimeException(e);
         }

         });
         document.close();
         */
        return "text";
    }

    public static boolean isPasswordRequired(final String pdfFile) throws IOException {
        File file = new File(pdfFile);
        PDDocument document = Loader.loadPDF(file);
        boolean isEncrypted = document.isEncrypted();
        document.close();
        return isEncrypted;
    }

    public static int getNumberOfPages(final String pdfFile) throws IOException {
        File file = new File(pdfFile);
        PDDocument document = Loader.loadPDF(file);
        int pages = document.getNumberOfPages();
        document.close();
        return pages;
    }


    public static PDDocumentInformation getInfo(final String pdfFile) throws IOException {
        File file = new File(pdfFile);
        PDDocument document = Loader.loadPDF(file);
        PDDocumentInformation info = document.getDocumentInformation();
        document.close();
        return info;
    }
}