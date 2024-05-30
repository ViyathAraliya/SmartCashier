import axios from "axios";
import { useState } from "react";
import { useAuth } from "../utils/AuthContext";
import { useNavigate } from "react-router-dom";

function CreateUsers(){
    const[username, setUsername]=useState(null);
    const[password,setPassword]=useState(null);
    const[email,setEmail]=useState(null)
    const{isAuthenticated, jwtToken}=useAuth();
    const navigate = useNavigate();
 
    const config={
        headers :{ Authorization:`Bearer ${jwtToken}`}
    }

    function create(){
        const data={
            "userID":null,
            "name":username,
            "password":password,
            "email":email
        }
        axios.post("http://localhost:8080/createUser",data,config)
        .then(function(response){
            alert("user created succesfully")
          
            console.log(response)
            navigate("/")
        })
        .catch(function(error){
            alert(error)
        })
    }


return(
    <div className="main_Users">
        <h2>Greetings!! Register a User to use the app</h2>
        <div className="createUser_Users">
            
            <label>Username</label>
            <input onChange={(e)=>{setUsername(e.target.value); }}/><br></br>
            <label>Password</label>
            <input type="password" onChange={(e)=>{setPassword(e.target.value);}}/>
            <br>
            </br>
            <label>Email</label>
            <input onChange={(e)=>{setEmail(e.target.value);}}/>
            <button onClick={create}>create</button>
        </div>
    </div>
)}
export default CreateUsers;