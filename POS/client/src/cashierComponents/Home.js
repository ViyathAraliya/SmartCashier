import { Link } from "react-router-dom";

function Home() {


    return (
      <div><label>Home</label>
      <li><Link to="/items">Items</Link></li>
      <li><Link to="/userItems">Access Items</Link></li>
      <li><Link to=""></Link></li>
      <li><Link to="/item">create Item</Link></li>
      <li><Link to="/Category">Manage Categories</Link></li>
      <li><Link to="/stock">Manage Stock</Link></li>
      <li><Link to="/transaction">Make a Transaction</Link></li>
      </div>
    );
  }
  
  export default Home;