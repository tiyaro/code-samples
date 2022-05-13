package ai.tiyaro.samples.question_answering;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

// Sample code to run NLP Question Answering
public class NLP {

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/huggingface/1/distilbert-base-cased-distilled-squad";
    // Input
    var context =
        "Formed in November 1990 by the equal merger of Sky Television \n"
            + "and British Satellite Broadcasting, BSkyB became the UK's largest \n"
            + "digital subscription television company. Following BSkyB's 2014 \n"
            + "acquisition of Sky Italia and a majority 90.04% interest in \n"
            + "Sky Deutschland in November 2014, its holding company \n"
            + "British Sky Broadcasting Group plc changed its name to Sky plc. \n"
            + "The United Kingdom operations also changed the company name from \n"
            + "British Sky Broadcasting Limited to Sky UK Limited, still trading as Sky.";

    var question = "What is the name of the United Kingdom operation for BSkyB?";
    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("question", question);
    payload.put("context", context);

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
