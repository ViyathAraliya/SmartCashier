import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './userComponents/Login';
import Home from './customerComponents/Home';
import Items from './customerComponents/Items';
import UserItems from './userComponents/UserItems';
import Suppliers from './userComponents/Suppliers';
import Item from './userComponents/Item';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
          <Route path="/" element={<Home/>} />
       <Route path="/login" element={<Login/>} />
       <Route path="/items" element={<Items/>}/>
       <Route path="/userItems" element={<UserItems/>}/>
       <Route path="/suppliers" element={<Suppliers/>}/>
       <Route path="/Item" element={<Item/>}/>
       </Routes>
       </BrowserRouter>
    </div>
  );
}

export default App;
