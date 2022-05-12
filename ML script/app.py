from flask import Flask, request, jsonify
import joblib

app = Flask(__name__)


@app.route('/predict', methods=['POST'])
def predict(comment):

    json = request.json()
    d = { 'NEGATIVE': 0 , 'POSITIVE': 1 }
    
    model = joblib.load( 'model.pkl' )
    pred = model.predict( comment )
    result = d[ pred[ 'label' ] ]

    return jsonify({'result':  result})


if __name__ == '__main__':
    app.run(port=8081)