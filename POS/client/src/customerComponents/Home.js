import { Link } from "react-router-dom";

function Home() {


    return (
      <div><label>Home</label>
      <li><Link to="/items">Items</Link></li>
      <li><Link to="/userItems">Access Items</Link></li>
      <li><Link to=""></Link></li>
      </div>
    );
  }
  
  export default Home;