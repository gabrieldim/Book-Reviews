from transformers import pipeline
import pickle

if __name__ == '__main__':
    pipe = pipeline("text-classification")
    text = "There are negative things about this book"
    review = pipe(text)
    filename = 'finalized_model.pkl'
    pickle.dump(pipe, open(filename, 'wb'))
    print(review)