package ai.tiyaro.samples.automatic_speech_recognition;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;

// Sample code to run speech recognition on a local audio file
public class LocalAudioInput {

  public String audioToBase64(String filePath) throws Exception {
    File file = new File(ClassLoader.getSystemResource(filePath).toURI());
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
    var url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/wav2vec2-base-960h";
    // Convert binary audio to base64
    var audioPath = "testdata/speech-audio.flac";
    var b64Audio = audioToBase64(audioPath);
    var client = HttpClient.newHttpClient();

    var payload = new JSONObject();
    payload.put("Bytes", b64Audio);

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
    LocalAudioInput demo = new LocalAudioInput();
    demo.infer();
  }
}
