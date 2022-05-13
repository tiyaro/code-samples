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

## Build
From your IDE, import [pom.xml](pom.xml) as maven project.
```
mvn install
```
Then, open any [package](./src/main/java/ai/tiyaro/samples/) and run classes to see outputs.