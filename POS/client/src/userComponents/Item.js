import axios from "axios";
import { useEffect, useState } from "react";

function Item() {
    const [name, setItemName] = useState(null);
    const [unit, setUnit] = useState(null);
    const [unitPrice, setUnitPrice] = useState(null);
    const [categories, setCategories] = useState(null);
    const [suppliers, setSuppliers] = useState(null);
    const [items,setItems]=useState(null);

    const [categoryID, setCategoryID] = useState(null);
    //  const [categoryName,setCategoryName]=useState(null);
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

        axios.get("http://localhost:8080/loadSuppliers")
            .then(function (respnose) {
                setSuppliers(respnose.data)
            })
            .catch(function (error) {
                console.log(error)
            })
            axios.get("http://localhost:8080/loadItems")
            .then(function (respnose) {
                setItems(respnose.data)
            })
            .catch(function (error) {
                console.log(error)
            })
    }


        , [])

        function loadItems(){
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

    const handleSupplierName = (event) => {
        setSupplierName(event.target.value);
    };

    const handleSupplierContactNo = (event) => {
        setSupplierContactNo(event.target.value);
    };

    const handleSupplierEmail = (event) => {
        setSupplierEmail(event.target.value);
    };

    const handleSupplierAddress = (event) => {
        setSupplierAddress(event.target.value);
    };

    const handleQty = (event) => {
        setQty(event.target.value);
    };


    function saveItemWithNewSupplier() {
        if (categoryID == null) {
            alert("please select a category")
            
            return
        }

        for(let i=0;i<suppliers.length;i++){
            if(suppliers[i].name==supplierName){
                console.log("Supplier name already exists in the databse."
                +" Add this via 'Create Item with an existing supplier' section ");
                alert("Supplier name already exists in the databse."
                +" Add this via 'Create Item with an existing supplier' section ")
                return;
            }
        }

        for(let i=0;i<items.length;i++){
            if(items[i].name==name){
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
                "stockID": null,
                "item": null,
                "unit": null,
                "qty_on_hand": null,
                "ordered_by_customers": null,
                "available_for_sale": null
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
            })
            .catch(function (error) {
                console.log(error)
                //alert(" Make sure you are creating a new item and a new supplier. Not existsing ones")
            })
    }


    return (<div><div className="createItemWithNewSupplier">
        <h1>create an item with a new supplier</h1>
        <div className="itemBasics">


            <label className="itemName">Name</label>
            <input className="itemName" onChange={handleName} />

            <label className="itemUnit">unit</label>
            <input className="itemUnit" onChange={handleUnit} />

            <label className="itemUnitPrice">unit price</label>
            <input className="itemUnitPrice" onChange={handleUnitPrice} />

            <label className="categoryID">category</label>
            <select onChange={handleCategoryID}>
                <option value={null}>-select a category</option>

                {categories && categories.map((category) => (
                    <option key={category.categoryID} value={category.categoryID}>{category.name}</option>
                ))}</select>


            <label className="qty_on_hand">qty_on_hand</label>
            <input className="qty_on_hand" onChange={handleQty} />
        </div>

        <div className="supplierForItem">
            <h3>supplier details</h3>

            <label className="supplierName">supplier name</label>
            <input className="supplierName" onChange={handleSupplierName}></input>

            <label className="contactNo">Contact Number</label>
            <input className="contactNo" onChange={handleSupplierContactNo} />

            <label className="email">Email</label>
            <input className="email" onChange={handleSupplierEmail} />

            <label className="address">Address</label>
            <input className="address" onChange={handleSupplierAddress} />
        </div>
        <button onClick={saveItemWithNewSupplier}>save</button>
    </div>
    <div className="itemTable">
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
                {items && items.map((item)=>(
                    <tr>
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

    </div>);
}

export default Item;