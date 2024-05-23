import { useState } from "react"

function Stock(){
    const[stockSearchedByID, setStockSearchedByID]=useState(null)
  
    return(
        <div>
            <h2>Manage Stocks</h2>
            <div className="searchByID">
                <label className="searchByIDLabel">search by name</label>
                <input className="searchByIDInput"/>
                {stockSearchedByID && stockSearchedByID.map((stock)=>(
                    <label className="stockName">{stock.stockID}</label>
                ))}             
            </div>
        </div>
    )
}
