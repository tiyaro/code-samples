#!/usr/bin/env python

"""
Sample code to run NLP Zero shot classification
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/bart-large-mnli"

    # Input
    inputString = """Ju Lim could have opened Ssong’s Hotdog in Annandale or Centreville or somewhere 
        else in Fairfax County, where there are plenty of folks familiar with the South Korean 
        street food that dominates his menu. But Lim wasn’t interested in catering exclusively 
        to the Korean American community. He was convinced that his corn dogs — simply known 
        as hot dogs in South Korea — would appeal to a much wider audience."""
    labels = ["travel", "cooking", "dancing", "sports", "places"]

    payload = {
        "input": inputString,
        "multiLabel": True,
        "labels": labels
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
