import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

function Suppliers() {
    const [suppliers, setSuppliers] = useState(null);
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const itemID = queryParams.get("itemID");
    const [supplierID,setSupplierID]=useState(null);
    
    
    useEffect(() => {
        console.log("item id: " + itemID);
        axios.get(`http://localhost:8080/loadSupplier_Item/${itemID}`)
            .then(function (response) {
                setSuppliers(response.data)
                console.log("respnose: " + suppliers == null)
            })
            .catch(function (error) {
                console.log(error)
            })
    }, [])
    function deleteSupplier_Item(event,supplierID){
        handleSupplierID(supplierID);
        handleSupplierDoesntProvideItem(event);
        
    }

    function handleSupplierDoesntProvideItem(){
        
        console.log("item id "+itemID+"supplier id :"+supplierID)
        axios.delete(`http://localhost:8080/supplierDoesntProvideThisItem?itemID=${itemID}&supplierID=${supplierID}`)
        .then(function(respnose){
            console.log(respnose)
        })
        .catch(function(error){
            console.log(88888888)
            console.log(error)
        })
    }
   
function handleSupplierID(supplierID){
   
    setSupplierID(supplierID)
}
function h(s){
    console.log(s);
}
   
    return (<div>
        <button onClick={() => console.log(suppliers == null)}>gf</button>
        <table>
            <thead>
                <tr>
                    <th>Supplier ID</th>
                    <th>supplier name</th>
                    <th>contact_No</th>
                    <th>email</th>
                    <th>address</th>
                </tr>
            </thead>
            <tbody>
                {suppliers && suppliers.map((supplier) => {
                    console.log(supplier); return (

                        <tr key={supplier.supplierID}>
                            <td><input value={supplier.supplierID} /></td>
                            <td ><input value={supplier.name} /></td>
                            <td><input value={supplier.contactNo} /></td>
                            <td><input value={supplier.email} /></td>
                            <td ><input value={supplier.address} /></td>
                            <td><button onClick={(event)=>deleteSupplier_Item(event,supplier.supplierID)}>this supplier no longer provide this item</button></td>
                           
                        </tr>
                    )
                })}
            </tbody>
        </table>
        <label>add new supplier</label>
        <input value="enter supplierID "/>
        <button>add</button>

    </div>)
}

export default Suppliers;