$(document).ready(function() {
    // Retrieve values from sessionStorage
    var vehicleMake = sessionStorage.getItem('vehicleMake');
    var vehicleModel = sessionStorage.getItem('vehicleModel');
    var onRoadPrice = sessionStorage.getItem('onRoadPrice');
    var calculatedEMI = sessionStorage.getItem('calculatedEMI');
    
    // Set values in the page
    $('#vehicleDetails').text(vehicleMake + ' ' + vehicleModel);
    $('#loanAmount').text(parseInt(onRoadPrice));
    $('#monthlyEMI').text(parseInt(calculatedEMI));
});
