package com.example.demo.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "sk-qHZDZlLcnwJRDKKP7mgbSibl3hTfyaXKiHiZLUrNHBT3BlbkFJNRSa5nZg11cfCvAnaOI5VomgYjJzTZOO6JiuCAizoA";

    public String summarizeText(String transcription) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + API_KEY);
            post.setHeader("Content-Type", "application/json");

            String json = "{"
                    + "\"model\": \"gpt-3.5-turbo\","
                    + "\"messages\": ["
                    + "{"
                    + "\"role\": \"system\","
                    + "\"content\": \"summerize and turn this speech transcription into a report in French\""
                    + "},"
                    + "{"
                    + "\"role\": \"user\","
                    + "\"content\": \"" + transcription + "\""
                    + "}"
                    + "],"
                    + "\"max_tokens\": 1500"
                    + "}";

            post.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);

                // Parse the response to extract the summary
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray choices = jsonResponse.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                    return message.getString("content").trim();
                }

                return "No summary available.";
            }
        }
    }
}
