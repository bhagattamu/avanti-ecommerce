/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
const storageKey = "CART";

function addToCart(product) {
    const rawData = localStorage.getItem(storageKey);
    let stockOut = false;
    if (rawData) {
        let data = JSON.parse(rawData);
        const isAlreadyInCart = data.find(item => item.id === product.id);
        if (isAlreadyInCart) {
            data = data.map(item => {
                if (item.id === product.id) {
                    item.quantity += product.quantity;
                    if (item.quantity > item.stockInHand) {
                        stockOut = true;
                        alert(`We don't have enough stock for item: ${item.name}, total stock: ${item.stockInHand}`);
                    }
                }
                return item;
            });
            localStorage.setItem(storageKey, JSON.stringify(data));
        } else {
            const newData = [...data, product];
            localStorage.setItem(storageKey, JSON.stringify(newData));
        }
        if (stockOut) {
            return;
        }
    } else {
        localStorage.setItem(storageKey, JSON.stringify([product]));
    }
    location.href = "/cart";

}

function onChangeCart(product, event) {
    console.log({product, val: event.value});
//    const productId = document.getElementById("")
//    console.log(product);
//    if (product.quantity > product.stockInHand) {
//        alert(`We don't have enough stock for item: ${product.name}, total stock: ${product.stockInHand}`);
//        return;
//    }
//    const rawData = localStorage.getItem(storageKey);
//    const data = JSON.parse(rawData);
//    const newData = data.map(item => item.id === product.id ? product : item);
//    localStorage.setItem(storageKey, JSON.stringify(newData));
//    location.href = "/cart";

}

function getCartData() {
    return JSON.parse(localStorage.getItem(storageKey));
}

function getNoOfDataInCart() {
    const rawData = localStorage.getItem(storageKey);
    if (rawData) {
        const data = JSON.parse(rawData);
        return data.length;
    }
    return 0;

}

function removeItem(id) {
    const rawData = localStorage.getItem(storageKey);
    if (rawData) {
        const data = JSON.parse(rawData);
        localStorage.setItem(storageKey, JSON.stringify(data.filter(item => item.id != id)));
    }
    location.reload();
}
