import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useAuth } from "../utils/AuthContext";



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
    const{isAuthenticated,jwtToken}=useAuth();

    const config={headers :{ Authorization:`Bearer ${jwtToken}`}}

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


   

    useEffect(() => {
if(isAuthenticated){
        loadSuppliers();
}
    }, [isAuthenticated])

    function loadSuppliers(){axios.get(`http://localhost:8080/loadSupplier_Item/${itemID}`,config)
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
        axios.post("http://localhost:8080/supplierDoesntProvideThisItem",  data ,config)
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

        axios.post("http://localhost:8080/saveSupplier_Item", data,config)
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
        axios.get(`http://localhost:8080/findSupplierByName/${nameToBeSearched}`,config).
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

        axios.post("http://localhost:8080/addNewSupplier",data,config)
        .then(function(response){
            console.log(response)
           
            alert("Supplier Created. Now search this supplier and add to item")
        }).catch(function(error){
            console.log(error)
            alert(error+". Check if the supplier already exists ")
        })
        

    }
    function inputChange() { }



    return (<div>
        <h3>suppliers for itemID :{itemID}</h3>
       
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Supplier ID</th>
                        <th>Supplier Name</th>
                        <th>Contact No</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {suppliers && suppliers.map((supplier) => (
                        <tr key={supplier.supplierID}>
                            <td>{supplier.supplierID}</td>
                            <td><input value={supplier.name} onChange={inputChange} /></td>
                            <td><input value={supplier.contactNo} onChange={inputChange} /></td>
                            <td><input value={supplier.email} onChange={inputChange} /></td>
                            <td><input value={supplier.address} onChange={inputChange} /></td>
                            <td><button onClick={(event) => deleteSupplier_Item(event, supplier.supplierID)}>Remove</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        <div>
            <br></br>
            <h3>Add an existing supplier to item</h3>
            <div>
                <input placeholder="Search By Name" onChange={handleNameToBeSearched} />
                <button onClick={handleSearchedSupplier}>Search</button>
            </div>
            <br />
            {searchedSupplier ? (
                <div>
                    <label>{searchedSupplier.name}</label><br />
                    <label>{searchedSupplier.contactNo}</label><br />
                    <label>{searchedSupplier.email}</label><br />
                    <label>{searchedSupplier.address}</label>
                    <button onClick={addSearchedSupplierToItem}>Add</button>
                </div>
                 
            ) : (
                <label>{''}</label>
            )}
           
        </div><br></br>
        <div>

            <button onClick={handleAddNewSupplier}>Create a new supplier</button>
            
            {addNewSupplier && (
                <div>
                    <label>Name</label>
                    <input onChange={handleName} />
                    <label>Contact No</label>
                    <input onChange={handleContactNo} />
                    <label>Email</label>
                    <input onChange={handleEmail} />
                    <label>Address</label>
                    <input onChange={handleAddress} />
                    <button onClick={handleCreateSupplier}>Add This Supplier to suppliers</button>
                </div>
            )}
        </div>
    </div>
    )
}

export default Suppliers;