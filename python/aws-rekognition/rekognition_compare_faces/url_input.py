#!/usr/bin/env python

"""
Sample code to run compare faces api with image url as input
"""

import requests
import os
import sys


def infer():
    # Get the API key for invoking Tiyaro API
    apiKey = os.getenv("TIYARO_API_KEY")
    if apiKey is None:
        print("Please set TIYARO_API_KEY environment variable. You can generate your API key from here - https://console.tiyaro.ai/apikeys")
        sys.exit(1)

    # API endpoint
    url = "https://api.tiyaro.ai/v1/ent/aws/1/rekognition_compare_faces"

    # Input image
    img1URL = "http://starschanges.com/wp-content/uploads/2015/10/Tom-Brady_1-847x1024.jpg"
    img2URL = "http://starschanges.com/wp-content/uploads/2015/10/Tom-Brady_9.jpg"

    payload = {
        "SourceImage": {"URL": img1URL},
        "TargetImage": {"URL": img2URL}
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
