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
    const [addNewSupplier, setAddNewSupplier] = useState(false);
 

    const [name, setName] = useState(null);
    const [contactNo, setContactNo] = useState(null);
    const [email, setEmail] = useState(null);
    const [address, setAddress] = useState(null);


    const handleName = (event) => {
        setName(event.target.value);
    }

    const handleContactNo = (event) => {
        setContactNo(event.target.value);
    }

    const handleEmail = (event) => {
        setEmail(event.target.value);
    }

    const handleAddress = (event) => {
        setAddress(event.target.value);
    }
    function handleAddNewSupplier() {
        setAddNewSupplier(true)
    }


    function removeSupplierById(id) {
        setSuppliers(prevSuppliers => prevSuppliers.filter(supplier => supplier.supplierID !== id));
    }

    useEffect(() => {

        loadSuppliers();

    }, [])

    function loadSuppliers(){axios.get(`http://localhost:8080/loadSupplier_Item/${itemID}`)
    .then(function (response) {
        setSuppliers(response.data)


    })
    .catch(function (error) {
        console.log(error)
    })}


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
                loadSuppliers();
                alert("supplier removed from item succesfuuly")

            })
            .catch(function (error) {
                console.log(error);
                alert(error)
            });

    }

    function addSearchedSupplierToItem(event) {
        event.preventDefault();
        let a = 0;
        if (suppliers != null) {
            for (a = 0; a < suppliers.length; a++) {
                if (suppliers[a].supplierID == searchedSupplier.supplierID) {
                    alert("supplier has already been added for this item")
                    return;
                }
            }

        }
        const data = {
            "supplier_Item_ID": {
                "itemID": itemID,
                "supplierID": searchedSupplier.supplierID
            }
        }

        axios.post("http://localhost:8080/saveSupplier_Item", data)
            .then(function (respnose) {
                console.log(respnose)
               loadSuppliers();
               alert("supplier added to this item succesfully!")
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
                alert("couldn't find supplier")
                setSearchedSupplier(null)
            })

    }

    function handleCreateSupplier(event) {
        console.log(5)
        event.preventDefault();
        const data = {
            "name": name,
            "contactNo": contactNo,
            "email": email,
            "address": address,
        }

        axios.post("http://localhost:8080/addNewSupplier",data)
        .then(function(response){
            console.log(response)
            alert("Supplier Created. Now search this supplier and add to item")
        }).catch(function(error){
            console.log(error)
            alert(error+". Check if the supplier already exists ")
        })
        

    }
    function inputChange() { }



    return (<div className="container">
    <div className="row">
        <div className="col">
            <button className="btn btn-primary" onClick={() => console.log(suppliers == null)}>Click me</button>
        </div>
    </div>
    <div className="row mt-3">
        <div className="col">
            <table className="table">
                <thead>
                    <tr>
                        <th style={{ color: '#4CAF50' }}>Supplier ID</th>
                        <th style={{ color: '#4CAF50' }}>Supplier Name</th>
                        <th style={{ color: '#4CAF50' }}>Contact No</th>
                        <th style={{ color: '#4CAF50' }}>Email</th>
                        <th style={{ color: '#4CAF50' }}>Address</th>
                        <th style={{ color: '#4CAF50' }}>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {suppliers && suppliers.map((supplier) => (
                        <tr key={supplier.supplierID}>
                            <td>{supplier.supplierID}</td>
                            <td><input className="form-control" value={supplier.name} onChange={inputChange} /></td>
                            <td><input className="form-control" value={supplier.contactNo} onChange={inputChange} /></td>
                            <td><input className="form-control" value={supplier.email} onChange={inputChange} /></td>
                            <td><input className="form-control" value={supplier.address} onChange={inputChange} /></td>
                            <td><button className="btn btn-danger" onClick={(event) => deleteSupplier_Item(event, supplier.supplierID)}>Remove</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    </div>
    <div className="row mt-3">
        <div className="col">
            <label>Add an existing supplier</label>
            <div className="input-group">
                <input className="form-control" placeholder="Search By Name" onChange={handleNameToBeSearched} />
                <button className="btn btn-primary" onClick={handleSearchedSupplier}>Search</button>
            </div>
            <br />
            {searchedSupplier ? (
                <div>
                    <label style={{ color: '#007BFF' }}>{searchedSupplier.name}</label><br />
                    <label style={{ color: '#007BFF' }}>{searchedSupplier.contactNo}</label><br />
                    <label style={{ color: '#007BFF' }}>{searchedSupplier.email}</label><br />
                    <label style={{ color: '#007BFF' }}>{searchedSupplier.address}</label>
                </div>
            ) : (
                <label style={{ color: '#DC3545' }}>Supplier not found</label>
            )}
            <button className="btn btn-success" onClick={addSearchedSupplierToItem}>Add</button>
        </div>
    </div>
    <div className="row mt-3">
        <div className="col">
            <button className="btn btn-primary" onClick={handleAddNewSupplier}>Create a new supplier</button>
            <button className="btn btn-success ms-3" onClick={handleCreateSupplier}>Add This Supplier</button>
            {addNewSupplier && (
                <div className="mt-3">
                    <label style={{ color: '#007BFF' }}>Name</label>
                    <input className="form-control mb-2" onChange={handleName} />
                    <label style={{ color: '#007BFF' }}>Contact No</label>
                    <input className="form-control mb-2" onChange={handleContactNo} />
                    <label style={{ color: '#007BFF' }}>Email</label>
                    <input className="form-control mb-2" onChange={handleEmail} />
                    <label style={{ color: '#007BFF' }}>Address</label>
                    <input className="form-control" onChange={handleAddress} />
                </div>
            )}
        </div>
    </div>
</div>)
}

export default Suppliers;