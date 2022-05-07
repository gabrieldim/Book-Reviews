from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route('/predict', methods=['POST'])
def predict():
    json = request.json()
    return jsonify({'result':  list()})


if __name__ == '__main__':
    app.run(port=8081)