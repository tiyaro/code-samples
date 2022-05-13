#!/usr/bin/env python

"""
Sample code to run NLP Named entity recognition (NER)
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/xlm-roberta-large-finetuned-conll03-english"

    # Input string
    inputString = """Brady just launched his performance and tech-focused golf 
            collection on Tuesday, and continued working with college 
            athletes as the line will feature Texas golfer Cole Hammer and 
            Wake Forest golfer Michael Brennan in the campaign."""

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
