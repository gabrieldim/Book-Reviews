import pickle
# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    loaded_model = pickle.load(open("finalized_model.pkl", 'rb'))
    text = "Very Bad weather!"
    result = loaded_model(text)
    print(result)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
