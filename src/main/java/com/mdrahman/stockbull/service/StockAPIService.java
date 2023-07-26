package com.mdrahman.stockbull.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdrahman.stockbull.model.Stock;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StockAPIService {

    private final String apiKey = "pk_a25f6c418b13402a9c20fef3f1fc09d5"; // Replace with your IEX Cloud API key
    private final String iexCloudEndpoint = "https://cloud.iexapis.com/stable";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Stock> getAllStocks() throws IOException {
        String function = "search";
        String keywords = "YOUR_KEYWORD"; // Replace with keywords to search stocks
        String url = iexCloudEndpoint + "/stock/" + function + "?token=" + apiKey + "&q=" + keywords;

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        List<Stock> stocks = new ArrayList<>();

        if (entity != null) {
            JsonNode root = objectMapper.readTree(entity.getContent());

            Iterator<JsonNode> matchesIterator = root.elements();

            while (matchesIterator.hasNext()) {
                JsonNode matchNode = matchesIterator.next();
                String symbol = matchNode.get("symbol").asText();
                String name = matchNode.get("name").asText();

                // Fetch stock price using your existing getStockPrice() method (not shown here)
                double price = getStockPrice(symbol);

                Stock stock = new Stock();
                stock.setStockSymbol(symbol);
                stock.setStockName(name);
                stock.setStockPrice(price);

                stocks.add(stock);
            }
        }

        return stocks;
    }

    public double getStockPrice(String symbol) throws IOException {
        String function = "quote";
        String url = iexCloudEndpoint + "/stock/" + symbol + "/" + function + "?token=" + apiKey;

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            JsonNode root = objectMapper.readTree(entity.getContent());

            // Check if the response contains the "latestPrice" field
            if (root.has("latestPrice")) {
                double price = root.get("latestPrice").asDouble();
                return price;
            }
        }

        return 0.0; // Return 0.0 if there's an error or no data found
    }

    public String getStockName(String symbol) throws IOException {
        String url = iexCloudEndpoint + "/stock/" + symbol + "/company?token=" + apiKey;

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            JsonNode root = objectMapper.readTree(entity.getContent());

            // Check if the response contains the "companyName" field
            if (root.has("companyName")) {
                String stockName = root.get("companyName").asText();
                return stockName;
            }
        }

        return ""; // Return empty string if there's an error or no data found
    }
}
