# Ponto de entrada para o Vercel (deteta o Flask neste ficheiro e encaminha todos os pedidos para a app).
# A API esta em api/index.py. Localmente continua a ser: flask --app api/index run --port 8000 --debug
from api.index import app  # noqa: F401
