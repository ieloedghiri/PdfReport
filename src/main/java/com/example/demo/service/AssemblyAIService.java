package com.example.demo.service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;
import org.springframework.stereotype.Service;

@Service
public class AssemblyAIService {

    private final AssemblyAI client;

    public AssemblyAIService() {
        this.client = AssemblyAI.builder()
                .apiKey("86b4508024b9474ea8c5b172206608b2")
                .build();
    }

    public String getTranscriptionSummary(String audioUrl) {
        var params = TranscriptOptionalParams.builder()
                .summarization(true)
                .summaryModel(SummaryModel.INFORMATIVE)
                .summaryType(SummaryType.PARAGRAPH)
                .build();

        Transcript transcript = client.transcripts().transcribe(audioUrl, params);

        return transcript.getSummary().orElse("Summary not available");
    }
}
