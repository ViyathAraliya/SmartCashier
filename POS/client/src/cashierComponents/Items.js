import axios from "axios";
import { useState } from "react";
import { useAuth } from "../utils/AuthContext";

function Items(){
     const[items,setItems]=useState(null);
     const{isAuthenticated,jwtToken}=useAuth();

     const config={
        headers : {
            Authorization:'Bearer ${jwtToken}'
        }
        
     }

    const loadItems=()=>{
        axios.get("http://localhost:8080/loadItems")
        .then(function(response){
            setItems(response.data)
        })
        .catch(function(error){
            console.log(error);
        })
    }

    return(
        <div>
            <button onClick={loadItems}>load items</button>
            <table>
                <thead>
                    <tr>
                    <th>Item</th>
                    <th>price</th>
                    </tr>
                </thead>
                <tbody>{items &&
                   items.map((item)=>(<tr key={item.itemID}>
                    <td>{item.name}</td>
                    <td>{item.price}</td>
                   </tr>)) }</tbody>
            </table>
                    
        </div>
    )
}

export default Items;