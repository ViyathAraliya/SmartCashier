import { Link } from "react-router-dom";

function Home() {


    return (
      <div><label>Home</label>


<li><Link to="/Category">Manage Categories</Link></li>
<li><Link to="/item">create Item</Link></li>
      <li><Link to="/userItems">Access Items</Link></li>
      <li><Link to="/createUser">Users</Link></li>
      <li><Link to="/stock">Manage Stock</Link></li>
      <li><Link to="/transaction">Make a Transaction</Link></li>
      <li><Link to ="/ViewTransactions">View Transcations</Link></li>
      </div>
    );
  }
  
  export default Home;