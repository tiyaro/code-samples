# Code samples for calling Tiyaro API 
This repository includes full working samples for invoking the inference APIs supported by Tiyaro in multiple languages. The samples are all self explanatory and are organized by the various ML **model types**.

## Model Types
We have curated the open source community, model hubs and popular AI vendors to make sure you have access to the best AI models. Each model in the Tiyaro universe has its own API URL. 

For example
| Model  | API URL |
| ------------- | ------------- |
| imagenet/inception_resnet_v2  | https://api.tiyaro.ai/v1/ent/google/5/imagenet/inception_resnet_v2/classification  |
| gpt2  | https://api.tiyaro.ai/v1/ent/huggingface/1/gpt2  |
| gcpvision-object-detection | https://api.tiyaro.ai/v1/ent/gcp/1/gcpvision-object-detection |

\
Models are further classified into different **types** based on their function.
For example, all the following models are 'image-classification' type models
| Model | Model Type|
| ------------- | ------------- |
| mobilenet_v3_large | image-classification |
| rekognition-image-classification| image-classification|
| efficientnet/b5 |image-classification |

\
You can find the type of any model from the model card in Tiyaro.
For example here is the model card for the model **mobilenet_v3_large**

![Screenshot from 2022-05-12 21-41-12](https://user-images.githubusercontent.com/88600050/168212643-ad1c3aee-60da-40ed-acf9-89eeb6a10d4d.png)

And here is where to find the type (image-classification) of the model
![Screenshot from 2022-05-12 21-45-26](https://user-images.githubusercontent.com/88600050/168212889-bb4aaa74-35ac-4d91-bc1c-e1d492afdf9e.png)

## Finding the sample for your model
You can search for models in the Tiyaro console using the model explorer. After you find the model of your choice, you can locate the 'model type' of that models as shown above. This repo is organized by 'model types'. So once you have your model type you navigate to the right subdir of the langauage of your choice to find the sample that you can adapt for your use. 

For example, the python sample that you can adapt for **mobilenet_v3_large** model is in [python/image-classification](python/image-classification)

Similarly, the python sample that you can adapt for **gpt2** which is of model type **text-generation** is in [python/text-generation](python/text-generation)


## Inputs for the API
Where applicable an API can accept input data in multiple formats. For instance all vision APIs can accept either a image URL or the image in base64 format. For such APIs you will find separated samples for each type of input. The code to convert an image or audio file into base64 format is included in the samples.

For example, the python sample for using a local image and coverting it into a base64 image for image-classification is in [python/image-classification/local_image_input.py](python/image-classification/local_image_input.py)

Similarly the sample for using an image URL is in [python/image-classification/url_input.py](python/image-classification/url_input.py)



