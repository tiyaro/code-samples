package ai.tiyaro.samples.gcp_classes.gcp_detect_web;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;

/** Sample code to run google detect web with image url as input */
public class URLInput {

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/gcp/1/gcp_detect_web";
    // Input image
    var imgURL = "https://public-model-demo.s3.us-west-2.amazonaws.com/ford_slogan.jpg";

    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("imageRef", Collections.singletonMap("URL", imgURL));

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
    System.out.println(String.format("Response Body: \n%s", new JSONObject(response.body()).toString(4)));
    assert 200 == response.statusCode();
  }

  public static void main(String[] args) throws Exception {
    URLInput demo = new URLInput();
    demo.infer();
  }
}
