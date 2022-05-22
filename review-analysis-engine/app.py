from flask import Flask, request
import pickle
from transformers import pipeline
import json

app = Flask(__name__)

model_prediction="model output"


@app.route('/v1/api/model', methods=['GET', 'POST'])
def index():
    if request.method == 'GET':
        global  model_prediction
        return str(model_prediction)
    if request.method == 'POST':
        loaded_model = pickle.load(open("finalized_model.pkl", 'rb'))
        text = "Very Bad weather!"
        #instead of text, we should have request.get.form od formata kade se pisuva komentar/review za odredena kniga!
        result = loaded_model(text)
        # print(result[0]['label'])
        # print(result[0]['score'])
        value = {
            "sentiment": result[0]['label']
        }
        json_result = json.dumps(value)
        model_prediction=json_result
        return str(json_result)

if __name__ == '__main__':
    app.run(debug=True)