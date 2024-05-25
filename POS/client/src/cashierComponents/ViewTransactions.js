import axios from "axios";
import { useEffect, useState } from "react"

function ViewTransactions(){
    const[transactionDtos,setTransactionDtos]=useState(null)
    const[transaction_itemDtoss,setTransaction_itemDtos]=useState(null)
    const[checkNull,setCheckNull]=useState(null)
useEffect(()=>{
    loadTranscation();
    
},[])

useEffect(()=>{
setCheckNull()
},[transactionDtos])


function hh(){
   let g=transactionDtos.length;
   console.log(g)
}
function loadTranscation(){
    axios.get("http://localhost:8080/loadTransactions")
    .then(function(respnose){
        setTransactionDtos(respnose.data)
     
    })
    .catch(function(error){
        console.log(error)
        
    })
}

    return(<><h2>Transactions</h2><button onClick={hh}>check</button>
    <label>ss</label>
    <label>{transactionDtos==null}</label>
    <table>
        <thead>
            <tr>
                <th>customerID       
                </th>
                <th>Date an Time</th>
                <th>Total Amount</th>
            </tr>
        </thead>
        <tbody>
            {transactionDtos && transactionDtos.map((transactionDto)=>(
                <tr key={transactionDto.transactionID}>
                    <td>{transactionDto.customer.customerID}</td>
                    <td>{transactionDto.dateTime}</td>
                    <td>{transactionDto.totalAmount}</td>
                </tr>
            ))}
        </tbody>
    </table>
    
    </>)



}
export default ViewTransactions