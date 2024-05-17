import axios from "axios";
import { useEffect, useState } from "react";

function Suppliers(itemID){
    const[suppliers, setSuppliers]=useState(null);
   

    useEffect(()=>{
        axios.get("http://localhost:8080/loadSuppliersByItem"+itemID)
        .then(function(response){
            setSuppliers(response.data)
        })
        .catch(function(error){
            console.log(error)
        })
    })/*@Column(name="supplier_name")
    private String name;

    @Column(name="contactNo")
    private String contactNo;

    @Column(name="email")
    private String email;

    @Column(name="address") */

    return(<div>
        <table>
            <thead>
                <tr>
                    <th>supplierID</th>
                    <th>supplier name</th>
                    <th>contact_No</th>
                    <th>email</th>
                    <th>address</th>
                </tr>
            </thead>
        <tbody>
            {suppliers && suppliers.map((supplier)=>(
                <tr>
                <td>{supplier.supplierID}</td>
                <td>{supplier.name}</td>
                <td>{supplier.contactNo}</td>
                <td>{supplier.email}</td>
                <td>{supplier.address}</td>
                </tr>
            ))}
        </tbody>
                    </table>

    </div>)
}

export default Suppliers;