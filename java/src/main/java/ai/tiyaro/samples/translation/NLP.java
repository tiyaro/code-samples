package ai.tiyaro.samples.translation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

/** Sample code to run NLP Translation */
public class NLP {

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/huggingface/1/Helsinki-NLP/opus-mt-en-de";
    // Input
    var inputString = "Trees live a long time, so it’s important to pick a local species that won’t struggle to survive." +
        " If you aren’t sure which species grow locally, spend some time researching trees that are native to your area.";

    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("input", inputString);

    var request =
        HttpRequest.newBuilder(URI.create(url))
            .setHeader("accept", "*/*")
            .setHeader("content-type", "application/json")
            .setHeader("authorization", String.format("Bearer %s", apiKey))
            .POST(BodyPublishers.ofString(payload.toString()))
            .build();

    System.out.println(String.format("Making inference request to: %s", url));
    var response = client.send(request, BodyHandlers.ofString());
    System.out.println(String.format("Response Status: %s", response.statusCode()));
    System.out.println(
        String.format("Response Body: \n%s", new JSONObject(response.body()).toString(3)));
    assert 200 == response.statusCode();
  }

  public static void main(String[] args) throws Exception {
    NLP demo = new NLP();
    demo.infer();
  }
}
