package ai.tiyaro.samples.zero_shot_classification;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.json.JSONObject;

/** Sample code to run NLP Zero shot classification */
public class NLP {

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/bart-large-mnli";
    // Input
    var inputString =
        "Ju Lim could have opened Ssong’s Hotdog in Annandale or Centreville or somewhere else in Fairfax County, where" +
            " there are plenty of folks familiar with the South Korean street food that dominates his menu. " +
            "But Lim wasn’t interested in catering exclusively to the Korean American community. He was convinced that " +
            "his corn dogs — simply known as hot dogs in South Korea — would appeal to a much wider audience.";
    var labels = List.of("travel", "cooking", "dancing", "sports", "places");

    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("input", inputString);
    payload.put("multiLabel", true);
    payload.put("labels", labels);

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
        String.format("Response Body: \n%s", new JSONObject(response.body()).toString(4)));
    assert 200 == response.statusCode();
  }

  public static void main(String[] args) throws Exception {
    NLP demo = new NLP();
    demo.infer();
  }
}
