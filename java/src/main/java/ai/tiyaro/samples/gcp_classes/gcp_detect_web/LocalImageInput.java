package ai.tiyaro.samples.gcp_classes.gcp_detect_web;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.Collections;

// Sample code to run google detect web with local image as input
public class LocalImageInput {

  public String imageToBase64(String filePath) throws Exception {
    File file = new File(LocalImageInput.class.getClassLoader().getResource(filePath).toURI());
    var fileContent = FileUtils.readFileToByteArray(file);
    String encodedBytesString = Base64.getEncoder().encodeToString(fileContent);
    return encodedBytesString;
  }

  public void infer() throws Exception {
    // Get the API key for invoking Tiyaro API
    var apiKey = System.getenv("TIYARO_API_KEY");
    if (null == apiKey || apiKey.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys");
    }
    // API endpoint
    var url = "https://api.tiyaro.ai/v1/ent/gcp/1/gcp_detect_web";
    // Convert binary image to base64
    var imgPath = "testdata/ford_slogan.jpg";
    var b64Img = imageToBase64(imgPath);
    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("imageRef", Collections.singletonMap("Bytes", b64Img));

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
    LocalImageInput demo = new LocalImageInput();
    demo.infer();
  }
}
