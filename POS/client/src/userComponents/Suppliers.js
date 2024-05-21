import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";



function Suppliers() {
    const [suppliers, setSuppliers] = useState(null);
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const itemID = queryParams.get("itemID");

    const [nameToBeSearched, setNameToBeSearched] = useState(null);
    const [searchedSupplier, setSearchedSupplier] = useState(null);


    useEffect(() => {

        axios.get(`http://localhost:8080/loadSupplier_Item/${itemID}`)
            .then(function (response) {
                setSuppliers(response.data)


            })
            .catch(function (error) {
                console.log(error)
            })

    }, [])



    function deleteSupplier_Item(event, supplierID) {
        event.preventDefault();
        handleSupplierDoesntProvideItem(event, itemID, supplierID);
    }

    function handleSupplierDoesntProvideItem(event, itemID, supplierID) {
        event.preventDefault();

        const data = {
            "itemID": itemID,
            "supplierID": supplierID
        };


        console.log("item id : " + itemID + "supplierID :" + supplierID)
        axios.delete("http://localhost:8080/supplierDoesntProvideThisItem", { data: data })
            .then(function (response) {
                console.log(response);
                setSuppliers(null);
            })
            .catch(function (error) {
                console.log(error);
            });

    }

    function addSearchedSupplierToItem(event) {
        event.preventDefault();
        const data = {
            "supplier_Item_ID": {
                "itemID": itemID,
                "supplierID": searchedSupplier.supplierID
            }
        }

        axios.post("http://localhost:8080/saveSupplier_Item", data)
            .then(function (respnose) {
                console.log(respnose)
                if (suppliers == null) {

                    setSuppliers([searchedSupplier])
                }
                else {
                    setSuppliers(prevSuppliers => [...prevSuppliers, searchedSupplier])
                }
            })
            .catch(function (error) {
                console.log(error)
            })



    }


    function handleNameToBeSearched(event) {
        setNameToBeSearched(event.target.value);
    }

    function handleSearchedSupplier(event) {
        event.preventDefault();
        axios.get(`http://localhost:8080/findSupplierByName/${nameToBeSearched}`).
            then(function (respnose) {
                console.log(respnose);
                setSearchedSupplier(respnose.data)
            })
            .catch(function (error) {
                console.log(error);
                setSearchedSupplier(null)
            })

    }
    function inputChange() { }



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
                            <td><label>{supplier.supplierID} </label></td>
                            <td ><input value={supplier.name} onChange={inputChange} /></td>
                            <td><input value={supplier.contactNo} onChange={inputChange} /></td>
                            <td><input value={supplier.email} onChange={inputChange} /></td>
                            <td ><input value={supplier.address} onChange={inputChange} /></td>
                            <td><button onClick={(event) => deleteSupplier_Item(event, supplier.supplierID)}>this supplier no longer provide this item</button></td>

                        </tr>
                    )
                })}
            </tbody>
        </table>
        <div>
            <label>add an existing supplier</label>
            <input placeholder="Search By Name" onChange={handleNameToBeSearched} />
            <button onClick={handleSearchedSupplier}>search</button>
            {searchedSupplier && <div>

                <label>{searchedSupplier.name}</label><br></br>
                <label>{searchedSupplier.contactNo}</label><br></br>
                <label>{searchedSupplier.email}</label><br></br>
                <label>{searchedSupplier.address}</label>  </div>}
            <button onClick={addSearchedSupplierToItem}>add</button>           </div>
       


    </div>)
}

export default Suppliers;