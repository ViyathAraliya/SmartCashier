import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './adminComponents/Login';
import Home from './cashierComponents/Home';
import Items from './cashierComponents/Items';
import UserItems from './adminComponents/UserItems';
import Suppliers from './adminComponents/Suppliers';
import Item from './adminComponents/Item';
import Category from './adminComponents/Category';
import Stock from './adminComponents/Stock';

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
       <Route path="/Category" element={<Category/>}/>
       <Route path="/stock" element={<Stock/>}/>
       </Routes>
       </BrowserRouter>
    </div>
  );
}

export default App;
