import pickle

from flask import Flask, request

app = Flask(__name__)


@app.route('/v1/api/model', methods=['POST'])
def index():
    loaded_model = pickle.load(open("finalized_model.pkl", 'rb'))
    text = request.json['reviewDescription']
    if text:
        result = loaded_model(text)
        model_prediction = result[0]['label']
        return str(model_prediction)
    else:
        return str()


if __name__ == '__main__':
    app.run(port=5000, debug=True)
