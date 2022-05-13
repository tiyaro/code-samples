#!/usr/bin/env python

"""
Sample code to run NLP Text Classification
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/finiteautomata/beto-sentiment-analysis"

    # Input string
    inputString = """I bought this for my husband who plays the piano. 
            He is having a wonderful time playing these old hymns. 
            The music  is at times hard to read because we think the book was 
            published for singing from more than playing from.  Great purchase though!"""

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
