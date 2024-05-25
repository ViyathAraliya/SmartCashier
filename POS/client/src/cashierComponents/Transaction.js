import axios from "axios"
import { useEffect, useState } from "react"
import "../css/Transaction.css"
import 'bootstrap/dist/css/bootstrap.min.css';


function Transaction() {
    const [phoneNumber, setPhoneNumber] = useState(null)
    const [existingCustomer, setExistingCustomer] = useState("new customer")
    const [items, setItems] = useState(null)
    const [search, setSearch] = useState(null)
    const [searchedItem, setSearchedItem] = useState(null)
    const [cart, setCart] = useState([])
    const [stocks,setStocks]=useState(null)
    const [qty,setQty]=useState(0)
    const [total,setTotal]=useState(0);
    function hanldePhonenNumber(event) {
        setPhoneNumber(event.target.value)
    }

    useEffect(() => {

        loadItems()
        loadStocks()
    },[])

function handleTotal(){
    let total=0;
    for(let i=0;i<cart.length;i++){
        console.log(cart[i].qty)
        total=total+cart[i].subTotal
    }
    setTotal(total)
}
    function handleQty(event){
        
        setQty(event.target.value)
       
    }
    function loadStocks() {
        axios.get("http://localhost:8080/loadStocks")
            .then(function (respnose) {
                setStocks(respnose.data)

            })
            .catch(function (error) {
                console.log(error)
            })
    }
    function loadItems() {
        axios.get("http://localhost:8080/loadItems")
            .then(function (respnose) {
                setItems(respnose.data)
            })
            .catch(function (error) {
                console.log(error)
            })
    }
    function handleSearch(event) {
        setSearch(event.target.value)
    }

    function searchItemByName() {
        for (let i = 0; i < items.length; i++) {
            if (items[i].name == search) {
                setSearchedItem(items[i])
                //   console.log("t ",items[i].name, " s ",search)
                return
            }
        }
        console.log("item not found")
        alert("ïtem not found")

    }

    function addItemToCart(item) {
        if(qty<1){
            alert("please enter the quantity befire adding")
            return;
        }
        const parsedQty=parseInt(qty)
        const subTotal=parsedQty*item.unitPrice
        const  cartItem={
            "item":item,
            "qty":qty,
            "subTotal":subTotal
        }
        setCart(prevCart => [...prevCart, cartItem])
        setQty(0);
        handleTotal()
    }
    return (
        /* trasnactionDto:    private Customer customer;
                              private List<Transaction_Item_Dto> transaction_Item_dtos;//with transactionID null
                              private  Date dateTime;
                              private Long totalAmount;
  
      transaction_itemDto:    private Long itemID;
                              private Long qty;
                              private Long amount;*/
        <>
        <h1>Transaction</h1>
            <div className="basicDetails_transaction">
                <label>customer phone number</label>
                <input placeholder="optional" onChange={hanldePhonenNumber} />{' '}
                <label>customer status : </label>{' '} <input value={existingCustomer} readOnly style={{ color: 'lightcoral', backgroundColor: '#f5f5f5', border: 'none' }} />
                <br></br>
                <div className="addItem_transcation">

                    <div className="itemAndCartTable">
                        <div className="miniTableItem_transaction">
                            <h4> Add item </h4>
                            <br></br>
                            <label>Search item</label>
                            <input onChange={handleSearch} />{' '}
                            <button onClick={searchItemByName}>search by name</button>{' '}
                            <button>search by ID</button>
                            {searchedItem && (
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>itemID</th>
                                            <th>item name</th>
                                            <th>unit</th>
                                            <th>unit price</th>
                                            <th>category</th>
                                            <th>qty</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr key={searchedItem.itemID}>
                                            <td>{searchedItem.itemID}</td>
                                            <td>{searchedItem.name}</td>
                                            <td>{searchedItem.unit}</td>
                                            <td>{searchedItem.unitPrice}</td>
                                            <td>{searchedItem.category.name}</td>
                                            <td><input placeholder="Enter quantity" onChange={handleQty}/></td>
                                            <td><button onClick={() => { addItemToCart(searchedItem) }}>add to cart</button></td>
                                        </tr>

                                    </tbody>
                                </table>
                            )}
                        </div>
                        <div className="cart">
                            <h3>cart</h3>
                            <table className="table table-striped" >
                                <thead>
                                    <tr>

                                        <th>item</th>
                                        <th>unit_price</th>
                                        <th>order qty</th>
                                        <th>sub total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {cart && cart.map((cartItem) => (

                                        <tr key={cartItem.item.itemID}>
                                            <td>{cartItem.item.name}</td>
                                            <td>{cartItem.item.unitPrice}</td>
                                            <td>{cartItem.qty}</td>
                                            <td>{cartItem.subTotal}</td>

                                        </tr>

                                    ))}
                                    <tr><td></td>
                                    <td>{"total: "+total}</td></tr>
                                </tbody>
                            </table>


                        </div>
                    </div>

                </div>

            </div>

<div className="ItemsAndStocks">
            <div className="itemTable_transcation">
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>itemID</th>
                            <th>item name</th>
                            <th>unit</th>
                            <th>unit price</th>
                            <th>category</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items && items.map((item) => (
                            <tr key={item.itemID}>
                                <td>{item.itemID}</td>
                                <td>{item.name}</td>
                                <td>{item.unit}</td>
                                <td>{item.unitPrice}</td>
                                <td>{item.category.name}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className="stocksTable_transaction">
            <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>itemID</th>
                            <th>qty_on_hand</th>

                        </tr>
                    </thead>
                    <tbody>
                        {stocks && stocks.map((stock) => (
                            <tr key={stock.stockID}>
                               <td>{stock.item.itemID}</td>
                                <td>{stock.qty_on_hand}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            </div>
        </>
    )

}
export default Transaction