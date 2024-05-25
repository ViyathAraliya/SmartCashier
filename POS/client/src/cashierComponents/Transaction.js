import axios from "axios"
import { useEffect, useState } from "react"
import "../css/Transaction.css"
import 'bootstrap/dist/css/bootstrap.min.css';


function Transaction() {
    const [customers, setCustomers] = useState([])
    const [phoneNumber, setPhoneNumber] = useState(null)
    const [customerExist, setCustomerExist] = useState(false)
    
    const [items, setItems] = useState(null)
    const [search, setSearch] = useState(null)
    const [idIndex, setIdIndex] = useState(null)
    const [searchedItem, setSearchedItem] = useState(null)
    const [cart, setCart] = useState([])
    const [stocks, setStocks] = useState(null)
    const [qty, setQty] = useState(0)
    const [total, setTotal] = useState(0);
    const [transaction_Item_dtos, setTransaction_itemDtos] = useState([])


    useEffect(() => {
       
       // loadCustomers()
        loadItems()
        loadStocks()

    }, [])

    useEffect(() => {
        handleTotal()

    }, [cart])

  
    function handlePhonenNumber(event) {
        setPhoneNumber(event.target.value)
    }
/*
    function handleCustomerExist() {
        for (let i = 0; i < customers.length; i++) {
            if (phoneNumber == customers[i].phoneNumber) {
                return setCustomerExist(true)
            }
        }
        setCustomerExist(false)
    }

    function loadCustomers() {
        axios.get("http://localhost:8080/loadCustomers")
            .then(function (respnose) {
                setCustomers(respnose.data)
            })
            .catch(function (error) {
                console.log(error)
            })
    }
*/

    function handleTotal() {

        let total = 0;
        for (let i = 0; i < cart.length; i++) {

            total = total + cart[i].subTotal
        }
        setTotal(total)
    }
    function handleQty(event) {

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
                setIdIndex(i)
                
                return
            }
        }
        console.log("item not found")
        alert("ïtem not found")

    }


    function searchItemByID() {
        for (let i = 0; i < items.length; i++) {
            if (items[i].itemID == search) {
                setSearchedItem(items[i])
                setIdIndex(i)
                return
            }
        }
        console.log("item not found")
        alert("ïtem not found")

    }

    function addItemToCart(item) {
        if (!/^\d+$/.test(qty)) {
            return alert("please enter a valid quantity(a number)")
        }
        if (qty < 1) {
            alert("please enter the quantity before adding")
            return;
        }
        for (let i = 0; i < cart.length; i++) {
            if (cart[i].item.itemID == item.itemID) {
                alert("this item has been already added to item")
                return
            }
        }
        const parsedQty = parseInt(qty)
        const qty_on_hand2 = stocks[idIndex].qty_on_hand
        if (qty > qty_on_hand2) {
            return alert("qty_on_hand is lesser than the transction qty")
        }
        const subTotal = parsedQty * item.unitPrice
        const cartItem = {
            "item": item,
            "qty": qty,
            "subTotal": subTotal
        }
       const transaction_itemDto = {
            "itemID": item.itemID,
            "qty": qty,
            "amount": subTotal
        }
        setTransaction_itemDtos(prevState => [...prevState, transaction_itemDto])
        setCart(prevCart => [...prevCart, cartItem]);
        //setQty(0);

    }

    function makeTranscation() {
        if(cart.length<1){
            return alert("add to cart before making a transcation")
        }
        const data={
            "customer":{"phoneNumber":phoneNumber}
            ,
            "transaction_Item_dtos":transaction_Item_dtos,
            "dateTime":new Date(),
            "totalAmount":total
        }

        axios.post("http://localhost:8080/saveTransaction",data)
        .then(function(respnose){
            console.log(respnose)
            setCart([])
            loadStocks()
            alert("transaction succesful")
        })
        .catch(function(error){
            console.log(error)
            alert(error)
        })


    }

    if(stocks==null || items==null){return <div>loading ...</div>}
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
                <input placeholder="optional" onChange={handlePhonenNumber} />{' '}
                <label>customer status : </label>{' '}
                {customerExist ? (<input value={"customer exists"} readOnly style={{ color: 'lightcoral', backgroundColor: '#f5f5f5', border: 'none' }} />)
                    : (<input value={"new customer"} readOnly style={{ color: 'lightcoral', backgroundColor: '#f5f5f5', border: 'none' }} />)} <br></br>
                <div className="addItem_transcation">

                    <div className="itemAndCartTable">
                        <div className="miniTableItem_transaction">
                            <h4> Add item </h4>
                            <br></br>
                            <label>Search item</label>
                            <input onChange={handleSearch} />{' '}
                            <button onClick={searchItemByName}>search by name</button>{' '}
                            <button onClick={searchItemByID}>search by ID</button>
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
                                            <td><input placeholder="Enter quantity" onChange={handleQty} /></td>
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
                                        <td>{"total: " + total}</td></tr>
                                </tbody>
                            </table>

                            <button onClick={makeTranscation}>make transcation</button>
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
                                <th>category</th>
                                <th>unit</th>
                                <th>unit price</th>
                             
                            </tr>
                        </thead>
                        <tbody>
                            {items && items.map((item) => (
                                <tr key={item.itemID}>
                                    <td>{item.itemID}</td>
                                    <td>{item.name}</td>
                                    <td>{item.category.name}</td>
                                    <td>{item.unit}</td>
                                    <td>{item.unitPrice}</td>
                                    
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                <div className="stocksTable_transaction">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                
                                <th>qty_on_hand</th>

                            </tr>
                        </thead>
                        <tbody>
                            {stocks && stocks.map((stock) => (
                                <tr key={stock.stockID}>
                                    
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