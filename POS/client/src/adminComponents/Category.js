import axios from "axios";
import { useEffect, useState } from "react";
import { useAuth } from "../utils/AuthContext";

function Category() {

    const [toBeUpdatedName, set_ToBeUpdatedName] = useState(null)
    const [creatCategoryName, set_CreateCategoryName] = useState(null)
    const [categories, setCategories] = useState(null)


const {isAuthenticated,jwtToken}=useAuth();
const config={
    headers :{ Authorization:`Bearer ${jwtToken}`}
}


    useEffect(() => {if(isAuthenticated){
        loadCateories();

}}, [isAuthenticated])


    function loadCateories() {
        axios.get("http://localhost:8080/loadCategories",config)
            .then(function (response) {
                setCategories(response.data)
                console.log(response)
            })
            .catch(function (error) {
                console.log(error)
            })

    }

    function handle_ToBeUpdatedName(event, id) {
        const tempUpdatedCategoryName = { "id": id, "name": event.target.value }
        console.log("id ", id, "name ", tempUpdatedCategoryName.name)
        set_ToBeUpdatedName(tempUpdatedCategoryName)

    }

    function handle_CreateNewCategoryName(event) {
        set_CreateCategoryName(event.target.value)

    }
    function updateCategory(category) {
        let categoryID = category.categoryID
        let validated_name = category.name

        if (toBeUpdatedName != null && categoryID == toBeUpdatedName.id) {
            validated_name = toBeUpdatedName.name
        }

        const data = {
            "categoryID": category.categoryID,
            "name": validated_name
        }
        axios.post("http://localhost:8080/saveCategory", data,config)
            .then(function (response) {
                console.log(response)
                loadCateories();
                alert("category saved succesfully")
            })
            .catch(function (error) {
                console.log(error)
                alert(error)
            })
       

    }
    function createNewCategory() {
        const data = {
            "categoryID": null,
            "name": creatCategoryName
        }

        axios.post("http://localhost:8080/saveCategory", data,config)
            .then(function (response) {
                console.log(response)
                loadCateories();
                alert("category saved succesfully")
                
            })
            .catch(function (error) {
                console.log(error)
                alert(error)
            })
        
    }

    return (
        <div>
            <div className="createNewCategory">
                <h3>Create a new cateogry</h3>
                <label >category name</label>
                <input onChange={handle_CreateNewCategoryName} />
                <button onClick={createNewCategory}> save</button>

            </div>

            <div className="categories">
                <table>
                    <thead>
                        <tr>
                            <th>categoryID</th>
                            <th>name</th>
                        </tr>

                    </thead>
                    <tbody>
                        {categories && categories.map((category) => <tr key={category.categoryID}>
                            <td key={category.categoryID}>{category.categoryID}</td>
                            <td> <input placeholder={category.name} onChange={(event) => { handle_ToBeUpdatedName(event, category.categoryID) }} /></td>
                            <td><button onClick={() => updateCategory(category)}>update</button></td>
                        </tr>)}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Category;