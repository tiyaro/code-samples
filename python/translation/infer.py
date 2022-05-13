#!/usr/bin/env python

"""
Sample code to run NLP Translation
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

    # API endpoint - English to German translation model
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/Helsinki-NLP/opus-mt-en-de"

    # Input string
    inputString = """Trees live a long time, so it’s important to pick a local species 
                     that won’t struggle to survive. If you aren’t sure which species grow locally, 
                     spend some time researching trees that are native to your area."""

    payload = {"input": inputString}
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
