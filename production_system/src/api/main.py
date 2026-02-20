import numpy as np
import pandas as pd
import joblib
import os
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from production_system.src.api.schemas import HouseData

app = FastAPI(title="House Prediction API")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"], 
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

MODEL_PATH = "production_system/src/models/house_model.joblib"
if not os.path.exists(MODEL_PATH):
    MODEL_PATH = "house_model.joblib"

model = joblib.load(MODEL_PATH)

EXPECTED_COLUMNS = [
    'MSSubClass', 'MSZoning', 'LotFrontage', 'LotArea', 'Street', 'Alley', 'LotShape', 
    'LandContour', 'Utilities', 'LotConfig', 'LandSlope', 'Neighborhood', 'Condition1', 
    'Condition2', 'BldgType', 'HouseStyle', 'OverallQual', 'OverallCond', 'YearBuilt', 
    'YearRemodAdd', 'RoofStyle', 'RoofMatl', 'Exterior1st', 'Exterior2nd', 'MasVnrType', 
    'MasVnrArea', 'ExterQual', 'ExterCond', 'Foundation', 'BsmtQual', 'BsmtCond', 
    'BsmtExposure', 'BsmtFinType1', 'BsmtFinSF1', 'BsmtFinType2', 'BsmtFinSF2', 
    'BsmtUnfSF', 'TotalBsmtSF', 'Heating', 'HeatingQC', 'CentralAir', 'Electrical', 
    '1stFlrSF', '2ndFlrSF', 'LowQualFinSF', 'GrLivArea', 'BsmtFullBath', 'BsmtHalfBath', 
    'FullBath', 'HalfBath', 'BedroomAbvGr', 'KitchenAbvGr', 'KitchenQual', 'TotRmsAbvGrd', 
    'Functional', 'Fireplaces', 'FireplaceQu', 'GarageType', 'GarageYrBlt', 'GarageFinish', 
    'GarageCars', 'GarageArea', 'GarageQual', 'GarageCond', 'PavedDrive', 'WoodDeckSF', 
    'OpenPorchSF', 'EnclosedPorch', '3SsnPorch', 'ScreenPorch', 'PoolArea', 'PoolQC', 
    'Fence', 'MiscFeature', 'MiscVal', 'MoSold', 'YrSold', 'SaleType', 'SaleCondition'
]

@app.get("/")
def read_root():
    return {"status": "online", "model": "stacked Regressor v1.0"}

@app.post("/predict")
def predict_price(data: HouseData):
    try:
        input_dict = data.model_dump(by_alias=True)
        input_df = pd.DataFrame([input_dict])

        numeric_keywords = ["sf", "area", "bath", "built", "remod", "sold", "qual", "cond", "yr", "val", "frontage", "porch", "class"]
        for col in EXPECTED_COLUMNS:
            if col not in input_df.columns:
                if any(k in col.lower() for k in numeric_keywords) and "type" not in col.lower():
                    input_df[col] = 0
                else:
                    input_df[col] = "None"

        input_df = input_df[EXPECTED_COLUMNS]
        log_pred = model.predict(input_df)
        price = float(np.expm1(log_pred)[0])

        return {"predicted_price_usd": round(price, 2), "currency": "USD"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)