import pandas as pd
import numpy as np
import os
import joblib

from sklearn.pipeline import Pipeline
from sklearn.model_selection import cross_val_score, KFold
from sklearn.linear_model import RidgeCV, LassoCV, ElasticNetCV
from sklearn.ensemble import GradientBoostingRegressor, StackingRegressor
from production_system.src.data.data_pipeline import build_full_pipeline

class ModelTrainer:

    def __init__(self, data_path):
        self.data_path = data_path
        self.output_path = "house_model.joblib"

    def load_data(self):
        train = pd.read_csv(os.path.join(self.data_path, "train.csv"))

        y = np.log1p(train["SalePrice"])
        X = train.drop(["SalePrice", "Id"], axis=1)

        return X, y

    def train(self):

        X, y = self.load_data()
        preprocessing = build_full_pipeline()

        # -------- Level 0 Models --------
        base_models = [
            ("gbr", GradientBoostingRegressor(
                n_estimators=3000,
                learning_rate=0.01,
                max_depth=4,
                min_samples_leaf=15,
                min_samples_split=10,
                loss="huber",
                random_state=42
            )),
            ("lasso", LassoCV(
                alphas=[0.0001, 0.0005, 0.001, 0.01],
                cv=5,
                random_state=42
            )),
            ("enet", ElasticNetCV(
                alphas=[0.0001, 0.0005, 0.001, 0.01],
                l1_ratio=[0.1, 0.5, 0.9],
                cv=5
            ))
        ]

        meta_model = RidgeCV(alphas=[0.1, 1.0, 10.0])

        stacking_model = StackingRegressor(
            estimators=base_models,
            final_estimator=meta_model,
            cv=5,
            n_jobs=-1
        )

        full_model = Pipeline([
            ("prep", preprocessing),
            ("model", stacking_model)
        ])

        # -------- Cross Validation --------
        print("Running 5-Fold Cross Validation...")
        kf = KFold(n_splits=5, shuffle=True, random_state=42)

        scores = cross_val_score(
            full_model,
            X,
            y,
            scoring="neg_mean_squared_error",
            cv=kf,
            n_jobs=-1
        )

        rmse = np.sqrt(-scores)

        print("-" * 40)
        print(f"Average Log-RMSE: {rmse.mean():.5f}")
        print(f"Std Dev: {rmse.std():.5f}")
        print("-" * 40)

        # -------- Train Final Model --------
        print("Training final model on full dataset...")
        full_model.fit(X, y)

        joblib.dump(full_model, self.output_path)
        print("Model saved as house_model.joblib")


if __name__ == "__main__":
    trainer = ModelTrainer(data_path="production_system/data/raw")
    trainer.train()
