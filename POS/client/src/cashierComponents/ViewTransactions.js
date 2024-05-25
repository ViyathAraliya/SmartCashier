import axios from "axios";
import { useEffect, useState } from "react"
import "../css/viewTransaction.css"
import 'bootstrap/dist/css/bootstrap.min.css';

function ViewTransactions() {
    const [transactionDtos, setTransactionDtos] = useState(null)
    const [transaction_itemDtoss, setTransaction_itemDtos] = useState(null)
   
    useEffect(() => {
        loadTranscation();

    }, [])



    
    function loadTranscation() {
        axios.get("http://localhost:8080/loadTransactions")
            .then(function (respnose) {
                setTransactionDtos(respnose.data)

            })
            .catch(function (error) {
                console.log(error)

            })
    }

    function veiwDetails(transactionItemDtos) {
        setTransaction_itemDtos(transactionItemDtos)
    }
    return (<>
    <div className="main_viewTransaction">
    
        <div className="transactionDto_viewTransaction">
        <h2>Transactions</h2>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>customerID
                        </th>
                        <th>phone number</th>
                        <th>Date an Time</th>
                        <th>Total Amount</th>
                    </tr>
                </thead>
                <tbody>
                    {transactionDtos && transactionDtos.map((transactionDto) => (
                        <tr key={transactionDto.transactionID}>
                            <td>{transactionDto.customer.customerID}</td>
                            {<td>{transactionDto.customer.phoneNumber}</td>}
                            <td>{transactionDto.dateTime}</td>
                            <td>{transactionDto.totalAmount}</td>
                            <td>
                                <button onClick={() => { veiwDetails(transactionDto.transaction_Item_dtos) }}>view details</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        <div className="transactionItemDto_ViewTransaction">
        <h2>Transaction Details</h2>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>itemID</th>
                        <th>quantity</th>
                        <th>{"subTotal(Rs.)"}</th>
                    </tr>
                </thead>
                <tbody>{transaction_itemDtoss && transaction_itemDtoss.map((transactionDetail) => (
                    <tr key={transactionDetail.itemID}>
                        <td>{transactionDetail.itemID}</td>
                        <td>{transactionDetail.qty}</td>
                        <td>{transactionDetail.amount}</td>
                    </tr>
                ))}</tbody>

            </table>
        </div></div>

    </>)



}
export default ViewTransactions