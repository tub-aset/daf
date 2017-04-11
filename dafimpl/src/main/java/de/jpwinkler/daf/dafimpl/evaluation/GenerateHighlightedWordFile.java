package de.jpwinkler.daf.dafimpl.evaluation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleImpl;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.SentenceMarkupRange;
import javafx.scene.paint.Color;

public class GenerateHighlightedWordFile {

    private static final Color backgroundColor = Color.WHITE;
    public static final List<Color> DEFAULT_COLORS = Arrays.asList(Color.LIGHTCORAL, Color.LIMEGREEN, Color.ORANGE, Color.YELLOW, Color.VIOLET, Color.CORNFLOWERBLUE);

    private static String getColor(final SentenceMarkupRange range) {
        double r = 0;
        double g = 0;
        double b = 0;

        final int maxI = range.getStrongestIndex();
        final double max = range.getStrongest();

        r = DEFAULT_COLORS.get(maxI).getRed();
        g = DEFAULT_COLORS.get(maxI).getGreen();
        b = DEFAULT_COLORS.get(maxI).getBlue();

        r = backgroundColor.getRed() + (r - backgroundColor.getRed()) * max;
        g = backgroundColor.getGreen() + (g - backgroundColor.getGreen()) * max;
        b = backgroundColor.getBlue() + (b - backgroundColor.getBlue()) * max;

        final Color color = new Color(r, g, b, 1.0);
        return color.toString().substring(2, 8);
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {

        final ConvNetClassifier convNetClassifier = new ConvNetClassifier(ClassifierContext.getInstance());

        final List<String> examples = IOUtils.readLines(new FileInputStream("temp/case-study-data.txt"));

        final XWPFDocument doc = new XWPFDocument();

        for (final String example : examples) {

            final ConvNetClassificationResult result = convNetClassifier.classify(new ExampleImpl(example));
            final XWPFParagraph paragraph = doc.createParagraph();

            // if (result.getSentenceMarkup().getRanges().get(0).getStart() > 0) {
            // addRun(paragraph, example.substring(0, result.getSentenceMarkup().getRanges().get(0).getStart() - 1), null);
            // }

            int lastEnd = 0;

            for (final SentenceMarkupRange r : result.getSentenceMarkup().getRanges()) {
                if (lastEnd < r.getStart()) {
                    addRun(paragraph, example.substring(lastEnd, r.getStart()), null);
                }
                lastEnd = r.getStart() + r.getLength();
                addRun(paragraph, example.substring(r.getStart(), lastEnd), getColor(r));
            }

            if (lastEnd < example.length()) {
                addRun(paragraph, example.substring(lastEnd), null);
            }

            /*
            for (int i = 0; i < words.size(); i++) {
                final String word = words.get(i);
                final XWPFRun r1 = paragraph.createRun();
                r1.setText(word);
                r1.getCTR().addNewRPr().addNewShd().setFill(getColorAt(iv, i));

                final XWPFRun r2 = paragraph.createRun();
                r2.setText(" ");
            }
             */
        }

        doc.write(new FileOutputStream("case-study-highlighted.docx"));

    }

    private static void addRun(final XWPFParagraph paragraph, final String text, final String color) {
        final XWPFRun r1 = paragraph.createRun();
        r1.setText(text);
        if (color != null) {
            r1.getCTR().addNewRPr().addNewShd().setFill(color);
        }
    }

}
