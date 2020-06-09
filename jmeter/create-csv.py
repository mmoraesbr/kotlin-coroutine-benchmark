#!/usr/bin/python3

import requests
import json

api_url = "http://compras.dados.gov.br/licitacoes/v1/licitacoes.json"

def get_all():
    response = requests.get(api_url)
    if (response.status_code != 200):
        print("ERROR:{}".format(response))
    return response.json()["_embedded"]["licitacoes"]

licitacoes = get_all()

for licitacao in licitacoes:
    id = licitacao["identificador"]
    print(id)