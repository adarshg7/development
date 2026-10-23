import numpy as np
import pandas as pd
from scipy.stats import skew

from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer, make_column_selector
from sklearn.preprocessing import OneHotEncoder, RobustScaler
from sklearn.impute import SimpleImputer


# =====================================
# 1️⃣ Missing Value Handler
# =====================================
class MissingValueHandler(BaseEstimator, TransformerMixin):

    def fit(self, X, y=None):
        X = X.copy()
        self.lot_frontage_median = X["LotFrontage"].median()
        return self

    def transform(self, X):
        X = X.copy()

        if "LotFrontage" in X.columns:
            X["LotFrontage"] = X["LotFrontage"].fillna(self.lot_frontage_median)

        none_cols = [
            'PoolQC','MiscFeature','Alley','Fence','FireplaceQu',
            'GarageType','GarageFinish','GarageQual','GarageCond',
            'BsmtQual','BsmtCond','BsmtExposure',
            'BsmtFinType1','BsmtFinType2'
        ]

        zero_cols = [
            'GarageYrBlt','GarageArea','GarageCars',
            'BsmtFinSF1','BsmtFinSF2','BsmtUnfSF',
            'TotalBsmtSF','BsmtFullBath','BsmtHalfBath'
        ]

        for col in none_cols:
            if col in X.columns:
                X[col] = X[col].fillna("None")

        for col in zero_cols:
            if col in X.columns:
                X[col] = X[col].fillna(0)

        return X


# =====================================
# 2️⃣ Feature Engineering
# =====================================
class FeatureEngineer(BaseEstimator, TransformerMixin):

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        X = X.copy()

        X["TotalSF"] = X["TotalBsmtSF"] + X["1stFlrSF"] + X["2ndFlrSF"]
        X["TotalBath"] = (
            X["FullBath"] + 0.5 * X["HalfBath"] +
            X["BsmtFullBath"] + 0.5 * X["BsmtHalfBath"]
        )
        X["HouseAge"] = X["YrSold"] - X["YearBuilt"]
        X["RemodAge"] = X["YrSold"] - X["YearRemodAdd"]

        X["HasGarage"] = (X["GarageArea"] > 0).astype(int)
        X["Has2ndFlr"] = (X["2ndFlrSF"] > 0).astype(int)

        # Convert numeric categorical to string
        if "MSSubClass" in X.columns:
            X["MSSubClass"] = X["MSSubClass"].astype(str)

        return X


# =====================================
# 3️⃣ Skewness Fix (No Leakage)
# =====================================
class SkewnessHandler(BaseEstimator, TransformerMixin):

    def fit(self, X, y=None):
        X = X.copy()
        numeric_cols = X.select_dtypes(include=np.number).columns
        skew_vals = X[numeric_cols].apply(lambda x: skew(x.dropna()))
        self.high_skew = skew_vals[abs(skew_vals) > 0.75].index
        return self

    def transform(self, X):
        X = X.copy()
        for col in self.high_skew:
            if col in X.columns:
                X[col] = np.log1p(X[col])
        return X


# =====================================
# 4️⃣ Build Full Pipeline
# =====================================
def build_full_pipeline():

    numeric_transformer = Pipeline([
        ("imputer", SimpleImputer(strategy="median")),
        ("scaler", RobustScaler())
    ])

    categorical_transformer = Pipeline([
        ("imputer", SimpleImputer(strategy="constant", fill_value="None")),
        ("onehot", OneHotEncoder(handle_unknown="ignore"))
    ])

    preprocessor = ColumnTransformer([
        ("num", numeric_transformer,
         make_column_selector(dtype_include=np.number)),
        ("cat", categorical_transformer,
         make_column_selector(dtype_include=object))
    ])

    return Pipeline([
        ("missing", MissingValueHandler()),
        ("engineer", FeatureEngineer()),
        ("skew", SkewnessHandler()),
        ("preprocessor", preprocessor)
    ])
