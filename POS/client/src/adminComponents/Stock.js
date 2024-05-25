import axios from "axios"
import { useEffect, useState } from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/Stock.css'


function Stock() {
    const [searchStockByItemID, setSearchStockByItemID] = useState(null)
    const [searchedStock, setSearchedStock] = useState(null)
    const [stocks, setStocks] = useState(null)
    const [addQty, setAddQty] = useState(0)
    


    useEffect(() => {
        loadStocks()
    }, [])

    function loadStocks() {
        axios.get("http://localhost:8080/loadStocks")
            .then(function (respnose) {
                setStocks(respnose.data)

            })
            .catch(function (error) {
                console.log(error)
            })
    }
    function handleSearchStockByID(event) {
        setSearchStockByItemID(event.target.value)
        setAddQty(0)
    }

    function search() {

        for (let i = 0; i < stocks.length; i++) {


            if (stocks[i].item.itemID == searchStockByItemID) {
                setSearchedStock(stocks[i])
                return
            }
           
           
        }
        setSearchedStock(null)
        alert("no stock with provided itemID")
    }
    function handleAddQty(event) {
        setAddQty(event.target.value)
    }
    function save() {

        if(isNaN(addQty)){
            alert("quantity must be a number. please enter a number in the input field")
            console.log("quantity must be a number. please enter a number in the input field")
            return
        }
        const qty = searchedStock.qty_on_hand + parseInt(addQty,10);


        const data = {
            "stockID": searchedStock.stockID,
            "item": searchedStock.item,
            "unit": searchedStock.unit,
            "qty_on_hand": qty
        }
        axios.put("http://localhost:8080/updateStock", data)
            .then(function (respnose) {
                console.log(respnose)
                loadStocks();
                alert("quantity succesfully added")
                
            })
            .catch(function (error) {
                console.log(error)
                alert(error)
            })
setSearchedStock(null)
setAddQty(0)

    }

    return (
        <div>
            <h2>Manage Stocks</h2>
            <div className="searchAndAddQty_stock">

                <label >search by itemID</label>
                <input onChange={handleSearchStockByID} />
                <button onClick={search}> search</button>

                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>stockID</th>
                            <th>itemID</th>
                            <th>qty_on_hand</th>
                            <th>add quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        {searchedStock && (
                            <tr>
                                <td>{searchedStock.stockID}</td>
                                <td>{searchedStock.item.itemID}</td>
                                <td>{searchedStock.qty_on_hand}</td>
                                <td><input placeholder="add quantity" onChange={handleAddQty}></input></td>
                                <td>  <button onClick={save}>save</button></td>

                            </tr>
                        )}
                    </tbody>
                </table>


            </div>
            <div className="stockTable_stock">
                <label>stocks</label>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>stockID</th>
                            <th>ItemID</th>
                            <th>item name</th>
                            <th>unit</th>
                            <th>qty_on_hand</th>

                        </tr>
                    </thead>
                    <tbody>
                        {stocks && stocks.map((stock) => (
                            <tr key={stock.stockID}>
                                <td>{stock.stockID}</td>
                                <td>{stock.item.itemID}</td>
                                <td>{stock.item.name}</td>
                                <td>{stock.unit}</td>
                                <td>{stock.qty_on_hand}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
export default Stock;
