#!/usr/bin/env python

"""
Sample code to run NLP Text Summarization
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/facebook/bart-large-cnn"

    # Input string
    inputString = """The tower is 324 metres (1,063 ft) tall, about the same height as an 
        81-storey building, and the tallest structure in Paris. Its base is square, 
        measuring 125 metres (410 ft) on each side. During its construction, 
        the Eiffel Tower surpassed the Washington Monument to become the tallest 
        man-made structure in the world, a title it held for 41 years until the Chrysler 
        Building in New York City was finished in 1930. It was the first structure to 
        reach a height of 300 metres. Due to the addition of a broadcasting aerial at 
        the top of the tower in 1957, it is now taller than the Chrysler Building 
        by 5.2 metres (17 ft). Excluding transmitters, the Eiffel Tower is the second 
        tallest free-standing structure in France after the Millau Viaduct."""

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
