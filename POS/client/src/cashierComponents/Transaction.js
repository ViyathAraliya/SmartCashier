import axios from "axios"
import { useEffect, useState } from "react"

function Transaction() {
    const [phoneNumber, setPhoneNumber] = useState(null)
    const [existingCustomer, setExistingCustomer] = useState("new customer")
    const [items, setItems] = useState(null)
    const [search, setSearch] = useState(null)
    const [searchedItem, setSearchedItem] = useState(null)
    const [cart, setCart] = useState([])
    function hanldePhonenNumber(event) {
        setPhoneNumber(event.target.value)
    }

    useEffect(() => {

        loadItems()
    })
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
        console.log("ite ", item.name)
        setCart(prevCart => [...prevCart, item])
        console.log("ll", cart)
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
            <div className="basicDetails_transaction">
                <label>customer phone number</label>
                <input placeholder="optional" onChange={hanldePhonenNumber} />{' '}
                <label>customer status : </label>{' '} <input value={existingCustomer} readOnly style={{ color: 'lightcoral', backgroundColor: '#f5f5f5', border: 'none' }} />
                <br></br>
                <div className="addItem_transcation">
                    <h4> Add item </h4>
                    <br></br>
                    <label>Search item</label>
                    <input onChange={handleSearch} />{' '}
                    <button onClick={searchItemByName}>search by name</button>{' '}
                    <button>search by ID</button>
                    <div className="itemAndCartTable">
                        <div className="miniTableItem_transaction">
                            {searchedItem && (
                                <table>
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
                                        <tr key={searchedItem.itemID}>
                                            <td>{searchedItem.itemID}</td>
                                            <td>{searchedItem.name}</td>
                                            <td>{searchedItem.unit}</td>
                                            <td>{searchedItem.unit_price}</td>
                                            <td>{searchedItem.category.name}</td>
                                            <td><button onClick={() => { addItemToCart(searchedItem) }}>add to cart</button></td>
                                        </tr>

                                    </tbody>
                                </table>
                            )}
                        </div>
                        <div className="cart">

                            <table >
                                <thead>
                                    <tr>

                                        <th>item</th>
                                        <th>price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {cart && cart.map((item) => (
                                    
                                        <tr key={item.itemID}>
                                            <td>{item.name}</td>
                                            <td>{item.unitPrice}</td>
                                        </tr>
                                   
                                ))}
                                 </tbody>
                            </table>


                        </div>
                    </div>

                </div>

            </div>


            <div className="itemTable_transcation">
                <table>
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
                                <td>{item.unit_price}</td>
                                <td>{item.category.name}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    )

}
export default Transaction