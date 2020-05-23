var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http) {
    $scope.flights=[];
    $scope.flightsForm={
        idFlight: 1,
        numFlight:"",
        airportsDeparture:"2",
        airportsArrival:"2",
        departureDate:"2",
        departureTime:"",
        aircraftName:""
    };
    _refreshMainData()
    function _refreshMainData() {
        $http({
            method: 'GET',
            url: '/'
        }).then(
            function (res) { // success
                $scope.flights = res.data.flights;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

})