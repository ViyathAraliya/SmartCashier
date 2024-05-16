import {createContext, useContext, useEffect, useState} from "react"

const AuthContext=createContext({
    isAuthenticated: false,
    jwtToken: null,
    login:() =>{},
    logout:()=>{}
});


export const AuthProvider=({children})=>{
    const[isAuthenticated,setIsAuthenticated]=useState(false);
    const[jwtToken, setJwtToken]=useState(null);

    const login=(data)=>{
        setIsAuthenticated(true);
        setJwtToken(data);
        localStorage.setItem("jwtToken",data);
    }

    const logout=()=>{
        setIsAuthenticated(false);
        setJwtToken(null);
        localStorage.removeItem("jwtToken");
    }

    useEffect(()=>{
        const token=localStorage.getItem("jwtToken");
        if(token){
            setIsAuthenticated(true);
            setJwtToken(token);
            }
    },[])

    return(
        <AuthContext.Provider value={{isAuthenticated,jwtToken,login,logout}}>
            {children}</AuthContext.Provider>
    );
}
export const useAuth=()=>{
    return useContext(AuthContext);
}