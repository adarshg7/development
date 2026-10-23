document.getElementById('predictionForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const predictBtn = document.getElementById('predictBtn');
    const loader = document.getElementById('loader');
    const resultCard = document.getElementById('result');
    const priceSpan = document.getElementById('predictedPrice');

    // Show loading state
    predictBtn.disabled = true;
    loader.style.display = 'block';
    resultCard.classList.add('hidden');

    const grLivArea = parseInt(document.getElementById('GrLivArea').value);
    const fullBath = parseInt(document.getElementById('FullBath').value);

    // Collect data from form
    const formData = {
        MSSubClass: parseInt(document.getElementById('MSSubClass').value),
        LotArea: parseInt(document.getElementById('LotArea').value),
        Neighborhood: document.getElementById('Neighborhood').value,
        OverallQual: parseInt(document.getElementById('OverallQual').value),
        YearBuilt: parseInt(document.getElementById('YearBuilt').value),
        GrLivArea: grLivArea,
        FullBath: fullBath,

        // Structural heuristics to satisfy schema and improve accuracy
        TotalBsmtSF: Math.round(grLivArea * 0.8),
        "1stFlrSF": Math.round(grLivArea * 0.7),
        "2ndFlrSF": Math.round(grLivArea * 0.3),
        HalfBath: fullBath > 1 ? 1 : 0,
        BsmtFullBath: 1,
        BsmtHalfBath: 0,

        // Additional fixed fields for simplicity
        MSZoning: "RL",
        Street: "Pave",
        LotShape: "Reg",
        Condition1: "Norm",
        HouseStyle: "2Story",
        OverallCond: 5,
        YearRemodAdd: 2003,
        BedroomAbvGr: 3,
        KitchenAbvGr: 1,
        TotRmsAbvGrd: 8,
        Fireplaces: 1,
        GarageCars: 2,
        GarageArea: 548,
        MoSold: 6,
        YrSold: 2010
    };


    try {
        const response = await fetch('http://127.0.0.1:8000/predict', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });

        if (!response.ok) throw new Error('Prediction failed');

        const data = await response.json();

        // Update UI with result
        priceSpan.innerText = data.predicted_price_usd.toLocaleString();
        resultCard.classList.remove('hidden');

    } catch (error) {
        alert('Error communicating with the backend. Ensure your FastAPI server is running!');
        console.error(error);
    } finally {
        predictBtn.disabled = false;
        loader.style.display = 'none';
    }
});