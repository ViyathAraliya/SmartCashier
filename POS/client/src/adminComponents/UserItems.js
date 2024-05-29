import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../utils/AuthContext";

function UserItems() {

    const [items, setItems] = useState(null);


    
    const[name,setName]=useState(null);
    const [unit, setUnit] = useState(null);
    const [unitPrice, setUnitPrice] = useState(null);
    const [categoryID, setCategoryID] = useState(null);

    const [categories, setCategories] = useState(null);

    const{isAuthenticated, jwtToken}=useAuth();

    const config={
        headers :{ Authorization:`Bearer ${jwtToken}`}
    }

    useEffect(() => {if(isAuthenticated){
        axios.get("http://localhost:8080/loadItems",config)
            .then(function (response) {
                setItems(response.data)
            })
            .catch(function (error) {
                console.log(error);
            });

        axios.get("http://localhost:8080/loadCategories",config)
            .then(function (response) {
                setCategories(response.data)
            })
            .catch(function (error) {
                console.log(error);
            })
    }}, [isAuthenticated]
    );

    function handleName(event,id){
     const tempUpdatedName={"id":id, "name":event.target.value}
     console.log("checking "+id);
        setName(tempUpdatedName);
    }
    function handleUnit(event,id) {
        const tempUpdatedUnit={"id":id, "unit":event.target.value}
        setUnit(tempUpdatedUnit);
    }
    function handleUnitPrice(event,id) {
        const tempUpdatedUnitPrice={"id":id, "unitPrice":event.target.value}
        setUnitPrice(tempUpdatedUnitPrice);
    }
    function handleCategory(event,id) { 
        const tempUpdatedCategoryID={"id":id, "categoryID":event.target.value}
        console.log("Selected category ID:", tempUpdatedCategoryID.categoryID,"itemid ",id);
        setCategoryID(tempUpdatedCategoryID); }
  


    function updateItem(item){
      const itemID=item.itemID;
      let validated_name=item.name;
      let validated_unit=item.unit;
      let validated_unitPrice=item.unitPrice;
      let validated_categoryID=item.category.categoryID;

      if(name!=null && itemID==name.id){
        validated_name=name.name
      }
      if(unit!=null && itemID==unit.id){
        validated_unit=unit.unit
      }
      if(unitPrice!=null && itemID==unitPrice.id){
        validated_unitPrice=unitPrice.unitPrice
      }
      if(categoryID!=null && itemID==categoryID.id){
        validated_categoryID=categoryID.categoryID
      }

//console.log("id",itemID,"name",validated_name,"unit",validated_unit,"unitprice",validated_unitPrice,)
        const data={
            
                "itemID":itemID,
                "name": validated_name,
                "unit": validated_unit,
                "unitPrice":validated_unitPrice,
                  "categoryID": validated_categoryID,     

    }
       // console.log("jjj"+categoryID)
        axios.put("http://localhost:8080/updateItems",data,config)
        .then(function(response){
            console.log(response);
            alert("item updated succesfully")
        })
        .catch( function(error){
            console.log(error)
            alert(error)
        })
setName(null) 
setUnit(null)
setUnitPrice(null)
setCategoryID(null)

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

                        <td><input placeholder={item.name} onChange={(event)=>handleName(event,item.itemID)}/></td>
                        <td><input placeholder={item.unit} onChange={(event)=>handleUnit(event,item.itemID)} /></td>
                        <td><input placeholder={item.unitPrice} onChange={(event)=>handleUnitPrice(event,item.itemID)}/></td>
                        <td><select onChange={(event)=>handleCategory(event,item.itemID)} >
                        <option value={item.category.categoryID}>{item.category.name}</option> 
                            {categories && categories.map((category) => (
                                <option key={category.categoryID} value={category.categoryID}>{category.name}</option>
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