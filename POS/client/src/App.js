import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './adminComponents/Login';
import Home from './cashierComponents/Home';

import UserItems from './adminComponents/UserItems';
import Suppliers from './adminComponents/Suppliers';
import Item from './adminComponents/Item';
import Category from './adminComponents/Category';
import Stock from './adminComponents/Stock';
import Transaction from './cashierComponents/Transaction';
import ViewTransactions from './cashierComponents/ViewTransactions';
import ProtectedRoute from './utils/ProtectedRoute';
import { AuthProvider } from './utils/AuthContext';

function App() {
  return (
   <AuthProvider>
    <BrowserRouter>
        <Routes>
          <Route element={<ProtectedRoute />} >
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />

            <Route path="/userItems" element={<UserItems />} />
            <Route path="/suppliers" element={<Suppliers />} />
            <Route path="/Item" element={<Item />} />
            <Route path="/Category" element={<Category />} />
            <Route path="/stock" element={<Stock />} />
            <Route path="/transaction" element={<Transaction />} />
            <Route path="/ViewTransactions" element={<ViewTransactions />} />
          </Route>
          <Route path="/login" element={<Login />} />
        </Routes>
        </BrowserRouter>

        </AuthProvider>
  );
}

export default App;
