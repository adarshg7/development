import pandas as pd
import numpy as np
import joblib


class BatchPredictor:

    def __init__(self, model_path, test_path):
        self.model_path = model_path
        self.test_path = test_path

    def predict(self):

        print("Loading model...")
        model = joblib.load(self.model_path)

        print("Loading test data...")
        test = pd.read_csv(self.test_path)

        ids = test['Id']
        X_test = test.drop(['Id'], axis=1)

        print("Generating predictions...")
        log_preds = model.predict(X_test)

        final_preds = np.expm1(log_preds)

        result = pd.DataFrame({
            'Id': ids,
            'SalePrice': final_preds
        })

        output_path = "production_system/data/predictions.csv"
        result.to_csv(output_path, index=False)

        print(f"Predictions saved to {output_path}")
        print(result.head())


if __name__ == "__main__":
    predictor = BatchPredictor(
        model_path="production_system/src/models/house_model.joblib",
        test_path="production_system/data/raw/test.csv"
    )

    predictor.predict()
