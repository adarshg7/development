from pydantic import BaseModel, Field, ConfigDict
from typing import Optional

class HouseData(BaseModel):
    MSSubClass: int
    MSZoning: str
    LotArea: int
    Street: str
    LotShape: str
    Neighborhood: str
    Condition1: str
    HouseStyle: str
    OverallQual: int
    OverallCond: int
    YearBuilt: int
    YearRemodAdd: int
    GrLivArea: int
    FullBath: int
    BedroomAbvGr: int
    KitchenAbvGr: int
    TotRmsAbvGrd: int
    Fireplaces: int
    GarageCars: int
    GarageArea: int
    TotalBsmtSF: int
    FirstFlrSF: int = Field(alias="1stFlrSF")
    SecondFlrSF: int = Field(alias="2ndFlrSF")
    HalfBath: int
    BsmtFullBath: int
    BsmtHalfBath: int
    MoSold: int
    YrSold: int

    model_config = ConfigDict(
        populate_by_name=True,
        json_schema_extra={
            "example": {
                "MSSubClass": 60, "MSZoning": "RL", "LotArea": 8450, "Street": "Pave",
                "LotShape": "Reg", "Neighborhood": "CollgCr", "Condition1": "Norm",
                "HouseStyle": "2Story", "OverallQual": 7, "OverallCond": 5,
                "YearBuilt": 2003, "YearRemodAdd": 2003, "GrLivArea": 1710,
                "FullBath": 2, "BedroomAbvGr": 3, "KitchenAbvGr": 1,
                "TotRmsAbvGrd": 8, "Fireplaces": 0, "GarageCars": 2,
                "GarageArea": 548, "TotalBsmtSF": 856, "1stFlrSF": 856,
                "2ndFlrSF": 854, "HalfBath": 1, "BsmtFullBath": 1,
                "BsmtHalfBath": 0, "MoSold": 2, "YrSold": 2008
            }
        }
    )