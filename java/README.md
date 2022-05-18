---
title: "Java Code samples for calling Tiyaro Inference API"
---

# Java Code samples for calling Tiyaro Inference API

The samples are all self explanatory and are organized by the various ML **model types** [here](./src/main/java/ai/tiyaro/samples/).

## Requirements
### Java
```
Java 11

Maven
```

### API Key
Please set TIYARO_API_KEY environment variable. You can generate your API key from [here](https://console.tiyaro.ai/apikeys).

When running from IDE, make sure to restart after you set the environment variable in your shell's profile.

## Build & Run
From your IDE, import [pom.xml](pom.xml) as maven project.
```
mvn install
```
Then, open any [package](./src/main/java/ai/tiyaro/samples/) and run classes within the IDE to see outputs.

Alternately, you can also use the below command:
```
mvn compile exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass="ai.tiyaro.samples.audio_classification.LocalAudioInput"
```
and modify value of `-Dexec.mainClass` to try out different model types. Ex:
```
mvn compile exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass="ai.tiyaro.samples.recognition_classes.recognition_detect_moderation_labels.URLInput"
```