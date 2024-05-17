import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

function Suppliers() {
    const [suppliers, setSuppliers] = useState(null);
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const itemID = queryParams.get("itemID");


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

const[f,setF]=useState(null);
function handleF(){
    setF(suppliers);
}


    return (<div>
        <button onClick={()=>console.log(suppliers==null)}>gf</button>
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
                {suppliers && suppliers.map((supplier) => {console.log(supplier);return (
                    
                    <tr key={supplier.supplierID}>
                        <td>{supplier.supplierID}</td>
                        <td >{supplier.name}</td>
                        <td>{supplier.contactNo}</td>
                        <td>{supplier.email}</td>
                        <td >{supplier.address}</td>
                    </tr>
                )})}
            </tbody>
        </table>

    </div>)
}

export default Suppliers;