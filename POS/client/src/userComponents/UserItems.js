import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function UserItems() {

    const [items, setItems] = useState(null);

    //to put


    const [unit, setUnit] = useState(null);
    const [unitPrice, setUnitPrice] = useState(null);
    const [category, setCategory] = useState(null);

    const [categories, setCategories] = useState(null);


    useEffect(() => {
        axios.get("http://localhost:8080/loadItems")
            .then(function (response) {
                setItems(response.data)
            })
            .catch(function (error) {
                console.log(error);
            });

        axios.get("http://localhost:8080/loadCategories")
            .then(function (response) {
                setCategories(response.data)
            })
            .catch(function (error) {
                console.log(error);
            })
    }, []
    );
    function handleUnit(event) {
        setUnit(event.target.value);
    }
    function handleUnitPrice(event) {
        setUnitPrice(event.target.value);
    }
    function handleCategory(event) { setCategory(event.target.value); }
  


    function updateItem(item){
        
        console.log("itemID" +item.itemID);
        const data={
        "itemID": item.itemID,
        "name": item.name,
        "unit": unit,
        "unitPrice": unitPrice,
        "category": {"categoryID":category.catagoryID,
            "name":category.name
        }

    }
        
        axios.put("http://localhost:8080/updateItems",data)
        .then(function(response){
            console.log(response);
        })
        .catch( function(error){
            console.log(error)
        })

        console.log("testing id :" +data.itemID)
    }


    return (<div><h2>User Items</h2>
        <div className="editItems">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>unit</th>
                        <th>Unit Price</th>
                        <th>category</th>
                        <th>in the stock</th>
                        <th>Suppliers</th>
                    </tr>
                </thead>

                <tbody>{items && items.map((item) => (
                    <tr key={item.itemID}>
                        <td>{item.itemID}</td>

                        <td>{item.name} </td>
                        <td><input value={item.unit} onChange={handleUnit} /></td>
                        <td><input value={item.unitPrice} onChange={handleUnitPrice}/></td>
                        <td><select onChange={handleCategory}>
                            <option value={item.category?.catagoryID}>{item.category?.name}</option>
                            {categories && categories.map((category) => (
                                <option key={category.categoryID} value={category.id}>{category.name}</option>
                            ))}</select></td>
                            <td>
                                <li><Link to={`/suppliers?itemID=${item.itemID}`}>Suppliers</Link>
                                    </li></td>
                            <td><button onClick={()=>{
                                updateItem(item);
                            }}>update</button></td>
                    </tr>
                ))}</tbody>
            </table>
        </div>
    </div>)
}

export default UserItems;