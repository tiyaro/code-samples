#!/usr/bin/env python

"""
Sample code to run NLP Conversational API
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/microsoft/DialoGPT-small"

    # Input string
    oldInputs = ["Can I use this filter in Australian 220V power?"]
    oldOutputs = [
        "No it is a 110 unit you would have to purchase a 110 converter elseware to use this"]
    input = "Where can I buy this converter and is it safe to use it with this filter?"

    payload = {"oldInputs": oldInputs,
               "oldOutputs": oldOutputs,
               "input": input}
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
