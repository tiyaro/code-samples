#!/usr/bin/env python

"""
Sample code to run compare faces api with local image as input
"""

import requests
import os
import sys
import base64


def imageToBase64(srcPath):
    with open(srcPath, 'rb') as image:
        b64Img = base64.b64encode(image.read()).decode('utf-8')
    return b64Img


def infer():
    # Get the API key for invoking Tiyaro API
    apiKey = os.getenv("TIYARO_API_KEY")
    if apiKey is None:
        print("Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys")
        sys.exit(1)

    # API endpoint
    url = "https://api.tiyaro.ai/v1/ent/aws/1/rekognition_compare_faces"

    img1Path = "../../../testdata/tom1.jpg"
    img1B64 = imageToBase64(img1Path)

    img2Path = "../../../testdata/tom2.jpg"
    img2B64 = imageToBase64(img2Path)

    payload = {
        "SourceImage": {"Bytes": img1B64},
        "TargetImage": {"Bytes": img2B64}
    }

    headers = {
        "accept": "*/*",
        "content-type": "application/json",
        "authorization": f"Bearer {apiKey}"
    }

    response = requests.request("POST", url, json=payload, headers=headers)
    # Check for errors
    response.raise_for_status()

    # Inference response
    print(response.text)


if __name__ == "__main__":
    infer()
