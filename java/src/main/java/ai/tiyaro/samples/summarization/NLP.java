package ai.tiyaro.samples.summarization;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

// Sample code to run NLP Text Summarization
public class NLP {

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/bart-large-cnn";
    // Input
    var inputString = "The tower is 324 metres (1,063 ft) tall, about the same height as an \n" +
        "81-storey building, and the tallest structure in Paris. Its base is square, \n" +
        "measuring 125 metres (410 ft) on each side. During its construction, \n" +
        "the Eiffel Tower surpassed the Washington Monument to become the tallest \n" +
        "man-made structure in the world, a title it held for 41 years until the Chrysler \n" +
        "Building in New York City was finished in 1930. It was the first structure to \n" +
        "reach a height of 300 metres. Due to the addition of a broadcasting aerial at \n" +
        "the top of the tower in 1957, it is now taller than the Chrysler Building \n" +
        "by 5.2 metres (17 ft). Excluding transmitters, the Eiffel Tower is the second \n" +
        "tallest free-standing structure in France after the Millau Viaduct.";
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
