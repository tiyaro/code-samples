#!/usr/bin/env python

"""
Sample code to run speech recognition on a local audio file
"""

import requests
import os
import sys
import base64


def audioToBase64(srcPath):
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/wav2vec2-base-960h"

    # Convert binary image to base64
    audioPath = "../../testdata/speech-audio.flac"
    b64Audio = audioToBase64(audioPath)

    payload = {"Bytes": b64Audio}
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
