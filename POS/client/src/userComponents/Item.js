import axios from "axios";
import { useEffect, useState } from "react";

function Item() {
    const [name, setItemName] = useState(null);
    const [unit, setUnit] = useState(null);
    const [unitPrice, setUnitPrice] = useState(null);
    const [categories, setCategories] = useState(null);
    const [categoryID, setCategoryID] = useState(null);
    //  const [categoryName,setCategoryName]=useState(null);
    const [supplierName, setSupplierName] = useState(null);
    const [supplierContactNo, setSupplierContactNo] = useState(null)//supplier
    const [supplierEmail, setSupplierEmail] = useState(null)
    const [supplierAddress, setSupplierAddress] = useState(null)
    const [qty, setQty] = useState(null)

    useEffect(()=>{
        axios.get("http://localhost:8080/loadCategories")
            .then(function (response) {
                setCategories(response.data)
            })
            .catch(function (error) {
                console.log(error);
            })
    },[])

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
        console.log("catID "+categoryID)
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
            })
            .catch(function (error) {
                console.log(error)
                alert(error)
            })
    }


    return (<div><div className="createItemWithNewSupplier">
        <div className="itemBasics">
            <h3>Add an item</h3>

            <label for="name">Name</label>
            <input id="name" onChange={handleName} />

            <label for="unit">unit</label>
            <input id="unit" onChange={handleUnit} />

            <label for="unitPrice">unit price</label>
            <input id="unitPrice" onChange={handleUnitPrice} />

            <label for="categoryID">category</label>
            <select onChange={handleCategoryID}>

                {categories && categories.map((category) => (
                    <option key={category.categoryID} value={category.categoryID}>{category.name}</option>
                ))}</select>


            <label for="qty_on_hand">qty_on_hand</label>
            <input id="qty_on_hand" onChange={handleQty} />
        </div>

        <div className="supplierForItem">
            <h3>supplier details</h3>

            <label for="supplierName">supplier name</label>
            <input for="supplierName" onChange={handleSupplierName}></input>

            <label for="contactNo">Contact Number</label>
            <input id="contactNo" onChange={handleSupplierContactNo} />

            <label for="email">Email</label>
            <input id="email" onChange={handleSupplierEmail} />

            <label for="address">Address</label>
            <input id="address" onChange={handleSupplierAddress} />
        </div>
        <button onClick={saveItemWithNewSupplier}>save</button>
    </div>

    </div>);
}

export default Item;