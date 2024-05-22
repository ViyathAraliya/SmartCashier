function Item(){

    return(<div>
        <div className="itemBasics">
        <h3>Add an item</h3>

        <label for="name">Name</label>
        <input id="name"/>
        
        <label for="unit">unit</label>
        <input id="unit"/>

        <label for="categoryID">category</label>
        <input id="categoryID"/>

        <label for="qty_on_hand">qty_on_hand</label>
        <input id="qty_on_hand"/>
        </div>

<div className="supplierForItem">
<h3>su</h3>

        <label for="supplierName">supplier name</label>
        <input for="supplierName"></input>

        <label for="contactNo">Contact Number</label>
<input id="contactNo" />

<label for="email">Email</label>
<input id="email" />

<label for="address">Address</label>
<input id="address"/>

        </div>
    </div>);
}

export default Item;