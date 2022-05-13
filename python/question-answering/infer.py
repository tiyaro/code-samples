#!/usr/bin/env python

"""
Sample code to run NLP Question Answering API
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
    url = "https://api.tiyaro.ai/v1/ent/huggingface/1/distilbert-base-cased-distilled-squad"

    # Input string
    context = """Formed in November 1990 by the equal merger of Sky Television 
                and British Satellite Broadcasting, BSkyB became the UK's largest 
                digital subscription television company. Following BSkyB's 2014 
                acquisition of Sky Italia and a majority 90.04% interest in 
                Sky Deutschland in November 2014, its holding company 
                British Sky Broadcasting Group plc changed its name to Sky plc. 
                The United Kingdom operations also changed the company name from 
                British Sky Broadcasting Limited to Sky UK Limited, still trading as Sky."""
    question = "What is the name of the United Kingdom operation for BSkyB?"

    payload = {"question": question,
               "context": context}
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
