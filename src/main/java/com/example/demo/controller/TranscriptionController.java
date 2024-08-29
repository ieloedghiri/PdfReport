package com.example.demo.controller;

import com.example.demo.model.TranscriptionRequest;
import com.example.demo.service.AssemblyAIService;
import com.example.demo.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TranscriptionController {

    @Autowired
    private AssemblyAIService assemblyAIService;

    @Autowired
    private PDFService pdfService;

    @PostMapping("/transcribe")
    public ResponseEntity<byte[]> transcribeAndGeneratePdf(@RequestBody TranscriptionRequest transcriptionRequest) throws IOException {
        String summary = assemblyAIService.getTranscriptionSummary(transcriptionRequest.getAudioUrl());
        byte[] pdfBytes = pdfService.generatePdfReport(transcriptionRequest.getIntervenantName(), summary);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
