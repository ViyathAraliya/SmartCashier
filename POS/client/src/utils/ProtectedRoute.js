import { Navigate,Outlet } from "react-router-dom";
import { useAuth } from "./AuthContext";

const ProtectedRoute=()=>{
    const{isAuthenticated}=useAuth();

    if(isAuthenticated){
        return <Outlet/>
    }
    else{
        return(
            <Navigate to="/login"/>
        )
    }

}
export default ProtectedRoute;