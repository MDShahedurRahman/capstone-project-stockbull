package com.mdrahman.stockbull.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockAPIService {

    private final String apiKey = "8AOVKXSV55DL81TN";
    private final String alphaVantageEndpoint = "https://www.alphavantage.co/query";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private JsonNode makeApiCall(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return objectMapper.readTree(entity.getContent());
        }

        return null;
    }

    public double getStockPrice(String symbol) throws IOException {
        String url = alphaVantageEndpoint + "?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
        JsonNode root = makeApiCall(url);

        if (root != null) {
            JsonNode globalQuoteNode = root.get("Global Quote");
            if (globalQuoteNode != null) {
                String priceStr = globalQuoteNode.get("05. price").asText();
                return Double.parseDouble(priceStr);
            }
        }

        return 0.0; // Return 0.0 if there's an error or no data found
    }

    public String getStockName(String symbol) throws IOException {
        String url = alphaVantageEndpoint + "?function=SYMBOL_SEARCH&keywords=" + symbol + "&apikey=" + apiKey;
        JsonNode root = makeApiCall(url);

        if (root != null) {
            JsonNode bestMatchesNode = root.get("bestMatches");
            if (bestMatchesNode.isArray() && bestMatchesNode.size() > 0) {
                JsonNode firstMatch = bestMatchesNode.get(0);
                String companyName = firstMatch.get("2. name").asText();
                return companyName;
            }
        }

        return ""; // Return empty string if there's an error or no data found
    }

    public String getStockSymbol(String symbol) throws IOException {
        String url = alphaVantageEndpoint + "?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
        JsonNode root = makeApiCall(url);

        if (root != null) {
            JsonNode globalQuoteNode = root.get("Global Quote");
            if (globalQuoteNode != null) {
                String stockSymbol = globalQuoteNode.get("01. symbol").asText();
                return stockSymbol;
            }
        }

        return ""; // Return empty string if there's an error or no data found
    }
}
