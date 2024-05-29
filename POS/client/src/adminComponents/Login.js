import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { useAuth } from "../utils/AuthContext";
import axios from "axios";

const Login = () => {
  const [userName, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const { login } = useAuth();
  const navigate = useNavigate();






  const handleLogin = (e) => {
    e.preventDefault();
    console.log(22)

    const data = {
      userName: userName,
      password: password,
    }


    axios.post("http://localhost:8080/auth/login", data)
      .then((response) => {
        login(response.data);
        console.log("login succesful")
       
        navigate("/")
      })
      .catch((error) => {
        alert("Invalid Credentials")
        console.log(error);
      });

  }

  return (
    <div>
      <h2>Login page</h2>
      <div>
        <div>
          <label>Username</label>
          <input type="text" onChange={(e) => setUsername(e.target.value)} />
        </div>
        <div>
          <label>Password</label>
          <input type="password" onChange={(e) => setPassword(e.target.value)} />
        </div>
        <button onClick={handleLogin}>Login</button>
      </div>
      <li><Link to="/">Home</Link></li>
    </div>
  )


}
export default Login;