import axios from "axios";
import { useEffect, useState } from "react";
import "../css/createItems.css"

function Item() {
    const [name, setItemName] = useState(null);
    const [unit, setUnit] = useState(null);
    const [unitPrice, setUnitPrice] = useState(null);
    const [categories, setCategories] = useState(null);
    const [suppliers, setSuppliers] = useState(null);
    const [items, setItems] = useState(null);

    const [categoryID, setCategoryID] = useState(null);
    //  const [categoryName,setCategoryName]=useState(null);
    const [supplierID, setSupplierID]=useState(null);
    const [supplierName, setSupplierName] = useState(null);
    const [supplierContactNo, setSupplierContactNo] = useState(null)//supplier
    const [supplierEmail, setSupplierEmail] = useState(null)
    const [supplierAddress, setSupplierAddress] = useState(null)
    const [qty, setQty] = useState(null)

    useEffect(() => {
        axios.get("http://localhost:8080/loadCategories")
            .then(function (response) {
                setCategories(response.data)
            })
            .catch(function (error) {
                console.log(error);
            })

        loadSuppliers();
        loadItems();
    }


        , [])

    function loadSuppliers() {
        axios.get("http://localhost:8080/loadSuppliers")
            .then(function (respnose) {
                setSuppliers(respnose.data)
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

    const handleName = (event) => {
        setItemName(event.target.value);
    };

    const handleUnit = (event) => {
        setUnit(event.target.value);
    };

    const handleUnitPrice = (event) => {
        setUnitPrice(event.target.value);
    };

    const handleCategoryID = (event) => {

        const selectedCategoryId = event.target.value;
        console.log("Selected category ID:", selectedCategoryId);
        setCategoryID(selectedCategoryId);
    };

    function handleSupplierID(event){
        setSupplierID(event.target.value)
    }

    function handleSupplierName(event){
        setSupplierName(event.target.value);
    };

    function handleSupplierContactNo(event){
        setSupplierContactNo(event.target.value);
    };

    function handleSupplierEmail(event){
        setSupplierEmail(event.target.value);
    };

     function handleSupplierAddress(event){
        setSupplierAddress(event.target.value);
    };

    function handleQty(event){
        setQty(event.target.value);
    };


    function saveItemWithNewSupplier() {
        if (categoryID == null) {
            alert("please select a category")

            return
        }

        for (let i = 0; i < suppliers.length; i++) {
            if (suppliers[i].name == supplierName) {
                console.log("Supplier name already exists in the databse."
                    + " Add this via 'Create Item with an existing supplier' section ");
                alert("Supplier name already exists in the databse."
                    + " Add this via 'Create Item with an existing supplier' section ")
                return;
            }
        }

        for (let i = 0; i < items.length; i++) {
            if (items[i].name == name) {
                alert("item already exists in the databse.");
                return;
            }
        }
        console.log("catID " + categoryID)
        const data = {
            "itemID": null,
            "name": name,
            "unit": unit,
            "unitPrice": unitPrice,
            "category": {
                "categoryID": categoryID,
                "name": null
            },
            "stock": {
             //   "stockID": null,
               // "item": null,
                //"unit": null,
                //"qty_on_hand": null,
                //"ordered_by_customers": null,
                //"available_for_sale": null
            },
            "supplier": {

                "supplierID": null,
                "name": supplierName,
                "contactNo": supplierContactNo,
                "email": supplierEmail,
                "address": supplierAddress


            },
            "qty": qty
        }


        axios.post("http://localhost:8080/saveItem", data)
            .then(function (response) {
                console.log(response)
                alert("item saved succesfully")
                loadItems();
                loadSuppliers();
            })
            .catch(function (error) {
                console.log(error)
              
            })
    }

    

    function saveItemWithAnExistingSupplier() {
        if (categoryID == null) {
            alert("please select a category")

            return
        }
        let supplierExists=false;
       l1: for (let i = 0; i < suppliers.length; i++) {
            
            if (suppliers[i].supplierID == supplierID) {
                supplierExists=true;
                break l1;
            }
        }

        if(!supplierExists){
            console.log("no supplier with provided id")
            alert("no supplier with provided id");
            return
        }

        for (let i = 0; i < items.length; i++) {
            if (items[i].name == name) {
                alert("item already exists in the databse.");
                return;
            }
        }
        console.log("catID " + categoryID)
        const data = {
            "itemID": null,
            "name": name,
            "unit": unit,
            "unitPrice": unitPrice,
            "category": {
                "categoryID": categoryID,
               // "name": null
            },
            "stock": {
               // "stockID": null,
                //"item": null,
                //"unit": null,
                //"qty_on_hand": null,
                //"ordered_by_customers": null,
                //"available_for_sale": null
            },
            "supplier": {

                "supplierID": supplierID,
           //     "name": supplierName,
             //   "contactNo": supplierContactNo,
               // "email": supplierEmail,
                //"address": supplierAddress


            },
            "qty": qty
        }


        axios.post("http://localhost:8080/saveItem", data)
            .then(function (response) {
                console.log(response)
                alert("item saved succesfully")
                loadItems();
                loadSuppliers();
            })
            .catch(function (error) {
                console.log(error)
              
            })
    }

    return (<div>

        <div className="_createItem">
            <h3>create  item</h3>
            <div className="itemBasics_createItems">
                <h4>Item details</h4>

                <label>Name</label>
                <input onChange={handleName} />
                <br></br>

                <label>unit</label>
                <input onChange={handleUnit} />
                <br></br>
                <label >unit price</label>
                <input onChange={handleUnitPrice} />
                <br></br>
                <label >category</label>
                <select onChange={handleCategoryID}>
                    <option value={null}>-select a category</option>

                    {categories && categories.map((category) => (
                        <option key={category.categoryID} value={category.categoryID}>{category.name}</option>
                    ))}</select><br></br>


                <label className="qty_on_hand_createItems">qty_on_hand</label>
                <input className="qty_on_hand_createItems" onChange={handleQty} />
            </div>
<br></br>
            <div className="supplierForItem_createItems">
                <div className="createWithANewSupplier">
                    <h3>save with a new supplier</h3>


                    <label>supplier name</label>
                    <input onChange={handleSupplierName}></input>
                    <br></br>
                    <label>Contact Number</label>
                    <input onChange={handleSupplierContactNo} />
                    <br></br>
                    <label >Email</label>
                    <input onChange={handleSupplierEmail} />
                    <br></br>
                    <label >Address</label>
                    <input onChange={handleSupplierAddress} />
                    <br></br>
                    <button onClick={saveItemWithNewSupplier}>save</button>
                </div>

                 <div className="createItemWithAnExistingSupplier_CreateItem">
                    <h3>save with an existing supplier </h3>
                   
                    <label>supplierID</label>
                    <input onChange={handleSupplierID}></input><br></br>
                    <br></br>
                    <button onClick={saveItemWithAnExistingSupplier}>save</button>


                </div>
            </div>
          



        </div>
        <div className="tables_createItems">
            <div className="itemTable_createItems">
                <h2 className="itemListHeading_createItems">Item List</h2>
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
            <div className="supplierTable_createItems">
                <h2 className="supplierListHeading_createItems">Supplier List</h2>
                <table>
                    <thead>
                        <tr>
                            <th>supplier ID</th>
                            <th>supplier name</th>
                            <th>contact number</th>
                            <th>email</th>
                            <th>address</th>
                        </tr>
                    </thead>


                    <tbody>{suppliers && suppliers.map((supplier) => (
                        <tr key={supplier.supplierID}>
                            <td>{supplier.supplierID}</td>
                            <td>{supplier.name}</td>
                            <td>{supplier.contactNo}</td>
                            <td>{supplier.email}</td>
                            <td>{supplier.address}</td>
                        </tr>
                    ))
                    }</tbody>
                </table>
            </div></div>

    </div>);
}

export default Item;