package com.example.demo.service;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA;
import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD;
import static com.itextpdf.layout.properties.TextAlignment.CENTER;
import static com.itextpdf.layout.properties.VerticalAlignment.MIDDLE;

@Service
public class PDFService {

    public byte[] generatePdfReport(String intervenantName, String summary) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add vertical line borders on each side
        document.setBorderLeft(new SolidBorder(ColorConstants.BLACK, 1));
        document.setBorderRight(new SolidBorder(ColorConstants.BLACK, 1));

        // Add title with padding
        Paragraph title = new Paragraph("Rapport d'intervention")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20); // Padding after the title
        document.add(title);

        // Add padding between paragraphs
        Paragraph intervenantParagraph = new Paragraph("Intervenant: " + intervenantName)
                .setFontSize(14)
                .setMarginBottom(20); // Padding between this and the next paragraph
        document.add(intervenantParagraph);

        Paragraph summaryParagraph = new Paragraph("Résumé:" + '\n' + summary)
                .setFontSize(14)
                .setMarginBottom(20); // Padding at the end
        document.add(summaryParagraph);

        document.close();
        return baos.toByteArray();
    }
}