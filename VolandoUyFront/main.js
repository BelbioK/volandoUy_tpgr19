import {navigate} from './utils/index.js'

$(document).ready(function() {
    const lastRoute = localStorage.getItem("lastRoute");
    let params = {};
    try {
        params = JSON.parse(localStorage.getItem("params"));
    } catch (e) {
        
    }
    console.log("main",params)
    navigate(lastRoute ? lastRoute : "/",params)
});